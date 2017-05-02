package springDemo.eclinic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import springDemo.eclinic.persistence.EclinicDao;
import springDemo.eclinic.persistence.EclinicHibernateDao;
import springDemo.eclinic.service.EclinicDaoService;
import springDemo.eclinic.service.EclinicService;
import springDemo.eclinic.vo.LabtestInsurarNet;
import springDemo.eclinic.vo.PortData;
import springDemo.eclinic.vo.ProcedureInsurerNetwork;
import springDemo.eclinic.vo.ProcedureSetup;
import springDemo.eclinic.vo.TestSetup;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EclinicAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	EclinicService eclinicService = new EclinicDaoService();
	EclinicDao eclinicDao = new EclinicHibernateDao();

	HttpServletRequest request;
	HttpServletResponse response;

	private String imageFileFileName;
	private File imageFile;

	String contextName = "testSpring";
	Integer type;
	Integer officeId;
	Integer[] setupIds;
	List<PortData> priceList;
	Integer sheetNo;
	Integer startLine;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() {
		// List<String>
		// columnList=eclinicDao.getClumnListFromTable("procedure_setup");
		List<ProcedureSetup> psList = eclinicDao.getProcedureListByLimit(10);
		List<TestSetup> tsList = eclinicDao.getLabTestListByLimit(10);
		return SUCCESS;
	}

	public String scanEmiratesId() {
		return SUCCESS;
	}

	public String clientFolderScan() {
		return SUCCESS;
	}

	public String portExcel() {
		officeId = 10;
		if (startLine == null)
			startLine = 1;
		sheetNo = 1;
		return SUCCESS;
	}

	public String portData() {
		Map session = ActionContext.getContext().getSession();
		List<PortData> priceList = (List<PortData>) session.get("priceList");
		PortData price = null;
		if (setupIds != null && priceList != null) {
			for (Integer id : setupIds) {
				price = priceList.get(id);
				System.out.println(price.getType() + "-" + price.getStatus());
				// Normal
				if (type == 1) {
					if (price.getType().equals("L")) {

					} else {

					}
				}
				// Network
				else {
					if (price.getType().equals("L")) {
						if (price.getStatus().equals("N")) {
							price.setTestId(insertTestSetup(price));
						}
						portLabtestNetworkData(price, price.getTestId());
					} else if (type == 2) {
						if (price.getStatus().equals("N")) {
							price.setProcedureId(insertProcedureSetup(price));
						}
						portProcedureNetworkData(price, price.getProcedureId());
					} else {

					}
				}
			}
		}
		return SUCCESS;
	}

	public String uploadExcel() throws IOException {
		Map session = ActionContext.getContext().getSession();
		String filePath = null;
		FileInputStream fis = null;
		if (getImageFileFileName() != null) {
			ServletContext servletContext = ServletActionContext.getServletContext();

			// getting the path of context
			filePath = servletContext.getRealPath("");
			filePath = filePath.replace(contextName, "EclinicExcelUploads");
			File uploadDir = new File(filePath);
			// if the folder does not exits, creating it
			if (uploadDir.exists() == false) {
				uploadDir.mkdirs();
			}

			try {
				Workbook workbook = null;
				filePath = filePath + "/" + imageFileFileName;

				FileUtils.copyFile(imageFile, new File(filePath));
				fis = new FileInputStream(new File(filePath));
				POIFSFileSystem filesystem = null;
				try {
					filesystem = new POIFSFileSystem(fis);
					EncryptionInfo info = new EncryptionInfo(filesystem);
					Decryptor d = Decryptor.getInstance(info);
					try {
						if (!d.verifyPassword(Decryptor.DEFAULT_PASSWORD)) {
							throw new RuntimeException("Unable to process: document is encrypted");
						}
						InputStream dataStream = d.getDataStream(filesystem);
						workbook = WorkbookFactory.create(dataStream);
					} catch (GeneralSecurityException ex) {
						throw new RuntimeException("Unable to process encrypted document", ex);
					}
				} catch (Exception e) {
					workbook = WorkbookFactory.create(new File(filePath));
					priceList = scanDocument(workbook);
					session.put("priceList", priceList);
					portPriceData(priceList);
				}
			} catch (Exception e) {
				addActionError("Unable to Port Data From the Document.");
				return ERROR;
			} finally {
				if (fis != null) {
					fis.close();
					if (hasActionErrors()) {
						return ERROR;
					}
				}
			}
		} else {
			addActionError("Please Select File");
			return ERROR;
		}

		return SUCCESS;
	}

	String portPriceData(List<PortData> PriceListData) {
		String cptCode = null;
		String labCheck = null;
		Integer testId = null;
		Integer procedureId = null;
		StringBuilder overAllStatus;
		String status;
		for (PortData price : PriceListData) {
			System.out.println(price.getId());
			cptCode = price.getCptCode();
			labCheck = cptCode.substring(0, 1);
			overAllStatus = new StringBuilder("");
			status = "O";
			if (labCheck.equals("7") || labCheck.equals("8")) {
				price.setType("L");
				overAllStatus.append("Lab Test");
				testId = eclinicDao.getTestIdByIcd(cptCode);
				price.setTestId(testId);
				if (testId == null) {
					overAllStatus.append("-New CPT");
					status = "N";
				} else {
					overAllStatus.append("-Old CPT");
				}
				// testId = checkTestSetup(price);
				if (type == 1) {
					// portLabtestNetworkData(price, testId);
				} else if (type == 2) {
					if (testId != null) {
						price = eclinicService.getLabtestNetworkPrice(price);
					}
					// portLabtestNetworkData(price, testId);
				}
			} else {
				price.setType("P");
				overAllStatus.append("Procedure");
				procedureId = eclinicDao.getProcedureIdByIcd(cptCode);
				price.setProcedureId(procedureId);
				if (procedureId == null) {
					overAllStatus.append("-New CPT");
					status = "N";
				} else {
					overAllStatus.append("-Old CPT");
				}
				// procedureId = checkProcedureSetup(price);
				if (type == 1) {
					// portProcedureNetworkData(price, testId);
				} else if (type == 2) {
					if (procedureId != null) {
						price = eclinicService.getProcedureNetworkPrice(price);
					}
					// portProcedureNetworkData(price, testId);
				}
			}
			if (compareValues(price)) {
				price.setPriceStatus("Y");
				if (!status.equals("N"))
					overAllStatus.append("-Same Price");
			}
			price.setOverAllStatus(overAllStatus.toString());
			price.setStatus(status);
		}
		this.priceList = PriceListData;
		return SUCCESS;
	}

	private Boolean compareValues(PortData price) {
		Boolean output = false;
		if (price != null) {
			if (price.getInsurarAmount().equals(price.getOldInsurarAmount())) {
				if (price.getPrediscAmt().equals(price.getOldPrediscAmt())) {
					if (price.getCorpDisc().equals(price.getOldCorpDisc())) {
						return true;
					}
				}
			}
		}
		return output;
	}

	private void portLabtestNetworkData(PortData price, Integer testId) {
		LabtestInsurarNet labInsNet = new LabtestInsurarNet();
		labInsNet.setTestId(testId);
		labInsNet.setCopayStat("N");
		labInsNet.setDeductableStat("N");
		labInsNet.setPreappStat("N");
		labInsNet.setPrediscAmt(price.getPrediscAmt());
		labInsNet.setCorpDisc(price.getCorpDisc());
		labInsNet.setInsurarAmount(price.getInsurarAmount());
		eclinicDao.insertLabtestInsurarNet(labInsNet);
	}

	private void portProcedureNetworkData(PortData price, Integer procedureId) {
		ProcedureInsurerNetwork proInsNet = new ProcedureInsurerNetwork();
		proInsNet.setProcedureId(procedureId);
		proInsNet.setCopayStat("N");
		proInsNet.setDeductableStat("N");
		proInsNet.setPreappStat("N");
		proInsNet.setPrediscAmt(price.getPrediscAmt());
		proInsNet.setCorpDisc(price.getCorpDisc());
		proInsNet.setInsurarAmount(price.getInsurarAmount());
		eclinicDao.insertProcedureInsurerNetwork(proInsNet);
	}

	private Integer checkTestSetup(PortData price) {
		TestSetup testSetup = null;
		String icdCode = price.getCptCode();
		testSetup = eclinicDao.getLabTestByIcd(icdCode);
		if (testSetup == null) {
			insertTestSetup(price);
		}
		return testSetup.getTestId();
	}

	private Integer insertTestSetup(PortData price) {
		TestSetup testSetup;
		String icdCode = price.getCptCode();
		String desc = price.getDescription();
		testSetup = new TestSetup();
		testSetup.setTestName(desc);
		testSetup.setIcdCode(icdCode);
		testSetup.setTestCode(icdCode);
		testSetup.setActiveStatus("Y");
		testSetup.setAmount(0 + "");
		testSetup.setAutomation("Y");
		testSetup.setCostPrice(testSetup.getAmount());
		testSetup.setOfficeId(officeId + "");
		testSetup.setProfileType("N");
		testSetup.setTestDoing("I");
		testSetup.setLabType("L");
		testSetup.setTestFormat("1");
		testSetup.setSingleTest("N");

		testSetup.setCategoryId(0);
		testSetup.setType("0");
		testSetup.setSampleId(0);

		eclinicDao.insertTestsetup(testSetup);
		return testSetup.getTestId();
	}

	private Integer checkProcedureSetup(PortData price) {
		ProcedureSetup procedureSetup = null;
		String icdCode = price.getCptCode();
		procedureSetup = eclinicDao.getProcedureByIcd(icdCode);
		if (procedureSetup == null) {
			insertProcedureSetup(price);
		}
		return procedureSetup.getProcedureId();
	}

	private Integer insertProcedureSetup(PortData price) {
		ProcedureSetup procedureSetup;
		String icdCode = price.getCptCode();
		String desc = price.getDescription();
		procedureSetup = new ProcedureSetup();
		procedureSetup.setIcdCode(icdCode);
		procedureSetup.setProcedureName(desc);
		procedureSetup.setProcedurePrice(0 + "");
		procedureSetup.setProcedureType("O");
		procedureSetup.setActiveStatus("Y");
		eclinicDao.insertProcedureSetup(procedureSetup);
		return procedureSetup.getProcedureId();
	}

	List<PortData> scanDocument(Workbook workbook) {
		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		XSSFRow row = null;
		XSSFCell cell = null;
		Integer rowtotalcount = sheet.getLastRowNum();
		List<Object[]> ol = new ArrayList<Object[]>();
		Object[] o = null;
		if (startLine == null)
			startLine = 1;
		for (int i = startLine - 1; i < rowtotalcount; i++) {
			row = sheet.getRow((short) i);
			if (row != null) {
				System.out.println("Row-----" + (i + 1));
				Integer coltotalcount = (int) row.getLastCellNum();
				if (coltotalcount > 0) {
					o = new Object[coltotalcount];
					for (int j = 0; j < coltotalcount; j++) {
						cell = row.getCell(j);
						if (cell != null) {
							o[j] = getCellValue(cell);
						}
					}
					ol.add(o);
				}
			}
		}
		List<PortData> priceList = transferDataToBean(ol);
		return priceList;
	}

	List<PortData> transferDataToBean(List<Object[]> ol) {
		List<PortData> priceList = new ArrayList<PortData>();
		PortData price;
		Integer id = 0;
		for (Object[] obj : ol) {
			price = new PortData();
			price.setId(id);
			price.setCptCode(obj[0].toString());
			price.setDescription(obj[1].toString());
			price.setPrediscAmt(roundNumber(obj[2].toString(), 2));
			price.setCorpDisc(roundNumber(obj[3].toString(), 2));
			price.setInsurarAmount(roundNumber(obj[4].toString(), 2));
			priceList.add(price);
			id++;
		}
		return priceList;
	}

	private String roundNumber(String numberString, Integer digits) {
		Double number = null;
		double value = Math.pow(10, digits);
		try {
			number = Double.parseDouble(numberString);
			number = Math.round(number * value) / value;
		} catch (Exception e) {
			return numberString;
		}
		return number + "";
	}

	String getCellValue(XSSFCell cell) {
		String value = null;
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			value = cell.getRawValue();
			break;
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue().trim();
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			value = cell.getRawValue();
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			System.out.println("Boolean -- " + cell.getCellType());
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			System.out.println("Blank -- " + cell.getCellType());
			value = "";
			break;
		case XSSFCell.CELL_TYPE_ERROR:
			System.out.println("Error -- " + cell.getCellType());
			break;
		default:
			System.out.println("Unknown -- " + cell.getCellType());
			break;
		}
		return value;
	}

	/**
	 * @return the imageFileFileName
	 */
	public String getImageFileFileName() {
		return imageFileFileName;
	}

	/**
	 * @param imageFileFileName
	 *            the imageFileFileName to set
	 */
	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	/**
	 * @return the imageFile
	 */
	public File getImageFile() {
		return imageFile;
	}

	/**
	 * @param imageFile
	 *            the imageFile to set
	 */
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the officeId
	 */
	public Integer getOfficeId() {
		return officeId;
	}

	/**
	 * @param officeId
	 *            the officeId to set
	 */
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	/**
	 * @return the startLine
	 */
	public Integer getStartLine() {
		return startLine;
	}

	/**
	 * @param startLine
	 *            the startLine to set
	 */
	public void setStartLine(Integer startLine) {
		this.startLine = startLine;
	}
	
	public Integer getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(Integer sheetNo) {
		this.sheetNo = sheetNo;
	}

	/**
	 * @return the priceList
	 */
	public List<PortData> getPriceList() {
		return priceList;
	}

	/**
	 * @param priceList
	 *            the priceList to set
	 */
	public void setPriceList(List<PortData> priceList) {
		this.priceList = priceList;
	}

	public Integer[] getSetupIds() {
		return setupIds;
	}

	public void setSetupIds(Integer[] setupIds) {
		this.setupIds = setupIds;
	}

}
