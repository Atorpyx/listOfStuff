package nicholas;

import java.util.ArrayList;

public class BlackjackLogic {
	private ArrayList<String> dealer = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	public boolean isSoft(ArrayList<String> holder) {
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
}
