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
        String testFileName = "JunitTestFile1";
        //StringBuffer filePathBuffer = new StringBuffer(System.getProperty("user.dir")).append(File.separator).append("src").append(testFileName);
        List<Integer> integerSet = PowerSetHelper.parseInputSet(testFileName, true);
        Assert.assertEquals(integerSet, testIntList);
        //System.out.println(Arrays.toString(integerSet.toArray()));
    }

    @Test
    public void testPreparingSinglePowerSet() throws PowerSetException {
        BitSet bitSetofNumber = BitSet.valueOf(new long[] {7});
        String singlePowerSetString = PowerSetHelper.prepareSinglePowerSet(bitSetofNumber,testIntList);
        Assert.assertEquals("{ 1,2,3 }", singlePowerSetString);
        //System.out.println(singlePowerSetString);
    }

    @Test
    public void testPrintSubsets() throws PowerSetException {
        List<Integer> integerList = new ArrayList<Integer>(5);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        //integerList.add(4);
        //integerList.add(5);
        PowerSetHelper.getPowerSetContent(integerList);
    }
}