package pd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cup {
	private final List<Die> dice = new ArrayList<>();

	public Cup() {
		IntStream.range(0, 5).forEach(x -> {
			dice.add(new Die());
		});
	}

	public List<Integer> rollDice() {
		return dice.stream().map(Die::roll).sorted().collect(Collectors.toList());
	}

	public void addDieToCup() {
		dice.add(new Die());
	}

	public void removeDieFromCup() {
		dice.remove(0);
	}

	public void refillCup() {
		IntStream.range(0, 5 - dice.size()).forEach(x -> {
			dice.add(new Die());
		});
	}
}
