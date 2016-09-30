import java.io.File;
import java.util.List;

/**
 * Created by Vicky on 29/09/16.
 */
public class PowerSetProcessor {
    public static void main( String[] args ) {
        File inputFile = null;
        if(args.length > 0) {
            inputFile = new File(args[0]);
            if (!inputFile.exists()) {
                System.out.println("Please check the input file path");
            } else {
                List<Integer> inputSetList = PowerSetHelper.parseInputSet(args[0],false);
                String outputContent = PowerSetHelper.getPowerSetContent(inputSetList);
                PowerSetHelper.writePowerSet("output.txt",outputContent);
            }
        } else {
            System.out.println("Usage: java powerset.jar <input_file_path>");
        }
    }
}
