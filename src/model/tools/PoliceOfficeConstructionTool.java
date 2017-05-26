package model.tools;

import java.io.Serializable;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.Tile;
import model.tiles.PoliceOfficeTile;

public class PoliceOfficeConstructionTool extends Tool  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constant
    private final static int CURRENCY_COST = 150;

    // Status
    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
    @Override
    public boolean canEffect(Tile aTarget) {
        return aTarget instanceof GrassTile;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof PoliceOfficeConstructionTool;

    }

    /**
     * isAfordable returns true if the user can apply the PowerPlant Tool, false
     * otherwise.
     */
    @Override
    public boolean isAfordable(Tile aTarget, CityResources r) {
        return PoliceOfficeConstructionTool.CURRENCY_COST <= r.getCurrency();
    }

    // Access
    @Override
    public int getCost(Tile aTarget) {
        return PoliceOfficeConstructionTool.CURRENCY_COST;
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

        r.spend(PoliceOfficeConstructionTool.CURRENCY_COST);

        return new PoliceOfficeTile(PoliceOfficeTile.DEFAULT_ENERGY_CONSUMPTION, PoliceOfficeTile.DEFAULT_NUMBER_WORKERS_MAX, topLeftCornerX , topLeftCornerY);
    }

    // Debugging
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

	@Override
	public  int getDimensionX(){
		return PoliceOfficeTile.DIMENSION_WIDTH;
	}
	
	@Override
	public  int getDimensionY(){
		return PoliceOfficeTile.DIMENSION_HEIGHT;
	}
}