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

	public List<DataEntryGui> getDomainData(String domain) {
		List<DataEntryGui> ret = new ArrayList<>();

		Optional<DomainEntity> dom = domainService.getDomain(domain);

		if (dom.isPresent()) {
			ret = map(dataEntryService.getEntries(dom.get()));
		}

		return ret;
	}

	private IndexDomainEntry map(String name, String selected) {
		IndexDomainEntry ret = new IndexDomainEntry();

		ret.setName(name);
		ret.setSelected(name.equals(selected));

		return ret;
	}

	private List<DataEntryGui> map(List<DataEntryEntity> entities) {
		Map<String, DataEntryGui> tmp = new HashMap<>();

		for (DataEntryEntity it : entities) {
			DataEntryGui deg;
			if (!tmp.containsKey(it.getCategory())) {
				deg = new DataEntryGui();
				deg.setCategory(it.getCategory());
				deg.setEntry(it.getEntry());
				deg.setLastChanged(format(DateUtils.fromMillies(it.getLastModified())));
			} else {
				deg = tmp.get(it.getCategory());
			}
			DataRowGui drg = map(it);
			deg.addDataRow(drg);
			tmp.put(it.getCategory(), deg);
		}

		List<DataEntryGui> ret = tmp.values().stream().toList();

		return ret;
	}
	
	private String format(LocalDateTime date) {
		if (dtf == null) {
			dtf = DateTimeFormatter.ofPattern(dateFormat);
		}
		return date.format(dtf);
	}

	private DataRowGui map(DataEntryEntity entity) {
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
