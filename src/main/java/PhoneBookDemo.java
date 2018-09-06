import java.util.Scanner;

public class PhoneBookDemo
{
    private static String[] names = {
            "Иванов И.И.",
            "Иванов И.П.",
            "Иванов И.С.",
            "Петров П.И.",
            "Петров П.П.",
            "Петров П.С.",
            "Сидоров С.И.",
            "Сидоров С.П.",
            "Сидоров С.С.",
    };
    public static void main(String[] args) {
        examples();
        //manuals(3);
    }
    public static void examples() {
        for(String FIO: names) {
            show(example(FIO));
        }
    }
    public static void manuals(int count) {
        for(int i=0; i < count; i++) {
            show(manual());
        }
    }
    private static String example(String FIO) {
        return FIO + "\n" + PhoneBook.getBook().getPhonesBy(FIO) + "\n";
    }
    private static String manual() {
        String FIO = new Scanner(System.in).nextLine();
        return PhoneBook.getBook().getPhonesBy(FIO)  + "\n";
    }
    private static void show(String message) {
        System.out.print(message);
    }
}
