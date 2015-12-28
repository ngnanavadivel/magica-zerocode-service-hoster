package com.codemagic.magica.hoster.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;

@Component
public class JSONMarshaller implements DataMarshaller {

	public String serializeToString(Object pojo) throws ServiceHosterException {
		String json = null;
		try {
			ObjectMapper mpr = new ObjectMapper();
			json = mpr.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
			System.out.println(json);
		} catch (Exception e) {
			ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
		}
		return json;
	}

}
