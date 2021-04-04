package catalog;

import java.util.Vector;

public class Student
{
    private final String           name;
    private int                    age;
    private final Vector <Parent>  parents  = new Vector <Parent>();
    private final Vector <Absence> absences = new Vector <Absence>();
    private final Vector <Grade>   grades   = new Vector <Grade>();
    private Group                  group    = null;

    public Student (String newName, int newAge)
    {
        this.name = newName;
        this.setAge (newAge);
    }

    public String getName () { return (this.name); }


    public void setAge (int newAge) { this.age = newAge; }

    public int  getAge () { return (this.age); }


    public void            addParent (Parent newParent) { this.parents.add (newParent); }

    public Vector <Parent> getParents () { return (this.parents); }


    public void             addAbsence (Absence newAbsence) { this.absences.add (newAbsence); }

    public Vector <Absence> getAbsences () { return (this.absences); }

    public void             motivateAbsence (String date)
    {
        for (Absence abs : this.absences)
            if (abs.getDate ().equals (date))
            {
                abs.motivate ();
                break;
            }
    }


    public void  setGroup (Group newGroup) { this.group = newGroup; }

    public Group getGroup () { return (this.group); }


    public void           addGrade (Grade newGrade) { this.grades.add (newGrade); }

    public Vector <Grade> getGrades () { return (this.grades); }
}
