import java.util.Random;
import java.util.Scanner;

public class PicoFermiNadaGame {
  private int spot1;
  private int spot2;
  private int spot3;

  public PicoFermiNadaGame(){
    Random r = new Random();
    spot1 = r.nextInt(9) + 1;
    spot2 = r.nextInt(10);
    while(spot2 == spot1){
      spot2 = r.nextInt(10);
    }
    spot3 = r.nextInt(10);
    while(spot3 == spot1 || spot3 == spot2){
      spot3 = r.nextInt(10);
    }

    System.out.println(toString());
  }

  public PicoFermiNadaGame(int number){
    if(!isValid(number)){
      System.out.println("Number must be in range 100-999");
      System.exit(0);
    }

    if(number < 10){
      spot1 = 0;
      spot2 = 0;
      spot3 = number;
    } else if(number < 100){
      spot1 = 0;
      spot2 = number / 10;

      spot3 = number % 10;
    } else {
      spot1 = number / 100;
      number %= 100;
      spot2 = number / 10;
      spot3 = number % 10;
    }
  }

  public String generatePFN(int guess){
    // Find the numbers in each spot of the user's guess
    int guess1, guess2, guess3;
    if(guess < 10){
      guess1 = 0;
      guess2 = 0;
      guess3 = guess;
    } else if(guess < 100){
      guess1 = 0;
      guess2 = guess / 10;
      guess3 = guess % 10;
    } else {
      guess1 = guess / 100;
      guess %= 100;
      guess2 = guess / 10;
      guess3 = guess % 10;
    }

    //System.out.println(guess1 + " " + guess2 + " " + guess3);

    // Find how many p's, f's, and n's we have
    int p = 0;
    int f = 0;
    int n = 0;
    if(guess1 == spot1){
      p++;
    } else if(guess1 == spot2){
      f++;
    } else if(guess1 == spot3){
      f++;
    } else {
      n++;
    }

    if(guess2 == spot2){
      p++;
    } else if(guess2 == spot1){
      f++;
    } else if(guess2 == spot3){
      f++;
    } else {
      n++;
    }

    if(guess3 == spot3){
      p++;
    } else if(guess3 == spot1){
      f++;
    } else if(guess3 == spot2){
      f++;
    } else {
      n++;
    }

    // System.out.println("P: " + p + " F: " + f + " N: " + n);

    // Build a string to return to the user to see how well they did with their guess
    String pfn = "";
    for(int i = 0; i < p; i++){
      pfn += "P";
    }
    for(int i = 0; i < f; i++){
      pfn += "F";
    }
    for(int i = 0; i < n; i++){
      pfn += "N";
    }

    return pfn;
  }

  private boolean isValid(int number){
    return number > 0 && number < 1000 ? true : false;
  }

  public void play(){
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to Pico Fermi Nada!!");
    System.out.println("-----------------------------");
    System.out.println();
    boolean win = false;
    while(!win){
      System.out.print("Please input your guess (100-999): ");
      int input = in.nextInt();
      if(input == 0){
        System.exit(1);
      }
      while(!isValid(input)){
        System.out.print("Invalid! Please enter a vaild number (100-999): ");
        input = in.nextInt();
      } 

      String pfn = generatePFN(input);
      if(pfn.equals("PPP")){
        System.out.println("Congratulations! You have won the game :)");
        System.out.println("The correct number was: " + input);
        in.close();
        return;
      } else {
        System.out.println(pfn);
      }
    }
    in.close();
  }

  public String toString(){
    return spot1 + " " + spot2 + " " + spot3;
  }
}

// Pico Fermi Nada
// 1. Create a number in the backend
// 2. Ask user for an input
// 3. Calculate how close that number is to the real number
// 4. Convert that calculation into PFN 
// 5. Display that PFN to the user
// 6. Repeat until the user guesses the number