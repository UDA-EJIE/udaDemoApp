#! /usr/bin/ksh
. /aplic/aa79b/.profile

# Borrado de archivos si se llena la carpeta /datos/aa79b/file/ durante las pruebas
#rm -r /datos/aa79b/file/19*
#rm -r /datos/aa79b/file/40*


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
#Definimos el id de documento original como parametro
IDDOCORIGINAL=$2


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
NOMBRE_FICHERO_TXT=${IDTAREA}_${IDDOCORIGINAL}_${TIMESTAMP}.txt

DIR_FILES_TMP=/datos/aa79b/file/${NOMBRE_CARPETA}
RUTA_PIF_BASE=/aa79b/tmp/
RUTA_PIF_BASE_SUBIDA_W43DF=/w43df/aa79b/
RUTA_PIF_SUBIDA_R02G=/r02g/aa79b/${NOMBRE_CARPETA}
FIRMA_EXTENSION_DEFECTO=.firma
ESZIP=0


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
CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar
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
	
	RUTA_PIF_FICH_ORIGINAL_FINAL="$(echo $RUTA_PIF_FICH_DESCARGADO | cut -d '$' -f1)"
	RUTA_PIF_FICH_FINAL="$(echo $RUTA_PIF_FICH_DESCARGADO | cut -d '$' -f2)"
	
	echo "RUTA_PIF_FICH_ORIGINAL_FINAL= $RUTA_PIF_FICH_ORIGINAL_FINAL -----  RUTA_PIF_FICH_FINAL= $RUTA_PIF_FICH_FINAL" >> $FICHERO_LOGS
	
	EXTENSION_ARCHIVO_PIF_ORIGINAL_FINAL=`echo "${RUTA_PIF_FICH_ORIGINAL_FINAL##*.}"`
	EXTENSION_ARCHIVO_PIF_FINAL=`echo "${RUTA_PIF_FICH_FINAL##*.}"`
	
	NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL=ORIGINAL.${EXTENSION_ARCHIVO_PIF_ORIGINAL_FINAL}
	NOMBRE_ARCHIVO_PIF_FINAL=FINAL.${EXTENSION_ARCHIVO_PIF_FINAL}
	
	if [ "${EXTENSION_ARCHIVO_PIF_FINAL}" = "ZIP" ] || [ "${EXTENSION_ARCHIVO_PIF_FINAL}" = "zip" ] || [ "${EXTENSION_ARCHIVO_PIF_FINAL}" = "Zip" ]
	then 
		ESZIP=1
	fi
	
	#Creo los directorios donde meteremos los archivos que se enviar�n a firmar...	
	mkdir ${DIR_FILES_TMP}/ARCH_FINALES
	mkdir ${DIR_FILES_TMP}/ARCH_ORIGINALES

	if [ $ESZIP = 1 ]
	then 
		# DESCARGA DEL FICHERO FINAL
		echo "Recuperamos el fichero $RUTA_PIF_FICH_FINAL y lo copiamos al directorio ${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL} " >> $FICHERO_LOGS
		/aplic/y31/cade/y31ApiBatch.sh aa79b get $RUTA_PIF_FICH_FINAL ${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL}
	else
		# DESCARGA DEL FICHERO ORIGINAL FINAL, COMO NO ES ZIP, LO BAJO DIRECTAMENTE A LA RUTA QUE ENVIAREMOS AL PIF DE R02G
		echo "Recuperamos el fichero $RUTA_PIF_FICH_ORIGINAL_FINAL y lo copiamos al directorio ${DIR_FILES_TMP}/ARCH_ORIGINALES/${NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL} " >> $FICHERO_LOGS
		/aplic/y31/cade/y31ApiBatch.sh aa79b get $RUTA_PIF_FICH_ORIGINAL_FINAL ${DIR_FILES_TMP}/ARCH_ORIGINALES/${NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL}
		
		# DESCARGA DEL FICHERO FINAL, COMO NO ES ZIP, LO BAJO DIRECTAMENTE A LA RUTA QUE ENVIAREMOS AL PIF DE R02G
		echo "Recuperamos el fichero $RUTA_PIF_FICH_FINAL y lo copiamos al directorio ${DIR_FILES_TMP}/ARCH_FINALES/${NOMBRE_ARCHIVO_PIF_FINAL} " >> $FICHERO_LOGS
		/aplic/y31/cade/y31ApiBatch.sh aa79b get $RUTA_PIF_FICH_FINAL ${DIR_FILES_TMP}/ARCH_FINALES/${NOMBRE_ARCHIVO_PIF_FINAL}
	fi
fi





# DESCOMPRIMIR ZIPs /datos/aa79b/files/ -----------------------------------------------------------------------------

