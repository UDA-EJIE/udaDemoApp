package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Proveedor;

@Repository()
@Transactional()
public class ProveedorDaoImpl extends GenericoDaoImpl<Proveedor> implements ProveedorDao {

    public ProveedorDaoImpl() {
        super(Proveedor.class);
    }

    public static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.TIPIDEN, DBConstants.PREDNI,
            DBConstants.DNI, DBConstants.SUFDNI, DBConstants.NOMBRE, DBConstants.APEL1, DBConstants.APEL2,
            DBConstants.ESTADO, DBConstants.TIPOENTIDAD, DBConstants.CODENTIDAD, DBConstants.DESCES, DBConstants.DESCEU,
            DBConstants.CIF, "TRADUCTOR", "INTERPRETE", DBConstants.DNICOMPLETO, DBConstants.NOMBRECOMPLETO };

    private RowMapper<Proveedor> rwMap = new ProveedorRM();

    private class ProveedorRM implements RowMapper<Proveedor> {
        @Override()
        public Proveedor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Proveedor proveedor = new Proveedor();
            proveedor.setTipIden(rs.getInt(DBConstants.TIPIDEN));
            proveedor.setPreDni(rs.getString(DBConstants.PREDNI));
            proveedor.setDni(rs.getString(DBConstants.DNI));
            proveedor.setSufDni(rs.getString(DBConstants.SUFDNI));
            proveedor.setNombre(rs.getString(DBConstants.NOMBRE));
            proveedor.setApellido1(rs.getString(DBConstants.APEL1));
            proveedor.setApellido2(rs.getString(DBConstants.APEL2));
            proveedor.setEstado(rs.getString(DBConstants.ESTADO));

            Entidad entidad = proveedor.getEntidad();
            entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
            entidad.setCodigo(rs.getInt(DBConstants.CODENTIDAD));
            entidad.setDescEs(rs.getString(DBConstants.DESCES));
            entidad.setDescEu(rs.getString(DBConstants.DESCEU));
            entidad.setCif(rs.getString(DBConstants.CIF));

            proveedor.setTraductor(rs.getString("TRADUCTOR"));
            proveedor.setInterprete(rs.getString("INTERPRETE"));
            proveedor.setDniCompleto(rs.getString(DBConstants.DNICOMPLETO));
            proveedor.setNombreCompleto(rs.getString(DBConstants.NOMBRECOMPLETO));

            return proveedor;
        }
    }

    private RowMapper<Proveedor> rwMapPK = new RowMapper<Proveedor>() {
        @Override()
        public Proveedor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Proveedor bean = new Proveedor();
            bean.setDni(resultSet.getString(DBConstants.DNI));
            return bean;
        }
    };

    @Override()
    protected String getSelect() {
        StringBuilder selectProveedor = new StringBuilder();
        selectProveedor.append("SELECT TIPIDEN");
        selectProveedor.append(", PREDNI");
        selectProveedor.append(", DNI");
        selectProveedor.append(", SUFDNI");
        selectProveedor.append(", NOMBRE");
        selectProveedor.append(", APEL1");
        selectProveedor.append(", APEL2");
        selectProveedor.append(", ESTADO");
        selectProveedor.append(", TIPO_ENTIDAD AS TIPOENTIDAD");
        selectProveedor.append(", COD_ENTIDAD AS CODENTIDAD");
        selectProveedor.append(", DESC_ES AS DESCES");
        selectProveedor.append(", DESC_EU AS DESCEU");
        selectProveedor.append(", CIF");
        selectProveedor.append(", TRADUCTOR");
        selectProveedor.append(", INTERPRETE");
        selectProveedor.append(", (PREDNI || DNI || SUFDNI) AS DNICOMPLETO");
        selectProveedor.append(", (APEL1 || ' ' || APEL2 || ', ' || NOMBRE) AS NOMBRECOMPLETO");

        return selectProveedor.toString();
    }

    @Override()
    protected String getFrom() {
        StringBuilder from = new StringBuilder();
        from.append(" FROM X54JAPI_PROVEEDORES");
        return from.toString();
    }

    @Override()
    protected RowMapper<Proveedor> getRwMap() {
        return this.rwMap;
    }

    @Override()
    protected String[] getOrderBy() {
        return ProveedorDaoImpl.ORDER_BY_WHITE_LIST;
    }

    @Override()
    protected String getPK() {
        return DBConstants.DNI;
    }

    @Override()
    protected RowMapper<Proveedor> getRwMapPK() {
        return this.rwMapPK;
    }

    @Override()
    protected String getWherePK(Proveedor bean, List<Object> params) {
        params.add(bean.getDni());

        StringBuilder where = new StringBuilder();
        where.append(" WHERE DNI = ? AND TRADUCTOR = 'S'");

        return where.toString();
    }

    @Override()
    protected String getWhere(Proveedor bean, List<Object> params) {
        StringBuilder whereProveedor = new StringBuilder();
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.TIPIDEN, bean.getTipIden(), params));
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.PREDNI, bean.getPreDni(), params));
        whereProveedor.append(SqlUtils.generarWhereIgualDni(DBConstants.DNI, bean.getDni(), params));
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.SUFDNI, bean.getSufDni(), params));
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.NOMBRE, bean.getNombre(), params));
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.APEL1, bean.getApellido1(), params));
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.APEL2, bean.getApellido2(), params));
        whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.ESTADO, bean.getEstado(), params));

        Entidad entidad = bean.getEntidad();
        if (entidad != null) {
            whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.TIPO_ENTIDAD, entidad.getTipo(), params));
            whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.COD_ENTIDAD, entidad.getCodigo(), params));
            whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.DESC_ES, entidad.getDescEs(), params));
            whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.DESC_EU, entidad.getDescEu(), params));
            whereProveedor.append(SqlUtils.generarWhereIgual(DBConstants.CIF, entidad.getCif(), params));
        }

        List<String> permisos = new ArrayList<String>();
        SqlUtils.comprobarPermisos(Constants.SI, "TRADUCTOR", permisos);
        SqlUtils.comprobarPermisos(bean.getInterprete(), "INTERPRETE", permisos);
        whereProveedor.append(SqlUtils.generarWherePermisos(permisos, params));

        return whereProveedor.toString();
    }

    @Override()
    protected String getWhereLike(Proveedor bean, Boolean startsWith, List<Object> params, Boolean search) {
        StringBuilder whereLikeProveedor = new StringBuilder();
        whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.TIPIDEN, bean.getTipIden(), params));
        whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.PREDNI, bean.getPreDni(), params));
        whereLikeProveedor.append(SqlUtils.generarWhereIgualDni(DBConstants.DNI, bean.getDni(), params));
        whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.SUFDNI, bean.getSufDni(), params));
        whereLikeProveedor
                .append(SqlUtils.generarWhereLikeNormalizado(DBConstants.NOMBRE, bean.getNombre(), params, startsWith));
        whereLikeProveedor.append(
                SqlUtils.generarWhereLikeNormalizado(DBConstants.APEL1, bean.getApellido1(), params, startsWith));
        whereLikeProveedor.append(
                SqlUtils.generarWhereLikeNormalizado(DBConstants.APEL2, bean.getApellido2(), params, startsWith));
        whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.ESTADO, bean.getEstado(), params));

        Entidad entidad = bean.getEntidad();
        if (entidad != null) {
            whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.TIPO_ENTIDAD, entidad.getTipo(), params));
            whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.COD_ENTIDAD, entidad.getCodigo(), params));
            whereLikeProveedor.append(
                    SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_ES, entidad.getDescEs(), params, startsWith));
            whereLikeProveedor.append(
                    SqlUtils.generarWhereLikeNormalizado(DBConstants.DESC_EU, entidad.getDescEu(), params, startsWith));
            whereLikeProveedor.append(SqlUtils.generarWhereIgual(DBConstants.CIF, entidad.getCif(), params));
        }

        List<String> permisos = new ArrayList<String>();
        SqlUtils.comprobarPermisos(Constants.SI, "TRADUCTOR", permisos);
        SqlUtils.comprobarPermisos(bean.getInterprete(), "INTERPRETE", permisos);
        whereLikeProveedor.append(SqlUtils.generarWherePermisos(permisos, params));

        return whereLikeProveedor.toString();
    }

}
