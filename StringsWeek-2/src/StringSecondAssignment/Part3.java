package StringSecondAssignment;

public class Part3 {

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

    int countGenes(String dna) {
        System.out.println("The number of possible genes in dna " + dna + " are ");
        int startIndex = 0;
        int countGene = 0;
        while (true) {
            String currGene = findSimpleGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            countGene++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return countGene;
    }

    void testCountGene() {
        System.out.println(countGenes("ATGCCCTAAGHATGTAGHIATGTGATASDATGHJKLIOPHGTAA"));
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }

    public static void main(String args[]) {
        Part3 p3 = new Part3();
        p3.testCountGene();

    }
}
