import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class Prueba {

    //Instalar https://imagemagick.org/script/download.php
    public static void main(String... args) throws IOException {

        try (Stream<Path> paths = Files.walk(Paths.get("D:\\Descargas 2023\\Huaraz"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            convert(path);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

    }

    private static void convertir(Path path) {
        File inputFile = new File(path.toUri());
        String nombre = path.getFileName().toString().substring(0,7)+".png";
        File outputFile = new File("D:\\Descargas 2023\\Huaraz\\new\\"+nombre);
        try {
            BufferedImage image = ImageIO.read(inputFile);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Archivo convertido exitosamente: "+nombre);
        } catch (IOException e) {
            System.out.println("Error al convertir el archivo: " + e.getMessage());
        }
    }

    public static void convert(Path path) {
        ConvertCmd cmd = new ConvertCmd();
        cmd.setSearchPath("D:\\Archivos de Programas\\ImageMagick-7.1.1-Q16-HDRI");

        File inputFile = new File(path.toUri());
        String nombre = path.getFileName().toString().substring(0,8)+".png";
        File outputFile = new File("D:\\Descargas 2023\\Huaraz\\new\\"+nombre);

        IMOperation operation = new IMOperation();
        operation.addImage(inputFile.getAbsolutePath());
        operation.addImage(outputFile.getAbsolutePath());

        try {
            cmd.run(operation);
            System.out.println("Archivo convertido exitosamente: "+nombre);
        } catch (IOException | InterruptedException | IM4JavaException e) {
            e.printStackTrace();
        }
    }

    public static File perform(Path path) throws Exception {
        File inputFile = new File(path.toUri());

        String nombre = path.getFileName().toString().substring(0,7)+".png";
        String folderFile = "D:\\Descargas 2023\\Huaraz\\new\\"+nombre;

        IMOperation op = new IMOperation();
        op.addImage(folderFile); //place holder for input file
        //op.addImage(); //place holder for output file

        File fout = File.createTempFile("fromHeic"+(new Random()).nextLong(), ".png");

        //file.tiff to file.png
        ConvertCmd convert = new ConvertCmd();
        convert.run(op, new Object[]{inputFile.getAbsolutePath(), fout.getAbsolutePath()});
        return fout;
    }

    public static void HEICtoPNGConverter(Path path) {

        File inputFile = new File(path.toUri());
        String nombre = path.getFileName().toString().substring(0,8)+".png";
        File outputFile = new File("D:\\Descargas 2023\\Huaraz\\new\\"+nombre);

        ConvertCmd cmd = new ConvertCmd();
        IMOperation operation = new IMOperation();
        operation.addImage(inputFile.getAbsolutePath());
        operation.addImage(outputFile.getAbsolutePath());

        try {
            cmd.run(operation);
            System.out.println("Archivo convertido exitosamente!");
        } catch (Exception e) {
            System.out.println("Error al convertir el archivo: " + e.getMessage());
        }
    }

}
