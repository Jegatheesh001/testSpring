package springDemo.admin.action;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import springDemo.admin.vo.Mails;

public class MailSender {

	private String sentemail = "emailnotsent";
	public Mails emailSetup;
	public static final String SSL = "SSL";
	public static final String Non_SSL = "Non-SSL";

	public String sendmail(Mails mails) {
		try {

			Properties props = new Properties();

			// ------------- Default Sender Details------------//
			String email;
			String emailpass;

			email = "testmail2527@gmail.com";
			emailpass = "9659542527";
			// email = "info@theimcentre.com";
			// emailpass = "dohaqatar";

			// -------------------------//

			if (mails.getMailId() == null || mails.getPassword() == null) {
				mails.setMailId(email);
				mails.setPassword(emailpass);
			}
			emailSetup = mails;
			String host = mails.getMailId().split("@")[1].split("\\.")[0];

			props = setProperties(props, host, SSL);
			props.put("mail." + protocol + ".user", mails.getMailId());
			props.put("mail." + protocol + ".password", mails.getPassword());

			Session session = null;
			Authenticator auth = new SMTPAuthenticator();
			session = Session.getDefaultInstance(props, auth);

			session.setDebug(true);

			MimeMessage msg = null;
			msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(mails.getMailId()));
			msg.setSubject(mails.getSubject(), "UTF-8");

			String[] mailTo;
			mailTo = mails.getMailto().split(",");
			InternetAddress[] recipientAddress;
			recipientAddress = new InternetAddress[mailTo.length];
			int counter = 0;
			for (String recipient : mailTo) {
				recipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			msg.setRecipients(Message.RecipientType.TO, recipientAddress);

			String[] mailCc;
			if (mails.getCc() != null) {
				mailCc = mails.getCc().split(",");
				recipientAddress = new InternetAddress[mailCc.length];
				counter = 0;
				for (String recipient : mailCc) {
					recipientAddress[counter] = new InternetAddress(recipient.trim());
					counter++;
				}
				msg.setRecipients(Message.RecipientType.CC, recipientAddress);
			}
			
			String[] mailBcc;
			if (mails.getBcc() != null) {
				mailBcc = mails.getBcc().split(",");
				recipientAddress = new InternetAddress[mailBcc.length];
				counter = 0;
				for (String recipient : mailBcc) {
					recipientAddress[counter] = new InternetAddress(recipient.trim());
					counter++;
				}
				msg.setRecipients(Message.RecipientType.BCC, recipientAddress);
			}

			BodyPart bodyPart = new MimeBodyPart();
			if (bodyPart != null) {

				MimeMultipart mpRoot = new MimeMultipart("mixed");
				MimeMultipart mpContent = new MimeMultipart("alternative");
				MimeBodyPart contentPartRoot = new MimeBodyPart();
				contentPartRoot.setContent(mpContent);
				mpRoot.addBodyPart(contentPartRoot);

				MimeBodyPart mbp2 = new MimeBodyPart();
				mbp2.setContent(mails.getDetails(), "text/html");
				mpContent.addBodyPart(mbp2);

				// bodypart.setFileName(filename);
				// mpRoot.addBodyPart(bodypart);
				msg.setContent(mpRoot);
				msg.saveChanges();

			}

			Transport.send(msg);
			sentemail = "emailsent";
		} catch (Exception exp) {
			System.out.println(" This is from Exception");
			exp.printStackTrace();
		}

		return sentemail;
	}

	/**
	 * @param props
	 * @param mailProvider
	 * 			@return13-May-20161:02:11 pm
	 */
	String protocol = "smtp";

	private Properties setProperties(Properties props, String mailProvider, String connectionType) {

		props.put("mail.transport.protocol", protocol);
		props.put("mail." + protocol + ".socketFactory.fallback", "false");
		if (connectionType.equals(SSL)) {
			// https
			props.put("mail." + protocol + ".starttls.enable", "true");
			// ssl
			props.put("mail." + protocol + ".auth", "true");
			props.put("mail." + protocol + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail." + protocol + ".port", "465");

			if (mailProvider.equals("gmail")) {
				props.put("mail." + protocol + ".host", "smtp.gmail.com");
			} else if (mailProvider.equals("yahoo")) {
				props.put("mail." + protocol + ".host", "smtp.mail.yahoo.com");
			} else if (mailProvider.equals("theimcentre")) {
				props.put("mail." + protocol + ".host", "vps.hkwebsolutions.co.uk");
			} else {
				// For other yahoo business mails like 'xyz@kran.co.in'
				props.put("mail." + protocol + ".host", "smtp.bizmail.yahoo.com");
			}
		} else {
			// https
			props.put("mail." + protocol + ".starttls.enable", "true");
			// non-ssl
			props.put("mail." + protocol + ".auth", "true");
			props.put("mail." + protocol + ".port", "25");
			if (mailProvider.equals("theimcentre")) {
				props.put("mail." + protocol + ".ssl.trust","mail.theimcentre.com");
				props.put("mail." + protocol + ".host", "mail.theimcentre.com");
			}
		}
		return props;
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(emailSetup.getMailId(), emailSetup.getPassword());
		}
	}

}
