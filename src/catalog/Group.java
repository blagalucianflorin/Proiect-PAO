package catalog;

import catalog.courses.Course;
import java.util.Vector;

public class Group
{
    private final String           name;
    private final Vector <Student> students = new Vector <Student>();
    private final Vector <Course>  courses  = new Vector <Course>();

    public Group (String newName)
    {
        this.name = newName;
    }

    public String           getName () { return (this.name); }

    public void             addStudent (Student newStudent) { this.students.add (newStudent); }

    public Vector <Student> getStudents () { return (this.students); }

    public void             removeStudent (int index) { this.students.remove (index); }

    public void             addCourse (Course newCourse) { this.courses.add (newCourse); }

    public Vector <Course>  getCourses () { return (this.courses); }
}
