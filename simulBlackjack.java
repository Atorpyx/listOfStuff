//added the simulation method, which follows this blackjack chart
//https://blackjack-strategy.co/blackjack-strategy-chart/blackjack-strategy-card-8-decks-pays-3-to-2-stand-17/
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
public class BlackJack {
	private double coins;
	private String[] card = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private ArrayList<String> cards = new ArrayList<String>();
	private double[] gameList = {0, 0, 0, 0, 0, 0, 0};
	private double[] doubledList = {0, 0, 0, 0, 0};
	private ArrayList<String> dealer = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	private ArrayList<Double> resultList = new ArrayList<Double>();
	private int numTotalDoubled;
	private int numHands;
	private boolean warning = true;
	private boolean noBust = false;
	private int numGames = 0;
	private int numTotalHands = 0;
	private boolean manualPlaying = true;
	private double mostAmountCoins = Integer.MIN_VALUE;
	private double leastAmountCoins = Integer.MAX_VALUE;
	
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
	public void cards() {
		//for(String i: cards) {
			//System.out.println(i + ", ");
		//}
	}
	///*
	private String cardLogic(int hardHit, int hardDouble, int hardHit2, int softHit, int softDouble, int spSplit, int spHit,  int spDouble, int spHit2, int spSplit2, int spStand, int spSplit3) {
		int cardValue = checkCardValue(player);
		boolean soft = isSoft(player);
		boolean splitable = player.size() == 2 && player.get(0).equals(player.get(1));
		int firstcard = firstCard();
	    if(splitable && spSplit != 0 && firstcard <= spSplit )  
		    return "sp";
	    if(splitable && spHit != 0 && firstcard <= spHit )  
		    return "h";
	    if(splitable && spDouble != 0 && firstcard <= spDouble )  
		    return "d";
	    if(splitable && spHit2 != 0 && firstcard <= spHit2 )  
		    return "h";
  	    if(splitable && spSplit2 != 0 && firstcard <= spSplit2 )  
		    return "sp";
	    if(splitable && spStand != 0 && firstcard <= spStand )  
		    return "s";
	    if(splitable && spSplit3 != 0 && firstcard <= spSplit3 )  
		    return "sp";
	    if (hardHit != 0 && soft == false && cardValue <= hardHit )  
		    return "h";
	    if (hardDouble != 0 && soft == false && cardValue <= hardDouble )  
		    return "d";
	    if ( hardHit2 != 0 && soft == false && cardValue <= hardHit2 )  
		    return "h";
	    if(soft == false) 
		    return "s";
	    if (cardValue <= softHit && soft == true && softHit != 0)  
		    return "h";
	    if (softDouble != 0 && cardValue <= softDouble && soft == true)  
		    return "d";
	    if (soft == true)  
		    return "s";
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
		return "I just put this here because the ide gets mad at me";
	}
	//*/
	/*
	public String cardLogic(int hardHit, int softHit) {
	      if (checkCardValue(player) <= hardHit && isSoft(player) == false) {
	        return "h";
	      } else if (checkCardValue(player) > hardHit && isSoft(player) == false) {
	        return "s";
	      } else if (checkCardValue(player) <= softHit && isSoft(player) == true) {
	        return "h";
	      } else if (checkCardValue(player) > softHit && isSoft(player) == true) {
	        return "s";
	      }
	      System.out.println("Something went horribly wrong");
	      return "Shit";
	    
	}
	public String getOutcome() {
		String dealerCard = dealer.get(0);
		if(dealerCard.equals("2"))
			return cardLogic(12, 17);
		if(dealerCard.equals("3"))
			return cardLogic(12, 17);
		if(dealerCard.equals("4"))
			return cardLogic(11, 17);
		if(dealerCard.equals("5"))
			return cardLogic(11, 17);
		if(dealerCard.equals("6"))
			return cardLogic(11, 17);
		if(dealerCard.equals("7"))
			return cardLogic(16, 17);
		if(dealerCard.equals("8"))
			return cardLogic(16, 17);
		if(dealerCard.equals("9"))
			return cardLogic(16, 18);
		if(dealerCard.equals("10") || dealerCard.equals("J") || dealerCard.equals("K") || dealerCard.equals("Q"))
			return cardLogic(16, 18);
		if(dealerCard.equals("A"))
			return cardLogic(16, 18);
		
		return "shit";
			
	}
	*/
	//above are methods in order to figure out the best choice for the hand you have. Below are the blackjack game coding.
	public void shuffle() {
		
	    cards.clear();
	    for(int j = 0; j < 32; j++)
	        for(int i = 0; i < card.length; i++)
	            cards.add(card[i]);
	    
	}
	public void drawCard(ArrayList<String> holder) {
		//holder.add(cards.remove((int) (cards.size()*Math.random())));
		holder.add(card[(int) (13*Math.random())]);
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
	private double handOutcome(int bet, boolean doubled) {
		int cardValue = checkCardValue(player);
		int dealerCardValue = checkCardValue(dealer);
		boolean playerBj = isBlackjack(player);
		boolean dealerBj = isBlackjack(dealer);
		if(doubled) {
			bet *= 2;
			numTotalDoubled++;
		}
		if(cardValue <= 21)
			noBust = true;
		if(dealerBj && !playerBj) {
			gameList[0]++;
			return -bet;
		}
		if(playerBj && !dealerBj) {
			gameList[1]++;
			return bet * 1.5;
		}
		if(cardValue > 21) {
			gameList[2]++;
			if(doubled)
				doubledList[0]++;
			return -bet;
		}
		if(dealerCardValue > 21) {
			gameList[3]++;
			if(doubled)
				doubledList[1]++;
			return bet;
			
		}
		if(cardValue < dealerCardValue) {
			gameList[4]++;
			if(doubled)
				doubledList[2]++;
			return -bet;
			
		}
		if(cardValue > dealerCardValue) {
			gameList[5]++;
			if(doubled)
				doubledList[3]++;
			return bet;
		}
		gameList[6]++;
		if(doubled)
			doubledList[4]++;
		return 0;
	}
	private String prompt() {
		return new Scanner(System.in).nextLine();
	}
	private void gameData() {
		//gamelist 0, 2, 4 dealer wins, 1, 3, 5 player wins
		System.out.println();
		System.out.print("Array of game results for player(blackjacks, dealer busts, wins): ");
		for(int i = 1; i < gameList.length- 1; i += 2)
			System.out.print(100.0 * gameList[i]/numTotalHands + "%(" + gameList[i] + "), ");
		
		
		
		System.out.println();
		System.out.print("Array of game results for dealer(blackjacks, player busts, wins): ");
		for(int i = 0; i < gameList.length - 1; i += 2)
			System.out.print(100.0 * gameList[i]/numTotalHands + "%(" + gameList[i] + "), ");
		
		
		System.out.println();
		System.out.println(100.0 * gameList[6]/numTotalHands + "%(" + gameList[6] + "), ");
		System.out.print("Array of doubled game results: ");
		for(double i: doubledList)
			System.out.print(100.0 * i/numTotalDoubled + "%(" + i + "), ");
		
		
		System.out.println();
		System.out.println();
		System.out.println("Player, then dealer, then ties percentage");
		System.out.println((gameList[1]/numTotalHands + gameList[3]/numTotalHands + gameList[5]/numTotalHands)*100);
		System.out.println((gameList[0]/numTotalHands + gameList[2]/numTotalHands + gameList[4]/numTotalHands)*100);
		System.out.println((gameList[6]/numTotalHands)*100);
		System.out.println();
		System.out.println((doubledList[1]/numTotalDoubled + doubledList[3]/numTotalDoubled)*100);
		System.out.println((doubledList[0]/numTotalDoubled + doubledList[2]/numTotalDoubled)*100);
		System.out.println((doubledList[4]/numTotalDoubled)*100);
		System.out.println("You now have " + coins + " coins");
		System.out.println("You now have " + coins + " coins");
		System.out.println("You now have " + coins + " coins");
		System.out.println("least amount of coins : " + leastAmountCoins);
		System.out.println("Max amount of coins : " + mostAmountCoins);
		System.out.println("You have played this many games: " + numGames);
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
		//
		//System.out.println("Hand " + numHands);
		//viewCards("player");
		//System.out.println("Dealer's cards: " + dealer.get(0) + ", ?");
		while(stop) {
			//System.out.println("You wanna hit, stand, split, or double down?(h/s/sp/d)");
			if(manualPlaying)
				option = prompt();
			else
				option = getOutcome();
			if(option.equals("sp") && player.size() == 2 && player.get(0).equals(player.get(1)))
				return true;
			if(option.equals("h") || option.equals("d")) {
				drawCard(player);
				//viewCards("player");
			}
			if(!option.equals("h") || checkCardValue(player) >= 21)
				stop = false;
		}
		if(checkCardValue(player) < 21) {
			while(checkCardValue(dealer) < 17)
				drawCard(dealer);
		}
		if(option.equals("d")) 
			resultList.add(handOutcome(bet, true));
		else
			resultList.add(handOutcome(bet, false));
		//numHands++;
		//System.out.println();
		return false;
	}
	public void game(int bet) {
		if(warning) 
			System.out.println("Warning: Choosing an invalid option will just lead to the 'stand' option.");
		warning = false;
		noBust = false;
		int handsLeft = 1;
		//numHands = 1;
		dealer.clear();
		//shuffle();
		resultList.clear();
		drawCard(dealer);
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
		//if(noBust)
			//viewCards("dealer");
		//System.out.println();
		//int hands = 1;
		for(double result: resultList) {
			//if(result < 0)
				//System.out.println("For hand " + hands + ", you lost " + Math.abs(result) + " coins");
			//else
				//System.out.println("For hand " + hands + ", you won " + Math.abs(result) + " coins");
			//hands++;
			coins += result;
			numTotalHands++;
		}
		if(coins > mostAmountCoins)
			mostAmountCoins = coins;
		if(coins < leastAmountCoins)
			leastAmountCoins = coins;
		numGames++;
		//System.out.println("You now have " + coins + " coins");
		//System.out.println("You have played this many games: " + numGames);
		//System.out.println("You want to try again? (y/n)");
		//cards();
		if(manualPlaying)
			if(!prompt().equals("n"))
				game(bet);
	}
	public void simulation(double times, int bet) {
		manualPlaying = false;
		LocalTime startTime = LocalTime.now();
		System.out.println(startTime);
		for(double i = 0; i < times; i++){
			game(bet);
		}
		LocalTime endTime = LocalTime.now();
	    System.out.println(endTime);
	    int hours = Integer.parseInt(endTime.toString().substring(0, 2)) - Integer.parseInt(startTime.toString().substring(0, 2));
		int minutes = Integer.parseInt(endTime.toString().substring(3, 5)) - Integer.parseInt(startTime.toString().substring(3, 5));
		int seconds = Integer.parseInt(endTime.toString().substring(6, 8)) - Integer.parseInt(startTime.toString().substring(6, 8));
		double microseconds = Double.parseDouble("0." + endTime.toString().substring(9, 14)) - Double.parseDouble("0." + startTime.toString().substring(9, 14));
		double total = hours*3600 + minutes*60 + seconds + microseconds;
		System.out.println(total);
		manualPlaying = true;
		gameData();
	}
}
