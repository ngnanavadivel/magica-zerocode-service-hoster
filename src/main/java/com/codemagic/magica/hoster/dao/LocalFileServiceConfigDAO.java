package com.codemagic.magica.hoster.dao;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.codemagic.magica.hoster.common.Constants;
import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.AppUtils;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;
import com.codemagic.magica.hoster.core.WorkspaceManager;
import com.codemagic.magica.hoster.model.ServiceConfiguration;
import com.codemagic.magica.hoster.model.ServiceConfiguration.DataFormat;
import com.codemagic.magica.hoster.model.ServiceConfiguration.EndpointType;
import com.codemagic.magica.hoster.reader.XmlUtils;

@Component("local")
public class LocalFileServiceConfigDAO implements ServiceConfigurationDAO {

	@Autowired
	WorkspaceManager wkspaceMgr;

	public ServiceConfiguration load(String clientId, String serviceId, String version, String resourceDiscriminator) throws ServiceHosterException {
		ServiceConfiguration svcConfig = null;
		FileInputStream stream = null;
		try {
			wkspaceMgr.init(clientId, serviceId, version, resourceDiscriminator);
			File cfg = new File(wkspaceMgr.getServiceConfigFile());
			if (cfg.exists()) {
				svcConfig = new ServiceConfiguration();
				stream = new FileInputStream(cfg);
				Document document = XmlUtils.getDocument(new InputSource(stream));
				if (document != null) {
					svcConfig.setJarLocation(XmlUtils.eval(document, "/service-cfg/runtime-cfg/jar-location"));
					
					svcConfig.setObjectMappingLocation(XmlUtils.eval(document, "/service-cfg/runtime-cfg/object-mapping-location"));
					svcConfig.setUrlMappingLocation(XmlUtils.eval(document, "/service-cfg/runtime-cfg/url-mapping-location"));

					svcConfig.setSourcePackage(XmlUtils.eval(document, "/service-cfg/runtime-cfg/source/package"));
					svcConfig.setTargetPackage(XmlUtils.eval(document, "/service-cfg/runtime-cfg/target/package"));

					svcConfig.setSourceClassName(XmlUtils.eval(document, "/service-cfg/runtime-cfg/source/class"));
					svcConfig.setTargetClassName(XmlUtils.eval(document, "/service-cfg/runtime-cfg/target/class"));

					svcConfig.setSourceEndpointType(EndpointType
							.valueOf(XmlUtils.eval(document, "/service-cfg/runtime-cfg/source/endpoint/type")));
					svcConfig.setTargetEndpointType(EndpointType
							.valueOf(XmlUtils.eval(document, "/service-cfg/runtime-cfg/target/endpoint/type")));

					svcConfig.setSourceDataFormat(DataFormat
							.valueOf(XmlUtils.eval(document, "/service-cfg/runtime-cfg/source/endpoint/format")));
					svcConfig.setTargetDataFormat(DataFormat
							.valueOf(XmlUtils.eval(document, "/service-cfg/runtime-cfg/target/endpoint/format")));

					svcConfig.setSourceEndpointConfigLocation(
							XmlUtils.eval(document, "/service-cfg/runtime-cfg/source/endpoint/cfg-location"));
					svcConfig.setTargetEndpointConfigLocation(
							XmlUtils.eval(document, "/service-cfg/runtime-cfg/target/endpoint/cfg-location"));
				}

			} else {
				ExceptionUtil.throwAsServiceHosterException(Constants.SERVICE_CFG_XML_FILE + " doesn't exist.");
			}
		} catch (Throwable t) {
			ExceptionUtil.wrapAndThrowAsServiceHosterException(t);
		} finally {
			AppUtils.closeStream(stream);
		}
		return svcConfig;
	}

}
