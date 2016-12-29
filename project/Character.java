public class Character
{
    private String name;
    private Inventory charInven;
    
    public Character(String name)
    {
        this.name = name;
        charInven = new Inventory();
    }
    
    public String getName()
    {
        return name;
    }
    
    public Inventory getInven()
    {
        return charInven;
    }
}