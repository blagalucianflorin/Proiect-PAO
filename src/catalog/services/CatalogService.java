package catalog.services;

import catalog.entities.courses.Course;
import catalog.entities.courses.CourseWithExam;
import catalog.entities.courses.CourseWithoutExam;
import catalog.entities.*;
<<<<<<< HEAD
import catalog.services.DB.DBService;

import java.sql.SQLException;
=======

>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
import java.util.Vector;

public class CatalogService
{
    private Vector <Student> students = new Vector <>();
    private Vector <Group>   groups   = new Vector <>();
    private Vector <Course>  courses  = new Vector <>();
    private Vector <Teacher> teachers = new Vector <>();

    public Vector <Vector <String>>  saveStudents ()
    {
        Vector <Vector <String>> retValues = new Vector <> ();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("name");
        headerLine.add ("age");
        headerLine.add ("group");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Student student : this.students)
        {
            Vector <String> newLine = new Vector <> ();

            newLine.add (student.getName ());
            newLine.add (Integer.toString (student.getAge ()));
            newLine.add (student.getGroup() == null ? "noGroup" : student.getGroup().getName());

            retValues.add (newLine);
        }

        return (retValues);
    }

    public void loadStudents (Vector <Vector <String>> students) throws Exception {
<<<<<<< HEAD
        if (students.size () == 0)
            return;
        students.remove (0); // Skip header line
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Vector <String> line : students)
        {
            this.addStudent (line.get (0), Integer.parseInt (line.get (1)));
            if (!line.get (2).equals ("noGroup"))
                this.addStudentToGroup (line.get (0), line.get (2));

        }
    }


    public Vector <Vector <String>> saveGroups ()
    {
        Vector <Vector <String>> retValues = new Vector<>();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("name");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Group group : this.groups)
        {
            Vector <String> newLine = new Vector<>();

            newLine.add (group.getName ());

            retValues.add (newLine);
        }

        return (retValues);
    }

    public void loadGroups (Vector <Vector <String>> groups)
    {
<<<<<<< HEAD
        if (groups.size () == 0)
            return;
        groups.remove (0); // Skip header line
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Vector <String> line : groups)
        {
            this.addGroup (line.get (0));
        }
    }


    public Vector <Vector <String>> saveCourses ()
    {
        Vector <Vector <String>> retValues = new Vector<>();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("name");
        headerLine.add ("teacher");
        headerLine.add ("withExam");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Course course : this.courses)
        {
            Vector <String> newLine = new Vector<>();

            newLine.add (course.getName ());
            newLine.add (course.getTeacher ().getName ());
            newLine.add (course instanceof CourseWithExam ? "yes" : "no");

            retValues.add (newLine);
        }

        return (retValues);
    }

    public void loadCourses (Vector <Vector <String>> courses) throws Exception
    {
<<<<<<< HEAD
        if (courses.size () == 0)
            return;
        courses.remove (0); // Skip header line
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Vector <String> line : courses)
            this.addCourse (line.get (0), line.get (1), line.get(2).equals ("yes"));
    }


    public Vector <Vector <String>> saveCoursesAssignments ()
    {
        Vector <Vector <String>> retValues = new Vector<>();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("courseName");
        headerLine.add ("groupName");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Group group : this.groups)
        {
            for (Course course : group.getCourses ()) {
                Vector<String> newLine = new Vector<>();

                newLine.add(course.getName());
                newLine.add(group.getName ());

                retValues.add(newLine);
            }
        }

        return (retValues);
    }

<<<<<<< HEAD
    public void loadCoursesAssignments (Vector <Vector <String>> coursesAssignements) throws Exception
    {
        if (coursesAssignements.size () == 0)
            return;
        coursesAssignements.remove (0); // Skip header line
        for (Vector <String> line : coursesAssignements)
=======
    public void loadCoursesAssignments (Vector <Vector <String>> courses) throws Exception
    {
        for (Vector <String> line : courses)
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
            this.addCourseToGroup (line.get (0), line.get (1));
    }



    public Vector <Vector <String>> saveGrades ()
    {
        Vector <Vector <String>> retValues = new Vector <> ();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("student");
        headerLine.add ("course");
        headerLine.add ("value");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Student student : this.students)
        {
            for (Grade grade : student.getGrades ()) {
                Vector<String> newLine = new Vector<>();

                newLine.add(student.getName ());
                newLine.add(grade.getCourse().getName ());
                newLine.add(String.valueOf(grade.getValue ()));

                retValues.add(newLine);
            }
        }

        return (retValues);
    }

