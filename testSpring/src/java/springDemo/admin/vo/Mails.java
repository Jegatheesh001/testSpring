package springDemo.admin.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class Mails implements Serializable {
    private static final long serialVersionUID = 1L;

    public Mails() {
    }

    private Integer id; 
    private String mailId;
    private String password;

    private String mailto;
    private String cc;
    private String bcc;
    private String subject;
    private String details;

	/**
	 * @return the id13-May-201612:30:27 pm
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id 13-May-2016 12:30:27 pmto set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the mailId13-May-201612:30:27 pm
	 */
	public String getMailId() {
		return mailId;
	}
	/**
	 * @param mailId the mailId 13-May-2016 12:30:27 pmto set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	/**
	 * @return the password13-May-201612:30:27 pm
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password 13-May-2016 12:30:27 pmto set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the mailto13-May-201612:30:27 pm
	 */
	public String getMailto() {
		return mailto;
	}
	/**
	 * @param mailto the mailto 13-May-2016 12:30:27 pmto set
	 */
	public void setMailto(String mailto) {
		this.mailto = mailto;
	}
	/**
	 * @return the cc13-May-201612:30:28 pm
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc the cc 13-May-2016 12:30:28 pmto set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	/**
	 * @return the subject13-May-201612:30:28 pm
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject 13-May-2016 12:30:28 pmto set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the details13-May-201612:30:28 pm
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details 13-May-2016 12:30:28 pmto set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
    
    
 
}
