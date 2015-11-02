package br.lp3.model;

import br.lp3.parser.ListaDeJogosParser;
import br.lp3.util.JogoComparatorId;
import br.lp3.util.JogoComparatorNome;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruce
 */
public class ListaDeJogos {

    List<Jogo> listaDeJogos;

    static final String LISTADEJOGOS
            = "etc/games_list.json";

    public ListaDeJogos() {
        init();
    }

    private void init() {
        File f = new File(LISTADEJOGOS);
        if (!f.exists()) {
            //Se o arquivo não existir, cria um novo e adiciona o conteúdo da API
            try {
                f.createNewFile();
                File content = getGamesFromServer();
                Files.copy(Paths.get(content.getAbsolutePath()),
                        Paths.get(f.getAbsolutePath()),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                throw new RuntimeException("Erro ao criar novo arquivo");
            }
        }
        listaDeJogos = ListaDeJogosParser.parseJogos(f);
    }

    /**
     * Recupera o objeto json diretamente da API da Steam
     *
     * @param uri
     * @return
     */
    private File getGamesFromServer() {
        String uri = "http://api.steampowered.com/ISteamApps/GetAppList/v2";
        //Conteudo Json
        File fileContent = new File("$tmp.json");        
        try {
            fileContent.createNewFile();
            URL url = new URL(uri);
            InputStream openStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(openStream));
            String s = null;
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileContent));
            while ((s = reader.readLine()) != null) {
                writer.append(s);
            }
            writer.flush();
            writer.close();
            reader.close();
            openStream.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ListaDeJogos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaDeJogos.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return fileContent;
    }
    
    /**
     * Pesquisa um jogo pelo seu AppId
     *
     * @param appId o id do jogo
     * @return O jogo encontrado, ou null
     */
    public Jogo getJogo(int appId) {
        Jogo j = new Jogo();
        j.setAppid(appId);

        JogoComparatorId comparador = new JogoComparatorId();
        Collections.sort(listaDeJogos, comparador);
        int result = Collections.binarySearch(listaDeJogos, j, comparador);
        if (result < 0) {
            return null;
        } else {
            return listaDeJogos.get(result);
        }
    }

    /**
     * Pesquisa um jogo pelo seu nome exato
     *
     * @param name O nome exato do jogo (acentuação e caracteres como "-")
     * @return O jogo encontrado, ou null
     */
    public Jogo getJogo(String name) {
        Jogo j = new Jogo();
        j.setName(name);

        JogoComparatorNome comparador = new JogoComparatorNome();
        Collections.sort(listaDeJogos, comparador);
        int result = Collections.binarySearch(listaDeJogos, j, comparador);
        if (result < 0) {
            return null;
        } else {
            return listaDeJogos.get(result);
        }
    }

    /**
     * Retorna uma lista com todos os jogos cadastrados
     *
     * @return
     */
    public List<Jogo> getAllJogos() {
        return listaDeJogos;
    }
}
