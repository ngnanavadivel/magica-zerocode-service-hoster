package com.codemagic.magica.hoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;
import com.codemagic.magica.hoster.model.ServiceConfiguration.DataFormat;

@Component
public class MarshallerFactory {
   @Autowired
   JSONMarshaller jsonMarshaller;

   @Autowired
   XMLMarshaller xmlMarshaller;

   public DataMarshaller getMarshaller(DataFormat fmt) throws ServiceHosterException {
      DataMarshaller marshaller = null;
      switch (fmt) {
         case XML:
            marshaller = xmlMarshaller;
            break;
         case JSON:
            marshaller = jsonMarshaller;
            break;
         case FLAT:
            halt(fmt);
         default:
            halt(fmt);
      }
      return marshaller;
   }

   private void halt(DataFormat fmt) throws ServiceHosterException {
      ExceptionUtil.throwAsServiceHosterException("UnSupported Data Format : " + fmt);
   }
}
