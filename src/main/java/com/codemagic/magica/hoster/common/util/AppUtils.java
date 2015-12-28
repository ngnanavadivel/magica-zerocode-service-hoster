package com.codemagic.magica.hoster.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;

import com.codemagic.magica.hoster.common.Constants;
import com.codemagic.magica.hoster.common.exception.ServiceHosterException;

public class AppUtils {

   public static void closeStream(InputStream stream) throws ServiceHosterException {
      if (stream != null) {
         try {
            stream.close();
         } catch (IOException e) {
            ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
         }
      }
   }

   public static InputSource createInputSource(String file) throws ServiceHosterException {
      InputSource source = null;
      try {
         source = new InputSource(new FileInputStream(new File(file)));
      } catch (FileNotFoundException e) {
         ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
      }
      return source;
   }

   public static String getKey(String clientId, String serviceId, String version) {
      return String.join(Constants.SERVICE_CFG_KEY_DELIMITER, clientId, serviceId, version);
   }

   public static InputStream loadFromClasspath(String fileName) {
      return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
   }
}
