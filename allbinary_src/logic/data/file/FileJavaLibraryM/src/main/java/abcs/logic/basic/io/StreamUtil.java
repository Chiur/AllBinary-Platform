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

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class StreamUtil
{

    private static final StreamUtil instance = new StreamUtil();

    /**
     * @return the instance
     */
    public static StreamUtil getInstance()
    {
        return instance;
    }

    private StreamUtil()
    {
    }

    /*
     * public static ByteArrayOutputStream get( InputStream in) throws Exception
     * { ByteArrayOutputStream buffer = new ByteArrayOutputStream();
     *
     * int ch;
     *
     * while ((ch = in.read()) != -1) { buffer.write((byte) ch); }
     *
     * in.close();
     *
     * return buffer; }
     */

    public OutputStream get(InputStream inputStream,
        OutputStream outputStream) throws Exception
    {
        return this.get(inputStream, outputStream, new byte[4096]);
    }

    public OutputStream get(InputStream inputStream,
        OutputStream outputStream, byte[] buffer) throws Exception
    {
        //int total = 0;
        int len = 0;
        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
        {
            outputStream.write(buffer, 0, len);
            //total = total + len;
        }

        /*
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FILE))
        {
        LogUtil.put(LogFactory.getInstance("Total Bytes Written: " + total, this, CommonStrings.getInstance().GET));
        }
         */

        return outputStream;
    }

    public byte[] getByteArray(InputStream inputStream)
        throws Exception
    {
    	ByteArrayOutputStream outputStream = null;
    	try
    	{
    		outputStream = (ByteArrayOutputStream) 
            this.get(inputStream, new ByteArrayOutputStream(8000));

        return outputStream.toByteArray();
        } 
        finally
        {
        	this.close(outputStream);
        }        
    }

    private final String CLOSE = "close";
    
    public boolean close(Closeable closeable)
    {
        try
        {
            if (closeable != null)
            {
                LogUtil.put(LogFactory.getInstance("Closing: " + closeable, this, CLOSE));
                closeable.close();
            }
            return true;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CLOSE, e));
            return false;
        }
    }
}
