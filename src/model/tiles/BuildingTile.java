package model.tiles;

public abstract class BuildingTile extends Tile implements Destroyable{

    /**
     * Set the link state
     * @param b
     */
    public abstract void setLinked(boolean b);
    
    public abstract boolean getLinked();
    
    public abstract boolean getIsEnergyMissing();
    
    
}
