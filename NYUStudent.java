public class NYUStudent extends Adventurer{
  private int money, moneyMax;

  public NYUStudent(String name, int hp) {
    super(name, hp);
    setmaxHP(30);

    moneyMax = 10;
    money = moneyMax / 2;

  }

  public NYUStudent(String name) {
    this(name, (int)(Math.random()*3) + 25);
  }

  public NYUStudent() {
    this("Jay Ma");
  }

  public String getSpecialName(){
    return "money";
  }

  public int getSpecial(){
    return money;
  }

  public void setSpecial(int n){
    money = n;
  }

  public int getSpecialMax(){
    return moneyMax;
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
    if(this.getSpecial() > 1){
      int hp = 3;
      if(other.getHP() + hp > other.getMaxHP()){
        hp = other.getMaxHP() - other.getHP();
      }
      other.setHP(other.getMaxHP()
      other.setSpecial(other.getSecial() - 2));
    return this " generously shares their money with everyone, boosting their special by  "+ other.restoreSpecial(1) +" and HP by "
    + hp;
    }else{
      return this + " needs at least 2 " + this.getSpecialName() + " to use ally support";
    } //+ other.restoreSpecial(5)+" "+other.getSpecialName();
  }

  /*if  at least 1 special and special less than 3 restores 4 hp to self.*/
  public String support(){
    if(getSpecial() < 3 && getSpecial() > 0){
      int hp = 4;
      if(getHP() + hp > getMaxHP()){
        hp = getMaxHP() - getHP();
      }
      setHP(getHP()+hp);
      setSpecial(getSpecial() - 1);
      return this+" receives their weekly deposit from dad, restoring "+hp+" HP";
    }else if(getSpecial() > 3){
      return this + " can only self support if " + getSpecialName() + " is less than 3"
    }else{
      return this + " doesn't have enough " + getSpecialName();
    }

  }


}
