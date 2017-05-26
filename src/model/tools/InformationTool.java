package model.tools;

import model.CityResources;
import model.tiles.BeachTile;
import model.tiles.GrassTile;
import model.tiles.Tile;
import model.tiles.WaterTile;

public class InformationTool extends Tool{

    // Constant
    private final static int CURRENCY_COST = 0;

    // Status
    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
    public boolean canEffect(Tile aTarget) {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof InformationTool;

    }

    /**
     * isAfordable returns true if the user can apply the PowerPlant Tool, false
     * otherwise.
     */
    @Override
    public boolean isAfordable(Tile aTarget, CityResources r) {
        return true;
    }

    // Access
    @Override
    public int getCost(Tile aTarget) {
        return InformationTool.CURRENCY_COST;
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
    protected Tile innerEffect(Tile aTarget, CityResources r, int x ,int y) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spend(InformationTool.CURRENCY_COST);

        return null;
    }

    // Debugging
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

	@Override
	public  int getDimensionX(){
		return 1;
	}
	
	@Override
	public  int getDimensionY(){
		return 1;
	}
}