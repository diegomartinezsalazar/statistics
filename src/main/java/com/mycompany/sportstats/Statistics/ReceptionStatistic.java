package com.mycompany.sportstats.Statistics;

import java.util.ArrayList;

public class ReceptionStatistic extends SkillStatistic {
    public ReceptionStatistic (ArrayList<String> values){
        super(values);
    }

    public ReceptionStatistic (SkillStatistic skillStatistic){
        super(skillStatistic);
    }
}