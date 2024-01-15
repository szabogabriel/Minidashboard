package com.github.szabogabriel.minidashboard.data.gui;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataCategoryGui {

	private String category;

	private String entryDescription;

	private String tableHeader0;
	private String tableHeader1;
	private String tableHeader2;
	private String tableHeader3;
	private String tableHeader4;
	private String tableHeader5;
	private String tableHeader6;
	private String tableHeader7;

	public boolean isLevel0;
	public boolean isLevel1;
	public boolean isLevel2;
	public boolean isLevel3;
	public boolean isLevel4;
	public boolean isLevel5;
	public boolean isLevel6;
	public boolean isLevel7;
	
	private List<DataEntryGui> dataEntries;
	
	public void addDataEntry(DataEntryGui dataEntry) {
		if (dataEntries == null) {
			dataEntries = new ArrayList<>();
		}
		dataEntries.add(dataEntry);
	}

	public void setMaxIsLevel(int level) {
		if (level >= 0) {
			isLevel0 = true;
		}
		if (level >= 1) {
			isLevel1 = true;
		}
		if (level >= 2) {
			isLevel2 = true;
		}
		if (level >= 3) {
			isLevel3 = true;
		}
		if (level >= 4) {
			isLevel4 = true;
		}
		if (level >= 5) {
			isLevel5 = true;
		}
		if (level >= 6) {
			isLevel6 = true;
		}
		if (level == 7) {
			isLevel7 = true;
		}
		if (dataEntries != null) {
			for (DataEntryGui it : dataEntries) {
				it.revalidateDataToMaxLevel(level);
			}
		}
	}
	
}
