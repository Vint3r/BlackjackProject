package com.skilldistillery.Blackjack;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Rank;

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
			case "ya":
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
		boolean canSplit = player.handCanSplit();
		do {
			turnOver = player.isBust(player.getHand());
			if (turnOver) {
				System.out.println("You bust, sorry 'bout that.");
				userIn = "stay";
			} else {
				System.out.println();
				System.out.println("What would you like to do?");
				System.out.println("Hit");
				if (canSplit && (player.getHand().size() == 2)) {
					System.out.println("Split");
					System.out.println("Stay");
				} else {
					System.out.println("Stay");
				}
				userIn = input.nextLine();
			}
			switch (userIn.toLowerCase()) {
			case "hit":
			case "hit me":
				player.addCard(dealer.hitPlayer());
				player.displayHands(player.getHand());
				break;
			case "stay":
			case "done":
			case "na":
				dealer.dealerTurn();
				app.finalCompare();
				turnOver = true;
				break;
			case "split":
			case "divide":
			case "split me":
				app.splitMenu(app);
				turnOver = true;
				break;
			default:
				System.err.println("THAT IS A INVALID SELECTION");
			}
		} while (turnOver == false);
	}

	private void splitMenu(BlackjackApp app) {
		boolean turnOver = false;
		String userIn = "";
		Map<Integer, List<Card>> splitHand = player.splitHand();
		Set<Integer> keys = splitHand.keySet();
		for (Integer key : keys) {
			if (key == 1) {
				player.displayHands(splitHand.get(key));
			} else {
				System.out.println();
				player.displayHands(splitHand.get(key));
			}
		}
		for (Integer key : keys) {
			turnOver = false;
			while (turnOver == false) {
				System.out.println("Hand is split, what would you like to do?");
				System.out.println("1.) Hit");
				System.out.println("2.) Stay on this hand");
				System.out.println("3.) Stay on both hands");
				userIn = input.nextLine();

				switch (userIn.toLowerCase()) {
				case "hit":
				case "hit me":
					player.addCardSplit(dealer.hitPlayer(), key);
					player.displayHandsSplit(keys);
					break;
				case "2":
				case "two":
				case "next":
				case "next hand":
					System.out.println("Moving on to next hand...");
					turnOver = true;
					break;
				case "3":
				case "three":
				case "finished":
				case "stay":
					dealer.dealerTurn();
					app.finalCompareSplit(splitHand);
					turnOver = true;
					break;
				}
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	private void finalCompareSplit(Map<Integer, List<Card>> splitHand) {
		Set<Integer> keys = splitHand.keySet();
		for (Integer key : keys) {
			List<Card> currentHand = splitHand.get(key);
			boolean playerBust = player.isBust(currentHand);
			boolean dealerBust = dealer.isBust(dealer.getHand());
			int playerValue = 0;
			for (int i = 0; i < splitHand.get(key).size(); i++) {
				playerValue += splitHand.get(key).get(i).getValue();
				if (playerValue > 21 && splitHand.get(key).contains(Rank.ACE)) {
					playerValue -= 10;
				}
			}
			int dealerValue = dealer.getHandValue();
			if (playerBust == true && dealerBust == true) {
				System.out.println("EVERYONE SUCKS WHY YOU DO THIS!");
				System.out.println("No one wins, only busts. Busts as far as the eye can see.");
			} else if (playerBust == false && dealerBust == false) {
				if (playerValue == dealerValue) {
					System.out.println("Push game, no one wins no one looses.");
				} else if (playerValue > dealerValue) {
					System.out.println("Player wins!! Good game, dealer finished with a hand value of: " + dealerValue
							+ " and you finished with a hand value of: " + playerValue);
				} else if (dealerValue > playerValue) {
					System.out.println("Dealer wins, better luck next time! Dealer finishes with a total of: "
							+ dealerValue + " and you finished with a hand value of: " + playerValue);
				}
			} else if (dealerBust == true && playerBust == false) {
				System.out.println("Player wins with a hand value of: " + playerValue + " dealer busts");
			} else if (dealerBust == false && playerBust == true) {
				System.out.println("Dealer wins with a hand value of: " + dealerValue + " player busts");
			}
		}
	}

	private void finalCompare() {
		boolean playerBust = player.isBust(player.getHand());
		boolean dealerBust = dealer.isBust(dealer.getHand());
		int playerValue = player.getHandValue();
		int dealerValue = dealer.getHandValue();
		if (playerBust == true && dealerBust == true) {
			System.out.println("EVERYONE SUCKS WHY YOU DO THIS!");
			System.out.println("No one wins, only busts. Busts as far as the eye can see.");
		} else if (playerBust == false && dealerBust == false) {
			if (playerValue == dealerValue) {
				System.out.println("Push game, no one wins no one looses.");
			} else if (playerValue > dealerValue) {
				System.out.println("Player wins!! Good game, dealer finished with a hand value of: " + dealerValue
						+ " and you finished with a hand value of: " + playerValue);
			} else if (dealerValue > playerValue) {
				System.out.println("Dealer wins, better luck next time! Dealer finishes with a total of: " + dealerValue
						+ " and you finished with a hand value of: " + playerValue);
			}
		} else if (dealerBust == true && playerBust == false) {
			System.out.println("Player wins with a hand value of: " + playerValue + " dealer busts");
		} else if (dealerBust == false && playerBust == true) {
			System.out.println("Dealer wins with a hand value of: " + dealerValue + " player busts");
		}
	}
}
