import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    // link da api: https://api.mocki.io/v2/549a5d8b
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes do imdb
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair  os dados que interessam (titulo, poster, rating)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);

        // exibir os dados extraídos
        for (Map<String,String> filme : listaFilmes) {
            System.out.println("{\n 'title' : '" + filme.get("title") + "',\n" +
                                " 'image' : '" + filme.get("image") + "',\n" +
                                " 'imdbRating' : '" + filme.get("imDbRating") + "' \n},");
        }

    }
}
