import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {

		JFrame frame = new JFrame(); // git text
		frame.getContentPane().setLayout(new FlowLayout());

		
		BufferedImage bigImg = ImageIO.read(new File("C:\\Users\\Zakash\\Java\\Wavlets\\src\\sheet.png"));		// Open Image

		int imgNSize  =  bigImg.getHeight(); // Can get only hieght as img is NxN 
		int subnSize = 20;					// sub image size nXn
		int k = 0;							// counter for subimages
		int c = 0;							// counter for rgb
		
		BufferedImage[] subImages = new BufferedImage[(int) Math.pow(imgNSize/subnSize, 2)];	// Sub images array
		int[] redArray = new int[(int) Math.pow(subnSize, 2)];									// Array for Red
		int[] greenArray = new int[(int) Math.pow(subnSize, 2)];								// Array for Green
		int[] blueArray = new int[(int) Math.pow(subnSize, 2)];									// Array for Blue
			
		
		// Divide image to sub images
		for (int i=0 ; i < imgNSize ; i+=20)
		{
			for (int j=0 ; j < imgNSize ; j+=20)
			{
				subImages[k] = bigImg.getSubimage(j, i, 20, 20);
				k++;
			}
		} // End of the divide

	    BufferedWriter redFile = null;
	    BufferedWriter greenFile = null;
	    BufferedWriter blueFile = null;
	    BufferedWriter redAFile = null;
	    BufferedWriter greenAFile = null;
	    BufferedWriter blueAFile = null; 

	    
		// Calc RGB for each sub-image
		for (int i = 0 ; i < subImages.length ; i++) {
			
			//We doing new file so we can run it in our Matlab wavelet funtion
			
		    redFile = new BufferedWriter(new FileWriter("red.txt"));			//Create new file for Red Array
		    greenFile = new BufferedWriter(new FileWriter("green.txt"));		//Create new file for Green Array
		    blueFile = new BufferedWriter(new FileWriter("blue.txt"));			//Create new file for Blue Array
		    
			for (int w = 0 ; w < subnSize ; w++)
			{
				for (int h = 0 ; h <subnSize ; h++)
				{
					int rgb = subImages[i].getRGB(h,w);
					redArray[c] =   (rgb >> 16) & 0xFF;
					greenArray[c] = (rgb >>  8) & 0xFF;
					blueArray[c] =  (rgb      ) & 0xFF;
					redFile.write(Integer.toString(redArray[c]));
					redFile.write(",");
					greenFile.write(Integer.toString(greenArray[c]));
					greenFile.write(",");
					blueFile.write(Integer.toString(greenArray[c]));
					blueFile.write(",");
					c++;
				}
				
			}

			redFile.close();
			blueFile.close();
			greenFile.close();
			
			Process process = new ProcessBuilder("C:\\Users\\Zakash\\Java\\Wavlets\\wavelet.exe").start(); // Wavelet
			process.waitFor();
			
			//Read Wavelete Result
			BufferedReader brRed = new BufferedReader(new FileReader("redafile.txt"));
			BufferedReader brgGreen = new BufferedReader(new FileReader("greenafile.txt"));
			BufferedReader brBlue = new BufferedReader(new FileReader("blueafile.txt"));
			
			String redWaveletTemp = brRed.readLine();
			String[] redWaveletArray = redWaveletTemp.split(",");
			
			String greenWaveletTemp = brgGreen.readLine();
			String[] greenWaveletArray = greenWaveletTemp.split(",");
			
			String blueWaveletTemp = brBlue.readLine();
			String[] blueWaveletArray = blueWaveletTemp.split(",");
			//End of reading
			
			c = 0;																								// Reset counter
			
			
		}			
			// Save Image
			try {
				File outputfile = new File("sheet2.png");
				File outputfile1 = new File("sheet3.png");
				File outputfile2 = new File("sheet4.png");
				File outputfile3 = new File("sheet5.png");
				File outputfile4 = new File("sheet6.png");
				File outputfile5 = new File("sheet7.png");
				File outputfile6 = new File("sheet8.png");
				File outputfile7 = new File("sheet9.png");
				File outputfile8 = new File("sheet10.png");
				File outputfile9 = new File("sheet11.png");
				
				ImageIO.write(subImages[889], "png", outputfile);
				ImageIO.write(subImages[890], "png", outputfile1);
				ImageIO.write(subImages[891], "png", outputfile2);
				ImageIO.write(subImages[892], "png", outputfile3);
				ImageIO.write(subImages[893], "png", outputfile4);
				ImageIO.write(subImages[894], "png", outputfile5);
				ImageIO.write(subImages[895], "png", outputfile6);
				ImageIO.write(subImages[896], "png", outputfile7);
				ImageIO.write(subImages[897], "png", outputfile8);
				ImageIO.write(subImages[899], "png", outputfile9);

				}
			catch (IOException e){} // End Save Image
	
	// Show Image
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet2.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet3.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet4.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet5.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet6.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet7.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet8.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet9.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet10.png")));
	frame.getContentPane().add(new JLabel(new ImageIcon("sheet11.png")));
	frame.pack();
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//End Show Image

		
	
	
}
	
}


