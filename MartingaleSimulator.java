/*
READ VERY IMPORTANT
This java program simulates the Martingale strategy when applied to a coinfliip(50/50 chance). 
if you would like to change the percentage do so in the flip method

The constructor takes in amount of coins, and amount you want to bet, so for example:
constructor(100, 1): this gives the simulation 100 coins to bet 1 at a time. 

The roundsBeforeBroke takes in a double and a boolean
The double represents how many times you are running a simulation of whatever amount of coins and the bet that you put in the instantiation
The boolean is whether or not you want the average and the max number of all the simulations you ran. 
Keep in mind that one the bet size is more than the amount of coins there are, that simulation is considered broke
Also the average is the highest amount of coins that the simulation had before going broke. 
You can put the data in spreadsheets and see how it turns out :)

The simulteMultipleRounds is simple, it takes in an int of how many times
It just simulates a round and shows how many games was won or lost. 
https://www.investopedia.com/terms/m/martingalesystem.asp

*/
public class MartingaleSimulator {
	int coins;
	int bet;
	public MartingaleSimulator(int coins, int amount) {
		this.coins = coins;
		bet = amount;
	}
	public boolean flip() {
		return Math.random() < 0.5;
	}
	public boolean simulate(double holder, double bet) {
		while(holder >= bet) {
			if(flip()) {
				return true;
			} else {
				holder = holder - bet;
				bet *= 2;
			}
			
		}
		return false;
	}
	public void roundsBeforeBroke(double times, boolean showAverageAndMax) {
		double average = 0;
		double max = 0;
		for(int j = 0; j < times; j++) {
			double holder = coins;
			while(simulate(holder, bet))
				holder += bet;
			average += holder;
			System.out.println(holder);
			if(holder > max)
				max = holder;
		}
		if(showAverageAndMax)
			System.out.println("average: " + average/times + " max " + max);
	}
	public void simulateMultipleRounds(int times) {
		int lost = 0;
		int won = 0;
		for(int i = 0; i < times; i++) {
			if(simulate(coins,bet))
				won++;
			else
				lost++;
		}
		System.out.println(won);
		System.out.println(lost);
	}
}
