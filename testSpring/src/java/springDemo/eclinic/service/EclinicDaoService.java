package springDemo.eclinic.service;

import springDemo.eclinic.persistence.EclinicDao;
import springDemo.eclinic.persistence.EclinicHibernateDao;
import springDemo.eclinic.vo.LabtestInsurarNet;
import springDemo.eclinic.vo.PortData;
import springDemo.eclinic.vo.ProcedureInsurerNetwork;

public class EclinicDaoService implements EclinicService {
	private EclinicDao dao;

	public EclinicDaoService() {
		this.dao = new EclinicHibernateDao();
	}

	@Override
	public PortData getLabtestNetworkPrice(PortData price) {
		LabtestInsurarNet labInsNet = null;
		labInsNet = dao.getLabTestNetPriceByTestId(price.getTestId());
		if (labInsNet != null) {
			price.setOldCorpDisc(labInsNet.getCorpDisc());
			price.setOldInsurarAmount(labInsNet.getInsurarAmount());
			price.setOldPrediscAmt(labInsNet.getPrediscAmt());
		}
		return price;
	}

	@Override
	public PortData getProcedureNetworkPrice(PortData price) {
		ProcedureInsurerNetwork proInsNet = null;
		proInsNet = dao.getProcedureNetPriceByTestId(price.getProcedureId());
		if (proInsNet != null) {
			price.setOldCorpDisc(proInsNet.getCorpDisc());
			price.setOldInsurarAmount(proInsNet.getInsurarAmount());
			price.setOldPrediscAmt(proInsNet.getPrediscAmt());
		}
		return price;
	}

}
