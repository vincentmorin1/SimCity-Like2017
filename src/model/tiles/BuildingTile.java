package model.tiles;

import java.io.Serializable;

public abstract class BuildingTile extends Tile implements Destroyable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public abstract boolean equals(BuildingTile o) ;
	/**
     * Set the link state
     * @param b
     */
    public abstract void setLinked(boolean b);
    
    /**
     * @return true if not enough energy, else false
     */
	public abstract boolean getIsEnergyMissing();
	
	/**
	 * @return true if linked by road, else false
	 */
    public abstract boolean getLinked();


	/**
	 * Help to know which picture is necessary
	 * @param tiles
	 * @return
	 */
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
