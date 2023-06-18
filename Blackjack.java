//Blackjack program with hit, double down, and money system in only 93 lines of code mostly following what's in AP Java subset with exceptions of the Scanner class for input
//I could have cut lines of code with still maintaining function but I figured it would take away from the readability
//AP CSA Final project- after 3 weeks of bug finding, brainstorming, and typing, here it is. You'll never win muahahahaha
import java.util.ArrayList;
import java.util.Scanner;
public class BlackJack {
	private int coins;
	private String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private ArrayList<String> dealer = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	public BlackJack(int coins) { 
		this.coins = coins;
	}
	private void viewCards(String num) {
		if(num == "player") {
			System.out.print("Player's cards: ");
			for(String str: player)
			    System.out.print(str + ",");
		} 
		if(num == "dealer") {
			System.out.print("Dealer's cards: ");
			for(String str: dealer)
			    System.out.print(str + ",");
		}
		System.out.println();
	}
	private void drawCard(ArrayList<String> holder) {
		holder.add(cards[(int) (13*Math.random())]);
	}
	private int checkCards(ArrayList<String> holder) {
		int num = 0;
		for(String i: holder) {
			if(i.equals("Q") || i.equals("K") || i.equals("J")) 
				num += 10;
			else if(i.equals("A"))
				num++;
			else 
				num += Integer.parseInt(i);
		}
		if(holder.indexOf("A") != -1 && num + 10 <= 21)
			num += 10;
		return num;
	}
	private void determiner(int bet) {
		if(checkCards(player) > 21) {
			coins -= bet;
			System.out.println("You Busted! You lost " + bet + " coins. You now have " + coins + " coins.");
		} else if(checkCards(dealer) > 21) {
			coins += bet;
			System.out.println("Dealer Busted! You won " + bet + " coins. You now have " + coins + " coins.");
		} else if(checkCards(player) > checkCards(dealer)) {
			coins += bet;
			System.out.println("You won " + bet + " coins. You now have " + coins + " coins.");
		} else if(checkCards(player) < checkCards(dealer)) {
			coins -= bet;
			System.out.println("You lost " + bet + " coins. You now have " + coins + " coins.");
		} else
			System.out.println("You drew :/");
	}
	public String prompt() {
		return new Scanner(System.in).nextLine();
	}
	public void game(int bet) {
		dealer.clear();
		player.clear();
		boolean stop = true;
		String option = "";
		drawCard(dealer);
		drawCard(dealer);
		drawCard(player);
		drawCard(player);
		viewCards("player");
		System.out.println("Dealer's cards: " + dealer.get(0) + ", ?");
		while(stop) {
			System.out.println("You wanna hit, stand, or double down?(h/s/d)");
			option = prompt();
			if(option.equals("h") || option.equals("d")) {
				drawCard(player);
				viewCards("player");
			}
			if(!option.equals("h") || checkCards(player) >= 21)
				stop = false;
		}
		while(checkCards(dealer) < 17)
			drawCard(dealer);
		if(checkCards(player) <= 21)
			viewCards("dealer");
		if(option.equals("d")) 
			determiner(bet*2);
		else
			determiner(bet);
		System.out.println("You want to try again? (y/n)");
		if(!prompt().equals("n"))
			game(bet);
	}
}
