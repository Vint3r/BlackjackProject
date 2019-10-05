package com.skilldistillery.cards;

public enum Suit {
	HEARTS("Hearts"), SPADES("Spades"), CLUBS("Clubs"), DIAMONDS("Diamonds");

	private String name;

	private Suit(String n) {
		this.name = n;
	}

	@Override
	public String toString() {
		return name;
	}
}