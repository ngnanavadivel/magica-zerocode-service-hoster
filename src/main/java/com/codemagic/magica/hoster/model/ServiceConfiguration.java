package com.codemagic.magica.hoster.model;

public class ServiceConfiguration {
   public enum DataFormat {
      FLAT, JSON, XML
   };

   public enum EndpointType {
      FTP, HTTP, JMS
   };

   private String       jarLocation;
   private String       objectMappingLocation;
   private String       sourceClassName;
   private DataFormat   sourceDataFormat;
   private String       sourceEndpointConfigLocation;
   private EndpointType sourceEndpointType;
   private String       sourcePackage;
   private String       targetClassName;
   private DataFormat   targetDataFormat;
   private String       targetEndpointConfigLocation;
   private EndpointType targetEndpointType;
   private String       targetPackage;
   private String       urlMappingLocation;

   public String getJarLocation() {
      return jarLocation;
   }

   public String getObjectMappingLocation() {
      return objectMappingLocation;
   }

   public String getSourceClassName() {
      return sourceClassName;
   }

   public DataFormat getSourceDataFormat() {
      return sourceDataFormat;
   }

   public String getSourceEndpointConfigLocation() {
      return sourceEndpointConfigLocation;
   }

   public EndpointType getSourceEndpointType() {
      return sourceEndpointType;
   }

   public String getSourcePackage() {
      return sourcePackage;
   }

   public String getTargetClassName() {
      return targetClassName;
   }

   public DataFormat getTargetDataFormat() {
      return targetDataFormat;
   }

   public String getTargetEndpointConfigLocation() {
      return targetEndpointConfigLocation;
   }

   public EndpointType getTargetEndpointType() {
      return targetEndpointType;
   }

   public String getTargetPackage() {
      return targetPackage;
   }

   public String getUrlMappingLocation() {
      return urlMappingLocation;
   }

   public void setJarLocation(String jarLocation) {
      this.jarLocation = jarLocation;
   }

   public void setObjectMappingLocation(String objectMappingLocation) {
      this.objectMappingLocation = objectMappingLocation;
   }

   public void setSourceClassName(String sourceClassName) {
      this.sourceClassName = sourceClassName;
   }

   public void setSourceDataFormat(DataFormat sourceDataFormat) {
      this.sourceDataFormat = sourceDataFormat;
   }

   public void setSourceEndpointConfigLocation(String sourceEndpointConfigLocation) {
      this.sourceEndpointConfigLocation = sourceEndpointConfigLocation;
   }

   public void setSourceEndpointType(EndpointType sourceEndpointType) {
      this.sourceEndpointType = sourceEndpointType;
   }

   public void setSourcePackage(String sourcePackage) {
      this.sourcePackage = sourcePackage;
   }

   public void setTargetClassName(String targetClassName) {
      this.targetClassName = targetClassName;
   }

   public void setTargetDataFormat(DataFormat targetDataFormat) {
      this.targetDataFormat = targetDataFormat;
   }

   public void setTargetEndpointConfigLocation(String targetEndpointConfigLocation) {
      this.targetEndpointConfigLocation = targetEndpointConfigLocation;
   }

   public void setTargetEndpointType(EndpointType targetEndpointType) {
      this.targetEndpointType = targetEndpointType;
   }

   public void setTargetPackage(String targetPackage) {
      this.targetPackage = targetPackage;
   }

   public void setUrlMappingLocation(String urlMappingLocation) {
      this.urlMappingLocation = urlMappingLocation;
   }

}
