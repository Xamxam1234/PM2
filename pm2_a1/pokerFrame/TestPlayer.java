package pokerFrame;
import cards.Card;
import static cards.Card.Constant.*;
import static pokerFrame.testSupport.HandRanking.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestPlayer {

    //tester
    Player tester = new Player();

    //test object with name of comparing HandRanking
    Card[] highCard = {C2,S3,D5,C6,S9,SJ,DK};
    Card[] onePair = {C2,S2,D5,C6,S9,SJ,DK};
    Card[] twoPairs = {C2,S2,D5,C5,S9,SJ,DK};
    Card[] trips = {C2,S2,D2,C6,S9,SJ,DK};
    Card[] straight = {C2,S3,D4,C5,S6,S7,D8};
    Card[] flush = {C2,C3,C5,C6,C9,CJ,CK};
    Card[] fullHouse = {C2,S2,D2,C6,S6,SJ,D6};
    Card[] quads = {C9,S2,D9,H9,S9,SJ,DK};
    Card[] straightFlush = {C2,C3,C4,C5,C6,SJ,C7};

    /**
     * Test outputs of the Contain's method, where inputs are right
     */
    @Test
    public void testContain(){
        assertEquals(true,tester.containStraight(straight));
        assertEquals(true,tester.containStraightFlush(straightFlush));
        assertEquals(true,tester.containHighCard(highCard));
        assertEquals(TWO,tester.containPairs(onePair));
        assertEquals(TWO,tester.containTrips(trips));
        assertEquals(CLUB,tester.containFlush(flush));
        assertEquals(SIX,tester.containFullHouse(fullHouse));
        assertEquals(NINE,tester.containQuads(quads));
    }

    /**
     * Test outputs of the Contain's method, where inputs are wrong
     */
    @Test
    public void testNotContain(){
        assertEquals(false,tester.containStraight(flush));
        assertEquals(false,tester.containStraightFlush(straight));
        assertEquals(false,tester.containStraightFlush(flush));
        assertEquals(false,tester.containHighCard(flush));
        assertEquals(null,tester.containTrips(twoPairs));
        assertEquals(null,tester.containFlush(straight));
        assertEquals(null,tester.containFullHouse(quads));
        assertEquals(null,tester.containQuads(fullHouse));
    }

    /**
     * Test if rateHand() return right value
     */
    @Test
    public void testRateHand() {

        assertEquals(STRAIGHT_FLUSH, tester.rateHand(straightFlush));
        assertEquals(QUADS, tester.rateHand(quads));
        assertEquals(FULL_HOUSE, tester.rateHand(fullHouse));
        assertEquals(FLUSH, tester.rateHand(flush));
        assertEquals(STRAIGHT, tester.rateHand(straight));
        assertEquals(TRIPS, tester.rateHand(trips));
        assertEquals(TWO_PAIR, tester.rateHand(twoPairs));
        assertEquals(ONE_PAIR, tester.rateHand(onePair));
        assertEquals(HIGH_CARD, tester.rateHand(highCard));
    }
}
