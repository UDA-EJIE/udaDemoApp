#! /usr/bin/ksh
. /aplic/aa79b/.profile

DIAS=$1
RUTABORRADO_FILE=/datos/aa79b/file/*
RUTABORRADO_LOGS=/datos/aa79b/log/*
DATE=`date +%Y%m%d_%H%M%S`
TIME="`date +%H%M%S`"
export DATE
export TIME

#para redirigir la salida estandar a fichero
RUTA=/datos/aa79b/log/
FICHERO=aa79b_purga_${DATE}_${TIME}.log

echo "Comienzo de la purga" >> $RUTA/$FICHERO

#Comprobamos si tenemos dias concretos para el lanzamiento
if [ -z "$DIAS" ]; then
  echo " El argumento de dias es vacio"  >> $RUTA/$FICHERO
  echo " Le damos el valor de 365"  >> $RUTA/$FICHERO
  DIAS=365
fi


# Ejecutamos el borrado de la carpeta file
echo "Ejecutamos el borrado de la carpeta ($RUTABORRADO_FILE)" >> $RUTA/$FICHERO
find $RUTABORRADO_FILE -type f -not \( -iname "*\.jks" \) -not \( -iname "*\.P12" \) -not \( -iname "plantillaMail.txt" \) -mtime +$DIAS -exec rm -f {} \;

SALIDA=$?
if [ "$SALIDA" -gt 0 ]; then
	echo "Error en el borrado" >> $RUTA/$FICHERO
	exit 1
fi

# Ejecutamos el borrado de la carpeta logs
echo "Ejecutamos el borrado de la carpeta ($RUTABORRADO_LOGS)" >> $RUTA/$FICHERO
find $RUTABORRADO_LOGS -type f -not \( -iname "*\.jks" \) -not \( -iname "*\.P12" \) -mtime +$DIAS -exec rm -f {} \;

SALIDA=$?
if [ "$SALIDA" -gt 0 ]; then
	echo "Error en el borrado" >> $RUTA/$FICHERO
	exit 1
fi

echo "La purga ha funcionado correctamente" >> $RUTA/$FICHERO

exit 0 
