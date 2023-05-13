package com.github.szabogabriel.minidashboard.data.gui;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataCategoryGui {

	private String category;
	
	private List<DataEntryGui> dataEntries;
	
	public void addDataEntry(DataEntryGui dataEntry) {
		if (dataEntries == null) {
			dataEntries = new ArrayList<>();
		}
		dataEntries.add(dataEntry);
	}
	
}
