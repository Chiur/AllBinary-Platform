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
package allbinary.game.layer;

import javax.microedition.lcdui.game.TiledLayer;

import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.media.graphics.geography.map.racetrack.AllBinaryTiledLayerFactoryInterface;
import allbinary.media.graphics.geography.map.racetrack.RaceTrackData;
import allbinary.media.graphics.geography.map.racetrack.RaceTrackInfo;
import allbinary.media.graphics.geography.map.racetrack.TiledLayerFactory;

/**
 *
 * @author user
 */
public class AllBinaryTiledLayerFactory implements AllBinaryTiledLayerFactoryInterface
{
    //@Override
    public AllBinaryTiledLayer getInstance(
            RaceTrackInfo raceTrackInfo,
            RaceTrackData raceTrackData)
            throws Exception
    {
        AllBinaryTiledLayer allbinaryTiledLayer;
        
            TiledLayer tiledLayer = new TiledLayerFactory().getInstance(raceTrackData);

            allbinaryTiledLayer = new AllBinaryJ2METiledLayer(
                    raceTrackData.getId(), tiledLayer, raceTrackData.getMapArray());

            allbinaryTiledLayer.setCells(raceTrackData.getMapArray());            
        
        return allbinaryTiledLayer;
    }

    //@Override
    public AllBinaryTiledLayer getMiniInstance(
            RaceTrackData raceTrackData)
            throws Exception
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        TiledLayer tiledLayer = new TiledLayerFactory().getMiniInstance(raceTrackData);

        AllBinaryTiledLayer allbinaryTiledLayer = new AllBinaryJ2METiledLayer(
                smallIntegerSingletonFactory.getInstance(raceTrackData.getId().intValue() + 100 + 1),
                tiledLayer, raceTrackData.getMapArray());

        allbinaryTiledLayer.setCells(raceTrackData.getMapArray());

        return allbinaryTiledLayer;
    }
}
