package model;

public class Factor {
	
	// Constant
    /**
     * Default value for {@link Factors#getValue()}.
     */
    public final static int DEFAULT_VALUE = 50;
	
    /**
     * Default value for {@link Factors#getEvolution()}.
     */
    public final static int DEFAULT_EVOLUTION = 0;
    
	// Implementation 
	/**
     * {@link #getValue()}
     */
	private int value;
	
	/**
     * {@link #getEvolution()}
     */
	private int evolution;
	
    // Creation
    public Factor() {
        this.value = Factor.DEFAULT_VALUE;
        this.evolution = Factor.DEFAULT_EVOLUTION;
    }
	
	//Status
    /**
     * @return Value of the factor
     */
	public int getValue() {
		return this.value;
	}
	
    /**
     * @return Evolution of the factor
     */
	public int getEvolution() {
		return this.evolution;
	}
	
	//Change
	public void evolve(int impact) {
		impact = (impact+100)/2;
		assert impact >= 0 && impact <= 100;
		this.evolution = impact - this.value;
		this.value = impact;
	}
}