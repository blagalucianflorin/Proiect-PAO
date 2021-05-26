package catalog;

import java.util.Collections;
import java.util.Vector;

public class Menu
{
    private final Service catalogService;


    public Menu (Service newCatalogService)
    {
        this.catalogService = newCatalogService;
    }

    public static Vector <String> getArgs (String command)
    {
        Vector <String> retArgs   = new Vector <String>();
        String[]        auxArgs;
        int             firstQ    = -1;
        int             lastSpace = -1;
        System.out.println (command);


//        command = command.trim ();
//        command = command.replaceAll ("\n", "");

        for (int i = 0; i < command.length (); i++)
        {
            if (command.charAt (i) == ' ' && firstQ == -1)
            {
                retArgs.add (command.substring (lastSpace + 1, i - 1));
                lastSpace = i;
            }
            else if (command.charAt (i) == '\"' || command.charAt (i) == '\'')
            {
                System.out.println ("\"gasit\"");
                if (firstQ == -1)
                {
                    firstQ = i;
                }
                else
                {
                    retArgs.add (command.substring (firstQ + 1, i - 1));
                    firstQ  = -1;
                }
            }
        }

//        auxArgs = command.split ("\\s+");
//        Collections.addAll(retArgs, auxArgs);

//        for (int i = 0; i < retArgs.size (); i++)
//            retArgs.set (i, retArgs.get (i).replaceAll ("^\"|\"$", ""));

        return (retArgs);
    }

    public void command (String command) throws Exception
    {
        selectCommand (Menu.getArgs (command));
    }

    private void checkArgsLen (Vector <String> args, int minLen) throws Exception
    {
        if (args.size () < minLen)
            throw new Exception ("Wrong number of arguments");
    }

    private void selectCommand (Vector <String> args) throws Exception
    {
        switch (args.get (0))
        {
            case "showTeachers":
                commandShowTeachers (this.catalogService.getTeachers ());
                break;

            case "addTeacher":
                this.checkArgsLen (args, 2);
                commandAddTeacher (args.get (1));
                break;
        }
    }


    private void commandShowTeachers (Vector <Teacher> teachers)
    {
        System.out.println ("Teachers: ");
        for (int i = 0; i < teachers.size (); i++)
            System.out.println ("\t" + (i + 1) + ". " + teachers.get (i).getName());
        System.out.println("");
    }

    private void commandAddTeacher (String newName)
    {
        this.catalogService.addTeacher (newName);
    }
}
