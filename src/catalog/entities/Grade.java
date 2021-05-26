package catalog.entities;

import catalog.entities.courses.Course;

public class Grade
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
}
