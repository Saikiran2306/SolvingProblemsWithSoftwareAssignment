package StringSecondAssignment;

public class Part1 {

    int findStopCodon(String dna, int startIndex, String stopCodon) {
        int result = -1;
        int indexOfStopCodon = dna.indexOf(stopCodon, startIndex + 3);
        if ((indexOfStopCodon - startIndex) % 3 == 0 && indexOfStopCodon != -1) {
            result = indexOfStopCodon;
        }
        return result;
    }

    String findSimpleGene(String dna, int where) {
        String result = "";
        int indexOfStartCodon = dna.indexOf("ATG", where);
        if (indexOfStartCodon == -1)
            return "";
        int indexOfStopCodon = -1;
        int taaIndex = findStopCodon(dna, indexOfStartCodon, "TAA");
        int tgaIndex = findStopCodon(dna, indexOfStartCodon, "TGA");
        int tagIndex = findStopCodon(dna, indexOfStartCodon, "TAG");
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            indexOfStopCodon = tgaIndex;
        } else {
            indexOfStopCodon = taaIndex;
        }
        if (indexOfStopCodon == -1 || (tagIndex != -1 && tagIndex < indexOfStopCodon)) {
            indexOfStopCodon = tagIndex;
        }
        if (indexOfStopCodon == -1) {
            return result = "";
        }

        if ((indexOfStopCodon - indexOfStartCodon) % 3 == 0) {
            result = dna.substring(indexOfStartCodon, indexOfStopCodon + 3);

        }
        return result;
    }

    void printAllGene(String dna) {
        System.out.println("All possible genes in dna " + dna + " are ");
        int startIndex = 0;
        while (true) {
            String currGene = findSimpleGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }

    void testfindSimpleGene() {

        String dna1 = "TATGTAAGGTA";
        String dna2 = "TACCATGAUTFATTAAGTA";
        String dna3 = "TATGFFTAAEWRT";
        String dna4 = "TATGAAAHTGATAHJUI";
        System.out.println("DNA String = " + dna1);
        System.out.println("Gene = " + findSimpleGene(dna1, 0));

        System.out.println("DNA String = " + dna2);
        System.out.println("Gene = " + findSimpleGene(dna2, 0));

        System.out.println("DNA String = " + dna3);
        System.out.println("Gene = " + findSimpleGene(dna3, 0));

        System.out.println("DNA String = " + dna4);
        System.out.println("Gene = " + findSimpleGene(dna4, 0));

    }

    void testPrintAllGene() {
        printAllGene("ATGCCCTAAGHATGTAGHIATGTGATASDATGHJKLIOPHGTAA");
        printAllGene("HIATGTGATASDATGHJKLIOPHGTAA");
    }


    void testFindStopCodon() {

        System.out.println(findStopCodon("ATGFRTTAAGGTA", 0, "TGA"));
        System.out.println(findStopCodon("ATGCCCCTAA", 0, "TAA"));
        System.out.println(findStopCodon("ATGTAGCCCCCTAA", 0, "TAG"));
        System.out.println(findStopCodon("TAATGTATGTATGRTAAGGTA", 2, "TAA"));


    }


    public static void main(String args[]) {
        Part1 p1 = new Part1();
        // p1.testFindStopCodon();
        //  p1.testfindSimpleGene();
        p1.testPrintAllGene();
    }
}
