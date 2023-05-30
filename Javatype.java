import java.time.LocalTime;
import java.util.Scanner;
/*
Recreation of monkeytype on java using scanner class and time class
hello miodec
*/
public class Tester {
	public static void startTest() {
		String[] wordList = {"the","be","of","and","a",
				"to","in","he","have","it","that","for","they","with","as","not","on","she",
				"at","by","this","we","you","do","but","from","or","which","one","would",
				"all","will","there","say","who","make","when","can","more","if","no","man",
				"out","other","so","what","time","up","go","about","than","into","could","state",
				"only","new","year","some","take","come","these","know","see","use","get","like",
				"then","first","any","work","now","may","such","give","over","think","most","even",
				"find","day","also","after","way","many","must","look","before","great","back",
				"through","long","where","much","should","well","people","down","own","just",
				"because","good","each","those","feel","seem","how","high","too","place",
				"little","world","very","still","nation","hand","old","life","tell","write",
				"become","here","show","house","both","between","need","mean","call","develop",
				"under","last","right","move","thing","general","school","never","same","another",
				"begin","while","number","part","turn","real","leave","might","want","point","form",
				"off","child","few","small","since","against","ask","late","home","interest","large",
				"person","end","open","public","Kevin Crosset","follow","during","present","without","again",
				"hold","govern","around","possible","head","consider","word","program","problem",
				"however","lead","system","set","order","eye","plan","run","keep","face",
				"fact","group","play","stand","increase","early","course","change","help", "line",};
		String wordTest = "";
		
		System.out.println("Enter the amount of words you want on your test");
		Scanner obj = new Scanner(System.in);
	    int word = obj.nextInt();  
	    
	    for(int i = 0; i < word-1; i++) {
	    	wordTest += wordList[(int) (Math.random() * wordList.length)] + " ";
	    }
	    wordTest += wordList[(int) (Math.random() * wordList.length)];
	    System.out.println("The test will start once you hit enter");
	    System.out.print(wordTest);

	    Scanner obj2 = new Scanner(System.in);
	    String something = obj2.nextLine();
	   
		System.out.println("Go!");
		LocalTime startTime = LocalTime.now();
		Scanner obj3 = new Scanner(System.in);
		String test = obj3.nextLine();
		LocalTime endTime = LocalTime.now();
		
		int hours = Integer.parseInt(endTime.toString().substring(0, 2)) - Integer.parseInt(startTime.toString().substring(0, 2));
		int minutes = Integer.parseInt(endTime.toString().substring(3, 5)) - Integer.parseInt(startTime.toString().substring(3, 5));
		int seconds = Integer.parseInt(endTime.toString().substring(6, 8)) - Integer.parseInt(startTime.toString().substring(6, 8));
		double microseconds = Double.parseDouble("0." + endTime.toString().substring(9, 14)) - Double.parseDouble("0." + startTime.toString().substring(9, 14));
		double total = hours*3600 + minutes*60 + seconds + microseconds;//toString of endTime is "08.45.13.273485920"
		double rawWpm = test.length()/total * 12;//calculates cpm(chars per minute): *12 changes it to wpm
		if(test.equals(wordTest)) 
			System.out.println("Your verified wpm is " + (rawWpm * 10000/10000));
		else
			System.out.println("You mistyped the prompt and therefore can not be given valid speed. Your wpm(with mistakes) is " + (rawWpm * 10000/10000));
		System.out.println("It took you " + total * 10000/10000 + " seconds to type the text");//round off but i fail anyways
		System.out.println("You want to try again? (y/n)");
		
		Scanner obj4 = new Scanner(System.in);
		String mes = obj3.nextLine();
		
		if(mes.equals("y")) {
			startTest();
		}else
			System.out.println("lol you quitter goodbye");
	}
	public static void main(String[] args) {
		startTest();
	}
}
