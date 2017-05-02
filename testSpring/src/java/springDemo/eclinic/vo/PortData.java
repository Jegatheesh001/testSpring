package springDemo.eclinic.vo;

public class PortData {

	private Integer id;
	private Integer testId;
	private Integer procedureId;
	private String cptCode;
	private String description;
	private String insurarAmount;
	private String corpDisc;
	private String prediscAmt;
	private String oldInsurarAmount = "0";
	private String oldCorpDisc = "0";
	private String oldPrediscAmt = "0";
	private String overAllStatus;
	private String type;// Test or procedure
	private String status;// New or Old
	private String priceStatus = "N";// Same or Not

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the testId
	 */
	public Integer getTestId() {
		return testId;
	}

	/**
	 * @param testId
	 *            the testId to set
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	/**
	 * @return the procedureId
	 */
	public Integer getProcedureId() {
		return procedureId;
	}

	/**
	 * @param procedureId
	 *            the procedureId to set
	 */
	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}

	/**
	 * @return the cptCode
	 */
	public String getCptCode() {
		return cptCode;
	}

	/**
	 * @param cptCode
	 *            the cptCode to set
	 */
	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the insurarAmount
	 */
	public String getInsurarAmount() {
		return insurarAmount;
	}

	/**
	 * @param insurarAmount
	 *            the insurarAmount to set
	 */
	public void setInsurarAmount(String insurarAmount) {
		this.insurarAmount = insurarAmount;
	}

	/**
	 * @return the corpDisc
	 */
	public String getCorpDisc() {
		return corpDisc;
	}

	/**
	 * @param corpDisc
	 *            the corpDisc to set
	 */
	public void setCorpDisc(String corpDisc) {
		this.corpDisc = corpDisc;
	}

	/**
	 * @return the prediscAmt
	 */
	public String getPrediscAmt() {
		return prediscAmt;
	}

	/**
	 * @param prediscAmt
	 *            the prediscAmt to set
	 */
	public void setPrediscAmt(String prediscAmt) {
		this.prediscAmt = prediscAmt;
	}

	/**
	 * @return the overAllStatus
	 */
	public String getOverAllStatus() {
		return overAllStatus;
	}

	/**
	 * @param overAllStatus
	 *            the overAllStatus to set
	 */
	public void setOverAllStatus(String overAllStatus) {
		this.overAllStatus = overAllStatus;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the oldInsurarAmount
	 */
	public String getOldInsurarAmount() {
		return oldInsurarAmount;
	}

	/**
	 * @param oldInsurarAmount
	 *            the oldInsurarAmount to set
	 */
	public void setOldInsurarAmount(String oldInsurarAmount) {
		this.oldInsurarAmount = oldInsurarAmount;
	}

	/**
	 * @return the oldCorpDisc
	 */
	public String getOldCorpDisc() {
		return oldCorpDisc;
	}

	/**
	 * @param oldCorpDisc
	 *            the oldCorpDisc to set
	 */
	public void setOldCorpDisc(String oldCorpDisc) {
		this.oldCorpDisc = oldCorpDisc;
	}

	/**
	 * @return the oldPrediscAmt
	 */
	public String getOldPrediscAmt() {
		return oldPrediscAmt;
	}

	/**
	 * @param oldPrediscAmt
	 *            the oldPrediscAmt to set
	 */
	public void setOldPrediscAmt(String oldPrediscAmt) {
		this.oldPrediscAmt = oldPrediscAmt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriceStatus() {
		return priceStatus;
	}

	public void setPriceStatus(String priceStatus) {
		this.priceStatus = priceStatus;
	}

}