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
        this.setName(name);
        this.position = position;
        for (SkillStatistic skillStatistic: subjectsList
             ) {
            if (skillStatistic.getClass() == ServeStatistic.class){
                setServeStatistic(new ServeStatistic (skillStatistic));
            }
            if (skillStatistic.getClass() == ReceptionStatistic.class){
                setReceptionStatistic(new ReceptionStatistic (skillStatistic));
            }
            if (skillStatistic.getClass() == SetStatistic.class){
                setSetStatistic(new SetStatistic (skillStatistic));
            }
            if (skillStatistic.getClass() == AttackStatistic.class){
                setAttackStatistic(new AttackStatistic (skillStatistic));
            }
            if (skillStatistic.getClass() == BlockStatistic.class){
                setBlockStatistic(new BlockStatistic (skillStatistic));
            }
            if (skillStatistic.getClass() == DigStatistic.class){
                setDigStatistic(new DigStatistic (skillStatistic));
            }
        }
    }

    public void addServeValue(String value){
        getServeStatistic().addSkill(value);
    }

    public void addReceptionValue(String value){
        getReceptionStatistic().addSkill(value);
    }

    public void addSetValue(String value){
        getSetStatistic().addSkill(value);
    }

    public void addAttackValue(String value){
        getAttackStatistic().addSkill(value);
    }

    public void addBlockValue(String value){
        getBlockStatistic().addSkill(value);
    }

    public void addDigValue(String value){
        getDigStatistic().addSkill(value);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServeStatistic getServeStatistic() {
        return serveStatistic;
    }

    public void setServeStatistic(ServeStatistic serveStatistic) {
        this.serveStatistic = serveStatistic;
    }

    public ReceptionStatistic getReceptionStatistic() {
        return receptionStatistic;
    }

    public void setReceptionStatistic(ReceptionStatistic receptionStatistic) {
        this.receptionStatistic = receptionStatistic;
    }

    public SetStatistic getSetStatistic() {
        return setStatistic;
    }

    public void setSetStatistic(SetStatistic setStatistic) {
        this.setStatistic = setStatistic;
    }

    public AttackStatistic getAttackStatistic() {
        return attackStatistic;
    }

    public void setAttackStatistic(AttackStatistic attackStatistic) {
        this.attackStatistic = attackStatistic;
    }

    public BlockStatistic getBlockStatistic() {
        return blockStatistic;
    }

    public void setBlockStatistic(BlockStatistic blockStatistic) {
        this.blockStatistic = blockStatistic;
    }

    public DigStatistic getDigStatistic() {
        return digStatistic;
    }

    public void setDigStatistic(DigStatistic digStatistic) {
        this.digStatistic = digStatistic;
    }
}
