import java.util.Timer;
import java.util.TimerTask;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class DateFrame extends JFrame implements ActionListener,KeyListener{
    
    Color[] colors = { Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.ORANGE,
        Color.YELLOW,
        Color.CYAN,
        Color.MAGENTA,
        Color.PINK,
        Color.LIGHT_GRAY,
        Color.DARK_GRAY,
        Color.BLACK,
        Color.WHITE};
    int colorindex = 0;
    
    JPanel topPanel;
    JPanel centerPanel;
    JPanel radioPanel;
    JPanel comboPanel;
    
    JLabel title;
    JLabel resultlabel;

    JRadioButton englishdate; // radiobutton which gets to sets the combobox for nepali to englsih date
    JRadioButton nepalidate; // radiobutton which gets to set the combobox to convert english to nepali date 
    
    JComboBox EnglishYear;
    JComboBox EnglishMonth;
    JComboBox EnglishDay;
    

    JButton submit;


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
       

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                title.setForeground(colors[colorindex]);
                colorindex = (colorindex + 1) % colors.length;

            }
            
        }, 0,5000);

        topPanel.add(title);
        //--------------------------------------------------------------

        //----------------CENTER PANEL-----------------------------------

        centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(25,10,10,10));
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        //centerPanel.setBackground(null);
        // centerPanel.setOpaque(true);
        //centerPanel.setPreferredSize(new Dimension(400,400));
        
        
        radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel,BoxLayout.X_AXIS));
        //created a new button group so that the user can select one radio button at a time.
        ButtonGroup radiobuttonGroup = new ButtonGroup();


        englishdate = new JRadioButton();
        englishdate.setSelected(true);
        englishdate.setText("English To Nepali Date");
        englishdate.setFont(new Font("Calibri",Font.PLAIN,15));
        englishdate.setFocusable(false);
        
        nepalidate = new JRadioButton();
        nepalidate.setText("Nepali to English Date");     
        nepalidate.setFont(new Font("Calibri",Font.PLAIN,15));
        nepalidate.setFocusable(false);

        //adding the two radiobuttons into the buttongroup
        radiobuttonGroup.add(englishdate);  
        radiobuttonGroup.add(nepalidate);

        radioPanel.add(englishdate);
        radioPanel.add(nepalidate);

        //creating a subpanel for the centerpanel
        comboPanel = new JPanel();
        //adding the english years to the combobox
        int startyear = 1944;
        int endyear = 2024;
        int totalyears = endyear -startyear +1;
    
        String[] englishyear = new String[totalyears];
    
        for(int i = 0;i < totalyears;i++){
            englishyear[i] = String.valueOf(startyear + i);
        }
        EnglishYear = new JComboBox<String>(englishyear);
        EnglishYear.setFocusable(false);
        

        int totalmonth = 12;
        String[] englishmonth = new String[totalmonth];
        for(int i = 0;i<totalmonth;i++){
            englishmonth[i]= String.format("%02d",i+1);
        }
        EnglishMonth = new JComboBox<String>(englishmonth);
        EnglishMonth.addActionListener(this);
        EnglishMonth.setFocusable(false);
       
        int totaldays=31;
        String[] englishday = new String[totaldays];
        for(int i = 0;i<totaldays;i++){
            englishday[i]= String.format("%02d",i+1);
        }
        EnglishDay = new JComboBox<>(englishday);
        EnglishDay.setFocusable(false);

        //used for submitting the total info 
        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.addActionListener(this);
        
        resultlabel = new JLabel();

        comboPanel.add(EnglishYear);
        comboPanel.add(EnglishMonth);
        comboPanel.add(EnglishDay);
        comboPanel.add(submit);

        centerPanel.add(radioPanel);
        centerPanel.add(comboPanel);
        centerPanel.add(resultlabel);

        //----------------------------------------------------------------
        this.addKeyListener(this);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(topPanel,BorderLayout.NORTH);
        this.setVisible(true);
    }


    
    public long DifferenceEnglishAndNepaliDates(String ReferenceDate,String DateToConvert){
        //THIS method is used for finding the difference between the reference date and the daate we want to convertto.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate refDate= LocalDate.parse(ReferenceDate,format);
        LocalDate convertdaDate = LocalDate.parse(DateToConvert,format);
        
        long differencedate = ChronoUnit.DAYS.between(refDate,convertdaDate);      
        return differencedate;
    }
    
    public String  addDifferencetoNepaliDate(long differencedate){
   
        int year=2000;
        int month= 9;
        int day=17;

        while (differencedate!=0) {
            day++;
            if(day>daysinMonth(year, month)){
                day=1;
                month++;
            }
            if(month>12){
                month=1;
                year++;
            }

        
            differencedate--; //keeps on running till the differencedate is not 0;
        }
        String addedDate = year+"-"+month+"-"+day;
        return addedDate;
 
    }



    private int daysinMonth(int year, int month) {
        
    Map<Integer, int[]>  nepaliMap = new HashMap<Integer, int[]>();

    nepaliMap.put(2000, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2001, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2002, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2003, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2004, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2005, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2006, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2007, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2008, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31 });
    nepaliMap.put(2009, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2010, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2011, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2012, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
    nepaliMap.put(2013, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2014, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2015, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2016, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
    nepaliMap.put(2017, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2018, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2019, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2020, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2021, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2022, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
    nepaliMap.put(2023, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2024, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2025, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2026, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2027, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2028, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2029, new int[] { 0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2030, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2031, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2032, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2033, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2034, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2035, new int[] { 0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31 });
    nepaliMap.put(2036, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2037, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2038, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2039, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
    nepaliMap.put(2040, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2041, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2042, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2043, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
    nepaliMap.put(2044, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2045, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2046, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2047, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2048, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2049, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
    nepaliMap.put(2050, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2051, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2052, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2053, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
    nepaliMap.put(2054, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2055, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2056, new int[] { 0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2057, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2058, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2059, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2060, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2061, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2062, new int[] { 0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31 });
    nepaliMap.put(2063, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2064, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2065, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2066, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31 });
    nepaliMap.put(2067, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2068, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2069, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2070, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
    nepaliMap.put(2071, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2072, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2073, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
    nepaliMap.put(2074, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2075, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2076, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
    nepaliMap.put(2077, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
    nepaliMap.put(2078, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2079, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
    nepaliMap.put(2080, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
    nepaliMap.put(2081, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2082, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2083, new int[] { 0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2084, new int[] { 0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2085, new int[] { 0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2086, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2087, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2088, new int[] { 0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2089, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
    nepaliMap.put(2090, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
    
    return nepaliMap.get(year)[month];
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String selectedyear = EnglishYear.getSelectedItem()+"-"+EnglishMonth.getSelectedItem()+"-"+EnglishDay.getSelectedItem();

            long diff = DifferenceEnglishAndNepaliDates("1944-01-01", selectedyear);
            String NepaliDateConverted= addDifferencetoNepaliDate(diff);

            centerPanel.removeAll();

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            resultlabel.setText("The Converted Nepali Date is: " +NepaliDateConverted);
            resultlabel.setFont(new Font("Calibiri",Font.BOLD,25));
            
            resultPanel.add(resultlabel);

            centerPanel.add(resultPanel);
            centerPanel.revalidate();
            centerPanel.repaint();
            
           
      }
    }



    @Override
    public void keyTyped(KeyEvent e) {
      //no
    }



    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()== KeyEvent.VK_ENTER){
        new DateFrame();
        dispose();
      }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        //no
    }
}
