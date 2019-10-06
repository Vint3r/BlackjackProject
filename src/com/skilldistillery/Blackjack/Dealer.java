package com.skilldistillery.Blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;
import com.skilldistillery.cards.Rank;

public class Dealer extends Particapents {
	Deck deck;

	public Dealer() {
		super();
	}

	public Dealer(String role) {
		super(role);
	}

	public List<Card> dealStartHand() {
		deck = new Deck();
		List<Card> userCards = new ArrayList<>();
		List<Card> dealerCards = new ArrayList<>();
		hand.removeAll(hand);
		deck.shuffle();
		for (int i = 1; i <= 2; i++) {
			dealerCards.add(deck.dealCard());
			userCards.add(deck.dealCard());
		}
		hand.addAll(dealerCards);
		if (isBlackjack(hand, role)) {
			displayHandAfterUser();
		} else {
			displayHands();
			return userCards;
		}
		return userCards;
	}

	public void displayHands() {
		System.out.println("The dealer has a card face down and a " + hand.get(1) + " face up for a total of "
				+ hand.get(1).getValue());
	}

	public int displayHandAfterUser() {
		int handValue = 0;
		System.out.println("Dealer's cards are ");
		for (Card card : hand) {
			System.out.println(card);
			handValue += card.getValue();
		}
		System.out.println("For a total of " + handValue);
		return handValue;
	}

	public Card hitPlayer() {
		Card drawn = deck.dealCard();
		System.out.println("You get a " + drawn);
		return drawn;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void dealerTurn() {
		int mustStay = 17;
		boolean bust = isBust(hand);
		int counter = 1;
		System.out.println("Your turn is now over, dealers turn begins...");
		displayHandAfterUser();
		int handValue = hand.get(0).getValue() + hand.get(1).getValue();

		while (bust == false) {
			if ((handValue < mustStay) || hand.contains(Rank.ACE)) {
				if (!hand.contains(Rank.ACE)) {
					counter++;
					hand.add(deck.dealCard());
					handValue += hand.get(counter).getValue();
					System.out.println(
							"Dealer draws a " + hand.get(counter) + " bringing his hand value to: " + handValue);
					if (isBust(hand)) {
						System.out.println("Dealer busts.");
						bust = true;
					}
				} else {
					handValue -= 10;
					counter++;
					hand.add(deck.dealCard());
					handValue += hand.get(counter).getValue();
					System.out.println(
							"Dealer draws a " + hand.get(counter) + " bringing his hand value to: " + handValue);
					if (isBust(hand)) {
						System.out.println("Dealer busts.");
						bust = true;
					}
				}
			} else if (handValue >= mustStay) {
				if (handValue <= 21) {
					System.out.println("Dealer stands with a finishing hand total of " + handValue);
					bust = true;
				}
			}
		}
	}

	@Override
	public void displayHands(List<Card> hand) {
	}

}
