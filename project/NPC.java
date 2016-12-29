public class NPC
{
    private String name;
    private String descrip;
    //private boolean Alive;
    
    public NPC(String name, String descrip)
    {
        this.name = name;
        this.descrip = descrip;
        //Alive = true;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescrip()
    {
        return descrip;
    }
    
    /*public boolean getAttacked()
    {
        int random = (int)(Math.random() * 6 + 1);
        if(random == 1 || random == 3 || random == 5 || random == 6){
           return false;
        }else{
           Alive = false;
           return false;
        }
    }*/
}