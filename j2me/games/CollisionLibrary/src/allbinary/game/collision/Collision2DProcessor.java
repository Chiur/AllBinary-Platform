package allbinary.game.collision;

import allbinary.layer.AllBinaryLayer;

public class Collision2DProcessor
extends CollisionProcessor
{
    public boolean isCollision(AllBinaryLayer myLayer, AllBinaryLayer myLayer2)
    {
        if (myLayer2.getX() >= myLayer.getX2() || myLayer2.getY() >= myLayer.getY2() || 
                myLayer2.getX2() <= myLayer.getX() || myLayer2.getY2() <= myLayer.getY())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
