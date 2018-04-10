package com.mycompany.sportstats.Statistics;

import java.util.ArrayList;

public class ServeStatistic extends SkillStatistic {
    public ServeStatistic (ArrayList<String> values){
        super(values);
    }

    public ServeStatistic (SkillStatistic skillStatistic){
        super(skillStatistic);
    }
}
