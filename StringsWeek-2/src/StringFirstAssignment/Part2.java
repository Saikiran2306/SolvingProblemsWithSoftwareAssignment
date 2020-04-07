package StringFirstAssignment;

public class Part2 {
    String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        char getFirstChar = dna.charAt(0);
        if (Character.isUpperCase(getFirstChar)) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }

        int indexOfStartCodon = dna.indexOf(startCodon);
        if (indexOfStartCodon == -1) {
            return result;
        }
        int indexOfFirstStopCodon = dna.indexOf(stopCodon, indexOfStartCodon + 3);
        if (indexOfFirstStopCodon == -1) {
            return result;
        }
        if ((indexOfFirstStopCodon - indexOfStartCodon) % 3 == 0) {
            result = dna.substring(indexOfStartCodon, indexOfFirstStopCodon + 3);
        }
        return result;

    }

    void testSimpleGene() {

        String dna1 = "TACCATGAATVGTTAAGGTA";
        String dna2 = "TACCATGTATGAVGTGGTA";
        String dna3 = "TACCAGTGAVGTAGGGTA";
        String dna4 = "avdtaatgrbjtgaagttag";
        String dna5 = "TACCATGTAAATVGATAAGGTA";

        System.out.println("DNA String = " + dna1);
        System.out.println("Gene = " + findSimpleGene(dna1, "ATG", "TAA"));

        System.out.println("DNA String = " + dna2);
        System.out.println("Gene = " + findSimpleGene(dna2, "ATG", "TAG"));

        System.out.println("DNA String = " + dna3);
        System.out.println("Gene = " + findSimpleGene(dna3, "ATG", "TGA"));

        System.out.println("DNA String = " + dna4);
        System.out.println("Gene = " + findSimpleGene(dna4, "ATG", "TGA"));

        System.out.println("DNA String = " + dna5);
        System.out.println("Gene = " + findSimpleGene(dna5, "ATG", "TAA"));
    }

    public static void main(String args[]) {
        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }
}
