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

package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameBoard;
import model.tiles.GrassTile;
import model.tiles.BuildingTile;
import model.tiles.RoadTile;
import model.tiles.Tile;
import model.tools.Tool;

public class TileUI extends JLabel {

    private static final long serialVersionUID = 1L;

    /**
     * Coordinate X
     */
    private int row;

    /**
     * Coordinate Y
     */
    private int column;

    private GameBoard model;
    
    /**
     * {@link #getUnderCursor()}
     */
	private boolean isUnderCursor;

	/**
	 * TileUI constructor
	 * 
	 * @param m
	 * @param row
	 * @param column
	 */
    public TileUI(GameBoard m, final int row, final int column) {
        super(" ");
        this.model = m;
        this.row = row;
        this.column = column;
		this.isUnderCursor = false;
        this.setBorder(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TileUI.this.model.effectTile(row, column);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	TileUI.this.setUnderCursor(true);
                TileUI.this.model.setSelectedTile(row, column);
            }

        });
        this.update();
    }
    
    public Tile[] tilesAround() {
	    final int posX = this.row;
	    final int posY = this.column;
	    final int sizeX = this.model.getHeight();
	    final int sizeY = this.model.getWidth();
	    
	    Tile[] tilesAround = new Tile[4];
	    for (int i=0; i<sizeX; i++) {
	    	for (int j=0; j<sizeY; j++) {
	    		if (this.model.getTile(i,j) instanceof GrassTile) {
	    			for (int k=0; k<4; k++)
	    				tilesAround[k] = this.model.getTile(i,j);
	    }	}	}
	    if (this.row > 0)
	    	tilesAround[0] = this.model.getTile(posX-1,posY);
	    if (this.row < sizeY-1)
	    	tilesAround[1] = this.model.getTile(posX+1,posY);
	    if (this.column < sizeX-1)
	    	tilesAround[2] = this.model.getTile(posX,posY+1);
	    if (this.column > 0)
	    	tilesAround[3] = this.model.getTile(posX,posY-1);
	    
	    if (posX == sizeX/2 && posY == 0)
	    	tilesAround[3] = this.model.getTile(posX,posY);
	    			
	    return tilesAround;
    }
    
    // Component refresh
    /**
     * Update
     */
    public void update() {
        final Tile elt = this.model.getTile(this.row, this.column);
        final Tool selectedTool = this.model.getSelectedTool();        
        if (selectedTool.canEffect(elt)) {
            final int cost = selectedTool.getCost(elt);
            this.setToolTipText(MessageFormat.format(this.model.getTexts().getCurrencyMsg(), cost));
        } else {
            this.setToolTipText(this.model.getTexts().getToolCannotAffectMsg());
        }

        String eltConnection = "";
        if (elt instanceof RoadTile) {
        	RoadTile rt = (RoadTile) elt;
        	eltConnection = rt.getRoadConnection(this.tilesAround());
        } else if (elt instanceof BuildingTile) {
        	BuildingTile bt = (BuildingTile) elt;
        	eltConnection = bt.getBuildingConnection(this.tilesAround());
        }
        ImageIcon ii = IconFactory.getInstance().getTileIcon(elt,eltConnection);
        this.setIcon(ii);
    }
    

    public boolean getUnderCursor() {
    	return this.isUnderCursor;
    }
    
    public void setUnderCursor(boolean v) {
    	this.isUnderCursor = v;
    }
    
    public void updateCursor() {
    	final Tile elt = this.model.getTile(this.row, this.column);
    	if (!(elt instanceof BuildingTile)) {
    		ImageIcon cursor = IconFactory.getInstance().getCursorIcon(elt);
    		this.setIcon(cursor);
    	}
    }
}
