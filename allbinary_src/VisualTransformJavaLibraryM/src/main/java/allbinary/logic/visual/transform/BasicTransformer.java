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
package allbinary.logic.visual.transform;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.transform.URIResolver;

import abcs.logic.basic.io.AbFileInputStream;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.path.AbPathUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.AbKeys;
import allbinary.data.tree.dom.BasicUriResolver;
import allbinary.logic.control.crypt.jcehelper.AbCrypt;
import allbinary.logic.control.crypt.jcehelper.KeySpecFactory;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class BasicTransformer extends AbTransformer
{
    //private InputStream inputStream;

    public BasicTransformer(TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
        this.setTemplateAsInputStream();
    }

    //private InputStream getEncryptedTemplateFileAsInputStream(File file)
    private void setEncryptedTemplateFileAsInputStream(AbFile file) throws Exception
    {
    	ByteArrayOutputStream outputStream = null;
    	AbFileInputStream inputStream = null;
        try
        {
        	outputStream = new ByteArrayOutputStream();
        	inputStream = new AbFileInputStream(file);

        	outputStream = (ByteArrayOutputStream)
        	    StreamUtil.getInstance().get(inputStream, outputStream);              
            
            AbCrypt abCrypt = new AbCrypt(KeySpecFactory.DESEDE, AbKeys.getKey(file.getAbsolutePath()));
            byte[] decrypted = abCrypt.decrypt(outputStream.toByteArray());

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
            {
                LogUtil.put(LogFactory.getInstance(
                    "Decrypted Template: \n" + new String(decrypted), 
                    this, "setEncryptedTemplateFileAsInputStream(file)"));
            }

            this.setInputStream((InputStream) new ByteArrayInputStream(decrypted));
            this.setURIResolver((URIResolver) new BasicUriResolver(
            		TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION));
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Failed to get Encrypted File: ");
                stringBuffer.append(file.getPath());
                stringBuffer.append(" \nName:");
                stringBuffer.append(file.getName());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "setEncryptedTemplateFileAsInputStream()", e));
            }
            throw e;
        }
        finally
        {
        	StreamUtil.getInstance().close(outputStream);
        	StreamUtil.getInstance().close(inputStream);
        }
    }

    private void setEncryptedTemplateAsInputStream() throws Exception
    {
    	ByteArrayOutputStream outputStream = null;
    	ByteArrayInputStream inputStream = null;
        try
        {
        	outputStream = new ByteArrayOutputStream();
        	inputStream = new ByteArrayInputStream(
                this.getTransformInfoInterface().getTemplate().getBytes());
            
            outputStream = (ByteArrayOutputStream) 
                StreamUtil.getInstance().get(inputStream, outputStream); 
            
            AbCrypt abCrypt = new AbCrypt(KeySpecFactory.DESEDE, AbKeys.getKey(this.getTransformInfoInterface().getName()));

            byte[] decrypted = abCrypt.decrypt(outputStream.toByteArray());

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
            {
                LogUtil.put(LogFactory.getInstance(
                    "Decrypted Template: \n" + decrypted.toString(), this, "setEncryptedTemplateAsInputStream()"));
            }

            this.setInputStream((InputStream) new ByteArrayInputStream(decrypted));
            this.setURIResolver((URIResolver) 
            		new BasicUriResolver(TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION));
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                LogUtil.put(LogFactory.getInstance(
                    "Failed to set with template: "
                    + this.getTransformInfoInterface().getTemplateFilePath(),
                    this, "setEncryptedTemplateFileAsInputStream()", e));
            }
            throw e;
        }
        finally
        {
        	StreamUtil.getInstance().close(outputStream);
        	StreamUtil.getInstance().close(inputStream);
        }
    }

    //If Templatefilename is provided then load the file and return it as a input stream
    //otherwise return the Template as an inputstream
    private void setTemplateAsInputStream() throws Exception
    {
        AbFileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        try
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
            {
                LogUtil.put(LogFactory.getInstance(
                    this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()"));
            }

            TransformInfoTemplateData transformInfoTemplateData = 
            	TransformInfoTemplateData.getInstance();
            
            AbPathUtil abPathUtil = AbPathUtil.getInstance();
            
            if (this.getTransformInfoInterface().getTemplateFilePath() != null)
            {
                String extension = abPathUtil.getExtension(
                    this.getTransformInfoInterface().getTemplateFilePath());
                String filePath = abPathUtil.getWithoutExtension(
                    this.getTransformInfoInterface().getTemplateFilePath());

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
                {
                    LogUtil.put(LogFactory.getInstance(
                        this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()"));
                }

                if (extension.compareTo(transformInfoTemplateData.UNCRYPTED_EXTENSION) == 0)
                {
                    //attempt to load an encrypted version

                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(filePath);
                    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
                    stringBuffer.append(transformInfoTemplateData.ENCRYPTED_EXTENSION);

                    AbFile encFile = new AbFile(stringBuffer.toString());

                    if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
                    {
                        LogUtil.put(LogFactory.getInstance(
                            this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()"));
                    }

                    if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
                    {
                        stringBuffer = new StringBuffer();
                        //stringBuffer.delete(0, stringBuffer.length());

                        stringBuffer.append("Encrypted Template File isFile=");
                        stringBuffer.append(encFile.isFile());
                        stringBuffer.append("\nEncTemplateFilePath: ");
                        stringBuffer.append(encFile.getPath());

                        LogUtil.put(LogFactory.getInstance(
                            stringBuffer.toString(), this, "setTemplateAsInputStream()"));
                    }

                    if (encFile.isFile())
                    {
                        this.setEncryptedTemplateFileAsInputStream(encFile);
                        return;
                    } else
                    {
                        AbFile file =
                            new AbFile(this.getTransformInfoInterface().getTemplateFilePath());

                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
                        {
                            LogUtil.put(LogFactory.getInstance(
                                this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()"));
                        }

                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
                        {
                            LogUtil.put(LogFactory.getInstance(
                                "Template File isFile=" + file.isFile(), this, "setTemplateAsInputStream()"));
                        }

                        if (file.isFile())
                        {
                            inputStream = new AbFileInputStream(file);
                            outputStream = new ByteArrayOutputStream();

                            outputStream = (ByteArrayOutputStream) 
                                StreamUtil.getInstance().get(inputStream, outputStream); 

                            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
                            {
                                LogUtil.put(LogFactory.getInstance(
                                    "Template: " + outputStream.toString(), this, "getTemplateAsInputStream()"));
                            }

                            this.setInputStream((InputStream) new ByteArrayInputStream(outputStream.toByteArray()));

                            this.setURIResolver((URIResolver) new BasicUriResolver(
                                transformInfoTemplateData.UNCRYPTED_EXTENSION));
                            return;
                        }
                    }
                } else if (extension.compareTo(transformInfoTemplateData.ENCRYPTED_EXTENSION) == 0)
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(filePath);
                    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
                    stringBuffer.append(transformInfoTemplateData.ENCRYPTED_EXTENSION);

                    AbFile file = new AbFile(stringBuffer.toString());

                    if (file.isFile())
                    {
                        this.setEncryptedTemplateFileAsInputStream(file);
                    }
                } else
                {
                    throw new Exception(
                        "View Template File Type Is Not Recognized: "
                        + this.getTransformInfoInterface().getTemplateFilePath());
                }
            } else
            {
                this.setEncryptedTemplateAsInputStream();
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                LogUtil.put(LogFactory.getInstance(
                    "Failed to get Template Data", this, "setTemplateAsInputStream()", e));
            }
            throw e;
        }
        finally
        {
        	StreamUtil.getInstance().close(outputStream);
        	StreamUtil.getInstance().close(inputStream);
        }        
        throw new Exception("Error setTemplateAsInputStream()");
    }
}
