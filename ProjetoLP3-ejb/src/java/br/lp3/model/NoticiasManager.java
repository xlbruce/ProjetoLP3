package br.lp3.model;

import br.lp3.parser.ListaDeNoticiaParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Provê uma interface para consulta de Notícias
 *
 * @author bruce
 */
public class NoticiasManager {

    private static final String URLAPI
            = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=%d&count=%d&format=json";

    public static List<Noticia> getNoticiasDoJogo(int appId, int qtde) {
        String uri = URLAPI;
        List<Noticia> noticias = new ArrayList<>();
        try {
            URL url = new URL(String.format(uri, appId, qtde));
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            Stream<String> lines = reader.lines();
            StringBuilder strBuilder = new StringBuilder();
            lines.forEach((String t) -> {
                strBuilder.append(t);
            });

            noticias = ListaDeNoticiaParser.parse(strBuilder.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(NoticiasManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NoticiasManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noticias;
    }
    
    public static List<Noticia> getNoticiasDoJogo(String nomeJogo, int qtde) {
        ListaDeJogos jogos = new ListaDeJogos();
        Jogo jogo = jogos.getJogo(nomeJogo);
        return getNoticiasDoJogo(jogo.getAppid(), qtde);
    }
}
