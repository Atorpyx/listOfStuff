import java.util.ArrayList;
import java.util.Scanner;
public class Blackjack {
	private int coins;
	private String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private ArrayList<String> dealer = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	public Blackjack(int coins) { 
		this.coins = coins;
	}
	private void viewCards(int num) {
		if(num == 0)
			System.out.print("Dealer's cards: " + dealer.get(0) + ", ?");
		if(num == 1) {
			System.out.print("Player's cards: ");
			for(String str: player)
			    System.out.print(str + ",");
		}
		if(num == 2) {
			System.out.print("Dealer's cards: ");
			for(String str: dealer)
			    System.out.print(str + ",");
		}
		System.out.println();
	}
	private void drawCard(int i) {
		if(i == 0)
			dealer.add(cards[(int) (13*Math.random())]);
		if(i == 1)
			player.add(cards[(int) (13*Math.random())]);
	}
	private int checkCards(int number) {
		int num = 0;
		boolean hasAce = true;
		ArrayList<String> holder = null;
		if(number == 0)
			holder = player;
		if(number == 1)
			holder = dealer;
		for(String i: holder) {
			if(i.equals("Q") || i.equals("K") || i.equals("J")) 
				num += 10;
			else if(i.equals("A")) {
			    hasAce = true;
				num++;
			} else 
				num += Integer.parseInt(i);
		}
		if(hasAce && num + 10 <= 21)
			num += 10;
		return num;
	}
	private String prompt(int num) {
		if(num == 0)
			System.out.println("You wanna hit, stand, or double down?(h/s/d)");
		else
			System.out.println("You want to try again? (y/n)");
		Scanner obj = new Scanner(System.in);
		return obj.nextLine();
	}
	private void determiner(int bet) {
		if(checkCards(0) > 21) {
			coins -= bet;
			System.out.println("You Busted! You lost " + bet + " coins. You now have " + coins + " coins.");
		} else if(checkCards(1) > 21) {
			coins += bet;
			System.out.println("Dealer Busted! You won " + bet + " coins. You now have " + coins + " coins.");
		} else if(checkCards(0) > checkCards(1)) {
			coins += bet;
			System.out.println("You won " + bet + " coins. You now have " + coins + " coins.");
		} else if(checkCards(0) < checkCards(1)) {
			coins -= bet;
			System.out.println("You lost " + bet + " coins. You now have " + coins + " coins.");
		} else
			System.out.println("You drew :/");
	}
	public void game(int bet) {
		dealer.clear();
		player.clear();
		boolean stop = true;
		String lastResult = "";
		drawCard(0);
		drawCard(0);
		drawCard(1);
		drawCard(1);
		viewCards(1);
		viewCards(0);
		while(stop) {
			String option = prompt(0);
			if(option.equals("h")) {
				drawCard(1);
				viewCards(1);
			} else if(option.equals("d")) {
				drawCard(1);
				viewCards(1);
				stop = false;
			} else 
				stop = false;
			if(checkCards(0) >= 21)
				stop = false;
			lastResult = option;
		}
		while(checkCards(1) < 17)
			drawCard(0);
		if(checkCards(0) > 21)
			viewCards(0);
		else
			viewCards(2);
		if(lastResult.equals("d")) 
			determiner(bet*2);
		else
			determiner(bet);
		if(prompt(1).equals("y"))
			game(bet);
		else
			System.out.println("Come back next time!!");
	}
}