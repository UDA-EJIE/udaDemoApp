/**
 * The file Utils.java.
 */
package com.ejie.aa79b.common;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * The type Utils.
 * 
 * @author dsierra
 */
public class JasperUtils {

    /**
     * The method loadReport.
     * 
     * @param resource
     *            Resource
     * @return JasperReport
     */
	public final JasperReport loadReport(Resource resource) {
		try {
			String filename = resource.getFilename();
			
			InputStream is = resource.getInputStream();
			try {
				return cargar(filename, is);
			} finally {
				is.close();
				}
		} catch (IOException ex) {
			throw new ApplicationContextException("Could not load JasperReports report from " + resource, ex);
		} catch (JRException ex) {
			throw new ApplicationContextException("Could not parse JasperReports report from " + resource, ex);
		}
	}

	private JasperReport cargar(String filename, InputStream is) throws JRException {
		if (filename != null) {
			if (filename.endsWith(".jasper")) {
				return (JasperReport) JRLoader.loadObject(is);
			} else if (filename.endsWith(".jrxml")) {
				JasperDesign design = JRXmlLoader.load(is);
				return JasperCompileManager.compileReport(design);
			}
		}
		throw new IllegalArgumentException("Report filename [" + filename + "] must end in either .jasper or .jrxml");
	}
}
