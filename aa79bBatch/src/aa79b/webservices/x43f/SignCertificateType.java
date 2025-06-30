
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para signCertificateType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="signCertificateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="policy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validezDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validezHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suscriptor" type="{http://com.ejie.x43f/X43FNSHF/}suscriptorType" minOccurs="0"/>
 *         &lt;element name="entidad" type="{http://com.ejie.x43f/X43FNSHF/}entidadType" minOccurs="0"/>
 *         &lt;element name="fechaFirma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firmaCualificada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tsl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signCertificateType", propOrder = {
    "policy",
    "cn",
    "tipo",
    "subject",
    "issuer",
    "serialNumber",
    "validezDesde",
    "validezHasta",
    "suscriptor",
    "entidad",
    "fechaFirma",
    "firmaCualificada",
    "tsl"
})
public class SignCertificateType {

    protected String policy;
    protected String cn;
    protected String tipo;
    protected String subject;
    protected String issuer;
    protected String serialNumber;
    protected String validezDesde;
    protected String validezHasta;
    protected SuscriptorType suscriptor;
    protected EntidadType entidad;
    protected String fechaFirma;
    protected String firmaCualificada;
    protected String tsl;

    /**
     * Obtiene el valor de la propiedad policy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * Define el valor de la propiedad policy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicy(String value) {
        this.policy = value;
    }

    /**
     * Obtiene el valor de la propiedad cn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCn() {
        return cn;
    }

    /**
     * Define el valor de la propiedad cn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCn(String value) {
        this.cn = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad subject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Define el valor de la propiedad subject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Obtiene el valor de la propiedad issuer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Define el valor de la propiedad issuer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Obtiene el valor de la propiedad serialNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Define el valor de la propiedad serialNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad validezDesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidezDesde() {
        return validezDesde;
    }

    /**
     * Define el valor de la propiedad validezDesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidezDesde(String value) {
        this.validezDesde = value;
    }

    /**
     * Obtiene el valor de la propiedad validezHasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidezHasta() {
        return validezHasta;
    }

    /**
     * Define el valor de la propiedad validezHasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidezHasta(String value) {
        this.validezHasta = value;
    }

    /**
     * Obtiene el valor de la propiedad suscriptor.
     * 
     * @return
     *     possible object is
     *     {@link SuscriptorType }
     *     
     */
    public SuscriptorType getSuscriptor() {
        return suscriptor;
    }

    /**
     * Define el valor de la propiedad suscriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link SuscriptorType }
     *     
     */
    public void setSuscriptor(SuscriptorType value) {
        this.suscriptor = value;
    }

    /**
     * Obtiene el valor de la propiedad entidad.
     * 
     * @return
     *     possible object is
     *     {@link EntidadType }
     *     
     */
    public EntidadType getEntidad() {
        return entidad;
    }

    /**
     * Define el valor de la propiedad entidad.
     * 
     * @param value
     *     allowed object is
     *     {@link EntidadType }
     *     
     */
    public void setEntidad(EntidadType value) {
        this.entidad = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFirma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFirma() {
        return fechaFirma;
    }

    /**
     * Define el valor de la propiedad fechaFirma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFirma(String value) {
        this.fechaFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad firmaCualificada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaCualificada() {
        return firmaCualificada;
    }

    /**
     * Define el valor de la propiedad firmaCualificada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaCualificada(String value) {
        this.firmaCualificada = value;
    }

    /**
     * Obtiene el valor de la propiedad tsl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsl() {
        return tsl;
    }

    /**
     * Define el valor de la propiedad tsl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsl(String value) {
        this.tsl = value;
    }

}
