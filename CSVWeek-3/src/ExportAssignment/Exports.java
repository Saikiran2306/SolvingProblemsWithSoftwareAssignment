package ExportAssignment;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Exports {

    void tester() {

        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));

        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");

        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));

        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
    }

    String countryInfo(CSVParser parser, String country) {
        String result = "";
        boolean flag = false;
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                result += record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                flag = true;
                break;
            }
        }
        if (flag == false)
            result += "NOT FOUND";
        return result;
    }

    void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        System.out.println("The countries which have export items " + exportItem1 + " and " + exportItem2 + " are ");
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    int numberOfExporters(CSVParser parser, String exportItem) {
        System.out.println("The number of countries which have export item " + exportItem + " are");
        int countryCount = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                countryCount++;
            }
        }
        return countryCount;
    }

    void bigExporters(CSVParser parser, String amount) {
        System.out.println("The countries whose value is greater than " + amount + " are ");
        for (CSVRecord record : parser) {
            if (amount.length() < record.get("Value (dollars)").length()) {
                String result = record.get("Country") + " " + record.get("Value (dollars)");
                System.out.println(result);
            }
        }
    }

    public static void main(String args[]) {
        Exports ex = new Exports();
        ex.tester();
    }
}
