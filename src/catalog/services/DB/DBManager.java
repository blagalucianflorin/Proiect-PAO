package catalog.services.DB;

import java.sql.SQLException;
import java.util.Vector;

public class DBManager {
    private static DBManager instance = null;

    private DBManager () {}

    public static DBManager getInstance ()
    {
        if (instance == null) {
            instance = new DBManager ();
        }

        return instance;
    }

    public void saveTable (String table, Vector <Vector <String>> data) throws SQLException {
        Vector <String> columns = data.get (0);

        if (!DBService.getInstance ().tableExists (table)) {
            System.out.println("Table '" + table + "' doesn't exist.");
            return;
        }

        DBService.getInstance ().delete (table);

        for (int i = 1; i < data.size (); i++)
            DBService.getInstance ().insert (table, columns.toArray(new String[0]), data.get (i).toArray(new String[0]));
    }

    public Vector <Vector<String>> loadTable (String table) throws SQLException {

        if (!DBService.getInstance ().tableExists (table)) {
            System.out.println("Table '" + table + "' doesn't exist.");
            return (new Vector <> ());
        }

        return (DBService.getInstance ().select (table));
    }
}
