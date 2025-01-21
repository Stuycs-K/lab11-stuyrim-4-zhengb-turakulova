import java.util.*;

public class Game{  
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    /*String stuff = String.format("%2s", 4+"")+"/"+String.format("%2s", 25+"");
    stuff = Text.colorize(stuff, Text.RED);
    String output = "HP:" + stuff;
    System.out.println("\"" + output + "\"");
    System.out.println(output.length());*/
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    for (int x = 1; x <= WIDTH; x++) {
      String whatDraw;
      if (x==1 || x==WIDTH) whatDraw = "+";
      else whatDraw = "=";

      drawText(whatDraw, 1, x);
      drawText(whatDraw, 20, x);
      drawText(whatDraw, 25, x);
      drawText(whatDraw, HEIGHT, x);
    }

    for (int y = 2; y < HEIGHT; y++) {
      drawText("|", y, 1);
      if (y < 25) drawText("|", y, 40);
      drawText("|", y, WIDTH);
    }

    
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    Text.hideCursor();

    int originalR = row, originalC = col, length = text.length();
    int blank = width * height - text.length();
    if (text.indexOf("\u001b[") != -1) {
      drawText(text, row, col);
      return;
    }

    if (blank < 0) {
      throw new IllegalArgumentException(Text.colorize("\"" + text + "\"" + " with length " + text.length() + " is too long for width: " + width + " and height: " + height, Text.BOLD, Text.RED + Text.BRIGHT, Text.UNDERLINE));
    }
    
    if (text.length() > width) {
      while (text.length() > width) {
        drawText(text.substring(0, width), row, col);
        text = text.substring(width);
        row++;
      }
    }
    drawText(text, row, col);

    row = originalR + (length / width);
    col = originalC + (length % width);

    int endRow = row;
    int endCol = col;

    // System.out.println(row+1);
    // System.out.println(col+1);
    // System.out.println(blank);

    while (blank > 0) {
      // System.out.println("(" + (row) + ", " + (col) + ")");
      drawText(" ", row, col);
      blank--;

      if (blank % width == 0) {
        row++;
        col = originalC;
      }
      else col++;
    }

