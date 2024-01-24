package com.ejie.x21a.model;

import java.util.List;

public class TableOptions {
    private List<String> plugins;
    private List<String> filterType;
    private List<String> selectionType;
    private List<String> editType;
    private List<String> otros;

    public List<String> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<String> plugins) {
        this.plugins = plugins;
    }

    public List<String> getFilterType() {
		return filterType;
	}

	public void setFilterType(List<String> filterType) {
		this.filterType = filterType;
	}

	public List<String> getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(List<String> selectionType) {
        this.selectionType = selectionType;
    }

    public List<String> getEditType() {
		return editType;
	}

	public void setEditType(List<String> editType) {
		this.editType = editType;
	}

	public List<String> getOtros() {
        return otros;
    }

    public void setOtros(List<String> otros) {
        this.otros = otros;
    }
}
