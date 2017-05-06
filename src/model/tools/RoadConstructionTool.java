package model.tools;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.RoadTile;
import model.tiles.Tile;

public class RoadConstructionTool extends Tool{

	// Constant
private final static int CURRENCY_COST = 5;


	// Status
	/**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
	@Override
	public boolean canEffect (Tile aTarget) {
		return aTarget instanceof GrassTile;
	}

	@Override
	public boolean equals (Object o) {
		return this == o || o instanceof RoadConstructionTool;
		
	}

	/**
     * isAfordable returns true if the user can apply the Industrial Tool, false
     * otherwise.
     */
	@Override
	public boolean isAfordable (Tile aTarget, CityResources r) {
		return RoadConstructionTool.CURRENCY_COST <= r.getCurrency();
	}

	// Access
	@Override
	public int getCost (Tile aTarget) {
		return RoadConstructionTool.CURRENCY_COST;
	}

	@Override
	public int hashCode () {
		return getClass().hashCode();
	}

	/**
     * innerEffect apply the Industrial tool to the given tile and update the
     * given CityResources.
     */
	@Override
	protected Tile innerEffect (Tile aTarget, CityResources r, int topLeftCornerX ,int topLeftCornerY) {
		assert canEffect(aTarget);
		assert isAfordable(aTarget, r);

		r.spend(RoadConstructionTool.CURRENCY_COST);

		return new RoadTile( topLeftCornerX , topLeftCornerY);
	}


	// Debugging
	@Override
	public String toString () {
		return getClass().getSimpleName();
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

