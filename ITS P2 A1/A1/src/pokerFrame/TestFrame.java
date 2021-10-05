package pokerFrame;


import java.time.Clock;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import pokerFrame.testSupport.*;


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
public class TestFrame {
    
    //@ChunkPreamble ( lastModified="2018/03/29", lastModifiedBy="Michael Schaefers" )
    public static void main( final String... unused ){
        
        System.out.printf( "\n\n\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "### Informationen zum Environment:\n" );
        System.out.printf( "### ==============================\n" );
        System.out.printf( "###\n" );
        System.out.printf( "### Java:     %s bzw. %s\n",                System.getProperty( "java.specification.version" ), System.getProperty( "java.version" ) );
        System.out.printf( "### O.-P.:    %s\n",                        new Object().getClass().getPackage() );
        System.out.printf( "###\n" );
        System.out.printf( "### %s  is home of \".class\"-files\n",     TestFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath() );
        System.out.printf( "### %s  is running\n",                      cid.getClass().getEnclosingClass().getCanonicalName() );
        System.out.printf( "###\n" );
        System.out.printf( "###\n" );
        System.out.printf( "### STARTING @ %s\n",                       DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone( ZoneId.of( "Europe/Berlin") ).format( Clock.systemUTC().instant() ) );
        System.out.printf( "\n" );
        System.out.flush();
        
        final GameAnalyzer ga = new DummyForYourSolution();
        final TestExecutor te = new FieldSimulator( ga );
        te.execute();
        
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "### THE END @ %s", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone( ZoneId.of( "Europe/Berlin") ).format( Clock.systemUTC().instant() ) );
        
    }//method()
    
    
    
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
