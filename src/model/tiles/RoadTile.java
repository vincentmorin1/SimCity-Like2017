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
    
    @Override
    public void update(CityResources res) {}

	@Override
	public boolean getIsEnergyMissing() {
		return false;
	}

}
