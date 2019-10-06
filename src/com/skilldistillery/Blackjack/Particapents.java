package com.skilldistillery.Blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Rank;

public abstract class Particapents {
	List<Card> hand;
	String role;

	public Particapents() {
		hand = new ArrayList<>();
	}

	public Particapents(String role) {
		this.role = role;
		hand = new ArrayList<>();
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isBlackjack(List<Card> hand, String player) {
		int handValue = 0;
		int numOfCards = 0;
		boolean isBlackjack = false;
		for (Card card : hand) {
			handValue += card.getValue();
			numOfCards++;
		}
		if (handValue == 21 && numOfCards <= 2) {
			System.out.println("Blackjack!!" + role + " wins!!");
			isBlackjack = true;
		}
		
		return isBlackjack;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean isBust(List<Card> hand) {
		boolean bust = false;
		int handValue = 0;
		for (Card card : hand) {
			handValue += card.getValue();
		}
		if (handValue > 21 && hand.contains(Rank.ACE)) {
			handValue -= 10;
			return bust;
		}
		else if (handValue > 21 && !hand.contains(Rank.ACE)) {
			bust = true;
			return bust;
		}
		return bust;
	}
	
	public abstract void displayHands(List<Card> hand);
	
	@SuppressWarnings("unlikely-arg-type")
	public int getHandValue() {
		int handValue = 0;
		for (Card card : hand) {
			handValue += card.getValue();
			if (handValue > 21 && hand.contains(Rank.ACE)) {
				handValue -= 10;
			}
		}
		return handValue;
	}
}
