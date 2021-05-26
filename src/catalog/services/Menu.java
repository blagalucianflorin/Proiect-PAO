package catalog.services;

import catalog.entities.*;
import catalog.entities.courses.Course;
<<<<<<< HEAD
import catalog.services.DB.DBManager;
import catalog.services.DB.DBService;
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.sql.SQLException;
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

public class Menu
{
    private final CatalogService catalogService;
<<<<<<< HEAD
    private String               dataSource     = "CSV";


    public Menu (CatalogService newCatalogCatalogService) {
        this.catalogService = newCatalogCatalogService;
    }

    public static Vector <String> getArgs (String command) {
=======


    public Menu (CatalogService newCatalogCatalogService)
    {
        this.catalogService = newCatalogCatalogService;
    }

    public static Vector <String> getArgs (String command)
    {
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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
<<<<<<< HEAD
        String dataSource      = SettingsManager.getInstance ().get ("DATASOURCE");

        if (!dataSource.equals ("") && dataSource.equals ("DB"))
            this.dataSource = "DB";
        else
            this.dataSource = "CSV";
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

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
<<<<<<< HEAD
        SettingsManager.getInstance ().set ("DATASOURCE", this.dataSource);
        SettingsManager.getInstance ().close ();
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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
<<<<<<< HEAD
                if (this.dataSource.equals ("CSV"))
                    System.out.println ("Saving data to '.csvs/'...");
                else
                    System.out.println ("Saving data to DB...");

=======
                System.out.println ("Saving data to '.csvs/'...");
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
                commandSaveData (".csvs");
                System.out.println ("Exiting...");
                break;

<<<<<<< HEAD
            case "showDataSource":
                this.checkArgsLen(args, 1, true);
                commandShowDataSource ();
                break;

            case "saveData":
                if (args.size () == 2) {
                    if (args.get(1).equals(".csvs")) {
                        if (this.dataSource.equals ("CSV"))
                            System.out.println("Can't use that folder to save data.");
                        break;
                    }
                    if (this.dataSource.equals ("CSV"))
                        System.out.println ("Saving data to '" + args.get (1) + "/'...");
                    commandSaveData (args.get (1));
                } else if (args.size () == 1) {
                    if (this.dataSource.equals ("CSV"))
                        System.out.println ("Saving data to '.csvs/'...");
=======
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
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
                    commandSaveData (".csvs");
                } else
                    this.checkArgsLen(args, 2, true);
                break;
                
            case "loadData":
                if (args.size () == 2) {
<<<<<<< HEAD
                    if (this.dataSource.equals ("CSV"))
                        System.out.println ("Loading data from '" + args.get (1) + "'.");
                    commandLoadData(args.get(1));
                }
                else if (args.size () == 1) {
                    if (this.dataSource.equals ("CSV"))
                        System.out.println ("Loading data from '.csvs/'...");
=======
                    System.out.println ("Loading data from '" + args.get (1) + "'.");
                    commandLoadData(args.get(1));
                }
                else if (args.size () == 1) {
                    System.out.println ("Loading data from '.csvs/'...");
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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

<<<<<<< HEAD
            case "chooseDataSource":
                this.checkArgsLen(args, 2, true);
                this.commandChooseDataSource (args.get(1));
                break;

            case "calculatePartialMark":
                this.checkArgsLen(args, 3, true);
                this.commandCalculatePartialMark (args.get (1), args.get (2));
                break;

            case "calculateAllMarks":
                this.checkArgsLen (args, 2, true);
                this.commandCalculateAllMarks (args.get (1));
                break;

=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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
<<<<<<< HEAD
                "'showDataSource'",
                "'chooseDataSource <\"CSV\"/\"DB\">'",
                "'saveData [folderName]'",
                "'loadData [folderName]'",
                "'clearData'",
                "'calculatePartialMark'",
                "'calculateAllMarks'",
=======
                "'saveData [folderName]'",
                "'loadData [folderName]'",
                "'clearData'",
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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

<<<<<<< HEAD
        advancedHelp.put ("showDataSource", "'showDataSource'\nDisplays the current data source.");

        advancedHelp.put("clear", "'clear'\n" +"Clear the console.");

        advancedHelp.put("exit", "'exit'\n" + "Exits the program. Current contents are saved to /.csvs/{files}.csv or DB");
=======
        advancedHelp.put("clear", "'clear'\n" +"Clear the console.");

        advancedHelp.put("exit", "'exit'\n" + "Exits the program. Current contents are saved to /.csvs/{files}.csv");
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        advancedHelp.put("saveData", "'saveData [folderName]'\n" + "Saves the whole " +
                "data to .csv files in folder 'folderName'." + "Don't provide the 'folderName' argument if you want" +
                " to load data from the default folder.");

        advancedHelp.put("loadData", """
                'loadData [folderName]'
                Loads the whole data from the .csv files in 'folderName'.
                Don't provide the 'folderName' argument if you want to load data from the default folder.""");

<<<<<<< HEAD
        advancedHelp.put ("calculatePartialMark", "'calculatePartialMark <studentName> <courseName>'" +
                "Displays mark at certain course of a student.");

        advancedHelp.put ("calculateAllMarks", "'calculateAllMarks <studentName>'" +
                "Displays total mark for all courses of a student");

=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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

<<<<<<< HEAD
        advancedHelp.put("chooseDataSource", """
                'chooseDataSource <"CSV"/"DB">'
                Changes the datasource of the program.
                CSV is selected by default when starting the program. Alters the bahaviour of 'loadData' and 'saveData'.
                Also changes where data will be saved when exiting the program.""");

=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

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

<<<<<<< HEAD

    private void commandChooseDataSource (String newDataSource) throws IOException {
        if (newDataSource.equals ("CSV")) {
            this.dataSource = newDataSource;
            SettingsManager.getInstance ().set ("DATASOURCE", "CSV");
        } else if (newDataSource.equals ("DB")) {
            this.dataSource = newDataSource;
            SettingsManager.getInstance ().set ("DATASOURCE", "DB");
        } else
            System.out.println ("Unknown data source");
    }

    private void commandShowDataSource () {
        System.out.println ("Current data source: " + this.dataSource + "\n");
    }


    private void commandSaveData (String folderName) throws SQLException {
        if (this.dataSource.equals ("CSV")) {
            CSVManager.getInstance().saveCSV(this.catalogService.saveTeachers(), folderName + "/teachers.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveStudents(), folderName + "/students.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveAbsences(), folderName + "/absences.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveParents(), folderName + "/parents.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveGroups(), folderName + "/groups.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveCourses(), folderName + "/courses.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveCoursesAssignments(), folderName +
                    "/courseAssignments.csv");
            CSVManager.getInstance().saveCSV(this.catalogService.saveGrades(), folderName + "/grades.csv");
        } else {
            DBManager.getInstance ().saveTable ("teachers", this.catalogService.saveTeachers());
            DBManager.getInstance ().saveTable ("students", this.catalogService.saveStudents());
            DBManager.getInstance ().saveTable ("absences", this.catalogService.saveAbsences());
            DBManager.getInstance ().saveTable ("parents", this.catalogService.saveParents());
            DBManager.getInstance ().saveTable ("groups", this.catalogService.saveGroups());
            DBManager.getInstance ().saveTable ("courses", this.catalogService.saveCourses());
            DBManager.getInstance ().saveTable ("courseAssignments", this.catalogService.saveCoursesAssignments());
            DBManager.getInstance ().saveTable ("grades", this.catalogService.saveGrades());
        }
    }
    
    private void commandLoadData (String folderName) throws Exception {
        if (this.dataSource.equals ("CSV")) {
            this.catalogService.clearData();

            this.catalogService.loadTeachers(CSVManager.getInstance().loadCSV(folderName + "/teachers.csv"));
            this.catalogService.loadGroups(CSVManager.getInstance().loadCSV(folderName + "/groups.csv"));
            this.catalogService.loadStudents(CSVManager.getInstance().loadCSV(folderName + "/students.csv"));
            this.catalogService.loadAbsences(CSVManager.getInstance().loadCSV(folderName + "/absences.csv"));
            this.catalogService.loadParents(CSVManager.getInstance().loadCSV(folderName + "/parents.csv"));
            this.catalogService.loadCourses(CSVManager.getInstance().loadCSV(folderName + "/courses.csv"));
            this.catalogService.loadCoursesAssignments(CSVManager.getInstance().loadCSV(folderName +
                    "/courseAssignments.csv"));
            this.catalogService.loadGrades(CSVManager.getInstance().loadCSV(folderName + "/grades.csv"));
        } else {
            this.catalogService.clearData();

            this.catalogService.loadTeachers (DBManager.getInstance ().loadTable ("teachers"));
            this.catalogService.loadGroups (DBManager.getInstance ().loadTable ("groups"));
            this.catalogService.loadStudents (DBManager.getInstance ().loadTable ("students"));
            this.catalogService.loadAbsences (DBManager.getInstance ().loadTable ("absences"));
            this.catalogService.loadParents (DBManager.getInstance ().loadTable ("parents"));
            this.catalogService.loadCourses (DBManager.getInstance ().loadTable ("courses"));
            this.catalogService.loadCoursesAssignments (DBManager.getInstance ().loadTable ("courseAssignments"));
            this.catalogService.loadGrades (DBManager.getInstance ().loadTable ("grades"));
        }
=======
    
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
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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
<<<<<<< HEAD
=======
//            System.out.println ("'" + line.get (0) + "'\t-\t" + line.get (1));
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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

<<<<<<< HEAD
    private void commandMotivateAbsence (String studentName, String absenceDate) throws SQLException {
        if (this.dataSource.equals("DB"))
            DBService.getInstance ().update ("absences", new String[] {"motivated"}, new String[] {"yes"},
                    "student='" + studentName + "' AND date='" + absenceDate + "'");
=======
    private void commandMotivateAbsence (String studentName, String absenceDate)
    {
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        this.catalogService.motivateAbsence (studentName, absenceDate);
    }


    private void commandAddGroup (String newName)
    {
        this.catalogService.addGroup (newName);
    }

<<<<<<< HEAD
    private void commandRemoveGroup (String groupName) throws SQLException {
        if (this.dataSource.equals ("DB")) {
            DBService.getInstance().delete ("groups", "name='" + groupName + "'");
            DBService.getInstance().update ("students", new String[] {"`group`"}, new String[] {"noGroup"},
                    "`group`='" + groupName + "'");
        }

=======
    private void commandRemoveGroup (String groupName)
    {
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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
<<<<<<< HEAD
        this.catalogService.addCourseToGroup (courseName, groupName);
=======
        this.catalogService.addCourseToGroup (groupName, courseName);
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
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
<<<<<<< HEAD

    private void commandCalculatePartialMark (String studentName, String course) throws Exception {
        try {
            float mark = catalogService.calcPartialMark (studentName, course);
            System.out.println ("Mark for student '" + studentName + "' at course '" + course + "': " + mark);
        } catch (Exception e) {
            throw e;
        }
    }

    private void commandCalculateAllMarks (String studentName) throws Exception {
        try {
            float mark = catalogService.calcAllMarks (studentName);
            System.out.println ("Total mark for student '" + studentName + "': " + mark);
        } catch (Exception e) {
            throw e;
        }
    }
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
}
