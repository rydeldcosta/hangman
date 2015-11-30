

package hangman;



import javax.swing.ImageIcon;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Hangman {

    
    int count=0;
    JLabel [] alphaspace;
    JPanel left,center,south,right,north;
    JLabel title,pan2,p,hangman;
    JTextField text;
    JButton click,image,play;
    JPanel mainpanel,mainpanel1,mainpanel2,mainpanel0;
    JToggleButton [] letters;
    
    CardLayout cl;
    ImageIcon hangmanImg,bac;
    JRadioButton easy , medium , hard ;
    JTextField namefield;
    JLabel  Easy , Medium , Hard , Name ,label1 , label2 , label3 , label4 , label5 , label6 , label7;
    JPanel  panel1 , panel2 , panel3 ,panel121, pan,panx ;
    
    CPU cpu;
    Player player;
    Font rFont = new Font("serif",Font.PLAIN,28);
    Font btnFont = new Font("serif",Font.PLAIN,12);
    
    public Hangman()
    {
      //cpu = new CPU();
        player = new Player();
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        //public CPU cpu;
        
        Hangman hg = new Hangman();
        hg.run();
        
        
        
    }
    
    public void run()
    {
        
       cpu = new CPU();
       
        cl = new CardLayout();
        mainpanel = new JPanel();
        mainpanel.setLayout(cl);
        
        
        mainpanel2 = new JPanel();
        mainpanel2.setBackground(Color.WHITE);
        mainpanel2.setLayout(new BorderLayout());
        mainpanel1 = new JPanel();
        mainpanel2.setLayout(new BorderLayout());
       // mainpanel2.setBackground(Color.WHITE);
        
        cpu.fr = new JFrame("Hangman");
        cpu.fr.add(mainpanel);
        //mainpanel.add(mainpanel0,"zero");
        mainpanel.add(mainpanel1,"first");
        mainpanel.add(mainpanel2,"second");
        
        //TITLE
        Font bigFont = new Font("CHILLER",Font.BOLD,40);
        
        title = new JLabel("T H E   H A N G M A N");
        title.setFont(bigFont);
        title.setForeground(Color.red);
        //MAINPANEL1
        
        mainpanel1.setLayout(new BorderLayout());
      
        mainpanel1.setBackground(Color.white);

        //Font bigFont = new Font("serif" , Font.BOLD , 30);
        hangman = new JLabel("T H E   H A N G M A N");
        panx = new JPanel();
        panx.setBackground(Color.black);
        panx.add( BorderLayout.CENTER,hangman);
        mainpanel1.add(BorderLayout.NORTH, panx);
        hangman.setFont(bigFont); 
        hangman.setForeground(Color.red);
        
       
       //hangmanImg = new ImageIcon(getClass().getResource("hangmanImg.png"));
       hangmanImg = new ImageIcon(getClass().getResource("hangmanImg.png"));
       label7 = new JLabel(hangmanImg); 
       mainpanel1.add(BorderLayout.WEST , label7);
       
       panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1 , BoxLayout.Y_AXIS));
        mainpanel1.add(BorderLayout.CENTER,panel1);
        panel121 = new JPanel();
        mainpanel1.add(BorderLayout.EAST , panel121);
        panel121.setLayout(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        panel121.setBackground(Color.white);   
        mainpanel1.add(BorderLayout.EAST , panel121);  
        easy = new JRadioButton("Easy");
        easy.setFont(rFont);
        easy.setBackground(Color.white);
        x.weighty=6;
        x.weightx=6;
        panel121.add(easy,x);
        medium = new JRadioButton("Medium");
        medium.setFont(rFont);
        medium.setBackground(Color.white);
        medium.setBackground(Color.white);
        x.weighty=1;
        x.weightx=6;
        panel121.add(medium,x);
        hard = new JRadioButton("Hard");
        hard.setFont(rFont);
        hard.setBackground(Color.white);
        hard.setBackground(Color.white);
        x.weighty=2;
        x.weightx=6;
        panel121.add(hard,x);
       
        panel1.setBackground(Color.white);
       
        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        mainpanel1.add(BorderLayout.SOUTH,panel2);
        hangman = new JLabel("WELCOME DUKE ! Select a difficulty to continue");
        Font BigFont = new Font("serif" , Font.BOLD , 24);
        hangman.setFont(BigFont);
        panel2.add(hangman,c);
        panel2.setBackground(Color.white);

        
        
        
        easy.addActionListener(new Startgame());
        medium.addActionListener(new Startgame());
        hard.addActionListener(new Startgame()); 

        
        
        //MAINPANEL2
        
        left = new JPanel();
        right = new JPanel();
        center = new JPanel();
        south = new JPanel();
        north =new JPanel();
        
        //north
        mainpanel2.add(BorderLayout.NORTH,north);
        north.setBackground(Color.BLACK);
        north.add(title);
        
        //right
        mainpanel2.add(BorderLayout.EAST,right);
        right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
        JPanel r1,r2;
        r1 = new JPanel();
        //r2 = new JPanel();
        
        right.add(r1);
       // right.add(r2);
        r1.setLayout(new GridLayout(0,4));
        //r2.setLayout(new BoxLayout(r2,BoxLayout.Y_AXIS));
        
         letters = new JToggleButton[26];
        
        for(int i=0;i<26;i++)
        {
            char xy;
            int y;
            y = i+65;
            xy = (char) y;
            letters[i] = new JToggleButton(""+xy);
            letters[i].setFont(btnFont);
            letters[i].addActionListener(new setLetter());
            r1.add(letters[i]);
        }
        
        
        
        //center
      //  center.setLayout(new GridLayout(2,14));
        mainpanel2.add(BorderLayout.CENTER,center);
       // GridBagLayout gbag = new GridBagLayout();
        /*
        GridBagConstraints gbc = new GridBagConstraints();
        center.setLayout(new GridBagLayout());*/
        
        //south
        cpu.message = new JLabel("THIS IS A MESSAGE");
        south.setLayout(new FlowLayout());
        south.add(cpu.message);
        mainpanel2.add(BorderLayout.SOUTH,south);
        
        mainpanel2.add(BorderLayout.WEST,left);
        cpu.pic = new JLabel(cpu.ic);
        left.add(cpu.pic);
        
    
        cpu.fr.setSize(700,410);
        cpu.fr.setVisible(true);
        cpu.fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    public class setLetter implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            player.setAlpha(e.getActionCommand());
           
            
            cpu.check(player);
            
        }
    }
    public class Startgame implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            
            Font labelfont = new Font("serif",Font.PLAIN,22);
            player.setDifficulty(e.getActionCommand());
       //      cpu = new CPU(player);
            cpu.setword(player);
            
            GridBagConstraints gbc = new GridBagConstraints();
        center.setLayout(new GridBagLayout());
            int length = cpu.len;
    //   System.out.println(length);
        cpu.alpha = new JLabel[length];
        alphaspace = new JLabel[length];         //n
        cpu.blanks = new JLabel[length];            //2*n
        
       
        
        for(int i=0;i<length;i+=1)
        {
            cpu.alpha[i] = new JLabel(" * ");
            cpu.alpha[i].setFont(labelfont);
            alphaspace[i] = new JLabel("");
            alphaspace[i].setFont(labelfont);
        }
        for(int i=0;i<length;i++)
        {
            cpu.blanks[i] = new JLabel(" _ ");
            cpu.blanks[i].setFont(labelfont);
            //cpu.blanks[i+1] = new JLabel(" ");
            //cpu.blanks[i+1].setFont(labelfont);
        }
        
        for(int i=0;i<length;i++)
        {
            gbc.gridy = 1;
            gbc.gridx = i;
           // gbag.setConstraints(alpha[i], gbc);
            center.add(cpu.alpha[i],gbc);
           // gbc.gridx = i+2;
            //gbag.setConstraints(alphaspace[i], gbc);
            center.add(alphaspace[i],gbc);
        }
        for(int i=0;i<length;i++)
        {
            gbc.gridx = i;
            gbc.gridy = 2;
            //gbag.setConstraints(cpu.blanks[i], gbc);
            center.add(cpu.blanks[i],gbc);
            //center.add(alphaspace[i]);
        }
            cl.next(mainpanel);
            
        }
    }
   /* class playnow implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            cl.next(mainpanel);
        }
    }*/
    
    
}
