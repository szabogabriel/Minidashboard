package com.github.szabogabriel.minidashboard.data.gui;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataEntryGui {

	private String domain;
	
	private String entry;
	
	private String lastChanged;
	private String created;
	private String validTo;
	
	private Boolean isLevel0 = Boolean.FALSE;
	private Boolean isLevel1 = Boolean.FALSE;
	private Boolean isLevel2 = Boolean.FALSE;
	private Boolean isLevel3 = Boolean.FALSE;
	private Boolean isLevel4 = Boolean.FALSE;
	private Boolean isLevel5 = Boolean.FALSE;
	private Boolean isLevel6 = Boolean.FALSE;
	private Boolean isLevel7 = Boolean.FALSE;
	
	private List<DataRowGui> dataRows;
	
	public void addDataRow(DataRowGui row) {
		if (dataRows == null) {
			dataRows = new ArrayList<>();
		}
		dataRows.add(row);
		
		if (row.getLevel0() != null) isLevel0 = Boolean.TRUE;
		if (row.getLevel1() != null) isLevel1 = Boolean.TRUE;
		if (row.getLevel2() != null) isLevel2 = Boolean.TRUE;
		if (row.getLevel3() != null) isLevel3 = Boolean.TRUE;
		if (row.getLevel4() != null) isLevel4 = Boolean.TRUE;
		if (row.getLevel5() != null) isLevel5 = Boolean.TRUE;
		if (row.getLevel6() != null) isLevel6 = Boolean.TRUE;
		if (row.getLevel7() != null) isLevel7 = Boolean.TRUE;
	}

	public int getMaxIsLevel() {
		if (isLevel7) return 7;
		if (isLevel6) return 6;
		if (isLevel5) return 5;
		if (isLevel4) return 4;
		if (isLevel3) return 3;
		if (isLevel2) return 2;
		if (isLevel1) return 1;
		return 0;
	}

	public void revalidateDataToMaxLevel(int level) {
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
	}

}
