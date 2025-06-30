package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.ConfigTextoEmails;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.SqlUtils;

/**
 * @author aresua
 *
 */
public class ConfigTextoEmailsSolver {

    /*
     * ROW_MAPPERS
     */
    private final RowMapper<ConfigTextoEmails> rwMap = new RowMapper<ConfigTextoEmails>() {
        @Override
        public ConfigTextoEmails mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            final ConfigTextoEmails bean = new ConfigTextoEmails();
            bean.setId(resultSet.getInt("ID006"));
            bean.setAsuntoEs(resultSet.getString("ASUNTOES006"));
            bean.setAsuntoEu(resultSet.getString("ASUNTOEU006"));
            bean.setTextoMailEs(SqlUtils.getClob(resultSet, "TEXTOMAILES006"));
            bean.setTextoMailEu(SqlUtils.getClob(resultSet, "TEXTOMAILEU006"));

            return bean;
        }
    };

    /**
     *
     * @param idTipoAviso int
     * @param baseSql BaseSql
     * @return ConfigTextoEmails
     * @throws Exception e
     */
    public ConfigTextoEmails findAviso(int idTipoAviso, BaseSql baseSql) throws Exception {
        final StringBuilder query = new StringBuilder();
        query.append("SELECT ID_006 ID006");
        query.append(", t1.TEXTO_MAIL_ES_006 TEXTOMAILES006");
        query.append(", t1.TEXTO_MAIL_EU_006 TEXTOMAILEU006");
        query.append(", t1.ASUNTO_ES_006 ASUNTOES006");
        query.append(", t1.ASUNTO_EU_006 ASUNTOEU006");
        query.append(" FROM AA79B06S01 t1 ");
        query.append(" WHERE t1.ID_006 = ? ");

        final List<Object> params = new ArrayList<Object>();
        params.add(idTipoAviso);

        final List<ConfigTextoEmails> lista = new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);

        if(lista==null || lista.isEmpty()){
            return null;
        } else {
            return lista.get(0);
        }
    }
}

