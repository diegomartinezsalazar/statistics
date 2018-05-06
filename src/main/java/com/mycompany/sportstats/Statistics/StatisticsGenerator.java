package com.mycompany.sportstats.Statistics;

import com.mycompany.sportstats.BBDD.BBDD;
import com.mycompany.sportstats.Statistics.Export.ExportToExcel;
import com.mycompany.sportstats.Team.Enum.Position;
import com.mycompany.sportstats.Team.Match.Jugada;
import com.mycompany.sportstats.Team.Match.Match;
import com.mycompany.sportstats.Team.Match.Set;
import com.mycompany.sportstats.Team.Match.Skill.*;
import com.mycompany.sportstats.Team.Player;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsGenerator {
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<SkillStatistic> subjectsList = new ArrayList<>();
    ServeStatistic serveStatistic;
    ReceptionStatistic receptionStatistic;
    SetStatistic setStatistic;
    AttackStatistic attackStatistic;
    BlockStatistic blockStatistic;
    DigStatistic digStatistic;
    BBDD database = new BBDD();


    public StatisticsGenerator(){
        loadValues();
    }

    public void loadBaseData(){

    }

    public void loadPlayers (String teamName){
        for (Player player: database.readPlayersTeam(teamName)
             ) {
            player.setSubjectStatistics(subjectsList);
            players.add (player);
        }
    }

    public void loadValues (){
        openConnectionBBDD();
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
        database.openMSSQLSERVERconnection();
    }

    public void matchTreatment (Match match){
        int movementPlayer = 0;
        Player player;

        // Carga jugadores
        loadPlayers(match.getNuestroEquipo().getNombre());

        for (Set set: match.getSets()
             ) {
            // Recorro los sets y, en cada uno, las jugadas
            for (Jugada jugada: set.getJugadas()
                 ) {
                for (Object movement: jugada.getMovimientos()
                     ) {
                    //Si el movimiento extiende de Skill, se lo sumo al jugador
                    if (Saque.class == movement.getClass()){
                        movementPlayer = ((Saque)movement).getPlayer();
                        player = playerWithNumber(movementPlayer);
                        player.addServeValue(((Saque) movement).getValue());
                    }
                    if (Recepcion.class == movement.getClass()){
                        movementPlayer = ((Recepcion)movement).getPlayer();
                        player = playerWithNumber(movementPlayer);
                        player.addReceptionValue(((Recepcion) movement).getValue());
                    }
                    if (Colocacion.class == movement.getClass()){
                        movementPlayer = ((Colocacion)movement).getPlayer();
                        player = playerWithNumber(movementPlayer);
                        player.addSetValue(((Colocacion) movement).getValue());
                    }
                    if (Remate.class == movement.getClass()){
                        movementPlayer = ((Remate)movement).getPlayer();
                        player = playerWithNumber(movementPlayer);
                        player.addAttackValue(((Remate) movement).getValue());
                    }
                    if (Bloqueo.class == movement.getClass()){
                        movementPlayer = ((Bloqueo)movement).getPlayer();
                        player = playerWithNumber(movementPlayer);
                        player.addBlockValue(((Bloqueo) movement).getValue());
                    }
                    if (Defensa.class == movement.getClass()){
                        movementPlayer = ((Defensa)movement).getPlayer();
                        player = playerWithNumber(movementPlayer);
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

    public void exportToExcel (Match match){
        try {
            ExportToExcel exportToExcel = new ExportToExcel(players, match);

            exportToExcel.ExportToExcelFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
