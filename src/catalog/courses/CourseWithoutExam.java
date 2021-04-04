package catalog.courses;

import catalog.Grade;
import catalog.Teacher;

import java.util.Vector;

public class CourseWithoutExam extends Course
{
    public CourseWithoutExam(String newName, Teacher newTeacher)
    {
        super (newName, newTeacher);
    }

    @Override
    public float calcFinalGrade (Vector<Grade> grades) throws Exception
    {
        float finalGrade = 0;

        if (grades.size () == 0)
            throw new Exception ("Nu există note la materia aleasă");

        for (Grade grade : grades)
            finalGrade += grade.getValue();

        finalGrade /= grades.size ();

        return (finalGrade);
    }
}
