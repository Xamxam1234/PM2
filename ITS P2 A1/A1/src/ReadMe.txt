###--------------------\\\vvv///--------------------
### CHECK-IN TOGGLE --->>> X <<<--- CHECK-IN TOGGLE
###--------------------///^^^\\\--------------------
################################################################################
###
###     P2 s20w ITS2 Aufgabenzettel 1 -> A1 : Poker-Template
###     ===================================
###



"Zentrales Repository":
=======================
PRIVATE!
VCS: git@git.HAW-Hamburg.de:shf/Px/LabExercise/XOX_Poker_Distr[.git]





Information
===========

Template für die Pokeraufgabe


Klasse(n):              Aufgabe/Sinn/Zweck:

DummyForYourSolution    Eine "Wrapper-Klasse" die die "gewachsene Loesung" adaptiert und das eingeforderte Interface
                        (test.)GameAnalyzer unterstuezt. Dies ist noetig, damit der TestFrame die Loesung akzeptiert.

TestFrame               Die TestSuite. Sie testet mit "Zufalls-Unterstuetzung". Die Reihenfolge der einzelnen Tests
                        sowie deren Detail-Auspraegung variieren mit jedem Durchlauf. Beim ersten Fehler wird der Test
                        abgebrochen. Wegen den "Zufalls-Effekten" ist eine Reproduzierbarkeit des Tests NICHT gegeben.

testSupport/...         (Hilfs-)Klassen, die nicht verstanden werden muessen/sollen. (Security by obscurity ;-)
                        Ihre Existenz ist jedoch zwingend erforderlich.
                        Die in testSupport enthaltenen Dinge duerfen WEDER direkt NOCH indirekt modifiziert werden.



Ausgabe:
========

Im Fehlerfall:

Internal Code: <NR>     NR  =  Current Test-ID
                        Hilft im "kritischen" Fall den konkreten Test zu identifizieren. Wegen der
                        "Zufalls-Unterstuetzung" ist es dem Test selbst nicht anzusehen.

                        
Im Erfolgsfall:

Internal Code: <...>    [Current Test-ID];[time]/[ExecutedTestCouNT]->[time/ExecutedTestCouNT}
                        ID des letzten Tests; Zeitbedarf in ns / Anzahl der durchgeführten Tests -> durchschnittliche Zeit pro Test
