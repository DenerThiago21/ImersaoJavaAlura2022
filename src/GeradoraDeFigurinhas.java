import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        // leitura da imagem
        /* ler do arquivo */
        // InputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg"));
        //String url = "https://imersao-java-apis.s3.amazonaws.com/TopMovies_4.jpg";
        /* Ler da internt */
        //InputStream inputStream = new URL(url).openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar imagem em memória com transparencia e novo tamanho
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar imagem original para nova imagem(em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte
        int fontSize = 64;
        var font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(font);

        String frase = "TOPZEIRA";

        // escrever uma frase na nova imagem
        graphics.drawString(frase, (width / 2) - frase.length(), height + 100);

        // escrever nova imagem em um arquivo
        String saida = "saida";
        File file = new File(saida);
        if(!file.exists()) {
            file.mkdirs();
        }
        ImageIO.write(newImage, "png", new File(saida, nomeArquivo));
    }
}
