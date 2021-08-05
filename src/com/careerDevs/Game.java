package com.careerDevs;

import java.util.*;

public class Game {
    public Scanner scanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();
    public Map<Integer, Integer> diceOnTable = new HashMap<>();
    private final int MAX_PLAYERS = 6;
    private final int MIN_PLAYERS = 1;

    public Game() {
        System.out.println("Enter amount of players: ");
        int numberOfPlayers;
        do {
            numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
        }   while (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS);

        while (players.size() < numberOfPlayers) {
            System.out.println("Enter player name: ");
            players.add(new Player((scanner.nextLine()).trim()));
        }
    }

    public void roll() {
        for (Player activePlayer : players) {
            activePlayer.cup.roll();
            System.out.println(activePlayer.cup.displayHand());
            setDiceOnTable(activePlayer.cup.dice);
        }
        System.out.println(diceOnTable);
    }

    public void setDiceOnTable(List<Die> dice) {
        for (Die die : dice) {
            if (diceOnTable.containsKey(die.faceUpValue)) {
                diceOnTable.put(die.faceUpValue, diceOnTable.get(die.faceUpValue) + 1);

            } else {
                diceOnTable.put(die.faceUpValue, 1);
            }
        }

    }


}

