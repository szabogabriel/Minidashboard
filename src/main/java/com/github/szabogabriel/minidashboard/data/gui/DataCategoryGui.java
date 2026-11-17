package com.github.szabogabriel.minidashboard.data.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	public String getEntryDescription() { return entryDescription; }
	public void setEntryDescription(String entryDescription) { this.entryDescription = entryDescription; }
	public String getTableHeader0() { return tableHeader0; }
	public void setTableHeader0(String tableHeader0) { this.tableHeader0 = tableHeader0; }
	public String getTableHeader1() { return tableHeader1; }
	public void setTableHeader1(String tableHeader1) { this.tableHeader1 = tableHeader1; }
	public String getTableHeader2() { return tableHeader2; }
	public void setTableHeader2(String tableHeader2) { this.tableHeader2 = tableHeader2; }
	public String getTableHeader3() { return tableHeader3; }
	public void setTableHeader3(String tableHeader3) { this.tableHeader3 = tableHeader3; }
	public String getTableHeader4() { return tableHeader4; }
	public void setTableHeader4(String tableHeader4) { this.tableHeader4 = tableHeader4; }
	public String getTableHeader5() { return tableHeader5; }
	public void setTableHeader5(String tableHeader5) { this.tableHeader5 = tableHeader5; }
	public String getTableHeader6() { return tableHeader6; }
	public void setTableHeader6(String tableHeader6) { this.tableHeader6 = tableHeader6; }
	public String getTableHeader7() { return tableHeader7; }
	public void setTableHeader7(String tableHeader7) { this.tableHeader7 = tableHeader7; }
	public boolean isLevel0() { return isLevel0; }
	public void setLevel0(boolean isLevel0) { this.isLevel0 = isLevel0; }
	public boolean isLevel1() { return isLevel1; }
	public void setLevel1(boolean isLevel1) { this.isLevel1 = isLevel1; }
	public boolean isLevel2() { return isLevel2; }
	public void setLevel2(boolean isLevel2) { this.isLevel2 = isLevel2; }
	public boolean isLevel3() { return isLevel3; }
	public void setLevel3(boolean isLevel3) { this.isLevel3 = isLevel3; }
	public boolean isLevel4() { return isLevel4; }
	public void setLevel4(boolean isLevel4) { this.isLevel4 = isLevel4; }
	public boolean isLevel5() { return isLevel5; }
	public void setLevel5(boolean isLevel5) { this.isLevel5 = isLevel5; }
	public boolean isLevel6() { return isLevel6; }
	public void setLevel6(boolean isLevel6) { this.isLevel6 = isLevel6; }
	public boolean isLevel7() { return isLevel7; }
	public void setLevel7(boolean isLevel7) { this.isLevel7 = isLevel7; }
	public List<DataEntryGui> getDataEntries() { return dataEntries; }
	public void setDataEntries(List<DataEntryGui> dataEntries) { this.dataEntries = dataEntries; }

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

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof DataCategoryGui)) return false;
		DataCategoryGui that = (DataCategoryGui) other;
		return that.canEqual(this)
				&& isLevel0 == that.isLevel0
				&& isLevel1 == that.isLevel1
				&& isLevel2 == that.isLevel2
				&& isLevel3 == that.isLevel3
				&& isLevel4 == that.isLevel4
				&& isLevel5 == that.isLevel5
				&& isLevel6 == that.isLevel6
				&& isLevel7 == that.isLevel7
				&& Objects.equals(category, that.category)
				&& Objects.equals(entryDescription, that.entryDescription)
				&& Objects.equals(tableHeader0, that.tableHeader0)
				&& Objects.equals(tableHeader1, that.tableHeader1)
				&& Objects.equals(tableHeader2, that.tableHeader2)
				&& Objects.equals(tableHeader3, that.tableHeader3)
				&& Objects.equals(tableHeader4, that.tableHeader4)
				&& Objects.equals(tableHeader5, that.tableHeader5)
				&& Objects.equals(tableHeader6, that.tableHeader6)
				&& Objects.equals(tableHeader7, that.tableHeader7)
				&& Objects.equals(dataEntries, that.dataEntries);
	}

	protected boolean canEqual(Object other) {
		return other instanceof DataCategoryGui;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, entryDescription, tableHeader0, tableHeader1, tableHeader2,
				tableHeader3, tableHeader4, tableHeader5, tableHeader6, tableHeader7, isLevel0,
				isLevel1, isLevel2, isLevel3, isLevel4, isLevel5, isLevel6, isLevel7, dataEntries);
	}

	@Override
	public String toString() {
		return "DataCategoryGui(category=" + category + ", entryDescription=" + entryDescription
				+ ", tableHeader0=" + tableHeader0 + ", tableHeader1=" + tableHeader1
				+ ", tableHeader2=" + tableHeader2 + ", tableHeader3=" + tableHeader3
				+ ", tableHeader4=" + tableHeader4 + ", tableHeader5=" + tableHeader5
				+ ", tableHeader6=" + tableHeader6 + ", tableHeader7=" + tableHeader7
				+ ", isLevel0=" + isLevel0 + ", isLevel1=" + isLevel1 + ", isLevel2=" + isLevel2
				+ ", isLevel3=" + isLevel3 + ", isLevel4=" + isLevel4 + ", isLevel5=" + isLevel5
				+ ", isLevel6=" + isLevel6 + ", isLevel7=" + isLevel7 + ", dataEntries=" + dataEntries + ")";
	}
	
}
