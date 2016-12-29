public class Map
{
    private Area[][] areaMatrix;
    private Area current;
    
    public Map(String s)
    { //hi
        if (s.equals("main"))
        {
            areaMatrix = new Area[3][3];
            for(int i = 0; i < areaMatrix.length;i++)
            {
                for (int j = 0; j < areaMatrix[i].length;j++)
                {
                    if (i == 0)
                    {
                        if (j == 0)
                            areaMatrix[i][j] = new Area("Deep Forest", i, j, "As you continue in the forest, you become surrounded by trees. You see a clearing to the south, and your footprints to the east.");  
                        else if (j == 1)
                            areaMatrix[i][j] = new Area("Light Forest", i, j, "You enter the treeline, seeing the back of the house to the south, a seeming endless path to the west, and a boulder to the east.");     
                        else
                            areaMatrix[i][j] = new Area("Rock Face", i, j, "Wow, that's a big rock! There are trees to the west and water east, with parts of the corroded rock at the shoreline.");  
                    }
                    if (i == 1)
                    {
                        if (j == 0)
                            areaMatrix[i][j] = new Area("Barren Field", i, j, "The sun beats down on your stamina as you gaze at the house to your east. You can't see anything to the south.");  
                        else if (j == 1)
                            areaMatrix[i][j] = new Area("House Property", i, j, "You find yourself infront of a grey house, with a slightly ajar window infront of you.");     
                        else
                            areaMatrix[i][j] = new Area("Lake", i, j, "You see the glimmer of fish scales beneath the surface as you dip your feet into the clear water for some relief, as you see the house to the west.");  
                    }
                    if (i == 2)
                    {
                        if (j == 0)
                            areaMatrix[i][j] = new Area("Open Field", i, j, "There is nothing in sight except the sun and distant noises of gusting winds.");  
                        else if (j == 1)
                            areaMatrix[i][j] = new Area("Desolate Field", i, j, "You see a potential opening in the mountainface to your east, as well as the house from before in the northern direction.");     
                        else
                            areaMatrix[i][j] = new Area("Cavern Entrance", i, j, "You see an opening you can barely squeeze through, as you look back to see the field and tip of the house in the distance.");  
                    }
                }
            }
            current = areaMatrix[1][1];
        }   
        else if (s.equals("house"))
        {
            areaMatrix = new House[1][2];
            areaMatrix[0][0] = new House("Living Room", 0, 0, "You find yourself in a large room with the same slightly ajar window. You see another door that may lead to another room, or your inpending doom. Who knows.");
            areaMatrix[0][1] = new House("Shady Study", 0, 1, "A wide, open room with a large, obsolete looking chandelier hanging above you. There is a shady looking bookshelf standing in the corner. It is the only new looking furniture in the room.");
            current = areaMatrix[0][0];
        }
        else if (s.equals("cave"))
        {
            areaMatrix = new Cave[1][1];
            areaMatrix[0][0] = new Cave("Strange Cave Area", 0, 0, "A dark, dank hole in the ground with some kind of mist in the air, obstructing your view of your surroundings. There is an ugly troll standing in the corner.");
            current = areaMatrix[0][0];
        }
    }
    
    public Area getArea()  
    {
        return current;
    }
    
    public void goEast()    //need to check that it doesn't go out of bounds
    {
        current = areaMatrix[current.getRow()][current.getCol()+1];
    }
    
    public void goNorth()
    {
        current = areaMatrix[current.getRow()-1][current.getCol()];
    }
    
    public void goWest()
    {
        current = areaMatrix[current.getRow()][current.getCol()-1];
    }
    
    public void goSouth()
    {
        current = areaMatrix[current.getRow()+1][current.getCol()];
    }
    
    public boolean isOutOfBound(String s)
    {
        if (s.equals("east"))
        {
            if ((current.getCol() + 1) > areaMatrix[0].length - 1)
                return true;
            else
                return false;
        }
        else if (s.equals("west"))
        {
            if ((current.getCol() -1) < 0)
                return true;
            else
                return false;
        }
        else if (s.equals("north"))
        {
            if ((current.getRow() - 1) < 0)
                return true;
            else
                return false;
        }
        else if (s.equals("south"))
        {
            if ((current.getRow() + 1) > areaMatrix.length - 1)
                return true;
            else
                return false;
        }
        else
            return true;
    }
}