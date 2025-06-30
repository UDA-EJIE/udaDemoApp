package aa79b.mains;

import aa79b.batch.FirmarDocumento;

/**
 * w22ValidacionTest
 * @author hterron
 *
 */
public class TestFirmarDocumento {

	/**
	 * w22ValidacionTest
	 */
	private TestFirmarDocumento(){
	}
	/**
	 * @autor hterron
	 * @param args String[]
	 */
	public static void main(String[] args) {
		
//		String DNS = "jdbc:oracle:thin:@ejld1180:1530/x54.ejie.eus";
//		String DNS = "jdbc:oracle:thin:@172.16.0.59:1521:ora12";
//		String USUARIO_XLNETS = "aa79b";
//		String PASSWORD = "aa79b";
//		String DB_DRIVER = "oracle.jdbc.OracleDriver";
//		String rutaArchivoOriginal = "/datos/aa79b/file/192252_98344_20190523171336/ORIGINAL.zip";
//		String rutaArchivoFinal = "/datos/aa79b/file/192252_98344_20190523171336/FINAL.zip";
		String rutaArchivoOriginal = "C:\\pruebas\\ORIGINAL.zip";
		String rutaArchivoFinal = "C:\\pruebas\\FINAL.zip";
	
 
		String[] params = new String[]{
//				USUARIO_XLNETS,
//				PASSWORD,
//				DB_DRIVER ,
//				DNS, 
				rutaArchivoOriginal, rutaArchivoFinal
		};	
		
		FirmarDocumento.main(params);

	}

	
}
