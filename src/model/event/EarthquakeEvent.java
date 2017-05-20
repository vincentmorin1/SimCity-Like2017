package model.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import localization.LocalizedTexts;
import model.CityResources;
import model.GameBoard;
import model.tiles.Destroyable;

public class EarthquakeEvent extends Event  {
	
	private String message;
    /**
     * Default Constructor.
     */
	public EarthquakeEvent(GameBoard world) {
        super(world);
		this.message="";
    }

    /**
     * Apply no effects.
     */
	@Override
    public List<Event> applyEffects(CityResources resources) {
		int amplitude = amplitude();

        this.message = "Earthquake! Magnitude of "+ amplitude +"  at "+this.startingTile.getRow()+ " "+this.startingTile.getColumn();
        
        for (int i= 0 ; i< this.world.getHeight(); i++){
        	for (int j= 0 ; j< this.world.getWidth(); j++){
        		
            	
        		if (this.world.getTile(i,j).getTopLeftCornerX()==i && this.world.getTile(i,j).getTopLeftCornerY()==j){
            		
            		int proba = ThreadLocalRandom.current().nextInt(0, 100);
            		
            		if (proba < destroyBuildingProba(i,j, amplitude) && this.world.getTile(i,j) instanceof Destroyable){
            			Destroyable building = (Destroyable) this.world.getTile(i,j);
            			if (!building.isDestroyed()){
                			building.disassemble(resources);
                			this.message += "\nBatiment perdu : " + building.getClass().getSimpleName();            				
            			}
            		}
            		
            	}
            }
        }
        return new ArrayList<>(0);
    }

    /**
     * Return an empty message.
     */
	@Override
    public String getMessage(LocalizedTexts texts) {
        return this.message;
    }
	
    private int destroyBuildingProba(int row, int column, int amplitude){
    	int epicentreX = this.startingTile.getRow();
    	int epicentreY = this.startingTile.getColumn();
    	
    	int distance = Math.abs(row - epicentreX) + Math.abs(column - epicentreY);
    	
    	int probaCentre = Math.min(amplitude*20, 100);
    	int proba = Math.max(0, probaCentre - distance*(11-amplitude));
    	
    	return proba;
    }

    private int amplitude(){
    	int p = ThreadLocalRandom.current().nextInt(0, 100);
    	
    	if (p<27){ return 1;}
    	else if (p<27+22){ return 2;}
    	else if (p<27+22+17){ return 3;}
    	else if (p<27+22+17+13){ return 4;}
    	else if (p<27+22+17+13+9){ return 5;}
    	else if (p<27+22+17+13+9+5){ return 6;}
    	else if (p<27+22+17+13+9+5+3){ return 7;}
    	else if (p<27+22+17+13+9+5+3+2){ return 8;}
    	else if (p<27+22+17+13+9+5+3+2+1){ return 9;}
    	else if (p<27+22+17+13+9+5+3+2+1+1){ return 10;}
    	
    	else { return -1;}
    }
	

}