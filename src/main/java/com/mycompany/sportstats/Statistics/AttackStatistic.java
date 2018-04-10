package com.mycompany.sportstats.Statistics;

import java.util.ArrayList;

public class AttackStatistic extends SkillStatistic {
    public AttackStatistic (ArrayList<String> values){
        super(values);
    }

    public AttackStatistic (SkillStatistic skillStatistic){
        super(skillStatistic);
    }
}