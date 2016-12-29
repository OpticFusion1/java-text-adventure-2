public class Area
{
    private String name;
    private String desc;
    
    private int row;
    private int col;
    
    protected String outOfBounds = "You attempt to leave the field, but the wild winds buffer you back.";
    
    public Area(String name, int row, int col, String desc) //String descrip
    {
        this.name = name;
        this.row = row;
        this.col = col;
        this.desc = desc;
    }
    
    public String getDescrip()
    {
        return desc;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getOut()
    {
        return outOfBounds;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    
}