package com.github.szabogabriel.minidashboard.data.api;

import java.util.Objects;

public class DataRequest {
	
	private String level0;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private String level6;
	private String level7;

	public String getLevel0() { return level0; }
	public void setLevel0(String level0) { this.level0 = level0; }
	public String getLevel1() { return level1; }
	public void setLevel1(String level1) { this.level1 = level1; }
	public String getLevel2() { return level2; }
	public void setLevel2(String level2) { this.level2 = level2; }
	public String getLevel3() { return level3; }
	public void setLevel3(String level3) { this.level3 = level3; }
	public String getLevel4() { return level4; }
	public void setLevel4(String level4) { this.level4 = level4; }
	public String getLevel5() { return level5; }
	public void setLevel5(String level5) { this.level5 = level5; }
	public String getLevel6() { return level6; }
	public void setLevel6(String level6) { this.level6 = level6; }
	public String getLevel7() { return level7; }
	public void setLevel7(String level7) { this.level7 = level7; }

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof DataRequest)) return false;
		DataRequest that = (DataRequest) other;
		return that.canEqual(this)
				&& Objects.equals(level0, that.level0)
				&& Objects.equals(level1, that.level1)
				&& Objects.equals(level2, that.level2)
				&& Objects.equals(level3, that.level3)
				&& Objects.equals(level4, that.level4)
				&& Objects.equals(level5, that.level5)
				&& Objects.equals(level6, that.level6)
				&& Objects.equals(level7, that.level7);
	}

	protected boolean canEqual(Object other) {
		return other instanceof DataRequest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(level0, level1, level2, level3, level4, level5, level6, level7);
	}

	@Override
	public String toString() {
		return "DataRequest(level0=" + level0 + ", level1=" + level1 + ", level2=" + level2
				+ ", level3=" + level3 + ", level4=" + level4 + ", level5=" + level5
				+ ", level6=" + level6 + ", level7=" + level7 + ")";
	}
}
