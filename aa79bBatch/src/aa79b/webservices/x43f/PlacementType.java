
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para placementType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="placementType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="detached"/>
 *     &lt;enumeration value="enveloped"/>
 *     &lt;enumeration value="enveloping"/>
 *     &lt;enumeration value="idetached"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "placementType")
@XmlEnum
public enum PlacementType {

    @XmlEnumValue("detached")
    DETACHED("detached"),
    @XmlEnumValue("enveloped")
    ENVELOPED("enveloped"),
    @XmlEnumValue("enveloping")
    ENVELOPING("enveloping"),
    @XmlEnumValue("idetached")
    IDETACHED("idetached");
    private final String value;

    PlacementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PlacementType fromValue(String v) {
        for (PlacementType c: PlacementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
