package catalog.services;

import catalog.entities.*;
import catalog.entities.courses.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

public class Menu
{
    private final CatalogService catalogService;


    public Menu (CatalogService newCatalogCatalogService)
    {
        this.catalogService = newCatalogCatalogService;
    }

    public static Vector <String> getArgs (String command)
    {
        Vector <String> retArgs     = new Vector<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean inQuote = false;

        command = command.trim ();
        for (char chr : command.toCharArray ())
        {

            if (chr == '"')
                inQuote = !inQuote;

            if (chr == ' ' && !inQuote)
            {
                retArgs.add (stringBuilder.toString ());
                stringBuilder.delete (0, stringBuilder.length ());
            }
            else
                stringBuilder.append (chr);
        }
        retArgs.add (stringBuilder.toString ());

        for (int i = 0; i < retArgs.size (); i++)
            if (retArgs.get (i).charAt (0) == '"' && retArgs.get (i).charAt (retArgs.get (i).length () - 1) == '"')
                retArgs.set (i, retArgs.get (i).substring (1, retArgs.get (i).length () - 1));

        return (retArgs);
    }

    public void command (String command) throws Exception
    {
        this.addCommandToHistory (command);
        selectCommand(Menu.getArgs(command));
    }

    private void checkArgsLen (Vector <String> args, int num, boolean strict) throws Exception
    {
        if (strict && args.size () > num)
            throw new Exception ("Wrong number of arguments");
        if (args.size () < num)
            throw new Exception ("Wrong number of arguments");
    }

    public void console () throws Exception
    {
        BufferedReader reader  = new BufferedReader( new InputStreamReader(System.in));
        String         command = "";

        System.out.println ("Opening console...");
        commandLoadData (".csvs");
        System.out.println ("Try using 'help' to see a list of commands.");
        System.out.print (">>> ");
        while (!command.equals ("exit"))
        {
            command = reader.readLine();
            try
            {
                this.command (command);
            }
            catch (Exception e)
            {
                System.out.println (e.getMessage ());
            }
            System.out.print (">>> ");
        }
        Historian.getInstance ().close ();
    }

    private void addCommandToHistory (String command) throws IOException {
        Historian.getInstance ().add (command);
    }


    private void selectCommand (Vector <String> args) throws Exception
    {
        switch (args.get(0)) {
            case "help":
                commandHelp (args);
                break;

            case "clear":
                this.checkArgsLen(args, 1, true);
                commandClear ();
                break;

            case "exit":
                System.out.println ("Saving data to '.csvs/'...");
                commandSaveData (".csvs");
                System.out.println ("Exiting...");
                break;

            case "saveData":
                if (args.size () == 2) {
                    if (args.get(1).equals(".csvs")) {
                        System.out.println("Can't use that folder to save data.");
                        break;
                    }
                    System.out.println ("Saving data to '" + args.get (1) + "/'...");
                    commandSaveData (args.get (1));
                } else if (args.size () == 1) {
                    System.out.println ("Saving data to '.csvs/'...");
                    commandSaveData (".csvs");
                } else
                    this.checkArgsLen(args, 2, true);
                break;
                
            case "loadData":
                if (args.size () == 2) {
                    System.out.println ("Loading data from '" + args.get (1) + "'.");
                    commandLoadData(args.get(1));
                }
                else if (args.size () == 1) {
                    System.out.println ("Loading data from '.csvs/'...");
                    commandLoadData(".csvs");
                }
                else
                    this.checkArgsLen(args, 2, true);
                break;

            case "clearData":
                this.checkArgsLen (args, 1, true);
                commandClearData ();
                break;

            case "history":
                this.checkArgsLen (args, 1, false);
                commandHistory ();
                break;

            case "clearHistory":
                this.checkArgsLen (args, 1, false);
                commandClearHistory ();
                break;

            case "addParent":
                this.checkArgsLen(args, 4, true);
                commandAddParent (args.get (1), args.get (2), args.get (3));
                break;

            case "showTeachers":
                this.checkArgsLen(args, 1, true);
                commandShowTeachers();
                break;

            case "addTeacher":
                this.checkArgsLen(args, 2, true);
                commandAddTeacher(args.get(1));
                break;

            case "addStudent":
                this.checkArgsLen(args, 3, true);
                commandAddStudent(args.get(1), args.get(2));
                break;

            case "showStudents":
                this.checkArgsLen(args, 1, true);
                commandShowStudents();
                break;

            case "removeStudent":
                this.checkArgsLen(args, 2, true);
                commandRemoveStudent(args.get(1));
                break;

            case "addAbsence":
                this.checkArgsLen(args, 3, true);
                commandAddAbsence(args.get(1), args.get(2));
                break;

            case "motivateAbsence":
                this.checkArgsLen(args, 3, true);
                commandMotivateAbsence(args.get(1), args.get(2));
                break;

            case "addGroup":
                this.checkArgsLen(args, 2, true);
                this.commandAddGroup(args.get(1));
                break;

            case "removeGroup":
                this.checkArgsLen(args, 2, true);
                this.commandRemoveGroup(args.get(1));
                break;

            case "showGroups":
                this.checkArgsLen(args, 1, true);
                this.commandShowGroups();
                break;

            case "addCourse":
                this.checkArgsLen(args, 4, true);
                this.commandAddCourse(args.get(1), args.get(2), args.get(3));
                break;

            case "showCourses":
                this.checkArgsLen(args, 1, true);
                this.commandShowCourses();
                break;

            case "addCourseToGroup":
                this.checkArgsLen(args, 3, true);
                this.commandAddCourseToGroup(args.get(1), args.get(2));
                break;

            case "addGrade":
                this.checkArgsLen(args, 4, true);
                this.commandAddGrade(args.get(1), args.get(2), args.get(3));
                break;

            case "addStudentToGroup":
                this.checkArgsLen(args, 3, true);
                this.commandAddStudentToGroup(args.get(1), args.get(2));
                break;

            default:
                throw new Exception ("Unknown command. Try using 'help'.");
        }
    }


