public class House extends Area
{
    public House(String name, int row, int col, String desc)
    {
        super(name, row, col, desc);
        super.outOfBounds = "You walk straight into the hardwood wall. Thankfully, no one was there to see it.";
    }
}