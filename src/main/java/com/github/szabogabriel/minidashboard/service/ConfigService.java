package com.github.szabogabriel.minidashboard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.entites.ConfigurationEntity;
import com.github.szabogabriel.minidashboard.data.enums.ConfigurationEnum;
import com.github.szabogabriel.minidashboard.data.enums.ConfigurationTypeEnum;
import com.github.szabogabriel.minidashboard.repository.ConfigRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepo;

    private Map<String, String> cache = new HashMap<>();

    @PostConstruct
    public void init() {
        Set<String> dbConfigs = getAllEntries().stream().map(e -> e.getConfKey()).collect(Collectors.toSet());
        List<ConfigurationEntity> newEntities = new ArrayList<>();

        for (ConfigurationEnum it : ConfigurationEnum.values()) {
            if (!dbConfigs.contains(it.getKey())) {
                ConfigurationEntity newEntity = new ConfigurationEntity();
                newEntity.setConfKey(it.getKey());
                newEntity.setConfValue(it.getValue());
                newEntities.add(newEntity);
            }
        }

        if (!newEntities.isEmpty()) {
            for (ConfigurationEntity it : newEntities) {
                configRepo.save(it);
            }
        }
    }

    public List<String> getValue(String key) {
        List<String> ret = new ArrayList<>();
        if (key.contains("*")) {
            String selectKey = key.replaceAll("*", "%");

            List<ConfigurationEntity> data = configRepo.findAllEntitiesViaWildcard(selectKey);

            ret = data.stream().map(e -> e.getConfValue()).collect(Collectors.toList());
        } else if (!cache.containsKey(key)) {
            Optional<ConfigurationEntity> configEntity = configRepo.findByConfKey(key);
            String data = "";

            if (configEntity.isPresent()) {
                data = configEntity.get().getConfValue();
            }

            cache.put(key, data);

            ret = Arrays.asList(cache.get(key));
        } else {
            ret = Arrays.asList(cache.get(key));
        }

        return ret;
    }

    public String getValue(ConfigurationEnum config) {
        return getValue(config.getKey()).get(0);
    }

    public void setValue(String key, String value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
        if (Strings.isNotEmpty(value)) {
            Optional<ConfigurationEntity> entity = configRepo.findByConfKey(key);
            if (entity.isPresent()) {
                ConfigurationEntity confEntity = entity.get();
                confEntity.setConfValue(value);
                configRepo.save(confEntity);
            } else {
                ConfigurationEntity confEntity = new ConfigurationEntity();
                confEntity.setConfKey(key);
                confEntity.setConfValue(value);
                configRepo.save(confEntity);
            }
        } else {
            Optional<ConfigurationEntity> entity = configRepo.findByConfKey(key);
            if (entity.isPresent() && !isDefaultConfigKey(key)) {
                configRepo.delete(entity.get());
            }
        }
    }

    public List<String> getEntryDescription(ConfigurationTypeEnum type, String domain, String category) {
        String key = type.getCategory() + "/" + domain + "/" + category + "/entry";
        return getValue(key);
    }

    public String[] getTableHeaderValue(String domain, String category) {
        String[] ret = new String[] { "", "", "", "", "", "", "", "" };

        for (int i = 0; i < ret.length; i++) {
            String key = ConfigurationTypeEnum.TABLE_HEADER.getCategory() + "/" + domain + "/" + category + "/level"
                    + i;
            ret[i] = getValue(key).get(0);
        }

        return ret;
    }

    private boolean isDefaultConfigKey(String key) {
        for (ConfigurationEnum it : ConfigurationEnum.values()) {
            if (it.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void createConfig(String key, String value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
        ConfigurationEntity tmp = configRepo.findByConfKey(key).orElse(new ConfigurationEntity());

        tmp.setConfKey(key);
        tmp.setConfValue(value);

        configRepo.save(tmp);
    }

    public List<ConfigurationEntity> getAllEntries() {
        return configRepo.findAll().stream().sorted((e1, e2) -> e1.getConfKey().compareTo(e2.getConfKey()))
                .collect(Collectors.toList());
    }

}
