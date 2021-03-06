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
public class ResidentialTile extends BuildableTile implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constants
	/**
	 * Dimension x of the tile 
	 */
	public final static int DIMENSION_WIDTH = 2;
	
	/**
	 * Dimension y of the tile
	 */
	public final static int DIMENSION_HEIGHT = 2;
	
    /**
     * Default value of {@link ResidentialTile#getEvolutionEnergyConsumption()}
     */
    public final static int DEFAULT_EVOLUTION_ENERGY_CONSUMPTION = 5;

    /**
     * Default value of {@link ResidentialTile#maxJoiningInhabitants}
     */
    private final static int DEFAULT_MAX_JOINING_INHABITANTS = 15;

    /**
     * Default value of {@link ResidentialTile#maxLeavingInhabitants}
     */
    private final static int DEFAULT_MAX_LEAVING_INHABITANTS = 10;

    /**
     * Default value of {@link ResidentialTile#getMaxNeededEnergy()}
     */
    public final static int DEFAULT_MAX_NEEDED_ENERGY = 30;

    /**
     * Default value of {@link ResidentialTile#getInhabitantsCapacity()}
     */
    public final static int DEFAULT_INHABITANTS_CAPACITY = 40;

    // Implementation
    
    private final int maxJoiningInhabitants;

    /**
     * Maximum number of leaving inhabitants for each update.
     */
    private final int maxLeavingInhabitants;

    /**
     * {@link #getMaxNeededEnergy()}
     */
    private final int maxNeededEnergy;

    /**
     * Evolution state
     * {@link #isDestroyed()}
     */
    protected boolean isDestroyed;

    /**
     * {@link #getResidents()}
     */
    private int residents;
    
    /**
     * {@link #getIsEnergyMissing()}
     */
    private boolean isEnergyMissing;

    // Creation
    /**
     * 
     * @param topLeftCornerX - {@link #getTopLeftCornerX()}
     * @param topLeftCornerY - {@link #getTopLeftCornerY()}
     */
    public ResidentialTile(int topLeftCornerX ,int topLeftCornerY) {
        super(ResidentialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION,ResidentialTile.DEFAULT_INHABITANTS_CAPACITY, topLeftCornerX ,topLeftCornerY);

        this.maxNeededEnergy = ResidentialTile.DEFAULT_MAX_NEEDED_ENERGY;
        this.maxJoiningInhabitants = ResidentialTile.DEFAULT_MAX_JOINING_INHABITANTS;
        this.maxLeavingInhabitants = ResidentialTile.DEFAULT_MAX_LEAVING_INHABITANTS;
        this.residents = 0;
        this.isDestroyed = false;
        this.isEnergyMissing = true;
    }
    
    public ResidentialTile() {
    	this(0,0);
    }


    // Access
    
    /**
     * @return Maximum number of energy units to consume. This maximum is
     *         consumed if the residence is full.
     */
    public final int getMaxNeededEnergy() {
        return this.maxNeededEnergy;
    }

    @Override
    public int hashCode() {
    	int result=1;
        result= result * super.hashCode();
        result = result* 17 + this.residents;
        result = result* 17 + this.maxNeededEnergy;
        result = result* 17 + this.maxJoiningInhabitants;
        result = result* 17 + this.maxLeavingInhabitants;
        result = result* 17 + Boolean.hashCode(this.isDestroyed);
        return result;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof ResidentialTile && this.equals((ResidentialTile) o);
    }

    @Override
    public boolean equals(BuildingTile o1) {
    	if (o1 instanceof ResidentialTile){
    		ResidentialTile o = (ResidentialTile) o1;
    		return this == o || super.equals(o) &&  o.maxJoiningInhabitants == this.maxJoiningInhabitants
                && o.maxLeavingInhabitants == this.maxLeavingInhabitants && o.maxNeededEnergy == this.maxNeededEnergy;
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
        	if (this.state==ConstructionState.BUILT){
                res.decreasePopulation(this.residents);
                res.decreasePopulationCapacity(this.inhabitantsCapacity);
                this.residents = 0;
        	}
            super.disassemble(res);
            this.isDestroyed = true;
            this.state = ConstructionState.DESTROYED;
        }
    }

    @Override
    public void evolve(CityResources res) {
        super.evolve(res);

        if (this.state == ConstructionState.BUILT) {
            res.increasePopulationCapacity(this.inhabitantsCapacity);

            this.update(res);
        }
    }

    @Override
    public void update(CityResources res) {
        if (this.state == ConstructionState.BUILT) {
            //final int inhabitants = this.getInhabitants(res);

            final int busyPercentage = this.residents * 100 / this.inhabitantsCapacity; // Integer
                                                                                     // division
            final int neededEnergy = Math.max(1, busyPercentage * this.maxNeededEnergy / 100); // Integer
                                                                                               // division
            if (! this.getLinked()){
            	final int leavingInhabitants = Math.max(this.maxLeavingInhabitants, 20 * this.residents / 100); // Integer
                // division
            	this.residents -= leavingInhabitants;
            	res.decreasePopulation(leavingInhabitants);
            }
            
            else if (res.getUnconsumedEnergy() >= neededEnergy) {
            	final int newcomers;
                res.consumeEnergy(neededEnergy);
                this.isEnergyMissing = false;

                // Less space is available, less newcomers join
                final int vacantPercentage = 100 - busyPercentage;
                if (this.residents != this.inhabitantsCapacity){
                    newcomers = Math.max(1,vacantPercentage * this.maxJoiningInhabitants / 100);
                }
                else{
                    newcomers = vacantPercentage * this.maxJoiningInhabitants / 100;
                }

                this.residents += newcomers;
                res.increasePopulation(newcomers);
                
            } else {
                final int consumedEnergy = res.getUnconsumedEnergy();
                res.consumeEnergy(consumedEnergy);
                this.isEnergyMissing = true;

                // More energy units are missing, more inhabitants leave
                final int missingEnergyPercentage = 100 - consumedEnergy * 100 / neededEnergy; // Integer
                                                                                               // division
                final int leavingInhabitants = Math.min(this.maxLeavingInhabitants, missingEnergyPercentage * this.residents / 100); // Integer
                                                                                                                                  // division
            	this.residents -= leavingInhabitants;
                res.decreasePopulation(leavingInhabitants);
            }
        }
    }


    /**
     * @return Number of residents.
     */
    public int getResidents(){
    	return this.residents;
    }

    @Override
    public String[] getInformations(){
    	String[] res = new String[4];
    	res[0] = this.getClass().getSimpleName();
    	res[1] = "Residents : " + this.getResidents() + " / " + this.inhabitantsCapacity;
    	res[2] = "Linked by road : " + this.getLinked();
    	res[3] = "Powered : " + !this.isEnergyMissing;
    	return res;
    }


	@Override
	public boolean getIsEnergyMissing() {
		return this.isEnergyMissing;
	}

}
