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


import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Represents the resources and the parameters of the city. An ephemeral
 * resource is reset at each step thanks to {@link CityResources#getVat()}.
 */
public class CityResources implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private int unworkingSeniorPopulation;

    private int seniorPopulation;

    
    private int numberStudentWithoutLeisure;
    
    private int numberSeniorWithoutLeisure;

    /**
     * {@link #getPopulation()}
     */
    private int population;

    /**
     * {@link #getPopulationCapacity()}
     */
    private int populationCapacity;

    /**
     * {@link #getNumberStudent()}
     */
    private int studentPopulation;
    
    private int unworkingStudentPopulation;
    
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
     * {@link #getNbBeaches()}
     */
    private int nbBeaches;

    /**
     * {@link #getNbCommercials()}
     */
    private int nbCommercials;
    
    /**
     * {@link #getNbHospitals()}
     */
    private int nbHospitals;
    
    /**
     * {@link #getNbIndustrials()}
     */
    private int nbIndustrials;
    
    /**
     * {@link #getNbPoliceOffices()}
     */
    private int nbPoliceOffices;
    
    /**
     * {@link #getNbPowerPlants()}
     */
    private int nbPowerPlants;
    
    /**
     * {@link #getNbResidentials()}
     */
    private int nbResidentials;
    
    /**
     * {@link #getNbSchools()}
     */
    private int nbSchools;
    
    /**
     * {@link #getNbSnowStations()}
     */
    private int nbSnowStations;
    

    // Creation
    
    /**
     * CityResources constructor
     *
     * @param aCurrency
     * - {@link #getCurrency()}
     */
    public CityResources(int aCurrency) {
        assert aCurrency >= 0;

        this.currency = aCurrency;
        this.vat = CityResources.DEFAULT_VAT;
        this.unconsumedEnergy = 0;
        this.energyProduction = 0;
        this.moneyProduction = 0;
        this.unconsumedMoney = 0;
        this.unworkingSeniorPopulation = 0;
        this.unworkingStudentPopulation = 0;
        this.population = 0;
        this.seniorPopulation = 0;
        this.studentPopulation = 0;
        this.numberStudentWithoutLeisure=0;
        this.numberSeniorWithoutLeisure=0;
        this.populationCapacity = 0;
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
                && o.unconsumedMoney == this.unconsumedMoney && o.unworkingSeniorPopulation == this.unworkingSeniorPopulation && o.population == this.population && o.populationCapacity == this.populationCapacity && o.productsCount == this.productsCount
                && o.productsCapacity == this.productsCapacity;
    }

    // Access
    @Override
    public int hashCode() {
    	int result = 1;
        result = result* 17 + this.currency;
        result = result* 17 + this.vat;
        result = result* 17 + this.energyProduction;
        result = result* 17 + this.unconsumedEnergy;
        result = result* 17 + this.moneyProduction;
        result = result* 17 + this.unconsumedMoney;
        result = result* 17 + this.unworkingStudentPopulation;
        result = result* 17 + this.unworkingSeniorPopulation;
        result = result* 17 + this.studentPopulation;
        result = result* 17 + this.seniorPopulation;
        result = result* 17 + this.numberStudentWithoutLeisure;
        result = result* 17 + this.numberSeniorWithoutLeisure;
        result = result* 17 + this.population;
        result = result* 17 + this.populationCapacity;
        result = result* 17 + this.productsCount;
        result = result* 17 + this.productsCapacity;
        return result;
    }

    
    public int enrolStudent(int maxEnrol){
    	int res =  Math.min(maxEnrol, this.unworkingStudentPopulation);
    	this.unworkingStudentPopulation -= res;
    	return res;
    }
    
    // Access (Currency)
    /**
     *
     * @return Accumulated currency.
     */
    public int getCurrency() {
        return this.currency;
    }
    public void setCurrency(int c){
    	this.currency = c;
    }
    /**
     * @return Value-Added-Tax in percentage.
     */
    public int getVat() {
        return this.vat;
    }
    public void setVat(int v){
    	this.vat = v;
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

    public int getStudentPopulation(){
    	return this.studentPopulation;
    }
    
    public int getUnworkingStudentPopulation(){
    	return this.unworkingStudentPopulation;
    }

    // Access (Population)
    /**
     * @return Number of job-less citizens.
     */
    public int getUnworkingSeniorPopulation() {
        return this.unworkingSeniorPopulation;
    }

    /**
     * @return Number of citizens with a job.
     */
    public int getWorkingSeniorPopulation() {
        return this.population - this.unworkingSeniorPopulation;
    }

    public int getSeniorPopulation(){
    	return this.seniorPopulation;
    }

    public int getNumberStudentWithoutLeisure(){
    	return this.numberStudentWithoutLeisure;
    }
    
    public int getNumberSeniorWithoutLeisure(){
    	return this.numberSeniorWithoutLeisure;
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
    public int getNbBeaches(){
    	return this.nbBeaches;
    }
    public int getNbCommercials(){
		return this.nbCommercials;
    }
    public int getNbHospitals(){
		return this.nbHospitals;
    }
    public int getNbIndustrials(){
    	return this.nbIndustrials;
    }	
    public int getNbPoliceOffices(){
		return this.nbPoliceOffices;
    }	
    public int getNbPowerPlants(){
		return this.nbPowerPlants;
    }	
    public int getNbResidentials(){
		return this.nbResidentials;
    }	
    public int getNbSchools(){
		return this.nbSchools;
    }	
    public int getNbSnowStations(){
		return this.nbSnowStations;
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
        assert 0 <= amount && amount <= this.getUnworkingSeniorPopulation();

        this.unworkingSeniorPopulation = this.unworkingSeniorPopulation - amount;
    }

    /**
     * Increase {@link #getPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void increasePopulation(int amount) {
        assert amount >= 0;

        final int joiningPopulation = Math.min(this.populationCapacity - this.population, amount);
        
        this.studentPopulation += joiningPopulation/2;
        this.unworkingStudentPopulation += joiningPopulation/2;

        this.seniorPopulation += (joiningPopulation+1)/2;
        this.unworkingSeniorPopulation = this.unworkingSeniorPopulation + (joiningPopulation+1)/2;
        
        this.population += joiningPopulation;

    }

    /**
     * Decrease {@link #getPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void decreasePopulation(int amount) {
        assert amount >= 0;

        this.studentPopulation = Math.max(0, this.studentPopulation - amount/2);
        this.unworkingStudentPopulation = Math.min(this.unworkingStudentPopulation, this.studentPopulation);
        
        this.seniorPopulation = Math.max(0, this.seniorPopulation - (amount+1)/2);
        this.unworkingSeniorPopulation = Math.min(this.unworkingSeniorPopulation, this.seniorPopulation);
        
        this.population = Math.max(0, this.population - amount);
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
        
        int sdf = Math.max(this.population - (this.populationCapacity - amount),0);
        this.decreasePopulation(sdf);
        
        this.populationCapacity = this.populationCapacity - amount;
    }

    public int peopleToLeisure(int maxAmount, int probaStudent, int probaSenior){
    	int res = 0;
    	int i=0;
    	while (i < this.numberStudentWithoutLeisure && res < maxAmount){
    		if (ThreadLocalRandom.current().nextInt(0, 100) < probaStudent){
    			res ++;
    			this.numberStudentWithoutLeisure --;
    		}
    		i++;
    	}
    	
    	i=0;
    	
    	while (i < this.numberSeniorWithoutLeisure && res < maxAmount){
    		if (ThreadLocalRandom.current().nextInt(0, 100) < probaSenior){
    			res ++;
    			this.numberSeniorWithoutLeisure --;
    		}
    		i++;
    	}
    	return res;
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

    public void beachesCount() {
    	nbBeaches += 1;
    }
    public void commercialsCount() {
    	nbCommercials += 1;
    }
    public void hospitalsCount() {
    	nbHospitals += 1;
    }
    public void industrialsCount() {
    	nbIndustrials += 1;
    }
    public void policeOfficesCount() {
    	nbPoliceOffices += 1;
    }
    public void powerPlantsCount() {
    	nbPowerPlants += 1;
    }
    public void residentialsCount() {
    	nbResidentials += 1;
    }
    public void schoolsCount() {
    	nbSchools += 1;
    }
    public void snowStationsCount() {
    	nbSnowStations += 1;
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
    	this.creditWithTaxes(this.unconsumedMoney); ;
    	
    	this.studentPopulation = this.population/2;
    	this.seniorPopulation = this.population - this.studentPopulation;
        this.unworkingSeniorPopulation = this.seniorPopulation;
        this.unworkingStudentPopulation = this.studentPopulation;
        this.numberStudentWithoutLeisure = this.studentPopulation;
        this.numberSeniorWithoutLeisure = this.seniorPopulation;
        
        this.unconsumedEnergy = this.energyProduction;
        this.unconsumedMoney = this.moneyProduction;
    }


}
