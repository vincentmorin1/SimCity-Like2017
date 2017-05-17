package model.tiles;

public class RoadConnection {
	
	
	private Tile[][] mapTiles;
	
	private int[][] mapConnection;
	
	private int heightMax;
	
	private int widthMax;
	
	private Integer[] position;
	
	private Stack<Integer[]> stack;
	
	/**
	 * RoadConnection constructor
	 * 
	 * @param tiles
	 */
	public RoadConnection(Tile[][] tiles){
		this.mapTiles = tiles;
		this.heightMax = tiles.length;
		this.widthMax = tiles[0].length;
		this.mapConnection = new int[heightMax][widthMax];
		this.stack = new Stack<Integer[]>();
		this.position = new Integer[2];
	}
	 
	/**
	 * {@link #explorate()}
	 */
	public void loadRoads(){
		position[0] = heightMax/2;
		position[1] = 0;
		stack.push(position);
		explorate();
	}
	
	/**
	 * {@link #clone()}
	 */
	private void verifAndAddStack(){
		if (mapConnection[position[0]][position[1]] == 0){
			stack.push(position.clone());
		}
	}
	
	/**
	 * {@link #verifAndAddStack()}
	 */
	private void explorate(){
		
		position = stack.pop();
		
		if (mapTiles[position[0]][position[1]] instanceof RoadTile){
			mapConnection[position[0]][position[1]] = 1;
		}
		else if (mapTiles[position[0]][position[1]] instanceof BuildableTile){
			mapConnection[position[0]][position[1]] = 2;
		}
		else {
			mapConnection[position[0]][position[1]] = -1;
		}
		
		if (position[0] != 0 && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[0] -= 1;
			verifAndAddStack();
			position[0] += 1;
		}
		if (position[0] != heightMax-1 && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[0] += 1;
			verifAndAddStack();
			position[0] -= 1;
		}
		if (position[1] != 0 && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[1] -= 1;
			verifAndAddStack();
			position[1] += 1;
		}
		if (position[1] != widthMax-1 && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[1] += 1;
			verifAndAddStack();
			position[1] -= 1;
		}
		
		if (!stack.isEmpty()){
			explorate();
		}
	}

	/**
	 * {@link #loadRoads()}
	 */
	public void setRoadLink(){
		loadRoads();
		for (int i = 0; i < heightMax; i++){
			for (int j = 0; j < widthMax; j++){
				Tile actuelTile = this.mapTiles[i][j];
				
				if (actuelTile instanceof GrassTile){
					actuelTile.setLinked(false);
				}
				
				else {
					if (this.mapConnection[i][j] ==2 | this.mapConnection[i][j] ==1){
						for (int i2=0; i2< actuelTile.getDimensionX();i2++){
							for (int j2=0; j2< actuelTile.getDimensionY();j2++){
								this.mapTiles[actuelTile.getTopLeftCornerX()+i2][actuelTile.getTopLeftCornerY()+j2].setLinked(true);
							}
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @param rt
	 * @return The position of a road tile
	 */
	private int[] getPosition(RoadTile rt){
		int[] res = new int[2];
		
		for (int i = 0; i < heightMax; i++){
			for (int j = 0; j < widthMax; j++){
				if (mapTiles[i][j] == rt){
					res[0] = i;
					res[1] = j;
				}
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @param rt
	 * @return Where are road tile around this road (West, North, Est, South)
	 */
	public String roadsAround(RoadTile rt){
		String res =  "";
		
		int height = getPosition(rt)[0];
		int width = getPosition(rt)[1];
		
		
		if (height != 0 && mapTiles[height-1][width] instanceof RoadTile){
			res += "N";
		}
		if (height != heightMax-1 && mapTiles[height+1][width] instanceof RoadTile){
			res += "S";
		}
		if (width != widthMax-1 && mapTiles[height][width+1] instanceof RoadTile){
			res += "E";
		}
		if (width != 0 && mapTiles[height][width-1] instanceof RoadTile){
			res += "W";
		}
		
		if (height == heightMax/2 && width == 0){
			if ( res == ""){
				res = "W";
			}
			else {
				res += "W";
			}
		}
		
	
		return res;
	}	
}