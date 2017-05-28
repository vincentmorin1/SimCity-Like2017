package model.tiles;

import model.CityResources;

public class PoliceOfficeTile extends BuildingTile{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public final static int DEFAULT_ENERGY_CONSUMPTION = 15;

	    public final static int DEFAULT_NUMBER_WORKERS_MAX = 25;
	    
	    public final static int DEFAULT_NUMBER_INHABITANTS_CARED = 50;
	    
		/**
		 * Dimension x of the tile 
		 */
		public final static int DIMENSION_WIDTH = 1;
		
		/**
		 * Dimension y of the tile
		 */
		public final static int DIMENSION_HEIGHT = 2;
		
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
	     * {@link #getMaxNeededEnergy()}
	     */
	    private final int maxNeededEnergy;
	    
	    /**
	     * {@link #getNumberWorkers()}
	     */
	    private int numberWorkers;

	    /**
	     * {@link #getNumberWorkersMax()}
	     */
	    private final int numberWorkersMax;
	    
	    /**
	     * Evolution state
	     * {@link #isDestroyed()}
	     */
	    protected boolean isDestroyed;

	    /**
	     * {@link #getIsEnergyMissing()} 
	     */
	    private boolean isEnergyMissing;

	    // Creation
	    /**
	     * @param energyConsumption
	     * 			  - {@link #getProductionCapacity()}
	     * @param topLeftCornerX
	     *            - {@link #getTopLeftCornerX()}
	     * @param topLeftCornerY
	     *             - {@link #getTopLeftCornerY()}
	     */
	    public PoliceOfficeTile(int energyConsumption, int topLeftCornerX ,int topLeftCornerY) {
	        super();
	    	this.topLeftCornerX = topLeftCornerX;
	    	this.topLeftCornerY = topLeftCornerY;
	    	this.numberWorkers=0;
	    	this.numberWorkersMax = PoliceOfficeTile.DEFAULT_NUMBER_WORKERS_MAX;
	    	this.linked = false;
	    	this.isEnergyMissing = true;
	    	this.maxNeededEnergy = energyConsumption;
	    	this.isDestroyed = false;
	    }

	    /**
	     * Create with default settings.
	     */
	    public PoliceOfficeTile() {
	        this(PoliceOfficeTile.DEFAULT_ENERGY_CONSUMPTION, 0, 0);
	    }
	    
	    /**
	     * @return Maximum of needed energy
	     */
	    public int getMaxNeededEnergy() {
	    	return this.maxNeededEnergy;
	    }
	    
	    /**
	     * @return Maximum number of workers
	     */
	    public int getNumberWorkersMax() {
	    	return this.numberWorkersMax;
	    }

	    @Override
	    public int getDimensionX(){
			return PoliceOfficeTile.DIMENSION_WIDTH;
		}

	    @Override
		public int getDimensionY(){
			return PoliceOfficeTile.DIMENSION_HEIGHT;
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
	     * @return Number of workers
	     */
		public int getNumberWorkers() {
			return this.numberWorkers;
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
	        result = result* 17 + this.numberWorkers;
	        result = result* 17 + this.numberWorkersMax;
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
	        return o instanceof PoliceOfficeTile && this.equals((PoliceOfficeTile) o);
	    }

	    @Override
	    public boolean equals(BuildingTile o) {
	    	if (o instanceof PoliceOfficeTile){
	    		PoliceOfficeTile sc = (PoliceOfficeTile) o;
	    		return this == sc || (sc.isDestroyed == this.isDestroyed && this.numberWorkers == sc.numberWorkers);
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
		        	this.numberWorkers = Math.min(res.getUnworkingSeniorPopulation(), this.numberWorkersMax);	                
		            res.hireWorkers(this.numberWorkers);	
		        }
		        else{
		        	this.isEnergyMissing = true;
		        	this.numberWorkers = 0;
		        }  
	        }
	    }

	    @Override
	    public String[] getInformations(){
	    	String[] res = new String[4];
	    	res[0] = this.getClass().getSimpleName();
	    	res[1] = "Policemen : " + this.getNumberWorkers() + " / " + this.numberWorkersMax;
	    	res[2] = "Linked by road : " + this.getLinked();
	    	res[3] = "Powered : " + !this.isEnergyMissing;
	    	return res;
	    }
	}
