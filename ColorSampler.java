// Marcus Casey
// CS 326 hw 8 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class ColorSampler extends JFrame {
    protected JLabel redL, greenL, blueL;
    protected JTextField redF, greenF, blueF;
    protected JButton save, reset, 
                      plusRed, minusRed,
                      plusBlue, minusBlue,
                      plusGreen, minusGreen;
    protected JList colorList;
    protected displayC colorDisplay;
    protected int colorIndex;
    // default  values 
    protected int redB = 255;
    protected int greenB = 255;
    protected int blueB = 255;
    String title = "Color Sampler";
    String titleChanged = title + "*";
    int numColors = 0;
    String colorNames[]; 
    // get chars for list
    public class colorObject{
        public String colorName;
        public int red, blue, green;
    }
    protected colorObject[] colorArray = new colorObject[16];
    public static void main( String args[] ){
        new ColorSampler( "Color Sampler");
    }
    public ColorSampler( String title){
        super(title);
        setSize(480, 510);       
          initGui();
        setGui();
        try{
        readInput( colorArray );
        }
        catch (IOException e) {
                System.out.println("error, is there an input file?");
                System.exit(0);
        }
        colorNames = new String[ numColors ];
        for( int i = 0; i < numColors; i++ ){
            colorNames[ i ] = colorArray[ i ].colorName;
        }
        colorList.setListData( colorNames );
        colorList.setSelectedIndex( 0 );  
        redB = colorArray[ 0 ].red;
        greenB = colorArray[ 0 ].green;
        blueB = colorArray[ 0 ].blue;
        redF.setText( redB + "" );
        blueF.setText( blueB + "" );
        greenF.setText( greenB + "" );
        repaint();
        colorList.setLocation(370, 8);
        colorList.setSize(70, 250);     
        colorDisplay.setLocation( 10, 10 );
        colorDisplay.setSize( 350, 260 );
        assignHandler();
        setVisible(true);
    }


    public void initGui() {
        redL = new JLabel( "Red: " );
        greenL = new JLabel( "Green: " );
        blueL = new JLabel( "Blue: ");
        redF = new JTextField();
        redF.setEditable( false );
        greenF = new JTextField();
        greenF.setEditable( false );
        blueF = new JTextField();
        blueF.setEditable( false );
        plusRed = new JButton( " + " );
        plusGreen = new JButton( " + ");
        plusBlue = new JButton( " + ");
        minusRed = new JButton( " - ");
        minusGreen = new JButton( " - ");
        minusBlue =  new JButton( " - ");
        save = new JButton( " Save " );
        reset = new JButton( " Reset ");
        colorDisplay = new displayC();
        colorList = new JList();

    }

    public void setGui() {
        getContentPane().setLayout( null );
        getContentPane().add( redL );
        getContentPane().add( greenL );
        getContentPane().add( blueL );
        getContentPane().add( redF );
        getContentPane().add( greenF );
        getContentPane().add( blueF );
        getContentPane().add( plusRed );
        getContentPane().add( plusBlue );
        getContentPane().add( plusGreen );
        getContentPane().add( minusRed );
        getContentPane().add( minusGreen );
        getContentPane().add( minusBlue );
        getContentPane().add( save );
        getContentPane().add( reset );
        getContentPane().add( colorDisplay );
        getContentPane().add( colorList );
        redL.setLocation( 10, 275);
        redL.setSize( 55, 55 );
        greenL.setLocation( 10, 325);
        greenL.setSize( 55, 55 );
        blueL.setLocation( 10, 375);
        blueL.setSize( 55, 55 );
        redF.setLocation( 60, 280 );
        redF.setSize( 100, 40 );
        greenF.setLocation( 60, 330 );
        greenF.setSize( 100, 40 );        
        blueF.setLocation( 60, 380 );
        blueF.setSize( 100, 40 );
        plusRed.setLocation( 170, 280 );
        plusRed.setSize( 150, 40 );
        plusBlue.setLocation( 170, 380 );
        plusBlue.setSize( 150, 40 );
        plusGreen.setLocation( 170, 330 );
        plusGreen.setSize( 150, 40 );      
        minusRed.setLocation(325, 280);
        minusRed.setSize(150 ,40);
        minusGreen.setLocation(325, 330);
        minusGreen.setSize(150 ,40);
        minusBlue.setLocation(325, 380);
        minusBlue.setSize(150 ,40);
        save.setLocation( 5, 430 );
        save.setSize( 235, 50 );
        reset.setLocation( 245, 430 );
        reset.setSize( 235, 55 );
    }

    public void assignHandler() {
        addWindowListener( new destroyWindow() );
        colorList.addListSelectionListener( new listH() );
        save.addActionListener( new actionC() );
        reset.addActionListener( new actionC() );
        plusRed.addActionListener( new actionC(){
            public void actionPerformed( ActionEvent event){
                 if( redB < 255 ){
                    redB += 5;
                    redF.setText( redB + "" );
                    repaint();
                    ColorSampler.this.setTitle(titleChanged);
                }
            }
        });
        plusGreen.addActionListener( new actionC() {
            public void actionPerformed( ActionEvent event){
              if( greenB < 255 ){
                    greenB += 5;
                    greenF.setText( greenB + "" );
                    repaint();
                    ColorSampler.this.setTitle(titleChanged);                    
                }
            }
        });

        plusBlue.addActionListener( new actionC() {
            public void actionPerformed( ActionEvent event){
                if( blueB < 255 ){
                    blueB += 5;
                    blueF.setText( blueB + "" );
                    repaint();
                    ColorSampler.this.setTitle(titleChanged);
                }
            }            
        });
        minusRed.addActionListener( new actionC() {
            public void actionPerformed( ActionEvent event){
                if( redB > 0 ){
                    redB -= 5;
                    redF.setText( redB + "" );
                    repaint(); 
                    ColorSampler.this.setTitle(titleChanged);
                }
            }
        });
        minusGreen.addActionListener( new actionC() {
            public void actionPerformed( ActionEvent event){
                if( greenB > 0 ){
                    greenB -= 5;
                    greenF.setText( greenB + "" );
                    repaint(); 
                    ColorSampler.this.setTitle(titleChanged);
                }
            }
        });

        minusBlue.addActionListener( new actionC() {
            public void actionPerformed( ActionEvent event){
                if( blueB > 0 ){
                    blueB -= 5;
                    blueF.setText( blueB + "" );
                    repaint(); 
                    ColorSampler.this.setTitle(titleChanged);
                }
            }
        });
    }

    private class destroyWindow extends WindowAdapter {
        public void windowClosing( WindowEvent event){
                try{
                    FileOutputStream ostream = new FileOutputStream("input.txt");
                    PrintWriter writer = new PrintWriter(ostream);
                    for( int i = 0; i < numColors; i++ ){
                        writer.println( colorArray[i].colorName + " " + colorArray[i].red + " " + colorArray[i].green + " " + colorArray[i].blue);
                    }
                    writer.flush();
                    ostream.close();
                }
                catch ( IOException exception ) {
                    System.out.println("Error");
                }
                System.exit(0);
        }    
    }

    private class actionC implements ActionListener {
        public void actionPerformed( ActionEvent event ){
            if( event.getSource() == save ){
                int i = colorList.getSelectedIndex();
                colorArray[ i ].red = redB;
                colorArray[ i ].green = greenB;
                colorArray[ i ].blue = blueB;
                ColorSampler.this.setTitle(title);
            }
              if( event.getSource() == reset ){
                ColorSampler.this.setTitle(title);
                int i = colorList.getSelectedIndex();
                redB = colorArray[i].red;
                greenB = colorArray[i].green;
                blueB = colorArray[i].blue;
                redF.setText( redB + "");
                greenF.setText( greenB + "");
                blueF.setText( blueB + "");          
                repaint();
            }
            repaint();
        }
    }

    private class listH implements ListSelectionListener {
        public void valueChanged( ListSelectionEvent event ){
            if( event.getSource() == colorList ){
                if( !event.getValueIsAdjusting() ){
                    ColorSampler.this.setTitle(title);
                    int i = colorList.getSelectedIndex();
                    String temp = colorNames[ i ];
                    redB = colorArray[ i ].red;
                    redF.setText( redB + "" );
                    greenB = colorArray[ i ].green;
                    greenF.setText( greenB + "" );
                    blueB = colorArray[ i ].blue;
                    blueF.setText( blueB + "" );
                    repaint();
                }
            }
        }
    }
	
    public class displayC extends JComponent {
        public void paint( Graphics g){
            Dimension d = getSize();
            g.setColor( new Color( redB, greenB, blueB, 255 ) );
            g.fillRect( 0, 0, d.width-2, d.height-2 );
        }
    }

    public void readInput( colorObject[] array ) throws IOException {
        FileInputStream stream = new FileInputStream("input.txt");  
        InputStreamReader reader = new InputStreamReader(stream); 
        StreamTokenizer tokens = new StreamTokenizer(reader); 
        while( tokens.nextToken() != tokens.TT_EOF ) {
            array[ numColors ] = new colorObject();
            array[ numColors ].colorName = ( String ) tokens.sval;
            tokens.nextToken();
            array[ numColors ].red = ( int ) tokens.nval;
            tokens.nextToken();
            array[ numColors ].green = ( int ) tokens.nval;
            tokens.nextToken();
            array[ numColors ].blue = ( int ) tokens.nval;
            numColors++;
        }
        stream.close();
    }
}