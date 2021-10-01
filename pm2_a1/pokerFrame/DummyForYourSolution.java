// release for WS20/21 by Michael Schaefers;  1st release WS07/08 by Michael Schaefers
// lastModified 2020/10/26 by Michael Schaefers
package pokerFrame;


import static cards.Card.Constant.*;
import static pokerFrame.testSupport.HandRanking.*;

//
import cards.*;
import pokerFrame.testSupport.*;

import java.util.*;


public class DummyForYourSolution implements GameAnalyzer {

    @Override
    public ResultOfGame compute( final TestCaseActual tca ){
        // Zugriff auf die Karten
        // ======================

        Card[]  player1_hole_cards = tca.getPlayerHoleCards( 0 );         // player #1
        Card[]  player2_hole_cards = tca.getPlayerHoleCards( 1 );         // player #2
        Card[]  player3_hole_cards = tca.getPlayerHoleCards( 2 );         // player #3
        Card[]  player4_hole_cards = tca.getPlayerHoleCards( 3 );         // player #4
        Card[]  player5_hole_cards = tca.getPlayerHoleCards( 4 );         // player #5

        Card[]  community_cards = tca.getCommunityCards();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();

        Card[] player1_total = player1.joinHand(player1_hole_cards,community_cards);
        Card[] player2_total = player2.joinHand(player2_hole_cards,community_cards);
        Card[] player3_total = player3.joinHand(player3_hole_cards,community_cards);
        Card[] player4_total = player4.joinHand(player4_hole_cards,community_cards);
        Card[] player5_total = player5.joinHand(player5_hole_cards,community_cards);

        // Ihre Berechnung
        // ===============
        //
        // ...
        // Hier kommt Code von Ihnen hin - (moegliche) Idee:
        // Moeglicherweise Konstruktion der 5 Player-Objekte/Spieler und Uebergabe der 7 zugehoerigen Karten an den jeweiligen Konstruktor.
        // Abfrage des Ergebnisses vom jeweiligen Spieler
        // Vergeleich der Ergenisse der jeweiligen Spieler und Bestimmung ihrer Position
        //
        // Weitere Annahme fuer das Beispiel - das (berechnete) Ergebnis sei "wie unten"

        
        // Im Vorfeld berechnen der Ergebnisse
        //
        // Welche (Poker-)Hand bzw. welche 5 Karten spielen die jeweiligen Spieler
        final Card[] pokerHandOfPlayer1 = player1.getBestHand();               // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer2 = player2.getBestHand();                    // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer3 = player3.getBestHand();                    // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer4 = player4.getBestHand();                   // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer5 = player5.getBestHand();                   // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        //
        // Wie heißt die jeweilige (Poker-)Hand
        final HandRanking handRankingOfPlayer1 = player1.getHandRanking();                                // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer2 = player2.getHandRanking();                                     // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer3 = player3.getHandRanking();;                                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer4 = player4.getHandRanking();;                                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer5 = player5.getHandRanking();;                                  // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        //
        // Welche Position hat der Spieler - ist er Erster, Zweiter, Dritter , Vierter oder Fuenfter ?


        final int positionOfPlayer1 = player1.positionRanking(player1,player2,player3,player4,player5);    // Spieler1 ist Zweiter (zusammen mit Spieler5)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer2 = player1.positionRanking(player2,player1,player3,player4,player5);   // Spieler2 ist Erster                           - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer3 = player1.positionRanking(player3,player2,player1,player4,player5);   // Spieler3 ist Vierter (zusammen mit Spieler4)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer4 = player1.positionRanking(player4,player2,player3,player1,player5);  // Spieler4 ist Vierter (zusammen mit Spieler3)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer5 = player1.positionRanking(player5,player2,player3,player4,player1);   // Spieler5 ist Zweiter (zusammen mit Spieler1)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        //
        // Nachdem Sie alles berechnet haben

        // Abliefern des Ergebnisses
        // =========================
        
        final ResultOfGame rog = new ResultOfGame(
            new ResultOfPlayer[]{
                //
                //                  /------------------------------------------------------ position of player:
                //                  |                                                       1 is the best, .., 5 is the worst
                //                  |
                //                  |                   /---------------------------------- handranking of player - valid values are:
                //                  |                   |                                   STRAIGHT_FLUSH, QUADS, FULL_HOUSE, FLUSH, STRAIGHT, 
                //                  |                   |                                   TRIPS, TWO_PAIR, ONE_PAIR and HIGH_CARD.
                //                  |                   |                                   These values are defined in the enum poker.testSupport.HandRanking
                //                  |                   |
                //                  |                   |                      /----------- cards of player that are played
                //                  |                   |                      |
                //                  V                   V                      V
                new ResultOfPlayer( positionOfPlayer1,  handRankingOfPlayer1,  pokerHandOfPlayer1 ),   // results for player #1
                new ResultOfPlayer( positionOfPlayer2,  handRankingOfPlayer2,  pokerHandOfPlayer2 ),   // results for player #2
                new ResultOfPlayer( positionOfPlayer3,  handRankingOfPlayer3,  pokerHandOfPlayer3 ),   // results for player #3
                new ResultOfPlayer( positionOfPlayer4,  handRankingOfPlayer4,  pokerHandOfPlayer4 ),   // results for player #4
                new ResultOfPlayer( positionOfPlayer5,  handRankingOfPlayer5,  pokerHandOfPlayer5 )    // results for player #5
            }//array-new
        );//new
        return rog;
    }//method()
}//class
