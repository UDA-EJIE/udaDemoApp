package aa79b.util.beans;

import java.io.Serializable;

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tipIden;
    private String preDni;
    private String dni;
    private String sufDni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String estado;
    private Entidad entidad = new Entidad();
    private String nombreFiltro;


    public String getNombreFiltro() {
        return this.nombreFiltro;
    }
    public void setNombreFiltro(String nombreFiltro) {
        this.nombreFiltro = nombreFiltro;
    }
    /**
     * @return the dni
     */
    public String getDni() {
        return this.dni;
    }
    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return this.apellido1;
    }
    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return this.apellido2;
    }
    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    /**
     * @return the tipIden
     */
    public Integer getTipIden() {
        return this.tipIden;
    }
    /**
     * @param tipIden the tipIden to set
     */
    public void setTipIden(Integer tipIden) {
        this.tipIden = tipIden;
    }
    /**
     * @return the preDni
     */
    public String getPreDni() {
        return this.preDni;
    }
    /**
     * @param preDni the preDni to set
     */
    public void setPreDni(String preDni) {
        this.preDni = preDni;
    }
    /**
     * @return the sufDni
     */
    public String getSufDni() {
        return this.sufDni;
    }
    /**
     * @param sufDni the sufDni to set
     */
    public void setSufDni(String sufDni) {
        this.sufDni = sufDni;
    }

    /**
     * @return String
     */
    public String getNombreCompleto() {
        final StringBuilder apeNom = new StringBuilder();
        if (this.apellido1 != null) {
            apeNom.append(this.apellido1);
            if (this.apellido2 != null) {
                apeNom.append(" ");
            } else if (this.nombre != null) {
                apeNom.append(", ");
            }
        }
        if (this.apellido2 != null) {
            apeNom.append(this.apellido2);
            if (this.nombre != null) {
                apeNom.append(", ");
            }
        }
        if (this.nombre != null) {
            apeNom.append(this.nombre);
        }
        return apeNom.toString();
    }

    /**
     * @return String
     */
    public String getApellidos() {
        final StringBuilder apellidos = new StringBuilder();
        if (this.apellido1 != null) {
            apellidos.append(this.apellido1);
            if (this.apellido2 != null) {
                apellidos.append(" ");
            }
        }
        if (this.apellido2 != null) {
            apellidos.append(this.apellido2);
        }
        return apellidos.toString();
    }

    public void setApellidos(String apellidos){
        if(apellidos!=null && !apellidos.isEmpty()){
            this.setApellido1(apellidos);
            this.setApellido2(apellidos);
        }
    }

    public Entidad getEntidad() {
        return this.entidad;
    }
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    public String getEstado() {
        return this.estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
