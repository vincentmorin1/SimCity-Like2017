package model.tiles;

import model.CityResources;

public class RoadTile extends Tile implements Destroyable{
	
    protected boolean isDestroyed;

    /**
     * {@link #getLinked()} 
     */
    protected boolean linked;
    
    // Creation
    public RoadTile() {}

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
     * Change la valeur de linked
     * @param b
     */
    public void setLinked(boolean b){
    	this.linked = b;
    }
    
    @Override
    public void update(CityResources res) {}

}
