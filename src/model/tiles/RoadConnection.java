package model.tiles;

public class RoadConnection {
	
	private Tile[][] mapTiles;
	private int[][] mapConnection;
	private int heightMax;
	private int widthMax;
	private Integer[] position;
	private Stack<Integer[]> stack;
	
	public RoadConnection(Tile[][] tiles){
		this.mapTiles = tiles;
		this.heightMax = tiles.length;
		this.widthMax = tiles[0].length;
		this.mapConnection = new int[heightMax][widthMax];
		this.stack = new Stack<Integer[]>();
		this.position = new Integer[2];
	}
	
	public void loadRoads(){
		position[0] = heightMax/2;
		position[1] = 0;
		stack.push(position);
		explorate();
	}
	
	private void verifAndAddStack(){
		if (mapConnection[position[0]][position[1]] == 0){
			stack.push(position.clone());
		}
	}
	
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
		if (position[0] != widthMax && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[0] += 1;
			verifAndAddStack();
			position[0] -= 1;
		}
		if (position[1] != 0 && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[1] -= 1;
			verifAndAddStack();
			position[1] += 1;
		}
		if (position[1] != heightMax && mapTiles[position[0]][position[1]] instanceof RoadTile){
			position[1] += 1;
			verifAndAddStack();
			position[1] -= 1;
		}
		
		if (!stack.isEmpty()){
			explorate();
		}
	}

	public void setRoadLink(){
		loadRoads();
		for (int i = 0; i < heightMax; i++){
			for (int j = 0; j < widthMax; j++){
				if (this.mapConnection[i][j] ==2 | this.mapConnection[i][j] ==1){
					this.mapTiles[i][j].setLinked(true);
				}
			}
		}
	}

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
	
	public String roadsAround(RoadTile rt){
		String res =  "";
		
		int height = getPosition(rt)[0];
		int width = getPosition(rt)[1];
		
		
		if (height != 0 && mapTiles[height-1][width] instanceof RoadTile){
			res += "N";
		}
		if (height != heightMax && mapTiles[height+1][width] instanceof RoadTile){
			res += "S";
		}
		if (width != widthMax && mapTiles[height][width+1] instanceof RoadTile){
			res += "E";
		}
		if (width != 0 && mapTiles[height][width-1] instanceof RoadTile){
			res += "W";
		}
		
		if (height == heightMax/2 && width == 0){
			if ( res == ""){
				res = "EW";
			}
			else {
				res += "W";
			}
		}
		return res;
	}
	/**
	public static void main(String[] args){
       Tile[][] tiles = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tiles[i][j] = GrassTile.getDefault();
            }
        }
        tiles[0][2] = new RoadTile();
        tiles[0][1] = new RoadTile();
        tiles[0][0] = new RoadTile();
        tiles[1][0] = new ResidentialTile();
        
        RoadConnection r = new RoadConnection(tiles);
        r.setRoadLink();
        
        BuildableTile bt = (BuildableTile) r.mapTiles[1][0];
        System.out.println(bt.getLinked());
        
        
	}
	*/
	
}
