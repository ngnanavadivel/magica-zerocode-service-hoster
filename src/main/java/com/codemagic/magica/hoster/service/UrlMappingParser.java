package com.codemagic.magica.hoster.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.AppUtils;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;
import com.codemagic.magica.hoster.model.Fragment;
import com.codemagic.magica.hoster.model.Fragment.FragmentType;
import com.codemagic.magica.hoster.model.Fragment.GoesAsType;
import com.codemagic.magica.hoster.model.ServiceConfiguration.EndpointType;
import com.codemagic.magica.hoster.model.UrlMappingConfig;
import com.codemagic.magica.hoster.reader.XmlUtils;

@Component
public class UrlMappingParser {
	public UrlMappingConfig parse(String urlMappingfile) throws ServiceHosterException {
		UrlMappingConfig cfg = new UrlMappingConfig();
		try {
			Document document = XmlUtils.getDocument(AppUtils.createInputSource(urlMappingfile));

			cfg.setDynamicInputURI(Boolean.parseBoolean(XmlUtils.eval(document, "/url-mpg/dynamic-uri-content")));

			cfg.setReaderType(EndpointType.valueOf(XmlUtils.eval(document, "/url-mpg/reader-type")));

			NodeList nodes = XmlUtils.getNodes(document, "/url-mpg/path-fragment-map/fragment");
			int length = nodes.getLength();
			List<Fragment> fragments = new ArrayList<Fragment>();
			for (int i = 0; i < length; ++i) {
				Fragment fragment = new Fragment();
				Element node = (Element) nodes.item(i);
				fragment.setIndex(Integer.parseInt(node.getAttribute("index")));
				fragment.setType(FragmentType.valueOf(node.getAttribute("type")));
				if (fragment.getType() == FragmentType.RESOURCE_ID) {
					fragment.setGoesAs(GoesAsType.valueOf(XmlUtils.eval(node, "reader-cfg/goesAs")));
					fragment.setName(XmlUtils.eval(node, "reader-cfg/name"));
				}
				fragments.add(fragment);
			}
			if (!fragments.isEmpty()) {
				Collections.sort(fragments, new Comparator<Fragment>() {

					public int compare(Fragment o1, Fragment o2) {
						return o1.getIndex() - o2.getIndex();
					}

				});
			}
			cfg.setFragments(fragments);
		} catch (Exception e) {
			ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
		}
		return cfg;
	}
}
