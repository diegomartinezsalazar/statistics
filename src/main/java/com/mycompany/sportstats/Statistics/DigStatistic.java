package com.mycompany.sportstats.Statistics;

import java.util.ArrayList;

public class DigStatistic extends SkillStatistic {
    public DigStatistic (ArrayList<String> values){
        super(values);
    }

    public DigStatistic (SkillStatistic skillStatistic){
        super(skillStatistic);
    }
}