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
package admin.tags;

import javax.servlet.jsp.*;



import admin.taghelpers.SpecialItemsHelperFactory;
import admin.taghelpers.SpecialItemsRequestHelperFactory;


public class SpecialItemsTag extends TableTag
{
   public SpecialItemsTag()
   {
      this.setTagHelperFactory(new SpecialItemsHelperFactory());
      this.setTagRequestHelperFactory(new SpecialItemsRequestHelperFactory());      
   }
}
