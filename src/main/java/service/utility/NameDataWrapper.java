package service.utility;

import Util.JSONHandler;
import Util.ReadString;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

/**
 * The way to interface with the JSON name data (female, male, and surnames).
 */
public class NameDataWrapper {
    /**
     * File location of female name data.
     */
    private static final String fNameFileName = "./json/fnames.json";
    /**
     * File location of male name data.
     */
    private static final String mNameFileName = "./json/mnames.json";
    /**
     * File location of surname name data.
     */
    private static final String sNameFileName = "./json/snames.json";

    public static NameData fNameData;
    public static NameData mNameData;
    public static NameData sNameData;

    private static final Random random = new Random();

    static {
        setup();
    }

    /**
     * Read all the names, set up the arrays to hold them.
     */
    private static void setup() {
        try {
            Reader reader = new FileReader(fNameFileName);
            String locationsData = ReadString.readString(reader);
            fNameData = (NameData) JSONHandler.jsonToObject(locationsData, NameData.class);

            reader = new FileReader(mNameFileName);
            locationsData = ReadString.readString(reader);
            mNameData = (NameData) JSONHandler.jsonToObject(locationsData, NameData.class);

            reader = new FileReader(sNameFileName);
            locationsData = ReadString.readString(reader);
            sNameData = (NameData) JSONHandler.jsonToObject(locationsData, NameData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets a random female name from the list of random names.
     * @return a random female name.
     */
    public static String fRandom() {
        return fNameData.getData()[random.nextInt(fNameData.getData().length)];
    }
    /**
     * Gets a random male name from the list of random names.
     * @return a random male name.
     */
    public static String mRandom() {
        return mNameData.getData()[random.nextInt(mNameData.getData().length)];
    }
    /**
     * Gets a random surname name from the list of random names.
     * @return a random surname name.
     */
    public static String sRandom() {
        return sNameData.getData()[random.nextInt(sNameData.getData().length)];
    }
}
