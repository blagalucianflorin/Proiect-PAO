package catalog;

public class Absence
{
    private final String date;
    private boolean      motivated = false;

    public Absence (String newDate)
    {
        this.date = newDate;
    }

    public String getDate () { return (this.date); }

    public void motivate () { this.motivated = true; }

    public boolean isMotivated () { return (this.motivated); }
}
