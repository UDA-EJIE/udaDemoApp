clear

# ------------------------------------------------------------------
###
echo "Alta de Roles y permisos para Itzulnet en X54J"
echo " "
echo " "

echo "Obteniendo el objeto de seguridad"
. /aplic/n38/cade/n38bd 34047 01 STRING
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

echo "Lanzando script /aplic/aa79b/scripts/Cargas/X54JCargasIniciales.SQL"
sqlplus -s $USUARIO/$PASSWORD @/aplic/aa79b/scripts/Cargas/X54JCargasIniciales.SQL
