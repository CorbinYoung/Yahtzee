package pd;

import java.util.Random;

public class Die {
	private final int sides;

	public Die() {
		sides = 6;
	}

	public Die(int sides) {
		this.sides = sides;
	}

	public int roll() {
		return (new Random()).nextInt(sides) + 1;
	}
}
