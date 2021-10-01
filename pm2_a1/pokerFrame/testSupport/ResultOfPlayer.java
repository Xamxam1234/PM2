package pokerFrame.testSupport;


import cards.*;


//@ClassPreamble (
//    vcs             = "git@git.HAW-Hamburg.de:shf/Px/LabExercise/XOX_Poker_Distr",
//    author          = "Michael Schaefers",
//    contact         = "Michael.Schaefers@HAW-Hamburg.de",
//    organization    = "Dept.Informatik; HAW Hamburg",
//    date            = "2012/11/19",
//    version         = "3.10",
//    note            = "release for WS20/21 ;  1st release WS08/09",
//    lastModified    = "2020/10/25",
//    lastModifiedBy  = "Michael Schaefers",
//    reviewers       = ( "none" )
//)
public class ResultOfPlayer {
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    final HandRanking handRank;
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    final Card[] card;
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    int position;                                                               // 1=best, 5=worst(of 5)
    
    
    
    
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    public ResultOfPlayer( final int pos,  final HandRanking hrk,  final Card[] card ){
        this.position = pos;
        this.handRank = hrk;
        this.card     = card.clone();
    }//constructor()
    //
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    public ResultOfPlayer( final HandRanking hrk,  final Card[] card ){ this( Integer.MAX_VALUE, hrk, card ); }
    
    
    
    
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    public int setPosition( final int newPositionValue ){
        final int oldPositionValue = position;
        position = newPositionValue;
        return oldPositionValue;
    }//method()
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    @Override
    public ResultOfPlayer clone(){ return new ResultOfPlayer( this.position, this.handRank, card.clone() ); }
    
    
    
    //___under_development______________________________________________________
    //@ChunkPreamble ( lastModified="2014/05/28", lastModifiedBy="Michael Schaefers" )
    static final private CID cid = new CID();
    //
    //@ChunkPreamble ( lastModified="2014/05/28", lastModifiedBy="Michael Schaefers" )
    static final private class CID {                                            // Class IDentification
        //@ChunkPreamble ( lastModified="2014/05/28", lastModifiedBy="Michael Schaefers" )
        private CID(){
            final Registration registration = Registration.getInstance();
            registration.registrate( getClass() );
        }//constructor()
    }//class
    
}//class
