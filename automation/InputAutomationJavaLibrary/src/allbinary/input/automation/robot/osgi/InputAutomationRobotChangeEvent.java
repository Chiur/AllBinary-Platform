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
package allbinary.input.automation.robot.osgi;

public class InputAutomationRobotChangeEvent
{
    private InputAutomationRobotInterfaceWrapper inputAutomationRobotInterfaceWrapper;
    
    public InputAutomationRobotChangeEvent(
        InputAutomationRobotInterfaceWrapper inputAutomationRobotInterfaceWrapper)
    {
        this.setInputAutomationRobotInterfaceWrapper(inputAutomationRobotInterfaceWrapper);
    }

    public InputAutomationRobotInterfaceWrapper getInputAutomationRobotInterfaceWrapper()
    {
        return inputAutomationRobotInterfaceWrapper;
    }

    public void setInputAutomationRobotInterfaceWrapper(
        InputAutomationRobotInterfaceWrapper inputAutomationRobotInterfaceWrapper)
    {
        this.inputAutomationRobotInterfaceWrapper = inputAutomationRobotInterfaceWrapper;
    }
}
