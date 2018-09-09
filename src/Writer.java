import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Writer {

    void write(String result) throws IOException {
         FileWriter fileWriter = new FileWriter(new File("out.txt"));
         fileWriter.write(result);
         fileWriter.close();
    }
}
