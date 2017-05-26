package model.tiles;

import java.io.Serializable;

import model.CityResources;

public class WaterTile  extends Tile implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int topLeftCornerX;
    private final int topLeftCornerY;
    
    // Constant
    /**
     * Default instance.
     */
    private final static WaterTile INSTANCE = new WaterTile();

    // Factory
    /**
     * @return Default grass tile.
     */
    public static WaterTile getDefault() {
        // Provide always the same instance since Grass is not changing.
        return WaterTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link GrassTile#getDefault()} instead.
     */
    private WaterTile() {
    	this.topLeftCornerX = 0;
    	this.topLeftCornerY = 0;
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
	
    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof WaterTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }
    
    public String[] getInformations(){
    	String[] res = new String[1];
    	res[0] = this.getClass().getSimpleName();
    	return res;
    }

}