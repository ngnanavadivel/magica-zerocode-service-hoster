package com.codemagic.magica.hoster.mapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.Constants;
import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.AppUtils;
import com.codemagic.magica.hoster.common.util.ExceptionUtil;
import com.codemagic.magica.hoster.mapping.operation.Operation;

@Component
public class ObjectMapper {
   private Properties mappings   = new Properties();
   private Properties operations = new Properties();

   public void map(Object source, Object target, String configFile) throws ServiceHosterException {
      try {
         mappings.load(FileUtils.openInputStream(new File(configFile)));
         operations.load(AppUtils.loadFromClasspath(Constants.OPERATIONS_PROPERTIES_FILE));

         for (Object key : mappings.keySet()) {
            String destProperty = (String) key;
            handleMapping(destProperty, source, target);
         }

      } catch (Exception e) {
         ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
      }
   }

   private void handleMapping(String destProperty, Object src, Object dest) throws Exception {
      if (destProperty != null) {
         System.out.println("Reading property : " + destProperty);
         String propValue = mappings.getProperty(destProperty);
         System.out.println("Value : " + propValue);
         String[] fragments = propValue.split(" ");
         if (fragments != null && fragments.length > 1) {
            String operation = fragments[0];
            System.out.println("Operation : " + operation);
            String[] properties = fragments[1].split(",");
            System.out.println("source Properties : " + Arrays.toString(properties));
            // load operation class and perform the operation on properties.
            String operationClass = (String) operations.get(operation);
            if (operationClass != null) {
               Operation instance = (Operation) Class.forName(operationClass).newInstance();
               // Get Values of properties from Source Object.
               List<String> values = new ArrayList<String>();
               for (String srcProperty : properties) {
                  try {
                     if (srcProperty.startsWith("\"") && srcProperty.endsWith("\"")) {
                        String constValue = srcProperty.substring(1, srcProperty.length() - 1);
                        values.add(constValue);
                        System.out.println(srcProperty + " -> " + constValue);
                     } else {
                        String srcValue = (String) PropertyUtils.getNestedProperty(src, srcProperty);
                        values.add(srcValue);
                        System.out.println(srcProperty + " -> " + srcValue);
                     }
                  } catch (Exception e) {
                     System.err.println(e.getMessage());
                  }

               }
               Object result = instance.operate(values.toArray());
               System.out.println("After operation execution : " + destProperty + " -> " + result);
               PropertyUtils.setNestedProperty(dest, destProperty, result);
            }
         } else {
            String result = null;
            // no operations defined.
            if (propValue.startsWith("\"") && propValue.endsWith("\"")) {
               result = propValue.substring(1, propValue.length() - 1);
               System.out.println(propValue + " -> " + result);
            } else {
               result = (String) PropertyUtils.getNestedProperty(src, propValue);
               System.out.println(destProperty + " -> " + result);
            }

            PropertyUtils.setNestedProperty(dest, destProperty, result);
         }
      }
   }
}
