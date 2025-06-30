package aa79b.util.beans;

import java.util.ArrayList;
import java.util.List;



/**
 * @author aresua
 */

public class Statement  implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String sql;
    private List<Object[]> parametros;

    /**
     * Method 'Statement'.
     */
    public Statement() {
    }

    /**
     *
     * @return sql
     */
    public String getSql() {
        return sql;
    }

    /**
     *
     * @param sql la insert/update
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * Method to get the parametros.
     * @return the parametros
     */
    public List<Object[]> getParametros() {
        return parametros;
    }

    /**
     * Method to set the parametros.
     * @param parametros the parametros to set
     */
    public void setParametros(List<Object[]> parametros) {
        this.parametros = parametros;
    }

    /**
     * The method addParametro.
     * @param parametros List
     */
    public void addParametro(List parametros) {
        if (this.parametros == null) {
            this.parametros = new ArrayList();
        }
        this.parametros.add(parametros.toArray());
    }






}

