//added the simulation method, which follows this blackjack chart
//https://blackjack-strategy.co/blackjack-strategy-chart/blackjack-strategy-card-8-decks-pays-3-to-2-stand-17/
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
	private boolean noBust = false;
	private int numGames = 0;
	private boolean manualPlaying = true;
	private double mostAmountCoins = Integer.MIN_VALUE;
	public BlackJack(double coins) { 
		this.coins = coins;
	}
	private int firstCard() {
		String i = player.get(0);
			if(i.equals("Q") || i.equals("K") || i.equals("J")) 
				return 10;
			else if(i.equals("A"))
				return 11;
			else 
				return Integer.parseInt(i);
	}
	private boolean isSoft(ArrayList<String> holder) {
        int num = 0;
        for(String i: holder) {
            if(i.equals("Q") || i.equals("K") || i.equals("J")) 
                num += 10;
            else if(i.equals("A"))
                num += 1;
            else 
                num += Integer.parseInt(i);
        }
        return holder.indexOf("A") != -1 && num + 10 <= 21;
	}
    private boolean isSplitable() {
    	return player.size() == 2 && player.get(0).equals(player.get(1));
    }
	private String cardLogic(int hardHit, int hardDouble, int hardHit2, int softHit, int softDouble, int spSplit, int spHit,  int spDouble, int spHit2, int spSplit2, int spStand, int spSplit3) {
		if(isSplitable() && firstCard() <= spSplit && spSplit != 0) {
			System.out.println("SPLIT SPLIT");
			return "sp";
		}
		if(isSplitable() && firstCard() <= spHit && spHit != 0 ) {
			System.out.println("SPLIT HIT");
			return "h";
		}
		if(isSplitable() && firstCard() <= spDouble && spDouble != 0 ) {
			System.out.println("SPLIT DOUBLE");
			return "d";
		}
		if(isSplitable() && firstCard() <= spHit2 && spHit2 != 0 ) {
			System.out.println("SPLIT HIT2");
			return "h";
		}
		if(isSplitable() && firstCard() <= spSplit2 && spSplit2 != 0) {
			System.out.println("SPLIT SPLIT2");
			return "sp";
		}
		if(isSplitable() && firstCard() <= spStand && spStand != 0) {
			System.out.println("SPLIT STAND");
			return "s";
		}
		if(isSplitable() && firstCard() <= spSplit3 && spSplit3 != 0) {
			System.out.println("SPLIT SPLIT3");
			return "sp";
		}
		
		
      if (checkCardValue(player) <= hardHit && isSoft(player) == false && hardHit != 0) {
    	System.out.println("HARD HIT");
        return "h";
      } 
      if (checkCardValue(player) <= hardDouble && isSoft(player) == false && hardDouble != 0) {
    	 System.out.println("HDOUBLED");
        return "d";
      } 
      if (checkCardValue(player) <= hardHit2 && isSoft(player) == false && hardHit2 != 0) {
	    System.out.println("HARD HIT2");
		return "h";
      } 
      if(isSoft(player) == false){
    	System.out.println("HARD STAND");
    	return "s";
      }
      
      if (checkCardValue(player) <= softHit && isSoft(player) == true && softHit != 0) {
    	System.out.println("SOFT HIT");
        return "h";
      } 
      if (checkCardValue(player) <= softDouble && isSoft(player) == true && softDouble != 0) {
    	 System.out.println("SOFT DOUBLED");
        return "d";
      }
      if (isSoft(player) == true) {
	    System.out.println("SOFT STAND");
		return "s";
      }
      	System.out.println("something went horribly wrong");
		return null;
	}
	private String getOutcome() {
		String dealerCard = dealer.get(0);
		if(dealerCard.equals("2"))
			return cardLogic(9, 11, 12, 17, 0, 3, 4, 5, 0, 9, 10, 11);
		if(dealerCard.equals("3"))
			return cardLogic(8, 11, 12, 16, 18, 3, 4, 5, 0, 9, 10, 11);
		if(dealerCard.equals("4"))
			return cardLogic(8, 11, 0, 14, 18, 3, 4, 5, 0, 9, 10, 11);
		if(dealerCard.equals("5"))
			return cardLogic(8, 11, 0, 0, 18, 4, 0, 5, 0, 9, 10, 11);
		if(dealerCard.equals("6"))
			return cardLogic(8, 11, 0, 0, 18, 4, 0, 5, 0, 9, 10, 11);
		if(dealerCard.equals("7"))
			return cardLogic(9, 11, 16, 17, 0, 3, 4, 5, 6, 8, 10, 11);
		if(dealerCard.equals("8"))
			return cardLogic(9, 11, 16, 17, 0, 0, 4, 5, 7, 9, 10, 11);
		if(dealerCard.equals("9"))
			return cardLogic(9, 11, 16, 18, 0, 0, 4, 5, 7, 9, 10, 11);
		if(dealerCard.equals("10") || dealerCard.equals("J") || dealerCard.equals("K") || dealerCard.equals("Q"))
			return cardLogic(10, 11, 16, 18, 0, 0, 7, 0, 0, 8, 10, 11);
		if(dealerCard.equals("A"))
			return cardLogic(16, 0, 0, 18, 0, 0, 7, 0, 0, 8, 10, 11);
		return "shit";
	}
	
	//above are methods in order to figure out the best choice for the hand you have. Below are the blackjack game coding.
	
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
			noBust = true;
			return bet * 1.5;
		} 
		if(isBlackjack(dealer) && !isBlackjack(player)) {
			coins -= bet;
			noBust = true;
			return -bet;
		} 
		if(checkCardValue(player) > 21) {
			coins -= bet;
			return -bet;
		} 
		if(checkCardValue(dealer) > 21) {
			coins += bet;
			noBust = true;
			return bet;
		} 
		if(checkCardValue(player) > checkCardValue(dealer)) {
			coins += bet;
			noBust = true;
			return bet;
		} 
		if(checkCardValue(player) < checkCardValue(dealer)) {
			coins -= bet;
			noBust = true;
			return -bet;
		}
		System.out.println();
		noBust = true;
		return 0;
	}
	private String prompt() {
		return new Scanner(System.in).nextLine();
	}
	private boolean hand(String card, int bet) {
		boolean stop = true;
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
		while(stop) {
			System.out.println("You wanna hit, stand, split, or double down?(h/s/sp/d)");
			if(manualPlaying)
				option = prompt();
			else
				option = getOutcome();
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
		if(warning) 
			System.out.println("Warning: Choosing an invalid option will just lead to the 'stand' option.");
		warning = false;
		noBust = false;
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
		if(noBust)
			viewCards("dealer");
		int hands = 1;
		for(double result: resultList) {
			if(result < 0)
				System.out.println("For hand " + hands + ", you lost " + Math.abs(result) + " coins");
			else
				System.out.println("For hand " + hands + ", you won " + Math.abs(result) + " coins");
			hands++;
		}
		if(coins > mostAmountCoins)
			mostAmountCoins = coins;
		numGames++;
		System.out.println("You now have " + coins + " coins");
		System.out.println("Max amount of coins : " + mostAmountCoins);
		System.out.println("You have played this many games: " + numGames);
		System.out.println("You want to try again? (y/n)");
		if(manualPlaying)
			if(!prompt().equals("n"))
				game(bet);
	}
	public void simulation(int times, int bet) {
		manualPlaying = false;
		for(int i = 0; i < times; i++){
			game(bet);
		}
		manualPlaying = true;
	}
}
