package com.codemagic.magica.hoster.dao;

import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.model.ServiceConfiguration;

@Component
public interface ServiceConfigurationDAO {
   ServiceConfiguration load(String clientId, String serviceId, String version, String resourceDiscriminator)
         throws ServiceHosterException;
}