    private void commandHelp (Vector <String> args) {
        Vector<String> simpleHelp              = new Vector<>();
        Hashtable<String, String> advancedHelp = new Hashtable<>();

        String[] simpleHelpArr = {
                "'help'",
                "'clear'",
                "'exit'",
                "'saveData [folderName]'",
                "'loadData [folderName]'",
                "'clearData'",
                "'history'",
                "'clearHistory'",
                "'showTeachers'",
                "'addTeacher <teacherName>'",
                "'addStudent <studentName> <studentAge>'",
                "'showStudents'",
                "'removeStudent <studentName>'",
                "'addStudentToGroup <studentName> <groupName>'",
                "'addParent <studentName> <parentName> <parentPhone>'",
                "'addAbsence <studentName> <absenceDate>'",
                "'motivateAbsence <studentName> <absenceDate>'",
                "'addGroup <groupName>'",
                "'removeGroup <groupName>'",
                "'showGroups'",
                "'addCourse <courseName> <teacherName> <withExam>'",
                "'showCourses'",
                "'addCourseToGroup <courseName> <groupName>'",
                "'addGrade <studentName> <courseName> <value>'",
        };
        Collections.addAll(simpleHelp, simpleHelpArr);

        advancedHelp.put("help", """
                'help'
                 Display this help message.
                All arguments can be enclosed by "". Ex.: 'command "argument with spaces"'.""");

        advancedHelp.put("clear", "'clear'\n" +"Clear the console.");

        advancedHelp.put("exit", "'exit'\n" + "Exits the program. Current contents are saved to /.csvs/{files}.csv");

        advancedHelp.put("saveData", "'saveData [folderName]'\n" + "Saves the whole " +
                "data to .csv files in folder 'folderName'." + "Don't provide the 'folderName' argument if you want" +
                " to load data from the default folder.");

        advancedHelp.put("loadData", """
                'loadData [folderName]'
                Loads the whole data from the .csv files in 'folderName'.
                Don't provide the 'folderName' argument if you want to load data from the default folder.""");

        advancedHelp.put ("history", "history: 'history'\nPrints the command history.");

        advancedHelp.put ("clearHistory", "clearHistory: 'clearHistory'\nClears the command history.");

        advancedHelp.put("showTeachers", "'showTeachers'\n" + "Prints all teachers.");

        advancedHelp.put("addTeacher", "'addTeacher <teacherName>'\n" + "Adds a new teacher with 'teacherName'.");

        advancedHelp.put("addStudent", "'addStudent <studentName> <studentAge>'\n" + "Adds a new student with the" +
                " attributes 'studentName' and 'studentName'.");

        advancedHelp.put("showStudents", "'showStudents'\n" + "Prints all students.");

        advancedHelp.put("removeStudent", "'removeStudent <studentName>'\n" + "Removes the student with 'studentName'.");

        advancedHelp.put("addStudentToGroup", "'addStudentToGroup <studentName> <groupName>'\n" + "Adds a student with" +
                " 'studentName' to the 'groupName' group.");

        advancedHelp.put("addParent", "'addParent <studentName> <parentName> <parentPhone>'\n" + "Creates a new parent " +
                "and ads the new parent to the student.");

        advancedHelp.put("addAbsence", "'addAbsence <studentName> <absenceDate>'\n" + "Adds a student to the " +
                "'studentName' student on 'absenceDate'.");

        advancedHelp.put("motivateAbsence", "'motivateAbsence <studentName> <absenceDate>'\n" + "Motivates the " +
                "'absenceDate' absence of the 'studentName' student.");

        advancedHelp.put("addGroup", "'addGroup <groupName>'\n" + "Adds a new group with the 'groupName' group.");

        advancedHelp.put("removeGroup", "'removeGroup <groupName>'\n" + "Removes the group with 'groupName'.");

        advancedHelp.put("showGroups", "'showGroups'\n" + "Prints all groups.");

        advancedHelp.put("addCourse", "'addCourse <courseName> <teacherName> <withExam>'\n" + "Adds a new course. " +
                "Teachers with 'teacherName' must exist. " + "'withExam' can be yes/no.");

        advancedHelp.put("showCourses", "'showCourses'\n" + "Prints all courses.");

        advancedHelp.put("addCourseToGroup", "'addCourseToGroup <courseName> <groupName>'\n" + "Adds the 'courseName' " +
                "course to the 'groupName' group.");

        advancedHelp.put("addGrade", "'addGrade <studentName> <courseName> <value>'\n" + "Adds a grade with 'value' " +
                "to the 'courseName' course of the 'studentName' " + "student.");

        advancedHelp.put("clearData", "'clearData'\n" + "Deletes all the data currently stored in the program.");


        if (args.size() == 1) {
            for (String command : simpleHelp)
                System.out.println(command);
        } else if (args.size() == 2) {
            if (advancedHelp.containsKey (args.get (1))) {
                System.out.print (args.get (1) + ": " + advancedHelp.get (args.get (1)) + "\n\n");
            }
            else
                System.out.println ("Command doesn't exist");
        } else if (args.size() > 2) {
            System.out.println ("Wrong number of arguments");
        }
    }

    
    private void commandSaveData (String folderName) {
        CSVManager.getInstance ().saveCSV (this.catalogService.saveTeachers (), folderName + "/teachers.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveStudents (), folderName + "/students.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveAbsences (), folderName + "/absences.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveParents (), folderName + "/parents.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveGroups (), folderName + "/groups.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveCourses (), folderName + "/courses.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveCoursesAssignments (), folderName +
                "/courseAssignments.csv");
        CSVManager.getInstance ().saveCSV (this.catalogService.saveGrades (), folderName + "/grades.csv");
    }
    
