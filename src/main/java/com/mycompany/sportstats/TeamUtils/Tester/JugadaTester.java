package com.mycompany.sportstats.TeamUtils.Tester;

import java.util.ArrayList;
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
    private static JugadaTester jugadaTester;

    public static JugadaTester getJugadaTester () {

        if (jugadaTester==null) {
            jugadaTester=new JugadaTester();
        }
        return jugadaTester;
    }

    private JugadaTester (){
        jugadaStart = new ArrayList<>();
        jugadaStart.add(Recepcion.class);
        jugadaStart.add(Saque.class);
        jugadaStart.add(ErrorContrario.class);
        jugadaStart.add(PuntoContrario.class);
        jugadaStart.add(Tiempo.class);

        jugadaEnd = new ArrayList<>();
        jugadaEnd.add(ErrorContrario.class);
        jugadaEnd.add(PuntoContrario.class);
        jugadaEnd.add(Tiempo.class);
    }

    private boolean testJugadaStart (Jugada jugada){
        return isJugadaStart(jugada.getMovimientos().get(0));
    }

    private boolean testJugadaEnd (Jugada jugada){
        return isJugadaEnd(jugada.getMovimientos().get(jugada.getMovimientos().size()-1));
    }

    private boolean isJugadaStart(Object movimiento){
        return jugadaStart.contains(movimiento.getClass());
    }

    private boolean isJugadaEnd(Object movimiento){
        return isFinishMovement(movimiento);
    }

    private boolean isFinishMovement (Object mov){
        if (jugadaEnd.contains(mov.getClass())){
            return true;
        } else if ((mov instanceof Skill) &&
                (("--".equals(((Skill) mov).getValue())) || ("++".equals(((Skill) mov).getValue())))){
            return true;
        }
        return false;
    }

    public void isJugadaCorrect (Jugada jugada){
        if (!testJugadaStart(jugada)){
            System.out.println("Error comienzo en la jugada: " + jugada);
        }
        if (!testJugadaEnd(jugada)){
            System.out.println("Error final en la jugada: " + jugada);
        }
    }
}
