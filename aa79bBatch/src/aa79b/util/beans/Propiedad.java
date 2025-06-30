package aa79b.util.beans;

import java.io.Serializable;

/**
 * @author mrodriguez
 *
 */
public class Propiedad implements Serializable {
	
	private static final long serialVersionUID = 2028244541577615073L;
	
	private Integer id;
    private String valor;

    /**
     * Method 'Constructor'.
     */
    public Propiedad() {
        // Constructor
    }

    /**
     * Method 'getId'.
     *
     * @return Integer
     */

    public Integer getId() {
        return this.id;
    }

    /**
     * Method 'setId'.
     *
     * @param id
     *            Integer
     * @return
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Method 'getValor'.
     *
     * @return String
     */

    public String getValor() {
        return this.valor;
    }

    /**
     * Method 'setValor'.
     *
     * @param bloqueo
     *            String
     * @return
     */

    public void setValor(String valor) {
        this.valor = valor;
    }

}
