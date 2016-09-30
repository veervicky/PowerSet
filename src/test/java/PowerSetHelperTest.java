import Exceptions.PowerSetException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Vicky on 28/09/16.
 */
public class PowerSetHelperTest {

    public static final List<Integer> testIntList = Arrays.asList(1, 2, 3);
    public static final String TEST_INPUT_FILE_NAME = "JunitTestFile1.txt";
    public static final String TEST_OUTPUT_FILE_NAME = "OutputTestFile1.txt";
    public static final String FORMATTED_SINGLE_POWERSET_TEST_STRING = "{ 1,2,3 }";
    public static final String FORMATTED_POWERSET_TEST_STRING = "{ }\n" +
            "{ 1 }\n" +
            "{ 2 }\n" +
            "{ 1,2 }\n" +
            "{ 3 }\n" +
            "{ 1,3 }\n" +
            "{ 2,3 }\n" +
            "{ 1,2,3 }\n";

    /**
     * Testing if parsing of the input file to create input list is proper.
     *
     * @throws PowerSetException
     */
    @Test
    public void testInputSetParsing() throws PowerSetException {
        List<Integer> integerSet = PowerSetHelper.parseInputSet(TEST_INPUT_FILE_NAME, true);
        Assert.assertEquals(integerSet, testIntList);
    }

    /**
     * Testing the format and bit set of single combination in a power set.
     *
     * @throws PowerSetException
     */
    @Test
    public void testPreparingSinglePowerSet() throws PowerSetException {
        BitSet bitSetOfNumber = BitSet.valueOf(new long[]{7});
        String singlePowerSetString = PowerSetHelper.prepareSinglePowerSet(bitSetOfNumber, testIntList);
        Assert.assertEquals(FORMATTED_SINGLE_POWERSET_TEST_STRING, singlePowerSetString);
    }

    /**
     * Testing format and content of all elements in power set.
     *
     * @throws PowerSetException
     */
    @Test
    public void testPowerSetContent() throws PowerSetException {
        List<Integer> integerList = new ArrayList<>(3);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        String powerSetContentString = PowerSetHelper.getPowerSetContent(integerList);
        Assert.assertEquals(FORMATTED_POWERSET_TEST_STRING, powerSetContentString);
    }

    /**
     * Test if the content is properly written to the given file.
     *
     * @throws PowerSetException
     */
    @Test
    public void testWritingFile() throws PowerSetException {
        PowerSetHelper.writePowerSet(TEST_OUTPUT_FILE_NAME, FORMATTED_SINGLE_POWERSET_TEST_STRING);
        File outputFile = new File(TEST_OUTPUT_FILE_NAME);

        String outputFileContentString = null;
        try {
            Scanner scanner = new Scanner(outputFile);
            outputFileContentString = scanner.nextLine();
            scanner.close();
        } catch (IOException e) {
            throw new PowerSetException(PowerSetHelper.IO_EXCEPTION_READING_FILE_STRING, e);
        }

        Assert.assertEquals(FORMATTED_SINGLE_POWERSET_TEST_STRING, outputFileContentString);
    }
}