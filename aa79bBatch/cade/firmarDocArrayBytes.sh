#! /usr/bin/ksh
. /aplic/aa79b/.profile

# Borrado de archivos si se llena la carpeta /datos/aa79b/file/ durante las pruebas
#rm -r /datos/aa79b/file/19*

# -----------------------------------------------------
# ----------------------------------------------------- Obtencion de fecha y hora actuales
# -----------------------------------------------------


DATE="`date +%Y%m%d`"
TIME="`date +%H%M%S`"
TIMESTAMP="`date +%Y%m%d%H%M%S`"

# -----------------------------------------------------
# ----------------------------------------------------- Inicializar variables
# -----------------------------------------------------


# Directorio desde donde se ejecuta
DIR_EJECUCION=/aplic/aa79b/cade
export DIR_EJECUCION
# Directorio donde estan los diferentes java
DIR_CLASE_MAIN=aa79b/batch
export DIR_CLASE_MAIN
# Fichero que ejecuta la descarga
CLASE_JAVA=DescargaPIDaPIF
export CLASE_JAVA
# Fichero que ejecuta la firma
CLASE_JAVA_FIRMA=FirmarDocumento
export CLASE_JAVA_FIRMA
CLASE_JAVA_UPLOAD_PID=SubirFirmasPIFaPID
export CLASE_JAVA_UPLOAD_PID
CLASE_JAVA_ERROR_ACT=ErrorEnFirma
export CLASE_JAVA_ERROR_ACT

#Parametros para el main de la primera clase java
#Servidor de BBDD
export DB_SERVER
#Driver de la conexion de BBDD
DB_DRIVER=oracle.jdbc.OracleDriver
export DB_DRIVER
#Definimos el id de tarea como parametro
IDTAREA=$1
#IDTAREA=192241
#Definimos el id de documento original como parametro
IDDOCORIGINAL=$2
#IDDOCORIGINAL=299


# Directorio para los logs
DIR_LOGS=/datos/aa79b/log
NOMBRE_FICHERO_LOGS=aa79bFirma_${DATE}_${TIME}_${IDDOCORIGINAL}
FICHERO_LOGS=${DIR_LOGS}/${NOMBRE_FICHERO_LOGS}.log


# ------------------------------------------------------------------

echo " "
echo "---------------------------------------------------------------------------------------------------------" >> $FICHERO_LOGS
echo "Comienzo del proceso batch java AA79B (Firmar documento) ....." >> $FICHERO_LOGS
date >>  $FICHERO_LOGS
echo " " >> $FICHERO_LOGS

echo "Comienzo objeto de seguridad XL-NETS"
# Para el objeto de seguridad XL-NETS

. n38bd   34814 01 STRING
echo "Recogido objeto de seguridad XL-NETS"
STAT=$?
if [ $STAT != 0 ]; then
   echo "El objeto de seguridad no es v�lido."
   read resp
   exit 1
else
   DNS=`echo $STRING |cut -d '#' -f1`
   USUARIO_XLNETS=`echo $STRING |cut -d '#' -f2`
   PASSWORD=`echo $STRING |cut -d '#' -f3`
fi






# Nombre del fichero donde dejaremos la ruta del PIF al bajar del PID
NOMBRE_CARPETA=${IDTAREA}_${IDDOCORIGINAL}_${TIMESTAMP}
#NOMBRE_CARPETA=${IDTAREA}_${IDDOCORIGINAL}_${DATE}_${TIME}
NOMBRE_FICHERO_TXT=${IDTAREA}_${IDDOCORIGINAL}_${TIMESTAMP}.txt
#NOMBRE_FICHERO_TXT=${IDTAREA}_${IDDOCORIGINAL}_${DATE}_${TIME}.txt
DIR_FILES_TMP=/datos/aa79b/file/${NOMBRE_CARPETA}
RUTA_PIF_BASE=/aa79b/tmp/
RUTA_PIF_BASE_SUBIDA_W43DF=/w43df/aa79b/
FIRMA_EXTENSION_DEFECTO=.firma

SALIDA=$?
if [ "$SALIDA" -gt 0 ]; then
	echo "Error al adjuntar fichero de datos (${FICHERO_LOGS}) al buzon"
	exit 2
fi

# Variable de control de errores
HAYERROR=0 


# PROCESO DE DESCARGA DEL PID AL PIF -----------------------------------------------------------------------------

