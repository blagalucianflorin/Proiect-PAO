package catalog.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class SettingsManager {
    private static SettingsManager instance                  = null;
    private static final Hashtable <String, String> settings = new Hashtable <> ();
    private static FileWriter settingsWriter;

    private SettingsManager () throws IOException {
        File historyFile      = new File(".settings.cfg");

        if (historyFile.exists ()) {
            Scanner historyReader = new Scanner(historyFile);

            while (historyReader.hasNextLine()) {
                String line = historyReader.nextLine();
                String[] values = line.split("=");
                settings.put(values[0], values[1]);
            }
            historyReader.close();
        }
        settingsWriter = new FileWriter(".settings.cfg", true);
    }

    public static SettingsManager getInstance () throws IOException {
        if (instance == null)
            instance = new SettingsManager();

        return (instance);
    }

    private void dumpSettings () throws IOException {
        settingsWriter = new FileWriter (".settings.cfg", false);

        for (String key : settings.keySet ())
            settingsWriter.write (key + "=" + settings.get (key) + "\n");
        settingsWriter.close ();
        settingsWriter = new FileWriter (".settings.cfg", true);
    }

    public void set (String setting, String value) throws IOException {
        setting = setting.toUpperCase();

        if (settings.containsKey(setting)) {
            settings.put (setting, value);
            dumpSettings ();
        } else {
            settings.put (setting, value);
            settingsWriter.write (setting + "=" + settings.get (setting) + "\n");
        }
    }

    public String get (String setting) {
        setting = setting.toUpperCase ();

        return settings.getOrDefault (setting, "");
    }

    public void close () throws IOException {
        settingsWriter.close ();
    }
}