<<<<<<< HEAD
    public void loadGrades (Vector <Vector <String>> grades) throws Exception
    {
        if (grades.size () == 0)
            return;
        grades.remove (0); // Skip header line
        for (Vector <String> line : grades)
=======
    public void loadGrades (Vector <Vector <String>> courses) throws Exception
    {
        for (Vector <String> line : courses)
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
            this.addGrade (line.get (0), line.get (1), Float.parseFloat(line.get (2)));
    }



    public Vector <Vector <String>> saveTeachers ()
    {
        Vector <Vector <String>> retValues = new Vector <> ();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("name");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Teacher teacher : this.teachers)
        {
            Vector <String> newLine = new Vector <> ();

            newLine.add (teacher.getName ());

            retValues.add (newLine);
        }

        return (retValues);
    }

    public void loadTeachers (Vector <Vector <String>> teachers)
    {
<<<<<<< HEAD
        if (teachers.size () == 0)
            return;
        teachers.remove (0); // Skip header line
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Vector <String> line : teachers)
        {
            this.addTeacher (line.get (0));
        }
    }


    public Vector <Vector <String>> saveAbsences ()
    {
        Vector <Vector <String>> retValues = new Vector <> ();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("student");
        headerLine.add ("date");
        headerLine.add ("motivated");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Student student : this.students)
        {
            for (Absence absence : student.getAbsences ())
            {
                Vector <String> newLine = new Vector <> ();

                newLine.add (student.getName ());
                newLine.add (absence.getDate ());
                newLine.add (absence.isMotivated () ? "yes" : "no");

                retValues.add (newLine);
            }
        }

        return (retValues);
    }

    public void loadAbsences (Vector <Vector <String>> absences)
    {
<<<<<<< HEAD
        if (absences.size () == 0)
            return;
        absences.remove (0); // Skip header line
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Vector <String> line : absences) {
            for (Student student : this.students)
                if (student.getName ().equals (line.get (0))) {
                    student.addAbsence(new Absence(line.get (1)));
                    if (line.get (2).equals ("yes"))
                        student.motivateAbsence (line.get (1));
                }
        }
    }


    public Vector <Vector <String>> saveParents ()
    {
        Vector <Vector <String>> retValues = new Vector <> ();
<<<<<<< HEAD
        Vector <String> headerLine         = new Vector <> ();

        headerLine.add ("student");
        headerLine.add ("name");
        headerLine.add ("phone");
        retValues.add (headerLine);
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        for (Student student : this.students)
        {
            for (Parent parent : student.getParents ())
            {
                Vector <String> newLine = new Vector <> ();

                newLine.add (student.getName ());
                newLine.add (parent.getName ());
                newLine.add (parent.getPhone ());

                retValues.add (newLine);
            }
        }

        return (retValues);
    }

    public void loadParents (Vector <Vector <String>> parents)
    {
<<<<<<< HEAD
        if (parents.size () == 0)
            return;
        parents.remove (0); // Skip header line
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Vector <String> line : parents) {
            for (Student student : this.students) {
                if (student.getName().equals(line.get(0))) {
                    student.addParent(new Parent(line.get(1), line.get(2)));
                }
            }
        }
    }




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

    public void             addStudentToGroup (String studentName, String groupName) throws Exception
    {
        Student student = null;
        Group   group   = null;

        for (Group auxGroup : this.groups)
            if (auxGroup.getName ().equals (groupName))
                group = auxGroup;

        for (Student auxStudent : this.students)
            if (auxStudent.getName ().equals (studentName))
                student = auxStudent;

        if (student == null)
            throw new Exception ("Student doesn't exist");

        if (group == null)
            throw new Exception ("Group doesn't exist");

        student.setGroup (group);
        if (!group.getStudents ().contains (student))
            group.addStudent (student);
    }


    public void clearData ()
    {
        this.students = new Vector <>();
        this.groups   = new Vector <>();
        this.teachers = new Vector <>();
        this.courses  = new Vector <>();
    }


    public void addParent (String studentName, String parentName, String parentPhone)
    {
        for (Student student : this.students)
            if (student.getName().equals(studentName)) {
                student.addParent (new Parent(parentName, parentPhone));
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


    public void           addGroup (String newName) { this.groups.add (new Group (newName)); }

<<<<<<< HEAD
    public void           removeGroup (String groupName) {
=======
    public void           removeGroup (String groupName)
    {
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
        for (Group group : this.groups)
            if (group.getName ().equals (groupName))
            {
                for (Student student : this.students)
                    if (student.getGroup () == group)
                        student.setGroup (null);
                break;
            }
    }

    public Vector <Group> getGroups () { return (this.groups); }


    public void            addCourse (String newName, String teacherName, boolean withExam) throws Exception
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

    public void            addCourseToGroup (String courseName, String groupName) throws Exception
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

    public Vector <Course> getCourses () { return (this.courses); }


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
                boolean found = false;
                for (Course course :student.getGroup ().getCourses ())
                    if (course.getName ().equals (courseName))
                    {
                        student.addGrade(new Grade(value, auxCourse));
                        found = true;
                    }
                if (!found)
                    throw new Exception ("Student doesn't study that course");
                break;
            }
    }


    public void             addTeacher (String newName) { this.teachers.add (new Teacher (newName)); }

    public Vector <Teacher> getTeachers () { return (this.teachers); }


    public float calcPartialMark (String studentName, String courseName) throws Exception
    {
        float          mark;
        Vector <Grade> grades    = new Vector <>();
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
<<<<<<< HEAD
            throw new Exception ("Course doesn't exist");
=======
            throw new Exception ("Nu există cursul");
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        mark = auxCourse.calcFinalGrade (grades);

        return (mark);
    }

    public float calcAllMarks (String studentName) throws Exception
    {
        float           mark    = 0;
        Vector <Course> courses;
<<<<<<< HEAD
        int courses_count       = 1;
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf


        for (Student student : this.students)
            if (student.getName ().equals (studentName))
            {
                courses = student.getGroup ().getCourses ();
<<<<<<< HEAD
                courses_count = courses.size ();
=======
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

                for (Course course : courses)
                {
                    try
                    {
                        mark += this.calcPartialMark (studentName, course.getName ());
                    } catch (Exception e)
                    {
<<<<<<< HEAD
                        throw new Exception ("Can't calculate mark for " + studentName + " at course "
=======
                        throw new Exception ("Nu se poate calcula media elevului " + studentName + " la materia "
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
                                + course.getName ());
                    }
                }

                break;
            }

<<<<<<< HEAD
        return (mark / courses_count);
    }
}
=======
        return (mark);
    }
}
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf
