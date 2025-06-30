#! /usr/bin/ksh
. /aplic/aa79b/.profile


# -----------------------------------------------------
# ----------------------------------------------------- Obtencion de fecha y hora actuales
# -----------------------------------------------------


DATE="`date +%Y%m%d`"
TIME="`date +%H%M%S`"
TIMESTAMP="`date +%Y%m%d%H%M%S`"

# -----------------------------------------------------
# ----------------------------------------------------- Inicializar variables
# -----------------------------------------------------


# Directorio para los logs
DIR_LOGS=/datos/aa79b/log
DIR_FILES=/datos/aa79b/file

NOMBRE_FICHERO_LOGS=aa79bAvisosAuditorias_${DATE}_${TIME}
FICHERO_LOGS=${DIR_LOGS}/${NOMBRE_FICHERO_LOGS}.log

# Directorio desde donde se ejecuta

DIR_EJECUCION=/aplic/aa79b/cade
export DIR_EJECUCION
# Fichero que se ejecuta
CLASE_JAVA=AvisosAuditorias
export CLASE_JAVA
# Directorio donde estan los diferentes java
DIR_CLASE_MAIN=aa79b/batch

export DIR_CLASE_MAIN
# ------------------------------------------------------------------

echo " "
echo "---------------------------------------------------------------------------------------------------------" >> $FICHERO_LOGS
echo "Comienzo del proceso batch java AA79B (Avisos Auditorias) ....." >> $FICHERO_LOGS
date >>  $FICHERO_LOGS
echo " " >> $FICHERO_LOGS

echo "Comienzo objeto de seguridad XL-NETS"
# Para el objeto de seguridad XL-NETS

. n38bd   34814 01 STRING
echo "Recogido objeto de seguridad XL-NETS"
STAT=$?
if [ $STAT != 0 ]; then
   echo "El objeto de seguridad no es valido."
   read resp
   exit 1
else
   DNS=`echo $STRING |cut -d '#' -f1`
   USUARIO_XLNETS=`echo $STRING |cut -d '#' -f2`
   PASSWORD=`echo $STRING |cut -d '#' -f3`
fi

#Parametros para el main de la clase java
#Servidor de BBDD
export DB_SERVER
#Driver de la conexion de BBDD
DB_DRIVER=oracle.jdbc.OracleDriver
export DB_DRIVER

SALIDA=$?
if [ "$SALIDA" -gt 0 ]; then
	echo "Error al adjuntar fichero de datos (${FICHERO_LOGS}) al buzon"
	exit 2
fi

cd $DIR_EJECUCION
CLASSPATH_JAVA_PROCESO=/aplic/aa79b/lib/imap.jar:/aplic/aa79b/lib/mail.jar:/aplic/aa79b/lib/mailapi.jar:/aplic/aa79b/lib/pop3.jar:/aplic/aa79b/lib/smpt.jar:/aplic/aa79b/dist/aa79b.jar:$ORACLE_HOME/jdbc/lib/ojdbc6.jar:/config/:/aplic/aa79b/lib/commons-lang3-3.1.jar:/aplic/aa79b/lib/commons-io-1.3.1.jar:/aplic/aa79b/lib/jackson-core-asl-1.9.7.jar:/aplic/aa79b/lib/jackson-mapper-asl-1.9.7.jar
$JAVA_HOME/bin/java -Dfile.encoding=ISO-8859-15 -d64 -cp $CLASSPATH_JAVA_PROCESO $DIR_CLASE_MAIN.$CLASE_JAVA "$USUARIO_XLNETS" "$PASSWORD" "$DB_DRIVER" "$DNS" >> $FICHERO_LOGS
	
if [ ${?} != 0 ]; then	
	echo "El proceso java para enviar avisos de auditoria no ha finalizado correctamente"
	echo "Comprobar fichero de datos ($FICHERO_LOGS) para detectar errores"
	exit 1
else
	echo "El proceso java para enviar avisos de auditoria ha finalizado correctamente"
fi


echo "El proceso ha finalizado correctamente"
exit 0 