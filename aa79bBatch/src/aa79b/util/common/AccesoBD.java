package aa79b.util.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.bbdd.RowMapperResultSetExtractor;
import aa79b.util.beans.Statement;

/**
 *
 * @author aresua
 *
 */
public class AccesoBD{

    /**
     *
     * @param strPfSelect s
     * @param arrPfCampos a
     * @param baseSql la conexion utilizada
     * @return r
     * @throws Exception e
     */
    public String fncConsultaEspecifica(String strPfSelect,Object[] arrPfCampos, BaseSql baseSql) throws Exception {

        Connection conexionBD = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conexionBD = baseSql.getConnection();
            
            Logger.batchlog(Logger.INFO,
                    "fncConsultaEspecifica - La sentencia a lanzar es: "
                            + strPfSelect);
            
            preparedStatement = conexionBD.prepareStatement(strPfSelect, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if (arrPfCampos != null) {
                for (int i = 0; i < arrPfCampos.length; i++) {
                    this.fncPonerParametro(preparedStatement, i + 1, arrPfCampos[i]);
                }
            }
            rs = preparedStatement.executeQuery();
           
            String strResult = null;
            if (rs.next()) {
                strResult = rs.getString(1);
            }
            //Devuelve null si no encuentra nada.
            return strResult;
        } catch (final Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
            try {
                if (conexionBD != null) {
                    conexionBD.close();
                    conexionBD = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
        }
    }

    /**
     * Lanza una consulta con preparedstatement y devuelve un arraylist con los
     * datos
     * @param <T> lista resultado
     * @param strPfSelect query
     * @param arrPfCampos campos
     * @param rowMapper rowmapper
     * @param baseSql la conexion
     * @return lista ersultado
     * @throws Exception cualquier excepcion
     */

    public <T>List<T> fncLanzaBusqueda(String strPfSelect,
            Object[] arrPfCampos, RowMapper<T> rowMapper, BaseSql baseSql) throws Exception {


        Connection conexionBD = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            conexionBD = baseSql.getConnection();
            Logger.batchlog(Logger.INFO,"fncLanzaBusqueda - La sentencia a lanzar es: "
                    + strPfSelect);
            preparedStatement = conexionBD.prepareStatement(strPfSelect, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if (arrPfCampos != null) {
                for (int i = 0; i < arrPfCampos.length; i++) {
                    this.fncPonerParametro(preparedStatement, i + 1, arrPfCampos[i]);
                }
            }
            final long antesM = System.currentTimeMillis();

            rs = preparedStatement.executeQuery();

            final long despuesM = System.currentTimeMillis();
            final GregorianCalendar despues = new GregorianCalendar();
            despues.setTimeInMillis(despuesM-antesM);

            return new RowMapperResultSetExtractor<T>(rowMapper).extractData(rs);

        } catch (final Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
            try {
                if (conexionBD != null) {
                    conexionBD.close();
                    conexionBD = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
        }
    }


    /**
     * Lanza una consulta con preparedstatement y devuelve un arraylist con los
     * datos,
     *
     * @param strPfProcedimiento
     *            String: Procedimiento a lanzar
     * @param baseSql la conexion utilizada
     * @param arrPfParametros
     *            List: ArrayList con los valores a asignar a los ? del
     *            procedimiento
     * @return List: ArrayList con el retorno del procedimiento almacenado
     * @throws Exception
     * 				Se lanza exception si se produce algun error
     */
    public List<?> fncLanzaProcedimiento(String strPfProcedimiento,
            Object[] arrPfParametros, BaseSql baseSql) throws Exception {
        Connection conexionBD = null;
        CallableStatement callableStatement = null;
        try {
            conexionBD = baseSql.getConnection();
            
            Logger.batchlog(Logger.INFO,
                    "fncLanzaProcedimiento - El procedimiento a lanzar es: "
                            + strPfProcedimiento);
            callableStatement = conexionBD.prepareCall(strPfProcedimiento);
            if (arrPfParametros != null){
                for (int i = 0; i < arrPfParametros.length; i++) {
                    this.fncPonerParametroProcedimiento(callableStatement, i + 1,
                            (ParametroSQL) arrPfParametros[i]);
                }
            }
            callableStatement.executeUpdate();
            ParametroSQL parametroSQL;
            final List<Object> arrResultado = new ArrayList<Object>();
            if (arrPfParametros != null) {
                for (int i = 0; i < arrPfParametros.length; i++) {
                    parametroSQL = (ParametroSQL) arrPfParametros[i];
                    if (parametroSQL.getTipoParametro() == ParametroSQL.TIPO_PARAMETRO_INOUT
                            || parametroSQL.getTipoParametro() == ParametroSQL.TIPO_PARAMETRO_OUT){
                        arrResultado.add(callableStatement.getObject(i + 1));
                    }
                }
            }
            return arrResultado;
        } catch (final Exception e) {
            throw e;
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
            } catch (final SQLException e) {
            }
            try {
                if (conexionBD != null) {
                    conexionBD.close();
                    conexionBD = null;
                }
            } catch (final SQLException e) {
            }
        }

    }


    /**
     * Pasa parametros al CallableStatement
     *
     * @param callablePfStatement
     *            CallableStatement: CallableStatement sobre el que trabajaremos
     * @param intPfPosicion
     *            int: n de posicin del campo a pasar
     * @param objPfParametro
     *            Object: valor a pasar como parametro
     * @return boolean
     * @throws Exception
     *       se lanza una exception si se produce un error
     */
    private boolean fncPonerParametroProcedimiento(
            CallableStatement callablePfStatement, int intPfPosicion,
            ParametroSQL objPfParametro) throws Exception {

        boolean retorno = true;
        
        Logger.batchlog(Logger.INFO,
                "fncPonerParametroProcedimiento - parametro["+intPfPosicion+"]: "
                        + objPfParametro);
        
        //Si el parametro es de entrada o entrada salida registrarlo como
        // parametro de entrada
        if (objPfParametro.getTipoParametro() == ParametroSQL.TIPO_PARAMETRO_IN
                || objPfParametro.getTipoParametro() == ParametroSQL.TIPO_PARAMETRO_INOUT) {
            retorno = retorno
                    && this.fncPonerParametro(callablePfStatement, intPfPosicion,
                            objPfParametro.getValor());
        }
        //Si el parametro es de salida o entrada salida registrarlo como
        // parametro de salida
        if (objPfParametro.getTipoParametro() == ParametroSQL.TIPO_PARAMETRO_OUT
                || objPfParametro.getTipoParametro() == ParametroSQL.TIPO_PARAMETRO_INOUT) {
            callablePfStatement.registerOutParameter(intPfPosicion,
                    objPfParametro.getTipoSQL());

        }
        return retorno;
    }

    /**
     * Lanza una actualizacion con preparedstatement y devuelve un n con los
     * registros modificados
     *
     * @param strPfSQL
     *            String: Sentencia SQL a lanzar
     * @param arrPfCampos
     *            ArrayList: ArrayList con los valores a asignar a los ? de la
     *            select
     * @param baseSql la conexion utilizada
     * @return int: el n de filas involucradas en la actualizaci
     * n.
     * @throws Exception e
     */
    public int fncLanzaMovimiento(String strPfSQL, Object[]  arrPfCampos, BaseSql baseSql)
            throws Exception {

        Connection conexionBD = null;
        PreparedStatement preparedStatement = null;
        try {
            conexionBD = baseSql.getConnection();
            
            Logger.batchlog(Logger.INFO,
                    "fncLanzaMovimiento - La sentencia a lanzar es: "
                            + strPfSQL);
            
            preparedStatement = conexionBD.prepareStatement(strPfSQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            for (int i = 0; i < arrPfCampos.length; i++) {
                this.fncPonerParametro(preparedStatement, i + 1, arrPfCampos[i]);
            }

            final long antesM = System.currentTimeMillis();

            final int intColumnasAfectadas = preparedStatement.executeUpdate();

            final long despuesM = System.currentTimeMillis();
            final GregorianCalendar despues = new GregorianCalendar();
            despues.setTimeInMillis(despuesM-antesM);
            return intColumnasAfectadas;
        } catch (final Exception e) {
            throw e;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
            try {
                if (conexionBD != null) {
                    conexionBD.close();
                    conexionBD = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
        }
    }


    /**
     * Lanza una actualizacin con preparedstatement y devuelve un n con los
     * registros modificados
     *
     * @param arrSQLs
     *            Lista de sentencias a ser lanzadas en grupo
     * @param baseSql la conexion utilizada
     * @throws Exception e
     */
    public void fncLanzaMovimientosBatch(List<Statement> arrSQLs, BaseSql baseSql)
            throws Exception {


        Connection conexionBD = null;
        PreparedStatement preparedStatement = null;

        try {
            conexionBD = baseSql.getConnection();

            //Commit a false para controlarlo nosotros
            conexionBD.setAutoCommit(false);
            for (final Statement sql : arrSQLs) {

                //Por cada PreparedStatement tenemos una lista con los distintos grupos de parmetros
            	Logger.batchlog(Logger.INFO,
                        "fncLanzaMovimientosBatch - La sentencia a lanzar es: "
                                + sql.getSql());
            	
                preparedStatement = conexionBD.prepareStatement(sql.getSql());
                for (final Object[] parametros : sql.getParametros()) {
                    if (parametros != null) {
                        for (int j = 0; j < parametros.length; j++) {
                            this.fncPonerParametro(preparedStatement, j + 1, parametros[j]);
                        }
                    }

                    //Añadimos una ejecucion para el batch
                    preparedStatement.addBatch();
                }
                final long antesM = System.currentTimeMillis();
                //Ejecutamos el PreparedStatement
                preparedStatement.executeBatch();
                final long despuesM = System.currentTimeMillis();
                final GregorianCalendar despues = new GregorianCalendar();
                despues.setTimeInMillis(despuesM-antesM);
                //cerramos el PreparedStatement para ir a por otro dentro de la misma transaccion
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

            }

            //Hacemos commit
            conexionBD.commit();
        } catch (final Exception e) {
            //hacemos rollback
            conexionBD.rollback();
            throw e;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
            try {
                if (conexionBD != null) {
                    conexionBD.close();
                    conexionBD = null;
                }
            } catch (final SQLException e) {
                throw e;
            }
        }
    }

    /**
     *
     * Pasa parametros al PreparedStatement.
     *
     * @param preparedPfStatement
     *            PreparedStatement: PreparedStatement sobre el que trabajaremos
     * @param intPfPosicion
     *            int: n de posicin del campo a pasar
     * @param objPfValor
     *            Object: valor a pasar como parmetro
     * @return boolean: true si paso el parmetro o false si el tipo no se
     *         reconoci
     * @throws Exception e
     */
    private boolean fncPonerParametro(PreparedStatement preparedPfStatement,
            int intPfPosicion, Object objPfValor) throws Exception {
        
    	Logger.batchlog(Logger.INFO,
                "fncPonerParametro - parametro["+intPfPosicion+"]: "
                        + objPfValor);
    	
    	if (objPfValor == null) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.VARCHAR);
            return true;
        }

        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_INTEGER)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.INTEGER);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_DOUBLE)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.FLOAT);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_STRING)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.VARCHAR);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_LONG)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.NUMERIC);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_FLOAT)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.FLOAT);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_TIMESTAMP)) {
            preparedPfStatement
            .setNull(intPfPosicion, java.sql.Types.TIMESTAMP);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_DATE)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.DATE);
            return true;
        }
        if (objPfValor.toString().equalsIgnoreCase(
                Constants.CONSTANTE_NULO_BLOB)) {
            preparedPfStatement.setNull(intPfPosicion, java.sql.Types.BLOB);
            return true;
        }
        if (!objPfValor.toString().equalsIgnoreCase("")) {
            preparedPfStatement.setObject(intPfPosicion, objPfValor);
            return true;
        }
        return false;
    }


}