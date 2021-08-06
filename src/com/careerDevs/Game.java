package com.careerDevs;

import java.util.*;

public class Game {
    public Scanner scanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();
    List<Integer> previousBids = new ArrayList<>();
    public Map<Integer, Integer> diceOnTable = new HashMap<>();
    private final int MAX_PLAYERS = 6;
    private final int MIN_PLAYERS = 1;
    public int initialBidDiceQty;
    public int initialBidDiceFaceValue;
    public int nextGuessDiceQty;
    public int nextGuessDiceFaceValue;
    public boolean isALie = false;
    public boolean isActiveRound = false;
    public boolean isStartingPlayer = true;

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

    public void round() {
        isActiveRound = true;
            roll();
        while (isActiveRound) {
            turn();

            isActiveRound = false;
        }
        //declareWinner();
    }

    public void turn() {
        for (Player activePlayer : players) {
            System.out.println(activePlayer.playerName + " 's turn.");
            System.out.println(activePlayer.cup.displayHand());

            if (isStartingPlayer) {
                makeBid();
                isStartingPlayer = false;
            } else {
                guessOrCall(activePlayer);

            }
        }
        isStartingPlayer = true;
    }

    public void makeBid() {
        System.out.println("make your bid: ");
        System.out.println("Enter qty of dice on the table: ");
        initialBidDiceQty = scanner.nextInt();
        System.out.println("Enter dice face value: ");
        initialBidDiceFaceValue = scanner.nextInt();
        System.out.println("Current bid : " + initialBidDiceQty + "x " + initialBidDiceFaceValue);

        scanner.nextLine();
    }

    public void guessOrCall(Player activePlayer) {
        System.out.println("Type (bid) to bid or (lie) if you think the bid is a lie.");
        String playerGuess = scanner.nextLine();
        if (playerGuess.equals("bid")) {
            System.out.println("Enter qty of dice on table: ");
            nextGuessDiceQty = scanner.nextInt();
            System.out.println("Enter face value: ");
            nextGuessDiceFaceValue = scanner.nextInt();
            System.out.println("Current bid : " + nextGuessDiceQty + "x " + nextGuessDiceFaceValue);

            validateBid();


        } else if (playerGuess.equals("lie")) {
            checkLie(activePlayer);
            for (Player player : players) {
                System.out.println(player.cup.displayHand());
            }
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

    public void checkLie(Player activePlayer) {
        if (diceOnTable.containsKey(initialBidDiceFaceValue) && diceOnTable.get(initialBidDiceFaceValue) >= initialBidDiceQty) {
            isALie = false;
            isActiveRound = false;
        } else {
            isALie = true;
            isActiveRound = false;
        }
        if (isALie) {
            System.out.println("bid was a lie");
            System.out.println(players.get(players.indexOf(activePlayer) - 1).playerName + " loses a die.");
            players.get(players.indexOf(activePlayer) - 1).cup.dice.remove(0);

//            if (activePlayer.cup.dice.size() == 0) {
//                System.out.println(activePlayer.playerName + " is out of dice. You are out of the game");
//           }
        } else {
            System.out.println("Bid was not a lie you lose a die");
            players.get(players.indexOf(activePlayer)).cup.dice.remove(0);
        }
    }

    public void declareWinner() {
        if (players.get(0).cup.dice.size() > players.get(1).cup.dice.size()) {
            System.out.println(players.get(0).playerName + " is the winner." );
        } else {
            System.out.println(players.get(1).playerName + " is the winner.");
        }
    }


}

