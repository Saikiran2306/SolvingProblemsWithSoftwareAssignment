package ImageConversion;

import edu.duke.*;

import java.io.*;


public class GrayScaleConverter {

    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel : outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            //set pixel's red to average
            pixel.setRed(average);
            //set pixel's green to average
            pixel.setGreen(average);
            //set pixel's blue to average
            pixel.setBlue(average);
        }
        //outImage is your answer
        return outImage;
    }


    public void selectConvertToGrayAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(f);
            ImageResource gray = makeGray(inFile);
            String fname = inFile.getFileName();
            String newName = "gray-" + fname;
            gray.setFileName("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/images/" + newName);
            gray.draw();
            gray.save();
        }
    }


    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }

    public static void main(String args[]) {
        GrayScaleConverter gs = new GrayScaleConverter();
        // gs.testGray();
        gs.selectConvertToGrayAndSave();

    }
}