package StringFirstAssignment;

public class Part3 {
    boolean twoOccurrences(String stringa, String stringb) {
        boolean result = false;
        int indexOfFirstOccur = stringb.indexOf(stringa);
        if (indexOfFirstOccur != -1) {
            int indexoFSecondOccur = stringb.indexOf(stringa, indexOfFirstOccur + 1);
            if (indexoFSecondOccur != -1) {
                result = true;
            }
        }
        System.out.println("Is " + stringa + " appears at least twice in " + stringb);
        return result;
    }

    String lastPart(String stringa, String stringb) {

        String result = "";
        int index = stringb.indexOf(stringa);
        if (index == -1) {
            result = stringb;
        } else {
            result = stringb.substring(index + stringa.length());
        }
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is ");
        return result;
    }

    void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("old", "old is gold"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(twoOccurrences("b", "banana b"));
        System.out.println(twoOccurrences("or", "order o r"));

        System.out.println();

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("old", "old is gold"));
        System.out.println(lastPart("zoo", "forest"));
    }

    public static void main(String args[]) {
        Part3 p3 = new Part3();
        p3.testing();
    }
}
