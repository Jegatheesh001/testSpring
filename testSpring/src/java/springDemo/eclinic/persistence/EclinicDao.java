package springDemo.eclinic.persistence;

import java.util.List;

import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;
import springDemo.eclinic.vo.LabtestInsurarNet;
import springDemo.eclinic.vo.PortData;
import springDemo.eclinic.vo.ProcedureInsurerNetwork;
import springDemo.eclinic.vo.ProcedureSetup;
import springDemo.eclinic.vo.TestSetup;


public interface EclinicDao {

	List<String> getClumnListFromTable(String tableName);

	List<ProcedureSetup> getProcedureListByLimit(Integer limit);

	List<TestSetup> getLabTestListByLimit(Integer limit);

	TestSetup getLabTestByIcd(String icdCode);

	void insertTestsetup(TestSetup ts);
	
	ProcedureSetup getProcedureByIcd(String icdCode);
	
	void insertProcedureSetup(ProcedureSetup ps);

	LabtestInsurarNet getLabTestNetPriceByTestId(Integer testId);
	
	ProcedureInsurerNetwork getProcedureNetPriceByTestId(Integer procedureId);

	void insertLabtestInsurarNet(LabtestInsurarNet labInsNet);

	void insertProcedureInsurerNetwork(ProcedureInsurerNetwork proInsNet);

	Integer getLabTestCountByIcd(String icdCode);

	Integer getProcedureCountByIcd(String icdCode);

	Integer getTestIdByIcd(String icdCode);

	Integer getProcedureIdByIcd(String icdCode);

}