    private void commandLoadData (String folderName) throws Exception {
        this.catalogService.clearData ();

        this.catalogService.loadTeachers (CSVManager.getInstance ().loadCSV (folderName + "/teachers.csv"));
        this.catalogService.loadGroups (CSVManager.getInstance ().loadCSV (folderName + "/groups.csv"));
        this.catalogService.loadStudents (CSVManager.getInstance ().loadCSV (folderName + "/students.csv"));
        this.catalogService.loadAbsences (CSVManager.getInstance ().loadCSV (folderName + "/absences.csv"));
        this.catalogService.loadParents (CSVManager.getInstance ().loadCSV (folderName + "/parents.csv"));
        this.catalogService.loadCourses (CSVManager.getInstance ().loadCSV (folderName + "/courses.csv"));
        this.catalogService.loadCoursesAssignments (CSVManager.getInstance ().loadCSV (folderName +
                "/courseAssignments.csv"));
        this.catalogService.loadGrades (CSVManager.getInstance ().loadCSV (folderName + "/grades.csv"));
    }
    

    private void commandShowTeachers ()
    {
        Vector <Teacher> teachers = this.catalogService.getTeachers ();

        System.out.println ("Teachers: ");
        if (teachers.size () == 0)
            System.out.println ("\tNo teachers");
        for (int i = 0; i < teachers.size (); i++)
            System.out.println ("\t" + (i + 1) + ". " + teachers.get (i).getName());
        System.out.println();
    }

    private void commandAddTeacher (String newName)
    {
        this.catalogService.addTeacher (newName);
    }


    private void commandHistory () throws IOException {
        for (Vector <String> line : Historian.getInstance ().getHistory ()) {
//            System.out.println ("'" + line.get (0) + "'\t-\t" + line.get (1));
            System.out.println (line.get (1) + "\t-\t'" + line.get (0) + "'");
        }
    }

