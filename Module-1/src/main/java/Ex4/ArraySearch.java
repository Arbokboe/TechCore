package Ex4;

import java.util.Random;

public class ArraySearch {

    public static int[] createRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int linearSearch(int[] array, int target) {
        if (array == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] numbers = createRandomArray(20, 1, 100);
        int maxElement = findMax(numbers);
        int linearSearch = linearSearch(numbers, maxElement);
        System.out.println("Максимальный элемент " + maxElement);
        System.out.println("Линейный поиск " + linearSearch);
    }
}