package model.tiles;

import java.io.Serializable;

import model.CityResources;

public class MountainTile  extends Tile implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * {@link #getTopLeftCornerX()}
     */
    private final int topLeftCornerX;
    
    /**
     * {@link #getTopLeftCornerY()}
     */
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

    @Override
    public String[] getInformations(){
    	String[] res = new String[1];
    	res[0] = this.getClass().getSimpleName();
    	return res;
    }



}