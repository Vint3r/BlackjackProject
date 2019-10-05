package com.skilldistillery.Blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;

public class Player extends Particapents {
	double cash;

	public Player(String role, double cash) {
		super(role);
		this.cash = cash;
		hand = new ArrayList<>();
	}

	public Player() {
		super();
		hand = new ArrayList<>();
	}

	public Player(String role, List<Card> hand) {
		super(role);
		this.hand = hand;
	}

	@Override
	public void displayHands(List<Card> hand) {
		int handValue = 0;
		System.out.print("You have a ");
		for (Card card : hand) {
			System.out.print(card + " ");
			handValue += card.getValue();
		}
		System.out.println(" for a total hand value of " + handValue);
	}
	
}
