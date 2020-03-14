package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.card.Rank;
import hu.ak_akademia.blackjack.card.Suit;
import hu.ak_akademia.blackjack.constans.Constans;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;

public class MainConsolVersion {

	public static void main(String[] args) throws FileNotFoundException {
		

		Carddeck carddeck = new Carddeck();
		ArrayList<Card> cards = carddeck.getCarddeck();
		for (Card card : cards) {
			System.out.println(card);
		}
		System.out.println("--------------------------");
		carddeck.shuffle();
		cards = carddeck.getCarddeck();
		for (Card card : cards) {
			System.out.println(card);
		}
		System.out.println("-------------------------------");
		ArrayList<Gamer> gamers = new ArrayList<>();
		Gamer gamer1 = new Gamer("Első");
		Gamer gamer2 = new Gamer("Második");
		Gamer gamer3 = new Gamer("Hármadik");
		Gamer gamer4 = new Gamer("Negyedik");

		gamers.add(gamer1);
		gamers.add(gamer2);
		gamers.add(gamer3);
		gamers.add(gamer4);

		for (Gamer gamer : gamers) {
			System.out.println(gamer);
		}

		Player gamer5 = new Player(gamer1.getName());
		Player gamer6 = new Player(gamer2.getName());
		Player gamer7 = new Player(gamer3.getName());
		Player gamer8 = new Player(gamer4.getName());

		Diller gamer9 = new Diller(gamer1.getName());

		System.out.println("--------------------");
		gamers.add(1, gamer5);
		gamers.add(3, gamer6);
		gamers.add(5, gamer7);
		gamers.add(7, gamer8);
		gamers.add(0, gamer9);
		
		for (Gamer gamer : gamers) {
			System.out.println(gamer);
		}
		
		System.out.println(gamers.get(0) instanceof Diller);
		


	}

}
