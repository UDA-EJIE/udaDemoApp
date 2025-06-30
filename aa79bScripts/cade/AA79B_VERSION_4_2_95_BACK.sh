clear

# ------------------------------------------------------------------
###
echo "Comienzo de actualización de base de datos x54j versión 4.2.95..."
echo " "
echo "Obteniendo el objeto de seguridad"
. /aplic/n38/cade/n38bd 34047 01 STRING
STAT=$?
if [ $STAT != 0 ]; then
   echo "El objeto de seguridad no es válido."
   read resp
   exit 1
else
   echo "El objeto de seguridad es válido."
   USUARIO=`echo $STRING |cut -d '#' -f2`
   PASSWORD=`echo $STRING |cut -d '#' -f3`
fi

echo "Lanzando script /aplic/aa79b/scripts/X54J_V4_2_95_BACK.SQL"
sqlplus -s $USUARIO/$PASSWORD @/aplic/aa79b/scripts/X54J_V4_2_95_BACK.sql


###
echo "Comienzo de la creacion de la base de datos de AA79B"
echo " "
echo "Obteniendo el objeto de seguridad"
. /aplic/n38/cade/n38bd 34814 01 STRING
STAT=$?
if [ $STAT != 0 ]; then
	echo "El objeto de seguridad no es válido."
	read resp
	exit 1
else
	echo "El objeto de seguridad es válido."
	USUARIO=`echo $STRING |cut -d '#' -f2`
	PASSWORD=`echo $STRING |cut -d '#' -f3`
fi

echo "Lanzando script /aplic/aa79b/scripts/AA79B_VERSION_4_2_95_BACK.SQL"
sqlplus -s $USUARIO/$PASSWORD @/aplic/aa79b/scripts/AA79B_VERSION_4_2_95_BACK.SQL