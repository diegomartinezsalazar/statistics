package com.mycompany.sportstats.Statistics;

import com.mycompany.sportstats.BBDD.BBDD;
import com.mycompany.sportstats.Team.Enum.Position;
import com.mycompany.sportstats.Team.Match.Jugada;
import com.mycompany.sportstats.Team.Match.Match;
import com.mycompany.sportstats.Team.Match.Set;
import com.mycompany.sportstats.Team.Match.Skill.*;
import com.mycompany.sportstats.Team.Player;

import java.util.ArrayList;

public class StatisticsGenerator {
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<SkillStatistic> subjectsList;
    ServeStatistic serveStatistic;
    ReceptionStatistic receptionStatistic;
    SetStatistic setStatistic;
    AttackStatistic attackStatistic;
    BlockStatistic blockStatistic;
    DigStatistic digStatistic;
    BBDD database = new BBDD();

    public StatisticsGenerator(){
        loadValues();
        loadPlayers();
    }

    public void loadBaseData(){

    }

    public void loadPlayers (){
        // Aquí añado a los jugadores aunque los debería leer de la base de datos
        Player player1 = new Player(1, Position.CUATRO, "Raquel", subjectsList);
        Player player2 = new Player(2, Position.CENTRAL, "Marta", subjectsList);
        Player player3 = new Player(3, Position.COLOCADOR, "Esther", subjectsList);
        Player player6 = new Player(6, Position.LÍBERO, "Hansie", subjectsList);
        Player player7 = new Player(7, Position.OPUESTO, "Tami", subjectsList);
        Player player8 = new Player(8, Position.COLOCADOR, "Maddi", subjectsList);
        Player player9 = new Player(9, Position.CENTRAL, "Chris", subjectsList);
        Player player10 = new Player(10, Position.CUATRO, "Irene", subjectsList);
        Player player11 = new Player(11, Position.LÍBERO, "Elena", subjectsList);
        Player player12 = new Player(12, Position.CUATRO, "Eli", subjectsList);
        Player player13 = new Player(13, Position.CENTRAL, "Andrea", subjectsList);
        Player player14 = new Player(14, Position.OPUESTO, "Sofí", subjectsList);
        Player player15 = new Player(15, Position.CUATRO, "Vane", subjectsList);
        Player player19 = new Player(19, Position.CENTRAL, "Luque", subjectsList);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);
        players.add(player11);
        players.add(player12);
        players.add(player13);
        players.add(player14);
        players.add(player15);
        players.add(player19);
    }

    public void loadValues (){
        ArrayList values = database.readAllValuesFromBBDD();
        serveStatistic = new ServeStatistic(values);
        receptionStatistic = new ReceptionStatistic(values);
        setStatistic = new SetStatistic(values);
        attackStatistic = new AttackStatistic(values);
        blockStatistic = new BlockStatistic(values);
        digStatistic = new DigStatistic(values);

        subjectsList.add(serveStatistic);
        subjectsList.add(receptionStatistic);
        subjectsList.add(setStatistic);
        subjectsList.add(attackStatistic);
        subjectsList.add(blockStatistic);
        subjectsList.add(digStatistic);
    }

    public void openConnectionBBDD(){
        database = new BBDD();
        database.openconnection();
    }

    public void matchTreatment (Match match){
        int movementPlayer = 0;
        Player player;

        for (Set set: match.getSets()
             ) {
            // Recorro los sets y, en cada uno, las jugadas
            for (Jugada jugada: set.getJugadas()
                 ) {
                for (Object movement: jugada.getMovimientos()
                     ) {
                    player = playerWithNumber(((Skill) movement).getPlayer());
                    //Si el movimiento extiende de Skill, se lo sumo al jugador
                    if (Saque.class == movement.getClass()){
                        movementPlayer = ((Saque)movement).getPlayer();
                        //player = playerWithNumber(movementPlayer);
                        player.addServeValue(((Saque) movement).getValue());
                    }
                    if (Recepcion.class == movement.getClass()){
                        movementPlayer = ((Recepcion)movement).getPlayer();
                        //player = playerWithNumber(movementPlayer);
                        player.addReceptionValue(((Recepcion) movement).getValue());
                    }
                    if (Colocacion.class == movement.getClass()){
                        movementPlayer = ((Colocacion)movement).getPlayer();
                        player.addSetValue(((Colocacion) movement).getValue());
                    }
                    if (Remate.class == movement.getClass()){
                        movementPlayer = ((Remate)movement).getPlayer();
                        player.addAttackValue(((Remate) movement).getValue());
                    }
                    if (Bloqueo.class == movement.getClass()){
                        movementPlayer = ((Bloqueo)movement).getPlayer();
                        player.addBlockValue(((Bloqueo) movement).getValue());
                    }
                    if (Defensa.class == movement.getClass()){
                        movementPlayer = ((Defensa)movement).getPlayer();
                        player.addDigValue(((Defensa) movement).getValue());
                    }
                }
            }
        }
    }

    public Player playerWithNumber (int playerNumber){
        Player player = new Player();

        for (Player teamPlayer: players
             ) {
            if (teamPlayer.getNumber() == playerNumber){
                player = teamPlayer;
            }
        }

        return player;
    }

    //public void printMatchStatistics ()
}
