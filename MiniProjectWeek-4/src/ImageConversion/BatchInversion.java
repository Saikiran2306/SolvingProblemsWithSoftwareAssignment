package ImageConversion;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchInversion {

    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel : outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //set pixel's red
            pixel.setRed(255 - inPixel.getRed());
            //set pixel's green
            pixel.setGreen(255 - inPixel.getGreen());
            //set pixel's blue
            pixel.setBlue(255 - inPixel.getBlue());
        }
        //outImage is your answer
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(f);
            ImageResource gray = makeInversion(inFile);
            String fname = inFile.getFileName();
            String newName = "inverted-" + fname;
            gray.setFileName("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/images/" + newName);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String args[]) {
        BatchInversion gs = new BatchInversion();
        gs.selectAndConvert();

    }

}
