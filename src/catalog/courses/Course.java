package catalog.courses;

import catalog.Grade;
import catalog.Teacher;
import java.util.Vector;

abstract public class Course
{
    protected final String  name;
    protected final Teacher teacher;

    public Course (String newName, Teacher newTeacher)
    {
        this.name    = newName;
        this.teacher = newTeacher;
    }

    public String         getName () { return (this.name); }

    public Teacher        getTeacher () { return (this.teacher); }

    abstract public float calcFinalGrade (Vector <Grade> grades) throws Exception;
}
