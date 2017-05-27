package model.tiles;

import java.io.Serializable;

import model.CityResources;

public class SchoolTile extends BuildingTile implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int DEFAULT_ENERGY_CONSUMPTION = 15;

    public final static int DEFAULT_NUMBER_STUDENT_MAX = 70;
    
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
     * {@link #getIsEnergyMissing()}
     */
    private boolean isEnergyMissing;
    
    /**
     * {@link #getMaxNeededEnergy()}
     */
    private final int maxNeededEnergy;
    
    /**
     * {@link #getNumberStudent()}
     */
    private int numberStudent;

    /**
     * {@link #getNumberStudentMax()}
     */
    private final int numberStudentMax;

    /**
     * Evolution state
     */
    protected boolean isDestroyed;

    // Creation
    /**
     * 
     * @param energyConsumption  - {@link #getProductionCapacity()}
     * @param topLeftCornerX  - {@link #getTopLeftCornerX()}
     * @param topLeftCornerY - {@link #getTopLeftCornerY()}
     */
    public SchoolTile(int energyConsumption, int topLeftCornerX ,int topLeftCornerY) {
        super();
        this.isDestroyed = false;
    	this.topLeftCornerX = topLeftCornerX;
    	this.topLeftCornerY = topLeftCornerY;
    	this.numberStudent=0;
    	this.numberStudentMax = SchoolTile.DEFAULT_NUMBER_STUDENT_MAX;
    	this.linked = false;
    	this.isEnergyMissing = true;
    	this.maxNeededEnergy = energyConsumption;
    	this.isDestroyed = false;
    }

    /**
     * Create with default settings.
     */
    public SchoolTile() {
        this(SchoolTile.DEFAULT_ENERGY_CONSUMPTION, 0, 0);
    }

    @Override
	public int getDimensionX(){
		return SchoolTile.DIMENSION_WIDTH;
	}

    /**
     * @return Total energy needed.
     */
	public int getMaxNeededEnergy() {
		return this.maxNeededEnergy;
	}

    @Override
	public int getDimensionY(){
		return SchoolTile.DIMENSION_HEIGHT;
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
     * @return Number of students.
     */
	public int getNumberStudent() {
		return this.numberStudent;
	}
	
	/**
	 * @return Maximum number of students.
	 */
	public int getNumberStudentMax() {
		return this.numberStudentMax;
	}

	/**
	 * @param numberStudent
	 */
	public void setNumberStudent(int numberStudent) {
		this.numberStudent = Math.min(numberStudent, this.numberStudentMax);
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

    @Override
    public boolean equals(BuildingTile o) {
    	if (o instanceof SchoolTile){
    		SchoolTile sc = (SchoolTile) o;
            return this == sc || (sc.isDestroyed == this.isDestroyed && this.numberStudent == sc.numberStudent);
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
        	this.setNumberStudent(0);
            this.isDestroyed = true;
        }
    }

    @Override
    public void update(CityResources res) {
    	
        if (!(this.isDestroyed) && this.getLinked()){
        	
        	if (res.getUnconsumedEnergy() > this.maxNeededEnergy) { 
	    		res.consumeEnergy(maxNeededEnergy);
	    		this.isEnergyMissing = false;
	        	this.numberStudent = res.enrolStudent(this.numberStudentMax);	
	        }
	        else{
	        	this.isEnergyMissing = true;
	        	this.numberStudent = 0;
	        }  
        }
    }

    @Override
    public String[] getInformations(){
    	String[] res = new String[4];
    	res[0] = this.getClass().getSimpleName();
    	res[1] = "Students : " + this.getNumberStudent() + "/" + this.numberStudentMax;
    	res[2] = "Linked by road : " + this.getLinked();
    	res[3] = "Powered: " + !this.getIsEnergyMissing();
    	return res;
    }
    

}
