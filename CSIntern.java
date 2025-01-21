public class CSIntern extends Adventurer {
    int experience, experienceMax;
    public CSIntern(String name, int hp) {
        super(name, hp);
        setmaxHP(25);

        experienceMax = 15;
        experience = 9;

    }

    public CSIntern(String name) {
      this(name, (int)(Math.random()*3) + 19);
    }

    public CSIntern() {
      this("Daniel");
    }
      public String getSpecialName(){
        return "expr";
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
		if(this.getParalyzedD() > 0){
			this.setParalyzedD(this.getParalyzedD() - 1);
			return this + " is currently too depressed to attack, and will still be for the next " + this.getParalyzedD() + " rounds";
		}
		int damage = (int)(Math.random()*2)+1; // 1 to 2 damage
		if(this.getBuffedD() > 0){
			this.setBuffedD(other.getBuffedD() - 1);
			damage++;
		}
		other.applyDamage(damage);
		restoreSpecial(1); // passively restore 1 special
		return this + " attacked "+ other + " and dealt "+ damage +
		" points of damage. They then gain " + this.restoreSpecial(1) + " expr from the attack";
	  }

      //4 points of damage. Consumes 2 work experience
      public String specialAttack(Adventurer other){
		if(this.getParalyzedD() > 0){
			this.setParalyzedD(this.getParalyzedD() - 1);
			return this + " is currently too depressed to attack, and will still be for the next " + this.getParalyzedD() + " rounds";
		}
        if(this.getSpecial() > 1){
          int damage = 4;
		  if(this.getBuffedD() > 0){ 
		  this.setBuffedD(this.getBuffedD() - 1);
		  damage++;
		  }
          other.applyDamage(damage);
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
			other.setHP(other.getHP() + hp);
			this.setSpecial(this.getSpecial() - 2);
			return this + " advises "+other+" to avoid unpaid internships and restores "
			+ other.restoreSpecial(1)+" "+other.getSpecialName() + " and " + hp + " HP";
		 }else{
		 return this + " doesn't have enough " + this.getSpecialName() + " to support ally";
		 }
      }

      /*uses 3 work experience: adds buffedD for 2 rounds Restores 2 hp to self, only hp if insufficient experience.*/
      public String support(){
		int hp = 2;
		if(getHP() + hp > getmaxHP()){
			hp = getmaxHP() - getHP();
		}
        setHP(getHP()+hp);
		if(getSpecial() > 1){
			setBuffedD(2);
			setSpecial(getSpecial() - 2);
			return this + " locks in, boosts their damage by 1.3x for the next 2 rounds, and restores "+hp+" HP"; //should we make another field for "buffed" in adventurer or just this one
		}else{
			return this + " doesn't have enough " + getSpecialName() + " to buff, instead just restore " + hp + " HP";
		}
	  }

}
