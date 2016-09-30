import Exceptions.PowerSetException;

import java.io.File;
import java.util.List;

/**
 * Created by Vicky on 29/09/16.
 */
public class PowerSetProcessor {

    /**
     * Main class: Entry point of the program.
     * 1) It takes in the input file path from command line
     * 2) If no arg is given then it prints usage help
     * 3) If invaild path is given then it shows appropriate error.
     * 4) Gets the power set of a valid set and writes to an output file.
     *
     * @param args
     */
    public static void main(String[] args) {
        File inputFile = null;
        if (args.length > 0) {
            inputFile = new File(args[0]);
            if (!inputFile.exists()) {
                System.out.println(PowerSetHelper.INVALID_FILE_PATH_STRING);
            } else {
                try {
                    List<Integer> inputSetList = PowerSetHelper.parseInputSet(args[0], false);
                    String outputContent = PowerSetHelper.getPowerSetContent(inputSetList);
                    PowerSetHelper.writePowerSet("output.txt", outputContent);
                } catch (PowerSetException pse) {
                    pse.printStackTrace();;
                }
            }
        } else {
            System.out.println(PowerSetHelper.HELP_MESSAGE_STRING);
        }
    }
}
