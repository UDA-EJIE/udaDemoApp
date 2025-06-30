clear

# ------------------------------------------------------------------

# ------------------------------------------------------------------
###
echo "Grants en R75 para AA79B"
echo " "
echo " "

echo "Obteniendo el objeto de seguridad"
. /aplic/n38/cade/n38bd 32732 01 STRING
STAT=$?
if [ $STAT != 0 ]; then
	echo "El objeto de seguridad no es v�lido."
	read resp
	exit 1
else
	echo "El objeto de seguridad es v�lido."
	USUARIO=`echo $STRING |cut -d '#' -f2`
	PASSWORD=`echo $STRING |cut -d '#' -f3`
fi

echo "Lanzando script /aplic/aa79b/scripts/R75B_Migracion.sql"
sqlplus -s $USUARIO/$PASSWORD @/aplic/aa79b/scripts/R75B_Migracion.sql


###
echo "Comienzo de actualizaci�n de la base de datos de AA79B"
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

echo "Lanzando script /aplic/aa79b/scripts/AA79B_VERSION_3_6_04.SQL"
sqlplus -s $USUARIO/$PASSWORD @/aplic/aa79b/scripts/AA79B_VERSION_3_6_04.SQL

