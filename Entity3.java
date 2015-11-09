public class Entity3 extends Entity
{    
    // Perform any necessary initialization in the constructor
    public Entity3()
    {
		super();
		distanceTable[3][3] = 0;
		distanceTable[0][0] = 7;
		distanceTable[2][2] = 2;
		printDT();
		int[] minimumCost = generateMinimumCostArray();
		sendToLayer2(3 , 0 , minimumCost);
		sendToLayer2(3 , 2 , minimumCost);
		System.out.println("Table 3 initialized");
    }
    
    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p)
    {
		boolean changed = performUpdate(p);
		if(changed){
			printDT();
			int[] minimumCost = generateMinimumCostArray();
			sendToLayer2(3 , 0 , minimumCost);	
			sendToLayer2(3 , 2 , minimumCost);
			System.out.println("Table 3 updated\n");
		}

    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    }
    
    public void printDT()
    {
        System.out.println("         via");
        System.out.println(" D3 |   0   2");
        System.out.println("----+--------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 3)
            {
                continue;
            }
            
            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j += 2)
            {
               
                if (distanceTable[i][j] < 10)
                {    
                    System.out.print("   ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else 
                {
                    System.out.print(" ");
                }
                
                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}
