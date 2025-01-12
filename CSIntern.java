public class CSIntern extends Adventurer {
    int experience, experienceMax;
    public CSIntern(String name, int hp) {
        super(name, hp);
        setmaxHP(25);
        
        experienceMax = 15;
        experience = 9;
    
      }
    
      public String getSpecialName(){
        return "work experience";
      }
      
      public int getSpecial(){
        return experience;
      }
    
      public void setSpecial(int n){
        experience = n;
      }
    
      public int getSpecialMax(){
        return experienceMax;
      }
    
      public String attack(Adventurer other){
        int damage = (int)(Math.random()*2)+1; // 1 to 2 damage
        other.applyDamage(damage);
        restoreSpecial(1); // psasively restore 1 special
        return this + " attacked "+ other + " and dealt "+ damage +
        " points of damage. They then pick up the (1) money " + other + "dropped";
      }
    
    
      public String specialAttack(Adventurer other){
        if(getSpecial() >= 1){
          setSpecial(getSpecial()-1); // consumes 1 daddy's money
          int duration = (int)(Math.random()*2) + 1; // paralyzes the opponent for the next 1-2 rounds
    
          other.setParalyzedD(duration);
          return this + " flaunts their bank account, causing the opponent to fall into a depression for the next "
          + duration + " rounds";
        }
        else{
          return "Not enough money to use special attack. Instead "+attack(other);
        }
    
      }
      // 
      public String support(Adventurer other){
        return "Gives a coffee to "+other+" and restores "
        + other.restoreSpecial(5)+" "+other.getSpecialName();
      }
      /*Restores 6 special and 1 hp to self.*/
      public String support(){
        int hp = 1;
        setHP(getHP()+hp);
        this.setSpecial(getSpecial() - 3);
        return this+ " locks in, boosting its damage by 1.3x for the next 2 rounds and  "+restoreSpecial(6)+" "
        + getSpecialName()+ " and "+hp+" HP"; //should we make another field for "buffed" in adventurer or just this one
      }
}
