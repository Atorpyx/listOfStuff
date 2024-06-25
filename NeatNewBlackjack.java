import java.util.ArrayList;
import java.util.Scanner;
public class BlackJack {
	private double coins;
	private String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private ArrayList<String> dealer = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	private ArrayList<Double> resultList = new ArrayList<Double>();
	private int numHands;
	private boolean warning = true;
	private boolean busted = false;
	public BlackJack(double coins) { 
		this.coins = coins;
	}
	private void viewCards(String name) {
		if(name.equals("player")) {
			System.out.print("Player's cards: ");
			for(String str: player)
			    System.out.print(str + ",");
		} 
		if(name.equals("dealer")) {
			System.out.print("Dealer's cards: ");
			for(String str: dealer)
			    System.out.print(str + ",");
		}
		System.out.println();
	}
	private void drawCard(ArrayList<String> holder) {
		holder.add(cards[(int) (13*Math.random())]);
	}
	private boolean isBlackjack(ArrayList<String> name) {
		return name.size() == 2 && name.indexOf("A") != -1 && (name.indexOf("10") != -1 || name.indexOf("J") != -1 || name.indexOf("Q") != -1 || name.indexOf("K") != -1);
	}
	private int checkCardValue(ArrayList<String> holder) {
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
	private double handOutcome(int bet) {
		if(isBlackjack(player) && !isBlackjack(dealer)) {
			coins += bet * 1.5;
			return bet * 1.5;
		} else if(isBlackjack(dealer) && !isBlackjack(player)) {
			coins -= bet;
			return -bet;
		} else if(checkCardValue(player) > 21) {
			coins -= bet;
			busted = true;
			return -bet;
		} else if(checkCardValue(dealer) > 21) {
			coins += bet;
			return bet;
		} else if(checkCardValue(player) > checkCardValue(dealer)) {
			coins += bet;
			return bet;
		} else if(checkCardValue(player) < checkCardValue(dealer)) {
			coins -= bet;
			return -bet;
		}
		System.out.println();
		return 0;
	}
	private String prompt() {
		return new Scanner(System.in).nextLine();
	}
	private boolean hand(String card, int bet) {
		String option = "";
		player.clear();
		
		if(!card.equals("r"))
			player.add(card);
		else
			drawCard(player);
		drawCard(player);
		
		System.out.println("Hand " + numHands);
		viewCards("player");
		System.out.println("Dealer's cards: " + dealer.get(0) + ", ?");
		
		boolean stop = true;
		while(stop) {
			System.out.println("You wanna hit, stand, split, or double down?(h/s/sp/d)");
			option = prompt();
			if(option.equals("sp") && player.size() == 2 && player.get(0).equals(player.get(1)))
				return true;
			if(option.equals("h") || option.equals("d")) {
				drawCard(player);
				viewCards("player");
			}
			if(!option.equals("h") || checkCardValue(player) >= 21)
				stop = false;
		}
		if(option.equals("d")) 
			resultList.add(handOutcome(bet*2));
		else
			resultList.add(handOutcome(bet));
		numHands++;
		System.out.println();
		return false;
	}
	public void game(int bet) {
		busted = false;
		int handsLeft = 1;
		numHands = 1;
		dealer.clear();
		resultList.clear();
		
		while(checkCardValue(dealer) < 17)
			drawCard(dealer);
		String card = "r";
		while(handsLeft != 0) {
			if(hand(card, bet)) {
				card = player.get(0);
				handsLeft++;
			}
			else
				handsLeft--;
		}
		if(!busted)
			viewCards("dealer");
		int hands = 1;
		for(double result: resultList) {
			if(result < 0)
				System.out.println("For hand " + hands + ", you lost " + Math.abs(result) + " coins");
			else
				System.out.println("For hand " + hands + ", you won " + Math.abs(result) + " coins");
			hands++;
		}
		System.out.println("You now have " + coins + " coins");
		System.out.println("You want to try again? (y/n)");
		if(!prompt().equals("n"))
			game(bet);
	}
}
