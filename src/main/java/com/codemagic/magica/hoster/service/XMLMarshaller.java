package com.codemagic.magica.hoster.service;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;

@Component
public class XMLMarshaller implements DataMarshaller {

	public String serializeToString(Object pojo) throws ServiceHosterException {
		String xml = null;
		try {
			JAXBContext context = JAXBContext.newInstance(pojo.getClass().getPackage().getName(),
					pojo.getClass().getClassLoader());

			Marshaller marshaller = context.createMarshaller();
			StringWriter writer = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(pojo, writer);
			xml = writer.toString();
			System.out.println(xml);
		} catch (Exception e) {
			ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
		}
		return xml;
	}

}
