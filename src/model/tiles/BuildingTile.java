package model.tiles;

import java.io.Serializable;

public abstract class BuildingTile extends Tile implements Destroyable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Set the link state
     * @param b
     */
    public abstract void setLinked(boolean b);
    
    public abstract boolean getLinked();
    
    public abstract boolean getIsEnergyMissing();
    
    
}
