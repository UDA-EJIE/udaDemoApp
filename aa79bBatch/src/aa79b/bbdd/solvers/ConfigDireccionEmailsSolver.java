package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.ConfigDireccionEmail;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.Constants;

/**
 * @author aresua
 *
 */
public class ConfigDireccionEmailsSolver {

    /*
     * ROW_MAPPERS
     */
    private final RowMapper<ConfigDireccionEmail> rwMap = new RowMapper<ConfigDireccionEmail>() {
        @Override
        public ConfigDireccionEmail mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new ConfigDireccionEmail(
                    resultSet.getInt("ID007"), resultSet.getString("DIREMAIL007")
                    );
        }
    };

    /**
     *
     * @param baseSql BaseSql
     * @return ConfigTextoEmails
     * @throws Exception e
     */
    public ConfigDireccionEmail find(BaseSql baseSql) throws Exception {
        final StringBuilder query = new StringBuilder();
        query.append("SELECT t1.ID_007 ID007, t1.DIR_EMAIL_007 DIREMAIL007");
        query.append(" FROM AA79B07S01 t1");
        query.append(" WHERE t1.ID_007 = ?");

        final List<Object> params = new ArrayList<Object>();
        params.add(Constants.MAGIC_NUMBER0);

        final List<ConfigDireccionEmail> lista = new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);

        if(lista==null || lista.isEmpty()){
            return null;
        } else {
            return lista.get(0);
        }
    }
}

