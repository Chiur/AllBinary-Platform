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
package allbinary.logic.communication.smtp.email;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.UserInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import allbinary.logic.communication.smtp.event.handler.factory.UserEmailEventHandlerSingletons;
import allbinary.logic.communication.smtp.info.AdminEmailInfo;
import allbinary.logic.communication.smtp.info.BasicEmailInfo;
import allbinary.logic.communication.smtp.info.EmailInfo;

public class NewPasswordEmail
{
   private UserInterface userInterface;
   private String newPassword;
   
   public NewPasswordEmail(UserInterface userInterface, String newPassword)
   {
      this.userInterface = userInterface;
      this.newPassword = newPassword;
   }

   public void process() throws Exception
   {
      this.notifyStoreAdmin();
      this.notifyUser();
   }
   
   private void notifyStoreAdmin() throws Exception
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("notifyStoreAdmin", this, "notifyStoreAdmin"));
         }

         String emailSubject = 
            "New Password For User: " + this.userInterface.getUserName();
         String emailBody = "New Password: " + this.newPassword;

         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new AdminEmailInfo(emailSubject, emailBody);

         /*
         BasicEmailInfo storeAdminBasicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(this.storeFrontInterface, 
            emailSubject, emailTextBody);
         */
         
         //EmailInfo storeAdminEmailInfo = new EmailInfo(storeAdminBasicEmailInfo);

         EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

         //Send response to Admin(s)
         UserEmailEventHandler adminUserEmailEventHandler =
            AdminUserEmailEventHandlerSingletons.getInstance(
               UserEmailEventNameData.NEWPASSWORD);

         /*
         UserEmailEventHandler storeAdminUserEmailEventHandler =
            StoreAdminUserEmailEventHandlerSingletons.getInstance(
               UserEmailEventNameData.NEWPASSWORD,
               this.storeFrontInterface);

          */

         //storeAdminUserEmailEventHandler.receiveEmailInfo(emailInfo);
         adminUserEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.NEWPASSWORD, emailInfo);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "emailAdmin", e));
         }
         //throw e;
      }
   }

   ///String subject, String textBody
   private void notifyUser() throws Exception
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Email User", this, "notifyUser()"));
         }

         String subject = "New Password";
         String body = "New Password: " + newPassword;

         /*
         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(this.storeFrontInterface, subject, body);
         */

         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new AdminEmailInfo(subject, body);
         
         EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

         UserEmailEventHandler userEmailEventHandler =
            UserEmailEventHandlerSingletons.getInstance(
               UserEmailEventNameData.NEWPASSWORD, this.userInterface);

         userEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.NEWPASSWORD, emailInfo);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "notifyUser", e));
         }
         throw e;
      }
   }   
}
