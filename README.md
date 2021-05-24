# PA Project
Backgammon in Java

Aceasta aplicatie constituie proiectul final pentru materia Programare Avansata.


Proiectul are o parte din logica jocului de Backgammon, plus o reprezentare grafica a tablei de joc cu piesele aferente fiecarui jucator.

![img.png](img.png)

Aplicatia se bazeaza pe comunicarea in retea, cu ajutorul socketurilor.

Serverul contine clase folosite pentru a permite conectarea din partea a 2 clienti simultan si un handler pentru fiecare thread de client.
De asemenea, in folderul aferent serverului putem gasi clase responsabile de logica tablei, printre care amintim: Player, Dice, Table.

Deoarece cei doi playeri vor juca in fire de executie diferite, pentru a partaja tabla de joc se va folosi un singleton iar comunicarea dintre client si server va avea ca scop trimiterea/primirea tablei actualizate.
	
Clientul contine, pe langa clasa aferenta conectarii la server, clase pentru realizarea mutarilor (TableMovesInside, TableMovesOutside), clase utilitare pentru crearea logicii tablei (GameLogic, Table, Dice).
De asemenea contine si elemente de Graphical User Interface: tabla de joc, piese, buton de generare zaruri si de realizare a mutarilor, boxuri de confirmare parasire joc.
