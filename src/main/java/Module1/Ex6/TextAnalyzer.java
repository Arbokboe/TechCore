package Module1.Ex6;

import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите предложение: ");
        String input = scanner.nextLine();

        int wordCount = countWords(input);
        int vowelCount = countVowels(input);
        int consonantCount = countConsonants(input);

        System.out.println("Количество слов: " + wordCount);
        System.out.println("Количество гласных букв: " + vowelCount);
        System.out.println("Количество согласных букв: " + consonantCount);

        scanner.close();
    }

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouаеёиоуыэюяAEIOUАЕЁИОУЫЭЮЯ";

        for (char c : text.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public static int countConsonants(String text) {
        int count = 0;
        String consonants = "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщBCDFGHJKLMNPQRSTVWXYZБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";

        for (char c : text.toCharArray()) {
            if (consonants.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}