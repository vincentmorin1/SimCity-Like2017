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
 * Tile that can evolve and be destroyed. An evolution has an energy cost.
 */
public abstract class BuildableTile extends Tile implements Evolvable, Destroyable {

    // Implementation
	/**
     * {@link #getInhabitantsCapacity()}
     */
    protected final int inhabitantsCapacity;

	/**
     * {@link #getEvolutionEnergyConsumption()}
     */
    private final int evolutionEnergyConsumption;

    /**
     * {@link #isEnergyMissing()}
     */
    protected boolean isEnergyMissing;

    /**
     * {@link #getState()}
     */
    protected ConstructionState state;

    /**
     * {@link #getLinked()} 
     */
    protected boolean linked;

    
    // Creation
    /**
     * Create a tile under construction.
     *
     * @param evolutionEnergyConsumption
     *            - {@link #getEvolutionEnergyConsumption()}
     */
    public BuildableTile(int evolutionEnergyConsumption, int capacity) {
        assert evolutionEnergyConsumption >= 0;

        this.evolutionEnergyConsumption = evolutionEnergyConsumption;
        this.state = ConstructionState.UNDER_CONSTRUCTION;
        this.isEnergyMissing = false;
        this.inhabitantsCapacity = capacity;
        this.linked = false; 
    }

    /**
     *  @return true if linked by road.
     */
    public boolean getLinked(){
    	return this.linked;
    }
    
    /**
     * Change la valeur de linked
     * @param b
     */
    public void setLinked(boolean b){
    	this.linked = b;
    }
    
    // Access
    /**
     * @return Maximum number of inhabitants.
     */
    public final int getInhabitantsCapacity() {
        return this.inhabitantsCapacity;
    }

    /**
     * @return Maximum number of energy units to consume. This maximum is
     *         consumed if the residence is full.
     */
    /**
     * @return Consumed energy during an evolution.
     */
    public final int getEvolutionEnergyConsumption() {
        return this.evolutionEnergyConsumption;
    }

    /**
     * @return construction's state.
     */
    public final ConstructionState getState() {
        return this.state;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.inhabitantsCapacity;
        result = result * 17 + evolutionEnergyConsumption;
        result = result * 17 + state.hashCode();
        return result;
    }

    // Status
    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(BuildableTile o) {
        return o.evolutionEnergyConsumption == evolutionEnergyConsumption && o.state == state;
    }

    // Status
    @Override
    public final boolean gotEvolutions() {
        return this.state == ConstructionState.UNDER_CONSTRUCTION;
    }

    // Status
    @Override
    public final boolean canEvolve() {
        return this.getLinked();
    }
    
    /**
     * @return Is energy missing in order to evolve or to update?
     */
    public final boolean getIsEnergyMissing() {
        return this.isEnergyMissing;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        this.state = ConstructionState.DESTROYED;
    }

    @Override
    public void evolve(CityResources res) {
        if (this.canEvolve()  && this.gotEvolutions()) {
            if (res.getUnconsumedEnergy() >= evolutionEnergyConsumption) {
                this.isEnergyMissing = false;

                res.consumeEnergy(this.evolutionEnergyConsumption);
                this.state = ConstructionState.BUILT;
                
            } else {
                this.isEnergyMissing = true;
            }
        }
    }

}
