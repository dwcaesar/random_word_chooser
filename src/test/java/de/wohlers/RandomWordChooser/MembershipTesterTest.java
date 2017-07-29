package de.wohlers.RandomWordChooser;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Wohlers, mydata GmbH
 */
public class MembershipTesterTest {
    @org.junit.Test
    public void testMembership() throws Exception {
        List<int[]> members = Arrays.asList(new int[][]{{18, 20},{45, 2},{61, 12},{37, 6},{21, 21},{78, 9}});
        List<String> status = Arrays.asList("Open", "Open", "Senior", "Open", "Open", "Senior");
        assertEquals(status, MembershipTester.testMembership(members));
    }

}