package com.github.szabogabriel.minidashboard.service;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.dto.DataEntryDto;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.handler.DefaultEntryHandler;
import com.github.szabogabriel.minidashboard.handler.EntryHandler;

@Service
public class EntryHandlerService {

    private final EntryHandler DEFAULT = new DefaultEntryHandler();

    @Autowired
    private ConfigService configService;

    private Map<String, EntryHandler> handlers = new HashMap<>();

    public DataEntryDto handleDataEntry(DataEntryEntity toBeHandled) {
        EntryHandler handler = getEntryHandler(toBeHandled.getDomain().getName(), toBeHandled.getCategory(),
                toBeHandled.getEntry());

        return handler.handle(toBeHandled);
    }

    public EntryHandler getEntryHandler(String domain, String category, String entry) {
        String handler = configService.getEntryHandler(domain, category, entry);
        
        return getHandler(handler);
    }

    private EntryHandler getHandler(String handlerPackage) {
        EntryHandler ret = DEFAULT;
        
        if (handlerPackage != null && !handlerPackage.isEmpty()) {
            if (handlers.containsKey(handlerPackage)) {
                ret = handlers.get(handlerPackage);
            } else {
                try {
                    Constructor<?> cons [] = Class.forName(handlerPackage).getConstructors();
                    if (cons != null && cons.length > 0) {
                        Object o = cons[0].newInstance();

                        if (o instanceof EntryHandler) {
                            ret = (EntryHandler)o;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return ret;
    }

}
