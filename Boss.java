public class Boss extends Adventurer {
    int savings, savingsMax;
    public Boss String name, int hp) {
        super(name, hp);
        setmaxHP(30);

        experienceMax = 16;
        experience = 14;

    }

    public CSIntern(String name) {
      this(name, (int)(Math.random()*3) + 25);
    }

    public CSIntern() {
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
		int damage = (int)(Math.random()*2)+1; // 1 to 2 damage
		other.applyDamage(damage);
		restoreSpecial(1); // passively restore 1 special
		return this + " attacked "+ other + " and dealt "+ damage +
		" points of damage. They then pick up the " + this.restoreSpecial(1) + " money " + other + " dropped";
	  }

      //4 points of damage. Consumes 2 work experience
      public String specialAttack(Adventurer other){
        if(this.getSpecial() > 1){
          int damage = 4;
          other.setHP(other.getHP() - damage);
		  this.setSpecial(this.getSpecial()-2); // consumes 1 work experience
          return this + " shows " + other + " their superior resume, dealing "
          + damage + " damage";
        }
        else{
          return "Not enough " + this.getSpecialName() + " to use special attack. Instead "+ this.attack(other);
        }

      }

      // restores 1 special and 2 health for 2 work experience
      public String support(Adventurer other){
		if(this.getSpecial() > 1){
			int hp = 2;
			if(other.getHP() + hp > other.getmaxHP()){
				hp = other.getmaxHP() - other.getHP();
			}
			other.setHP(hp);
			this.setSpecial(this.getSpecial() - 2);
			return this + " advises "+other+" to avoid unpaid internships and restores "
			+ other.restoreSpecial(1)+" "+other.getSpecialName() + " and " + hp + " HP";
		 }else{
		 return this + " doesn't have enough " + this.getSpecialName() + " to support ally";
		 }
      }

      /*uses 3 work experience: adds buffedD for 2 rounds Restores 4 hp to self, only hp if insufficient experience.*/
      public String support(){
    		int hp = 3;
    		if(getHP() + hp > getmaxHP()){
    		    hp = getmaxHP() - getHP();
    		}
        setHP(getHP()+hp);
			  return this + " checks their bank account, increasing their sense of security, +" + restoreSpecial(2) +  " " + getSpecialName() + ", and +" + hp + " HP"; //should we make another field for "buffed" in adventurer or just this one
	  }

}
