package model.tiles;

import model.CityResources;

public class SnowStationTile extends BuildingTile{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
		 *  Default energy consumption 
		 */
		public final static int DEFAULT_ENERGY_CONSUMPTION = 80;

		/**
		 * Default maximum capacity
		 */
	    public final static int DEFAULT_NUMBER_TOURISTS_MAX = 25;
	    
		/**
		 * Dimension x of the tile 
		 */
		public final static int DIMENSION_WIDTH = 1;
		
		/**
		 * Dimension y of the tile
		 */
		public final static int DIMENSION_HEIGHT = 1;
		
	    /**
	     * {@link #getTopLeftCornerX()}
	     */
	    private final int topLeftCornerX;
	    
	    /**
	     * {@link #getTopLeftCornerY()}
	     */
	    private final int topLeftCornerY;
	    
	    /**
	     * {@link #getLinked()}
	     */
	    private boolean linked;
	    
	    /**
	     * {@link #getIsEnergyMissing()}
	     */
	    private boolean isEnergyMissing;
	    
	    /**
	     * {@link #getMaxNeededEnergy()}
	     */
	    private final int maxNeededEnergy;
	    
	    /**
	     * {@link #getNumberTourists()}
	     */
	    private int numberTourists;

	    /**
	     * {@link #getNumberTouristsMax()}
	     */
	    private final int numberTouristsMax;

	    /**
	     * Evolution state
	     * {@link #getLinked()}
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

		@Override
		public int getDimensionX(){
			return SnowStationTile.DIMENSION_WIDTH;
		}
		
		/**
		 * @return Maximum needed energy.
		 */
		public int getMaxNeededEnergy() {
			return this.maxNeededEnergy;
		}
		
		/**
		 * @return Maximum number of tourists
		 */
		public int getNumberTouristsMax() {
			return this.numberTouristsMax;
		}

		@Override
		public int getDimensionY(){
			return SnowStationTile.DIMENSION_HEIGHT;
		}

		@Override
		public int getTopLeftCornerX(){
			return this.topLeftCornerX;
		}

		@Override
		public int getTopLeftCornerY(){
			return this.topLeftCornerY;
		}
		
		/**
		 * @return Number of tourists.
		 */
		public int getNumberTourists() {
			return this.numberTourists;
		}
   
	    @Override
	    public void setLinked(boolean b){
	    	this.linked = b;
	    	
	    }
	    
		@Override
	    public boolean getLinked(){
	    	return this.linked;
	    }	    

		@Override
	    public final boolean getIsEnergyMissing() {
	        return this.isEnergyMissing;
	    }

		@Override
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

	    @Override
	    public boolean equals(BuildingTile o) {
	    	if (o instanceof SnowStationTile){
	    		SnowStationTile sc = (SnowStationTile) o;
		        return this == sc || (sc.isDestroyed == this.isDestroyed && this.numberTourists == sc.numberTourists);
	    	}
	    	else{
	    		return false;
	    	}
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
	    
	    @Override
	    public String[] getInformations(){
	    	String[] res = new String[4];
	    	res[0] = this.getClass().getSimpleName();
	    	res[1] = "Tourists : " + this.getNumberTourists() + "/" + this.numberTouristsMax;
	    	res[2] = "Linked by road : " + this.getLinked();
	    	res[3] = "Powered : " + !this.getIsEnergyMissing();
	    	return res;
	    }
	    

	}