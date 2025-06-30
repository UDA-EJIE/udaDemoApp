
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfFTContentCondition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfFTContentCondition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fulltextExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fuzzySearch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fuzzySimilarity" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfFTContentCondition", propOrder = {
    "fulltextExpression",
    "fuzzySearch",
    "fuzzySimilarity"
})
public class W43DfFTContentCondition {

    protected String fulltextExpression;
    protected boolean fuzzySearch;
    protected float fuzzySimilarity;

    /**
     * Gets the value of the fulltextExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFulltextExpression() {
        return fulltextExpression;
    }

    /**
     * Sets the value of the fulltextExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFulltextExpression(String value) {
        this.fulltextExpression = value;
    }

    /**
     * Gets the value of the fuzzySearch property.
     * 
     */
    public boolean isFuzzySearch() {
        return fuzzySearch;
    }

    /**
     * Sets the value of the fuzzySearch property.
     * 
     */
    public void setFuzzySearch(boolean value) {
        this.fuzzySearch = value;
    }

    /**
     * Gets the value of the fuzzySimilarity property.
     * 
     */
    public float getFuzzySimilarity() {
        return fuzzySimilarity;
    }

    /**
     * Sets the value of the fuzzySimilarity property.
     * 
     */
    public void setFuzzySimilarity(float value) {
        this.fuzzySimilarity = value;
    }

}