echo "Nombre del fichero temporal con la ruta de la descarga del PID: $NOMBRE_FICHERO_TXT"  >> $FICHERO_LOGS
echo "Creamos la carpeta temporal: $DIR_FILES_TMP"  >> $FICHERO_LOGS
mkdir ${DIR_FILES_TMP}

cd $DIR_EJECUCION
CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar:/config/
$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA "$USUARIO_XLNETS" "$PASSWORD" "$DB_DRIVER" "$DNS" "$IDTAREA" "$IDDOCORIGINAL" "$NOMBRE_FICHERO_TXT" "$DIR_FILES_TMP" >> $FICHERO_LOGS
	
if [ ${?} != 0 ]; then	
	echo "El proceso java para descargar documento del PID al PIF no ha finalizado correctamente" >> $FICHERO_LOGS
	echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores" >> $FICHERO_LOGS
	#exit 1
	HAYERROR=1
else
	echo "El proceso java para descargar documento del PID al PIF documentos ha finalizado correctamente" >> $FICHERO_LOGS
fi





# PROCESO DE DESCARGA DEL PIF A CARPETA /datos/aa79b/files/ -----------------------------------------------------------------------------

if [ $HAYERROR = 0 ]
then
	echo "cat de ${DIR_FILES_TMP}/${NOMBRE_FICHERO_TXT}" >> $FICHERO_LOGS
	RUTA_PIF_FICH_DESCARGADO=`cat ${DIR_FILES_TMP}/${NOMBRE_FICHERO_TXT}`
	echo "RUTA_PIF_FICH_DESCARGADO ($RUTA_PIF_FICH_DESCARGADO)" >> $FICHERO_LOGS
	
	RUTA_PIF_FICH_FINAL="$(echo $RUTA_PIF_FICH_DESCARGADO | cut -d '$' -f2)"
	
	echo "RUTA_PIF_FICH_FINAL= $RUTA_PIF_FICH_FINAL" >> $FICHERO_LOGS
	
	EXTENSION_ARCHIVO_PIF_FINAL=`echo "${RUTA_PIF_FICH_FINAL##*.}"`
	NOMBRE_ARCHIVO_PIF_FINAL=FINAL.${EXTENSION_ARCHIVO_PIF_FINAL}
y	
	# DESCARGA DEL FICHERO FINAL
	echo "Recuperamos el fichero $RUTA_PIF_FICH_FINAL y lo copiamos al directorio ${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL} " >> $FICHERO_LOGS
	/aplic/y31/cade/y31ApiBatch.sh aa79b get $RUTA_PIF_FICH_FINAL ${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL}
	echo "GET PIF  FINAL CORRECTO!!! ${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL}" >> $FICHERO_LOGS
fi



# Llamada a la clase que obtiene la firma del fichero y la guarda ----------------------------------------------------------------

if [ $HAYERROR = 0 ]
then

	echo "............. LLAMADA A FIRMARDOCUMENTO " >> $FICHERO_LOGS
	
	cd $DIR_EJECUCION
	CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar:/config/
	$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA_FIRMA "${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL}" >> $FICHERO_LOGS
		
	if [ ${?} != 0 ]; then	
		echo "El proceso java para firmar documento no ha finalizado correctamente" >> $FICHERO_LOGS
		echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores" >> $FICHERO_LOGS
		#exit 1
		HAYERROR=1
	else
		echo "El proceso java para firmar documento ha finalizado correctamente" >> $FICHERO_LOGS
	fi
fi






# PROCESO DE SUBIDA DE LAS FIRMAS AL PIF ------------------------------------------------------------------------------------------
# PUEDEN SER  2 FIRMAS o DOS ARCHIVOS ZIP ----------------------------------------------------------------------------

