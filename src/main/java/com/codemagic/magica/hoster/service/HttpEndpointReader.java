package com.codemagic.magica.hoster.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.AppUtils;
import com.codemagic.magica.hoster.model.Fragment;
import com.codemagic.magica.hoster.model.Fragment.FragmentType;
import com.codemagic.magica.hoster.model.Fragment.GoesAsType;
import com.codemagic.magica.hoster.model.ServiceConfiguration.EndpointType;
import com.codemagic.magica.hoster.model.UrlMappingConfig;
import com.codemagic.magica.hoster.reader.HttpReader;
import com.codemagic.magica.hoster.reader.HttpRequestConfig;
import com.codemagic.magica.hoster.reader.XmlHttpRequestCfgReader;

@Component
public class HttpEndpointReader implements EndpointReader {

   public String read(String configFile, List<String> uriFragments, UrlMappingConfig urlMpgCfg)
         throws ServiceHosterException {
      HttpRequestConfig httpRequestCfg = new XmlHttpRequestCfgReader().parse(AppUtils.createInputSource(configFile));
      if (urlMpgCfg.isDynamicInputURI() && urlMpgCfg.getReaderType() == EndpointType.HTTP) {
         List<Fragment> fragments = urlMpgCfg.getFragments();
         for (Fragment fragment : fragments) {
            if (fragment.getType() == FragmentType.RESOURCE_ID) {
               if (fragment.getGoesAs() == GoesAsType.QUERY_PARAMETER) {
                  int index = fragment.getIndex();
                  httpRequestCfg.getQueryParameters().put(fragment.getName(), uriFragments.get(index));
               }
            }
         }
      }
      return new HttpReader().read(httpRequestCfg);
   }

}
