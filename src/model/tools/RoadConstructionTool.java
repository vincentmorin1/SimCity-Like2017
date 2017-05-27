package model.tools;

import java.io.Serializable;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.RoadTile;
import model.tiles.Tile;

public class RoadConstructionTool extends Tool implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constant
	/**
	 * Price to build a piece of road.
	 */
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
     * isAfordable returns true if the user can apply the Road Tool, false
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
     * innerEffect apply the Road tool to the given tile and update the
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
		return RoadTile.DIMENSION_WIDTH;
	}
	
	@Override
	public  int getDimensionY(){
		return RoadTile.DIMENSION_HEIGHT;
	}

}

