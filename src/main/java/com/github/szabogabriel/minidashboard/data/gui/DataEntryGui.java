package com.github.szabogabriel.minidashboard.data.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	public String getDomain() { return domain; }
	public void setDomain(String domain) { this.domain = domain; }
	public String getEntry() { return entry; }
	public void setEntry(String entry) { this.entry = entry; }
	public String getLastChanged() { return lastChanged; }
	public void setLastChanged(String lastChanged) { this.lastChanged = lastChanged; }
	public String getCreated() { return created; }
	public void setCreated(String created) { this.created = created; }
	public String getValidTo() { return validTo; }
	public void setValidTo(String validTo) { this.validTo = validTo; }
	public Boolean getIsLevel0() { return isLevel0; }
	public void setIsLevel0(Boolean isLevel0) { this.isLevel0 = isLevel0; }
	public Boolean getIsLevel1() { return isLevel1; }
	public void setIsLevel1(Boolean isLevel1) { this.isLevel1 = isLevel1; }
	public Boolean getIsLevel2() { return isLevel2; }
	public void setIsLevel2(Boolean isLevel2) { this.isLevel2 = isLevel2; }
	public Boolean getIsLevel3() { return isLevel3; }
	public void setIsLevel3(Boolean isLevel3) { this.isLevel3 = isLevel3; }
	public Boolean getIsLevel4() { return isLevel4; }
	public void setIsLevel4(Boolean isLevel4) { this.isLevel4 = isLevel4; }
	public Boolean getIsLevel5() { return isLevel5; }
	public void setIsLevel5(Boolean isLevel5) { this.isLevel5 = isLevel5; }
	public Boolean getIsLevel6() { return isLevel6; }
	public void setIsLevel6(Boolean isLevel6) { this.isLevel6 = isLevel6; }
	public Boolean getIsLevel7() { return isLevel7; }
	public void setIsLevel7(Boolean isLevel7) { this.isLevel7 = isLevel7; }
	public List<DataRowGui> getDataRows() { return dataRows; }
	public void setDataRows(List<DataRowGui> dataRows) { this.dataRows = dataRows; }

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

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof DataEntryGui)) return false;
		DataEntryGui that = (DataEntryGui) other;
		return that.canEqual(this)
				&& Objects.equals(domain, that.domain)
				&& Objects.equals(entry, that.entry)
				&& Objects.equals(lastChanged, that.lastChanged)
				&& Objects.equals(created, that.created)
				&& Objects.equals(validTo, that.validTo)
				&& Objects.equals(isLevel0, that.isLevel0)
				&& Objects.equals(isLevel1, that.isLevel1)
				&& Objects.equals(isLevel2, that.isLevel2)
				&& Objects.equals(isLevel3, that.isLevel3)
				&& Objects.equals(isLevel4, that.isLevel4)
				&& Objects.equals(isLevel5, that.isLevel5)
				&& Objects.equals(isLevel6, that.isLevel6)
				&& Objects.equals(isLevel7, that.isLevel7)
				&& Objects.equals(dataRows, that.dataRows);
	}

	protected boolean canEqual(Object other) {
		return other instanceof DataEntryGui;
	}

	@Override
	public int hashCode() {
		return Objects.hash(domain, entry, lastChanged, created, validTo, isLevel0, isLevel1,
				isLevel2, isLevel3, isLevel4, isLevel5, isLevel6, isLevel7, dataRows);
	}

	@Override
	public String toString() {
		return "DataEntryGui(domain=" + domain + ", entry=" + entry + ", lastChanged=" + lastChanged
				+ ", created=" + created + ", validTo=" + validTo + ", isLevel0=" + isLevel0
				+ ", isLevel1=" + isLevel1 + ", isLevel2=" + isLevel2 + ", isLevel3=" + isLevel3
				+ ", isLevel4=" + isLevel4 + ", isLevel5=" + isLevel5 + ", isLevel6=" + isLevel6
				+ ", isLevel7=" + isLevel7 + ", dataRows=" + dataRows + ")";
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
