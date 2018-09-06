import java.util.Scanner;

public class IPRangeDemo
{
    private static String[][] iPs = {
            {"192.168.0.1", "192.168.0.1"},
            {"192.168.0.11", "192.168.0.12"},
            {"192.168.0.21", "192.168.0.23"},
            {"192.168.0.31", "192.168.0.34"},
            {"192.168.0.41", "192.168.0.45"},
            {"192.168.0.251", "192.168.1.20"},
            {"192.168.0.256", "192.168.1.9"}
    };
    public static void main(String[] args) {
        show(examples());
        //show(manual());
    }
    private static String examples() {
        String results = "";
        for(String[] arr: iPs) {
            results += example(arr[0]+"\n"+arr[1]+"\n");
        }
        return results;
    }
    private static String example(String iPs) {
        return iPs + IPRange.getRangeOf(iPs) +"\n";
    }
    private static String manual() {
        Scanner scan = new Scanner(System.in);
        String iPFirst = scan.nextLine();
        String iPSecond = scan.nextLine();
        return IPRange.getRangeOf(iPFirst, iPSecond);
    }
    private static void show(String message) {
        System.out.print(message);
    }
}
