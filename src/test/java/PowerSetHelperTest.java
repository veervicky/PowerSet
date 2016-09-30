import Exceptions.PowerSetException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * Created by Vicky on 28/09/16.
 */
public class PowerSetHelperTest {

    public static final List<Integer> testIntList = Arrays.asList(1, 2, 3);
    public static final String TEST_FILE_NAME = "JunitTestFile1.txt";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    /**
     * Setting up error and output stream before the test starts.
     * This enables to test the console error and output during tests.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Clearing out error and output streams at the end of the tests.
     * This makes sure that there is no irrelevant messages shown on the console while running the main program.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Testing if parsing of the input file to create input list is proper.
     *
     * @throws PowerSetException
     */
    @Test
    public void testInputSetParsing() throws PowerSetException {
        List<Integer> integerSet = PowerSetHelper.parseInputSet(TEST_FILE_NAME, true);
        Assert.assertEquals(integerSet, testIntList);
    }

    /**
     * Testing if the format and bit set of single combination in a power set is correct.
     *
     * @throws PowerSetException
     */
    @Test
    public void testPreparingSinglePowerSet() throws PowerSetException {
        BitSet bitSetOfNumber = BitSet.valueOf(new long[]{7});
        String singlePowerSetString = PowerSetHelper.prepareSinglePowerSet(bitSetOfNumber, testIntList);
        Assert.assertEquals("{ 1,2,3 }", singlePowerSetString);
    }

    /**
     * Testing Usage help message functionality if no arguments are provided by user.
     * The message is shown on the console
     *
     * @throws PowerSetException
     */
    @Test
    public void testUsageHelpMessage() throws PowerSetException {
        String[] nullArgs = {};
        PowerSetProcessor.main(nullArgs);
        Assert.assertEquals(PowerSetHelper.HELP_MESSAGE_STRING, outContent.toString());
    }

    /**
     * Testing invalid file path message functionality if an invalid file path is given by user.
     * The invalid message is shown on the console.
     *
     * @throws PowerSetException
     */
    @Test
    public void testInvalidPath() throws PowerSetException {
        String[] invalidFilePath = {"abc.txt"};
        PowerSetProcessor.main(invalidFilePath);
        Assert.assertEquals(PowerSetHelper.INVALID_FILE_PATH_STRING, outContent.toString());
    }
}