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
 * Tile that can evolve and be destroyed. An evolution has an energy cost.
 */
public abstract class BuildableTile extends BuildingTile implements Evolvable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Implementation
	/**
     * {@link #getInhabitantsCapacity()}
     */
    protected final int inhabitantsCapacity;

    /**
     * {@link #getTopLeftCornerX()}
     */
    private final int topLeftCornerX;
    
    /**
     * {@link #getTopLeftCornerY()}
     */
    private final int topLeftCornerY;
    
	/**
     * {@link #getEvolutionEnergyConsumption()}
     */
    private final int evolutionEnergyConsumption;

    /**
     * {@link #isEnergyMissing()}
     */
    protected boolean isEnergyMissingEvolution;

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
     * @param capacity
     *            - {@link #getProductionCapacity()}
     *            - {@link #getInhabitantsCapacity()}
     *            - {@link #getTopLeftCornerX()}
     *            - {@link #getTopLeftCornerY()}
     */
    public BuildableTile(int evolutionEnergyConsumption, int capacity,int topLeftCornerX ,int topLeftCornerY) {
        assert evolutionEnergyConsumption >= 0;

        this.evolutionEnergyConsumption = evolutionEnergyConsumption;
        this.state = ConstructionState.UNDER_CONSTRUCTION;
        this.isEnergyMissingEvolution = false;
        this.inhabitantsCapacity = capacity;
        this.linked = false;
        this.topLeftCornerX = topLeftCornerX;
        this.topLeftCornerY = topLeftCornerY;
    }


    @Override
    public boolean getLinked(){
    	return this.linked;
    }
    

    @Override
    public void setLinked(boolean b){
    	this.linked = b;
    }
    
    @Override
	public int getDimensionX(){
		return 2;
	}

	@Override
	public int getDimensionY(){
		return 2;
	}

	@Override
	public int getTopLeftCornerX(){
		return this.topLeftCornerX;
	}
	
	@Override
	public int getTopLeftCornerY(){
		return this.topLeftCornerY;
	}
	
    // Access
    /**
     * @return Maximum number of inhabitants.
     */
    public final int getInhabitantsCapacity() {
        return this.inhabitantsCapacity;
    }

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
        result = result* 17 + this.topLeftCornerX;
        result = result* 17 + this.topLeftCornerY;
        result = result* 17 + this.inhabitantsCapacity;
        result = result* 17 + this.evolutionEnergyConsumption;
        result = result* 17 + this.state.hashCode();
        result = result* 17 + Boolean.hashCode(this.linked);
        result = result* 17 + Boolean.hashCode(this.isEnergyMissingEvolution);
        return result;
    }

    @Override
    public boolean equals(BuildingTile o) {
    	BuildableTile bt = (BuildableTile) o;
        return bt.evolutionEnergyConsumption == evolutionEnergyConsumption && bt.state == state;
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
    
    // Change
    @Override
    public void disassemble(CityResources res) {
        this.state = ConstructionState.DESTROYED;
    }

    @Override
    public void evolve(CityResources res) {
        if (this.canEvolve()  && this.gotEvolutions()) {
            if (res.getUnconsumedEnergy() >= evolutionEnergyConsumption) {
                this.isEnergyMissingEvolution = false;
                res.consumeEnergy(this.evolutionEnergyConsumption);
                this.state = ConstructionState.BUILT;
                
            } else {
                this.isEnergyMissingEvolution = true;
            }
        }
    }

}
