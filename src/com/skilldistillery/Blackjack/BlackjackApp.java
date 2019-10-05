package com.skilldistillery.Blackjack;

import java.util.Scanner;

public class BlackjackApp {
	Dealer dealer = new Dealer("Dealer");
	Player player;
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		System.out.println("Welcome to Blackjack!");
		app.launch(app);
	}

	private void launch(BlackjackApp app) {
		boolean userWants = true;
		while (userWants) {
			System.out.println();
			System.out.println("Would you like to play?");
			String user = input.nextLine();

			switch (user.toLowerCase()) {
			case "yes":
			case "sure":
			case "yea":
			case "yeah":
			case "1":
				app.startGame(app);
				break;
			case "no":
			case "nope":
			case "not really":
			case "2":
				System.out.println("Goodbye! Hope to see you again soon!");
				userWants = false;
				break;
			default:
				System.err.println("THAT IS NOT A VALID INPUT TRY AGAIN");
			}
		}
	}

	private void startGame(BlackjackApp app) {
		System.out.println("Excellent, I'll deal our cards.");
		System.out.println();
		player = new Player("Player", dealer.dealStartHand());
		System.out.println();
		boolean isBJPlayer = player.isBlackjack(player.getHand(), player.getRole());
		boolean isBJDealer = dealer.isBlackjack(dealer.getHand(), dealer.getRole());
		if ((isBJPlayer == false) && (isBJDealer == false)) {
			player.displayHands(player.getHand());
			app.game(app);
		}
	}

	private void game(BlackjackApp app) {
		boolean turnOver = false;
		String userIn = "";
		do {
			turnOver = player.isBust(player.getHand());
			if(turnOver) {
				System.out.println("You bust, sorry 'bout that.");
				userIn = "2";
			}
			else {
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("1.) Hit");
			System.out.println("2.) Stay");
			userIn = input.nextLine();
			}
			switch (userIn.toLowerCase()) {
			case "hit":
			case "one":
			case "1":
			case "hit me":
				player.addCard(dealer.hitPlayer());
				player.displayHands(player.getHand());
				break;
			case "stay":
			case "2":
			case "two":
				dealer.dealerTurn();
				app.finalCompare();
				turnOver = true;
				break;
			default:
				System.err.println("THAT IS A INVALID SELECTION");
			}
		} while (turnOver == false);
	}

	private void finalCompare() {
		if (player.isBust(player.getHand()) && dealer.isBust(dealer.getHand())) {
			System.out.println("EVERYONE SUCKS WHY YOU DO THIS!");
		}
	}

}
