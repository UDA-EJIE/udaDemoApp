package com.ejie.aa79b.model;

/**
 *
 * @author aresua
 *
 */
public class RdoBatch {

    private boolean exito = false;
    private int nRet = 0;
    private long lNumeroPeticion = 0;
    private String sFechaPeticion = "Fecha";
    private long lErrorCodigo = 0;
    private String sErrorDescripcion = "Error Default";
    private int nErrorSQL = 0;
    private String sErrorMotivo = "La clase esta sin acabar";

    /**
     * @return the exito.
     */
    public boolean isExito() {
        return this.exito;
    }

    /**
     * @param exito
     *            the exito to set.
     */
    public void setExito(boolean exito) {
        this.exito = exito;
    }

    /**
     * @return the nRet.
     */
    public int getnRet() {
        return this.nRet;
    }

    /**
     * @param nRet
     *            the nRet to set.
     */
    public void setnRet(int nRet) {
        this.nRet = nRet;
    }

    /**
     * @return the lNumeroPeticion.
     */
    public long getlNumeroPeticion() {
        return this.lNumeroPeticion;
    }

    /**
     * @param lNumeroPeticion
     *            the lNumeroPeticion to set.
     */
    public void setlNumeroPeticion(long lNumeroPeticion) {
        this.lNumeroPeticion = lNumeroPeticion;
    }

    /**
     * @return the sFechaPeticion.
     */
    public String getsFechaPeticion() {
        return this.sFechaPeticion;
    }

    /**
     * @param sFechaPeticion
     *            the sFechaPeticion to set.
     */
    public void setsFechaPeticion(String sFechaPeticion) {
        this.sFechaPeticion = sFechaPeticion;
    }

    /**
     * @return the lErrorCodigo.
     */
    public long getlErrorCodigo() {
        return this.lErrorCodigo;
    }

    /**
     * @param lErrorCodigo
     *            the lErrorCodigo to set.
     */
    public void setlErrorCodigo(long lErrorCodigo) {
        this.lErrorCodigo = lErrorCodigo;
    }

    /**
     * @return the sErrorDescripcion.
     */
    public String getsErrorDescripcion() {
        return this.sErrorDescripcion;
    }

    /**
     * @param sErrorDescripcion
     *            the sErrorDescripcion to set.
     */
    public void setsErrorDescripcion(String sErrorDescripcion) {
        this.sErrorDescripcion = sErrorDescripcion;
    }

    /**
     * @return the nErrorSQL.
     */
    public int getnErrorSQL() {
        return this.nErrorSQL;
    }

    /**
     * @param nErrorSQL
     *            the nErrorSQL to set.
     */
    public void setnErrorSQL(int nErrorSQL) {
        this.nErrorSQL = nErrorSQL;
    }

    /**
     * @return the sErrorMotivo.
     */
    public String getsErrorMotivo() {
        return this.sErrorMotivo;
    }

    /**
     * @param sErrorMotivo
     *            the sErrorMotivo to set.
     */
    public void setsErrorMotivo(String sErrorMotivo) {
        this.sErrorMotivo = sErrorMotivo;
    }

}