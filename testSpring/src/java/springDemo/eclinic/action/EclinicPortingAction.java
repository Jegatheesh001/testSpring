package springDemo.eclinic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import springDemo.actionDispatcherFilter.HibernateUtil;
import springDemo.eclinic.persistence.EclinicDao;
import springDemo.eclinic.persistence.EclinicHibernateDao;
import springDemo.eclinic.service.EclinicDaoService;
import springDemo.eclinic.service.EclinicService;
import springDemo.eclinic.vo.PortData;

public class EclinicPortingAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	EclinicService eclinicService = new EclinicDaoService();
	EclinicDao eclinicDao = new EclinicHibernateDao();

	private String imageFileFileName;
	private File imageFile;

	String contextName = "testSpring";
	Integer startLine;
	Integer sheetNo;

	HttpServletRequest request;
	HttpServletResponse response;

	String masterDb = "eclinic_kmcmaster";
	String branchDb = "eclinic_kmc";

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		branchDb = "temp_schema";
		createSchema(branchDb);
		createTable(branchDb, "temp_proc_insurar", " ( `id` INT NOT NULL, PRIMARY KEY (`id`));");
		dropTable(branchDb, "temp_proc_insurar");
		dropSchema(branchDb);
		return SUCCESS;
	}

	public String portMasterToBranch() {
		execute();
		return SUCCESS;
	}

	public void findCount() throws IOException {
		String query = request.getParameter("query");
		Session session = HibernateUtil.getSession();
		String count = "0";
		try {
			count = (String) session.createSQLQuery("select count(*) from " + query).uniqueResult().toString();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		this.response.getWriter().write(count + "");
		this.response.setContentType("application/json;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	public void executeQuery() throws IOException {
		String query = request.getParameter("query");
		Session session = HibernateUtil.getSession();
		List<Object> ol = null;
		try {
			ol = (List<Object>) session.createSQLQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		String gg = "<br/>";
		gg += new Gson().toJson(ol);
		gg = gg.replace("],[", "<br/>");
		this.response.getWriter().write(gg);
		this.response.setContentType("application/json;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	public void createTable(String db, String table, String values) {
		Session session = HibernateUtil.getSession();
		try {
			session.createSQLQuery("create table " + db + "." + table + " " + values).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void dropTable(String db, String table) {
		Session session = HibernateUtil.getSession();
		try {
			session.createSQLQuery("drop table " + db + "." + table).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void createSchema(String db) {
		Session session = HibernateUtil.getSession();
		try {
			session.createSQLQuery("create schema " + db).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void dropSchema(String db) {
		Session session = HibernateUtil.getSession();
		try {
			session.createSQLQuery("drop schema " + db).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public String portLotCode() throws IOException {
		String filePath = null;
		String db = "eclinic_oasis";
		Integer controlNameLine = 1;
		Integer lotCodeLine = 2;
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
				} catch (Exception e1) {
					workbook = WorkbookFactory.create(new File(filePath));
					XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(sheetNo - 1);
					XSSFRow row = null;
					XSSFCell cell = null;
					Integer rowtotalcount = sheet.getLastRowNum();
					String control_Name = "";
					String lot_code = "";
					java.math.BigInteger count;
					Session session = HibernateUtil.getSession();
					Transaction tx = session.beginTransaction();
					for (int i = startLine - 1; i < rowtotalcount; i++) {
						count = null;
						row = sheet.getRow((short) i);
						if (row != null) {
							cell = row.getCell(controlNameLine);
							if (cell == null)
								continue;
							control_Name = getCellValue(cell);
							cell = row.getCell(lotCodeLine);
							if (cell == null)
								continue;
							lot_code = getCellValue(cell);
							try {
								count = (java.math.BigInteger) session
										.createSQLQuery(
												"select count(*) from " + db + ".lot_code_master where control_Name='"
														+ control_Name + "' and lot_code='" + lot_code + "'")
										.uniqueResult();
								if (count.signum() == 0)
									session.createSQLQuery("INSERT INTO " + db
											+ ".lot_code_master (control_Name, lot_code, active_status) VALUES ('"
											+ control_Name + "', '" + lot_code + "', 'Y')").executeUpdate();
							} catch (HibernateException e) {
								e.printStackTrace();
								break;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					tx.commit();
					session.close();
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

	// -------------- Getters and Setters -----------------//

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public Integer getStartLine() {
		return startLine;
	}

	public void setStartLine(Integer startLine) {
		this.startLine = startLine;
	}

	public Integer getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(Integer sheetNo) {
		this.sheetNo = sheetNo;
	}

}
