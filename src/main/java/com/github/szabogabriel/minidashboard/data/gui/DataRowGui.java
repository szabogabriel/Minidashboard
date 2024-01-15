package com.github.szabogabriel.minidashboard.data.gui;

import lombok.Data;

@Data
public class DataRowGui {

	private String level0;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private String level6;
	private String level7;

	public void revalidateDataToLevel(int level) {
		if (level >= 0) {
			if (level0 == null) level0 = "";
		}
		if (level >= 1) {
			if (level1 == null) level1 = "";
		}
		if (level >= 2) {
			if (level2 == null) level2 = "";
		}
		if (level >= 3) {
			if (level3 == null) level3 = "";
		}
		if (level >= 4) {
			if (level4 == null) level4 = "";
		}
		if (level >= 5) {
			if (level5 == null) level5 = "";
		}
		if (level >= 6) {
			if (level6 == null) level6 = "";
		}
		if (level == 7) {
			if (level7 == null) level7 = "";
		}
	}
	
}
