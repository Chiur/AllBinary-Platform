/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package tags;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import admin.taghelpers.TagHelperFactoryInterface;

public class HelperTag extends PropertiesTag
{
   private TagHelperFactoryInterface tagHelperFactoryInterface;
   private Object object;
   
   public HelperTag(TagHelperFactoryInterface tagHelperFactoryInterface)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
    	  LogUtil.put(LogFactory.getInstance("Constructor", this, "HelperTag(TagHelperFactoryInterface)"));
      }

      this.tagHelperFactoryInterface = tagHelperFactoryInterface;
   }
   
   //This must occur in do start tag after properties are set
   protected void setHelper() throws Exception
   {
      if(this.object==null)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
         {
            LogUtil.put(LogFactory.getInstance("Creating TagHelper with: \n" +
            this.getPropertiesHashMap().toString() ,this,"doStartTag"));
         }
         
         
         this.object = tagHelperFactoryInterface.getInstance(
         this.getPropertiesHashMap(), this.pageContext);
      }
   }
   
   //Used after set helper for calling methods with reflection
   public Object getHelper()
   {
      return this.object;
   }
   
   public int doEndTag()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Ended",this,"doEndTag"));
      }
      this.object = null;
      return super.doEndTag();
   }
}
