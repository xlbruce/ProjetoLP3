package br.lp3.model;

import java.util.List;

/**
 * Prove uma interface para consulta de jogos
 * @author bruce
 */
public class JogoManager {
    
    static ListaDeJogos jogos = new ListaDeJogos();
    
    public static List<Jogo> getJogos() {
        return jogos.getAllJogos();
    }
    
    public static Jogo getJogo(String name) {
        return jogos.getJogo(name);
    }
    
    public static Jogo getJogo(int appId) {
        return jogos.getJogo(appId);
    }    
}
