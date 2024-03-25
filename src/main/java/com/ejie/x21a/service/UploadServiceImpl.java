/*
* Copyright 2011 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "uploadService")
public class UploadServiceImpl implements UploadService {

	@Override
	public Boolean saveToDisk(MultipartFile file, String path) {
		
		File fileToDisk = new File(path,file.getOriginalFilename());
		try {
			FileUtils.writeByteArrayToFile(fileToDisk, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean deleteFromDisk(String path, String fileName) {
		
		File fileToDelete = new File(path,fileName);
		
		 // Make sure the file or directory exists and isn't write protected
	    if (!fileToDelete.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + fileName);

	    if (!fileToDelete.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + fileName);

	    // If it is a directory, make sure it is empty
	    if (fileToDelete.isDirectory()) {
	      String[] files = fileToDelete.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException(
	            "Delete: directory not empty: " + fileName);
	    }

	    // Attempt to delete it
	    fileToDelete.delete();
	    
	    return Boolean.TRUE;
	}

	@Override
	public File getFromDisk(String path, String fileName) {
		
		File file = new File(path,fileName);
		
		return file;
	}
}