    // System.out.println(row);
    // System.out.println(col);
    Text.go(endRow, endCol);

  }

    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
  public static Adventurer createRandomAdventurer(){
    double choice = (int) (Math.random()*3);
    
    if (choice == 0) return new CodeWarrior();
    if (choice == 1) return new NYUStudent();
    else return new CSIntern();
  }

  public static Adventurer createRandomAdventurer(String name) {
    Random rand = new Random();
    int choice = rand.nextInt(0, 3);
    if (choice == 0) return new CodeWarrior(name);
    if (choice == 1) return new NYUStudent(name);
    else return new CSIntern(name);
  }

  /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
  *Should include Name HP and Special on 3 separate lines.
  *Note there is one blank row reserved for your use if you choose.
  *Format:
  *Bob          Amy        Jun
  *HP: 10       HP: 15     HP:19
  *Caffeine: 20 Mana: 10   Snark: 1
  * ***THIS ROW INTENTIONALLY LEFT BLANK***
  */
  public static void drawParty(ArrayList<Adventurer> party,int startRow, int startCol){
    int width = 12;//
    // the health was getting cut before size < 3

    TextBox(startRow, startCol, 38, 4, " "); // what if we move this inside and erase only width and 4
    for (int a = 0; a < party.size(); a++) {
      Adventurer current = party.get(a);
      TextBox(startRow, startCol, width, 1, current.toString());
      TextBox(startRow+1, startCol, width, 1, "HP:" + colorByPercent(current.getHP(), current.getmaxHP()));
      TextBox(startRow+2, startCol, width, 1, current.getSpecialName() + ": " + current.getSpecial());
      
      // String status = "";
    
      // if (current.isDead()) {
      //   status += Text.colorize("Dead", Text.RED, Text.BOLD);
      // }
      // else {
      //   if (current.isParalyzed()) {
      //     status += Text.colorize("para: " + current.getParalyzedD(), Text.YELLOW);
      //   }
      //   if (current.isBuffed()) {
      //     status += Text.colorize(", buff: " + current.getBuffedD(), Text.BLUE);
      //   }
      // }
     
      //TextBox(startRow+3, startCol, 7, 1, status);
     // Text.go(31, 2);
      //System.out.println("\"" + status + "\"" + status.length());

      startCol += (width+1);
    }
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    double hpFraction = 100.0 * (hp / (double) maxHP);
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    if(hpFraction < 25.0){
      return Text.colorize(output, Text.RED);
    }else if( hpFraction < 75.0){
      return Text.colorize(output, Text.YELLOW);
    }else{
      return output;
    }
  }

  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){
    drawBackground();
    // drawBackground()

    drawParty(party, 21, 2);

    //draw player party
    Text.hideCursor();
    drawParty(enemies, 21, 41);

    //draw enemy party

    // Text.go(27, 2);
    

  }

  public static String userInput(Scanner in){
    Text.showCursor();
      //Move cursor to prompt location
      Text.go(27, 2);

      //show cursor
      String input = in.nextLine();

      //clear the text that was written
    return input;
  }
	
	  //checl given list of adventurers for at least one surviving member hp>0
  public static boolean checkLiving(ArrayList<Adventurer> list){
	  boolean live = false;
	  for(int i = 0; i < list.size(); i++){
		  if(list.get(i).getHP() > 0){
			  live = true;
		  }
	  }
	  return live;
  }

  public static void drawResult(ArrayList<Adventurer> party, boolean win) {
    String WIN = "__   _____  _   _  __        _____ _   _ _ \n\\ \\ / / _ \\| | | | \\ \\      / /_ _| \\ | | |\n \\ V / | | | | | |  \\ \\ /\\ / / | ||  \\| | |\n  | || |_| | |_| |   \\ V  V /  | || |\\  |_|\n  |_| \\___/ \\___/     \\_/\\_/  |___|_| \\_(_)";
    String LOSE = "__   _____  _   _   _     ___  ____  _____ _ \n\\ \\ / / _ \\| | | | | |   / _ \\/ ___|| ____| |\n \\ V / | | | | | | | |  | | | \\___ \\|  _| | |\n  | || |_| | |_| | | |__| |_| |___) | |___|_|\n  |_| \\___/ \\___/  |_____\\___/|____/|_____(_)";
    TextBox(1, 1, 80, 30, " ");

    Text.go(1, 1);
    if (win) System.out.println(WIN);
    else System.out.println(LOSE);

    System.exit(0);
  }


  public static boolean checkDead(ArrayList<Adventurer> list){
	  boolean dead = true; //accidentally changed this before mb it should be fixed
	  for(int i = 0; i < list.size(); i++){
		  if(!list.get(i).isDead()){
			  dead = false;
		  }
	  }
	  return dead;
  }

  //sets as dead if dead
  public static void setIfDead(ArrayList<Adventurer> list){
    for(int i = 0; i < list.size(); i++){
      if(list.get(i).getHP() <= 0){
        list.get(i).setDead();
      }
    }
  }

  public static ArrayList<Adventurer> removeDead(ArrayList<Adventurer> adventurers) {
    for (int i = 0; i < adventurers.size(); i++) {
      if (adventurers.get(i).isDead()) {
        adventurers.remove(i);
        i--;
      }
    }//remove so i -- when remove runs? wait i remember something abt this k said it in class one time that was either really good or just meh
    return adventurers;
  } // why tf did the health go from 0 to max when die alr what now why is stillll duppepeeeee
  // ok we try
 
  public static void quit(){
    Text.reset();
    TextBox(1, 1, 80, 30, " ");
    System.out.print("Thanks for Playing!");
    Text.showCursor();
    Text.go(32,1);
    System.exit(0);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    
    int num = (int)(Math.random()*3) + 1; // 1 to 3

    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    ArrayList<Adventurer> enemies = new ArrayList<Adventurer>();

    

    if(num == 1){
      enemies.add(new Boss());
      for (int i = 0; i < 3; i++) {
        party.add(createRandomAdventurer());
      }
    }
    else{
      for (int i = 0; i < num; i++) {
        party.add(createRandomAdventurer());
      }
      for (int i = 0; i < num; i++) {
        enemies.add(createRandomAdventurer());
      }
    } 
    
    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int enemyMoves = 0;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
	
    //Draw the window border


    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "AEnter command for "+party.get(whichPlayer)+": attack/special/quit";
    TextBox(26, 2, 78, 2, preprompt);
    Text.go(27, 2);// have u tested party is size 0 for some reason

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);
      // check if t is less than the number of party members to not check input if its !partyTurn  why !partyTurn oh ic
      if (input.equals("q") || input.equals("quit")) { 
        quit();
      }

      //is the format good on your end when java ing  u have to expand the terminal

      
      //example debug statment
      
      //display event based on last turn's nvm ill just add a few points to boss
      if(partyTurn){
        // error checking order:
        // checks if length is 2, so in format: action, target
        // checks if target is valid for party size
        // checks if target is alive
        
        int length = input.split(" ").length;
        int target = 0;

        while (length != 2) {
          TextBox(27, 2, 78, 2, "aincorrect input, please enter in format: action <target index 0-2>");
          input = userInput(in); 
          length = input.split(" ").length;
        }

        target = Integer.parseInt(input.substring(input.length() - 1));
        
        while(target >= party.size()){//how about a  if statement outside the nested while for length
          TextBox(27, 2, 78, 2, "bincorrect input, please enter in format: action <target index 0-2>");
          input = userInput(in); // wait no
          target = Integer.parseInt(input.substring(input.length() - 1));
        }

        whichPlayer %= party.size();
        Adventurer current = party.get(whichPlayer);
        String message = "";

        //Process user input for the last Adventurer:
        if(input.startsWith("attack") || input.startsWith("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          while (enemies.get(target).isDead()) {
            TextBox(26, 2, 78, 2, "Please enter a new target as " + enemies.get(target)  + " is dead");
            input = userInput(in); 
            target = Integer.parseInt(input.substring(input.length() - 1));
          }
          message = current.attack(enemies.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          while (enemies.get(target).isDead()) {
            TextBox(26, 2, 78, 2, "Please enter a new target as " + enemies.get(target)  + " is dead");
            input = userInput(in); 
            target = Integer.parseInt(input.substring(input.length() - 1));
          }
          message = current.specialAttack(enemies.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          while (party.get(target).isDead()) {
            TextBox(26, 2, 78, 2, "Please enter a new target as " + party.get(target)  + " is dead");
            input = userInput(in); 
            target = Integer.parseInt(input.substring(input.length() - 1));
          }
          if (party.get(target) == current) message = current.support();
          else message = current.support(party.get(target));
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        Text.hideCursor();


        TextBox(2, 2, 38, 5, message);

        //You should decide when you want to re-ask for user input
        //If no errors:

        whichPlayer++;


        if (enemies.size() == 1) {
          // will only let the party move 3 times if fighting a boss
          if(whichPlayer < party.size()){
            // TextBox(31,2,100,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );
            String prompt = "BEnter command for "+party.get(whichPlayer)+": attack/special/quit";
            TextBox(26, 2, 78, 2, prompt);
            // Text.go(27, 2);
          }
          else{
            String prompt = "press enter to see monster's turn";
            TextBox(26, 2, 78, 2, prompt);
            partyTurn = false;
            whichOpponent = 0;
          }
        }
        else{
          String prompt = "press enter to see monster's turn";
          TextBox(26, 2, 78, 2, prompt);
          partyTurn = false;
          whichOpponent %= enemies.size();
        }
      }
      else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        whichOpponent %= enemies.size();
        Adventurer currentOpp = enemies.get(whichOpponent);


        int attack = (int) (Math.random() * 4);
        int which = (int) (Math.random() * party.size());
        
        
        String message;
        if(attack == 0){
          message = currentOpp.attack(party.get(which));
        }else if(attack == 1){ // try running stuff
          message = currentOpp.specialAttack(party.get(which)); // ???
        }else if(attack == 2){ // we could add it to the initial if partyTurn if  isDead is who is dead
          message = currentOpp.support();
        }else{
          int rand = (int) (Math.random()*enemies.size());
          Adventurer supportWho = enemies.get(rand);
          while (supportWho.isDead()) {
            rand = (int) (Math.random()*enemies.size());
            supportWho = enemies.get(rand);
          }
          message = currentOpp.support(supportWho);
        }
        TextBox(2, 41, 39, 5, message);
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        TextBox(26, 2, 78, 2, prompt);

        enemyMoves++;
        whichOpponent++;

      }//end of one enemy.

      if(!partyTurn && enemyMoves == 1){
        //ends enemy's turn once 1 enemy has moved
        enemyMoves = 0;
        whichPlayer %= party.size();
        turn++;
        partyTurn=true;
        //display this prompt before player's turn

        String prompt = "CEnter command for "+party.get(whichPlayer)+": attack/special/quit";
        TextBox(26, 2, 78, 2, prompt);
      }

      //display the updated screen after input has been processed.
      setIfDead(party);
      setIfDead(enemies);
      

      if(checkDead(party)) drawResult(party, false);
      
      if(checkDead(enemies)) drawResult(party, true);

      enemies = removeDead(enemies);
      party = removeDead(party);
      TextBox(31, 1, 30, 1, " ");
      Text.go(31,1);


      for (Adventurer a : party) {
        System.out.print(a + ", ");
      }
      System.out.println();
      for (Adventurer a : enemies) {
        System.out.print(a + ", ");
      }
      TextBox(32, 1, 30, 1, party.size() +" ; "+ enemies.size());
 // that didn't count cuz only 1 enemy
      drawScreen(party, enemies);
  
    
      
	 
    } //end of main game loop
    quit();
  }//the parse int for num should only run if theres a spce in input since q doesnt have a #
} // okay imma run
