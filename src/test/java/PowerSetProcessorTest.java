import Exceptions.PowerSetException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Vicky on 30/09/16.
 */
public class PowerSetProcessorTest {

    public static final String INVALID_TEST_FILE_NAME = "invalidFile.txt";

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
     * Testing Usage help message functionality if no arguments are provided by user.
     * The message is shown on the console
     *
     * @throws PowerSetException
     */
    @Test
    public void testUsageHelpMessage() throws PowerSetException {
        String[] nullArgs = {};
        PowerSetProcessor.main(nullArgs);
        Assert.assertEquals(PowerSetHelper.HELP_MESSAGE_STRING, outContent.toString().trim());
    }

    /**
     * Testing invalid file path message functionality if an invalid file path is given by user.
     * The invalid message is shown on the console.
     *
     * @throws PowerSetException
     */
    @Test
    public void testInvalidPath() throws PowerSetException {
        String[] invalidFilePath = {INVALID_TEST_FILE_NAME};
        PowerSetProcessor.main(invalidFilePath);
        Assert.assertEquals(PowerSetHelper.INVALID_FILE_PATH_STRING, outContent.toString().trim());
    }

    /**
     * Testing Main functionality end to end.
     * Check if the power set of input set is in proper format and correct.
     *
     * @throws PowerSetException
     */
    @Test
    public void testMain() throws PowerSetException {
        String[] args = null;
        try {
            File inputTestFile = new File(PowerSetProcessor.class.getClassLoader().getResource(PowerSetHelperTest.TEST_INPUT_FILE_NAME).getFile());
            args = new String[]{inputTestFile.getAbsolutePath()};
        } catch (NullPointerException ne) {
            throw new PowerSetException(PowerSetHelper.NULL_POINTER_EXCEPTION_STRING, ne);
        }
        PowerSetProcessor.main(args);
        File defaultOutputFile = new File(PowerSetHelper.DEFAULT_OUTPUT_FILE_NAME);

        StringBuilder outputFileContentStringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(defaultOutputFile);
            while (scanner.hasNextLine()) {
                outputFileContentStringBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            throw new PowerSetException(PowerSetHelper.IO_EXCEPTION_READING_FILE_STRING, e);
        }

        Assert.assertEquals(PowerSetHelperTest.FORMATTED_POWERSET_TEST_STRING, outputFileContentStringBuilder.toString());
    }
}