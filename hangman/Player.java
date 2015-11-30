/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangman;

/**
 *
 * @author Rydel D'costa
 */
class Player {
    
    String name;
    int difficulty; //1 easy 2 medium 3 hard
    String curr_alpha;
    public void setDifficulty(String d)
    {
       if(d.equals("Easy"))
           difficulty = 1;
       else if (d.equals("Medium"))
           difficulty = 2;
       else
           difficulty = 3;
       
    }
    
    public void setAlpha(String a)
    {
        this.curr_alpha = a;
        
    }
    public String getAlpha()
    {
        return this.curr_alpha;
        
    }
}
