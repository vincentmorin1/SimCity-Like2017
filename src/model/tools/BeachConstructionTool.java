package model.tools;

import model.CityResources;
import model.tiles.WaterTile;
import model.tiles.BeachTile;
import model.tiles.GrassTile;
import model.tiles.Tile;

public class BeachConstructionTool extends Tool {

    // Constant
    private final static int CURRENCY_COST = 70;

    // Status
    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
    public boolean canEffect(Tile aTarget) {
        return aTarget instanceof GrassTile;
    }
    
    public boolean nextToWaterTile(int x, int y, Tile[][] tiles){
    	if (x>0 && tiles[x-1][y] instanceof WaterTile){
    		return true;
    	}
    	else if (x< tiles.length-1 && tiles[x+1][y] instanceof WaterTile){
    		return true;
    	}
    	else if (y>0 && tiles[x][y-1] instanceof WaterTile){
    		return true;
    	}
    	else if (y< tiles[0].length-1 && tiles[x][y+1] instanceof WaterTile){
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof BeachConstructionTool;

    }

    /**
     * isAfordable returns true if the user can apply the PowerPlant Tool, false
     * otherwise.
     */
    @Override
    public boolean isAfordable(Tile aTarget, CityResources r) {
        return BeachConstructionTool.CURRENCY_COST <= r.getCurrency();
    }

    // Access
    @Override
    public int getCost(Tile aTarget) {
        return BeachConstructionTool.CURRENCY_COST;
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

        r.spend(BeachConstructionTool.CURRENCY_COST);

        return new BeachTile(BeachTile.DEFAULT_ENERGY_CONSUMPTION, BeachTile.DEFAULT_NUMBER_TOURISTS_MAX, topLeftCornerX , topLeftCornerY);
    }

    // Debugging
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

	@Override
	public  int getDimensionX(){
		return BeachTile.DIMENSION_WIDTH;
	}
	
	@Override
	public  int getDimensionY(){
		return BeachTile.DIMENSION_HEIGHT;
	}
	
}
