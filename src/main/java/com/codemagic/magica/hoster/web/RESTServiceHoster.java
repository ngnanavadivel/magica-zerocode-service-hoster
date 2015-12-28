package com.codemagic.magica.hoster.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import com.codemagic.magica.hoster.common.Constants;
import com.codemagic.magica.hoster.common.util.WorkspaceUtils;
import com.codemagic.magica.hoster.mapping.ObjectMapper;
import com.codemagic.magica.hoster.model.UrlMappingConfig;
import com.codemagic.magica.hoster.model.ServiceConfiguration;
import com.codemagic.magica.hoster.service.DataMarshaller;
import com.codemagic.magica.hoster.service.DataUnMarshaller;
import com.codemagic.magica.hoster.service.EndpointReader;
import com.codemagic.magica.hoster.service.EndpointReaderFactory;
import com.codemagic.magica.hoster.service.UrlMappingParser;
import com.codemagic.magica.hoster.service.MarshallerFactory;
import com.codemagic.magica.hoster.service.ServiceConfigManager;
import com.codemagic.magica.hoster.service.UnMarshallerFactory;

@Controller
@RequestMapping("/")
public class RESTServiceHoster {
	@Autowired
	ServiceConfigManager serviceCfgMgr;
	
	@Autowired
	UrlMappingParser inputMpgParser;

	@Autowired
	EndpointReaderFactory readerFactory;

	@Autowired
	UnMarshallerFactory unMarshallerFactory;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	MarshallerFactory marshallerFactory;

	@RequestMapping(value = "{clientId}/{serviceId}/{version}/**",
					method = RequestMethod.GET,
					produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
							MediaType.TEXT_PLAIN_VALUE })
	public @ResponseBody String process(@PathVariable("clientId") String clientId,
										@PathVariable("serviceId") String serviceId,
										@PathVariable("version") String version,
										HttpServletRequest request,
										HttpServletResponse response) {
		String data = null;

		try {

			// 0. Identify Service's Resource discriminator based on path fragment Hierarchy.
			List<String> uriFragments = WorkspaceUtils.getAllURIFragments(request.getRequestURI(), request.getContextPath(), clientId, serviceId, version);
			String resourceDiscriminatorPath = WorkspaceUtils.getResourceDiscriminatorsAsPath(uriFragments);

			// 1. Load Service Configuration
			ServiceConfiguration serviceConfig = serviceCfgMgr.load(clientId, serviceId, version, resourceDiscriminatorPath);

			// 2. ClassLoad the JAR file
			String jarLocation = serviceConfig.getJarLocation();
			JarClassLoader classLoader = new JarClassLoader();
			classLoader.add(jarLocation);
			
			// 2.5. Read Input URI data mappings which serves as the driving criteria for the Reader.
			UrlMappingConfig urlMpgCfg = inputMpgParser.parse(serviceConfig.getUrlMappingLocation());

			// 3. Get Request Data from the source endpoint using appropriate
			// Reader.
			EndpointReader reader = readerFactory.getReader(serviceConfig.getSourceEndpointType());
			String requestData = reader.read(serviceConfig.getSourceEndpointConfigLocation(), uriFragments, urlMpgCfg);

			// 4. Convert request Data into a POJO using appropriate
			// UnMarshaller.
			DataUnMarshaller unMarshaller = unMarshallerFactory.getUnMarshaller(serviceConfig.getSourceDataFormat());
			Object sourcePojo = unMarshaller.convertToPOJO(requestData, serviceConfig.getSourcePackage(), serviceConfig
					.getSourceClassName(), classLoader);

			// 5. Instantiate a blank Target object.
			Object targetPojo = JclObjectFactory.getInstance().create(classLoader, serviceConfig.getTargetPackage()
																					+ "."
																					+ serviceConfig
																							.getTargetClassName());

			// 6. Perform Source to Target property mapping.
			mapper.map(sourcePojo, targetPojo, serviceConfig.getObjectMappingLocation());

			// 7. Convert Target object to Response DataFormat.
			DataMarshaller marshaller = marshallerFactory.getMarshaller(serviceConfig.getTargetDataFormat());
			data = marshaller.serializeToString(targetPojo);

			// 8. Push the response to target endpoint using appropriate Writer.
			// Yet to be implemented.

			// 9. Set ContentType Response Header.
			updateContentType(response, serviceConfig);

		} catch (Throwable t) {
			t.printStackTrace();
		}

		return data;
	}

	

	private void updateContentType(HttpServletResponse resp, ServiceConfiguration serviceConfig) {
		switch (serviceConfig.getTargetDataFormat()) {
			case XML:
				resp.setContentType("application/xml");
				break;
			case JSON:
				resp.setContentType("application/json");
				break;
			default:
				resp.setContentType("text/plain");
		}
	}
}
