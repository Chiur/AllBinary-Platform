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
package allbinary.data.tables.user.address;

import abcs.business.init.db.UserDbInitInfo;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.user.UserData;
import allbinary.business.user.address.StreetAddress;
import allbinary.business.user.address.StreetAddressData;
import allbinary.data.tables.TableDataFactory;
import allbinary.logic.communication.sql.AbSqlBean;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class StreetAddressesEntity extends AbSqlBean implements StreetAddressesEntityInterface
{   
   private String userName;
   
   public StreetAddressesEntity()
   {
      super(new UserDbInitInfo());
   }
   
   public StreetAddressesEntity(String userName)
   {
      super(new UserDbInitInfo());
      this.userName = userName;
   }
      
   public void remove(Integer index)
   {
      try
      {
         HashMap whereHashMap = new HashMap();
         whereHashMap.put(UserData.USERNAME,userName);
         whereHashMap.put(StreetAddressData.ID,(String) index.toString());         
         super.deleteWhere(whereHashMap);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"remove"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"remove",e));
         }
      }
   }
   
   public void add(StreetAddress address)
   {
      this.add(address, TableDataFactory.getInstance().INTEGER_MAX_VALUE_STRING);
   }
   
   private void add(StreetAddress address,String index)
   {
      try
      {
         Vector values = new Vector();
         
         values.add(index);
         values.add(userName);
         values.add(StringUtil.getInstance().EMPTY_STRING);
         values.add(address.getName());
         values.add(address.getStreet());
         values.add(address.getCity());
         values.add(address.getState());
         values.add(address.getCode());
         values.add(address.getCountry());
         
         Calendar calendar=Calendar.getInstance();         
         String time = new String(new Long(calendar.getTimeInMillis()).toString());         
         values.add(time);
         
         super.insert(values);
         
         this.setDefault(getLastId());
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"add"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"add",e));
         }
      }
   }

   public void update(StreetAddress address)
   {
      try
      {
         HashMap addressHashMap = address.toHashMap();

         //Calendar calendar=Calendar.getInstance();
         //String time = new String(new Long(calendar.getTimeInMillis()).toString());
         
         HashMap whereKeyValuePairs = new HashMap();
         whereKeyValuePairs.put(StreetAddressData.ID,address.getId());
         whereKeyValuePairs.put(UserData.USERNAME,this.userName);         
         
         super.updateWhere(whereKeyValuePairs, addressHashMap);
         
         this.setDefault(address.getId());

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"update"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"add",e));
         }
      }
   }
   
   public String getLastId()
   {
      return super.getLargestIntegerInColumnWhere(StreetAddressData.ID,UserData.USERNAME,userName);
   }
   
   //retrieve one users addresses
   public Vector get()
   {
      try
      {
         Vector streetAddressVector = new Vector();
         HashMap keyAndValue = new HashMap();
         keyAndValue.put(UserData.USERNAME,userName);
         Vector addresses = super.getRows(keyAndValue);

         Iterator iter = addresses.iterator();
                           
         while(iter.hasNext())
         {
            HashMap addressHashMap = (HashMap) iter.next();
            
            StreetAddress streetAddress = new StreetAddress (addressHashMap);
            if(streetAddress!=null)
            {
                streetAddressVector.add(streetAddress);
            }
         }
         
         return streetAddressVector;
         
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"get",e));
         }
         return null;
      }
   }

   public StreetAddress get(Integer index)
   {
      try
      {
         //Vector streetAddressVector = new Vector();
         HashMap keyAndValue = new HashMap();
         keyAndValue.put(UserData.USERNAME,userName);
         keyAndValue.put(StreetAddressData.ID,index.toString());
         HashMap addressHashMap = super.getRow(keyAndValue);

         if(addressHashMap!=null)                     
            return new StreetAddress (addressHashMap);
         else
          return null;         
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"get",e));
         }
         return null;
      }
   }
   
   public StreetAddress getDefault()
   {
      try
      {
         HashMap addressHashMap = new HashMap();
         HashMap updateKeyAndValue = new HashMap();
         updateKeyAndValue.put(StreetAddressData.DEFAULT,StreetAddressData.DEFAULT);
         updateKeyAndValue.put(UserData.USERNAME,userName);
         
         addressHashMap = super.getRow(updateKeyAndValue);
         if(addressHashMap!=null)
         {
            StreetAddress streetAddress = new StreetAddress(addressHashMap);
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("Command Success",this,"getDefault"));
            }
            return streetAddress;
         }
         else
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("No Default Address Found",this,"getDefault"));
            }
            return null;
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"getDefault",e));
         }
         return null;
      }            
   }
      
   public void setDefault(String value)
   {
      try
      {
         HashMap updateKeyAndValue = new HashMap();
         HashMap whereKeyAndValue = new HashMap();

         whereKeyAndValue.put(UserData.USERNAME,userName);
         
         //remove old default value if it exist
         StreetAddress streetAddress = getDefault();
         if(streetAddress!=null)
         {
            updateKeyAndValue.put(StreetAddressData.DEFAULT, StringUtil.getInstance().EMPTY_STRING);
            whereKeyAndValue.put(StreetAddressData.ID,streetAddress.getId());
            super.updateWhere(whereKeyAndValue,updateKeyAndValue);
         }
         
         //now update new default         
         whereKeyAndValue.put(StreetAddressData.ID,value);
         updateKeyAndValue.put(StreetAddressData.DEFAULT,StreetAddressData.DEFAULT);
         
         super.updateWhere(whereKeyAndValue,updateKeyAndValue);         
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"setDefault"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"setShippingAddress",e));
         }
      }
   }
         
   protected String getUserName()
   {
      return this.userName;
   }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(this.getTableName());
        stringBuffer.append(" (");

        stringBuffer.append(StreetAddressData.ID);
        stringBuffer.append(" BIGINT(19) UNSIGNED AUTO_INCREMENT NOT NULL,");
        
        		stringBuffer.append(UserData.USERNAME);
        		stringBuffer.append(" VARCHAR(255) NOT NULL,");

		stringBuffer.append(StreetAddressData.DEFAULT);
		stringBuffer.append(" VARCHAR(255) NOT NULL,");

		stringBuffer.append(StreetAddressData.NAME);
		stringBuffer.append(" VARCHAR(255) NOT NULL,");

		stringBuffer.append(StreetAddressData.STREET);
		stringBuffer.append(" VARCHAR(255) ,");
      
		stringBuffer.append(StreetAddressData.CITY);
		stringBuffer.append(" VARCHAR(255) ,");
      
		stringBuffer.append(StreetAddressData.STATE);
		stringBuffer.append(" VARCHAR(255) ,");
      
		stringBuffer.append(StreetAddressData.CODE);
		stringBuffer.append(" VARCHAR(255) ,");

		stringBuffer.append(StreetAddressData.COUNTRY);
		stringBuffer.append(" VARCHAR(255) ,");

		stringBuffer.append(EntryData.getInstance().TIMECREATED);
		stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(StreetAddressData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    //return super.createTable("CREATE TABLE " +  + tableData);
    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }
   
   public String drop()
   {
      return super.dropTable();
   }
      
   /*
   public String getForm()
   {
      return super.getInputWhere(UserData.USERNAME,userName);
   }
   */
   /*
   public String getTable()
   {
      return super.getTable();
   }*/
   
}
