import java.util.Scanner;

abstract class Player {
	int numberOfMatches;
	String type;

	Player(int numberOfMatches, String type) {
		this.numberOfMatches = numberOfMatches;
		this.type = type;
	}

	abstract void playerStats();
}

class CricketPlayer extends Player {
	int runs, wickets, centuries;

	CricketPlayer(int runs, int wickets, int centuries, int numberOfMatches) {
		super(numberOfMatches, "Cricket player");
		this.runs = runs;
		this.wickets = wickets;
		this.centuries = centuries;
	}

	public void playerStats() {
		System.out.println("Runs: " + runs + "\nWickets: " + wickets + "\nCenturies: " + centuries + "\nNumber of matches: " + numberOfMatches + "\nType: " + type);
		System.out.println("Average (runs per match): " + (runs / numberOfMatches));		
	}
}

class FootballPlayer extends Player {
	int goals;

	FootballPlayer(int goals, int numberOfMatches) {
		super(numberOfMatches, "Football player");
		this.goals = goals;
	}

	public void playerStats() {
		System.out.println("Goals: " + goals + "\nNumber of matches: " + numberOfMatches + "\nType: " + type);
		System.out.println("Average goals per match: " + (goals / numberOfMatches));
	}
}

class Players {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Player p;

		System.out.print("\n\nPlayer type: \n\n1. Cricket\n2. Football\n\nEnter choice: ");
		int choice = sc.nextInt();
		if(choice == 1) {
			System.out.println("Enter the following details:");
			System.out.print("Runs: ");
			int runs = sc.nextInt();
			System.out.print("Wickets: ");
			int wickets = sc.nextInt();
			System.out.print("Centuries: ");
			int centuries = sc.nextInt();
			System.out.print("Number of matches: ");
			int numberOfMatches = sc.nextInt();

			p = new CricketPlayer(runs, wickets, centuries, numberOfMatches);
			p.playerStats();
		} else if(choice == 2) {
			System.out.println("Enter the following details:");
			System.out.print("Goals: ");
			int goals = sc.nextInt();
			System.out.print("Number of matches: ");
			int numberOfMatches = sc.nextInt();

			p = new FootballPlayer(goals, numberOfMatches);
			p.playerStats();
		} else {
			System.out.println("Error in input");
		}
	}
}