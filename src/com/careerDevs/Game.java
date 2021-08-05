package com.careerDevs;

import java.util.*;

public class Game {
    public Scanner scanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();
    public Map<Integer, Integer> diceOnTable = new HashMap<>();
    private final int MAX_PLAYERS = 6;
    private final int MIN_PLAYERS = 1;
    public int initialBidDiceQty;
    public int initialBidDiceFaceValue;
    public int nextGuessDiceQty;
    public int nextGuessDiceFaceValue;
    public boolean isALie = false;
    public boolean isActiveRound = false;

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

    public void makeBid() {
        System.out.println("make your bid: ");
        System.out.println("Enter qty of dice on the table: ");
        initialBidDiceQty = scanner.nextInt();
        System.out.println("Enter dice face value: ");
        initialBidDiceFaceValue = scanner.nextInt();
        scanner.nextLine();


    }

    public void guessOrCall() {
        System.out.println("Type (bid) to bid or (lie) if you think the bid is a lie.");
        String playerGuess = scanner.nextLine();
        if (playerGuess.equals("bid")) {
            System.out.println("Enter qty of dice on table: ");
            nextGuessDiceQty = scanner.nextInt();
            System.out.println("Enter face value: ");
            nextGuessDiceFaceValue = scanner.nextInt();
//            betRecordDisplay = player.playerName + " 's bid is: " + secondBidHowManyDice + "x " + secondBidDiceFaceValue;
            validateBid();


        } else if (playerGuess.equals("lie")) {
            checkLie();
        }
    }

    public void validateBid() {
        if (nextGuessDiceQty > initialBidDiceQty) {
            System.out.println("Valid bid");


        } else if (nextGuessDiceQty == initialBidDiceQty
                && nextGuessDiceFaceValue > initialBidDiceFaceValue) {
            System.out.println("Valid bid");


        } else {
            System.out.println("Invalid bid, bid again");

        }
    }

    public void checkLie() {
        if (diceOnTable.containsKey(initialBidDiceFaceValue) && diceOnTable.get(initialBidDiceFaceValue) >= initialBidDiceQty) {
            System.out.println("bid was true challenger loses");
            isALie = false;
            isActiveRound = false;
            return;

        } else {
            isALie = true;
            isActiveRound = false;
        }
        if (isALie) {
            System.out.println("bid was a lie");
            System.out.println(player.playerName + " loses a die.");
            player.cup.dice.remove(0);

            if (player.cup.dice.size() == 0) {
                System.out.println(player.playerName + " is out of dice. You are out of the game");
            }
        }
    }

    }


}

