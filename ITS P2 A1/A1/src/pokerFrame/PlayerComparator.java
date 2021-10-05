package pokerFrame;

import java.util.Comparator;

import cards.Card;

class PlayerComparator implements Comparator<Player>{

	@Override
    public int compare(Player player1, Player player2) {
        Card[] c1 = player1.getBestHand();
        Card[] c2 = player2.getBestHand();
        if (player2.getHandRanking().compareTo(player1.getHandRanking()) != 0)
        	return player2.getHandRanking().compareTo(player1.getHandRanking());
     
	        for (int i = 0; i < 5; i++) {
	        	if (c2[i].compareTo(c1[i]) != 0)
	        		return c2[i].compareTo(c1[i]);
	        
        }
        return 0;
    }

}

