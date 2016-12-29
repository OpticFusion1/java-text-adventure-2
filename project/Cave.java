public class Cave extends Area
{
    public Cave(String name, int row, int col, String desc)
    {
        super(name, row, col, desc);
        super.outOfBounds = "You run into the damp, musty side of the cave. Ouch!";
    }
}