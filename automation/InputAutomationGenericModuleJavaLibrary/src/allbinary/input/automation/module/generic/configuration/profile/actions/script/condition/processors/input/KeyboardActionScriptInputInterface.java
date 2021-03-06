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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.input;

public interface KeyboardActionScriptInputInterface
    extends ProfileActionScriptInputInterface
{
    int getDelayBetweenKeys();
    
    Integer[] getKeyArray();
    //void setKeyArray(Integer key[]);
    boolean isPress();
    boolean isRelease();
    boolean isNormal();
    
    void setPress(boolean press);
    void setRelease(boolean release);
    void setNormal();
            
    String getText();
    void setText(String text);
    void log();
}
