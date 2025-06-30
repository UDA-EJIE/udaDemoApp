package aa79b.mains;

import java.util.Date;

import aa79b.batch.DescargaPIDaPIF;

/**
 * w22ValidacionTest
 * @author hterron
 *
 */
public class TestDescargaPIDaPIF {

	/**
	 * w22ValidacionTest
	 */
	private TestDescargaPIDaPIF(){
	}
	/**
	 * @autor hterron
	 * @param args String[]
	 */
	public static void main(String[] args) {

		String DNS = "jdbc:oracle:thin:@ejld1180:1530/x54.ejie.eus";
		//String DNS = "jdbc:oracle:thin:@172.16.0.59:1521:ora12";
		String USUARIO_XLNETS = "aa79b";
		String PASSWORD = "aa79b";
		String DB_DRIVER = "oracle.jdbc.OracleDriver";
		String idTarea = "259858";
		String idDocOriginal = "162515";
		String timestamp = new Date().getTime()+ "";
		String nombreFicheroRutaPif = idTarea+"_"+idDocOriginal+"_"+ timestamp+".txt";
		String nombreCapeta = "/datos/aa79b/file/" + idTarea+"_"+idDocOriginal+"_"+ timestamp;


		String[] params = new String[]{
				USUARIO_XLNETS,
				PASSWORD,
				DB_DRIVER ,
				DNS, idTarea, idDocOriginal, nombreFicheroRutaPif, nombreCapeta
		};

		DescargaPIDaPIF.main(params);

	}


}
