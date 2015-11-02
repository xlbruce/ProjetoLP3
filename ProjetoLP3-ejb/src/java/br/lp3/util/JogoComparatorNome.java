package br.lp3.util;

import br.lp3.model.Jogo;
import java.util.Comparator;

/**
 * Essa classe é usada para facilitar a busca de jogos por nome na ListaDeJogos. * 
 * @author bruce
 */
public class JogoComparatorNome implements Comparator<Jogo> {

    @Override
    public int compare(Jogo o1, Jogo o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
