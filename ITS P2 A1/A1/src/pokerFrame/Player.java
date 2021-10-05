package pokerFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cards.Card;
import cards.Card.Rank;
import cards.Card.Suit;
import pokerFrame.testSupport.HandRanking;

public class Player {
	Card[] totalCard;
	HandRanking handRanking;
	Card[] bestHand;
	int position;
	
	public Player() {
		totalCard = new Card[7];
		bestHand = new Card[5];
		position = 0;
	}
	
	public Card[] getBestHand() {
		switch (rateHand(totalCard)) {
		case STRAIGHT_FLUSH: bestHand = pickStraightFlush(totalCard); break;
		case QUADS: 		 bestHand = pickQuads(totalCard); 		  break;
		case FULL_HOUSE:     bestHand = pickFullHouse(totalCard); 	  break;
		case FLUSH: 		 bestHand = pickFlush(totalCard); 		  break;
		case STRAIGHT: 		 bestHand = pickStraight(totalCard); 	  break;
		case TRIPS: 		 bestHand = pickTrips(totalCard); 		  break;
		case TWO_PAIR: 		 bestHand = pickTwoPair(totalCard); 	  break;
		case ONE_PAIR: 		 bestHand = pickOnePair(totalCard); 	  break;
		default: 			 bestHand = pickHighCard(totalCard); 	  break;
		};
		return bestHand;
	}
	
	public HandRanking getHandRanking() {
		handRanking = rateHand(totalCard);
		return handRanking;
	}
	
	public int positionRanking(Player in, Player player1, Player player2, Player player3, Player player4) {
		List<Player> ranking = new ArrayList<>();
        ranking.add(in);
        ranking.add(player1);
        ranking.add(player2);
        ranking.add(player3);
        ranking.add(player4);
        Collections.sort(ranking,new PlayerComparator());
        position = 1+ranking.indexOf(in);
		return position;
	}
	
	public HandRanking rateHand(Card[] totalCard) {
		if(pickStraightFlush(totalCard) != null) return HandRanking.STRAIGHT_FLUSH;
		if(pickQuads(totalCard) != null) return HandRanking.QUADS;
		if(pickFullHouse(totalCard) != null) return HandRanking.FULL_HOUSE;
		if(pickFlush(totalCard) != null) return HandRanking.FLUSH;
		if(pickStraight(totalCard) != null) return HandRanking.STRAIGHT;
		if(pickTrips(totalCard) != null) return HandRanking.TRIPS;
		if(pickTwoPair(totalCard) != null) return HandRanking.TWO_PAIR;
		if(pickOnePair(totalCard) != null) return HandRanking.ONE_PAIR;
		return HandRanking.HIGH_CARD;
	}
	
