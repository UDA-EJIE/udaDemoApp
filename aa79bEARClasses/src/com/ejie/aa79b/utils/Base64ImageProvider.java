package com.ejie.aa79b.utils;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;

public class Base64ImageProvider extends AbstractImageProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(Base64ImageProvider.class);

	@Override
	public Image retrieve(String src) {
		int pos = src.indexOf("base64,");
		try {
			if (src.startsWith("data") && pos > 0) {
				byte[] img = Base64.decode(src.substring(pos + 7));
				return Image.getInstance(img);
			} else {
				return Image.getInstance(new URL(src));
			}
		} catch (BadElementException ex) {
			Base64ImageProvider.LOGGER.error("Base64ImageProvider - retrieve ", ex);
			return null;
		} catch (IOException ex) {
			Base64ImageProvider.LOGGER.error("Base64ImageProvider - retrieve ", ex);
			return null;
		}
	}

	@Override
	public String getImageRootPath() {
		return null;
	}
}
