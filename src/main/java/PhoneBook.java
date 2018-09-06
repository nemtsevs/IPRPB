import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PhoneBook
{
    private static PhoneBook phoneBook;
    private static HashMap<String, Phones> pBook;
    static {
        phoneBook = new PhoneBook();
    }
    private PhoneBook() {
        pBook = new HashMap<String, Phones>();
        pBook.put("Иванов И.И.", new Phones(
                "+7 800 200 60 01",
                "+7 800 200 60 02",
                "+7 800 200 60 03",
                "+7 800 200 60 04",
                "+7 800 200 60 05")
        );
        pBook.put("Петров П.П.", new Phones(
                "8 800 200 60 10",
                "8 800 200 60 12",
                "8 800 200 60 14",
                "8 800 200 60 16")
        );
        pBook.put("Сидоров С.И.", new Phones(
                "+7-800-200-60-20",
                "+7-800-200-60-23",
                "+7-800-200-60-26")
        );
        pBook.put("Иванов И.С.", new Phones(
                "8-800-200-60-30",
                "8-800-200-60-35")
        );
        pBook.put("Сидоров С.С.", new Phones(
                "+78002006040",
                "+7 800 200 60 40",
                "+7-800-200-60-40",
                "8 800 200 60 40",
                "8-800-200-60-40",
                "88002006040"
                )
        );
    }
    public static PhoneBook getBook() {
        return phoneBook;
    }
    public String getPhonesBy(String name) {
        Phones phones = pBook.get(name);
        if (phones != null) {
            return phones.toString();
        }
        return "ФИО не найдено в базе.\n";
    }
    public static class Phones
    {
        private ArrayList<String> phones;
        public Phones(String...pArr) {
            phones = new ArrayList<String>();
            for(String pVal : pArr) {
                putPhone(pVal);
            }
        }
        public void putPhone(String phone) {
            String sPhone = getSamplePhone(phone);
            if (!sPhone.equals("0")) {
                if (!isTherePhone(sPhone)) {
                    phones.add(sPhone);
                }
            }
        }
        public String getPhone(int i) {
            if (0 <= i && i < count()) {
                return phones.get(i);
            }
            return "-1";
        }
        public int count() {
            return phones.size();
        }
        @Override
        public String toString() {
            String str = "";
            for(String p : phones) {
                str += p + "\n";
            }
            return str;
        }
        private String getSamplePhone(String phone) {
            for(String pPattern : pPatterns) {
                Pattern p = Pattern.compile(pPattern);
                Matcher m = p.matcher(phone);
                if (m.matches()) {
                    String sPhone = "8";
                    for(int i=2; i <= 5; i++) {
                        sPhone += " " + m.group(i);
                    }
                    return sPhone;
                }
            }
            return "0";
        }
        private boolean isTherePhone(String phone) {
            for(String p : phones) {
                if (p.equals(phone)) {
                    return true;
                }
            }
            return false;
        }
        private boolean checkPPattern(String phone) {
            for(String pPattern : pPatterns) {
                Pattern p = Pattern.compile(pPattern);
                Matcher m = p.matcher(phone);
                if (m.matches()) {
                    return true;
                }
            }
            return false;
        }
        private static String[] pPatterns = {
                "^([8])[ ]([0-9]{3})[ ]([0-9]{3})[ ]([0-9]{2})[ ]([0-9]{2})$",
                "^([8])[-]([0-9]{3})[-]([0-9]{3})[-]([0-9]{2})[-]([0-9]{2})$",
                "^([8])([0-9]{3})([0-9]{3})([0-9]{2})([0-9]{2})$",
                "^([+][7])[ ]([0-9]{3})[ ]([0-9]{3})[ ]([0-9]{2})[ ]([0-9]{2})$",
                "^([+][7])[-]([0-9]{3})[-]([0-9]{3})[-]([0-9]{2})[-]([0-9]{2})$",
                "^([+][7])([0-9]{3})([0-9]{3})([0-9]{2})([0-9]{2})$",
        };
    }
    public static class PTest
    {
        public static String getSamplePhone(Phones phones, String phone) {
            return phones.getSamplePhone(phone);
        }
        public static boolean isTherePhone(Phones phones, String phone) {
            return phones.isTherePhone(phone);
        }
        public static boolean checkPPattern(Phones phones, String phone) {
            return phones.checkPPattern(phone);
        }
    }
}
