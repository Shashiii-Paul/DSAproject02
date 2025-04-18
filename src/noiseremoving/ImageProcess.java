/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class ImageProcess {
    
    private BufferedImage buffered_image;
    
    // Constructor to load the image from file
    public ImageProcess(String imagePath) {
        try {
            buffered_image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            Logger.getLogger(ImageProcess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error loading image: " + ex.getMessage());
        }
    }
    
    // Save the processed image to a file
    public void save(String imageName) {
        try {
            String fileType = imageName.substring(imageName.lastIndexOf('.') + 1);
            ImageIO.write(buffered_image, fileType, new File(imageName));
        } catch (IOException e) {
            System.err.println("Image not saved: " + e.getMessage());
        }
    }
    
    // Add salt-and-pepper noise to the image
    public void addNoise(float density) {
        WritableRaster raster = buffered_image.getRaster();
        int width = buffered_image.getWidth();
        int height = buffered_image.getHeight();
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (Math.random() < density) {
                    int noise = (int)(255 * Math.round(Math.random()));
                    int[] noisyPixel = { noise, noise, noise };
                    raster.setPixel(j, i, noisyPixel);
                }
            }
        }
    }
    
    // Clean noise from the image using a median filter
    public void cleanNoise() {
        WritableRaster raster = buffered_image.getRaster();
        int width = buffered_image.getWidth();
        int height = buffered_image.getHeight();
        
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                Integer[] red = new Integer[9];
                Integer[] green = new Integer[9];
                Integer[] blue = new Integer[9];
                int index = 0;

                // Collect pixel values from the 3x3 neighborhood
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        int color = buffered_image.getRGB(j + l, i + k);
                        red[index] = (color >> 16) & 0xff;
                        green[index] = (color >> 8) & 0xff;
                        blue[index] = color & 0xff;
                        index++;
                    }
                }

                // Sort and find the median for each color channel
                SortArray<Integer> sorter = new SortArray<>(red);
                sorter.quickSort();
                int medianRed = red[4];
                
                sorter.setArray(green);
                sorter.quickSort();
                int medianGreen = green[4];
                
                sorter.setArray(blue);
                sorter.quickSort();
                int medianBlue = blue[4];
                
                int[] medianPixel = { medianRed, medianGreen, medianBlue };
                raster.setPixel(j, i, medianPixel);
            }
        }
    }
}
