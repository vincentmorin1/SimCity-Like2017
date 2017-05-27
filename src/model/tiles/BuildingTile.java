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
    
	public abstract boolean getIsEnergyMissing();
	
    public abstract boolean getLinked();
    
    
    public String getBuildingConnection(Tile[] tiles){
    	String res =  "";
		if (tiles[0] == this){
			res += "1";
		}
		else if (tiles[1] == this){
			res += "0";
		}
		if (tiles[2] == this){
			res += "0";
		}
		else if (tiles[3] == this){
			res += "1";
		}
		return res;
    }
}
