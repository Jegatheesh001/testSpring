package springDemo.admin.vo;

public class UserBean 
{
	public String getAdmin_Privilage() {
		return admin_Privilage;
	}
	public void setAdmin_Privilage(String adminPrivilage) {
		admin_Privilage = adminPrivilage;
	}
	public UserBean(){
		
	}
	
public UserBean(Integer seatid,Integer user_Id,String seatName,String seatCode,String empName,String empCode){
		this.setSeatId(new Integer(seatid).toString());
		this.setUserId(new Integer(user_Id).toString());
		this.setSeatName(seatName);
		this.setSeatCode(seatCode);
		this.setEmpCode(empCode);
		this.setEmpName(empName);
	}

public UserBean(Integer allotid,Integer seatid,Integer user_Id,String seatName,String seatCode,String empName,String empCode){
	this.setAllotid(new Integer(allotid).toString());
	this.setSeatId(new Integer(seatid).toString());
	this.setUserId(new Integer(user_Id).toString());
	this.setSeatName(seatName);
	this.setSeatCode(seatCode);
	this.setEmpCode(empCode);
	this.setEmpName(empName);
}

public UserBean(Integer parentGroupId,Integer groupId,String groupName,String category){
	this.setParentGroupId(new Integer(parentGroupId).toString());
	this.setGroupId(new Integer(groupId).toString());
	this.setGroupName(groupName);
	this.setCategory(category);
}

public UserBean(Integer parentSeatId,Integer seatId,String seatName,String desigination,String user_Name){
	this.setParentSeatId(new Integer(parentSeatId).toString());
	this.setSeatId(new Integer(seatId).toString());
	this.setSeatName(seatName);
	this.setDesigination(desigination);
	this.setUserName(user_Name);
}

public UserBean(Integer allotid,Integer parentSeatId,Integer seatId,String seatName,String category,String user_Name,Integer userId){
	this.setAllotid(new Integer(allotid).toString());
	this.setParentSeatId(new Integer(parentSeatId).toString());
	this.setSeatId(new Integer(seatId).toString());
	this.setSeatName(seatName);
	this.setCategory(category);
	this.setUserName(user_Name);
	this.setUserid(userId);
}

public UserBean(Integer userid,String username,String designation,Integer seatId)
{ this.setUserid(userid);
	this.setUserName(username);
	this.setDesigination(designation);
	this.setSeatid(seatId);
}

	private String seatId;
	private String userId;
	private String seatName;
	private String seatCode;
	private String empCode;
	private String empName;
	private String userOffice;
	private String userManager;
	private String userSection;
	private String kpSeatId;
	private String category;
	private String parentGroupId;
	private String groupId;
	private String groupName;
	private String parentSeatId;
	private String userName;
	private String admin_Privilage;
	private Integer userid;
	private String institutionCategory;

	private String ptsstatus;
	private Integer officeid;
	private String desigination;
	Integer seatid;
	
	private String allotid;
	private String adminlogin;
	
	private Integer lsgInstitutions;
	private Integer lsgReceivedCount;
	private Integer lsgPendingCount;
	private Integer lsgRejectedCount;
	private Integer lsgCompletedCount;
	private Integer lsgAcceptedCount;
	private Integer lsgReportIssued;
	private Integer lsgReportPending;
	
	
	private Integer miscInstitutions;
	private Integer miscReceivedCount;
	private Integer miscPendingCount;
	private Integer miscellaneousRejectedCount;
	private Integer miscellaneousCompletedCount;
	private Integer miscellaneousAcceptedCount;
	private Integer miscReportIssued;
	private Integer miscReportPending;
	
	
	private Integer totalInstitutionCount;
	private Integer totalReceivedCount;
	private Integer totalPendingCount;
	private Integer rejectedTotalCount;
	private Integer completedTotalCount;
	private Integer acceptedTotalCount;
	private Integer totalReportIssued;
	private Integer totalReportPending;
	
	
	
	
	public String getAdminlogin() {
		return adminlogin;
	}
	public void setAdminlogin(String adminlogin) {
		this.adminlogin = adminlogin;
	}
	
