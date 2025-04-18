/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class NoiseRemoving extends JPanel {

    private String imageFilePath = "";
    private boolean isImageOpened = false;

    private JButton openImageButton;
    private JButton cleanImageButton;
    private JButton showCleanedImageButton;

    private JLabel originalImageLabel;
    private JLabel cleanedImageLabel;

    private JFileChooser fileChooser;

    public NoiseRemoving() {
        initializeComponents();
        setUpLayout();
        applyTheme();
    }

    // Initialize GUI components and set up event listeners
    private void initializeComponents() {
        fileChooser = new JFileChooser(new File(".")); 

        openImageButton = new JButton("Open Image");
        openImageButton.addActionListener(this::openImage);

        cleanImageButton = new JButton("Clean Image");
        cleanImageButton.addActionListener(this::cleanImage);

        showCleanedImageButton = new JButton("Show Cleaned Image");
        showCleanedImageButton.addActionListener(this::showCleanedImage);

        originalImageLabel = new JLabel();
        cleanedImageLabel = new JLabel();
        originalImageLabel.setHorizontalAlignment(JLabel.CENTER);
        cleanedImageLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    // Set up the layout and add components to the panel
    private void setUpLayout() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout(10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(openImageButton);
        buttonPanel.add(cleanImageButton);
        buttonPanel.add(showCleanedImageButton);
        add(buttonPanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        imagePanel.add(originalImageLabel);
        imagePanel.add(cleanedImageLabel);
        add(imagePanel, BorderLayout.CENTER);
    }

    // Apply light pink and white color theme to the components
    private void applyTheme() {
        Color lightPink = new Color(255, 182, 193);
        Color white = Color.WHITE;

        setBackground(white);
        openImageButton.setBackground(lightPink);
        cleanImageButton.setBackground(lightPink);
        showCleanedImageButton.setBackground(lightPink);
        
        openImageButton.setForeground(Color.DARK_GRAY);
        cleanImageButton.setForeground(Color.DARK_GRAY);
        showCleanedImageButton.setForeground(Color.DARK_GRAY);
    }

    // Open an image and display it with maintained aspect ratio
    private void openImage(ActionEvent e) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            imageFilePath = fileChooser.getSelectedFile().getPath();
            try {
                BufferedImage image = ImageIO.read(new File(imageFilePath));
                originalImageLabel.setIcon(new ImageIcon(scaleImage(image, 400, 400)));
                isImageOpened = true;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to open image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Clean the noise from the opened image and save it
    private void cleanImage(ActionEvent e) {
        if (isImageOpened) {
            ImageProcess imageProcessor = new ImageProcess(imageFilePath);
            imageProcessor.cleanNoise();
            imageProcessor.save("noise_removed.jpg");
            JOptionPane.showMessageDialog(this, "Image cleaned and saved as 'noise_removed.jpg'.");
        } else {
            JOptionPane.showMessageDialog(this, "Please open an image first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Display the cleaned image with maintained aspect ratio
    private void showCleanedImage(ActionEvent e) {
        if (isImageOpened) {
            try {
                BufferedImage cleanedImage = ImageIO.read(new File("noise_removed.jpg"));
                cleanedImageLabel.setIcon(new ImageIcon(scaleImage(cleanedImage, 400, 400)));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to load cleaned image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please open and clean an image first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Helper method to scale images while maintaining aspect ratio
    private Image scaleImage(BufferedImage img, int maxW, int maxH) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        
        double scale = Math.min((double) maxW / imgWidth, (double) maxH / imgHeight);
        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);
        
        return img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        JFrame frame = new JFrame("Noise Removal Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new NoiseRemoving());
        frame.pack();
        frame.setLocationRelativeTo(null);  // Center the window
        frame.setVisible(true);
    }
}