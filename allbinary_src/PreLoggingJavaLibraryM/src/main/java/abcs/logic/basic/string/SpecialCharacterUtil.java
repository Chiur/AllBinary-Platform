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
package abcs.logic.basic.string;

import abcs.logic.basic.path.AbPathData;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class SpecialCharacterUtil {

   public static HashMap getHashMap()
   {
      HashMap hashMap = new HashMap();
      hashMap.put("!","");
      hashMap.put("@","");
      hashMap.put("#","");
      hashMap.put("$","");
      hashMap.put("%","");
      hashMap.put("^","");
      hashMap.put("&","");
      hashMap.put("*","");
      hashMap.put("(","");
      hashMap.put(")","");
      hashMap.put("-","");
      hashMap.put("_","");
      hashMap.put("+","");
      hashMap.put("=","");
      hashMap.put("\\","");
      hashMap.put("|","");
      hashMap.put(AbPathData.getInstance().EXTENSION_SEP,"");
      hashMap.put(",","");
      hashMap.put("<","");
      hashMap.put(">","");
      hashMap.put("?","");
      hashMap.put(AbPathData.getInstance().SEPARATOR,"");
      hashMap.put("~","");
      hashMap.put("`","");
      return hashMap;
   }
   
}
