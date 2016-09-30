import Exceptions.PowerSetException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Vicky on 28/09/16.
 */
public class PowerSetHelper {

    public static final String START_CURLY_BRACKETS_STRING = "{ ";
    public static final String END_CURLY_BRACKETS_STRING = " }";
    public static final String COMMA_STRING = ",";
    public static final String NULL_POINTER_EXCEPTION_STRING = "Null Pointer Exception";
    public static final String EMPTY_INPUT_SET_STRING = "Empty Input Set";
    public static final String EXCEPTION_READING_FILE_STRING = "Exception: Reading a File";
    public static final String IO_EXCEPTION_READING_FILE_STRING = "IOException: Reading a File";
    public static final String OUTPUT_FILE_ERROR_STRING = "Unable to create output file";
    public static final String IO_EXCEPTION_OUTPUT_FILE_STRING = "IOException: Writing output file";
    public static final String IO_EXCEPTION_CLOSING_BW_STRING = "IOException: Unable to close BufferWriter";

    public static final String HELP_MESSAGE_STRING = "Usage: java -jar PowerSet-1.0-jar-with-dependencies.jar <input_file_path>\n";
    public static final String INVALID_FILE_PATH_STRING = "Please check input file path\n";

    /**
     * Parses the Input set from an input file or from a resource file
     *
     * @param filePath
     * @param isResourceFile
     * @return
     * @throws PowerSetException
     */
    public static List<Integer> parseInputSet(String filePath, Boolean isResourceFile) throws PowerSetException {
        //Get file from resources folder
        File inputFile = null;
        try {
            if (isResourceFile) {
                ClassLoader classLoader = new PowerSetHelper().getClass().getClassLoader();
                inputFile = new File(classLoader.getResource(filePath).getFile());
            } else {
                inputFile = new File(filePath);
            }
        } catch (NullPointerException ne) {
            throw new PowerSetException(NULL_POINTER_EXCEPTION_STRING, ne);
        } catch (Exception e) {
            throw new PowerSetException(EXCEPTION_READING_FILE_STRING, e);
        }

        String line = null;
        try {
            Scanner scanner = new Scanner(inputFile);
            line = scanner.nextLine();
            scanner.close();
        } catch (IOException e) {
            throw new PowerSetException(IO_EXCEPTION_READING_FILE_STRING, e);
        }
        //parsing "1,2,3" from a file format of "{1,2,3}"
        String[] setStringArray = line.split("\\{")[1].split("\\}")[0].split(",");
        List<Integer> integerList = new ArrayList<Integer>();
        for (String number : setStringArray) {
            integerList.add(Integer.parseInt(number));
        }
        return integerList;
    }

    /**
     * Prepares Power set string in the proper format for a given bit set corresponding to the input list.
     *
     * @param bitSet
     * @param list
     * @return
     * @throws PowerSetException
     */
    public static String prepareSinglePowerSet(BitSet bitSet, List<Integer> list) throws PowerSetException {
        // check to see if the list is null
        if (list == null) {
            throw new PowerSetException(EMPTY_INPUT_SET_STRING);
        }

        StringBuilder stringBuilder = new StringBuilder(START_CURLY_BRACKETS_STRING);
        for (int i = 0; i < list.size(); i++) {
            if (bitSet.get(i)) {
                // adding "," after each integer
                stringBuilder.append(list.get(i)).append(COMMA_STRING);
            }
        }
        //removing last "," for proper formatting
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(END_CURLY_BRACKETS_STRING);
        return stringBuilder.toString();
    }

    /**
     * It calculates Power set for all possible combinations given an input list.
     * It also assemble the final Content to be written on the output file.
     *
     * @param list
     * @return
     * @throws PowerSetException
     */
    public static String getPowerSetContent(List<Integer> list) throws PowerSetException {
        // maximum number of combinations possible for n items is 2^n.
        long max = (long) Math.pow(2, list.size());
        StringBuilder powerSetContentBuffer = new StringBuilder();
        for (long currentNumber = 0; currentNumber < max; currentNumber++) {
            // Convert number to bit set using native java method available.
            BitSet numberConvertedBitSet = BitSet.valueOf(new long[]{currentNumber});

            // Use Binary Value of the combination to print its corresponding subset.
            powerSetContentBuffer.append(prepareSinglePowerSet(numberConvertedBitSet, list)).append("\n");
        }
        return powerSetContentBuffer.toString();
    }

    /**
     * This method writes the power set to the output file.
     * 1) Checks if file exists otherwise creates it. Throws Exception if unable to create output file.
     * 2) Writes to the file and closes all open resources cleanly.
     *
     * @param filePath
     * @param content
     * @throws PowerSetException
     */
    public static void writePowerSet(String filePath, String content) throws PowerSetException {
        File file = new File(filePath);
        BufferedWriter outputBufferWriter = null;
        try {
            // if file doesnt exists, then create it else throw power set exception.
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new PowerSetException(OUTPUT_FILE_ERROR_STRING);
                }
            }
            FileWriter outputFileWriter = new FileWriter(file.getAbsoluteFile());
            outputBufferWriter = new BufferedWriter(outputFileWriter);
            outputBufferWriter.write(content);
            outputBufferWriter.close();
        } catch (IOException e) {
            throw new PowerSetException(IO_EXCEPTION_OUTPUT_FILE_STRING, e);
        } finally {
            if (outputBufferWriter != null) {
                try {
                    outputBufferWriter.close();
                } catch (IOException e) {
                    throw new PowerSetException(IO_EXCEPTION_CLOSING_BW_STRING, e);
                }
            }
        }
    }
}
