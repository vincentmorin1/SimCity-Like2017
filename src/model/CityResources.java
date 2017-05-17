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

package model;


import model.tiles.RoadConnection;
import model.tiles.Tile;

/**
 * Represents the resources and the parameters of the city. An ephemeral
 * resource is reset at each step thanks to {@link CityResources#getVat()}.
 */
public class CityResources {

    // Constant
    /**
     * Default value for {@link CityResources#getVat()}.
     */
    public final static int DEFAULT_VAT = 20;

    // Implementation (Currency)
    /**
     * {@link #getCurrency()}
     */
    private int currency;

    /**
     * {@link #getVat()}
     */
    private int vat;

    // Implementation (Energy)
    /**
     * {@link #getUnconsumedEnergy()}
     */
    private int unconsumedEnergy;

    /**
     * {@link #getEnergyProduction()}
     */
    private int energyProduction;
    
    //Implementation (Commerce)
    /**
     * {@link #getMoneyProduction()}
     */
    private int moneyProduction;
    
    /**
     * {@link #getUnconsumedEnergy()}
     */
    private int unconsumedMoney;


    // Implementation (Population)
    /**
     * {@link #getUnworkingPopulation()}
     */
    private int unworkingPopulation;

    /**
     * {@link #getPopulation()}
     */
    private int population;

    /**
     * {@link #getPopulationCapacity()}
     */
    private int populationCapacity;

    // Implementation (Product)
    /**
     * {@link #getProductsCount()}
     */
    private int productsCount;

    /**
     * {@link #getProductsCapacity()}
     */
    private int productsCapacity;

    /**
     * {@link #getRoadConnection()}
     */
 	private RoadConnection roadConnection;

    /**
     * Set the connection between road tiles
     * 
     * @param tiles
     * 
     * @see RoadConnection#setRoadLink()
     * @see RoadConnection
     * @see CityResources#roadConnection
     */
    public void setRoadConnection(Tile[][] tiles){
    	roadConnection = new RoadConnection(tiles);
    	roadConnection.setRoadLink();
    }
    
    /**
     * Return the type of connection between 2 road tiles
     * 
     * @return RoadConnection
     * 
     * @see RoadConnection
     * @see CityResources#roadConnection
     */
    public RoadConnection getRoadConnection(){
    	return roadConnection;
    }

    // Creation
    
    /**
     * CityResources constructor
     *
     * @param aCurrency
     * - {@link #getCurrency()}
     * 
     * @see CityResources#vat
     * @see CityResources#unconsumedEnergy
     * @see CityResources#energyProduction
     * @see CityResources#moneyProduction
     * @see CityResources#unconsumedMoney
     * @see CityResources#unworkingPopulation
     * @see CityResources#productsCount
     * @see CityResources#productsCapacity
     */
    public CityResources(int aCurrency) {
        assert aCurrency >= 0;

        this.currency = aCurrency;
        this.vat = CityResources.DEFAULT_VAT;
        this.unconsumedEnergy = 0;
        this.energyProduction = 0;
        this.moneyProduction = 0;
        this.unconsumedMoney = 0;
        this.unworkingPopulation = 0;
        //this.population = 0;
        //this.populationCapacity = 0;
        this.productsCount = 0;
        this.productsCapacity = 0;
    }

    /**
     * CityResources constructor
     *
     * @param aCurrency
     *            - {@link #getCurrency()}
     * @param aPopulation
     *            - {@link #getPopulation()} and
     *            {@link #getPopulationCapacity()}
     */
    public CityResources(int aCurrency, int aPopulation) {
        this(aCurrency);
        assert aPopulation >= 0;

        this.population = aPopulation;
        this.populationCapacity = aPopulation;

        this.resetEphemerals();
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof CityResources && this.equals((CityResources) o);
    }

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    
    public boolean equals(CityResources o) {
        return this == o || super.equals(o) && o.currency == this.currency && o.vat == this.vat && o.unconsumedEnergy == this.unconsumedEnergy && o.energyProduction == this.energyProduction && o.moneyProduction == this.moneyProduction
                && o.unconsumedMoney == this.unconsumedMoney && o.unworkingPopulation == this.unworkingPopulation && o.population == this.population && o.populationCapacity == this.populationCapacity && o.productsCount == this.productsCount
                && o.productsCapacity == this.productsCapacity;
    }

