import java.util.ArrayList;
import java.util.Scanner;
public class BlackJack {
	private double coins;
	private String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private ArrayList<String> dealer = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	private ArrayList<Double> resultList = new ArrayList<Double>();
	private int hasBusted;
	private int numHands;
	private boolean hasBusted = false;
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
			hasBusted = true;
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
		String option = "";//variable for prompt
		player.clear();//crucial in the case of a split, you have to clear the player's cards, and the outcome of the previous hand is stored in resultList
		
		if(card.equals("random-notsplit"))
			drawCard(player);
		else
			player.add(card);
		drawCard(player);//this is where the cards for the player's hand is assigned randomly or by a previous split. 
		
		System.out.println("Hand " + numHands);
		viewCards("player");
		System.out.println("Dealer's cards: " + dealer.get(0) + ", ?");//simply just text
		
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
		}//What happenes when the player chooses each choice, with split, the hand method simply returns true, and 1 more hand is added, and also terminating the loop when the player busts
		
		if(option.equals("d")) 
			resultList.add(handOutcome(bet*2));
		else
			resultList.add(handOutcome(bet));//special case for double down
		
		numHands++;//this will only matter with the split, if there is no split then the variable goes to two hands, but is never mentioned so oh well. 
		System.out.println();
		return false;//signals to decrease handsLeft by 1. 
	}
	public void game(int bet) {
		hasBusted = false;//notes weather the player has busted or not. The default case is that they have not busted, until they do.
		int handsLeft = 1;//the amount of hands left
		numHands = 1;//simply just for system.out.print stuff no actual calculations use this variable. 
		dealer.clear();//resets everything for when the play wants to start a new game. 
		resultList.clear();//same
		
		while(checkCardValue(dealer) < 17)
			drawCard(dealer);//the dealers final hand is already determined at the beginning, just not shown to the player; only the first card
		String card = "random-notsplit";//the default case. It signals to the game method to draw a random card into the player's hand, and not the card that the player would have split
		while(handsLeft != 0) {
			if(hand(card, bet)) {
				card = player.get(0);//getting to this point would mean the player had successfully split, so you make it so the card variable signals to the game method that it needs to have a predetermined card. 
				handsLeft++;//this makes the amount of hands increase by 1, so now the player would have 2 hands instead of one hand due to splitting.
			}
			else
				handsLeft--;//once the hand did not split, this decreases the amount of hands the player has left by 1. 
		}
		if(!hasBusted)
			viewCards("dealer");//views the dealers cards if and only if the player has not busted. If the player has busted then the dealers cards will not be revealed. 
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
