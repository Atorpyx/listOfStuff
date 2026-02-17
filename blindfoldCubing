
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	public static String prompt() {
		return new Scanner(System.in).nextLine();
	}
	public static void letters(int num, boolean parity) {
		ArrayList<String> Fulllist = new ArrayList<>();
    	Random random = new Random();

    	String letters = "ABCDEFGHIJKLNMOPQRSTUVWX"; 
    	String list;
        char drawnLetter;
        char drawnLetter2;
        int runs = num + random.nextInt(2); 
        for (int i = 0; i < runs; i++) {
        drawnLetter = letters.charAt(random.nextInt(letters.length()));
        drawnLetter2 = letters.charAt(random.nextInt(letters.length()));
        while(drawnLetter2 == drawnLetter)
        	drawnLetter2 = letters.charAt(random.nextInt(letters.length()));
        list = ""+drawnLetter+drawnLetter2;
        System.out.println(list);
        Fulllist.add(list);
        prompt();
        }
        if(parity) {
        	drawnLetter = letters.charAt(random.nextInt(letters.length()));
            list = ""+drawnLetter;
            System.out.println(list);
            Fulllist.add(list);
            prompt();
        }
        System.out.println("done with sequence");
        prompt();
        System.out.println("Full list:");
        for (String word: Fulllist) {
        	System.out.print(word + " ");
        
        }
	}
    public static void main(String[] args) {
    	boolean parity = false;
    	while(prompt() != "stop") {
    	if(Math.random()>0.5)
    		parity = true;
    	letters(5, parity);
    	System.out.println();
    	System.out.println();
    	letters(3, parity);
    	System.out.println();
    	System.out.println("end");
    	}
        
    }
}
