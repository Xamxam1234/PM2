package pokerFrame;

import cards.Card;
import pokerFrame.testSupport.HandRanking;

import java.util.*;

import static cards.Card.Constant.*;
import static cards.Card.Constant.SPADES;
import static pokerFrame.testSupport.HandRanking.*;

public class Player {

    Card[] totalCard = new Card[7];
    HandRanking handRanking = HIGH_CARD;
    Card[] bestHand = new Card[5];
    int position = 0;

    public int getPosition(){
        return this.position;
    }

    public Card[] getBestHand(){
        this.bestHand = bestHand(totalCard);
        return this.bestHand;
    }

    public HandRanking getHandRanking(){
        this.handRanking = rateHand(this.totalCard);
        return this.handRanking;
    }

    /**
     * 7 (2 from player and 5 from community) total card for player
     * @param player player's Card array
     * @param community community's Card array
     * @return
     */
    public Card[] joinHand(Card[] player, Card[] community){
        this.totalCard = new Card[]{player[0],player[1],community[0],community[1],community[2],community[3],community[4]};
        return this.totalCard;
    }

    /**
     * Find the Ranking of first player "in"
     * @param in main Player to find
     * @param player1 other player 1
     * @param player2 other player 2
     * @param player3 other player 3
     * @param player4 other player 4
     * @return position of "in" player
     */
    public int positionRanking(Player in, Player player1, Player player2,Player player3,Player player4){
        List<Player> ranking = new ArrayList<>();
        ranking.add(in);
        ranking.add(player1);
        ranking.add(player2);
        ranking.add(player3);
        ranking.add(player4);

        Collections.sort(ranking,new PlayerComparator());
        return 5-ranking.indexOf(in);
    }

    /**
     * HandRanking of "in" player's Card array
     * @param in player's Card array
     * @return "HandRanking" enum's object
     */
    public HandRanking rateHand(Card[] in){
        if(containStraightFlush(in)) return STRAIGHT_FLUSH;
        if(containQuads(in) != null) return QUADS;
        if(containFullHouse(in) != null) return FULL_HOUSE;
        if(containFlush(in) != null) return FLUSH;
        if(containStraight(in)) return STRAIGHT;
        if(containTrips(in) != null) return TRIPS;
        if(containTwoPairs(in) != null) return TWO_PAIR;
        if(containPairs(in) != null) return ONE_PAIR;
        else return HIGH_CARD;
    }

    /**
     * best 5 Card out of 7
     * @param in 7 total Card array
     * @return best output for 7 total Card
     */
    public Card[] bestHand(Card[] in){
        switch (rateHand(in)){
            case STRAIGHT_FLUSH: return pickingStraightOrStraightFlush(in,containStraightFlush(in));

            case QUADS: return pickingQuads(in,containQuads(in));

            case FULL_HOUSE: return pickingFullHouse(in,containFullHouse(in));

            case FLUSH: return pickingFlush(in,containFlush(in));

            case STRAIGHT: return pickingStraightOrStraightFlush(in,containStraight(in));

            case TRIPS: return pickingTrips(in,containTrips(in));

            case TWO_PAIR: return pickingTwoPairs(in,containTwoPairs(in));

            case ONE_PAIR: return pickingPairs(in,containPairs(in));

            case HIGH_CARD: return pickingHighCard(in,containHighCard(in));
            default: return null;
        }
    }

    /**
     * If contain nothing but HighCard
     * @param in 7 total Card array
     * @return true, when 7 Card have 7 diffirent Rank and contain no Flush or Straight, else false
     */
    public boolean containHighCard(Card[] in){
        Arrays.sort(in);
        Set<Card.Rank> rankSet = new HashSet<Card.Rank>();
        for (Card a : in){
            rankSet.add(a.getRank());
        }
        if(rankSet.size() == 7 && !containStraight(in) && containFlush(in) == null) return true;
        else return false;
    }

    public Card[] pickingHighCard(Card[] in, boolean contain){
        Arrays.sort(in);
        Card[] out = new Card[5];
        for(int i = 6; i > 1; i--){
            out[i-2] = in[i];
        }
        return out;
    }

    /**
     * if Straight is the best option
     * @param in 7 total Card array
     * @return true, when Rank is larger than or equals 5 and contain 5 consecutive Card's Rank, else false
     */
    public boolean containStraight(Card[] in){
        boolean check = true;
        Set<Card.Rank> rankSet = new HashSet<Card.Rank>();
        for (Card a : in){
            rankSet.add(a.getRank());
        }
        if(rankSet.size() < 5) {
            return false;
        }

        Card.Rank[] arrRank = new Card.Rank[0];
        arrRank = rankSet.toArray(arrRank);

        Arrays.sort(arrRank);
        int count = 0;

        for(int i = 0; i < arrRank.length-4; i++){
            if(arrRank[i] == arrRank[i+1].decremented()
                    && arrRank[i+1] == arrRank[i+2].decremented()
                    && arrRank[i+2] == arrRank[i+3].decremented()
                    && arrRank[i+3] == arrRank[i+4].decremented()) continue;
            else check = false;

        }
        return check;
    }

