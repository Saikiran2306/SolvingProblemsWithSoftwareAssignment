package StringSecondAssignment;

public class Part2 {
    int howMany(String stringa, String stringb) {
        int count = 0;
        int index = 0;
        while (stringb.indexOf(stringa, index) != -1) {
            index = stringb.indexOf(stringa, index) + stringa.length();
            count++;
        }
        System.out.println("The number of occurrences " + stringa + " in " + stringb + " are");
        return count;

    }

    void testHowMany() {
        System.out.println(howMany("GAA", "ATGACGTTGATC"));
        System.out.println(howMany("AA", "AAA AAAA"));
        System.out.println(howMany("by", "by the book bysjsyj "));
    }

    public static void main(String args[]) {
        Part2 p2 = new Part2();
        p2.testHowMany();

    }


}
