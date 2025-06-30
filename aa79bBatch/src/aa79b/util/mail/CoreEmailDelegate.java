/**
 * Fichero: CoreEmailDelegate.java
 * Autor: aresua
 * Fecha: 2017
 */
package aa79b.util.mail;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import aa79b.util.beans.MailFileBean;
import aa79b.util.common.Logger;

/**
 * Titulo: CoreEmailDelegate. Empresa: Eurohelp Consulting Copyright: Copyright
 * (c) 2017
 * 
 * @author aresua
 * @version 1.0
 *
 */
public class CoreEmailDelegate implements EmailDelegate {
	private String smtpHost = null;

	/**
	 * Un constructor para CoreEmailDelegate.
	 * 
	 * @author aresua
	 * @param smtpHost
	 *            El host smpt
	 */
	public CoreEmailDelegate(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * Este es un metodo sobreescrito.
	 * 
	 * @see aa79b.aa79butil.aa79bmail.aa79bEmailDelegate#sendEmail(Emailable)
	 * @author aresua
	 * @param emailMessage
	 *            El mensaje
	 */
	@Override
	public void sendEmail(Emailable emailMessage) {
		Session mailSession = null;
		try {
			final Properties props = System.getProperties();
			props.put("mail.smtp.host", this.smtpHost);
			props.put("mail.smtp.auth", "false");
			props.put("mail.stmp.sendpartial ", "true");

			
			Logger.batchlog(Logger.INFO, "Enviar el  mail a:" + emailMessage.getToAddress());
			
			final String[] destinatarios = emailMessage.getToAddress().split(";");
			mailSession = Session.getInstance(props, null);

			for (final String destinatario : destinatarios) {
				Logger.batchlog(Logger.INFO, "Destinatario:" + destinatario);
				if (!destinatario.isEmpty()) {
					final Message msg = new MimeMessage(mailSession);
					// Set the mail properties
					msg.setFrom(new InternetAddress(emailMessage.getFromAddress()));
					InternetAddress[] addresses = null;

						addresses = new InternetAddress[1];
						addresses[0] = new InternetAddress(destinatario);
						msg.setRecipients(javax.mail.Message.RecipientType.TO, addresses);

					if (emailMessage.getCccAddress() != null) {
						addresses = new InternetAddress[emailMessage.getCccAddress().size()];
						for (int i = 0; i < emailMessage.getCccAddress().size(); i++) {
							addresses[i] = new InternetAddress(emailMessage.getCccAddress().get(i));
						}
						msg.setRecipients(javax.mail.Message.RecipientType.BCC, addresses);
					}

					msg.setSubject(emailMessage.getSubject());
					msg.setSentDate(new Date());

					// Create the body text
					// Creamos y añadimos las partes
					final Multipart parts = new MimeMultipart();
					final MimeBodyPart mainBody = new MimeBodyPart();
					mainBody.setContent(emailMessage.getBody(), emailMessage.getContentType());

					parts.addBodyPart(mainBody);

					// Añadimos la colecion de ficheros que hubiera
					final Collection files = emailMessage.getFiles();
					if (files != null) {
						final Iterator i = files.iterator();
						while (i.hasNext()) {
							DataHandler hdl;
							final Object obj = i.next();

							// Si es un data handler o lo podemos crear
							// directamente lo
							// hacemos. En caso de tener un objeto
							// indefinido lo enviamos como tipo texto
							if (obj instanceof MailFileBean) {
								final MailFileBean aa79bMailFileBean = (MailFileBean) obj;
								hdl = new DataHandler(aa79bMailFileBean.getBytes(), aa79bMailFileBean.getContentType());

								final MimeBodyPart anexo = new MimeBodyPart();
								anexo.setDataHandler(hdl);
								anexo.setFileName(aa79bMailFileBean.getNombre());

								parts.addBodyPart(anexo);
							} else {
								if (obj instanceof DataHandler) {
									hdl = (DataHandler) obj;
								} else if (obj instanceof FileDataSource) {
									hdl = new DataHandler((FileDataSource) obj);
								} else if (obj instanceof DataSource) {
									hdl = new DataHandler((javax.activation.DataSource) obj);
								} else if (obj instanceof java.net.URL) {
									hdl = new DataHandler((java.net.URL) obj);
								} else {
									hdl = new DataHandler(obj, "text/plain");

								}
								final MimeBodyPart anexo = new MimeBodyPart();
								anexo.setDataHandler(hdl);
								anexo.setFileName((hdl.getName() == null) ? "noName" : hdl.getName());

								parts.addBodyPart(anexo);
							}
						}
					}

					msg.setContent(parts);

					Transport.send(msg);
				}
			}
		} catch (final Exception e) {
			Logger.batchlog(Logger.ERROR, "Error al enviar el mail");
			Logger.batchlog(Logger.ERROR, e.toString());
			throw new UndeclaredThrowableException(e);
		} finally {
			mailSession = null;
		}
	}
}
