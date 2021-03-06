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
package allbinary.data.tables.user.commerce.inventory.order;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class OrderItemsEntityFactory
{
   //private static final String CLASSNAME = "allbinary.data.tables.OrderItemsEntity";
   
   private OrderItemsEntityFactory()
   {
   }
   
   public static OrderItemsEntityInterface getInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (OrderItemsEntityInterface) InterfaceCastProxy.newInstance(object);
         return (OrderItemsEntityInterface) new allbinary.data.tables.user.commerce.inventory.order.OrderItemsEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"OrderItemsEntityFactory","getInstance()",e);
         }
         throw e;
      } 
       */  
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "OrderItemsEntityFactory", "getInstance()", e));
         }
         return null;
      }   
   }
}
