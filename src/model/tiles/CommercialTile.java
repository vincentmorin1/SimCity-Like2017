/**
 * TNCity
 * Copyright (c) 2017
 *  Jean-Philippe Eisenbarth,
 *  Victorien Elvinger
 *  Martine Gautier,
 *  Quentin Laporte-Chabasse
 *
 *  This file is part of TNCity.
 *
 *  TNCity is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TNCity is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with TNCity.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.tiles;

import java.io.Serializable;

import model.CityResources;

/**
 * Enable to welcome new inhabitants and consume energy units according to the
 * number of inhabitants.
 */
public class CommercialTile extends BuildableTile implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constants
	public final static int DIMENSION_WIDTH = 2;
	public final static int DIMENSION_HEIGHT = 2;

    /**
     * Default value of {@link CommercialTile#getEvolutionEnergyConsumption()}
     */
    public final static int DEFAULT_EVOLUTION_ENERGY_CONSUMPTION = 5;

    /**
     * Extra money produces for each new update. In the limit of the capacity
     * {@link #getProductionCapacity()}.
     */
    public final static int DEFAULT_MONEY_PRODUCTION = 15;

    /**
     * Products consumption for each new update. In the limit of what is available
     * {@link #getProductionCapacity()}.
     */
    public final static int DEFAULT_CONSUMPTION_CAPACITY = 10;

    /**
     * Default value of {@link CommercialTile#getMaxNeededEnergy()}
     */
    public final static int DEFAULT_MAX_NEEDED_ENERGY = 30;

    /**
     * Default value of {@link ResidentialTile#getInhabitantsCapacity()}
     */
    public final static int DEFAULT_WORKERS_CAPACITY = 40;


    // Implementation
    
    /**
     * Maximum number of newcomers for each update.
     */
    /**
     * {@link #getMaxNeededEnergy()}
     */
    private final int maxNeededEnergy;
    
    /**
     * {@link #getProduction()}
     */
    protected int moneyProduction;
    
    /**
     * {@link #getProductionCapacity()}
     */
    protected final int productionCapacity;

    /**
     * Consumption of products
     */
    protected int productsConsumption;
    /**
     * Number of workers
     */
    protected int workers;
    
    /**
     * Evolution state
     */
    protected boolean isDestroyed;

    // Creation
    /**
     * @param capacity
     *            - {@link #getProductionCapacity()}
     */
    public CommercialTile(int productionCapacity, int topLeftCornerX ,int topLeftCornerY) {
        super(CommercialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION, CommercialTile.DEFAULT_WORKERS_CAPACITY, topLeftCornerX , topLeftCornerY);     
        this.maxNeededEnergy = CommercialTile.DEFAULT_MAX_NEEDED_ENERGY;
        this.workers = 0;
        this.productsConsumption = 0;
        this.moneyProduction = 0;
        this.productionCapacity = CommercialTile.DEFAULT_CONSUMPTION_CAPACITY;
        this.isDestroyed = false;        
    }

    /**
     * Create with default settings.
     */
    public CommercialTile() {
        this(CommercialTile.DEFAULT_CONSUMPTION_CAPACITY,0,0);
    }

    // Access
    
    /**
     * @return Maximum number of energy units to consume. This maximum is
     *         consumed if the shop is full.
     */
    public final int getMaxNeededEnergy() {
        return this.maxNeededEnergy;
    }
    /**
     * @return Current production.
     */
    public int getProduction() {
        return this.moneyProduction;
    }

    /**
     * @return Maximum production.
     */
    public int getProductionCapacity() {
        return this.productionCapacity;
    }

    @Override
    public int hashCode() {
    	int result = super.hashCode();
        result = result* 17 + this.moneyProduction;
        result = result* 17 + this.productionCapacity;
        result = result* 17 + this.maxNeededEnergy;
        result = result* 17 + this.workers;
        result = result* 17 + Boolean.hashCode(this.isDestroyed);
        result = result* 17 + this.productsConsumption;
        return result;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof CommercialTile && this.equals((CommercialTile) o);
    }

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(CommercialTile o) { // A compléter!!
    	 return this == o || o.workers == this.workers && o.moneyProduction == this.moneyProduction && o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed && o.maxNeededEnergy == this.maxNeededEnergy;
    }

    @Override
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            super.disassemble(res);
            this.isDestroyed = true;
        }
    }

    @Override
    public void evolve(CityResources res) {
        super.evolve(res);

        if (this.state == ConstructionState.BUILT) {
            this.update(res);
        }
    }
    
    @Override
    public void update(CityResources res) {

        if (this.state == ConstructionState.BUILT && this.getLinked()) {
        	
        	final int neededEnergy =  this.maxNeededEnergy; 
        	int energyPercentage;
        	
        	// Si l'on a assez d'énergie
            if (res.getUnconsumedEnergy() >= neededEnergy) {
            	res.consumeEnergy(neededEnergy);
            	this.isEnergyMissing = false;
            	energyPercentage = 100;
            }
            // Sinon la production est diminuée de manière linéaire
            else {
            	final int consumedEnergy = res.getUnconsumedEnergy();
            	res.consumeEnergy(consumedEnergy);
            	this.isEnergyMissing = true;
            	energyPercentage = consumedEnergy * 100 / neededEnergy; // Integer division
            }
            

	        this.workers = Math.min(res.getUnworkingSeniorPopulation(), this.inhabitantsCapacity); 
	        final int workersPercentage = (this.workers*100 / CommercialTile.DEFAULT_WORKERS_CAPACITY);
	  
	        res.hireWorkers(this.workers);
	
	        final int productsAvailable = res.getProductsCount();
	        final int productsPercentage = Math.min(100, productsAvailable * 100 / CommercialTile.DEFAULT_CONSUMPTION_CAPACITY); 
	        
	        this.moneyProduction = productsPercentage * workersPercentage * energyPercentage * CommercialTile.DEFAULT_MONEY_PRODUCTION  / (100 * 100 * 100); // Integer division
	        this.productsConsumption = productsPercentage * workersPercentage * energyPercentage * CommercialTile.DEFAULT_CONSUMPTION_CAPACITY / (100 * 100 * 100);
	        
	        res.consumeProducts(this.productsConsumption);
	        res.increaseMoneyProduction(this.moneyProduction);
            
        }
    }
    
    public int getWorkers(){
    	return this.workers;
    }
    
    public String[] getInformations(){
    	String[] res = new String[5];
    	res[0] = this.getClass().getSimpleName();
    	res[1] = "Workers : " + this.getWorkers() + " / " + this.inhabitantsCapacity;
    	res[2] = "Production : " + this.getProduction() + " / " + this.getProductionCapacity();
    	res[3] = "Linked by road : " + this.getLinked();
    	res[4] = "Powered : " + this.isEnergyMissing;
    	return res;
    }
}
