package Mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * Classe gérant l'envoie de mail
 */

public class SendMail
{
	private Session session = null;
    private Transport transport = null;
    final String username="cinemaj2ee@gmail.com";
    final String password = "CinemaJava";
    
    public void sendMail(String from, String to, String subject, String body) throws AddressException, MessagingException
    {
    	/*
    	 * On fixe les propriétés
    	 */
    	Properties props = new Properties();
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.port", "587");
    	
    	/*
    	 * Création de la session pour l'envoie
    	 */
    	
    	Session session = Session.getInstance(props, new Authenticator() {
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(username, password);
    		}
		});
 
    	/*
    	 * Création du message
    	 */
    	try
	    {
	    	Message message = new MimeMessage(session);
	    	message.setFrom(new InternetAddress(from));
	    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    	message.setSubject(subject);
	    	message.setText(body);
	    	Transport.send(message);
		}
	    catch (MessagingException e)
	    {
			throw new RuntimeException(e);
		}
    }
}