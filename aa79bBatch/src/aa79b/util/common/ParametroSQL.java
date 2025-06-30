package aa79b.util.common;

/**
 * <p>Título: K81ClsParametroSQL.java</p>
 * <p>Descipción: Clase que encapsula un parametro de un procedimiento o funcion de SQL</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: Eurohelp </p>
 * @author David Belmez
 * @version 1.0 06-jun-2005
 */

public class ParametroSQL {
    //Constantes de clase
    public static final int TIPO_SQL_STRING = java.sql.Types.VARCHAR;

    public static final int TIPO_SQL_INTEGER = java.sql.Types.INTEGER;

    public static final int TIPO_SQL_DOUBLE = java.sql.Types.DOUBLE;

    public static final int TIPO_SQL_DATE = java.sql.Types.DATE;

    public static final int TIPO_PARAMETRO_IN = 0;

    public static final int TIPO_PARAMETRO_OUT = 1;

    public static final int TIPO_PARAMETRO_INOUT = 2;

    private static final String msgError = "debe tener los siguientes valores";

    //Propiedades internas
    private transient int intTipoSQL;

    private transient int intTipoParametro;

    private transient String strValor = null;

    /**
     * Construye un parametro
     *
     * @param intPfTipoSQL
     *            int: Tipo de parametro SQL
     * @param intPfTipoParametro
     *            int: Tipo de valor de parametro
     * @param strPfValor
     *            String Valor del parametro
     * @throws Exception e
     */

    public ParametroSQL(int intPfTipoSQL, int intPfTipoParametro,
            String strPfValor) throws Exception {
        if (this.esTipoSQLCorrecto(intPfTipoSQL)) {
            this.intTipoSQL = intPfTipoSQL;
        } else {
            throw new IllegalArgumentException(
                    "intPfTipoSQL "
                            + ParametroSQL.msgError
                            + " [TIPO_SQL_STRING,TIPO_SQL_INTEGER,TIPO_SQL_DOUBLE,TIPO_SQL_DATE]");
        }
        if (this.esTipoCorrecto(intPfTipoParametro)) {
            this.intTipoParametro = intPfTipoParametro;
        } else {
            throw new IllegalArgumentException(
                    "intPfTipoParametro "
                            + ParametroSQL.msgError
                            + " [TIPO_PARAMETRO_IN,TIPO_PARAMETRO_OUT,TIPO_PARAMETRO_INOUT]");
        }
        this.strValor = strPfValor;
    }

    /**
     * Construye un parametro sin valor
     *
     * @param intPfTipoSQL
     *            int: Tipo de parametro SQL
     * @param intPfTipoParametro
     *            int: Tipo de valor de parametro
     * @throws Exception e
     */

    public ParametroSQL(int intPfTipoSQL, int intPfTipoParametro)
            throws Exception {
        if (this.esTipoSQLCorrecto(intPfTipoSQL)) {
            this.intTipoSQL = intPfTipoSQL;
        } else {
            throw new IllegalArgumentException(
                    "intPfTipoSQL "
                            + ParametroSQL.msgError
                            + " [TIPO_SQL_STRING,TIPO_SQL_INTEGER,TIPO_SQL_DOUBLE,TIPO_SQL_DATE]");
        }
        if (this.esTipoCorrecto(intPfTipoParametro)) {
            this.intTipoParametro = intPfTipoParametro;
        } else {
            throw new IllegalArgumentException(
                    "intPfTipoParametro "
                            + ParametroSQL.msgError
                            + " [TIPO_PARAMETRO_IN,TIPO_PARAMETRO_OUT,TIPO_PARAMETRO_INOUT]");
        }
    }

    /**
     * Obtiene el tipo de parametro
     *
     * @return String
     */

    public int getTipoParametro() {
        return this.intTipoParametro;
    }

    /**
     * Obtiene el tipo SQL de parametro
     *
     * @return String
     */

    public int getTipoSQL() {
        return this.intTipoSQL;
    }

    /**
     * Obtiene el Valor
     *
     * @return String
     */

    public String getValor() {
        return this.strValor;
    }

    /**
     * Comprueba que el tipo es correcto
     *
     * @param intPfTipoSQL int
     * @return boolean: True si correcto
     */

    private boolean esTipoSQLCorrecto(int intPfTipoSQL) {
        return intPfTipoSQL == ParametroSQL.TIPO_SQL_STRING
                || intPfTipoSQL == ParametroSQL.TIPO_SQL_INTEGER
                || intPfTipoSQL == ParametroSQL.TIPO_SQL_DOUBLE || intPfTipoSQL == ParametroSQL.TIPO_SQL_DATE;
    }

    /**
     * Comprueba que el tipo SQL es correcto
     *
     * @param intPfTipo
     *            int
     * @return boolean: True si correcto
     */

    private boolean esTipoCorrecto(int intPfTipo) {
        return intPfTipo == ParametroSQL.TIPO_PARAMETRO_IN
                || intPfTipo == ParametroSQL.TIPO_PARAMETRO_OUT || intPfTipo == ParametroSQL.TIPO_PARAMETRO_INOUT;
    }

}