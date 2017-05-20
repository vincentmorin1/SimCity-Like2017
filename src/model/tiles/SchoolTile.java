package model.tiles;

import model.CityResources;

public class SchoolTile extends BuildingTile{

    public final static int DEFAULT_ENERGY_CONSUMPTION = 15;

    public final static int DEFAULT_NUMBER_STUDENT_MAX = 70;
    
	public final static int DIMENSION_WIDTH = 1;
	
	public final static int DIMENSION_HEIGHT = 2;
	
    private final int topLeftCornerX;
    
    private final int topLeftCornerY;
    
    private boolean linked;
    
    private boolean isEnergyMissing;
    
    private final int maxNeededEnergy;
    
    private int numberStudent;

    private final int numberStudentMax;

    /**
     * Evolution state
     */
    protected boolean isDestroyed;

    // Creation
    /**
     * @param productionCapacity
     *            - {@link #getProductionCapacity()}
     */
    public SchoolTile(int energyConsumption, int numberStudentMax, int topLeftCornerX ,int topLeftCornerY) {
        super();
        this.isDestroyed = false;
    	this.topLeftCornerX = topLeftCornerX;
    	this.topLeftCornerY = topLeftCornerY;
    	this.numberStudent=0;
    	this.numberStudentMax = numberStudentMax;
    	this.linked = false;
    	this.isEnergyMissing = true;
    	this.maxNeededEnergy = energyConsumption;
    	this.isDestroyed = false;
    }

    /**
     * Create with default settings.
     */
    public SchoolTile() {
        this(SchoolTile.DEFAULT_ENERGY_CONSUMPTION, SchoolTile.DEFAULT_NUMBER_STUDENT_MAX, 0, 0);
    }

	public int getDimensionX(){
		return SchoolTile.DIMENSION_WIDTH;
	}

	public int getDimensionY(){
		return SchoolTile.DIMENSION_HEIGHT;
	}
	
	public int getTopLeftCornerX(){
		return this.topLeftCornerX;
	}
	
	public int getTopLeftCornerY(){
		return this.topLeftCornerY;
	}
	
	public int getNumberStudent() {
		return numberStudent;
	}

	public void setNumberStudent(int numberStudent) {
		this.numberStudent = Math.min(numberStudent, this.numberStudentMax);
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

    

    
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.numberStudent;
        result = result * 17 + Boolean.hashCode(this.isDestroyed);
        return result;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof SchoolTile && this.equals((SchoolTile) o);
    }

    /**
     * @param sc
     * @return Is {@value sc} equals to this?
     */
    public boolean equals(SchoolTile sc) {
        return this == sc || (sc.isDestroyed == this.isDestroyed && this.numberStudent == sc.numberStudent);
    }

    @Override
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
        	this.setNumberStudent(0);
            this.isDestroyed = true;
        }
    }

    @Override
    public void update(CityResources res) {
    	
        if (!(this.isDestroyed) && this.getLinked()) {
        	final int neededEnergy =  this.maxNeededEnergy; 
        	
        	// Si l'on a assez d'énergie
            if (res.getUnconsumedEnergy() >= neededEnergy) {
            	res.consumeEnergy(neededEnergy);
            	this.isEnergyMissing = false;
            }
            // Sinon la production est diminuée de manière linéaire
            else {
            	this.isEnergyMissing = true;
            }
            
            if(! this.isEnergyMissing){
            	this.setNumberStudent(res.enrolStudent(this.numberStudentMax));
            }
            else{
            	this.setNumberStudent(0);
            }
            	
        }
    }


    

}
