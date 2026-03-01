package program;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	public static String prompt() {
		return new Scanner(System.in).nextLine();
	}
	public static void letters(int edge, int corners) {
		ArrayList<String> Edges = new ArrayList<>();
		ArrayList<String> Corn = new ArrayList<>();
    	Random random = new Random();

    	String letters = "ABCDEFGHIJKLNMOPQRSTUVWX"; 
    	String list;
        char drawnLetter;
        char drawnLetter2;
        int runs = edge + random.nextInt(2); 
        int corner = corners + random.nextInt(2);
        boolean parity = false;
        if(Math.random()>0.5)
    		parity = true;
        for (int i = 0; i < runs; i++) {
        drawnLetter = letters.charAt(random.nextInt(letters.length()));
        drawnLetter2 = letters.charAt(random.nextInt(letters.length()));
        while(drawnLetter2 == drawnLetter)
        	drawnLetter2 = letters.charAt(random.nextInt(letters.length()));
        list = ""+drawnLetter+drawnLetter2;
        System.out.println(list);
        Corn.add(list);
        prompt();
        }
        if(parity) {
        	drawnLetter = letters.charAt(random.nextInt(letters.length()));
            list = ""+drawnLetter;
            System.out.println(list);
            Corn.add(list);
            prompt();
        }
        System.out.println("done with Corners");
        for (int i = 0; i < corner; i++) {
            drawnLetter = letters.charAt(random.nextInt(letters.length()));
            drawnLetter2 = letters.charAt(random.nextInt(letters.length()));
            while(drawnLetter2 == drawnLetter)
            	drawnLetter2 = letters.charAt(random.nextInt(letters.length()));
            list = ""+drawnLetter+drawnLetter2;
            System.out.println(list);
            Edges.add(list);
            prompt();
            }
            if(parity) {
            	drawnLetter = letters.charAt(random.nextInt(letters.length()));
                list = ""+drawnLetter;
                System.out.println(list);
                Edges.add(list);
                prompt();
            }
        System.out.println("done with sequence");
        prompt();
        
        
        System.out.println("Edges:");
        for (String word: Edges) {
        	System.out.print(word + " ");
        
        }
        System.out.println();
        System.out.println("Corners:");
        for (String word: Corn) {
        	System.out.print(word + " ");
        
        }
        System.out.println();
        
	}
    public static void main(String[] args) {
    	boolean parity = false;
    	while(!prompt().equals("stop")) {
    		parity = false;
    	if(Math.random()>0.5)
    		parity = true;
    	letters(3, 5);
    	}
        
    }
}
