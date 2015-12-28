package com.codemagic.magica.hoster.service;

import java.io.StringReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;

@Component
public class JSONUnMarshaller implements DataUnMarshaller {

	public Object convertToPOJO(String data, String packageWhereTypeExists, String className, ClassLoader classLoader)
			throws ServiceHosterException {
		Object pojo = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			pojo = mapper.readValue(new StringReader(packageWhereTypeExists),
					Class.forName(packageWhereTypeExists + "." + className, true, classLoader));
		} catch (Exception e) {
			ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
		}
		return pojo;
	}

}
