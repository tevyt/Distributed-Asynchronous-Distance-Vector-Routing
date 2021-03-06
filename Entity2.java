public class Entity2 extends Entity
{    
    // Perform any necessary initialization in the constructor
    public Entity2()
    {

	    super();
        distanceTable[2][2] = 0;
		distanceTable[1][1] = 1;
		distanceTable[0][0] = 3;
		distanceTable[3][3] = 2;
		printDT();
		int[] minimumCost = generateMinimumCostArray();
	    sendToLayer2(2 , 1 , minimumCost);	
		sendToLayer2(2 , 0 , minimumCost);
		sendToLayer2(2 , 3 , minimumCost);
		System.out.println("Table 2 initialized");
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
			sendToLayer2(2 , 1 , minimumCost);	
			sendToLayer2(2 , 0 , minimumCost);
			sendToLayer2(2 , 3 , minimumCost);
			System.out.println("Table 2 updated\n");
		}
    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    }
    
    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D2 |   0   1   3");
        System.out.println("----+------------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 2)
            {
                continue;
            }
            
            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++)
            {
                if (j == 2)
                {
                    continue;
                }
                
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
