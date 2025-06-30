package aa79b.util.beans;

import java.io.Serializable;


public class Entidad implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipo;
    private Integer codigo;
    private String descAmpEs;
    private String descAmpEu;
    private String descEs;
    private String descEu;
    private String cif;
    private String estado;
    private Direccion direccion = new Direccion();

    private String tipoDesc;

    /**
     * @return the tipo
     */
    public String getTipo() {
        return this.tipo;
    }
    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return this.codigo;
    }
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    /**
     * @return the descAmpEs
     */
    public String getDescAmpEs() {
        return this.descAmpEs;
    }
    /**
     * @param descAmpEs the descAmpEs to set
     */
    public void setDescAmpEs(String descAmpEs) {
        this.descAmpEs = descAmpEs;
    }
    /**
     * @return the descAmpEu
     */
    public String getDescAmpEu() {
        return this.descAmpEu;
    }
    /**
     * @param descAmpEu the descAmpEu to set
     */
    public void setDescAmpEu(String descAmpEu) {
        this.descAmpEu = descAmpEu;
    }
    /**
     * @return the descEs
     */
    public String getDescEs() {
        return this.descEs;
    }
    /**
     * @param descEs the descEs to set
     */
    public void setDescEs(String descEs) {
        this.descEs = descEs;
    }
    /**
     * @return the descEu
     */
    public String getDescEu() {
        return this.descEu;
    }
    /**
     * @param descEu the descEu to set
     */
    public void setDescEu(String descEu) {
        this.descEu = descEu;
    }
    /**
     * @return the direccion
     */
    public Direccion getDireccion() {
        return this.direccion;
    }
    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * @return String
     */
    public String getCodigoCompleto() {
        if (this.tipo != null && !"".equals(this.tipo) && this.codigo != null) {
            return this.tipo + "_" + this.codigo;
        } else {
            return null;
        }
    }
    public void setCodigoCompleto(String codigo) {
        final String[] aux = codigo.split("_");
        if (aux.length == 2) {
            Integer i = 0;
            this.tipo = aux[i++];
            this.codigo = Integer.parseInt(aux[i++]);
        }
    }
    public String getCif() {
        return this.cif;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }
    public String getEstado() {
        return this.estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipoDesc() {
        return this.tipoDesc;
    }
    public void setTipoDesc(String tipoDesc) {
        this.tipoDesc = tipoDesc;
    }
}
