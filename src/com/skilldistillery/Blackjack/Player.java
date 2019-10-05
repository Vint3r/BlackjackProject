package com.skilldistillery.Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.skilldistillery.cards.Card;

public class Player extends Particapents {
	List<Card> splitHand = new ArrayList<>();
	Map<Integer, List<Card>> newHand = new TreeMap<>();
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

	public void addCardSplit(Card hitPlayer, Integer key) {
		newHand.get(key).add(hitPlayer);
	}

	public void displayHandsSplit(Set<Integer> keys) {
		for (Integer key : keys) {
			int handValue = 0;
			for (int i = 0; i < newHand.get(key).size(); i++) {
				handValue += newHand.get(key).get(i).getValue();
			}
			if (key == 2) {
				System.out.println("You have: " + newHand.get(key) + " in you second "
						+ "hand, with a value of " + handValue);
			}
			else if (key == 1) {
				System.out.println("You have: " + newHand.get(key) + " in your first "
						+ "hand, with a value of " + handValue);
			}
		}
	}
	
}
