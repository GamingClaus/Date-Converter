import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class DateFrame extends JFrame {
    JPanel topPanel;
    JPanel centerPanel;
    JLabel title;
    JRadioButton englishdate; // radiobutton which gets to sets the combobox for nepali to englsih date
    JRadioButton nepalidate; // radiobutton which gets to set the combobox to convert english to nepali date 

    DateFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(650,650);
        this.setLocationRelativeTo(null);

        //----------------TOP PANEL-----------------------------------
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100,55));
        topPanel.setBackground(new Color(0x2193b0));
        topPanel.setOpaque(true);


        title = new JLabel();
        title.setText("DATE CONVERTER");
        title.setFont(new Font("Caslon",Font.BOLD,30));
        title.setForeground(Color.BLACK);

        topPanel.add(title);
        //--------------------------------------------------------------

        //----------------CENTER PANEL-----------------------------------

        centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(25,10,10,10));
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(null);
        centerPanel.setOpaque(true);
        centerPanel.setPreferredSize(new Dimension(400,400));
        
        //created a new button group so that the user can select one radio button at a time.
        ButtonGroup radiobuttonGroup = new ButtonGroup();


        englishdate = new JRadioButton();
        englishdate.setText("Nepali To English Date");
        englishdate.setFont(new Font("Calibri",Font.PLAIN,15));
        englishdate.setFocusable(false);
        
        nepalidate = new JRadioButton();
        nepalidate.setText("English to Nepali Date");     
        nepalidate.setFont(new Font("Calibri",Font.PLAIN,15));
        nepalidate.setFocusable(false);

        //adding the two radiobuttons into the buttongroup
        radiobuttonGroup.add(englishdate);  
        radiobuttonGroup.add(nepalidate);

        centerPanel.add(englishdate);
        centerPanel.add(nepalidate);

        //----------------------------------------------------------------

        this.add(centerPanel,BorderLayout.CENTER);
        this.add(topPanel,BorderLayout.NORTH);
        this.setVisible(true);
    }
    
}
