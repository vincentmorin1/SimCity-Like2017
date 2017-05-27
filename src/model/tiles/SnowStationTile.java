package model.tiles;

import model.CityResources;

public class SnowStationTile extends BuildingTile{

	    public final static int DEFAULT_ENERGY_CONSUMPTION = 80;

	    public final static int DEFAULT_NUMBER_TOURISTS_MAX = 25;
	    
		public final static int DIMENSION_WIDTH = 1;
		
		public final static int DIMENSION_HEIGHT = 1;
		
	    private final int topLeftCornerX;
	    
	    private final int topLeftCornerY;
	    
	    private boolean linked;
	    
	    private boolean isEnergyMissing;
	    
	    private final int maxNeededEnergy;
	    
	    private int numberTourists;

	    private final int numberTouristsMax;

	    /**
	     * Evolution state
	     */
	    protected boolean isDestroyed;

	    // Creation
	    /**
	     * @param productionCapacity
	     *            - {@link #getProductionCapacity()}
	     */
	    public SnowStationTile(int energyConsumption, int topLeftCornerX ,int topLeftCornerY) {
	        super();
	    	this.topLeftCornerX = topLeftCornerX;
	    	this.topLeftCornerY = topLeftCornerY;
	    	this.numberTourists = 0;
	    	this.numberTouristsMax = SnowStationTile.DEFAULT_NUMBER_TOURISTS_MAX;
	    	this.linked = false;
	    	this.isEnergyMissing = true;
	    	this.maxNeededEnergy = energyConsumption;
	    	this.isDestroyed = false;
	    }

	    /**
	     * Create with default settings.
	     */
	    public SnowStationTile() {
	        this(SnowStationTile.DEFAULT_ENERGY_CONSUMPTION, 0, 0);
	    }

		public int getDimensionX(){
			return SnowStationTile.DIMENSION_WIDTH;
		}

		public int getDimensionY(){
			return SnowStationTile.DIMENSION_HEIGHT;
		}
		
		public int getTopLeftCornerX(){
			return this.topLeftCornerX;
		}
		
		public int getTopLeftCornerY(){
			return this.topLeftCornerY;
		}
		
		public int getNumberTourists() {
			return this.numberTourists;
		}
   
	    @Override
	    public void setLinked(boolean b){
	    	this.linked = b;
	    	
	    }
	    
	    /**
	     *  @return true if linked by road.
	     */
	    public boolean getLinked(){
	    	return this.linked;
	    }
	    
	    /**
	     * @return Is energy missing in order to evolve or to update?
	     */
	    public final boolean getIsEnergyMissing() {
	        return this.isEnergyMissing;
	    }

	    
	    public int hashCode() {
	    	int result=1;
	        result = result* 17 + this.numberTourists;
	        result = result* 17 + this.numberTouristsMax;
	        result = result* 17 + this.topLeftCornerX;
	        result = result* 17 + this.topLeftCornerY;
	        result = result* 17 + this.maxNeededEnergy;
	        result = result* 17 + Boolean.hashCode(this.isDestroyed);
	        result = result* 17 + Boolean.hashCode(this.linked);
	        result = result* 17 + Boolean.hashCode(this.isEnergyMissing);
	        return result;
	    }
	    
	    // Status
	    @Override
	    public boolean equals(Object o) {
	        return o instanceof SnowStationTile && this.equals((SnowStationTile) o);
	    }

	    /**
	     * @param sc
	     * @return Is {@value sc} equals to this?
	     */
	    public boolean equals(SnowStationTile sc) {
	        return this == sc || (sc.isDestroyed == this.isDestroyed && this.numberTourists == sc.numberTourists);
	    }

	    @Override
	    public boolean isDestroyed() {
	        return this.isDestroyed;
	    }

	    // Change
	    @Override
	    public void disassemble(CityResources res) {
	        if (!this.isDestroyed) {
	            this.isDestroyed = true;
	        }
	    }

	    @Override
	    public void update(CityResources res) {
	    	
	        if (!(this.isDestroyed) && this.getLinked()){
	        	if (res.getUnconsumedEnergy() > this.maxNeededEnergy) { 

	        		res.consumeEnergy(maxNeededEnergy);
	        		this.isEnergyMissing = false;
		        	this.numberTourists = res.peopleToLeisure(this.numberTouristsMax,20,40);	
		        }
		        else{
		        	this.isEnergyMissing = true;
		        	this.numberTourists = 0;
		        }  
	        }
	    }
	    
	    public String[] getInformations(){
	    	String[] res = new String[4];
	    	res[0] = this.getClass().getSimpleName();
	    	res[1] = "Tourists : " + this.getNumberTourists() + "/" + this.numberTouristsMax;
	    	res[2] = "Linked by road : " + this.getLinked();
	    	res[3] = "Powered : " + !this.getIsEnergyMissing();
	    	return res;
	    }
	    

	}