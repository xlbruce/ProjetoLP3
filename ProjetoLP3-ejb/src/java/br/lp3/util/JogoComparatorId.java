package br.lp3.util;


import br.lp3.model.Jogo;
import java.util.Comparator;


/**
 * Essa classe é usada para facilitar a busca de jogos por ID na ListaDeJogos.
 * @author bruce
 */
public class JogoComparatorId implements Comparator<Jogo> {

    @Override
    public int compare(Jogo o1, Jogo o2) {
        if (o1.getAppid() == o2.getAppid()) {
            return 0;
        }        
        return o1.getAppid() < o2.getAppid() ? -1 : 1;
    }

   
    
}
