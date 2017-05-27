package model.tools;

import java.io.Serializable;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.SchoolTile;
import model.tiles.Tile;

public class SchoolConstructionTool extends Tool implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constant
	/**
	 * Price to build a school.
	 */
    private final static int CURRENCY_COST = 100;

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
        return this == o || o instanceof SchoolTile;

    }

    /**
     * isAfordable returns true if the user can apply the PowerPlant Tool, false
     * otherwise.
     */
    @Override
    public boolean isAfordable(Tile aTarget, CityResources r) {
        return SchoolConstructionTool.CURRENCY_COST <= r.getCurrency();
    }

    // Access
    @Override
    public int getCost(Tile aTarget) {
        return SchoolConstructionTool.CURRENCY_COST;
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

        r.spend(SchoolConstructionTool.CURRENCY_COST);

        return new SchoolTile(SchoolTile.DEFAULT_ENERGY_CONSUMPTION, topLeftCornerX , topLeftCornerY);
    }

    // Debugging
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

	@Override
	public  int getDimensionX(){
		return SchoolTile.DIMENSION_WIDTH;
	}
	
	@Override
	public  int getDimensionY(){
		return SchoolTile.DIMENSION_HEIGHT;
	}
}