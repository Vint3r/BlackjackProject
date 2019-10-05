package com.skilldistillery.Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.skilldistillery.cards.Card;

public class Player extends Particapents {
	List<Card> splitHand = new ArrayList<>();
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

	public Map<Integer, List<Card>> splitHand() {
		splitHand.add(hand.remove(1));
		Map<Integer, List<Card>> newHand = new TreeMap<>();
		newHand.put(1, hand);
		newHand.put(2, splitHand);
		return newHand;
	}

	public boolean handCanSplit() {
		Card compare = hand.get(0);
		Card otherCanSplit;
		for (int i = 0; i < hand.size(); i++) {
			if (i == 0) {
				continue;
			}
			else {
				otherCanSplit = hand.get(i);
				if (otherCanSplit.equals(compare)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
