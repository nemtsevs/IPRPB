import static org.junit.Assert.*;

public class PhoneBookTest
{
    @org.junit.Test
    public void pBook_getPhonesBy_1() {
        String responseTemplate =
                "8 800 200 60 01\n" +
                "8 800 200 60 02\n" +
                "8 800 200 60 03\n" +
                "8 800 200 60 04\n" +
                "8 800 200 60 05\n";
        String responseInstance = PhoneBook.getBook().getPhonesBy("Иванов И.И.");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void pBook_getPhonesBy_2() {
        String responseTemplate = "8 800 200 60 40\n";
        String responseInstance = PhoneBook.getBook().getPhonesBy("Сидоров С.С.");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void pBook_getPhonesBy_3() {
        String responseTemplate = "ФИО не найдено в базе.\n";
        String responseInstance = PhoneBook.getBook().getPhonesBy("Иванов И.Я.");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void phones_get_1() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        assertEquals("-1", phones.getPhone(0));
    }

    @org.junit.Test
    public void phones_putGet_2() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        phones.putPhone("8 800 800 80 80");
        assertEquals("8 800 800 80 80", phones.getPhone(0));
    }

    @org.junit.Test
    public void phones_count_1() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        assertEquals(0, phones.count());
    }

    @org.junit.Test
    public void phones_count_2() {
        PhoneBook.Phones phones = new PhoneBook.Phones("8 800 800 80 80");
        assertEquals(1, phones.count());
    }

    @org.junit.Test
    public void phones_toString_1() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        assertEquals("", phones.toString());
    }

    @org.junit.Test
    public void phones_toString_2() {
        PhoneBook.Phones phones = new PhoneBook.Phones("8 800 800 80 80");
        assertEquals("8 800 800 80 80\n", phones.toString());
    }

    @org.junit.Test
    public void phones_getSamplePhone_1() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        String responseTemplate = "8 800 800 80 80";
        String responseInstance = PhoneBook.PTest.getSamplePhone(phones, "88008008080");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void phones_getSamplePhone_2() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        String responseTemplate = "8 800 800 80 80";
        String responseInstance = PhoneBook.PTest.getSamplePhone(phones, "8-800-800-80-80");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void phones_isTherePhone_1() {
        PhoneBook.Phones phones = new PhoneBook.Phones("8 800 800 80 80");
        boolean responseTemplate = true;
        boolean responseInstance = PhoneBook.PTest.isTherePhone(phones, "8 800 800 80 80");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void phones_isTherePhone_2() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        boolean responseTemplate = false;
        boolean responseInstance = PhoneBook.PTest.isTherePhone(phones, "8 800 800 80 80");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void phones_checkPPattern_1() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        boolean responseTemplate = true;
        boolean responseInstance = PhoneBook.PTest.checkPPattern(phones, "8 800 800 80 80");
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void phones_checkPPattern_2() {
        PhoneBook.Phones phones = new PhoneBook.Phones();
        boolean responseTemplate = false;
        boolean responseInstance = PhoneBook.PTest.checkPPattern(phones, "0 000 000 00 00");
        assertEquals(responseTemplate, responseInstance);
    }
}
