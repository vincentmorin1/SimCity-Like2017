package model.tiles;

import java.io.Serializable;

import model.CityResources;

public class RoadTile extends BuildingTile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int DIMENSION_WIDTH = 1;
	public final static int DIMENSION_HEIGHT = 1;
	
	/**
	 * Evolution state
	 */
    protected boolean isDestroyed;

    private final int topLeftCornerX;
    
    private final int topLeftCornerY;
    
    /**
     * {@link #getLinked()} 
     */
    protected boolean linked;
    
    // Creation
    /**
     * RoadTile constructor
     * 
     * @param topLeftCornerX
     * @param topLeftCornerY
     */
    public RoadTile(int topLeftCornerX ,int topLeftCornerY) {
    	this.topLeftCornerX = topLeftCornerX;
    	this.topLeftCornerY = topLeftCornerY;
    }
    
    public RoadTile() {
    	this (0,0);
    }

	public int getDimensionX(){
		return 1;
	}

	public int getDimensionY(){
		return 1;
	}
	
	public int getTopLeftCornerX(){
		return this.topLeftCornerX;
	}
	
	public int getTopLeftCornerY(){
		return this.topLeftCornerY;
	}
	
    public int hashCode() {
        return 1;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof RoadTile && this.equals((RoadTile) o);
    }
    
    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(RoadTile o) {
        return this == o || o.linked == this.linked && o.isDestroyed == this.isDestroyed;
    }

    @Override
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            this.isDestroyed = true;
        }
    }

    /**
     *  @return true if linked by road.
     */
    public boolean getLinked(){
    	return this.linked;
    }
    
    /**
     * Change the value of linked
     * 
     * @param b
     */
    public void setLinked(boolean b){
    	this.linked = b;
    }
    
    public String getRoadConnection(Tile[] tiles){
    	String res =  "-";		
		if (tiles[0] instanceof RoadTile)
			res += "N";
		else if (tiles[0] instanceof BuildingTile) {
			BuildingTile bt = (BuildingTile) tiles[0];
			bt.setLinked(true);
		}
		if (tiles[1] instanceof RoadTile)
			res += "S";
		else if (tiles[1] instanceof BuildingTile) {
			BuildingTile bt = (BuildingTile) tiles[1];
			bt.setLinked(true);
		}
		if (tiles[2] instanceof RoadTile)
			res += "E";
		else if (tiles[2] instanceof BuildingTile) {
			BuildingTile bt = (BuildingTile) tiles[2];
			bt.setLinked(true);
		}
		if (tiles[3] instanceof RoadTile)
			res += "W";
		else if (tiles[3] instanceof BuildingTile) {
			BuildingTile bt = (BuildingTile) tiles[3];
			bt.setLinked(true);
		}
		return res;
    }
    
    @Override
    public void update(CityResources res) {}

	@Override
	public boolean getIsEnergyMissing() {
		return false;
	}

    public String[] getInformations(){
    	String[] res = new String[3];
    	res[0] = this.getClass().getSimpleName();
    	res[1] = "Linked by road : " + this.getLinked();
    	return res;
    }
}
