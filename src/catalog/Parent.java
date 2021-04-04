package catalog;

public class Parent
{
    private final String name;
    private String       phone;

    public Parent (String newName, String newPhone)
    {
        this.name  = newName;
        this.phone = newPhone;
    }

    public String getName () { return (this.name); }

    public void   setPhone (String newPhone) { this.phone = newPhone; }

    public String getPhone () { return (this.phone); }
}
