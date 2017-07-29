package de.wohlers.RandomWordChooser;

import java.util.List;
import java.util.stream.Collectors;

public class MembershipTester {

    public static List<String> testMembership(List<int[]> members) {
        return members.stream().map((member) -> member[0] >= 55 && member[1] > 7 ? "Senior" : "Open").collect(Collectors.toList());
    }

}
