package WeatherAssignment;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Weather {

    public CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord coldestTemperatureRecord = null;
        double coldestTemperature = Double.MAX_VALUE;

        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (coldestTemperature > currentTemp && currentTemp != -9999) {
                coldestTemperature = currentTemp;
                coldestTemperatureRecord = currentRow;
            }
        }
        return coldestTemperatureRecord;
    }


    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println(smallest.get("TemperatureF") + " was the coldest temperature at " +
                smallest.get("TimeEST"));
    }

    public String fileWithColdestTemperature() {
        double coldestTemperature = Double.MAX_VALUE;
        String fileWithColdestTemperature = "";

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parse = fr.getCSVParser();
            CSVRecord currentColdestTemperatureRecord = coldestHourInFile(parse);
            double currentTemperature = Double.parseDouble(currentColdestTemperatureRecord.get("TemperatureF"));
            if (coldestTemperature > currentTemperature) {
                coldestTemperature = currentTemperature;
                fileWithColdestTemperature = f.getPath();
            }
        }
        return fileWithColdestTemperature;
    }

    public void testFileWithColdestTemperature() {

        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(filename);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were:");

        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));

        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord lowestHumidityRecord = null;
        double lowestHumidity = Double.MAX_VALUE;

        for (CSVRecord currentRow : parser) {
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if (lowestHumidity > currentHumidity) {
                lowestHumidity = currentHumidity;
                lowestHumidityRecord = currentRow;
            }
        }
        return lowestHumidityRecord;
    }

    public void testlowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity was " + lowestHumidityRecord.get("Humidity") +
                " at " + lowestHumidityRecord.get("DateUTC"));
    }


    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = null;
        double lowestHumidity = Double.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if (lowestHumidity > currentHumidity) {
                lowestHumidity = currentHumidity;
                lowestHumidityRecord = currentRow;
            }
        }
        return lowestHumidityRecord;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0;
        double avarageTemp = 0;
        int totalRecords = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemp += currentTemp;
            totalRecords++;
        }
        avarageTemp = (totalTemp) / totalRecords;
        return avarageTemp;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avarage = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avarage);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {

        double totalTemp = 0;
        double avarageTemp = 0;
        int totalRecords = 0;
        for (CSVRecord currentRow : parser) {
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentHumidity >= value) {
                totalTemp += currentTemp;
                totalRecords++;
            }

        }
        if (totalRecords != 0)
            avarageTemp = totalTemp / totalRecords;
        return avarageTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avarage = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avarage == 0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average temperature when high Humidity is " + avarage);
    }

    public static void main(String[] args) {
        Weather w1 = new Weather();
        // w1.testColdestHourInFile();
        //w1.testFileWithColdestTemperature();
        // w1.testlowestHumidityInFile();
        //  w1.testLowestHumidityInManyFiles();
        //  w1.testAverageTemperatureInFile();
        w1.testAverageTemperatureWithHighHumidityInFile();
    }
}