    private void commandClearHistory () throws IOException {
        Historian.getInstance ().clearHistory ();
    }


    private void commandClearData ()
    {
        this.catalogService.clearData ();
    }


    private void commandAddStudent (String newName, String newAge)
    {
        this.catalogService.addStudent (newName, Integer.parseInt (newAge));
    }

    private void commandShowStudents ()
    {
        Vector <Student> students = this.catalogService.getStudents ();

        System.out.println ("Students: ");
        if (students.size () == 0)
            System.out.println ("\tNo students");
        for (int i = 0; i < students.size (); i++)
        {
            System.out.print ("\t" + (i + 1) + ". Name: " + students.get(i).getName());
            System.out.println (" - Age: " + students.get (i).getAge ());

            System.out.println ("\t\tGroup:");
            if (students.get (i).getGroup () != null)
                System.out.println ("\t\t " + students.get (i).getGroup ().getName ());
            else
                System.out.println ("\t\t No group");

            System.out.println ("\t\tGrades:");
            if (students.get (i).getGrades ().size () != 0)
                for (Grade grade : students.get (i).getGrades ())
                    System.out.println("\t\t -- " + grade.getValue () + " (" + grade.getCourse ().getName () + ")");
            else
                System.out.println ("\t\t No grades");

            if (students.get (i).getParents ().size () != 0)
            {
                System.out.println ("\t\tParents:");
                for (Parent parent : students.get (i).getParents ())
                    System.out.println ("\t\t -- " + parent.getName () + " (" + parent.getPhone () +")");
            }

            System.out.println ("\t\tAbsences: ");
            if (students.get (i).getAbsences ().size () != 0)
                for (Absence abs : students.get (i).getAbsences ())
                {
                    System.out.print("\t\t -- " + abs.getDate() + " - Motivated: ");
                    System.out.println (abs.isMotivated () ? "Yes" : "No");
                }
            else
                System.out.println ("\t\t No absences");

        }
        System.out.println();
    }

    private void commandRemoveStudent (String oldName)
    {
        this.catalogService.removeStudent (oldName);
    }

    private void commandAddStudentToGroup (String studentName, String groupName) throws Exception
    {
        this.catalogService.addStudentToGroup (studentName, groupName);
    }


    private void commandAddParent (String studentName, String parentName, String parentPhone)
    {
        this.catalogService.addParent (studentName, parentName, parentPhone);
    }


    private void commandAddAbsence (String studentName, String absenceDate)
    {
        this.catalogService.addAbsence (studentName, absenceDate);
    }

    private void commandMotivateAbsence (String studentName, String absenceDate)
    {
        this.catalogService.motivateAbsence (studentName, absenceDate);
    }


    private void commandAddGroup (String newName)
    {
        this.catalogService.addGroup (newName);
    }

    private void commandRemoveGroup (String groupName)
    {
        this.catalogService.removeGroup (groupName);
    }

    private void commandShowGroups ()
    {
        Vector <Group> groups = this.catalogService.getGroups ();

        System.out.println ("Groups:");
        for (Group group : groups)
        {
            System.out.println("\t - Name: '" + group.getName() + "' Courses:");
            if (group.getCourses ().size () != 0)
                for (Course course : group.getCourses())
                    System.out.println ("\t\t -- Name: '" + course.getName() + "'");
            else
                System.out.println ("\t\tNo courses have been added yet.");
            System.out.println ();
        }
        System.out.println ();
    }


    private void commandAddCourse (String newName, String teacherName, String withExam) throws Exception
    {
        boolean auxWithExam;

        if (!withExam.equals ("yes") && !withExam.equals ("no"))
            throw new Exception ("Wrong argument 'withExam': " + withExam);

        auxWithExam = withExam.equals ("yes");

        this.catalogService.addCourse (newName, teacherName, auxWithExam);
    }

    private void commandShowCourses ()
    {
        Vector <Course> courses = this.catalogService.getCourses ();

        System.out.println ("Courses:");
        for (Course course : courses)
            System.out.println ("\t - Name: " + course.getName () + " - Teacher: " + course.getTeacher ().getName ());
        System.out.println();
    }

    private void commandAddCourseToGroup (String courseName, String groupName) throws Exception
    {
        this.catalogService.addCourseToGroup (groupName, courseName);
    }


    private void commandAddGrade (String studentName, String courseName, String value) throws Exception
    {
        this.catalogService.addGrade (studentName, courseName, Float.parseFloat (value));
    }

    private void commandClear ()
    {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }
}
