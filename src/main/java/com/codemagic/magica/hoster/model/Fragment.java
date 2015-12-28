package com.codemagic.magica.hoster.model;

public class Fragment {

   public enum FragmentType {
      RESOURCE_DISCRIMINATOR, RESOURCE_ID
   };

   public enum GoesAsType {
      PATH_FRAGMENT, QUERY_PARAMETER
   };

   private GoesAsType   goesAs;
   private int          index;
   private String       name;
   private FragmentType type;

   public GoesAsType getGoesAs() {
      return goesAs;
   }

   public int getIndex() {
      return index;
   }

   public String getName() {
      return name;
   }

   public FragmentType getType() {
      return type;
   }

   public void setGoesAs(GoesAsType goesAs) {
      this.goesAs = goesAs;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setType(FragmentType type) {
      this.type = type;
   }

}