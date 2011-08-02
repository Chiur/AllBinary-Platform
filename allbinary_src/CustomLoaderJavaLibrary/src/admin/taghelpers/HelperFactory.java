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
package admin.taghelpers;

import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import abcs.logic.communication.log.LogUtil;

import abcs.logic.system.security.licensing.LicensingException;

import abcs.logic.system.loader.AbeFactory;
import javax.servlet.http.HttpServletRequest;

public class HelperFactory
{
   public HelperFactory()
   {
   }

   public static Object getInstance(
      String factoryName, String className,
      HashMap hashMap, HttpServletRequest httpServletRequest)
      throws LicensingException
   {
      try
      {
         Object params[] = new Object[2];
         Class classes[] = new Class[2];

         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = AbeFactory.getClass("javax.servlet.http.HttpServletRequest");
         //pageContext.getClass();

         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) httpServletRequest;

         Object object = AbeFactory.getInstance(className, classes, params);
         return object;
      }
      catch(LicensingException e)
      {
         String error = "Failed To Get Instance Args: HashMap=" +
            hashMap.toString() + " HttpServletRequest=" + httpServletRequest;

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e));
         }
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e));
         }
         return null;
      }
   }

   public static Object getInstance(
      String factoryName, String className, 
      HashMap hashMap, PageContext pageContext) 
      throws LicensingException
   {
      try
      {
         Object params[] = new Object[2];
         Class classes[] = new Class[2];
                  
         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = AbeFactory.getClass("javax.servlet.jsp.PageContext");
         //pageContext.getClass();         
         
         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) pageContext;
                           
         Object object = AbeFactory.getInstance(className, classes, params);
         return object;
      }
      catch(LicensingException e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
             String error = "Failed To Get Instance Args: HashMap=" +
                 hashMap.toString() + " PageContext=" + pageContext;

            LogUtil.put(LogFactory.getInstance(error, factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e));
         }
         throw e;
      }      
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
             String error = "Failed To Get Instance";
             
             LogUtil.put(LogFactory.getInstance(error,factoryName + "->HelperFactory",
                 "getInstance(String, String, HashMap, PageContext)", e));
         }
         return null;
      }
   }     

   public static Object getInstance(
      String factoryName, String className, 
      HashMap hashMap, HashMap specialhashMap, PageContext pageContext) 
      throws LicensingException
   {
      try
      {
         Object params[] = new Object[3];
         Class classes[] = new Class[3];
                  
         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = specialhashMap.getClass();
         classes[2] = AbeFactory.getClass("javax.servlet.jsp.PageContext");
         //pageContext.getClass();         
         
         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) specialhashMap;
         params[2] = (Object) pageContext;
                           
         Object object = AbeFactory.getInstance(className, classes, params);
         return object;
      }
      catch(LicensingException e)
      {         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
             String error = "Failed To Get Instance Args: HashMap=" + 
                 hashMap.toString() + " PageContext=" + pageContext;

             LogUtil.put(LogFactory.getInstance(error, factoryName + "->HelperFactory",
                 "getInstance(String, String, HashMap, PageContext)",e));
         }
         throw e;
      }      
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)",e));
         }
         return null;
      }
   }     

   public static Object getInstance(
      String factoryName, String className)
      throws LicensingException
   {
      try
      {
         Object object = AbeFactory.getInstance(className);
         return object;
      }
      catch(LicensingException e)
      {
         String error = "Failed To Get Instance";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e));
         }
         throw e;
      }      
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e));
         }
         return null;
      }
   }        
}
