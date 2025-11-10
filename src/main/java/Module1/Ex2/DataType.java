package Module1.Ex2;

public class DataType {
    public static void main(String[] args) {

        byte byteVar = 127;
        short shortVar = 32767;
        int intVar = 2147483647;
        long longVar = 9223372036854775807L;

        float floatVar = 3.4028235E38f;
        double doubleVar = 1.7976931348623157E308;
        char charVar = 'A';
        boolean booleanVar = true;


        String stringVar = "Hello, Java!";


        System.out.println("=== ПРИМИТИВНЫЕ ТИПЫ ДАННЫХ В JAVA ===");
        System.out.println("byte:    значение = " + byteVar + ", размер = 1 байт");
        System.out.println("short:   значение = " + shortVar + ", размер = 2 байта");
        System.out.println("int:     значение = " + intVar + ", размер = 4 байта");
        System.out.println("long:    значение = " + longVar + ", размер = 8 байт");
        System.out.println("float:   значение = " + floatVar + ", размер = 4 байта");
        System.out.println("double:  значение = " + doubleVar + ", размер = 8 байт");
        System.out.println("char:    значение = '" + charVar + "', размер = 2 байта");
        System.out.println("boolean: значение = " + booleanVar + ", размер = ~1 байт (зависит от JVM)");

        System.out.println("\n=== СТРОКОВЫЙ ТИП ===");
        System.out.println("String:  значение = \"" + stringVar + "\"");

        System.out.println("\n=== ГРАНИЧНЫЕ ЗНАЧЕНИЯ ===");
        byte minByte = -128;
        short minShort = -32768;
        int minInt = -2147483648;
        long minLong = -9223372036854775808L;
        float minFloat = 1.4E-45f;
        double minDouble = 4.9E-324;

        System.out.println("Минимальное byte:  " + minByte);
        System.out.println("Минимальное short: " + minShort);
        System.out.println("Минимальное int:   " + minInt);
        System.out.println("Минимальное long:  " + minLong);
        System.out.println("Минимальное float: " + minFloat);
        System.out.println("Минимальное double: " + minDouble);
    }
}