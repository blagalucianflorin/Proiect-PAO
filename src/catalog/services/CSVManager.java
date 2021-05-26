package catalog.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;


public class CSVManager
{
    private CSVManager () {}

    private static CSVManager instance = null;

    /**
     * Gets an instance of CSVManager.
     *
     * @return An instance of a CSVManager.
     */
    public static CSVManager getInstance ()
    {
        if (instance == null)
        {
            instance = new CSVManager();
        }

        return instance;
    }

    /**
     * Writes a .csv file from the values supplied in the 'lines' argument.
     * Overwrites 'fileName' if file already exists.
     *
     * @param lines    List of file lines. Each line should be a list of values.
     * @param fileName Output file name.
     */
    public void saveCSV (Vector <Vector <String>> lines, String fileName)
    {
        FileWriter myWriter;

        try
        {
            if (fileName.lastIndexOf ("/") != -1) {
                String dirPath     = fileName.substring(0, fileName.lastIndexOf("/"));
                File file          = new File (dirPath);
                boolean createdDir = file.mkdir();

//                if (!createdDir)
//                    System.err.println ("Couldn't create new directory, maybe it already exists");
            }

            myWriter = new FileWriter(fileName, false);
//            System.out.println ("Saving to '" + fileName + "'...");

            // Write header row
            Vector <String> headerRow = lines.get (0);
            for (int i = 0; i <headerRow.size (); i++)
                myWriter.write (headerRow.get (i) + (i != headerRow.size () - 1 ? "," : ""));
            myWriter.write ("\n");
            lines.remove (0);

            for (Vector <String> line : lines)
            {
                for (int i = 0; i < line.size (); i++) {
                    myWriter.write (line.get (i) + (i != line.size () - 1 ? "," : ""));
                }
                myWriter.write ("\n");
            }

            myWriter.close();
        } catch (IOException e) {
//            System.out.println ("Couldn't write to file.");
            e.printStackTrace ();
        }
    }

    /**
     * Reads a .csv file and returns a list of lines. Each line is a list of
     * the values contained between the commas.
     *
     * @param fileName Input file name.
     * @return         Vector of file lines, each line's a vector of values.
     */
    public Vector <Vector <String>> loadCSV (String fileName)
    {
        Vector <Vector <String>> retValues = new Vector <> ();
        File    myObj;
        Scanner myReader;

        try
        {
            myObj = new File(fileName);
//            System.out.println ("Reading from '" + fileName + "'...");
            myReader = new Scanner(myObj);

            // Check if header row exists
            if (!myReader.hasNextLine ())
                return (retValues);

            String line               = myReader.nextLine ();
            String[] values           = line.split (",");
            Vector<String> headerRow  = new Vector <> (Arrays.asList(values));
            retValues.add (headerRow);

            while (myReader.hasNextLine ())
            {
                line                    = myReader.nextLine ();
                values                  = line.split (",");
                Vector<String> newLine  = new Vector <> (Arrays.asList (values));
                retValues.add (newLine);
            }

            myReader.close ();
        } catch (FileNotFoundException e) {
//            System.out.println ("File doesn't exist");
//            e.printStackTrace ();
        }

        return (retValues);
    }
}