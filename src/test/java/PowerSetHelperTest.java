import Exceptions.PowerSetException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * Created by Vicky on 28/09/16.
 */
public class PowerSetHelperTest {

    public static final List<Integer> testIntList = Arrays.asList(1, 2, 3);

    @Test
    public void testInputSetParsing() throws PowerSetException {
        String testFileName = "JunitTestFile1.txt";
        List<Integer> integerSet = PowerSetHelper.parseInputSet(testFileName, true);
        Assert.assertEquals(integerSet, testIntList);
    }

    @Test
    public void testPreparingSinglePowerSet() throws PowerSetException {
        BitSet bitSetofNumber = BitSet.valueOf(new long[] {7});
        String singlePowerSetString = PowerSetHelper.prepareSinglePowerSet(bitSetofNumber,testIntList);
        Assert.assertEquals("{ 1,2,3 }", singlePowerSetString);
    }

    @Test
    public void testPrintSubsets() throws PowerSetException {
    }
}