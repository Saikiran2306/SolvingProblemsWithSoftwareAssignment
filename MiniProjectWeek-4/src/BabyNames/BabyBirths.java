package BabyNames;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.*;

public class BabyBirths {

    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("The total names: " + totalBirths);
        System.out.println("The number of unique girls names: " + totalGirls);
        System.out.println("The number of unique boys names: " + totalBoys);
    }

    int getRank(int year, String name, String gender) {
        int rank = 0;
        //  System.out.println("The rank of "+name+" with gender "+gender+" in the year "+year+" is ");
        FileResource fr = new FileResource("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/us_babynames_small/testing/yob2012short.csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource();
        // System.out.println("The name of person whose rank is "+rank+" with gender "+gender+" in the year "+year+" is ");
        int tempRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {

                tempRank++;
                if (tempRank == rank)
                    return rec.get(0);
            }
        }
        return "NO NAME";
    }

    void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String nameWithThatRank = getName(newYear, rank, gender);
        if (gender == "F") {
            System.out.println(name + " born in " + year + " would be " + nameWithThatRank + " if she was born in " + newYear);
        } else if (gender == "M") {
            System.out.println(name + " born in " + year + " would be " + nameWithThatRank + " if he was born in " + newYear);
        }
    }

    public static int yearOfHighestRank(String name, String gender) {
        int rank = Integer.MAX_VALUE;
        int yearHigh = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File fi : dr.selectedFiles()) {
            String fileName = fi.getName();
            int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
            FileResource fr = new FileResource(fi);
            int currRank = -1;
            int temp = 0;
            for (CSVRecord record : fr.getCSVParser(false)) {

                if (record.get(1).equals(gender)) {
                    temp++;
                    if (record.get(0).equals(name)) {
                        currRank = temp;
                        break;
                    }
                }
            }
            if (currRank != -1 && currRank < rank) {
                rank = currRank;
                yearHigh = year;
            }
        }
        return yearHigh;
    }

    double getAverageRank(String name, String gender) {
        double result = -1.0;
        DirectoryResource dr = new DirectoryResource();
        int fileNum = 0;
        int totalRank = 0;
        for (File fi : dr.selectedFiles()) {
            fileNum++;
            FileResource fr = new FileResource(fi);
            int temp = 0;
            int currRank = 0;
            for (CSVRecord record : fr.getCSVParser(false)) {
                if (record.get(1).equals(gender)) {
                    temp++;
                    if (record.get(0).equals(name)) {
                        currRank = temp;
                        break;
                    }
                }
            }
            totalRank += currRank;
        }
        if (totalRank != 0) {
            result = totalRank / fileNum;

        }
        return result;
    }

    int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int count = 0;
        int checkRank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                int currRank = getRank(2012, rec.get(0), rec.get(1));
                if (currRank < checkRank) {
                    count += Integer.parseInt(rec.get(2));
                }
            }
        }
        return count;

    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    void testGetRank() {
        System.out.println(getRank(2012, "Mason", "M"));
        System.out.println(getRank(2012, "Mason", "F"));
    }

    void testgetName() {
        System.out.println(getName(2012, 2, "M"));
    }

    void testWhatIsNameInYear() {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }

    void testyearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mason", "M"));
    }


    void testGetAverageRank() {
        System.out.println(getAverageRank("Mason", "M"));
    }

    void testgetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }


    public static void main(String args[]) {
        BabyBirths b1 = new BabyBirths();
        // b1.testTotalBirths();
        //b1.testGetRank();
        // b1.testgetName();
        //b1.testWhatIsNameInYear();
        b1.testyearOfHighestRank();
        //b1.testGetAverageRank();
        // b1.testgetTotalBirthsRankedHigher();
    }
}