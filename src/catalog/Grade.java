package catalog;

import catalog.courses.Course;

public class Grade implements Comparable <Grade>
{
    private final float  value;
    private final Course course;

    public Grade(float newValue, Course newCourse)
    {
        this.value  = newValue;
        this.course = newCourse;
    }

    public float getValue () { return (this.value); }

    public Course getCourse () { return (this.course); }

    @Override
    public int compareTo(Grade otherGrade)
    {
        return Float.compare (this.value, otherGrade.getValue ());
    }
}
