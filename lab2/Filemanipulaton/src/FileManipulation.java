import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class FileManipulation {
    public static void main(String[] args) {
        writeTextToFile("student_file.txt", "Name: Valdemar Buco");
        readTextFromFile("student_file.txt");
    }

    // Creating a new file if file does not exist
    // If file already exists, merge it
    public static void writeTextToFile(String fileName, String content) {
        Path path = Paths.get(fileName);
        // If file does not exist then create new one
        if (!Files.exists(path)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(content);
                writer.newLine();
                writer.write("Food I Like: Funge, Calulu de Peixe, Calulu de Carne Seca, Pernil, Feijoada");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {// Append the text
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.newLine();
                writer.write(content);
                writer.newLine();
                writer.write("Food I Like: Funge, Pernil, Feijoada");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Reade a file given
    public static void readTextFromFile(String fileName) {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            throw new RuntimeException("File not found: " + fileName);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
