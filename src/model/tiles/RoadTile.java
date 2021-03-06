package model.tiles;

import java.io.Serializable;

import model.CityResources;

public class RoadTile extends BuildingTile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Dimension x of the tile 
	 */
	public final static int DIMENSION_WIDTH = 1;
	
	/**
	 * Dimension y of the tile
	 */
	public final static int DIMENSION_HEIGHT = 1;
	
	/**
	 * Evolution state
	 */
    protected boolean isDestroyed;

    /**
     * {@link #getTopLeftCornerX()}
     */
    private final int topLeftCornerX;
    
    /**
     * {@link #getTopLeftCornerY()}
     */
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

    @Override
	public int getDimensionX(){
		return 1;
	}

    @Override
	public int getDimensionY(){
		return 1;
	}

    @Override
	public int getTopLeftCornerX(){
		return this.topLeftCornerX;
	}

    @Override
	public int getTopLeftCornerY(){
		return this.topLeftCornerY;
	}

    @Override
    public int hashCode() {
        return 1;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof RoadTile && this.equals((RoadTile) o);
    }
    
    @Override
    public boolean equals(BuildingTile o) {
    	if (o instanceof RoadTile){
    		RoadTile sc = (RoadTile) o;
    		return this == sc || sc.linked == this.linked && sc.isDestroyed == this.isDestroyed;
    	}
    	else{
    		return false;
    	}
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


    @Override
    public boolean getLinked(){
    	return this.linked;
    }
    

    @Override
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

    @Override
    public String[] getInformations(){
    	String[] res = new String[3];
    	res[0] = this.getClass().getSimpleName();
    	res[1] = "Linked by road : " + this.getLinked();
    	return res;
    }
}
