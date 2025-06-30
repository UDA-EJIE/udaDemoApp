/**
 *
 */
package aa79b.bbdd.solvers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.BaseSqlTransaccional;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.DocumentosExpediente;
import aa79b.util.beans.Expediente;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.AccesoBDTransaccional;

/**
 * @author aresua
 *
 */
public class DocumentosExpedienteSolver {

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<DocumentosExpediente> rwMap = new RowMapper<DocumentosExpediente>() {
		@Override
		public DocumentosExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final DocumentosExpediente doc = new DocumentosExpediente();
			doc.setIdDoc(resultSet.getBigDecimal("IDDOC"));
			doc.setNombre(resultSet.getString("NOMBRE"));
			doc.setTitulo(resultSet.getString("TITULO"));
			doc.setRutaPif(resultSet.getString("RUTAPIF"));
			doc.setAnyo(resultSet.getLong("ANYO"));
			doc.setNumExp(resultSet.getInt("NUMEXP"));
			doc.setOid(resultSet.getString("OID"));
			doc.setEstadoBitacora(resultSet.getLong("IDESTADOBITACORA"));

			return doc;
		}
	};
	private final RowMapper<DocumentosExpediente> rwMapCompleto = new RowMapper<DocumentosExpediente>() {
		@Override
		public DocumentosExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final DocumentosExpediente doc = new DocumentosExpediente();
			doc.setIdDoc(resultSet.getBigDecimal("IDDOC"));
			doc.setAnyo(resultSet.getLong("ANYO"));
			doc.setNumExp(resultSet.getInt("NUMEXP"));
			doc.setPersonaContacto(resultSet.getString("PERSONACONTACTO"));
			doc.setEmailContacto(resultSet.getString("EMAILCONTACTO"));
			doc.setTelefonoContacto(resultSet.getString("TELEFONOCONTACTO"));
			doc.setDireccionContacto(resultSet.getString("DIRECCIONCONTACTO"));
			doc.setEntidadContacto(resultSet.getString("ENTIDADCONTACTO"));
			doc.setOid(resultSet.getString("OIDFICHERO"));
			doc.setExtension(resultSet.getString("EXTENSIONDOC"));
			doc.setTamano(resultSet.getString("TAMANODOC"));
			doc.setIndEncriptado(resultSet.getString("INDENCRIPTADO"));
			doc.setFechaAlta(resultSet.getTimestamp("FECHAALTA"));
			doc.setDniAlta(resultSet.getString("DNIALTA"));
			doc.setIdDocRel(resultSet.getBigDecimal("IDDOCREL"));
			doc.setContentType(resultSet.getString("CONTENTTYPE"));
			doc.setNombre(resultSet.getString("NOMBREFICHERO"));
			doc.setRutaPif(resultSet.getString("RUTAPIFFICHERO"));
			doc.setClaseDocumento(resultSet.getLong("CLASEDOCUMENTO"));
			if (resultSet.getString("TIPODOCUMENTO")!= null){
				doc.setTipoDocumento(resultSet.getLong("TIPODOCUMENTO"));
			}
			doc.setTitulo(resultSet.getString("TITULO"));
			doc.setNumPalSolic(resultSet.getInt("NUMPALSOLIC"));
			doc.setNumPalIzo(resultSet.getInt("NUMPALIZO"));
			doc.setIndVisible(resultSet.getString("INDVISIBLE"));
			doc.setOrigen(resultSet.getString("INDORIGEN"));
			return doc;
		}
	};

	/**
	 *
	 * @param baseSql BaseSql
	 * @param idDoc BigDecimal
	 * @return List<DocumentosExpediente>
	 * @throws Exception e
	 */
	public List<DocumentosExpediente> find(BaseSql baseSql, String idDoc) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT t1.ANYO_056 ANYO, t1.ID_DOC_056 IDDOC, t1.NOMBRE_FICHERO_056 NOMBRE, t1.NUM_EXP_056 NUMEXP, t1.RUTA_PIF_FICHERO_056 RUTAPIF, t1.OID_FICHERO_056 OID, t1.TITULO_056 TITULO,");
		query.append(" t2.ESTADO_BITACORA_051 IDESTADOBITACORA ");
		query.append(" FROM AA79B56S01 t1 ");
		query.append(" JOIN AA79B51S01 t2 ON t1.ANYO_056 = t2.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_056 = t2.NUM_EXP_051 ");
		query.append(" WHERE t1.ID_DOC_056 = ? ");

		params.add(idDoc);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	/**
	 *
	 * @param baseSql BaseSql
	 * @param idDoc BigDecimal
	 * @return List<DocumentosExpediente>
	 * @throws Exception e
	 */
	public List<DocumentosExpediente> find(BaseSql baseSql, Expediente exp) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT t1.ANYO_056 ANYO, t1.ID_DOC_056 IDDOC, t1.NOMBRE_FICHERO_056 NOMBRE, t1.NUM_EXP_056 NUMEXP, t1.RUTA_PIF_FICHERO_056 RUTAPIF, t1.OID_FICHERO_056 OID, t1.TITULO_056 TITULO,");
		query.append(" t2.ESTADO_BITACORA_051 IDESTADOBITACORA ");
		query.append(" FROM AA79B56S01 t1 ");
		query.append(" JOIN AA79B51S01 t2 ON t1.ANYO_056 = t2.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_056 = t2.NUM_EXP_051 ");
		query.append(" WHERE RUTA_PIF_FICHERO_056 IS NOT NULL ");
		query.append(" AND t1.ANYO_056 = ? ");
		query.append(" AND t1.NUM_EXP_056 = ? ");

		params.add(exp.getAnyo());
		params.add(exp.getNumExp());

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	
	
	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @throws Exception Cualquier excepción
	 */
	public void updateOid(BaseSql baseSql, DocumentosExpediente doc) throws Exception {
		final String query = "UPDATE AA79B56S01 SET OID_FICHERO_056 = ?, RUTA_PIF_FICHERO_056=? WHERE ID_DOC_056 = ? ";

		final List<Object> params = new ArrayList<Object>();
		params.add(doc.getOid());
		params.add(null);
		params.add(doc.getIdDoc());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @throws Exception Cualquier excepción
	 */
	public void deleteRutaPif(BaseSql baseSql, DocumentosExpediente doc) throws Exception {
		final String query = "UPDATE AA79B56S01 SET RUTA_PIF_FICHERO_056=? WHERE ID_DOC_056 = ? ";

		final List<Object> params = new ArrayList<Object>();
		params.add(null);
		params.add(doc.getIdDoc());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @throws Exception Cualquier excepción
	 */
	public void updateOid73(BaseSql baseSql, DocumentosExpediente doc) throws Exception {
		final String query = "UPDATE AA79B73S01 SET OID_FICHERO_073 = ? WHERE ID_DOC_073 = ? ";

		final List<Object> params = new ArrayList<Object>();
		params.add(doc.getOid());
		params.add(doc.getIdDoc());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
	
	/* Clonación de expedientes */

	public Long getNextVal(BaseSql baseSql) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT AA79B56Q00.NEXTVAL FROM DUAL");
        return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), null, baseSql));
    }
	/* Clonación de expedientes */
	
	public Long getNextValTransaccional(BaseSqlTransaccional baseSql) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("SELECT AA79B56Q00.NEXTVAL FROM DUAL");
		return Long.valueOf(new AccesoBDTransaccional().fncConsultaEspecifica(query.toString(), null, baseSql));
	}
	
	/**
	 * 
	 * @param baseSql
	 * @param anyo
	 * @param numExp
	 * @return
	 * @throws Exception
	 */
	public List<DocumentosExpediente> findDatosCompletos(BaseSqlTransaccional baseSql, Long anyo, Integer numExp) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT t1.ID_DOC_056 IDDOC");
		query.append(", t1.ANYO_056 ANYO");
		query.append(", t1.NUM_EXP_056 NUMEXP");
		query.append(", t1.OID_FICHERO_056 OIDFICHERO");
		query.append(", t1.EXTENSION_DOC_056 EXTENSIONDOC");
		query.append(", t1.TAMANO_DOC_056 TAMANODOC");
		query.append(", t1.IND_ENCRIPTADO_056 INDENCRIPTADO");
		query.append(", t1.CONTENT_TYPE_056 CONTENTTYPE");
		query.append(", t1.ID_DOC_REL_056 IDDOCREL");
		query.append(", t1.CLASE_DOCUMENTO_056 CLASEDOCUMENTO");
		query.append(", t1.TIPO_DOCUMENTO_056 TIPODOCUMENTO");
		query.append(", t1.TITULO_056 TITULO");
		query.append(", t1.NUM_PAL_SOLIC_056 NUMPALSOLIC");
		query.append(", t1.NUM_PAL_IZO_056 NUMPALIZO");
		query.append(", t1.PERSONA_CONTACTO_056 PERSONACONTACTO");
		query.append(", t1.EMAIL_CONTACTO_056 EMAILCONTACTO");
		query.append(", t1.TELEFONO_CONTACTO_056 TELEFONOCONTACTO");
		query.append(", t1.IND_VISIBLE_056 INDVISIBLE");
		query.append(", t1.FECHA_ALTA_056 FECHAALTA");
		query.append(", t1.DNI_ALTA_056 DNIALTA");
		query.append(", t1.DIRECCION_CONTACTO_056 DIRECCIONCONTACTO");
		query.append(", t1.ENTIDAD_CONTACTO_056 ENTIDADCONTACTO");
		query.append(", t1.NOMBRE_FICHERO_056 NOMBREFICHERO");
		query.append(", t1.RUTA_PIF_FICHERO_056 RUTAPIFFICHERO");
		query.append(", t1.IND_ORIGEN_056 INDORIGEN");
		
		query.append(" FROM AA79B56S01 t1 ");
		query.append(" WHERE t1.ANYO_056 = ? ");
		query.append(" AND t1.NUM_EXP_056 = ? ");

		params.add(anyo);
		params.add(numExp);

		return new AccesoBDTransaccional().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMapCompleto, baseSql);
	}
	
	
	public DocumentosExpediente add( BaseSqlTransaccional baseSql, DocumentosExpediente documentosexpediente) throws Exception{
		final Long elIdLong = this.getNextValTransaccional( baseSql );
		final BigDecimal elId = new BigDecimal(elIdLong.longValue());
		
		List<Object> params = new ArrayList<Object>();
		params.add(elId);
		params.add(documentosexpediente.getAnyo());
		params.add(documentosexpediente.getNumExp());
		params.add(documentosexpediente.getOid());
		params.add(documentosexpediente.getExtension());
		params.add(documentosexpediente.getTamano());
		params.add(documentosexpediente.getIndEncriptado());
		params.add( documentosexpediente.getIdDocRel());
		params.add(documentosexpediente.getClaseDocumento());
		params.add(documentosexpediente.getTipoDocumento());
		params.add(documentosexpediente.getTitulo());
		params.add(documentosexpediente.getNumPalSolic());
		params.add(documentosexpediente.getNumPalIzo());
		params.add(documentosexpediente.getPersonaContacto());
		params.add(documentosexpediente.getEmailContacto());
		params.add(documentosexpediente.getTelefonoContacto());
		params.add(documentosexpediente.getIndVisible());
		params.add(documentosexpediente.getDniAlta());
		params.add(documentosexpediente.getDireccionContacto());
		params.add(documentosexpediente.getEntidadContacto());
		params.add(documentosexpediente.getContentType());
		params.add(documentosexpediente.getNombre());
		params.add(documentosexpediente.getRutaPif());
		params.add(documentosexpediente.getOrigen());
		
		String query = "INSERT INTO AA79B56S01 (ID_DOC_056, ANYO_056, NUM_EXP_056, OID_FICHERO_056, EXTENSION_DOC_056, TAMANO_DOC_056, IND_ENCRIPTADO_056, ID_DOC_REL_056, CLASE_DOCUMENTO_056, TIPO_DOCUMENTO_056, "
				+ "TITULO_056, NUM_PAL_SOLIC_056, NUM_PAL_IZO_056, PERSONA_CONTACTO_056, EMAIL_CONTACTO_056, TELEFONO_CONTACTO_056, IND_VISIBLE_056, FECHA_ALTA_056, DNI_ALTA_056, DIRECCION_CONTACTO_056,"
				+ "ENTIDAD_CONTACTO_056, CONTENT_TYPE_056, NOMBRE_FICHERO_056, RUTA_PIF_FICHERO_056, IND_ORIGEN_056) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?)";

		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);

		documentosexpediente.setIdDoc(elId);

		return documentosexpediente;
	}
	
}
