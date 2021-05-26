package catalog.services.DB;

import java.sql.*;
import java.util.Vector;

public class DBService
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/paoproject" +
                                            "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER   = "paoproject";
    private static final String PASS   = "paoproject";
    private static Connection conn     = null;
    private static DBService instance  = null;

    private DBService() {}

    /**
     * Gets an instance of DBManager.
     *
     * @return An instance of a DBManager.
     */
    public static DBService getInstance ()
    {
        if (instance == null) {
            instance = new DBService ();

            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    public Vector <Vector <String>> select (String table) throws SQLException {
        return this.select (table, new String[] {}, "");
    }

    public Vector <Vector <String>> select (String table, String condition) throws SQLException {
        return this.select (table, new String[] {}, condition);
    }

    public Vector <Vector <String>> select (String table, String[] columns) throws SQLException {
        return this.select (table, columns, "");
    }

    public Vector <Vector <String>> select (String table, String[] columns, String condition) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT ");

        if (columns.length != 0)
            for (int i = 0; i < columns.length; i++)
                sql.append(columns[i]).append(" ").append(i != columns.length - 1 ? ", " : "");
        else
            sql.append("* ");
        sql.append("FROM `").append(table).append("` ").append(!condition.equals("") ? "WHERE " + condition : "");

        Statement stmt                          = conn.createStatement();
        ResultSet rs                            = stmt.executeQuery(sql.toString());
        ResultSetMetaData rsMeta                = rs.getMetaData();
        Vector <Vector <String>> resSet = new Vector<>();
        boolean headerRowAdded                  = false;

        while (rs.next ()) {
            Vector <String> newRow = new Vector<> ();

            if (!headerRowAdded) {
                Vector <String> headerRow = new Vector<> ();

                for (int i = 0; i < rsMeta.getColumnCount(); i++) {
                    headerRow.add (rsMeta.getColumnName (i + 1));
                }
                resSet.add (headerRow);
                headerRowAdded = true;
            }

            for (int i = 0; i < rsMeta.getColumnCount(); i++) {
                newRow.add (rs.getString (rsMeta.getColumnName (i + 1)));
            }
            resSet.add (newRow);
        }

        return (resSet);
    }


    public void delete (String table) throws SQLException {
        this.delete (table, "");
    }

    public void delete (String table, String condition) throws SQLException {
        String sql     = "DELETE FROM `" + table + "` " + (!condition.equals ("") ? "WHERE " + condition : "");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }


//    Disabled currently as it doesn't support autoincrement columns
//    public void insert (String table, String[] values) throws SQLException {
//        this.insert (table, new String[] {}, values);
//    }

    public void insert (String table, String[] columns, String[] values) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO `" + table + "` ");
        if (columns.length != 0) {
            sql.append("(");
            for (int i = 0; i < columns.length; i++)
                sql.append("`").append(columns[i]).append(i != columns.length - 1 ? "`, " : "`) ");
        }
        sql.append("VALUES (");
        for (int i = 0; i < values.length; i++)
            sql.append("'").append(values[i]).append("'").append(i != values.length - 1 ? ", " : ")");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql.toString());
    }


    public void update (String table, String[] columns, String[] values) throws SQLException {
        this.update (table, columns, values, "");
    }

    public void update (String table, String[] columns, String[] values, String condition) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE `" + table + "` SET ");

        for (int i = 0; i < columns.length; i++)
            sql.append(columns[i]).append("='").append(values[i]).append("'").append(i != columns.length - 1 ? "," : "");
        if (condition.length () != 0)
            sql.append(" WHERE ").append(condition);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql.toString());
    }

    public boolean tableExists (String table) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, table, new String[] {"TABLE"});

        return resultSet.next();
    }
}
