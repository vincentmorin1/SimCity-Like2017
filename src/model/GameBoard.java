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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import localization.LocalizedTexts;
import model.difficulty.DifficultyLevel;
import model.event.Event;
import model.event.EventFactory;
import model.tiles.Evolvable;
import model.tiles.GrassTile;
import model.tiles.MountainTile;
import model.tiles.RoadTile;
import model.tiles.Tile;
import model.tiles.WaterTile;
import model.tools.BeachConstructionTool;
import model.tools.BulldozerTool;
import model.tools.CommercialZoneDelimiterTool;
import model.tools.HospitalConstructionTool;
import model.tools.PowerPlantConstructionTool;
import model.tools.ResidentialZoneDelimiterTool;
import model.tools.RoadConstructionTool;
import model.tools.SchoolConstructionTool;
import model.tools.SnowStationConstructionTool;
import model.tools.IndustrialZoneDelimiterTool;
import model.tools.PoliceOfficeConstructionTool;
import model.tools.Tool;

public class GameBoard extends Observable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constant
    public final static DifficultyLevel DEFAULT_DIFFICULTY = DifficultyLevel.STANDARD_LEVEL;

    public final static TilePosition DEFAULT_SELECTED_LOCATION = new TilePosition(0, 0);

    public final static int DEFAULT_SELECTED_TOOL = 0;

    public final static int MAX_HANDLED_EVOLUTIONS = 5;

    public final static String NOTHING_MESSAGE = "";

    public final static AtomicInteger ROUNDCOUNTER = new AtomicInteger(0);

    // Implementation
    /**
     * Map of the world.
     */
    private Tile[][] tiles;

    /**
     * Available tools.
     */
    private List<Tool> tools;

    /**
     * {@link #getSelectedTool()}
     */
    private Tool selectedTool;

    /**
     * {@link #getSelectedTile()}
     */
    private Tile selectedTile;

    /**
     * Pending evolutions.
     */
    private Queue<Evolvable> pendingEvolutions;

    /**
     * Events to be applied to the world at the next refresh.
     */
    private List<Event> pendingEventsList;

    /**
     * Available money.
     */
    private CityResources resources;

    /**
     * Status message.
     */
    private String message;

        
    /**
     * {@link #getTexts()}
     */
    private LocalizedTexts texts;

    private int numberWaterCase;
    private int numberMountainCase;
    private int numberWaterCaseMax;
    private int numberMountainCaseMax;

    // Creation
    /**
     * Create a rectangle world with {@value height * width} tiles.
     *
     * @param height
     *            - {@link #getHeight()}
     * @param width
     *            - {@link #getWidth()}
     * @param difficulty
     *            - Game difficulty level.
     * @param texts
     *            - {@link #getTexts()}
     */
    public GameBoard(int height, int width, DifficultyLevel difficulty, LocalizedTexts texts) {
        assert width > 0 && height > 0 : "Dimensions incorrectes";

        this.numberMountainCase = 0;
        this.numberWaterCase = 0;
        this.numberWaterCaseMax = 60;
        this.numberMountainCaseMax = 60;
        
        this.tiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[i][j] = this.createMap(i,j);//GrassTile.getDefault();
            }
        }
        this.tiles[height/2][0] = new RoadTile(height/2,0);

        this.selectedTile = this.getTile(GameBoard.DEFAULT_SELECTED_LOCATION.getRow(), GameBoard.DEFAULT_SELECTED_LOCATION.getColumn());


        
        this.tools=new ArrayList<>();
        this.tools.add(new BulldozerTool());
        this.tools.add(new PowerPlantConstructionTool());
        this.tools.add(new ResidentialZoneDelimiterTool());
        this.tools.add(new CommercialZoneDelimiterTool());
        this.tools.add(new IndustrialZoneDelimiterTool());
        this.tools.add(new RoadConstructionTool());
        this.tools.add(new SchoolConstructionTool());
        this.tools.add(new HospitalConstructionTool());
        this.tools.add(new PoliceOfficeConstructionTool());
        this.tools.add(new BeachConstructionTool());
        this.tools.add(new SnowStationConstructionTool());

        this.selectedTool = this.tools.get(GameBoard.DEFAULT_SELECTED_TOOL);

        this.pendingEvolutions = new LinkedList<>();
        this.pendingEventsList = new LinkedList<>();
        this.resources = new CityResources(difficulty.getInitialCurrency());
      

        this.message = GameBoard.NOTHING_MESSAGE;
        this.texts = texts;
    }

    /**
     * Create a rectangle world with {@value height * width} tiles.
     *
     * @param height
     *            - {@link #getHeight()}
     * @param width
     *            - {@link #getWidth()}
     * @param texts
     *            - {@link #getTexts()}
     */
    public GameBoard(int height, int width, LocalizedTexts texts) {
        this(height, width, GameBoard.DEFAULT_DIFFICULTY, texts);
    }
    
    public GameBoard(String path, LocalizedTexts texts){
    	GameBoard world = null;
        
        try {
            FileInputStream fileinputstream = new FileInputStream(path);
            BufferedInputStream bufferedinputstream = new BufferedInputStream(fileinputstream);
            ObjectInputStream objectinputstream = new ObjectInputStream(bufferedinputstream);
        	try {
            	world = (GameBoard) objectinputstream.readObject();
        	}finally{
                objectinputstream.close();
                fileinputstream.close();
        	
            }
        }catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        if (world==null){
        	try {
				throw new Exception("Can't load file");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        else{
        	this.tiles = world.tiles;
        	this.tools = world.tools;
        	this.selectedTool = this.tools.get(GameBoard.DEFAULT_SELECTED_TOOL);
        	

            this.numberMountainCase = world.numberMountainCase;
            this.numberWaterCase = world.numberWaterCase;
            
            
            this.selectedTile = this.getTile(GameBoard.DEFAULT_SELECTED_LOCATION.getRow(), GameBoard.DEFAULT_SELECTED_LOCATION.getColumn());
            this.pendingEvolutions = world.pendingEvolutions;
            this.pendingEventsList = world.pendingEventsList;
            this.resources = world.resources;
                   
            this.message = GameBoard.NOTHING_MESSAGE;
            this.texts = world.texts;
        }
    }

   
    /**
     * Create a square world with {@value length * length} tiles.
     *
     * @param length
     *            - {@link #getWidth()} and {@link #getHeight()}
     * @param texts
     *            - {@link #getTexts()}
     */
    public GameBoard(int length, LocalizedTexts texts) {
        this(length, length, texts);
    }

    // Access
    public LocalizedTexts getTexts() {
        return this.texts;
    }

    // Access (World)
    /**
     * @return Height of the world in row count.
     */
    public int getHeight() {
        return this.tiles.length;
    }

    /**
     * @return Width of the world in column count.
     */
    public int getWidth() {
        return this.tiles[0].length;
    }

    /**
     * @param row
     *            - Row number
     * @param column
     *            - Column number
     * @return Cell with at location ({@value row}, {@value column}).
     * @exception AssertionError
     *                if {@value row} or {@value column} is invalid.
     */
    public Tile getTile(int row, int column) {
        assert row >= 0 && row < this.getHeight() && column >= 0 && column < this.getWidth() : "Ligne/Colonne incorrecte";
        return this.tiles[row][column];
    }

    // Access (Tool)
    /**
     * @return Number of available tools.
     */
    public int getToolCount() {
        return this.tools.size();
    }

    /**
     * @return Tools' iterator of available tools.
     */
    public Iterator<Tool> toolIterator() {
        return this.tools.iterator();
    }

    // Access (Selection)
    /**
     * @return Selected tile.
     */
    public Tile getSelectedTile() {
        return this.selectedTile;
    }
    
    /**
     * @return Selected tool.
     */
    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    // Access (City Resources)
    
    /**
     * @return Current Currency
     */
    public int getCurrency() {
        return this.resources.getCurrency();
    }
    
    /**
     * @return The resources and the parameters of the city
     */
    public CityResources getCityResources(){
    	return this.resources;
    }
    
    public int getSeniorPopulation(){
    	return this.resources.getSeniorPopulation();
    }

    /**
     * @return The number of job-less citizens 
     */
    public int getUnworkingSeniorPopulation() {
        return this.resources.getUnworkingSeniorPopulation();
    }
    
    public int getStudentPopulation(){
    	return this.resources.getStudentPopulation();
    }
    /**
     * @return The number of unstudying student
     */
    public int getUnworkingStudentPopulation() {
        return this.resources.getUnworkingStudentPopulation();
    }

    /**
     * 
     * @return Value of happiness in game, in percentage
     */
    public int getHappiness() {
		return this.resources.getHappiness();
	}

    /**
     * 
     * @return Value of current efficiency at work, in percentage
     */
	public int getEfficiencyAtWork() {
		return this.resources.getEfficiencyAtWork();
	}

	/**
	 * 
	 * @return Value of current economy in game, in percentage
	 */
	public int getEconomy() {
		return this.resources.getEconomy();
	}
	
    /**
     * 
     * @return The number of available energy units
     */
    public int getEnergy() {
        return this.resources.getUnconsumedEnergy();
    }
    
    /**
     * 
     * @return The number of available money units
     */
    public int getMoney() {
    	return this.resources.getUnconsumedMoney();
    }

    /**
     * 
     * @return The accumulated number of products
     */
    public int getProducts() {
        return this.resources.getProductsCount();
    }

    // Access (Status)
    /**
     * @return Status message.
     */
    public String getMessage() {
        return this.message;
    }

    // Change (Selection)
    /**
     *
     * @param tool
     *            - {@link #getSelectedTool()}.
     */
    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
        this.notifyViews();
    }

    /**
     * Select the tile at location ({@value row}, {@value column}).
     *
     * @param row
     * @param column
     */
    public void setSelectedTile(int row, int column) {
        this.selectedTile = this.getTile(row, column);
        this.notifyViews();
    }

    /**
     * Return a set of TilePosition defining a square created from the given
     * <code>startingTile</code> and the <code>areaSize</code>.
     *
     * @param startingTile
     * @param areaSize
     * @return Set of TilePosition
     */
    public Set<TilePosition> getTilesArea(TilePosition startingTile, int areaSize) {
        Set<TilePosition> tilesArea = new HashSet<>();

        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                int newRow = startingTile.getRow() + i < this.getHeight() ? startingTile.getRow() + i : this.getHeight() - 1;
                int newColumn = startingTile.getColumn() + j < this.getWidth() ? startingTile.getColumn() + j : this.getWidth() - 1;
                TilePosition newTile = new TilePosition(newRow, newColumn);
                tilesArea.add(newTile);
            }
        }
        return tilesArea;
    }

    // Change (World)
    /**
     * Effect the tile at location {@value row}, {@value column}) with
     * {@link #getSelectedTool()} if possible.
     *
     * @param row
     * @param column
     */
    public void effectTile(int row, int column) {
        assert row >= 0 && row < this.getHeight() && column >= 0 && column < this.getWidth() : "Ligne/Colonne incorrecte";

        int sizeX = this.selectedTool.getDimensionX();
        int sizeY = this.selectedTool.getDimensionY();
        int maxX = this.tiles.length;
        int maxY= this.tiles[0].length;
        
        boolean canEffect =  true;
        
        final Tile currentTile = this.tiles[row][column];

        
        if (this.selectedTool.getClass().getSimpleName().equals(BulldozerTool.class.getSimpleName())){
        	sizeX = this.tiles[row][column].getDimensionX(); // Faudra swap les x et les y
        	sizeY = this.tiles[row][column].getDimensionY();
      		row = this.tiles[row][column].getTopLeftCornerX();
    		column = this.tiles[row][column].getTopLeftCornerY();
        }
        
        if (row + sizeX -1 <maxX && column + sizeY-1 < maxY){
	        for (int i=0; i<sizeX;i++){
	        	for (int j=0; j<sizeY;j++){
	        		canEffect = canEffect && this.selectedTool.canEffect(this.tiles[row+i][column+j]);
	        	}
	        }
        }
        else{
        	canEffect = false;
        }
		
        if (this.selectedTool.getClass().getSimpleName().equals(BeachConstructionTool.class.getSimpleName())){
        	BeachConstructionTool bct = (BeachConstructionTool) this.selectedTool;
        	canEffect = canEffect && bct.nextToWaterTile(row,column,this.tiles) ;
        }

        if (canEffect) {
            if (this.selectedTool.isAfordable(currentTile, this.resources)) {

                final Tile newTile = this.selectedTool.effect(currentTile, this.resources, row, column);
                
                for (int i=0; i<sizeX;i++){
                	for (int j=0; j<sizeY;j++){
                        this.tiles[row+i][column+j] = newTile;
                	}
                }

                this.pendingEvolutions.remove(currentTile);
                if (newTile instanceof Evolvable) {
                    this.pendingEvolutions.add((Evolvable) newTile);
                }
            } else {
                this.message = this.texts.getMissingResourcesMsg();
            }
        } 
        else {
            this.message = this.texts.getToolCannotAffectMsg();
        }

        this.notifyViews();
    }

    /**
     * Compute the next world state.
     */
    public void nextState() {
        GameBoard.ROUNDCOUNTER.incrementAndGet();
        this.resources.resetEphemerals();
        this.applyPendingEvents();
        this.applyNewEvent();
        this.updateTiles();
        this.applyEvolutions();
        this.notifyViews();
    }

    /**
     * Applies the effects of all the pending events (resulting from the
     * previous one).
     */
    private void applyPendingEvents() {
        List<Event> entry;
        for (Event event : this.pendingEventsList) {
            entry = event.applyEffects(this.resources);
            this.pendingEventsList.addAll(entry);
        }
    }

    /**
     * Generates a new event and applies its effects.
     */
    private void applyNewEvent() {
        Event event = EventFactory.generateEvent(this);
        List<Event> resultingEvents = event.applyEffects(this.resources);
        assert resultingEvents != null;
        String eventMessage = event.getMessage(this.texts);
        assert eventMessage != null : "The event message must not be null.";
        this.message = eventMessage.isEmpty() ? GameBoard.NOTHING_MESSAGE : eventMessage;
        this.pendingEventsList.addAll(resultingEvents);
    }

    // Implementation (Notification)
    /**
     * Notify view of a state change.
     */
    private void notifyViews() {
        this.setChanged();
        this.notifyObservers();
        this.message = GameBoard.NOTHING_MESSAGE;
    }

    /**
     * Apply evolutions in the order where it was registered.
     */
    private void applyEvolutions() {
        final int count = Math.min(GameBoard.MAX_HANDLED_EVOLUTIONS, this.pendingEvolutions.size());

        for (int i = 0; i < count; i++) {
            final Evolvable e = this.pendingEvolutions.poll(); // Not null

            e.evolve(this.resources);

            if (e.gotEvolutions()) {
            	this.pendingEvolutions.add(e);
            }
        }
    }

    /**
     * Update all tiles via {@link Tile#update(CityResources)}.
     */
    private void updateTiles() {
    	
        for (int i=0; i< this.tiles.length;i++){
        	for (int j=0; j< this.tiles[0].length; j++){
        		if (this.tiles[i][j].getTopLeftCornerX() == i && this.tiles[i][j].getTopLeftCornerY() == j){
        			this.tiles[i][j].update(this.resources);
        		}
        	}
        }
        
    }
    
    /**
     * 
     * @param i
     * @param j
     * @return
     */
    public Tile createMap(int i, int j){
		int proba = ThreadLocalRandom.current().nextInt(0, 100);
		if (i!=0 && j!=0 && (this.tiles[i-1][j] instanceof WaterTile || this.tiles[i][j-1] instanceof WaterTile) && proba<60 && this.numberWaterCase<this.numberWaterCaseMax){
			this.numberWaterCase++;
			return WaterTile.getDefault();
		}
		else if (i!=0 && j!=0 && (this.tiles[i-1][j] instanceof MountainTile || this.tiles[i][j-1] instanceof MountainTile) && proba<60 && this.numberMountainCase<this.numberMountainCaseMax){
			this.numberMountainCase++;
			return MountainTile.getDefault();
		}
		else if (proba < 1 && this.numberWaterCase<this.numberWaterCaseMax){ 
			this.numberWaterCase++;
			return WaterTile.getDefault();
		}
		else if (proba < 2 && this.numberMountainCase<this.numberMountainCaseMax){ 
			this.numberMountainCase++;
			return MountainTile.getDefault();
		}
		else{ return GrassTile.getDefault(); }
    }

}
