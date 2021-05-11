package catalog.entities;

public class Teacher
{
    private final String name;

    public Teacher (String newName)
    {
        this.name = newName;
    }

    public String getName () { return (this.name); }
}
