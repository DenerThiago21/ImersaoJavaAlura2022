import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoImdb {
    public List<Conteudo> extrairConteudo (String json) {

        // extrair  os dados que interessam (titulo, poster, rating)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();
        
        // popular a lista de conteudos
        for (Map<String, String> atributos : listaAtributos) {
           String titulo = atributos.get("title");
           String urlImage = atributos.get("image");
           Conteudo conteudo = new Conteudo(titulo, urlImage);

           conteudos.add(conteudo);
        }

        return conteudos;
   }
}
