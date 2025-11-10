package Module3.Ex2;
import com.google.common.base.Strings;

public class GuavaTest {

    public static void validateStudentName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            System.out.println("Имя студента не может быть пустым или null");
        } else {
            System.out.println("Имя студента корректно: " + name);
        }
    }

    public static void main(String[] args) {
        validateStudentName("Иван");
        validateStudentName("");
        validateStudentName(null);
        validateStudentName("   ");
    }
}
