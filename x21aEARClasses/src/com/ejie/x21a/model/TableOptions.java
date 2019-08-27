package com.ejie.x21a.model;

import java.util.List;

public class TableOptions {
    private List<String> plugins;
    private List<String> tipoSeleccionTabla;
    private List<String> otros;

    public List<String> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<String> plugins) {
        this.plugins = plugins;
    }

    public List<String> getTipoSeleccionTabla() {
        return tipoSeleccionTabla;
    }

    public void setTipoSeleccionTabla(List<String> tipoSeleccionTabla) {
        this.tipoSeleccionTabla = tipoSeleccionTabla;
    }

    public List<String> getOtros() {
        return otros;
    }

    public void setOtros(List<String> otros) {
        this.otros = otros;
    }
}
