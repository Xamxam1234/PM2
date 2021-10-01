package pokerFrame.testSupport;


import cards.Card;


//@ClassPreamble (
//    vcs             = "git@git.HAW-Hamburg.de:shf/Px/LabExercise/XOX_Poker_Distr",
//    author          = "Michael Schaefers",
//    contact         = "Michael.Schaefers@HAW-Hamburg.de",
//    organization    = "Dept.Informatik; HAW Hamburg",
//    date            = "2012/11/19",
//    version         = "3.10",
//    note            = "release for SS18 ;  1st release WS08/09",
//    note            = "release for WS20/21 ;  1st release WS08/09",
//    lastModified    = "2020/10/25",
//    reviewers       = ( "none" )
//)
public class TestCaseProto {
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    final Card[] card;
    //
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    final long id;
    //
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    final long prm;
    //
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    final String[] rop;
    
    
    
    //@ChunkPreamble ( lastModified="2014/05/25", lastModifiedBy="Michael Schaefers" )
    public TestCaseProto( final Card[] card,  final long id,  final long prm,  final String[] rop ){
        this.card = card;
        this.id = id;
        this.prm = prm;
        this.rop = rop;
    }//constructor()
    
    
    
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
