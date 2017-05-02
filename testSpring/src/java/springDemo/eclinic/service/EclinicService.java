package springDemo.eclinic.service;

import springDemo.eclinic.vo.PortData;

public interface  EclinicService {

	PortData getLabtestNetworkPrice(PortData price);

	PortData getProcedureNetworkPrice(PortData price);

}
