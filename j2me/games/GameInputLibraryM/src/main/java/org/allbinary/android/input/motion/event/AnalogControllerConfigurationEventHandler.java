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
package org.allbinary.android.input.motion.event;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class AnalogControllerConfigurationEventHandler extends BasicEventHandler
{
    private static final AnalogControllerConfigurationEventHandler instance = new AnalogControllerConfigurationEventHandler();

    public static AnalogControllerConfigurationEventHandler getInstance()
    {
        return instance;
    }
    
    private final BasicArrayList list = new BasicArrayList();
    
    private AnalogControllerConfigurationEventHandler()
    {
    }

    public void addListener(AnalogControllerConfigurationEventListenerInterface analogControllerConfigurationEventListenerInterface)
    {
        if(!list.contains(analogControllerConfigurationEventListenerInterface))
        {
            list.add(analogControllerConfigurationEventListenerInterface);
        }
    }

    public void removeAllListeners()
    {
        this.list.clear();
        super.removeAllListeners();
    }

    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

    public void fireEvent(AllBinaryEventObject eventObject) throws Exception
    {        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
            	//Add deviceId
            	AnalogControllerConfigurationEventListenerInterface playerGameInput = 
            			(AnalogControllerConfigurationEventListenerInterface) this.list.objectArray[index];
                playerGameInput.onAnalogControllerConfigurationEvent(
                		(AnalogControllerConfigurationEvent) eventObject);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
            }
        }

        super.fireEvent(eventObject);
    }
    
    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception
    {
        ((AnalogControllerConfigurationEventListenerInterface) eventListenerInterface)
                .onAnalogControllerConfigurationEvent((AnalogControllerConfigurationEvent) eventObject);
    }
}
