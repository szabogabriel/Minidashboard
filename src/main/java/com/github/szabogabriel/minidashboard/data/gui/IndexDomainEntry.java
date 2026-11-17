package com.github.szabogabriel.minidashboard.data.gui;

import java.util.Objects;

public class IndexDomainEntry {
	
	private String name;
	private boolean selected;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof IndexDomainEntry)) return false;
		IndexDomainEntry that = (IndexDomainEntry) other;
		return that.canEqual(this) && selected == that.selected && Objects.equals(name, that.name);
	}

	protected boolean canEqual(Object other) {
		return other instanceof IndexDomainEntry;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, selected);
	}

	@Override
	public String toString() {
		return "IndexDomainEntry(name=" + name + ", selected=" + selected + ")";
	}
}
