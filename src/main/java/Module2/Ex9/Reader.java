package Module2.Ex9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader{

    public static void readFile(String filename) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            System.out.println("Содержимое файла " + filename + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("Файловый ресурс закрыт");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        readFile("example.txt");
        readFile("nonexistent.txt");
    }
}