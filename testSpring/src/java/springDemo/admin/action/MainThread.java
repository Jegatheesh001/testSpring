package springDemo.admin.action;

import springDemo.admin.vo.Mails;

class MainThread implements Runnable {
	private Thread t;
	private Mails mails;
	private String threadName;
	MailSender mailService = new MailSender();

	MainThread() {
	}

	public void run() {
		try {
			if (threadName.equals("sendMail")) {
				if (mails != null) {
					mailService.sendmail(mails);
				}
			}
			// Let the thread sleep for a while.
			Thread.sleep(1);

		} catch (InterruptedException e) {
			System.out.println("MailThread interrupted.");
		}

		System.out.println("MailThread exiting.");
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			if (threadName != null) {
				System.out.println("Starting Thread : " + threadName);
				t.start();
			}
		}
	}

	// ----------------------- Getters & Setters------------------//

	/**
	 * @return the mails
	 */
	public Mails getMails() {
		return mails;
	}

	/**
	 * @param mails
	 *            the mails to set
	 */
	public void setMails(Mails mails) {
		this.mails = mails;
	}

	/**
	 * @return the threadName
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName
	 *            the threadName to set
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
