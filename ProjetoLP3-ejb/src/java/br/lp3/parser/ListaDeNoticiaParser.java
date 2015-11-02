package br.lp3.parser;

import br.lp3.model.Noticia;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Classe responsável pelo parser de notícias da API
 * @author 31409695
 */
public class ListaDeNoticiaParser {

    /**
     * Executa o parse.
     * Deve-se passar uma String com o conteúdo completo de um objeto "appnews" (API Steam)
     * @param content O conteúdo com todas as notícias
     * @return Uma lista de notícias de acordo com o content
     */
    public static List<Noticia> parse(String content) {
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject obj = reader.readObject();
        JsonObject appNews = obj.getJsonObject("appnews");
        JsonArray newsItems = appNews.getJsonArray("newsitems");
        List<Noticia> noticias = new ArrayList<>();
        Noticia n = new Noticia();
        JsonObject noticiaObj = null;
        for (int i = 0; i < newsItems.size(); i++) {
            noticiaObj = newsItems.getJsonObject(i);
            long gid = Long.parseLong(noticiaObj.getString("gid"));
            String title = noticiaObj.getString("title");
            String url = noticiaObj.getString("url");
            String author = noticiaObj.getString("author");
            String contents = noticiaObj.getString("contents");
            long timestamp = noticiaObj.getJsonNumber("date").longValueExact();
            
            n = new Noticia(gid, title, url, author, contents, timestamp);
            noticias.add(n);
        }
        return noticias;
    }
}
