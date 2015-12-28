package com.codemagic.magica.hoster.model;

import java.util.List;

import com.codemagic.magica.hoster.model.ServiceConfiguration.EndpointType;

public class UrlMappingConfig {
   private boolean        dynamicInputURI;
   private List<Fragment> fragments;
   private EndpointType   readerType;

   public List<Fragment> getFragments() {
      return fragments;
   }

   public EndpointType getReaderType() {
      return readerType;
   }

   public boolean isDynamicInputURI() {
      return dynamicInputURI;
   }

   public void setDynamicInputURI(boolean dynamicInputURI) {
      this.dynamicInputURI = dynamicInputURI;
   }

   public void setFragments(List<Fragment> fragments) {
      this.fragments = fragments;
   }

   public void setReaderType(EndpointType readerType) {
      this.readerType = readerType;
   }

}
