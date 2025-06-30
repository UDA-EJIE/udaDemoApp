package aa79b.bbdd.solvers; 

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.DocumentoTareaAdjunto;
import aa79b.util.common.AccesoBD;

/**
 * @author javarona
 *
 */
public class DocumentoTareaSolver {
		
	
	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<DocumentoTareaAdjunto> rwMap = new RowMapper<DocumentoTareaAdjunto>() {
		@Override
		public DocumentoTareaAdjunto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final DocumentoTareaAdjunto documentoTarea = new DocumentoTareaAdjunto();
			documentoTarea.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
			documentoTarea.setIdDocOriginal(resultSet.getBigDecimal("IDDOCORIGINAL"));
			documentoTarea.setIdDocOriginalFinal(resultSet.getBigDecimal("IDDOCFINALORIG"));
			documentoTarea.setIdFichero(resultSet.getBigDecimal("IDFICHEROFINAL"));
			documentoTarea.setOidOriginal(resultSet.getString("OIDORIGINAL"));
			documentoTarea.setOidOriginalFinal(resultSet.getString("OIDORIGINALFINAL"));
			documentoTarea.setOidFinal(resultSet.getString("OIDFINAL"));
			documentoTarea.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
			return documentoTarea;
		}
	};
	private final RowMapper<DocumentoTareaAdjunto> rwMapT88Basico = new RowMapper<DocumentoTareaAdjunto>() {
		@Override
		public DocumentoTareaAdjunto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final DocumentoTareaAdjunto documentoTarea = new DocumentoTareaAdjunto();
			
			documentoTarea.setIdFichero(resultSet.getBigDecimal("IDFICHEROFINAL"));
			documentoTarea.setNombre(resultSet.getString("NOMBREFICHEROFINAL"));
			documentoTarea.setTitulo(resultSet.getString("TITULOFICHEROFINAL"));
			return documentoTarea;
		}
	};

	/**
	 *
	 * @param baseSql BaseSql
	 * @return List<DocumentosExpediente>
	 * @throws Exception e
	 */
	public List<DocumentoTareaAdjunto> leerInfoT92(BaseSql baseSql, Long idTarea, Long idDocOriginal) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT ID_DOC_FINAL_ORIG_092 IDDOCFINALORIG, ID_FICHERO_FINAL_092 IDFICHEROFINAL, ID_DOC_ORIG_092 IDDOCORIGINAL,OID_FICHERO_056 OIDORIGINAL,originalFinal.OID_FICHERO_088 OIDORIGINALFINAL,ficheroFinal.OID_FICHERO_088 OIDFINAL, ID_TAREA_092 IDTAREA, ESTADO_FIRMA_092 ESTADOFIRMA "); 
		query.append(" FROM AA79B92S01 ");
		query.append(" JOIN AA79B56S01 ON ID_DOC_ORIG_092 = ID_DOC_056 ");
		query.append(" JOIN AA79B88S01 ficheroFinal ON ID_FICHERO_FINAL_092 = ficheroFinal.ID_FICHERO_088 "); 
		query.append(" LEFT JOIN AA79B88S01 originalFinal ON ID_DOC_FINAL_ORIG_092 = originalFinal.ID_FICHERO_088 "); 
		query.append(" WHERE ID_TAREA_092 = ? AND ID_DOC_ORIG_092 = ? ");
		 
		params.add(idTarea);
		params.add(idDocOriginal);
		
		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}


	/**
	 *
	 * @param baseSql BaseSql
	 * @return List<DocumentosExpediente>
	 * @throws Exception e
	 */
	public List<DocumentoTareaAdjunto> leerInfoBasicaDocFinal(BaseSql baseSql, BigDecimal idDocFinal) throws Exception{
		final StringBuilder sb = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		sb.append("SELECT t1.ID_FICHERO_088 IDFICHEROFINAL, t1.NOMBRE_FICHERO_088 NOMBREFICHEROFINAL, t1.TITULO_FICHERO_088 TITULOFICHEROFINAL");
		sb.append(" FROM AA79B88S01 t1 WHERE t1.ID_FICHERO_088 = ?");
		params.add(idDocFinal);
		return new AccesoBD().fncLanzaBusqueda(sb.toString(), params.toArray(), this.rwMapT88Basico, baseSql);
	}
	
	
	
	public void insertTabla88(BaseSql baseSql, DocumentoTareaAdjunto documentoTareaAdjunto ) throws Exception {
		final Long elIdLong = this.getNextVal( baseSql );
		final BigDecimal elId = new BigDecimal(elIdLong.longValue());
		
		documentoTareaAdjunto.setIdFichero(elId);
		
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" INSERT INTO AA79B88S01 (ID_FICHERO_088, TITULO_FICHERO_088, EXTENSION_FICHERO_088, CONTENT_TYPE_FICHERO_088, TAMANO_FICHERO_088, IND_ENCRIPTADO_088, OID_FICHERO_088, NOMBRE_FICHERO_088) VALUES(?,?,?,?,?,?,?,?) "); 
		params.add(elId);
		params.add(documentoTareaAdjunto.getTitulo());
		params.add(documentoTareaAdjunto.getExtension());
		params.add(documentoTareaAdjunto.getContentType());
		params.add(documentoTareaAdjunto.getTamano());
		params.add(documentoTareaAdjunto.getEncriptado());
		params.add(documentoTareaAdjunto.getOidOriginal());
		params.add(documentoTareaAdjunto.getNombre());
		
		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
	
	
	public void updateEstadoTabla92(BaseSql baseSql, DocumentoTareaAdjunto documentoTareaAdjunto ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" UPDATE AA79B92S01 SET ESTADO_FIRMA_092=?, ID_DOC_FIRMADO_ORIG_092=?, ID_DOC_FIRMADO_FINAL_092=? "); 
		query.append(" WHERE ID_TAREA_092 = ? AND ID_DOC_ORIG_092 = ? ");
		
		params.add( documentoTareaAdjunto.getEstadoFirma() );
		params.add( documentoTareaAdjunto.getIdDocFirmaOriginal() );
		params.add( documentoTareaAdjunto.getIdDocFirmaFinal() );
		params.add( documentoTareaAdjunto.getIdTarea() );
		params.add( documentoTareaAdjunto.getIdDocOriginal() );
		
		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
	
	
	public Long getNextVal(BaseSql baseSql) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT AA79B88Q00.NEXTVAL FROM DUAL");
        return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), null, baseSql));
    }
	
}