    // Access
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.currency;
        result = result * 17 + this.vat;
        result = result * 17 + this.unconsumedEnergy;
        result = result * 17 + this.energyProduction;
        result = result * 17 + this.moneyProduction;
        result = result * 17 + this.unconsumedMoney;
        result = result * 17 + this.unworkingPopulation;
        result = result * 17 + this.population;
        result = result * 17 + this.populationCapacity;
        result = result * 17 + this.productsCount;
        result = result * 17 + this.productsCapacity;
        return result;
    }

    // Access (Currency)
    /**
     *
     * @return Accumulated currency.
     */
    public int getCurrency() {
        return this.currency;
    }

    /**
     * @return Value-Added-Tax in percentage.
     */
    public int getVat() {
        return this.vat;
    }

    // Access (Energy)
    /**
     * @return Number of consumed energy units.
     */
    public int getConsumedEnergy() {
        return this.energyProduction - this.unconsumedEnergy;
    }

    /**
     * @return Number of available energy units.
     */
    public int getUnconsumedEnergy() {
        return this.unconsumedEnergy;
    }

    /**
     *
     * @return Monthly production of energy units.
     */
    public int getEnergyProduction() {
        return this.energyProduction;
    }
    
    // Access (Money)
    /**
     * @return Monthly production of money units.
     */
    public int getMoneyProduction() {
    	return this.moneyProduction;
    }
    
    /**
     * @return Number of consumed money units.
     */
    public int getConsumedMoney() {
        return this.moneyProduction - this.unconsumedMoney;
    }
    
    /**
     * @return Number of available money units.
     */
    public int getUnconsumedMoney() {
        return this.unconsumedMoney;
    }


    // Access (Population)
    /**
     * @return Number of job-less citizens.
     */
    public int getUnworkingPopulation() {
        return this.unworkingPopulation;
    }

    /**
     * @return Number of citizens with a job.
     */
    public int getWorkingPopulation() {
        return this.population - this.unworkingPopulation;
    }

    /**
     * @return Total number of citizens.
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     *
     * @return Capacity of the city.
     */
    public int getPopulationCapacity() {
        return this.populationCapacity;
    }

    // Access (Product)
    /**
     * @return Accumulated number of products.
     */
    public int getProductsCount() {
        return this.productsCount;
    }

    /**
     * @return Maximum number of products that can be stored.
     */
    public int getProductsCapacity() {
        return this.productsCapacity;
    }

    // Change (Currency)
    /**
     * Decrease {@link #getCurrency()} by {@value amount}.
     *
     * @param amount
     */
    public void credit(int amount) {
        assert amount >= 0;

        this.currency = this.currency + amount;
    }

    /**
     * Get VAT on {@value currencyAmount} and {@link #credit(integer)} with the
     * obtained result.
     *
     * @param currencyAmount
     */
    public void creditWithTaxes(int currencyAmount) {
        assert currencyAmount >= 0;

        this.credit(currencyAmount * this.vat / 100); // Integer division
    }

    /**
     * Increase {@link #getCurrency()} by {@value amount}.
     *
     * @param amount
     */
    public void spend(int amount) {
        assert amount >= 0;

        this.currency = this.currency - amount;
    }

    // Change (Energy)
    
    /***
     * Increase {@link #getConsumedEnergy()} by {@value amount}.
     *
     * @param amount
     */
    public void consumeEnergy(int amount) {
        assert 0 <= amount && amount <= this.getUnconsumedEnergy();

        this.unconsumedEnergy = this.unconsumedEnergy - amount;
    }

    /**
     * Decrease {@link #getEnergyProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void decreaseEnergyProduction(int amount) {
        assert amount >= 0;

        this.energyProduction = Math.max(0, this.energyProduction - amount);
        this.unconsumedEnergy = Math.min(this.unconsumedEnergy, this.energyProduction);
    }

    /**
     * Increase {@link #getEnergyProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void increaseEnergyProduction(int amount) {
        assert amount >= 0;

        this.energyProduction = this.energyProduction + amount;
        this.unconsumedEnergy = this.unconsumedEnergy + amount;
    }
    
    // Change (Commerce)
    /**
     * Increase {@link #getConsumedMoney()} by {@value amount}
     * 
     * @param amount
     */
    public void consumeMoney (int amount) {
    	assert 0 <= amount && amount <= this.getUnconsumedMoney();
    	
    	this.unconsumedMoney = this.unconsumedMoney - amount;
    }
    
    /**
     * Decrease {@link #getMoneyProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void decreaseMoneyProduction(int amount) {
        assert amount >= 0;

        this.moneyProduction = Math.max(0, this.moneyProduction - amount);
        this.unconsumedMoney = Math.min(this.unconsumedMoney, this.moneyProduction);
    }
    
    /**
     * Increase {@link #getMoneyProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void increaseMoneyProduction(int amount) {
        assert amount >= 0;

        this.moneyProduction = this.moneyProduction + amount;
        this.unconsumedMoney = this.unconsumedMoney + amount;
    }

    // Change (Population)
    /**
     * Increase {@link #getWorkingPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void hireWorkers(int amount) {
        assert 0 <= amount && amount <= this.getUnworkingPopulation();

        this.unworkingPopulation = this.unworkingPopulation - amount;
    }

    /**
     * Increase {@link #getPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void increasePopulation(int amount) {
        assert amount >= 0;

        final int joiningPopulation = Math.min(this.populationCapacity - this.population, amount);
        this.population = this.population + joiningPopulation;
        this.unworkingPopulation = this.unworkingPopulation + joiningPopulation;
    }

    /**
     * Decrease {@link #getPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void decreasePopulation(int amount) {
        assert amount >= 0;

        this.population = Math.max(0, this.population - amount);
        this.unworkingPopulation = Math.min(this.unworkingPopulation, this.population);
    }

    /**
     * Increase {@link #getPopulationCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void increasePopulationCapacity(int amount) {
        assert amount >= 0;

        this.populationCapacity = this.populationCapacity + amount;
    }

    /**
     * Decrease {@link #getPopulationCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void decreasePopulationCapacity(int amount) {
        assert 0 <= amount && amount <= this.getPopulationCapacity();

        this.populationCapacity = this.populationCapacity - amount;
        this.population = Math.min(this.population, this.populationCapacity);
    }

    // Change (Product)
    /**
     * Decrease {@link #getProductsCount()} by {@value amount}.
     *
     * @param amount
     */
    public void consumeProducts(int amount) {
        assert amount >= 0;

        this.productsCount = Math.max(0, this.productsCount - amount);
    }

    /**
     * Increase {@link #getProductsCount()} by {@value amount}.
     *
     * @param amount
     */
    public void storeProducts(int amount) {
        assert amount >= 0;

        this.productsCount = Math.min(this.productsCapacity, this.productsCount + amount);
    }

    /**
     * Decrease {@link #getProductsCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void decreaseProductsCapacity(int amount) {
        assert 0 <= amount && amount <= this.getProductsCapacity();

        this.productsCapacity = this.productsCapacity - amount;
        this.productsCount = Math.min(this.productsCount, this.productsCapacity);
    }

    /**
     * Increase {@link #getProductsCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void increaseProductsCapacity(int amount) {
        assert amount >= 0;

        this.productsCapacity = this.productsCapacity + amount;
    }

    // Reset
    /**
     * Reset ephemeral resources.
     */
    public void resetEphemerals() {
        this.unworkingPopulation = this.population;
        this.unconsumedEnergy = this.energyProduction;
        this.unconsumedMoney = this.moneyProduction;
    }

}
