package test;

import pd.Cup;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	private final Cup cup = new Cup();
	private final List<Integer> currentValues = new ArrayList<>();
	private final Scanner input = new Scanner(System.in);

	public void startGame() {
		playerTurn();
	}

	private void playerTurn() {
		int rollCount = 0;
		boolean quitRolling = false;
		List<Integer> rolls;

		do {
			 rolls = cup.rollDice();

			if(rolls.size() > 0) {
				System.out.println("\nRolls: " + rolls);

				System.out.print("Enter the indices of the numbers you wish to keep separated by spaces (or just press Enter to skip): ");
				String nextLine = input.nextLine();
				if(!nextLine.equals("")) {
					List<Integer> copiedRolls = rolls;
					Arrays.stream(nextLine.split("\\s")).mapToInt(Integer::parseInt).forEach(index -> {
						currentValues.add(copiedRolls.get(index));
						cup.removeDieFromCup();
					});
				}

				Collections.sort(currentValues);
				System.out.println("Current kept dice: " + currentValues);

				System.out.print("\nEnter the indices of the numbers you wish to put back into the cup separated by spaces (or just press Enter to skip): ");
				nextLine = input.nextLine();
				if(!nextLine.equals("")) {
					Arrays.stream(nextLine.split("\\s")).mapToInt(Integer::parseInt).forEach(index -> {
						currentValues.remove(index);
						cup.addDieToCup();
					});
				}
			}

			if(rollCount != 2) {
				System.out.print("\nDo you wish to roll again (y or n): ");
				quitRolling = input.nextLine().toLowerCase().equals("n");
			}

			rollCount++;
		} while(rollCount < 3 && !quitRolling);

		if(currentValues.size() < 5) {
			currentValues.addAll(rolls);
		}

		System.out.println("\nFinal values: " + currentValues);

		printScoringOptions();

		currentValues.clear();
		cup.refillCup();
	}

	private void printScoringOptions() {
		System.out.println("\nPossible Options");
		System.out.println("1s: " + numberValue(1));
		System.out.println("2s: " + numberValue(2));
		System.out.println("3s: " + numberValue(3));
		System.out.println("4s: " + numberValue(4));
		System.out.println("5s: " + numberValue(5));
		System.out.println("6s: " + numberValue(6));
		System.out.println("3ofK: " + threeOfAKind());
		System.out.println("4ofK: " + fourOfAKind());
		System.out.println("FH: " + fullHouse());
		System.out.println("Sm. Str: " + smallStraight());
		System.out.println("Lg. Str: " + largeStraight());
		System.out.println("Chance: " + totalValue());
		System.out.println("Yahtzee: " + yahtzee());
	}

	private int numberValue(int number) {
		return currentValues.stream().filter(v -> v == number).reduce(0, Integer::sum);
	}

	private int totalValue() {
		return currentValues.stream().reduce(0, Integer::sum);
	}

	private int diff(int index1, int index2) {
		return currentValues.get(index1) - currentValues.get(index2);
	}

	private int threeOfAKind() {
		return (currentValues.get(0) == currentValues.get(2) || currentValues.get(1) == currentValues.get(3) || currentValues.get(2) == currentValues.get(4)) ? totalValue() : 0;
	}

	private int fourOfAKind() {
		return (currentValues.get(0) == currentValues.get(3) || currentValues.get(1) == currentValues.get(4)) ? totalValue() : 0;
	}

	private int fullHouse() {
		return
			(currentValues.get(0) == currentValues.get(2) && currentValues.get(3) == currentValues.get(4))
			|| (currentValues.get(0) == currentValues.get(1) && currentValues.get(2) == currentValues.get(4))
			? 25
			: 0;
	}

	private int smallStraight() {
		List<Integer> distinctValues = currentValues.stream().distinct().collect(Collectors.toList());
		return switch (distinctValues.size()) {
			case 5 -> IntStream.range(0, 3).allMatch(index -> distinctValues.get(index+1) - distinctValues.get(index) == 1) || IntStream.range(1, 4).allMatch(index -> distinctValues.get(index+1) - distinctValues.get(index) == 1) ? 30 : 0;
			case 4 -> IntStream.range(0, 3).allMatch(index -> distinctValues.get(index+1) - distinctValues.get(index) == 1) ? 30 : 0;
			default -> 0;
		};
	}

	private int largeStraight() {
		return IntStream.range(0, 4).allMatch(index -> diff(index+1, index) == 1) ? 40 : 0;
	}

	private int yahtzee() {
		return currentValues.get(0) == currentValues.get(4) ? 50 : 0;
	}
}