	public Integer getSeatid() {
		return seatid;
	}
	public void setSeatid(Integer seatid) {
		this.seatid = seatid;
	}
	public String getDesigination() {
		return desigination;
	}
	public void setDesigination(String desigination) {
		this.desigination = desigination;
	}
	public Integer getOfficeid() {
		return officeid;
	}
	public void setOfficeid(Integer officeid) {
		this.officeid = officeid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getPtsstatus() {
		return ptsstatus;
	}
	public void setPtsstatus(String ptsstatus) {
		this.ptsstatus = ptsstatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	public String getParentSeatId() {
		return parentSeatId;
	}
	public void setParentSeatId(String parentSeatId) {
		this.parentSeatId = parentSeatId;
	}
	public String getKpSeatId() {
		return kpSeatId;
	}
	public void setKpSeatId(String kpSeatId) {
		this.kpSeatId = kpSeatId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserOffice() {
		return userOffice;
	}
	public void setUserOffice(String userOffice) {
		this.userOffice = userOffice;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getUserManager() {
		return userManager;
	}
	public void setUserManager(String userManager) {
		this.userManager = userManager;
	}
	public String getUserSection() {
		return userSection;
	}
	public void setUserSection(String userSection) {
		this.userSection = userSection;
	}
	public String getAllotid() {
		return allotid;
	}
	public void setAllotid(String allotid) {
		this.allotid = allotid;
	}
	public Integer getTotalInstitutionCount() {
		return totalInstitutionCount;
	}
	public void setTotalInstitutionCount(Integer totalInstitutionCount) {
		this.totalInstitutionCount = totalInstitutionCount;
	}
	public Integer getLsgAcceptedCount() {
		return lsgAcceptedCount;
	}
	public void setLsgAcceptedCount(Integer lsgAcceptedCount) {
		this.lsgAcceptedCount = lsgAcceptedCount;
	}
	public Integer getMiscellaneousAcceptedCount() {
		return miscellaneousAcceptedCount;
	}
	public void setMiscellaneousAcceptedCount(Integer miscellaneousAcceptedCount) {
		this.miscellaneousAcceptedCount = miscellaneousAcceptedCount;
	}
	public Integer getAcceptedTotalCount() {
		return acceptedTotalCount;
	}
	public void setAcceptedTotalCount(Integer acceptedTotalCount) {
		this.acceptedTotalCount = acceptedTotalCount;
	}
	public Integer getLsgCompletedCount() {
		return lsgCompletedCount;
	}
	public void setLsgCompletedCount(Integer lsgCompletedCount) {
		this.lsgCompletedCount = lsgCompletedCount;
	}
	public Integer getMiscellaneousCompletedCount() {
		return miscellaneousCompletedCount;
	}
	public void setMiscellaneousCompletedCount(Integer miscellaneousCompletedCount) {
		this.miscellaneousCompletedCount = miscellaneousCompletedCount;
	}
	public Integer getCompletedTotalCount() {
		return completedTotalCount;
	}
	public void setCompletedTotalCount(Integer completedTotalCount) {
		this.completedTotalCount = completedTotalCount;
	}
	public Integer getLsgRejectedCount() {
		return lsgRejectedCount;
	}
	public void setLsgRejectedCount(Integer lsgRejectedCount) {
		this.lsgRejectedCount = lsgRejectedCount;
	}
	public Integer getMiscellaneousRejectedCount() {
		return miscellaneousRejectedCount;
	}
	public void setMiscellaneousRejectedCount(Integer miscellaneousRejectedCount) {
		this.miscellaneousRejectedCount = miscellaneousRejectedCount;
	}
	public Integer getRejectedTotalCount() {
		return rejectedTotalCount;
	}
	public void setRejectedTotalCount(Integer rejectedTotalCount) {
		this.rejectedTotalCount = rejectedTotalCount;
	}
	public String getInstitutionCategory() {
		return institutionCategory;
	}
	public void setInstitutionCategory(String institutionCategory) {
		this.institutionCategory = institutionCategory;
	}
	public Integer getLsgInstitutions() {
		return lsgInstitutions;
	}
	public void setLsgInstitutions(Integer lsgInstitutions) {
		this.lsgInstitutions = lsgInstitutions;
	}
	public Integer getMiscInstitutions() {
		return miscInstitutions;
	}
	public void setMiscInstitutions(Integer miscInstitutions) {
		this.miscInstitutions = miscInstitutions;
	}
	public Integer getLsgReportIssued() {
		return lsgReportIssued;
	}
	public void setLsgReportIssued(Integer lsgReportIssued) {
		this.lsgReportIssued = lsgReportIssued;
	}
	public Integer getLsgReportPending() {
		return lsgReportPending;
	}
	public void setLsgReportPending(Integer lsgReportPending) {
		this.lsgReportPending = lsgReportPending;
	}
	public Integer getMiscReportIssued() {
		return miscReportIssued;
	}
	public void setMiscReportIssued(Integer miscReportIssued) {
		this.miscReportIssued = miscReportIssued;
	}
	public Integer getMiscReportPending() {
		return miscReportPending;
	}
	public void setMiscReportPending(Integer miscReportPending) {
		this.miscReportPending = miscReportPending;
	}
	public Integer getTotalReportIssued() {
		return totalReportIssued;
	}
	public void setTotalReportIssued(Integer totalReportIssued) {
		this.totalReportIssued = totalReportIssued;
	}
	public Integer getTotalReportPending() {
		return totalReportPending;
	}
	public void setTotalReportPending(Integer totalReportPending) {
		this.totalReportPending = totalReportPending;
	}
	public Integer getLsgPendingCount() {
		return lsgPendingCount;
	}
	public void setLsgPendingCount(Integer lsgPendingCount) {
		this.lsgPendingCount = lsgPendingCount;
	}
	public Integer getMiscPendingCount() {
		return miscPendingCount;
	}
	public void setMiscPendingCount(Integer miscPendingCount) {
		this.miscPendingCount = miscPendingCount;
	}
	public Integer getTotalPendingCount() {
		return totalPendingCount;
	}
	public void setTotalPendingCount(Integer totalPendingCount) {
		this.totalPendingCount = totalPendingCount;
	}
	public Integer getLsgReceivedCount() {
		return lsgReceivedCount;
	}
	public void setLsgReceivedCount(Integer lsgReceivedCount) {
		this.lsgReceivedCount = lsgReceivedCount;
	}
	public Integer getMiscReceivedCount() {
		return miscReceivedCount;
	}
	public void setMiscReceivedCount(Integer miscReceivedCount) {
		this.miscReceivedCount = miscReceivedCount;
	}
	public Integer getTotalReceivedCount() {
		return totalReceivedCount;
	}
	public void setTotalReceivedCount(Integer totalReceivedCount) {
		this.totalReceivedCount = totalReceivedCount;
	}

}
