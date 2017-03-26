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

import model.CityResources;

/**
 * Enable to welcome new inhabitants and consume energy units according to the
 * number of inhabitants.
 */
public class CommercialTile extends BuildableTile {

    // Constants
    /**
     * Default value of {@link CommercialTile#getEvolutionEnergyConsumption()}
     */
    public final static int DEFAULT_EVOLUTION_ENERGY_CONSUMPTION = 5;

    /**
     * Extra money produces for each new update. In the limit of the capacity
     * {@link #getProductionCapacity()}.
     */
    private final static int EXTRA_MONEY_PRODUCTION = 15;

    /**
     * Default value of {@link CommercialTile#getMaxNeededEnergy()}
     */
    public final static int DEFAULT_MAX_NEEDED_ENERGY = 30;

    /**
     * Default value of {@link PowerPlantTile2#getProductionCapacity()}
     */
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;
    
    /**
     * Default value of {@link ResidentialTile#getInhabitantsCapacity()}
     */
    public final static int DEFAULT_INHABITANTS_CAPACITY = 40;


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
    protected int production;

    /**
     * {@link #getProductionCapacity()}
     */
    protected final int productionCapacity;

    /**
     * Evolution state
     */
    protected boolean isDestroyed;

    // Creation
    /**
     * @param capacity
     *            - {@link #getProductionCapacity()}
     */
    public CommercialTile(int productionCapacity) {
        super(CommercialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION, DEFAULT_INHABITANTS_CAPACITY);     
        this.maxNeededEnergy = CommercialTile.DEFAULT_MAX_NEEDED_ENERGY;
        this.productionCapacity = productionCapacity;
        this.production = 0;
        this.isDestroyed = false;        
    }

    /**
     * Create with default settings.
     */
    public CommercialTile() {
        this(CommercialTile.DEFAULT_PRODUCTION_CAPACITY);
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
        return this.production;
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
        result = result * 17 + this.maxNeededEnergy;
        result = result * 17 + this.production;
        result = result * 17 + this.productionCapacity;
        result = result * 17 + Boolean.hashCode(this.isDestroyed);
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
    public boolean equals(CommercialTile o) {
    	 return this == o || o.production == this.production && o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed && o.maxNeededEnergy == this.maxNeededEnergy;
    }

    @Override
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            res.decreaseEnergyProduction(this.productionCapacity);
            this.isDestroyed = true;
        }
    }

    @Override
    public void update(CityResources res) {
        if (!this.isDestroyed) {
            // Double production
        	
        	final int busyPercentage = production * 100 / this.productionCapacity; // Integer division
        	final int neededEnergy = Math.max(1, busyPercentage * this.maxNeededEnergy / 100); // Integer division
            final int extraProduction = Math.min(CommercialTile.EXTRA_MONEY_PRODUCTION, this.productionCapacity - this.production);
            final int inhabitants = getInhabitantsCapacity();
            this.production = this.production + extraProduction;
            res.increaseEnergyProduction(extraProduction);
            if (res.getUnconsumedEnergy() >= neededEnergy) {
            	res.consumeEnergy(neededEnergy);
            	this.isEnergyMissing = false;
            }
            else {
            	final int consumedEnergy = res.getUnconsumedEnergy();
            	res.consumeEnergy(consumedEnergy);
            	this.isEnergyMissing = true;
            	// More energy units are missing, more inhabitants leave
            	final int missingEnergyPercentage = 100 - consumedEnergy * 100 / neededEnergy; // Integer
                                                                                           // division
            	final int leavingInhabitants = Math.min(this.production, missingEnergyPercentage * inhabitants / 100); // Integer
                                                                                                                              // division

            	res.decreasePopulation(leavingInhabitants);
            }
        }
    }
}