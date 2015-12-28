package com.codemagic.magica.hoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;
import com.codemagic.magica.hoster.model.ServiceConfiguration.DataFormat;

@Component
public class UnMarshallerFactory {
   @Autowired
   JSONUnMarshaller jsonUnMarshaller;

   @Autowired
   XMLUnMarshaller xmlUnMarshaller;

   public DataUnMarshaller getUnMarshaller(DataFormat fmt) throws ServiceHosterException {
      DataUnMarshaller unMarshaller = null;
      switch (fmt) {
         case XML:
            unMarshaller = xmlUnMarshaller;
            break;
         case JSON:
            unMarshaller = jsonUnMarshaller;
            break;
         case FLAT:
            halt(fmt);
      }
      return unMarshaller;
   }

   private void halt(DataFormat fmt) throws ServiceHosterException {
      ExceptionUtil.throwAsServiceHosterException("UnSupported Data Format : " + fmt);
   }
}
