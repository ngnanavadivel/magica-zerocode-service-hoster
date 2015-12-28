package com.codemagic.magica.hoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.dao.ServiceConfigurationDAO;
import com.codemagic.magica.hoster.model.ServiceConfiguration;

@Component
public class ServiceConfigManager {
	@Autowired
	@Qualifier("local")
	ServiceConfigurationDAO configDAO;
	
	public ServiceConfiguration load(String clientId, String serviceId, String version, String resourceDiscriminator) throws ServiceHosterException {
		return configDAO.load(clientId, serviceId, version, resourceDiscriminator);
	}
}
