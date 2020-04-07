package StringThirdAssignment;

import edu.duke.*;

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


    StorageResource getAllGene(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currGene = findSimpleGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return geneList;
    }

    double cgRatio(String dna) {
        double result = 0;
        int countC = 0, countG = 0;
        int indexC = 0, indexG = 0;
        while (dna.indexOf("C", indexC) != -1) {
            countC++;
            indexC = dna.indexOf("C", indexC) + 1;
        }

        while (dna.indexOf("G", indexG) != -1) {
            countG++;
            indexG = dna.indexOf("G", indexG) + 1;
        }
        result = countC + countG;
        result /= dna.length();
        return result;

    }

    int countCTG(String dna) {
        String toFind = "CTG";
        int count = 0, index = 0;
        while (dna.indexOf(toFind, index) != -1) {
            count++;
            index = dna.indexOf(toFind, index) + toFind.length();
        }
        return count;
    }

    void processGenes(StorageResource sr) {
        int countStringOfLenNine = 0;
        int countStingCGRatio = 0;
        int longestGene = Integer.MIN_VALUE;
        System.out.println("All strings of length that are greater than 60 are: ");
        for (String s : sr.data()) {
            if (s.length() > 60) {
                System.out.println(s);
                countStringOfLenNine++;
            }

            if (s.length() > longestGene) {
                longestGene = s.length();

            }
        }

        System.out.println("Number of strings that are greater than length 60 are: ");
        System.out.println(countStringOfLenNine);

        System.out.println("Strings whose C-G ratio is higher than 0.35 are: ");
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                countStingCGRatio++;
            }
        }

        System.out.println("Number of strings whose C-G ratio is higher than 0.35 are: ");
        System.out.println(countStingCGRatio);

        System.out.println("Length of the longest gene is: ");
        System.out.println(longestGene);
    }


    void testcgRatio() {
        System.out.println(cgRatio("ATGCCATAG"));

    }

    void testCountCTG() {
        System.out.println(countCTG("CTGCTGCTCTGGGCTGG"));
    }

    void testProcessGenes() {
        FileResource fr = new FileResource("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentPrograms/StringsWeek-2/src/StringThirdAssignment/brca1line.txt");
        String dna = fr.asString();
        System.out.println(dna.length());
        StorageResource allGene = getAllGene(dna);
        processGenes(allGene);

    }

    void testfindSimpleGene() {
        String dna1 = "TATGTAAGGTA";
        String dna2 = "TACCATGAUTFATTAAGTA";
        String dna3 = "TATGFFTAAEWRT";
        String dna4 = "TATGTAHTGATAHJUI";
        System.out.println("DNA String = " + dna1);
        System.out.println("Gene = " + findSimpleGene(dna1, 0));

        System.out.println("DNA String = " + dna2);
        System.out.println("Gene = " + findSimpleGene(dna2, 0));

        System.out.println("DNA String = " + dna3);
        System.out.println("Gene = " + findSimpleGene(dna3, 0));

        System.out.println("DNA String = " + dna4);
        System.out.println("Gene = " + findSimpleGene(dna4, 0));

    }

    void testGetAllGene() {
        StorageResource allGenes = getAllGene("ATGCCCTAAGHATGTAGHIATGTGATASDATGHJKLIOPHGTAA");
        System.out.println("Testing all genes");
        for (String sr : allGenes.data()) {
            System.out.println(sr);
        }
    }


    void testFindStopCodon() {

        System.out.println(findStopCodon("ATGFRTTAAGGTA", 0, "TAA"));
        System.out.println(findStopCodon("ATGCCCTAA", 0, "TAA"));
        System.out.println(findStopCodon("ATGTAGCCCCCTAA", 0, "TAG"));
        System.out.println(findStopCodon("TAATGTATGTATGRTAAGGTA", 2, "TAA"));


    }

    public static void main(String args[]) {
        Part1 p1 = new Part1();
        p1.testProcessGenes();
    }
}
