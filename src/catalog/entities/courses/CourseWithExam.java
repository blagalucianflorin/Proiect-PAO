package catalog.entities.courses;

import catalog.entities.Grade;
import catalog.entities.Teacher;

import java.util.Vector;

public class CourseWithExam extends Course
{
    public CourseWithExam(String newName, Teacher newTeacher)
    {
        super (newName, newTeacher);
    }

    @Override
    public float calcFinalGrade (Vector<Grade> grades) throws Exception
    {
        float finalGrade = 0;

        if (grades.size () == 0)
            throw new Exception ("Nu există note la materia aleasă");

        if (grades.size () == 1)
            return (grades.get (0).getValue());

        for (int i = 0; i < grades.size () - 1; i++)
            finalGrade += grades.get (i).getValue ();

        finalGrade /= (grades.size() - 1);
        finalGrade *= 3;
        finalGrade += grades.get (grades.size () - 2).getValue ();
        finalGrade /= 4;

        return (finalGrade);
    }
}
