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
package allbinary.game.input;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.input.motion.button.BasicTouchInputFactory;

public class PlatformFormInputMappingFactory
{
    private static final PlatformFormInputMappingFactory instance = 
        new PlatformFormInputMappingFactory();
    
    private static InputToGameKeyMapping SINGLETON = null;

    public static InputToGameKeyMapping getInstance()
    {
        try
        {
            if (SINGLETON == null)
            {
                PCKeyFactory pcKeyFactory = PCKeyFactory.getInstance();

                final InputToGameKeyMapping inputToGameKeyMapping = new InputToGameKeyMapping();

                final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
                
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory.DPAD_UP);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory.DPAD_DOWN);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory.DPAD_LEFT);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory.DPAD_RIGHT);

        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.ENTER);

                //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, androidKeyFactory.DPAD_CENTER);

                inputToGameKeyMapping.add(gameKeyFactory.UP, BasicTouchInputFactory.getInstance().UP);
                inputToGameKeyMapping.add(gameKeyFactory.LEFT, BasicTouchInputFactory.getInstance().LEFT);
                inputToGameKeyMapping.add(gameKeyFactory.RIGHT, BasicTouchInputFactory.getInstance().RIGHT);
                inputToGameKeyMapping.add(gameKeyFactory.DOWN, BasicTouchInputFactory.getInstance().DOWN);
                
                SINGLETON = inputToGameKeyMapping;
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, instance, CommonStrings.getInstance().GET_INSTANCE, e));
        }
        return SINGLETON;
    }

}