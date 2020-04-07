package StringFirstAssignment;


class Part1 {
    String findSimpleGene(String dna) {
        String result = "";
        int indexOfStartCodon = dna.indexOf("ATG");

        if (indexOfStartCodon == -1) {
            return result;
        }
        int indexOfFirstStopCodon = dna.indexOf("TAA", indexOfStartCodon + 3);
        if (indexOfFirstStopCodon == -1) {
            return result;
        }
        if ((indexOfFirstStopCodon - indexOfStartCodon) % 3 == 0) {
            result = dna.substring(indexOfStartCodon, indexOfFirstStopCodon + 3);
        }
        return result;

    }

    void testSimpleGene() {

        String dna1 = "TACCAGAATVGTAAAGGTA";
        String dna2 = "TACCATGAATVGTGGTA";
        String dna3 = "TACCAGAATVGTAGGGTA";
        String dna4 = "TACCATGATVGGTTAAGGTA";
        String dna5 = "TACCATGAATVGTAAGGTA";

        System.out.println("DNA String = " + dna1);
        System.out.println("Gene = " + findSimpleGene(dna1));

        System.out.println("DNA String = " + dna2);
        System.out.println("Gene = " + findSimpleGene(dna2));

        System.out.println("DNA String = " + dna3);
        System.out.println("Gene = " + findSimpleGene(dna3));

        System.out.println("DNA String = " + dna4);
        System.out.println("Gene = " + findSimpleGene(dna4));

        System.out.println("DNA String = " + dna5);
        System.out.println("Gene = " + findSimpleGene(dna5));
    }

    public static void main(String args[]) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}
