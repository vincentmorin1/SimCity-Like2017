package model.tiles;

import model.CityResources;

public class MountainTile  extends Tile {

    private final int topLeftCornerX;
    private final int topLeftCornerY;
    
    // Constant
    /**
     * Default instance.
     */
    private final static MountainTile INSTANCE = new MountainTile();

    // Factory
    /**
     * @return Default grass tile.
     */
    public static MountainTile getDefault() {
        // Provide always the same instance since Grass is not changing.
        return MountainTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link GrassTile#getDefault()} instead.
     */
    private MountainTile() {
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
        return this == o || o instanceof MountainTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }
    


}