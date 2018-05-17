package photoshop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AlterImage {
	
	public int width;
	public int height;
	
	BufferedImage img;
	
	public BufferedImage photoshopPicture(File file, String choice) {
		
	    img = null;
	    File f = null;

	    //read image
	    try{
	      f = file;
	      img = ImageIO.read(f);
	    }catch(IOException e){
	      System.out.println(e);
	    }
		
		width = img.getWidth();
		height = img.getHeight();
		        
		        if(choice.equals("Heat Map")) {
		        	heatMap();
		        }
		        if(choice.equals("Heat Map Mk. II")) {
		        	heatMapMkII();
		        }
		        else if(choice.equals("Swap Colour Values")) {
		        	swapPixels();
		        }
		        else if(choice.equals("Black and White")) {
		        	blackAndWhite();
		        }
		        else if(choice.equals("Black and White Mk. II")) {
		        	blackAndWhiteMkII();
		        }
		        else if(choice.equals("Photo Negatives")) {
		        	photoNegatives();
		        }
		        else if(choice.equals("Monochrome Negative")) {
		        	monochromeNegative();
		        }
		        else if(choice.equals("Acid Trip")) {
		        	acidTrip();
		        }
		        else if(choice.equals("Sketch Outline")) {

		        	SliderOptionBox slider = new SliderOptionBox();
		        	slider.main(null);
		        	
		        	while(!SliderOptionBox.isValuePicked) {
		        		try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		       		        	
		        	sketchOutline(SliderOptionBox.chosenValue);
		        }
		        	
		        
		        //write image
		        /*
		        try
		        {
		        	File file = new File("Pictures\\Out.jpg");
		            ImageIO.write(img, "jpg", file);
		        }
		        catch(IOException e)
		        {
		            System.out.println(e);
		        }
		        */
	    
		return img;
		
	}
	
	public void heatMap(){
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
								
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
	
		        if(b > 100) {
		        	b = 0;
		        	g = 255;
		        	a = 255;
		        }
		        else {
			        a = 255;
			        r = 255;
			        g = 0;
			        b = 0;
		        }		
		        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
		
	}
	
	public void heatMapMkII(){
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
								
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
	
		        if(b > 100) {
		        	b = 0;
		        	g = 255;
		        	a = 255;
		        }
		        else if (g > 100){
			        a = 255;
			        r = 255;
			        g = 0;
			        b = 0;
		        }	
		        else if(r > 100){
			        a = 255;
			        r = 0;
			        g = 0;
			        b = 255;
		        }
		        else if(r < 50 && g < 50 && b < 50) {
		        	b = 255;
		        	g = 0;
		        	a = 0;
		        	a = 255;
		        }
		        else {
		        	a =  (int)(Math.random() * 255);
		        	r =  (int)(Math.random() * 255);
		        	g =  (int)(Math.random() * 255);
		        	b =  (int)(Math.random() * 255);
		        }
		        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
		
	}
	
	public void swapPixels(){
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
	
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
		        
			    int temp = r;
			    
			    r = g;
			    g = b;
			    b = temp;
		        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
	
		
	}
	
	public void blackAndWhite(){
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
				
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
		        
				int avg = (r + g + b) / 3;
				
				if(avg > 127) {
					r = 255;
					g = 255;
					b = 255;
				}
				else {
					r = 0;
					g = 0;
					b = 0;
				}
			      	        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
		
	}
	
	public void blackAndWhiteMkII() {
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
							
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
		        
				int avg = (r + g + b) / 3;
				
				r = avg;
				g = avg;
				b = avg;
		        		        	        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
		
	}
	
	public void photoNegatives() {
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
				
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
		        
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
		        		        	        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
		
	}
	
	public void monochromeNegative() {
		
		blackAndWhiteMkII();
		photoNegatives();
		
	}
	
	public void acidTrip() {
		
		for(int x = 0;x < height;x++) {
			for(int y = 0;y < width;y++) {
								
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        int tempR = r;
		        // get green
		        int g = (p>>8) & 0xff;
		        int tempG = g;
		        // get blue
		        int b = p & 0xff;

		        int[] colourArray = {r, g, b};
		        
		        Arrays.sort(colourArray);
		        
		        if(r == colourArray[0] && g == colourArray[1]) {
		        	b = 0;
		        }
		        else if(r == colourArray[0] && b == colourArray[1]) {
		        	g = 0;
		        }
		        else if(g == colourArray[0] && r == colourArray[1]) {
		        	b = 0;
		        }
		        else if(g == colourArray[0] && b == colourArray[1]) {
		        	r = 0;
		        }
		        else if(b == colourArray[0] && r == colourArray[1]) {
		        	g = 0;
		        }
		        else if(b == colourArray[0] && g == colourArray[1]) {
		        	r = 0;
		        }     
		        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
		
	}
	
	public void sketchOutline(int sensitivity) {
		
		for(int x = 0;x < (height - 1);x++) {
			for(int y = 0;y < (width - 1);y++) {
				
				// get pixel
				int p = img.getRGB(y, x);
			    // get alpha
		        int a = (p>>24) & 0xff;
		        // get red
		        int r = (p>>16) & 0xff;
		        // get green
		        int g = (p>>8) & 0xff;
		        // get blue
		        int b = p & 0xff;
		        
				int p2 = img.getRGB(y + 1, x + 1);
			    // get alpha
		        int a2 = (p2>>24) & 0xff;
		        // get red
		        int r2 = (p2>>16) & 0xff;
		        // get green
		        int g2 = (p2>>8) & 0xff;
		        // get blue
		        int b2 = p2 & 0xff;
		        
		        int avg = (r + g + b) / 3;
		        int avg2 = (r2 + g2 + b2) / 3;
		        
		        int difference = avg - avg2;
		        
		        //System.out.println("Avg colour difference between pixels: " + difference);
		        
		        if(difference > sensitivity || difference < (sensitivity * -1)) {
		        	r = 0;
		        	g = 0;
		        	b = 0;
		        }
		        else {
		        	r = 255;
		        	g = 255;
		        	b = 255;
		        }
		        
		        //set the pixel value
		        p = (a<<24) | (r<<16) | (g<<8) | b;
		        img.setRGB(y, x, p);
		        
			}
		}
			
		
	}
	

}
