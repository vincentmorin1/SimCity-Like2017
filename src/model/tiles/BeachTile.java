package model.tiles;

import model.CityResources;

public class BeachTile extends BuildingTile{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
		 * Default energy consumption {@link #getEnergyConsumption()}
		 */
		public final static int DEFAULT_ENERGY_CONSUMPTION = 0;

		/**
		 * Default value of {@link #getNumberTouristsMax()}
		 */
	    public final static int DEFAULT_NUMBER_TOURISTS_MAX = 100;
	    
		/**
		 * Dimension x of the tile {@link #getDimensionX()}
		 */
		public final static int DIMENSION_WIDTH = 1;
		
		/**
		 * Dimension x of the tile {@link #getDimensionY()} 
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
	     * {@link #getEnergyConsumption()}
	     */
	    private final int maxNeededEnergy;
	    
	    /**
	     * {@link #getNumberTourists()
	     */
	    private int numberTourists;
	    
	    /**
	     * {@link #getEnergyConsumption()}
	     */
	    private int energyConsumption;

	    /**
	     * {@link #getNumberTouristsMax()
	     */
	    private final int numberTouristsMax;

	    /**
	     * Evolution state
	     * {@link #isDestroyed()}
	     */
	    protected boolean isDestroyed;

	    // Creation
	    /**
	     * @param energyConsumption
	     *            - {@link #getEnergyConsumption()}
	     * @param numberTouristsMax
	     * 			  - {@link #getNumberTouristsMax()}
	     * @param topLeftCornerX
	     * 			  - {@link #topLeftCornerX()}
	     * @param topLeftCornerY
	     * 			  - {@link #topLeftCornerY()}
	     */
	    public BeachTile(int energyConsumption, int topLeftCornerX ,int topLeftCornerY) {
	        super();
	        this.energyConsumption = energyConsumption;
	    	this.topLeftCornerX = topLeftCornerX;
	    	this.topLeftCornerY = topLeftCornerY;
	    	this.numberTourists=0;
	    	this.numberTouristsMax = BeachTile.DEFAULT_NUMBER_TOURISTS_MAX;
	    	this.linked = false;
	    	this.maxNeededEnergy = energyConsumption;
	    	this.isDestroyed = false;
	    }

	    /**
	     * Create with default settings.
	     */
	    public BeachTile() {
	        this(BeachTile.DEFAULT_ENERGY_CONSUMPTION, 0, 0);
	    }
	    

	    @Override
		public int getDimensionX(){
			return BeachTile.DIMENSION_WIDTH;
		}


	    @Override
		public int getDimensionY(){
			return BeachTile.DIMENSION_HEIGHT;
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
		 * @return tourist's number
		 */
		public int getNumberTourists() {
			return this.numberTourists;
		}
		
		/**
		 * @return maximum tourist's number
		 */
		public int getNumberTouristsMax() {
			return this.numberTouristsMax;
		}
		
		/**
		 * @return energy consumption
		 */
		public int getEnergyConsumption() {
			return this.energyConsumption;
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
	        return false;
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
	        return result;
	    }
	    
	    // Status
	    @Override
	    public boolean equals(Object o) {
	        return o instanceof BeachTile && this.equals((BeachTile) o);
	    }

	    @Override
	    public boolean equals(BuildingTile o) {
	    	if (o instanceof BeachTile){
	    		BeachTile sc = (BeachTile) o;
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
	        this.numberTourists=0;
	    }

	    @Override
	    public void update(CityResources res) {
	    	
	        if (!(this.isDestroyed) && this.getLinked()) {
	        	this.numberTourists = res.peopleToLeisure(this.numberTouristsMax,40,20);
	        }	        
	    }
	    
	    @Override
	    public String[] getInformations(){
	    	String[] res = new String[4];
	    	res[0] = this.getClass().getSimpleName();
	    	res[1] = "Tourists : " + this.getNumberTourists() + "/" + this.numberTouristsMax;
	    	res[2] = "Linked : " + this.getLinked();
	    	res[3] = "Powered : " + !this.getIsEnergyMissing();
	    	return res;
	    }


	}
