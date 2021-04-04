package catalog;

import catalog.courses.Course;
import catalog.courses.CourseWithExam;
import catalog.courses.CourseWithoutExam;
import java.util.Vector;

public class Service
{
    private final Vector <Student> students = new Vector <Student>();
    private final Vector <Group>   groups   = new Vector <Group>();
    private final Vector <Course>  courses  = new Vector <Course>();
    private final Vector <Teacher> teachers = new Vector <Teacher>();

    public void             addStudent (String newName, int newAge)
    {
        this.students.add (new Student (newName, newAge));
    }

    public Vector <Student> getStudents () { return (this.students); }

    public void             removeStudent (String oldName)
    {
        for (Student student : this.students)
            if (student.getName ().equals (oldName))
            {
                this.students.removeElement (student);
                break;
            }
    }


    public void addAbsence (String studentName, String absenceDate)
    {
        for (Student student : this.students)
            if (student.getName ().equals (studentName))
            {
                student.addAbsence (new Absence (absenceDate));
                break;
            }
    }

    public void motivateAbsence (String studentName, String absenceDate)
    {
        for (Student student : this.students)
            if (student.getName ().equals (studentName))
            {
                for (Absence absence : student.getAbsences ())
                    if (absence.getDate ().equals (absenceDate))
                    {
                        absence.motivate ();
                        break;
                    }
                break;
            }
    }


    public void addGroup (String newName) { this.groups.add (new Group (newName)); }

    public void removeGroup (String groupName)
    {
        for (Group group : this.groups)
            if (group.getName ().equals (groupName))
            {
                for (Student student : this.students)
                    if (student.getGroup () == group)
                        student.setGroup (null);
                break;
            }
    }


    public void addCourse (String newName, String teacherName, boolean withExam) throws Exception
    {
        Teacher auxTeacher = null;

        for (Teacher teacher : this.teachers)
            if (teacher.getName ().equals (teacherName))
            {
                auxTeacher = teacher;
                break;
            }

        // TODO make a more specific exception
        if (auxTeacher == null)
            throw new Exception ("Teacher doesn't exist");

        if (withExam)
        {
            this.courses.add (new CourseWithExam (newName, auxTeacher));
        }
        else
        {
            this.courses.add (new CourseWithoutExam (newName, auxTeacher));
        }
    }

    public void addGrade (String studentName, String courseName, float value) throws Exception
    {
        Course auxCourse = null;

        for (Course course : this.courses)
            if (course.getName ().equals (courseName))
            {
                auxCourse = course;
                break;
            }

        // TODO make a more specific exception
        if (auxCourse == null)
            throw new Exception ("Course doesn't exist");

        for (Student student : this.students)
            if (student.getName ().equals (studentName))
            {
                student.addGrade (new Grade (value, auxCourse));
                break;
            }
    }

    public void             addTeacher (String newName) { this.teachers.add (new Teacher (newName)); }

    public Vector <Teacher> getTeachers () { return (this.teachers); }

    public void addCourseToGroup (String groupName, String courseName) throws Exception
    {
        Course auxCourse = null;

        for (Course course : this.courses)
            if (course.getName ().equals (courseName))
            {
                auxCourse = course;
                break;
            }

        // TODO make a more specific exception
        if (auxCourse == null)
            throw new Exception ("Course doesn't exist");

        for (Group group : this.groups)
            if (group.getName ().equals (groupName))
            {
                group.addCourse (auxCourse);
                break;
            }
    }


    public float calcPartialMark (String studentName, String courseName) throws Exception
    {
        float          mark      = 0;
        Vector <Grade> grades    = new Vector <Grade>();
        Course         auxCourse = null;

        for (Student student : this.students)
            if (student.getName ().equals (studentName))
            {
                for (Grade grade : student.getGrades ())
                {
                    if (grade.getCourse ().getName ().equals (courseName))
                    {
                        grades.add(grade);
                        auxCourse = grade.getCourse ();
                    }
                }
                break;
            }

        if (auxCourse == null)
            throw new Exception ("Nu există cursul");

        mark = auxCourse.calcFinalGrade (grades);

        return (mark);
    }

    public float calcAllMarks (String studentName) throws Exception
    {
        float           mark    = 0;
        Vector <Course> courses = new Vector <Course>();


        for (Student student : this.students)
            if (student.getName ().equals (studentName))
            {
                courses = student.getGroup ().getCourses ();

                for (Course course : courses)
                {
                    try
                    {
                        mark += this.calcPartialMark (studentName, course.getName ());
                    } catch (Exception e)
                    {
                        throw new Exception ("Nu se poate calcula media elevului " + studentName +
                                             " la materia " + course.getName ());
                    }
                }

                break;
            }

        return (mark);
    }
}
