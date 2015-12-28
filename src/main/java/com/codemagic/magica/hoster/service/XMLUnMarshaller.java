package com.codemagic.magica.hoster.service;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;

@Component
public class XMLUnMarshaller implements DataUnMarshaller {

	public Object convertToPOJO(String data, String packageWhereTypeExists, String className, ClassLoader classLoader)
			throws ServiceHosterException {
		Object pojo = null;
		try {
			JAXBContext context = JAXBContext.newInstance(packageWhereTypeExists, classLoader);

			Unmarshaller unMarshaller = context.createUnmarshaller();
			pojo = unMarshaller.unmarshal(new StreamSource(new StringReader(data)),
					Class.forName(packageWhereTypeExists + "." + className, true, classLoader));
			if (JAXBElement.class.isInstance(pojo)) {
				pojo = ((JAXBElement<?>) pojo).getValue();
			}
		} catch (Exception e) {
			ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
		}
		return pojo;
	}

}
