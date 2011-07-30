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
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class SmallDelete
{
    private String fileName;
    private String string;

    public SmallDelete(String fileName)
    {
        this.fileName = fileName;
        string = FileUtil.getInstance().readAsString(fileName);
    }

    //Include end
    public boolean deleteAtStart(String start, String end)
    {
        try
        {
            int beginIndex, endIndex;

            beginIndex = this.string.indexOf(start);
            endIndex = this.string.indexOf(end);

            //LogUtil.put(LogFactory.getInstance("StartIndex: " + beginIndex + " EndIndex: " + endIndex, this, "deleteAtStart"));
            
            if (endIndex > beginIndex && beginIndex >= 0 && endIndex >= 0 && beginIndex < 10 && endIndex < 525)
            {
                final String text = this.string.substring(endIndex, this.string.length());

                FileOutputStream idFile = new FileOutputStream(this.fileName);
                DataOutputStream idOutData = new DataOutputStream(idFile);
                idOutData.writeBytes(text);

                return true;
            }

            return false;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "deleteAtStart", e));
            }
            return false;
        }
    }

    public boolean deleteBetween(String start, String end)
    {
        try
        {
            int beginIndex, endIndex;

            beginIndex = this.string.indexOf(start);
            endIndex = this.string.indexOf(end);

            //LogUtil.put(LogFactory.getInstance("StartIndex: " + beginIndex + " EndIndex: " + endIndex, this, "deleteAtStart"));
            
            if (endIndex > beginIndex && beginIndex >= 0 && endIndex >= 0 && beginIndex < 50 && endIndex < 425)
            {
                final String newStart = this.string.substring(0, beginIndex);
                
                final String text = this.string.substring(endIndex + end.length(), this.string.length());

                FileOutputStream idFile = new FileOutputStream(this.fileName);
                DataOutputStream idOutData = new DataOutputStream(idFile);
                idOutData.writeBytes(newStart + text);

                return true;
            }

            return false;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "deleteAtStart", e));
            }
            return false;
        }
    }
}
