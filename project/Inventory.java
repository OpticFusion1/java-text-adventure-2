import java.util.ArrayList;
public class Inventory
{
    private ArrayList<Item> inven = new ArrayList<Item>();

    public Inventory()
    {
        inven.add(new Item("Instructions", "Hello Player! Welcome to Bork's Adventure. This is a text based game, so you'll need to know the operative keywords. \nUse 'look' + an object to examine an object, or just look to look around at your surroundings. \nUse Go in + a direction or entrance area to go somewhere, or retreat to leave.\nYou can also type -abc to quit. \nYou can use inventory to check your inventory, as well as look + itemname to get info. Try it! \nHave fun exploring this main area, and remember to give Alex Guan some money"));
    }
    
    public String getName()
    {
        String out = "";
        for(Item i : inven)
        {
            out += i.getName() + "\n";
        }
        return out;
    }
    
    public String getDesc(String s) //s = map
    {
        for (int i = 0; i < inven.size(); i++)
        {
            if (s.indexOf(inven.get(i).getName().toLowerCase()) >= 0)   //inven[1] = map
            {
                return inven.get(i).getDescription();
            }
        }
        return "You can't look at something that's not there.";
    }
    
    public void addItem(Item i)
    {
        inven.add(i);
    }
}
