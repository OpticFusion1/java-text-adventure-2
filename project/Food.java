public class Food extends Item
{
    private int healthGained;
    public Food(String name, String description, int healthGained)
    {
        super(name, description);
        this.healthGained = healthGained;
    }
}