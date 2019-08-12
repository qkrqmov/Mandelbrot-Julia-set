
/**
 * Code by Akram Kaidi
 * The code generates two images
 * The first image is the mandelbrot set
 * The second image is the julia set
 * The julia set constant is changed by clicking on the mandelbrot set image
 */


//---------Importing----------

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.MouseListener;


public class Main extends JFrame implements MouseListener {
    //The main Class inherits from JFrame to be able to display images and it implements MouseListener so it can listen to mouse click events

//---------Initialising variable for the Main class-------
    private final int maxIter = 200;            // varaible that holds the max iteration the calculations will be done to
    private int h, w;                           // hold the height and width of the images
    private double zoom = 140;                  // the zoom level of sets
    private double centerX, centerY;            //the center of the sets
    private BufferedImage img1, img2;           // hold the image of the sets
    private double juliaSetX, juliaSetY;        // juliaSetX holds the real part of the julia constant while JuliaSetY holds the imaginary part


    public Main() {


        super("Mandelbrot Set");                       //Calling the super class and assigning a title

        setBounds(100, 100, 1200, 600);     // setting the location and the size of the window

        setResizable(false);                                    // make the window no resizable

        setDefaultCloseOperation(EXIT_ON_CLOSE);                //Close the java instance when the JFrame is closed


        addMouseListener(this);                             // add a mouse listener to this instance


        //-------------Initializing variables------
        juliaSetX = 0;
        juliaSetY = 0;
        centerY = getHeight() / 2;
        centerX = getWidth() / 4 + 100;
        w = getWidth() / 2;
        h = getHeight();


        render();           //Render the both sets when the instance is initialised
    }

    public static void main(String[] args) {

        new Main().setVisible(true); // initializing a new instance and make the window visible

    }


    @Override                           // override the paint method from the window class
    public void paint(Graphics g) {
        g.drawImage(img1, 0, 0, this);              //draw the mandelbrot set on the window
        g.drawImage(img2, getWidth() / 2, 0, this);     // draw the juliet set on the window
    }

    public void render() {             // method that renders both sets


        img1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);     //create a new image and call a method that draws the mandelbrot set
        mandelbrotSet();

        img2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);     //create a new image and call a method that draws the julia set
        juliaSet();


        repaint();              // redraw the window's pixels and update it with the new images

    }

    public void mandelbrotSet() {           // method that renders the mandelbrot set to an image

        double zx, zy, cx, cy, temp;        // variables that hold the mandelbrot set different parts

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {               // two loops that go through all the pixels of the image

                zx = zy = 0;                        //initialising the variable before the iterations
                cx = ((x - centerX)) / zoom;
                cy = ((y - centerY)) / zoom;

                int iter = 0;                       // a variable that holds how many iterations have been done

                while (Math.pow(zx + zy, 2) < 16 && iter < maxIter) {    // a while loop that iterates on the mandelbrot set numbers

                    temp = zx * zx - zy * zy + cx;                      // holding the new value of zx as the next equation needs the original value of zx

                    zy = 2.0 * zx * zy + cy;

                    zx = temp;

                    iter++;

                }
                int blueColor = (iter * 255 / maxIter);             // normalising the blue color so it's between 0 and 255


                img1.setRGB(x, y, blueColor );          // setting the color of the pixel at the location x and y

            }
        }
    }

    public void juliaSet() {            // method that renders the julia set to an image
        double zx, zy, cx, cy, temp;        // variables that hold the julia set different parts

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {               // two loops that go through all the pixels of the image


                zx = ((x - centerX + 100)) / (zoom - 20);  //initialising the variable before the iterations
                zy = ((y - centerY)) / (zoom - 20);

                cx = juliaSetX;
                cy = juliaSetY;

                int iter = 0;                       // a variable that holds how many iterations have been done

                while (Math.pow(zx + zy, 2) < 16 && iter < maxIter) {   // a while loop that iterates on the mandelbrot set numbers

                    temp = zx * zx - zy * zy + cx;              // holding the new value of zx as the next equation needs the original value of zx

                    zy = 2.0 * zx * zy + cy;

                    zx = temp;

                    iter++;

                }
                int blueColor = (iter * 255 / maxIter);             // normalising the blue color so it's between 0 and 255


                img2.setRGB(x, y, blueColor );          // setting the color of the pixel at the location x and y


            }
        }

    }





    public void mousePressed(MouseEvent e) {            // a method that is called when the mouse is clicked
        double x = e.getX();                    //get the location of the click
        double y = e.getY();

        if (e.getX() < w) {                     // if the click happened on the side of the mandelbrot set

            juliaSetX = ((x - centerX)) / zoom;     // get the location of the click in term of x and y of the mandelbrot set
            juliaSetY = ((y - centerY)) / zoom;


            render();                   // render the images to the sereen

        }
    }


    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }
}