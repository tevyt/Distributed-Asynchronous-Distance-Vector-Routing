public abstract class Entity
{
	protected final int NOT_CONNECTED = 999;
	public Entity(){
		
		for(int to = 0; to < distanceTable.length; to++){
			for(int via = 0; via < distanceTable.length; via++){
				distanceTable[to][via] = NOT_CONNECTED;
			}
		}
	}
    // Each entity will have a distance table
    protected int[][] distanceTable = new int[NetworkSimulator.NUMENTITIES]
                                           [NetworkSimulator.NUMENTITIES];

    
    // The update function.  Will have to be written in subclasses by students
    public abstract void update(Packet p);
    
    // The link cost change handlder.  Will have to be written in appropriate
    // subclasses by students.  Note that only Entity0 and Entity1 will need
    // this, and only if extra credit is being done
    public abstract void linkCostChangeHandler(int whichLink, int newCost);

    // Print the distance table of the current entity.
    protected abstract void printDT();


	protected int[] generateMinimumCostArray(){
		int[] minimumCost = new int[4];
		for(int router = 0; router < 4; router++)
			minimumCost[router] = NOT_CONNECTED;

		for(int to = 0; to < distanceTable.length; to++){
			for(int via = 0; via < distanceTable.length; via++)
				if(distanceTable[to][via] < minimumCost[to])
					minimumCost[to] = distanceTable[to][via];
		}
		return minimumCost;
	}
	
	protected void sendToLayer2(int source , int destination , int[] minimumCostArray){
		Packet p = new Packet(source , destination , minimumCostArray);
		NetworkSimulator.toLayer2(p);
	}

	protected boolean performUpdate(Packet p){
		boolean changed = false;
		int source = p.getSource();
		for(int router = 0; router < 4; router++){
			if(distanceTable[router][source] > p.getMincost(router) + distanceTable[source][source]){
				changed = true;
				distanceTable[router][source] = p.getMincost(router) + distanceTable[source][source];
			}
		}
		return changed;
	}

	protected void printMinimumPaths(int[] paths){
		 for(int path: paths){
			 System.out.print(path + " ");
		 }
		 System.out.println();
	}
}
