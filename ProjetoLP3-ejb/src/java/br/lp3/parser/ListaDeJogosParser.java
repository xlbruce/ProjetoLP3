package br.lp3.parser;

import br.lp3.model.Jogo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author bruce
 */
public class ListaDeJogosParser {

    public static List<Jogo> parseJogos(File jsonFile) {
        JsonReader reader = null;
        List<Jogo> jogos = new ArrayList<>();

        try {
            reader = Json.createReader(new FileReader(jsonFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListaDeJogosParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (reader != null) {
            JsonObject obj = reader.readObject();
            JsonObject appList = obj.getJsonObject("applist");
            JsonArray apps = appList.getJsonArray("apps");
            for (int i = 0; i < apps.size(); i++) {
                JsonObject tmp = apps.getJsonObject(i);
                int appId = tmp.getInt("appid");
                String name = tmp.getString("name");
                Jogo j = new Jogo(appId, name);
                jogos.add(j);
            }
        }
        reader.close();
        return jogos;
    }

    private static String getContent(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Stream<String> lines = reader.lines();
        String out = "";
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            out += iterator.next();
        }
        try {
            reader.close();
        } catch (IOException ex) {
            System.err.println("Erro ao fechar o leitor");
        }
        return out;
    }
}
