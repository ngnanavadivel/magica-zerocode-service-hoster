package com.codemagic.magica.hoster.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.model.UrlMappingConfig;

@Component
public interface EndpointReader {
	String read(String configFile, List<String> uriFragments, UrlMappingConfig inputMpgCfg) throws ServiceHosterException;
}
