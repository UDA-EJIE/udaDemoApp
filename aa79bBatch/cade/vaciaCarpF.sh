#! /usr/bin/ksh
. /aplic/aa79b/.profile

# Borrado de archivos si se llena la carpeta /datos/aa79b/file/ durante las pruebas
rm -r /datos/aa79b/file/19*

#$(/aplic/y31/cade/y31ApiBatch.sh aa79b put /datos/aa79b/file/pruebaWS.zip /aa79b/tmp/pruebaWS.zip true 180000)

exit 0 