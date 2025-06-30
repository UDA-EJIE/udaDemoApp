package aa79b.util.common;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author aresua
 *
 */
public final class SqlUtils {

    /**
     * El metodo constructor.
     */
    private SqlUtils() {
    }

    /**
     * @param rs
     *            ResultSet
     * @param strColName
     *            String
     * @return String
     * @throws SQLException
     *             e
     */
    public static String getClob(ResultSet rs, String strColName) throws SQLException {
        final Clob clobEs = rs.getClob(strColName);
        if (clobEs != null) {
            return clobEs.getSubString(1, (int) clobEs.length());
        } else {
            return "";
        }
    }

}

