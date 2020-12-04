package pd;

public class Scorecard {
	private int ones = -1;
	private int twos = -1;
	private int threes = -1;
	private int fours = -1;
	private int fives = -1;
	private int sixes = -1;
	private int threeOfAKind = -1;
	private int fourOfAKind = -1;
	private int fullHouse = -1;
	private int smallStraight = -1;
	private int largeStraight = -1;
	private int chance = -1;
	private int yahtzee = -1;
	private int numBonusYahtzees = 0;

	private boolean gotBonus() {
		return (ones + twos + threes + fours + fives + sixes) >= 65;
	}

	public int getScore() {
		return ones + twos + threes + fours + fives + sixes + (gotBonus() ? 35 : 0)
						+ threeOfAKind + fourOfAKind + fullHouse + smallStraight + largeStraight + chance
						+ yahtzee + (numBonusYahtzees * 100);
	}

	public void setOnes(int ones) {
		this.ones = ones;
	}

	public void setTwos(int twos) {
		this.twos = twos;
	}

	public void setThrees(int threes) {
		this.threes = threes;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public void setFives(int fives) {
		this.fives = fives;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}

	public void setThreeOfAKind(int threeOfAKind) {
		this.threeOfAKind = threeOfAKind;
	}

	public void setFourOfAKind(int fourOfAKind) {
		this.fourOfAKind = fourOfAKind;
	}

	public void setFullHouse(int fullHouse) {
		this.fullHouse = fullHouse;
	}

	public void setSmallStraight(int smallStraight) {
		this.smallStraight = smallStraight;
	}

	public void setLargeStraight(int largeStraight) {
		this.largeStraight = largeStraight;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public void setYahtzee(int yahtzee) {
		this.yahtzee = yahtzee;
	}

	public void addBonusYahtzee() {
		numBonusYahtzees++;
	}
}
