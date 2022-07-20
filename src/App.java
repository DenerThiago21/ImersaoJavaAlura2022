import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    // link da api: https://api.mocki.io/v2/549a5d8b
    // link api2: https://api.mocki.io/v2/549a5d8b/NASA-APOD
    public static void main(String[] args) throws Exception {
        // api do imdb
        String url = "https://api.mocki.io/v2/549a5d8b";
        ExtratorConteudo extrator = new ExtratorConteudoImdb();

        // api da nasa
        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        // ExtratorConteudo extrator = new ExtratorConteudoNasa();


        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir os dados extraídos
        List<Conteudo> conteudos = extrator.extrairConteudo(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        
        for (Conteudo conteudo : conteudos) {
            
            String nomeArquivo = conteudo.getTitulo() + ".png";

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("{\n 'title' : '" + conteudo.getTitulo() + "',\n" +
                                " 'image' : '" + conteudo.getUrlImage() + "',\n" + "},");
                                //" 'imdbRating' : '" + imdb + "' \n},");
        }

    }
}
