import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Number_Guessing_Game1 
{

    JTextField t1,t2,t3,t4,moreText;
    JLabel j4,moreRounds;
    ButtonListener bl1;
    ButtonListener2 bl2;
    ButtonListener3 bl3;

    Random r=new Random();
    int rand=r.nextInt(101);
    int count=0;
    int maxAttempts=5;
    int moreTries=0;
    public Number_Guessing_Game1()
    {

      // creating Frame
      JFrame frame=new JFrame("Number Guessing Game");
      frame.setSize(550,550);
      frame.setLayout(null);
      frame.setVisible(true);
      //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Creating Image
      JLabel pic=new JLabel("");
      pic.setIcon(new ImageIcon("brown.jpg"));
      pic.setLocation(5,50);
      pic.setSize(325,290);
      frame.add(pic);
      
      // Creating Label Guess my number Game
      JLabel g=new JLabel("Guess My Number Game");
      g.setSize(290,30);
      g.setLocation(350,40);
      g.setFont(new Font("tunga",Font.BOLD,24));
      g.setForeground(Color.darkGray);
      frame.add(g);

      //Creating label for Enter a number....
      JLabel lb1=new JLabel("Enter a number(1-100)");
      lb1.setSize(270,20);
      lb1.setLocation(407,75);
      lb1.setFont(new Font("tunga",Font.PLAIN,17));
      frame.add(lb1);

      //Creating TextField for input guess
       t1=new JTextField(15);
       t1.setSize(80,30);
       t1.setLocation(455,115);
       frame.add(t1);

       //Creating Label for Display Message
       j4=new JLabel("Try and guess my number");
       j4.setFont(new Font("tunga",Font.PLAIN,17));
       j4.setSize(260,20);
       j4.setLocation(405,160);
       frame.add(j4);
       
       //Creating Label for best score
       JLabel j5=new JLabel("Best Score");
       j5.setFont(new Font("tunga",Font.PLAIN,17));
       j5.setSize(110,20);
       j5.setLocation(10,12);
       j5.setForeground(Color.DARK_GRAY);
       frame.add(j5);

    //Creating Text field for best score
    t2=new JTextField(10);
    t2.setSize(50,20);
    t2.setLocation(105,12);        
    frame.add(t2);

    //Creating number of attempts label
    JLabel j6=new JLabel("Attempts");
    j6.setFont(new Font("tunga",Font.PLAIN,17));
    j6.setSize(110,20);
    j6.setLocation(175,12);
    j6.setForeground(Color.DARK_GRAY);
    frame.add(j6);

    //Creating attempts text field
    t3=new JTextField(10);
    t3.setSize(50,20);
    t3.setLocation(255,12);
    frame.add(t3);


    //creating 3 buttons
    JButton b1=new JButton("Guess");
    b1.setSize(150,40);
    b1.setLocation(415,290);
    bl1=new ButtonListener();        
    b1.addActionListener(bl1);
    frame.add(b1);

    JButton b2=new JButton("Give up!");
    b2.setSize(110,30);
    b2.setLocation(350,230);
    bl2=new ButtonListener2();
    b2.addActionListener(bl2);     
    frame.add(b2);

    JButton b3=new JButton("Play Again");    
    b3.setSize(120,30);
    b3.setLocation(515,230);    
    bl3=new ButtonListener3();        
    b3.addActionListener(bl3);
    frame.add(b3);
    
    //Changing TextFields to UnEditable
    t2.setEditable(false);
    t3.setEditable(false);  
    
    //Want to add more rounds label
    moreRounds=new JLabel("Want to add more rounds");
    moreRounds.setSize(180,25);
    moreRounds.setLocation(405,185);
    moreRounds.setVisible(false);
    moreRounds.setFont(new Font("tunga",Font.PLAIN,15));
    frame.add(moreRounds);

    //Want to add more rounds textfield
    moreText=new JTextField(10);
    moreText.setSize(50,25);
    moreText.setLocation(580,185);     
    moreText.setVisible(false);   
    frame.add(moreText);


    }

    private class ButtonListener implements ActionListener
    {
        int bestScore=100;
        public void actionPerformed(ActionEvent e)
        {
            if(count<maxAttempts)
            {
            int a = Integer.parseInt(t1.getText());
            count=count+1;
            if(a<rand)
            {
                j4.setText(a+" is too low, try again!!");
                j4.setForeground(Color.RED);
                if(moreTries==2)
                {
                  t3.setText((count+2)+"");
                }
                else
                  t3.setText(count+"");
            }    
            else if(a>rand)
            {
                j4.setText(a+" is too high, try again!!");
                j4.setForeground(Color.RED);
                if(moreTries==2)
                {
                    t3.setText((count+2)+"");
                }
                else
                t3.setText(count+"");
            }
            else
            {
                j4.setText("CORRECT, YOU WIN!!!!"); 
                t1.setEditable(false);
                j4.setFont(new Font("tunga",Font.PLAIN,17));
                j4.setForeground(Color.green); 
                if(moreTries!=2)
                { 
                bestScore= (6-count)*20;
                t2.setText(bestScore+"");
                t3.setText(count+"");
                }
                else
                {
                    bestScore= (6-count)*15;
                    t2.setText(bestScore+"");
                    t3.setText((count=count+2)+"");
                }
            }

            //setting focus to input guess text field
            t1.requestFocus();
            t1.selectAll();

        }
        else if(moreTries<2)
        {
             moreTries++;
            j4.setText("Reached to maximum attempts");
            j4.setForeground(Color.blue); 
            t1.setText("");
            moreRounds.setVisible(true);
            moreText.setVisible(true);
            String str=moreText.getText();
            if(str.equalsIgnoreCase("yes"))
            {
                count=count-2;
                moreRounds.setVisible(false);
                moreText.setVisible(false);
                j4.setText("Two more rounds added");
                t1.setText("");
                j4.setForeground(Color.green); 
               
                moreText.setText("");
            }
            else if(str.equalsIgnoreCase("no"))
            {
                moreRounds.setVisible(false);
                moreText.setVisible(false);
                j4.setText("All attempts finished !!!You Lose");
                t2.setText(00+"");
                j4.setForeground(Color.RED);
                t1.setEditable(false);
            }
         }
         else
         {
          j4.setText("All attempts finished !!!You Lose");
          t2.setText(00+"");
          j4.setForeground(Color.RED);
          t1.setEditable(false);
         }
        }
    }  

    private class ButtonListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            t3.setText("");
            j4.setText(rand+" is the answer!");
            j4.setFont(new Font("tunga",Font.PLAIN,20));
            j4.setForeground(Color.GREEN);
            t1.setText("");
            t1.setEditable(false);
        
        }
    }        

    private class ButtonListener3 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            rand=(int) (Math.random()*100);
            t1.setText("");
            t3.setText("");
            j4.setText("Try and guess my number");
            j4.setForeground(Color.BLACK);
            t3.setText("");
            t2.setText("");
            t1.setText("");
            count=0;
            moreTries=0;
            moreRounds.setVisible(false);
            moreText.setVisible(false);
            t1.setEditable(true);    
            t1.requestFocus();
        }
    }

    public static void main(String[] args) {
       
        new Number_Guessing_Game1();
    }
}