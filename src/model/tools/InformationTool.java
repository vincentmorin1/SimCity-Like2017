package model.tools;

import java.io.Serializable;

import model.CityResources;
import model.tiles.Tile;

public class InformationTool extends Tool implements Serializable{

    
	private Tile tile;
	
	private static final long serialVersionUID = 1L;
	// Constant
	/**
	 * Seeing the detailed information is free
	 */
    private final static int CURRENCY_COST = 0;
        
    
	public InformationTool(Tile t){
		this.tile = t;
	}
	
	@Override
    public boolean canEffect(Tile aTarget) {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof InformationTool;
    }

    /**
     * Load the informations regarding a tile.
     * @param Tile
     */
    public void loadInformations(Tile t){
    	this.tile = t;
    }
    
    public String[] getInformations(){
    	return this.tile.getInformations();
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