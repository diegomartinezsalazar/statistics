package com.mycompany.sportstats.TeamUtils.Tester;

import java.util.List;

import com.mycompany.sportstats.Team.Match.ErrorContrario;
import com.mycompany.sportstats.Team.Match.Jugada;
import com.mycompany.sportstats.Team.Match.PuntoContrario;
import com.mycompany.sportstats.Team.Match.Skill.Recepcion;
import com.mycompany.sportstats.Team.Match.Skill.Saque;
import com.mycompany.sportstats.Team.Match.Skill.Skill;
import com.mycompany.sportstats.Team.Match.Tiempo;

public class JugadaTester {
    private List<Class> jugadaStart;
    private List<Class> jugadaEnd;

    public JugadaTester (){
        jugadaStart.add(Recepcion.class);
        jugadaStart.add(Saque.class);
        jugadaStart.add(ErrorContrario.class);
        jugadaStart.add(PuntoContrario.class);
        jugadaStart.add(Tiempo.class);

        jugadaEnd.add(ErrorContrario.class);
        jugadaEnd.add(PuntoContrario.class);
        jugadaEnd.add(Tiempo.class);
    }

    public boolean testJugadaStart (Jugada jugada){
        return isJugadaStart(jugada.getMovimientos().get(0));
    }

    private boolean isJugadaStart(Object movimiento){
        return jugadaStart.contains(movimiento.getClass());
    }

    private boolean isJugadaEnd(Object movimiento){
        return jugadaEnd.contains(movimiento.getClass()) || movimiento;
    }

    private boolean isFinishMovement (Object mov){
        if (jugadaEnd.contains(mov.getClass())){

        } else if (mov instanceof Skill){

        }
    }
}
