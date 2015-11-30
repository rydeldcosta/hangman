/*
 weee i just changed this comment
 */

package hangman;

/**
 *
 * @author Rydel D'costa
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.*;

class CPU {
    String[] easy = {"ADEPT", "AGENT", "BLAND", "CABLE", "CREEP", "DIARY", "EVADE", "FRESH", "GHOST", "IMAGE", "LOYAL", "MOIST", "OVARY", "ROUND", "SHEET"};
    
    String[] easy_mean = {"One fully skilled or well versed in anything; a proficient", "An active power or cause; that which has the power to produce an effect", "Mild; soft; gentle; smooth and soothing in manner", "A rope of steel wire, or copper wire", "To move or behave with servility or exaggerated humility", "A register of daily events or transactions; a daily record; a journal", "To attempt to escape", "Youthful; florid", "Any faint shadowy semblance; an unsubstantial image; a phantom", "A picture, example, or illustration, often taken from sensible objects", "Faithful to law; upholding the lawful authority", "Wet", "The essential female reproductive organ in which the ova are produced", "Having a curved out", "A single signature of a book or a pamphlet"};
    
    String[] medium = {"ABILITY", "BETWEEN", "CAPSULE", "DISTORT", "ELEMENT", "FULFILL", "HISTORY", "KINGDOM", "MIXTURE", "PATIENT", "QUININE", "RAINBOW", "SCIENCE", "THEATRE", "VAMPIRE"};
    
    String[] medium_mean = {"The quality or state of being able; power to perform", "In the space which separates", "A small cylindrical or spherical gelatinous envelope in which nauseous or acrid doses are inclosed to be swallowed", "To twist of natural or regular shape; to twist aside physically", " one of the imaginary principles of matter.", "To accomplish or carry into effect", "A systematic, written account of events, particularly of those affecting a nation, institution, science, or art, and usually connected with a philosophical explanation of their causes", "The territory or country subject to a king or queen; the dominion of a monarch", "a substance made by mixing other substances together", "Having the quality of enduring", "An alkaloid extracted from the bark of several species of cinchona", "A bow or arch exhibiting, in concentric bands, the several colors of the spectrum", "Accumulated and established knowledge, which has been systematized and formulated with reference to the discovery of general truths or the operation of general laws", "A sphere or scheme of operation", "blood-sucking bats"};

    String[] hard = {"AUTHENTIC", "AUTOGRAPH", "BIOGRAPHY", "DIAPHRAGM", "EVOLUTION", "FANTASTIC", "GEOGRAPHY", "HOROSCOPE", "INFLUENCE", "PROJECTOR", "PARAGRAPH", "SUBSTANCE", "TAUTOLOGY", "TELEGRAPH", "XYLOPHONE"};
    
    String[] hard_mean = {"genuine", "a person's own signature or handwriting", "The written history of a person's life", "The muscular and tendinous partition separating the cavity of the chest from that of the abdomen", "a gradual unfolding of successive phases of growth or development", "Having the nature of a phantom; unreal", "The science which treats of the world and its inhabitants", "The planisphere invented by Jean Paduanus", "controlling power quietly exerted", "An optical instrument for projecting a picture upon a screen", "A distinct part of a discourse or writing", "real or existing essence", "A repetition of the same meaning in different words", "An apparatus, or a process, for communicating intelligence rapidly between distant points", "a series of strips of wood or glass graduated in length to the musical scale"};
    
    String word;
    
    int len, lives, count, max, min, const_len;
    JLabel message, pic;
  //Hangman h;
    JLabel [] alpha,blanks;
    JFrame fwin,flose;
    JButton playagainw,playagain;
    JLabel win,lose,i1,i2;
    JPanel pane1,pane2;
    JFrame fr;
    
      ImageIcon ic, ic1, ic2, ic3, winimg,loseimg;  
      Font hintFont = new Font("Engravers MT",Font.PLAIN,12);
      Font bFont = new Font("Engravers MT",Font.PLAIN,22);
      Font mFont = new Font("Engravers MT",Font.PLAIN,15);
      Font msgFont = new Font("Engravers MT",Font.BOLD,20);
       
    Player p = new Player();
     
    public CPU()
    {
        ic = new ImageIcon(getClass().getResource("hang1.jpg"));
        ic1 = new ImageIcon(getClass().getResource("hang2.jpg"));
        ic2 = new ImageIcon(getClass().getResource("hang3.jpg"));
        ic3 = new ImageIcon(getClass().getResource("hang4.jpg"));
        winimg = new ImageIcon(getClass().getResource("win_gif.gif"));
        loseimg = new ImageIcon(getClass().getResource("lose_gif.gif"));
        
        max = 14;
        min = 1;
        lives = 3;
        count = 0;
        fwin = new JFrame();
        //fwin.setContentPane(message);
        fwin.setSize(710, 400);
        playagainw = new JButton("Play Again");
        playagainw.setFont(bFont);
        win = new JLabel("Congratulations! You Win !");
        win.setFont(bFont);
        
        pane1 = new JPanel();
      
        pane1.setLayout(new BorderLayout());
        i1 = new JLabel(winimg);
      
        pane1.add(BorderLayout.WEST,i1);
        pane1.add(BorderLayout.EAST,win);
        pane1.add(BorderLayout.SOUTH,playagainw);
        fwin.add(pane1);
        playagainw.addActionListener(new PlayAgain());
        
       // fwin.setBackground(Color.yellow);
        flose = new JFrame();
        flose.setSize(710, 400);
        
        playagain = new JButton("Play Again");
        playagain.setFont(bFont);
        pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        lose = new JLabel("You Lose! The correct word was "+ word+". Try again maybe ?");
        lose.setFont(mFont);
        i2 = new JLabel(loseimg);
        pane2.add(BorderLayout.WEST,i2);
        pane2.add(BorderLayout.EAST,lose);
        pane2.add(BorderLayout.SOUTH,playagain);
        flose.add(pane2);
        playagain.addActionListener(new PlayAgain());
        
       // h = new Hangman();
     //   setword(pl);
    }
    
    void good_message() {
       
       message.setText("WELL DONE ! You got a HIT ") ;
       message.setFont(msgFont);
    }
    
    void verygood_message() {
       message.setFont(msgFont);
       message.setText("BRAVO! You got multiple HITS ! Yippie ") ;
    }
    
    void bad_message() {
        message.setText("WRONG ANSWER!!! Lives Remaining : " +lives);
        message.setFont(msgFont);
        
    }
    
    void setword(Player p) {
        
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
     //   System.out.println(p.difficulty);
        message.setFont(hintFont);
        if (p.difficulty == 1) {
            word = easy[randomNum];
            //message.setFont(msgFont);
            message.setText("HINT : "+ easy_mean[randomNum]);
            
        }
        
        else if (p.difficulty == 2) {
            word = medium[randomNum];
            //message.setFont(msgFont);
            message.setText("HINT : "+medium_mean[randomNum]);
        }
        
        else {
            word = hard[randomNum];
            //message.setFont(msgFont);
            message.setText("HINT : "+hard_mean[randomNum]);
        }
        len = word.length();
        const_len = len;
        
                
    }
    
    int check_length()
    {
        return len;
    }
    
    void check_lives(){
       // return lives;
        if (lives == 2) {
            pic.setIcon(ic1);
        }  
        
        else if (lives == 1) {
            pic.setIcon(ic2);
        }
        
        else if (lives == 0) {
            pic.setIcon(ic3);
        }
    }
    
    void check(Player p) {
        count = 0;
    //    String alpha = p.curr_alpha;
        
        for (int i = 0; i < const_len; i++) {
            char c = word.charAt(i);
            String str = "" + c;
            if (str.equals(p.getAlpha())) {
                count++;
                alpha[i].setText(" " + str+" ");
                blanks[i].setText(" ");
                len--;
            }
        }
        
        if (count > 1) {
            verygood_message();
        }
        
        else if (count == 1) {
            good_message();
        }
        
        else {
            lives--;
            bad_message();
            
        }
        check_lives();
       if (len == 0) {
           fwin.setVisible(true);
           fwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
       } 
        
       if (lives == 0) 
       {
           lose.setText("You Lose! The correct word was "+ word);
           flose.setVisible(true);
           flose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
       }
    }
    
    public class PlayAgain implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            Hangman hg = new Hangman();
            hg.run();
            fwin.setVisible(false);
            flose.setVisible(false);
            fr.setVisible(false);
        }
    }
    
}


