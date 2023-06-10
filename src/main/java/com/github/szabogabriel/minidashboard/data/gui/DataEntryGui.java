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

}