if [ $HAYERROR = 0 ] && [ $ESZIP = 1 ]
then 
	echo "DESCOMPRIMIR .......${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL}  -d ${DIR_FILES_TMP}/ARCH_FINALES ..........." >> $FICHERO_LOGS
	unzip ${DIR_FILES_TMP}/${NOMBRE_ARCHIVO_PIF_FINAL} -d ${DIR_FILES_TMP}/ARCH_FINALES
	
	#renombramos para evitar espacios en los ficheros
	for origen in ${DIR_FILES_TMP}/ARCH_FINALES/*.* 
	do
		destino=${origen// /%20}
		mv "$origen" "$destino"  
	done
	
	# SUBIR LOS ARCHIVOS AL PIF DE /r02g/aa79b ---------------------------------------------------------------------------
	echo "subimos los archivos finales descomprimidos a ${RUTA_PIF_SUBIDA_R02G}_FIN " >> $FICHERO_LOGS
	$(/aplic/y31/cade/y31ApiBatch.sh aa79b putd ${DIR_FILES_TMP}/ARCH_FINALES/ ${RUTA_PIF_SUBIDA_R02G}_FIN true 18000)
else
	# Archivos NO zip
	echo "subimos los archivos NO ZIP ${DIR_FILES_TMP}/ARCH_ORIGINALES/${NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL} y ${DIR_FILES_TMP}/ARCH_FINALES/${NOMBRE_ARCHIVO_PIF_FINAL} ......... a ${RUTA_PIF_SUBIDA_R02G}_ORIG/${NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL} Y _FIN " >> $FICHERO_LOGS
	$(/aplic/y31/cade/y31ApiBatch.sh aa79b put ${DIR_FILES_TMP}/ARCH_ORIGINALES/${NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL} ${RUTA_PIF_SUBIDA_R02G}_ORIG/${NOMBRE_ARCHIVO_PIF_ORIGINAL_FINAL} true 18000)	
	$(/aplic/y31/cade/y31ApiBatch.sh aa79b put ${DIR_FILES_TMP}/ARCH_FINALES/${NOMBRE_ARCHIVO_PIF_FINAL} ${RUTA_PIF_SUBIDA_R02G}_FIN/${NOMBRE_ARCHIVO_PIF_FINAL} true 18000)	
fi






# Llamada a la clase que obtiene la firma del fichero y la guarda ----------------------------------------------------------------
		
if [ $HAYERROR = 0 ]
then

	echo "............. LLAMADA A FIRMARDOCUMENTO " >> $FICHERO_LOGS
	
	cd $DIR_EJECUCION
	CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar:/config/
	$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA_FIRMA "${DIR_FILES_TMP}/ARCH_ORIGINALES/" "${DIR_FILES_TMP}/ARCH_FINALES/" "${RUTA_PIF_SUBIDA_R02G}" >> $FICHERO_LOGS
		
	if [ ${?} != 0 ]; then	
		echo "El proceso java para firmar documento no ha finalizado correctamente" >> $FICHERO_LOGS
		echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores" >> $FICHERO_LOGS
		#exit 1
		HAYERROR=1
	else
		echo "El proceso java para firmar documento ha finalizado correctamente" >> $FICHERO_LOGS
	fi
fi
	
	

# SI ES ZIP RENOMBRO Y COMPRIMO LOS DOCUMENTOS JUNTO CON SUS FIRMAS 
	
if [ $HAYERROR = 0 ] && [ $ESZIP = 1 ]
then 
	#renombramos para restablecer espacios en los ficheros
	for origen in ${DIR_FILES_TMP}/ARCH_FINALES/*.*
	do
		destino=${origen//%20/ }
		mv "$origen" "$destino"  
	done

	echo "COMPRIMIR .......${DIR_FILES_TMP}/ARCH_FINALES ..........." >> $FICHERO_LOGS
	zip -r -j  ${DIR_FILES_TMP}/FINAL_FIRMADO.zip ${DIR_FILES_TMP}/ARCH_FINALES
fi	




# PROCESO DE SUBIDA DE LAS FIRMAS AL PIF ------------------------------------------------------------------------------------------
# PUEDEN SER  2 FIRMAS o DOS ARCHIVOS ZIP ----------------------------------------------------------------------------

if [ $HAYERROR = 0 ]
then
	#Extensi�n .zip o .firma
	if [ $ESZIP = 1 ]
	then 
		NOMBRE_FINAL_FIRMADO=FINAL_FIRMADO.zip
		RUTA_AUX_FINAL=
	else
		NOMBRE_FINAL_FIRMADO=FINAL${FIRMA_EXTENSION_DEFECTO}
		RUTA_AUX_FINAL=/ARCH_FINALES
		NOMBRE_ORIG_FINAL_FIRMADO=ORIGINAL${FIRMA_EXTENSION_DEFECTO}
		RUTA_AUX_ORIG=/ARCH_ORIGINALES
		
		# SUBIDA PIF DEL FICHERO ORIGINAL FINAL FIRMADO SOLO EL ARCHIVO DE FIRMA
	
		echo "Subimos el fichero ${DIR_FILES_TMP}${RUTA_AUX_ORIG}/${NOMBRE_ORIG_FINAL_FIRMADO} al PIF: ${RUTA_PIF_BASE_SUBIDA_W43DF}${NOMBRE_ORIG_FINAL_FIRMADO}" >> $FICHERO_LOGS
		resSubidaOrigFinal=$(/aplic/y31/cade/y31ApiBatch.sh aa79b put ${DIR_FILES_TMP}${RUTA_AUX_ORIG}/${NOMBRE_ORIG_FINAL_FIRMADO} ${RUTA_PIF_BASE_SUBIDA_W43DF}${NOMBRE_ORIG_FINAL_FIRMADO} false 18000)
		echo $resSubidaOrig >> $FICHERO_LOGS
		FIRMA_ORIGINAL_FINAL_RUTA_PIF=`echo $resSubidaOrigFinal | cut -d '#' -f 2` >> $FICHERO_LOGS
		FIRMA_ORIGINAL_FINAL_CONTENT_TYPE=`echo $resSubidaOrigFinal | cut -d '#' -f 1` >> $FICHERO_LOGS
		FIRMA_ORIGINAL_FINAL_TAMANO=`echo $resSubidaOrigFinal | cut -d '#' -f 4` >> $FICHERO_LOGS
		
		if [ -z $FIRMA_ORIGINAL_FINAL_RUTA_PIF ] || [ -z $FIRMA_ORIGINAL_FINAL_RUTA_PIF ]; then	
			echo "El proceso de subida de la firma ORIGINAL FINAL al PIF NO ha finalizado correctamente" >> $FICHERO_LOGS
			HAYERROR=1
		else
			echo "El proceso de subida de las firma ORIGINAL FINAL al PIF ha finalizado correctamente" >> $FICHERO_LOGS
		fi
		
	fi
	
	# SUBIDA PIF DEL FICHERO FINAL FIRMADO (ZIP) O SOLO EL ARCHIVO DE FIRMA
	
	echo "Subimos el fichero ${DIR_FILES_TMP}${RUTA_AUX_FINAL}/${NOMBRE_FINAL_FIRMADO} al PIF: ${RUTA_PIF_BASE_SUBIDA_W43DF}${NOMBRE_FINAL_FIRMADO}" >> $FICHERO_LOGS
	resSubidaFinal=$(/aplic/y31/cade/y31ApiBatch.sh aa79b put ${DIR_FILES_TMP}${RUTA_AUX_FINAL}/${NOMBRE_FINAL_FIRMADO} ${RUTA_PIF_BASE_SUBIDA_W43DF}${NOMBRE_FINAL_FIRMADO} false 18000)
	echo $resSubidaFinal >> $FICHERO_LOGS
	FIRMA_FINAL_RUTA_PIF=`echo $resSubidaFinal | cut -d '#' -f 2` >> $FICHERO_LOGS
	FIRMA_FINAL_CONTENT_TYPE=`echo $resSubidaFinal | cut -d '#' -f 1` >> $FICHERO_LOGS
	FIRMA_FINAL_TAMANO=`echo $resSubidaFinal | cut -d '#' -f 4` >> $FICHERO_LOGS
	
	if [ -z $FIRMA_FINAL_RUTA_PIF ] || [ -z $FIRMA_FINAL_RUTA_PIF ]; then	
		echo "El proceso de subida de la firma FICHERO FINAL al PIF NO ha finalizado correctamente" >> $FICHERO_LOGS
		HAYERROR=1
	else
		echo "El proceso de subida de la firma FICHERO FINAL al PIF ha finalizado correctamente" >> $FICHERO_LOGS
	fi
	
fi






# PROCESO DE SUBIDA DE LAS FIRMAS DEL PIF AL PID ------------------------------------------------------------------------------------------
# Inserts en la T88, modificar estado firma T92 ------------------------------------------------------------------------------------------

if [ $HAYERROR = 0 ]
then
	
	cd $DIR_EJECUCION
	CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-databind-2.6.5.jar:/config/
	$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA_UPLOAD_PID "$USUARIO_XLNETS" "$PASSWORD" "$DB_DRIVER" "$DNS" "$IDTAREA" "$IDDOCORIGINAL" "$FIRMA_ORIGINAL_FINAL_RUTA_PIF" "$FIRMA_ORIGINAL_FINAL_CONTENT_TYPE" "$FIRMA_ORIGINAL_FINAL_TAMANO" "$FIRMA_FINAL_RUTA_PIF" "$FIRMA_FINAL_CONTENT_TYPE" "$FIRMA_FINAL_TAMANO" >> $FICHERO_LOGS
		
	if [ ${?} != 0 ]; then	
		echo "El proceso java para SUBIDA DE LAS FIRMAS DEL PIF AL PID no ha finalizado correctamente" >> $FICHERO_LOGS
		echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores" >> $FICHERO_LOGS
		#exit 1
		HAYERROR=1
	else
		echo "El proceso java para SUBIDA DE LAS FIRMAS DEL PIF AL PID ha finalizado correctamente" >> $FICHERO_LOGS
	fi
fi



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