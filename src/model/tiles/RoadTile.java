package model.tiles;

import model.CityResources;

public class RoadTile extends Tile implements Destroyable{
	
    protected boolean isDestroyed;

    private final int topLeftCornerX;
    private final int topLeftCornerY;
    
    /**
     * {@link #getLinked()} 
     */
    protected boolean linked;
    
    // Creation
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
    public boolean equals(Object o) {
        return o instanceof RoadTile && this.equals((RoadTile) o);
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    // Change
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
     * Change la valeur de linked
     * @param b
     */
    public void setLinked(boolean b){
    	this.linked = b;
    }
    
    @Override
    public void update(CityResources res) {}

}
