public class Boss extends Adventurer {
    int savings, savingsMax;
    public Boss (String name, int hp) {
        super(name, hp);
        setmaxHP(55);

        savingsMax = 25;
        savings = 14;

    }

    public Boss(String name) {
      this(name, (int)(Math.random()*3) + 48);
    }

    public Boss() {
      this("Senior");
    }
      public String getSpecialName(){
        return "savings";
      }

      public int getSpecial(){
        return savings;
      }

      public void setSpecial(int n){
        savings = n;
      }

      public int getSpecialMax(){
        return savingsMax;
      }

      public String attack(Adventurer other){
		if(this.getParalyzedD() > 0){
			this.setParalyzedD(this.getParalyzedD() - 1);
			return this + " is currently too depressed to move, and will still be for the next " + this.getParalyzedD() + " rounds";
		}
		int damage = (int)(Math.random()*3)+4; // 4 to 6 damage
		other.applyDamage(damage);
		restoreSpecial(1); // passively restore 1 special
		return this + " attacked "+ other + " and dealt "+ damage +
		" points of damage. They then pick up the " + this.restoreSpecial(1) + " " + this.getSpecialName() + " "+ other + " dropped";
	  }

      //4 to 10 points of damage. Consumes 2 work experience
      public String specialAttack(Adventurer other){
		if(this.getParalyzedD() > 0){
			this.setParalyzedD(this.getParalyzedD() - 1);
			return this + " is currently too depressed to move, and will still be for the next " + this.getParalyzedD() + " rounds";
		}
        if(this.getSpecial() > 2){
          int damage =  (int) (Math.random()*7)+4; //4 - 10 damage
          other.applyDamage(damage);
		  this.setSpecial(this.getSpecial()-3); // consumes 1 work experience
          return this + " pulls up to the interview in their Jaguar and takes the job from " + other + ". Doing " 
		  + damage + " points of damage. They spend 3 " + this.getSpecialName() + " for unemployment expenses";   
        }
        else{
          return "Not enough " + this.getSpecialName() + " for unemploymeny spending while job hunting. Instead "+ this.attack(other);
        }
      }

      // restores 1 special and 2 health for 2 work experience
      public String support(Adventurer other){
        if(this.getParalyzedD() > 0){
          this.setParalyzedD(this.getParalyzedD() - 1);
          return this + " is currently too depressed to move, and will still be for the next " + this.getParalyzedD() + " rounds";
        }
        return this + " is the Boss, so they support themself like a boss: " + this.support();
      }

      /*Health + 4 special + 2 */
      public String support(){
        if(this.getParalyzedD() > 0){
          this.setParalyzedD(this.getParalyzedD() - 1);
          return this + " is currently too depressed to move, and will still be for the next " + this.getParalyzedD() + " rounds";
        }
        setHP(getHP()+6);
			  return this + " checks their bank account, increasing their sense of security, +" + restoreSpecial(4) +  " " + getSpecialName() + ", and +" + 6 + " HP"; //should we make another field for "buffed" in adventurer or just this one
	  }

}
