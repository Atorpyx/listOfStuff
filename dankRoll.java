public class Main
{
    public static void main(String[] args) {
        for(int i = 0; i < 25; i++)
		    getRollData(2000000000);
    }

    public static boolean simulateRoll() {

    		int range = 100;
    		boolean playersTurn = true;
    		while(true) {
    			int roll = ((int) (Math.random()*range))+1;
    			if(roll == 1)
    				break;
    			playersTurn = !playersTurn;
    			range = roll;
    		} 
    		return playersTurn;
    }
    public static void getRollData(double repetitions) {
    		double won = 0;
    		for(int i = 0; i < repetitions; i++){
    			if(simulateRoll())
    				won++;
    		}
    		System.out.println((won/repetitions)*100 + "%");
    }
}
