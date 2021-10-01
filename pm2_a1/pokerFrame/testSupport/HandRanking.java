package pokerFrame.testSupport;


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
public enum HandRanking {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    TRIPS,
    STRAIGHT,
    FLUSH,      
    FULL_HOUSE,
    QUADS,
    STRAIGHT_FLUSH;
    
    
    
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
    
}//enum
