package model.tiles;

import model.CityResources;

public class BeachTile extends BuildingTile{

	    public final static int DEFAULT_ENERGY_CONSUMPTION = 0;

	    public final static int DEFAULT_NUMBER_TOURISTS_MAX = 100;
	    
		public final static int DIMENSION_WIDTH = 1;
		
		public final static int DIMENSION_HEIGHT = 1;
		
	    private final int topLeftCornerX;
	    
	    private final int topLeftCornerY;
	    
	    private boolean linked;
	        
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
	    public BeachTile(int energyConsumption, int numberTouristsMax, int topLeftCornerX ,int topLeftCornerY) {
	        super();
	    	this.topLeftCornerX = topLeftCornerX;
	    	this.topLeftCornerY = topLeftCornerY;
	    	this.numberTourists=0;
	    	this.numberTouristsMax = numberTouristsMax;
	    	this.linked = false;
	    	this.maxNeededEnergy = energyConsumption;
	    	this.isDestroyed = false;
	    }

	    /**
	     * Create with default settings.
	     */
	    public BeachTile() {
	        this(BeachTile.DEFAULT_ENERGY_CONSUMPTION, BeachTile.DEFAULT_NUMBER_TOURISTS_MAX, 0, 0);
	    }

		public int getDimensionX(){
			return BeachTile.DIMENSION_WIDTH;
		}

		public int getDimensionY(){
			return BeachTile.DIMENSION_HEIGHT;
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
	        return false;
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
	        return result;
	    }
	    
	    // Status
	    @Override
	    public boolean equals(Object o) {
	        return o instanceof BeachTile && this.equals((BeachTile) o);
	    }

	    /**
	     * @param sc
	     * @return Is {@value sc} equals to this?
	     */
	    public boolean equals(BeachTile sc) {
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
	    	
	        if (!(this.isDestroyed) && this.getLinked()) {
	        	this.numberTourists = res.peopleToLeisure(this.numberTouristsMax,40,20);
	        }
	            /*
	            if(! this.isEnergyMissing){
	                this.numberTourists = Math.min(res.getUnworkingSeniorPopulation(), this.numberTouristsMax);	                
	                res.hireWorkers(this.numberTourists);
	            }
	            else{
	            	this.numberTourists=0;
	            }
	            */
	        
	    }

	}
