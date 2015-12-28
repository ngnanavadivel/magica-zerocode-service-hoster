package com.codemagic.magica.hoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;
import com.codemagic.magica.hoster.model.ServiceConfiguration.EndpointType;

@Component
public class EndpointReaderFactory {
   @Autowired
   HttpEndpointReader httpEndpointReader;

   public EndpointReader getReader(EndpointType type) throws ServiceHosterException {
      EndpointReader reader = null;
      switch (type) {
         case HTTP:
            reader = httpEndpointReader;
            break;
         case FTP:
            halt(type);
         case JMS:
            halt(type);
         default:
            halt(type);
      }
      return reader;
   }

   private void halt(EndpointType sourceEndpointType) throws ServiceHosterException {
      ExceptionUtil.throwAsServiceHosterException("UnSupported Endpoint Type : " + sourceEndpointType);
   }
}
