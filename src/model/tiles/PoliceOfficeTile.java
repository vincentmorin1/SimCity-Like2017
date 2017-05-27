package model.tiles;

import model.CityResources;

public class PoliceOfficeTile extends BuildingTile{

	    public final static int DEFAULT_ENERGY_CONSUMPTION = 15;

	    public final static int DEFAULT_NUMBER_WORKERS_MAX = 25;
	    
		public final static int DIMENSION_WIDTH = 1;
		
		public final static int DIMENSION_HEIGHT = 2;
		
	    private final int topLeftCornerX;
	    
	    private final int topLeftCornerY;
	    
	    private boolean linked;
	    
	    private boolean isEnergyMissing;
	    
	    private final int maxNeededEnergy;
	    
	    private int numberWorkers;

	    private final int numberWorkersMax;

	    /**
	     * Evolution state
	     */
	    protected boolean isDestroyed;

	    // Creation
	    /**
	     * @param capacity
	     *            - {@link #getProductionCapacity()}
	     *            - {@link #getTopLeftCornerX()}
	     *            - {@link #getTopLeftCornerY()}
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

		public int getDimensionX(){
			return PoliceOfficeTile.DIMENSION_WIDTH;
		}

		public int getDimensionY(){
			return PoliceOfficeTile.DIMENSION_HEIGHT;
		}
		
		public int getTopLeftCornerX(){
			return this.topLeftCornerX;
		}
		
		public int getTopLeftCornerY(){
			return this.topLeftCornerY;
		}
		
		public int getNumberWorkers() {
			return this.numberWorkers;
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

	    /**
	     * @param sc
	     * @return Is {@value sc} equals to this?
	     */
	    public boolean equals(PoliceOfficeTile sc) {
	        return this == sc || (sc.isDestroyed == this.isDestroyed && this.numberWorkers == sc.numberWorkers);
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

	    public String[] getInformations(){
	    	String[] res = new String[4];
	    	res[0] = this.getClass().getSimpleName();
	    	res[1] = "Policemen : " + this.getNumberWorkers() + " / " + this.numberWorkersMax;
	    	res[2] = "Linked by road : " + this.getLinked();
	    	res[3] = "Powered : " + !this.isEnergyMissing;
	    	return res;
	    }
	}
