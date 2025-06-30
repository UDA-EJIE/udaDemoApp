package com.ejie.aa79b.common;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * @author Eurohelp S.L.
 */
public class CustomMimeMessagePreparator implements MimeMessagePreparator {
    private String remitente;
    private List<String> destinatarios = new ArrayList<String>();
    private String asunto;
    private String texto;

    @Override()
    public void prepare(MimeMessage mimeMessage) throws Exception {
        Address[] emails = new Address[this.destinatarios.size()];
        int i = 0;
        for (String email : this.destinatarios) {
            emails[i++] = new InternetAddress(email);
        }
        mimeMessage.setRecipients(Message.RecipientType.TO, emails);
        mimeMessage.setFrom(new InternetAddress(this.remitente));
        mimeMessage.setSubject(this.asunto);
        mimeMessage.setText(this.texto);
        mimeMessage.setHeader("Content-Type", "text/html; charset=UTF-8");
//        mimeMessage.setText(this.texto, "ISO-8859-1");
//        mimeMessage.setHeader("Content-Type", "text/html; charset=ISO-8859-1");
    }

    /**
     * @return the remitente
     */
    public String getRemitente() {
        return this.remitente;
    }

    /**
     * @param remitente
     *            the remitente to set
     */
    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    /**
     * @return the destinatarios
     */
    public List<String> getDestinatarios() {
        return this.destinatarios;
    }

    /**
     * @param destinatarios
     *            the destinatarios to set
     */
    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return this.asunto;
    }

    /**
     * @param asunto
     *            the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * @param texto
     *            the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

}