	public Card[] joinHand(Card[] player, Card[] community) {
		this.totalCard = new Card[]{player[0],player[1],community[0],community[1],community[2],community[3],community[4]};
		Arrays.sort(totalCard);
		bestHand = pickHighCard(totalCard);
		handRanking = HandRanking.HIGH_CARD;
		return totalCard;
	}
	
	
	public Card[] pickStraightFlush(Card[] totalCard) {
		Map<Suit, List<Card>> suitMap = new HashMap<>();
		
		for (int i = totalCard.length-1; i > -1; i--) {
			Suit suit = totalCard[i].getSuit();
			List<Card> list = suitMap.get(suit);
			
			if (list == null) {
				list = new ArrayList<>();
				suitMap.put(suit, list);
			}//if
			
			list.add(totalCard[i]);
		}//loop
		
		for (Suit suit : Suit.values()) {
			List<Card> list = suitMap.get(suit);
			if (list == null) continue;
			if (list.size() >= 5) {
				List<Rank> rankList = new ArrayList<>();
				for (Card card : list) rankList.add(card.getRank());
				if (rankList.containsAll(Arrays.asList(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)) &&
						!rankList.contains(Rank.SIX)) {
					Card[] resu = new Card[5];
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < list.size(); j++) {
							if (i < 4) {
								if (list.get(j).getRank().value() == 5-i) {
									resu[i] = list.get(j);
									break;
								}
							} else {
								if (list.get(j).getRank() == Rank.ACE) {
									resu[i] = list.get(j);
									break;
								}
							}
						}
					}
					return resu;
				}
				
				int dec = 0;
				for (int i = 0; i < list.size() - 1; i++) {
					if (list.get(i).compareTo(list.get(i+1)) > 1) {
						list.remove(i);
						i = -1; dec = 0;
					} 
					else {
						dec++;
						if (dec == 4) break;
					}
				}
				if (list.size() >= 5) {
					Card[] resu = new Card[5];
					for (int i = 0; i < resu.length; i++)
						resu[i] = list.get(i);
					return resu;
				}
			}
		}
		return null;
	}
	
	
	public Card[] pickStraight(Card[] totalCard) {
		List<Rank> rankList = new ArrayList<>();
		for (Card card : totalCard) rankList.add(card.getRank());
		if (rankList.containsAll(Arrays.asList(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)) &&
				!rankList.contains(Rank.SIX)) {
			Card[] resu = new Card[5];
			for (int i = 0; i < 5; i++) {
				for (int j = 6; j >= 0; j--) {
					if (i < 4) {
						if (totalCard[j].getRank().value() == 5-i) {
							resu[i] = totalCard[j]; break;
						}
					} else {
						if (totalCard[j].getRank() == Rank.ACE) {
							resu[i] = totalCard[j];
							break;
						}
					}
				}
			}
			return resu;
		}
		
		List<Card> list = new ArrayList<>();
		for (int i = 6; i > 0; i--) {
			if (totalCard[i].getRank().value() - totalCard[i-1].getRank().value() == 1) {
				if (!list.contains(totalCard[i])) list.add(totalCard[i]);
				list.add(totalCard[i-1]);
			}
		}		
		
		if (list.size() >= 5) {
			int dec = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i).compareTo(list.get(i+1)) > 1) {
					list.remove(i);
					i = -1; dec = 0;
				} else if (list.get(i).compareTo(list.get(i+1)) == 0) {
					list.remove(i+1);
					i = -1; dec = 0;
				} else {
					dec++;
					if (dec == 4) break;
				}
			}
			if (list.size() >= 5) {
				Card[] resu = new Card[5];
				for (int i = 0; i < resu.length; i++)
					resu[i] = list.get(i);
				return resu;
			}
		}
		
		return null;
	}//method
	
	public Card[] pickFlush(Card[] totalCard) {
		Map<Suit, List<Card>> suitMap = new HashMap<>();
		
		for (int i = totalCard.length - 1; i >= 0; i--) {
			Suit suit = totalCard[i].getSuit();
			List<Card> list = suitMap.get(suit);
			
			if (list == null) {
				list = new ArrayList<>();
				suitMap.put(suit, list);
			}//if
			
			list.add(totalCard[i]);
			
			if (list.size() == 5) {
				Card[] resu = new Card[5];
				list.toArray(resu);
				return resu;
			}//if
			
		}//loop
		
		return null;
		
	}//method
	
	public Card[] pickQuads(Card[] totalCard) {
		Map<Rank, List<Card>> rankMap = new HashMap<>();
		for (int i = totalCard.length-1; i > -1; i--) {
			Rank rank = totalCard[i].getRank();
			List<Card> list = rankMap.get(rank);
			
			if (list == null) {
				list = new ArrayList<>();
				rankMap.put(rank, list);
			}
			
			list.add(totalCard[i]);
			if (list.size() == 4) {
				Card[] resu = new Card[5];
				for (int j = 0; j < 4; j++) {
					resu[j] = list.get(j);
				}
				resu[4] = list.contains(totalCard[6]) ? totalCard[2] : totalCard[6];
				return resu;
			}
		}
		return null;
	}
	
	public Card[] pickFullHouse(Card[] totalCard) {
		Card[] resu = new Card[5];
		resu = pickTrips(totalCard);
		if (resu != null) {
			Card[] arr = new Card[4]; int i = 0;
			
			for (Card card : totalCard) {
				if (card.getRank() != resu[0].getRank()) {
					arr[i++] = card;
				}
			}
			
			Map<Rank, List<Card>> rankMap = new HashMap<>();
			for (i = 3; i >= 0; i--) {
				Rank rank = arr[i].getRank();
				List<Card> list = rankMap.get(rank);
				
				if (list == null) {
					list = new ArrayList<>();
					rankMap.put(rank, list);
				}
				list.add(arr[i]);
				
				if (list.size() == 2) {
					resu[3] = list.get(1);
					resu[4] = list.get(0);
					return resu;
				}
			}
		}
		
		return null;
	}

	public Card[] pickTrips(Card[] totalCard) {
		Map<Rank, List<Card>> rankMap = new HashMap<>();
		for (int i = totalCard.length-1; i > -1; i--) {
			Rank rank = totalCard[i].getRank();
			List<Card> list = rankMap.get(rank);
			
			if (list == null) {
				list = new ArrayList<>();
				rankMap.put(rank, list);
			}
			list.add(totalCard[i]);
			
			if (list.size() == 3) {
				Card[] resu = new Card[5];
				for (int j = 0; j < 3; j++) {
					resu[j] = list.get(j);
				}
				
				if (list.contains(totalCard[6])) {
					resu[3] = totalCard[3];
					resu[4] = totalCard[2];
				} else {
					resu[3] = totalCard[6];
					resu[4] = list.contains(totalCard[5]) ? totalCard[2] : totalCard[5];
				}
				return resu;
			}
		}
		return null;
	}
	
	public Card[] pickTwoPair(Card[] totalCard) {
		List<Card> list = new ArrayList<>();
		for (int i = 6; i > 0; i--) {
			if (totalCard[i].compareTo(totalCard[i-1]) == 0) {
				list.add(totalCard[i]);
				list.add(totalCard[i-1]);
			}
		}
		if (list.size() >= 4) {
			Card[] resu = new Card[5];
			List<Card> tmp = new ArrayList<>();
			for (int i = 0; i < 4; i++) tmp.add(list.get(i));
			for (int i = 6; i > 0; i--) {
				if(!tmp.contains(totalCard[i])) {
					tmp.add(totalCard[i]);
					break;
				}
			}
			tmp.toArray(resu);
			return resu;
		}
		return null;
	}
	
	public Card[] pickOnePair(Card[] totalCard) {
		List<Card> list = new ArrayList<>();
		for (int i = 6; i > 0; i--) {
			if (totalCard[i].compareTo(totalCard[i-1]) == 0) {
				list.add(totalCard[i]);
				list.add(totalCard[i-1]);
			}
		}
		if (list.size() >= 2) {
			Card[] resu = new Card[5];
			List<Card> tmp = new ArrayList<>();
			for (int i = 0; i < 2; i++) tmp.add(list.get(i));
			for (int i = 6; i > 0; i--) {
				if(!tmp.contains(totalCard[i])) tmp.add(totalCard[i]);
				if (tmp.size() == 5) break;	
			}
			tmp.toArray(resu);
			return resu;
		}
		return null;
	}
	
	public Card[] pickHighCard(Card[] totalCard) {
		Card[] resu = new Card[5];
		for (int i = 0; i < 5; i++) resu[i] = totalCard[6-i];
		return resu;
	}
	
}
