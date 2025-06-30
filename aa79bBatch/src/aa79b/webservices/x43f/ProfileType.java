
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para profileType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="profileType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="xades"/>
 *     &lt;enumeration value="pdf"/>
 *     &lt;enumeration value="pades"/>
 *     &lt;enumeration value="cades"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "profileType")
@XmlEnum
public enum ProfileType {

    @XmlEnumValue("xades")
    XADES("xades"),
    @XmlEnumValue("pdf")
    PDF("pdf"),
    @XmlEnumValue("pades")
    PADES("pades"),
    @XmlEnumValue("cades")
    CADES("cades");
    private final String value;

    ProfileType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProfileType fromValue(String v) {
        for (ProfileType c: ProfileType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
