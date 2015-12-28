package com.codemagic.magica.hoster.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codemagic.magica.hoster.common.Constants;
import com.codemagic.magica.hoster.common.exception.ServiceHosterException;

public class WorkspaceUtils {
   public static List<String> getAllURIFragments(String requestURI,
                                                 String contextPath,
                                                 String clientId,
                                                 String serviceId,
                                                 String version) {
      List<String> uriFragments = null;
      String csvFragment = String.join(Constants.URL_PATH_SEPARATOR, clientId, serviceId, version);
      System.out.println("csvFragment : " + csvFragment);
      String resourcesPath = requestURI.replaceFirst(contextPath + Constants.URL_PATH_SEPARATOR + csvFragment,
            Constants.EMPTY_STRING);
      resourcesPath = WorkspaceUtils.removeLeadingAndTrailingSlashes(resourcesPath);
      if (resourcesPath != null && resourcesPath.length() > 0) {
         uriFragments = Arrays.asList(resourcesPath.split(Constants.URL_PATH_SEPARATOR));
      }
      System.out.println("getAllURIFragments : " + uriFragments);
      return uriFragments;
   }

   public static String getResourceDiscriminatorsAsPath(List<String> urifragments) {
      String resourceDiscriminator = null;
      if (urifragments != null && !urifragments.isEmpty()) {
         int i = 1;
         List<String> resources = new ArrayList<String>();
         for (String value : urifragments) {
            if (i++ % 2 == 1) {
               resources.add(value);
            }
         }
         resourceDiscriminator = String.join(Constants.URL_PATH_SEPARATOR, resources);
      }
      System.out.println("resourceDiscriminator : " + resourceDiscriminator);
      return resourceDiscriminator;
   }

   public static void mkdirs(String directoryName) throws ServiceHosterException {
      try {
         File toBeCreated = new File(directoryName);
         toBeCreated.mkdirs();
      } catch (Exception e) {
         ExceptionUtil.wrapAndThrowAsServiceHosterException(e);
      }
   }

   public static String removeLeadingAndTrailingSlashes(String resourcesPath) {
      resourcesPath = resourcesPath.startsWith("/")
            ? resourcesPath.substring(1)
            : resourcesPath;
      resourcesPath = resourcesPath.endsWith("/")
            ? resourcesPath.substring(0, resourcesPath.length() - 1)
            : resourcesPath;
      return resourcesPath;
   }
}
