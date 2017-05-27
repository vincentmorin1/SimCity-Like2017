package model.tools;

import java.io.Serializable;

import model.CityResources;
import model.tiles.MountainTile;
import model.tiles.SnowStationTile;
import model.tiles.Tile;

public class SnowStationConstructionTool extends Tool  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constant
    private final static int CURRENCY_COST = 250;

    // Status
    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
    @Override
    public boolean canEffect(Tile aTarget) {
        return aTarget instanceof MountainTile;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof SnowStationConstructionTool;

    }

    /**
     * isAfordable returns true if the user can apply the PowerPlant Tool, false
     * otherwise.
     */
    @Override
    public boolean isAfordable(Tile aTarget, CityResources r) {
        return SnowStationConstructionTool.CURRENCY_COST <= r.getCurrency();
    }

    // Access
    @Override
    public int getCost(Tile aTarget) {
        return SnowStationConstructionTool.CURRENCY_COST;
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    /**
     * innerEffect apply the PowerPlant tool to the given tile and update the
     * given CityResources.
     */
    @Override
    protected Tile innerEffect(Tile aTarget, CityResources r, int topLeftCornerX ,int topLeftCornerY) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spend(SnowStationConstructionTool.CURRENCY_COST);

        return new SnowStationTile(SnowStationTile.DEFAULT_ENERGY_CONSUMPTION, topLeftCornerX , topLeftCornerY);
    }

    // Debugging
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

	@Override
	public  int getDimensionX(){
		return SnowStationTile.DIMENSION_WIDTH;
	}
	
	@Override
	public  int getDimensionY(){
		return SnowStationTile.DIMENSION_HEIGHT;
	}
	
}
