package com.github.szabogabriel.minidashboard.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;
import com.github.szabogabriel.minidashboard.data.gui.DataCategoryGui;
import com.github.szabogabriel.minidashboard.data.gui.DataEntryGui;
import com.github.szabogabriel.minidashboard.data.gui.DataRowGui;
import com.github.szabogabriel.minidashboard.data.gui.IndexDomainEntry;
import com.github.szabogabriel.minidashboard.util.DateUtils;

@Service
public class GuiService {

	@Autowired
	private DataEntryService dataEntryService;

	@Autowired
	private DomainService domainService;

	@Value("${gui.date.format}")
	private String dateFormat;

	private DateTimeFormatter dtf = null;

	public List<IndexDomainEntry> getIndexDomainEntries(String selectedDomain) {
		return domainService.getDomainNames().stream().map(e -> map(e, selectedDomain)).collect(Collectors.toList());
	}

	public List<DataCategoryGui> getCurrentDomainData(String domain) {
		List<DataCategoryGui> ret = new ArrayList<>();

		Optional<DomainEntity> dom = domainService.getDomain(domain);

		if (dom.isPresent()) {
			ret = map(dataEntryService.getCurrentEntries(dom.get()).stream().sorted(this::comparator)
					.collect(Collectors.toList()));
		}

		return ret.stream().sorted(this::comparator).collect(Collectors.toList());
	}
	
	private int comparator(DataCategoryGui g1, DataCategoryGui g2) {
		return g1.getCategory().compareTo(g2.getCategory());
	}

	private int comparator(DataEntryEntity e1, DataEntryEntity e2) {
		return e1.getEntry().compareTo(e2.getEntry());
	}

	private IndexDomainEntry map(String name, String selected) {
		IndexDomainEntry ret = new IndexDomainEntry();

		ret.setName(name);
		ret.setSelected(name.equals(selected));

		return ret;
	}

	private List<DataCategoryGui> map(List<DataEntryEntity> entities) {
		Map<String, DataCategoryGui> tmp = new HashMap<>();

		for (DataEntryEntity it : entities) {
			DataCategoryGui dcg;
			if (!tmp.containsKey(it.getCategory())) {
				dcg = new DataCategoryGui();
				dcg.setCategory(it.getCategory());
			} else {
				dcg = tmp.get(it.getCategory());
			}

			dcg.addDataEntry(mapToEntryGui(it));
			tmp.put(it.getCategory(), dcg);
		}

		List<DataCategoryGui> ret = tmp.values().stream().toList();

		return ret;
	}

	private DataEntryGui mapToEntryGui(DataEntryEntity dataEntryEntity) {
		DataEntryGui ret = new DataEntryGui();

		ret.setEntry(dataEntryEntity.getEntry());
		ret.setLastChanged(format(DateUtils.fromMillies(dataEntryEntity.getLastModified())));

		DataRowGui drg = mapToRowGui(dataEntryEntity);
		ret.addDataRow(drg);

		return ret;
	}

	private String format(LocalDateTime date) {
		if (dtf == null) {
			dtf = DateTimeFormatter.ofPattern(dateFormat);
		}
		return date.format(dtf);
	}

	private DataRowGui mapToRowGui(DataEntryEntity entity) {
		DataRowGui ret = new DataRowGui();

		ret.setLevel0(entity.getLevel0());
		ret.setLevel1(entity.getLevel1());
		ret.setLevel2(entity.getLevel2());
		ret.setLevel3(entity.getLevel3());
		ret.setLevel4(entity.getLevel4());
		ret.setLevel5(entity.getLevel5());
		ret.setLevel6(entity.getLevel6());
		ret.setLevel7(entity.getLevel7());

		return ret;
	}

}
