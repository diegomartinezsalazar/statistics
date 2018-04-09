package com.mycompany.sportstats.Team;

import com.mycompany.sportstats.Team.Enum.Position;
import com.mycompany.sportstats.Statistics.*;

import java.util.ArrayList;

public class Player {
    private int number;
    private Position position;
    private String name;
    private ServeStatistic serveStatistic;
    private ReceptionStatistic receptionStatistic;
    private SetStatistic setStatistic;
    private AttackStatistic attackStatistic;
    private BlockStatistic blockStatistic;
    private DigStatistic digStatistic;

    public Player (){}

    public Player (int number, Position position, String name, ArrayList<SkillStatistic> subjectsList){
        this.setNumber(number);
        this.name = name;
        this.position = position;
        for (SkillStatistic skillStatistic: subjectsList
             ) {
            if (skillStatistic.getClass() == serveStatistic.getClass()){
                serveStatistic = (ServeStatistic) skillStatistic;
            }
            if (skillStatistic.getClass() == receptionStatistic.getClass()){
                receptionStatistic = (ReceptionStatistic) skillStatistic;
            }
            if (skillStatistic.getClass() == setStatistic.getClass()){
                setStatistic = (SetStatistic) skillStatistic;
            }
            if (skillStatistic.getClass() == attackStatistic.getClass()){
                attackStatistic = (AttackStatistic) skillStatistic;
            }
            if (skillStatistic.getClass() == blockStatistic.getClass()){
                blockStatistic = (BlockStatistic) skillStatistic;
            }
            if (skillStatistic.getClass() == digStatistic.getClass()){
                digStatistic = (DigStatistic) skillStatistic;
            }
        }
    }

    public void addServeValue(String value){
        serveStatistic.addSkill(value);
    }

    public void addReceptionValue(String value){
        receptionStatistic.addSkill(value);
    }

    public void addSetValue(String value){
        setStatistic.addSkill(value);
    }

    public void addAttackValue(String value){
        attackStatistic.addSkill(value);
    }

    public void addBlockValue(String value){
        blockStatistic.addSkill(value);
    }

    public void addDigValue(String value){
        digStatistic.addSkill(value);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