    /**
     * if StraightFlush is the best option
     * @param in 7 total Card array
     * @return true, when there is a Flush and this Flush is Straight as well, else false
     */
    public boolean containStraightFlush(Card[] in){
        Card[] tmp1 = new Card[7];
        int count = 0;
        if(containFlush(in) != null){
            for(int i = 0; i < 7; i++){
                if(in[i].getSuit() == containFlush(in)) {
                    tmp1[count] = in[i];
                    count++;
                }
            }
        } else return false;

        Card[] tmp2 = new Card[count];
        System.arraycopy(tmp1,0,tmp2,0,count);

        if(containStraight(tmp2)) return true;
        else return false;
    }

    public Card[] pickingStraightOrStraightFlush(Card[] in, boolean contain){
        Arrays.sort(in);
        Card[] out = new Card[5];
        out[4] = in[6];
        int count = 4;
        for(int i = 5; i > -1;i--){
            if(in[i].getRank() == in[i+1].getRank()) {
                continue;
            } else if (in[i].getRank() != in[i+1].getRank().decremented()){
                count = 4;
                out[count] = in[i];
            } else {
                count--;
                out[count] = in[i];
                if (count < 1) break;
            }
        }
        return out;
    }

    /**
     * if Flush is the best option
     * @param in 7 total Card array
     * @return Card.Suit's value, when there are more than 5 Card have this Suit, else null
     */
    public Card.Suit containFlush(Card[] in){
        List<Card> hSuit = new ArrayList<>();
        List<Card> dSuit = new ArrayList<>();
        List<Card> cSuit = new ArrayList<>();
        List<Card> sSuit = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            switch (in[i].getSuit()){
                case DIAMOND: dSuit.add(in[i]);
                    break;
                case CLUB: cSuit.add(in[i]);
                    break;
                case HEART: hSuit.add(in[i]);
                    break;
                case SPADES: sSuit.add(in[i]);
                    break;
                default: break;
            }
        }
        if(hSuit.size() >= 5) return HEART;
        if(dSuit.size() >= 5) return DIAMOND;
        if(cSuit.size() >= 5) return CLUB;
        if(sSuit.size() >= 5) return SPADES;
        else return null;
    }

    public Card[] pickingFlush(Card[] in, Card.Suit inSuit){
        Arrays.sort(in);
        Card[] out = new Card[5];
        int count = 4;

        for(int i = 6; i > -1 ; i--){
            if(in[i].getSuit() == inSuit){
                out[count] = in[i];
                count--;
                if (count < 0) break;
            }
        }
        return out;
    }

    /**
     * If Quads is the best option
     * @param in 7 total Card array
     * @return Card.Rank's value of Quads, when there are 4 Card of the same Rank, else null
     */
    public Card.Rank containQuads(Card[] in){
        Arrays.sort(in);
        for( int i = 0; i < 4; i++){
            if(in[i].getRank() == in[i+1].getRank()
                    && in[i+1].getRank() == in[i+2].getRank()
                    && in[i+2].getRank() == in[i+3].getRank()) return in[i].getRank();
        }
        return null;
    }

    public Card[] pickingQuads(Card[] in, Card.Rank inRank){
        Arrays.sort(in);

        Card[] out = new Card[5];
        int count = 0;

        for(int i = 0; i <7; i++){
            if(in[i].getRank() == inRank){
                out[count] = in[i];
                count++;
            }
        }

        if(in[6].getRank() == inRank){
            out[count] = in[2];
        } else out[count] = in[6];
        return out;
    }

    /**
     * If FullHouse is the best option
     * @param in 7 total Card array
     * @return Card.Rank's value of the Triplet in Fullhouse, when total number Rank of 7 Card is 3 or 4, not a Quads
     *                                                                                   and there is Triplet, else null
     */
    public Card.Rank containFullHouse(Card[] in){
        Set<Card.Rank> rankSet = new HashSet<>();
        for(Card a : in){
            rankSet.add(a.getRank());
        }
        Arrays.sort(in);
        for(int i = 6; i > 2; i--){
            if((rankSet.size() == 4 || rankSet.size() == 3)
                    && in[i].getRank() == in[i-1].getRank()
                    && in[i-1].getRank() == in[i-2].getRank()) return in[i].getRank();
        }
        return null;
    }

    public Card[] pickingFullHouse(Card[] in, Card.Rank inRank){
        Arrays.sort(in);
        Card[] out = new Card[5];
        int count1 = 5;
        int count2 = 2;
        for(int i = 6; i > 0; i--){
            if(in[i].getRank() == inRank){
                if(count1 < 3) continue;
                count1--;
                out[count1] = in[i];
            } else {
                if(count2 < 1) continue;
                if(in[i].getRank() == in[i-1].getRank()) {
                    count2--;
                    out[count2] = in[i];
                    count2--;
                    out[count2] = in[i-1];
                }
            }
        }
        return out;
    }

    /**
     * if Trips is the best option
     * @param in 7 total Card array
     * @return Card.Rank's value of the Trips, when total number Rank of 7 Card is 5, and have 3 Card of the same Rank, else null
     */
    public Card.Rank containTrips(Card[] in){
        Set<Card.Rank> rankSet = new HashSet<>();
        for(Card a : in){
            rankSet.add(a.getRank());
        }
        Arrays.sort(in);
        for(int i = 0; i < 5; i++){
            if(rankSet.size() == 5
                    && in[i].getRank() == in[i+1].getRank()
                    && in[i+1].getRank() == in[i+2].getRank()) return in[i].getRank();
        }
        return null;
    }

    public Card[] pickingTrips(Card[] in, Card.Rank inRank){
        Arrays.sort(in);
        Card[] out = new Card[5];
        int count1 = 5;
        int count2 = 2;
        for(int i = 6; i > -1; i--){
            if(in[i].getRank() == inRank){
                if(count1 < 3) continue;
                count1--;
                out[count1] = in[i];
            } else {
                if(count2 < 1) continue;
                count2--;
                out[count2] = in[i];
            }
        }
        return out;
    }

    /**
     * if OnePair is the best option
     * @param in 7 total Card array
     * @return Card.Rank's value of the Pair, when total number Rank of 7 Card is 6, and have 2 Card of the same Rank, else null
     */
    public Card.Rank containPairs(Card[] in){
        Set<Card.Rank> rankSet = new HashSet<>();
        for(Card a : in){
            rankSet.add(a.getRank());
        }
        Arrays.sort(in);
        for(int i = 0; i < 6; i++){
            if(rankSet.size() == 6 && in[i].getRank() == in[i+1].getRank()) return in[i].getRank();
        }
        return null;
    }

    public Card[] pickingPairs(Card[] in, Card.Rank inRank){
        Arrays.sort(in);
        Card[] out = new Card[5];
        int count1 = 5;
        int count2 = 3;
        for(int i = 6; i > -1; i--){
            if(in[i].getRank() == inRank){
                if(count1 < 4) continue;
                count1--;
                out[count1] = in[i];
            } else {
                if(count2 < 1) continue;
                count2--;
                out[count2] = in[i];
            }
        }
        return out;
    }

    /**
     * if TwoPair is the best option
     * @param in 7 total Card array
     * @return Card.Rank's values of the two Pair in array, when total number Rank of 7 Card is 5 and not a Trips
     * or when total number Rank of 7 Card is 4 and not a Quads or FullHouse , else null
     */
    public Card.Rank[] containTwoPairs(Card[] in){
        Set<Card.Rank> rankSet = new HashSet<>();
        Arrays.sort(in);
        for(Card a : in){
            rankSet.add(a.getRank());
        }

        Card.Rank[] out = new Card.Rank[2];
        int count = 0;

        if(rankSet.size() == 5 && containTrips(in) == null){
            for(int i = 6; i > 0; i--){
                if(in[i].getRank() == in[i-1].getRank()){
                    out[count] = in[i].getRank();
                    count++;
                }
            }
        }

        if(rankSet.size() == 4 && containFullHouse(in) == null && containQuads(in) == null){
            for(int i = 6; i > 0; i--){
                if(in[i].getRank() == in[i-1].getRank()){
                    out[count] = in[i].getRank();
                    count++;
                    if (count == 2) break;
                }
            }
        }
        if(count == 2){
            Arrays.sort(out);
            return out;
        }
        else return null;
    }

    public Card[] pickingTwoPairs(Card[] in, Card.Rank[] inRankArr){
        if(inRankArr == null) return null;
        Arrays.sort(in);
        Card[] out = new Card[5];
        int count1 = 5;
        int count2 = 1;
        for(int i = 6; i > -1; i--){
            if(in[i].getRank() == inRankArr[0] || in[i].getRank() == inRankArr[1]){
                count1--;
                out[count1] = in[i];
                if (count1 == 1) continue;
            } else {
                if(count2 < 1) continue;
                count2--;
                out[count2] = in[i];
            }
        }
        return out;
    }

}



class PlayerComparator implements Comparator<Player>{

    @Override
    public int compare(Player o1, Player o2) {
        Card[] player1 = o1.getBestHand();
        Card[] player2 = o2.getBestHand();
        if(o1.handRanking.compareTo(o2.handRanking) != 0) return o1.handRanking.compareTo(o2.handRanking);
        if(player1[4].compareTo(player2[4]) != 0) return player1[4].compareTo(player2[4]);
        if(player1[3].compareTo(player2[3]) != 0) return player1[3].compareTo(player2[3]);
        if(player1[2].compareTo(player2[2]) != 0) return player1[2].compareTo(player2[2]);
        if(player1[1].compareTo(player2[1]) != 0) return player1[1].compareTo(player2[1]);
        if(player1[0].compareTo(player2[0]) != 0) return player1[0].compareTo(player2[0]);
        else return 0;
    }
}
