import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
public class Game
{
    Scanner ScanIn;
    BufferedReader console;
    Scanner inF;
    Scanner inF2;
    private Map m1;
    private Map h1;
    private Map c1;
    
    boolean isWindowOpen = false;
    boolean swordTaken = false;    
    String currentSection;
    
    private Character mainChar;
    private NPC boss = new NPC("Kahn Shim", "A frumpy, salty looking troll with dried up, wrinkly skin and translucent yellow teeth. He stares at you blankly, then brandishes his dagger menacingly.");
    
    public Game()
    {
        ScanIn = new Scanner(System.in);
        console = new BufferedReader(new InputStreamReader(System.in));
        currentSection = "main";
        m1 = new Map("main");
        h1 = new Map("house");
        c1 = new Map("cave");
    }

    public void start() throws IOException, FileNotFoundException
    {
        System.out.println("Hello Player! What's your name?");
        mainChar = new Character(console.readLine());
        inF = new Scanner(new File("troll.txt"));
        inF2 = new Scanner(new File("map.txt"));
        System.out.println(m1.getArea().getName());
        while (1==1)
        {   
            String input = console.readLine().toLowerCase();
            if (input.equals("-abc"))
                System.exit(0);
            if (currentSection.equals("main"))
            {
                if (input.indexOf("go") >= 0)
                {
                    String direction;
                    if (input.length() >= 3)
                    {
                        direction = input.substring(3);
                        if (direction.indexOf("east") != -1)
                        {
                            if(m1.isOutOfBound("east"))
                            {
                                System.out.println(m1.getArea().getOut());
                                System.out.println(m1.getArea().getName());
                            }else{
                                m1.goEast();
                                System.out.println(m1.getArea().getName());
                                System.out.println(m1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("west") != -1)
                        {
                            if(m1.isOutOfBound("west"))
                            {
                                System.out.println(m1.getArea().getOut());
                                System.out.println(m1.getArea().getName());
                            }
                            else
                            {
                                m1.goWest();
                                System.out.println(m1.getArea().getName());
                                System.out.println(m1.getArea().getDescrip());
                                if (m1.getArea().getName().equals("Deep Forest"))
                                {
                                    if (!swordTaken)
                                    {
                                        System.out.println("You notice a shiny looking object on the ground.");
                                    }
                                }
                            }
                        }
                        else if (direction.indexOf("north") != -1)
                        {
                            if(m1.isOutOfBound("north"))
                            {
                                System.out.println(m1.getArea().getOut());
                                System.out.println(m1.getArea().getName());
                            }
                            else
                            {
                                m1.goNorth();
                                System.out.println(m1.getArea().getName());
                                System.out.println(m1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("south") != -1)
                        {
                            if(m1.isOutOfBound("south"))
                            {
                                System.out.println(m1.getArea().getOut());
                                System.out.println(m1.getArea().getName());
                            }
                            else
                            {
                                m1.goSouth();
                                System.out.println(m1.getArea().getName());
                                System.out.println(m1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("in window") != -1)
                        {
                            if (m1.getArea().getName().equals("House Property"))
                            {
                                if (isWindowOpen)
                                {
                                    currentSection = "house";
                                    System.out.println(h1.getArea().getName());
                                    System.out.println(h1.getArea().getDescrip());
                                }
                                else
                                {
                                    System.out.println("Unfortunately the window is still closed, you might be able to open it though.");
                                }
                            }                            
                        }
                        else if (direction.indexOf("in") != -1 || direction.indexOf("door") != -1 || direction.indexOf("in cave") >= 0)
                        {
                            if (m1.getArea().getName().equals("House Property"))
                            {
                                System.out.println("The door is locked, but there might be another way in. Maybe you found it and weren't specific enough?");
                            }
                            else if (m1.getArea().getName().equals("Cavern Entrance"))
                            {                                
                                while (inF.hasNextLine())
                                {
                                    System.out.println(inF.nextLine());
                                }
                                currentSection = "cave";
                                System.out.println(c1.getArea().getName());
                                System.out.println(c1.getArea().getDescrip());
                            }
                            else
                            {
                                System.out.println("There is nothing to go in to.");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("I only understood as far as wanting to go.");
                    }
                }
                else if (input.indexOf("look") >= 0)
                {
                    if (input.length() >= 5)
                    {
                        String object = input.substring(5).toLowerCase();
                        if (object.toLowerCase().indexOf("map") >= 0)
                        {
                            System.out.println(mainChar.getInven().getDesc(object));
                            if (!swordTaken)
                                System.out.println("Hey, what's that in the top left sector of the map?");
                            else
                                System.out.println("You remember that you've taken the sword, as you smile and continue on your journey.");
                        }
                        else if (object.toLowerCase().indexOf("instructions") >= 0)
                            System.out.println(mainChar.getInven().getDesc(object));
                        else if (object.toLowerCase().indexOf("shiny") >= 0)
                        {
                            if (m1.getArea().getName().equals("Deep Forest"))
                            {
                                if (!swordTaken)
                                {
                                    mainChar.getInven().addItem(new Weapon("sword", "A shiny sword, maybe it has an intended purpose?"));
                                    swordTaken = true;
                                    System.out.println("You have taken the object, which you now know is a sword.");
                                }
                                else
                                    System.out.println("There used to be an item here. Did you take it already?");
                            }
                        }
                        else if (object.toLowerCase().indexOf("sword") >= 0)
                            System.out.println(mainChar.getInven().getDesc(object));
                        else
                            System.out.println("There's nothing to look at .");
                    }
                    else
                    {
                        System.out.println(m1.getArea().getDescrip());
                    }
                }
                else if (input.indexOf("open") >= 0)
                {
                    boolean nameEgg = false;
                    String object;
                    if (input.length() >= 5)
                    {
                        object = input.substring(5);
                        if (object.indexOf("door") >= 0)
                        {
                            System.out.println("You successfully find the doorknob, only to learn that the door is jammed shut from the inside.");
                        }
                        else if (object.indexOf("window") >= 0)
                        {
                            if (isWindowOpen)
                                System.out.println("You already opened the window, remember?");
                            else
                            {
                                isWindowOpen = true;
                                System.out.println("You pull on the window with all of your might, and manage to open it just wide enough to fit through at your leisure.");
                            }
                        }
                        else if (object.indexOf("guan") >= 0)
                        {
                            if (m1.getArea().getName().equals("Cavern Entrance"))
                            {
                                boss = new NPC("Glex A'uan", "A frumpy, salty looking troll with dried up, wrinkly skin and translucent yellow teeth. He stares at you blankly, then brandishes his dagger menacingly.");
                                System.out.println("Egg Found!");
                            }
                            else 
                                System.out.println("I only understood you as far as wanting to open something.");
                        }
                        else
                        {
                            System.out.println("I only understood you as far as wanting to open something.");
                        }
                    }
                    else
                    {
                        System.out.println("I only understood you as far as wanting to open something.");
                    }
                }
                else if (input.indexOf("retreat") >= 0)
                {
                    System.out.println("There is nothing to retreat from.");
                }
                else if (input.indexOf("inventory") >= 0)
                {
                    System.out.println(mainChar.getInven().getName());
                }
                else
                {
                    System.out.println("I don't understand what that means");
                }
            }
            else if (currentSection.equals("house"))
            {
                boolean newDoor = false;
                if (input.indexOf("go") >= 0)
                {
                    String direction;
                    if (input.length() >= 3)
                    {
                        direction = input.substring(3);
                        if (direction.indexOf("door") != -1 || direction.indexOf("in") != -1)
                        {
                            if (newDoor = false)
                            {
                                System.out.println("Lucky for you, the door is already open.");
                                h1.goEast();
                                System.out.println(h1.getArea().getName());
                                System.out.println(h1.getArea().getDescrip());
                            }
                            else
                            {
                                h1.goEast();
                                System.out.println(h1.getArea().getName());
                                System.out.println(h1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("east") != -1)
                        {
                            if(h1.isOutOfBound("east"))
                            {
                                System.out.println(h1.getArea().getOut());
                                System.out.println(h1.getArea().getName());
                            }else{
                                h1.goEast();
                                System.out.println(h1.getArea().getName());
                                System.out.println(h1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("west") != -1)
                        {
                            if(h1.isOutOfBound("west"))
                            {
                                System.out.println(h1.getArea().getOut());
                                System.out.println(h1.getArea().getName());
                            }
                            else
                            {
                                h1.goWest();
                                System.out.println(h1.getArea().getName());
                                System.out.println(h1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("north") != -1)
                        {
                            if(h1.isOutOfBound("north"))
                            {
                                System.out.println(h1.getArea().getOut());
                                System.out.println(h1.getArea().getName());
                            }
                            else
                            {
                                h1.goNorth();
                                System.out.println(h1.getArea().getName());
                                System.out.println(h1.getArea().getDescrip());
                            }
                        }
                        else if (direction.indexOf("south") != -1)
                        {
                            if(h1.isOutOfBound("south"))
                            {
                                System.out.println(h1.getArea().getOut());
                                System.out.println(h1.getArea().getName());
                            }
                            else
                            {
                                h1.goSouth();
                                System.out.println(h1.getArea().getName());
                                System.out.println(h1.getArea().getDescrip());
                            }
                        }
                        else
                        {
                            System.out.println("I only understood as far as wanting to go.");
                        }
                    }
                    else
                    {
                        System.out.println("I only understood as far as wanting to go.");
                    }
                }     
                else if (input.indexOf("open") >= 0)
                {
                    String object;
                    if (input.length() >= 5)
                    {
                        object = input.substring(5);
                        if (object.indexOf("door") >= 0)
                        {
                            System.out.println("Well that was easy, the door was already open.");
                            newDoor = true;
                        }
                        else
                        {
                            System.out.println("I only understood you as far as wanting to open something.");
                        }
                    }
                    else
                    {
                        System.out.println("I only understood you as far as wanting to open something.");
                    }
                }               
                else if (input.indexOf("retreat") >= 0)
                {
                    if (h1.getArea().getName().equals("Shady Study"))
                    {
                        h1.goWest();
                        System.out.println(h1.getArea().getName());
                        System.out.println(h1.getArea().getDescrip());
                    }
                    else if (h1.getArea().getName().equals("Living Room"))
                    {
                        currentSection = "main";
                        System.out.println(m1.getArea().getName());
                        System.out.println(m1.getArea().getDescrip());
                    }
                    else
                    {
                        System.out.println("There is nothing to retreat from.");
                    }
                }
                else if (input.indexOf("inventory") >= 0)
                {
                    System.out.println(mainChar.getInven().getName());
                }
                else if (input.indexOf("look") >= 0)
                {
                    if (input.length() >= 5)
                    {
                        String object = input.substring(5).toLowerCase();
                        System.out.println(object);
                        if (object.indexOf("bookshelf") >= 0 || object.indexOf("shelf") >= 0)
                        {
                            if (h1.getArea().getName().equals("Shady Study"))
                            {
                                String mapString = "";
                                while (inF2.hasNextLine())
                                {
                                    mapString += inF2.nextLine() + "\n";
                                }
                                System.out.println("Examining the dusty bookshelf closely, you notice a rolled piece of paper on the third shelf. You take it.");
                                mainChar.getInven().addItem(new Item("map", mapString));
                            }
                        }    
                        else if (object.toLowerCase().indexOf("map") >= 0)
                        {
                            System.out.println(mainChar.getInven().getDesc(object));
                            System.out.println("Hey, what's that in the top left sector of the map?");
                        }
                        else if (object.toLowerCase().indexOf("instructions") >= 0)
                            System.out.println(mainChar.getInven().getDesc(object));
                        else if (object.toLowerCase().indexOf("sword") >= 0)
                            System.out.println(mainChar.getInven().getDesc(object));
                        else
                            System.out.println("There's nothing to look at.");
                    }
                    else
                    {
                        System.out.println(m1.getArea().getDescrip());
                    }
                }
                else
                {
                    System.out.println("I don't understand what that means");
                }
            }
            else if (currentSection.equals("cave"))
            {
                if (input.indexOf("look") >= 0)
                {
                    if (input.length() >= 5)
                    {
                        String object = input.substring(5);
                        System.out.println(mainChar.getInven().getDesc(object));
                    }
                    else
                    {
                        System.out.println(m1.getArea().getDescrip());
                    }
                }
                else if (input.indexOf("inventory") >= 0)
                {
                    System.out.println(mainChar.getInven().getName());
                }
                else if (input.indexOf("retreat") >= 0)
                {
                        currentSection = "main";
                        System.out.println(m1.getArea().getName());
                        System.out.println(m1.getArea().getDescrip());
                }
                else if (input.indexOf("fight") >= 0)
                {
                    if (swordTaken)
                    {
                        System.out.println("You draw your sword and slay " + boss.getName());
                        System.out.println("You win, " + mainChar.getName() + "!");
                    }
                    else
                    {
                        System.out.println("You attempt to fight the troll, but your fists alone aren't enough! You are slain by the legendary cave troll. As you fall, he mutters; \nNo one defeats me, " + boss.getName());
                        System.out.println("You have died. RIP " + mainChar.getName());
                    }
                }
            }
            
        }
    }
}
