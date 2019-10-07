package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> deck = new ArrayList<>();

	public Deck() {
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();
		for (Rank rank : ranks) {
			for (int i = 0; i < suits.length; i++) {
				Card card = new Card(rank, suits[i]);
				deck.add(card);
			}
		}
	}

	public int checkDeckSize() {
		return deck.size();
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public Card dealCard() {
		Card card = deck.remove(0);
		return card;
	}

}
