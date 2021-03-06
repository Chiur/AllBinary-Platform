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
package abcs.business.init.db;




public class InventoryDbInitInfo extends DbInitInfo
{
   private static final String INITFILE = "inventorydbinitdata.dat";
               
   public InventoryDbInitInfo()
   {
      super(INITFILE, true);
   }

   public InventoryDbInitInfo(boolean read)
   {
      super(INITFILE, read);
   }
}