if [ $HAYERROR = 0 ]
then
	if [ "${EXTENSION_ARCHIVO_PIF_FINAL}" = "ZIP" ] || [ "${EXTENSION_ARCHIVO_PIF_FINAL}" = "zip" ] || [ "${EXTENSION_ARCHIVO_PIF_FINAL}" = "Zip" ]
	then 
		NOMBRE_FINAL_FIRMADO=FINAL_FIRMADO.zip
	else
		NOMBRE_FINAL_FIRMADO=FINAL${FIRMA_EXTENSION_DEFECTO}
	fi
	
	# SUBIDA PIF DEL FICHERO FINAL FIRMADO (ZIP) O SOLO EL ARCHIVO DE FIRMA
	echo "Subimos el fichero ${DIR_FILES_TMP}/${NOMBRE_FINAL_FIRMADO} al PIF: ${RUTA_PIF_BASE_SUBIDA_W43DF}${NOMBRE_FINAL_FIRMADO}" >> $FICHERO_LOGS
	resSubidaFinal=$(/aplic/y31/cade/y31ApiBatch.sh aa79b put ${DIR_FILES_TMP}/${NOMBRE_FINAL_FIRMADO} ${RUTA_PIF_BASE_SUBIDA_W43DF}${NOMBRE_FINAL_FIRMADO} false 18000)
	echo $resSubidaFinal >> $FICHERO_LOGS
	FIRMA_FINAL_RUTA_PIF=`echo $resSubidaFinal | cut -d '#' -f 2` >> $FICHERO_LOGS
	FIRMA_FINAL_CONTENT_TYPE=`echo $resSubidaFinal | cut -d '#' -f 1` >> $FICHERO_LOGS
	FIRMA_FINAL_TAMANO=`echo $resSubidaFinal | cut -d '#' -f 4` >> $FICHERO_LOGS
	
	if [ -z $FIRMA_FINAL_RUTA_PIF ] || [ -z $FIRMA_FINAL_RUTA_PIF ]; then	
		echo "El proceso de subida de las firmas al PIF NO ha finalizado correctamente" >> $FICHERO_LOGS
		HAYERROR=1
	else
		echo "El proceso de subida de las firmas al PIF ha finalizado correctamente" >> $FICHERO_LOGS
	fi
	
fi





# PROCESO DE SUBIDA DE LAS FIRMAS DEL PIF AL PID ------------------------------------------------------------------------------------------
# Inserts en la T88, modificar estado firma T92 ------------------------------------------------------------------------------------------


if [ $HAYERROR = 0 ]
then
	
	cd $DIR_EJECUCION
	CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar:/config/
	$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA_UPLOAD_PID "$USUARIO_XLNETS" "$PASSWORD" "$DB_DRIVER" "$DNS" "$IDTAREA" "$IDDOCORIGINAL" "$FIRMA_FINAL_RUTA_PIF" "$FIRMA_FINAL_CONTENT_TYPE" "$FIRMA_FINAL_TAMANO" >> $FICHERO_LOGS
		
	if [ ${?} != 0 ]; then	
		echo "El proceso java para SUBIDA DE LAS FIRMAS DEL PIF AL PID no ha finalizado correctamente" >> $FICHERO_LOGS
		echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores" >> $FICHERO_LOGS
		#exit 1
		HAYERROR=1
	else
		echo "El proceso java para SUBIDA DE LAS FIRMAS DEL PIF AL PID ha finalizado correctamente" >> $FICHERO_LOGS
	fi
fi


# QUEDA ELIMINAR LOS FICHEROS TEMPORALES (toda la careta) --------------------------------------------------

echo "Comienza el borrado de la carpeta de ficheros temporales" >> $FICHERO_LOGS

if [ `echo ${#DIR_FILES_TMP}` -gt 20 ] 
then 
	echo "BORRAR CARPETA" >> $FICHERO_LOGS
	rm -r ${DIR_FILES_TMP}
else 
	echo "NO SE BORRA LA CARPETA" >> $FICHERO_LOGS
fi



# Si ha habido error en cualquier punto del proceso ACTUALIZO LA T92 con estado ERROR ----------------------------------------------------------

if [ $HAYERROR != 0 ]
then
	
	cd $DIR_EJECUCION
	CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar:/config/
	$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA_ERROR_ACT "$USUARIO_XLNETS" "$PASSWORD" "$DB_DRIVER" "$DNS" "$IDTAREA" "$IDDOCORIGINAL" >> $FICHERO_LOGS
		
	if [ ${?} != 0 ]; then	
		echo "El proceso java para actualizar con ERROR el estado de firma NO ha finalizado correctamente" >> $FICHERO_LOGS
		echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores" >> $FICHERO_LOGS
		exit 1
	else
		echo "El proceso java para actualizar con ERROR el estado de firma ha finalizado correctamente" >> $FICHERO_LOGS
		exit 1
	fi
else
	echo "EL PROCESO COMPLETO DE FIRMA DE DOCUMENTOS HA FINALIZADO CORRECTAMENTE" >> $FICHERO_LOGS
fi
exit 0 