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
package abcs.logic.system.security.licensing;

import java.io.InputStream;
import java.io.OutputStream;

import abcs.business.init.LicenseInitInfoUtil;
import abcs.logic.basic.io.FileStreamFactory;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.FileFactory;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.resource.ResourceUtil;

public class LicenseServerInitFileUtil
{
    public final void init()
    {
        try
        {

            // File file = this.getFilesDir();
            // String path = file.getAbsolutePath() + FilePathData.SEPARATOR;

            // LogUtil.put(LogFactory.getInstance("Path: " + path, this, AndroidStrings.getInstance().START));

            String filePath = LicenseInitInfoUtil.getInstance().INITFILENAME;
            LicenseInitInfoUtil.getInstance().setFilePath(StringUtil.getInstance().EMPTY_STRING);

            //PreLogUtil.put("SecurityManager = " + System.getSecurityManager(), this, CommonStrings.getInstance().INIT);

            if (FileFactory.getInstance().isFile(filePath))
            {
                LogUtil.put(LogFactory.getInstance("Using Existing License File", this, CommonStrings.getInstance().INIT));
            } else
            {
                write();
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().INIT, e));
        }
    }

    private void write()
    {
        OutputStream fileOutputStream = null;
        try
        {
            ResourceUtil resourceUtil = ResourceUtil.getInstance();

            String filePath = LicenseInitInfoUtil.getInstance().INITFILENAME;

            InputStream inputStream = resourceUtil.getResourceAsStream(filePath);

            LogUtil.put(LogFactory.getInstance("Writing Default License File", this, CommonStrings.getInstance().INIT));

            FileStreamFactory fileStreamFactory = FileStreamFactory.getInstance();

            fileOutputStream = fileStreamFactory.getFileOutputStreamInstance(
                StringUtil.getInstance().EMPTY_STRING, filePath);

            int b;
            int index = 0;
            while ((b = inputStream.read()) != -1)
            {
                fileOutputStream.write(b);
                index++;
            }

            LogUtil.put(LogFactory.getInstance("Wrote Bytes: " + index, this, CommonStrings.getInstance().INIT));

            fileOutputStream.flush();
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().INIT, e));
        } finally
        {
            StreamUtil.getInstance().close(fileOutputStream);
        }
    }
}
