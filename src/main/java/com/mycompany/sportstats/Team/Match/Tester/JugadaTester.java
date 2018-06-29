package com.mycompany.sportstats.Team.Match.Tester;

import java.util.List;

import com.mycompany.sportstats.Team.Match.ErrorContrario;
import com.mycompany.sportstats.Team.Match.Jugada;
import com.mycompany.sportstats.Team.Match.Movement;
import com.mycompany.sportstats.Team.Match.PuntoContrario;
import com.mycompany.sportstats.Team.Match.Skill.Recepcion;
import com.mycompany.sportstats.Team.Match.Skill.Saque;
import com.mycompany.sportstats.Team.Match.Tiempo;

public class JugadaTester {
    private List<Class> jugadaStart;

    public JugadaTester (){
        jugadaStart.add(Recepcion.class);
        jugadaStart.add(Saque.class);
        jugadaStart.add(ErrorContrario.class);
        jugadaStart.add(PuntoContrario.class);
        jugadaStart.add(Tiempo.class);
    }

    public boolean testJugadaStart (Jugada jugada){
        return isJugadaStart(jugada.getMovimientos().get(0));
    }

    private boolean isJugadaStart(Object jugada){
        return jugadaStart.contains(jugada.getClass());
    }
}
