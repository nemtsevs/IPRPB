import static org.junit.Assert.*;

public class IPRangeTest
{
    @org.junit.Test
    public void getRangeOf_str() {
        String request =
                        "192.168.0.1\n"+
                        "192.168.0.10\n";
        String responseTemplate =
                        "192.168.0.2\n" +
                        "192.168.0.3\n" +
                        "192.168.0.4\n" +
                        "192.168.0.5\n" +
                        "192.168.0.6\n" +
                        "192.168.0.7\n" +
                        "192.168.0.8\n" +
                        "192.168.0.9\n";
        String responseInstance = IPRange.getRangeOf(request);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getRangeOf_strArr() {
        String[] request = {
                        "192.168.0.255",
                        "192.168.1.10"};
        String responseTemplate =
                        "192.168.1.0\n" +
                        "192.168.1.1\n" +
                        "192.168.1.2\n" +
                        "192.168.1.3\n" +
                        "192.168.1.4\n" +
                        "192.168.1.5\n" +
                        "192.168.1.6\n" +
                        "192.168.1.7\n" +
                        "192.168.1.8\n" +
                        "192.168.1.9\n";
        String responseInstance = IPRange.getRangeOf(request);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getRangeOf_twoStr() {
        String reqIP1 = "192.168.0.251";
        String reqIP2 = "192.168.1.5";
        String responseTemplate =
                        "192.168.0.252\n" +
                        "192.168.0.253\n" +
                        "192.168.0.254\n" +
                        "192.168.0.255\n" +
                        "192.168.1.0\n" +
                        "192.168.1.1\n" +
                        "192.168.1.2\n" +
                        "192.168.1.3\n" +
                        "192.168.1.4\n";
        String responseInstance = IPRange.getRangeOf(reqIP1, reqIP2);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getRangeOf_inCorrectIP_1() {
        String reqIP1 = "192.168.0.0";
        String reqIP2 = "192.168.1.256";
        String responseTemplate =
                "Некорректный ввод IP.\n";
        String responseInstance = IPRange.getRangeOf(reqIP1, reqIP2);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getRangeOf_inCorrectIP_2() {
        String reqIP1 = "192.168.0.aaa";
        String reqIP2 = "256.168.1.0";
        String responseTemplate =
                "Некорректный ввод IP.\n";
        String responseInstance = IPRange.getRangeOf(reqIP1, reqIP2);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void checkIPPatAndSetArr_1() {
        String reqIP1 = "192.168.0.1";
        String reqIP2 = "192.168.0.255";
        boolean responseTemplate = true;
        boolean responseInstance = IPRange.Test.checkIPPatAndSetArr(reqIP1, reqIP2);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void checkIPPatAndSetArr_2() {
        String reqIP1 = "192.168.0.-1";
        String reqIP2 = "192.168.0.aaa";
        assertEquals(false, IPRange.Test.checkIPPatAndSetArr(reqIP1, reqIP2));
    }

    @org.junit.Test
    public void checkIPValue_1() {
        int[] reqOctets = {192, 168, 0, 1};
        assertEquals(true, IPRange.Test.checkIPValue(reqOctets));
    }

    @org.junit.Test
    public void checkIPValue_2() {
        int[] reqOctets = {192, 168, 0, 256};
        assertEquals(false, IPRange.Test.checkIPValue(reqOctets));
    }

    @org.junit.Test
    public void collectRange_1() {
        int[] reqOctets1 = {192, 168, 0, 0};
        int[] reqOctets2 = {192, 168, 0, 5};
        String responseTemplate =
                        "192.168.0.1\n" +
                        "192.168.0.2\n" +
                        "192.168.0.3\n" +
                        "192.168.0.4\n";
        String responseInstance = IPRange.Test.collectRange(reqOctets1, reqOctets2);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void collectRange_2() {
        int[] reqOctets1 = {192, 168, 0, 5};
        int[] reqOctets2 = {192, 168, 0, 0};
        String responseTemplate =
                        "192.168.0.1\n" +
                        "192.168.0.2\n" +
                        "192.168.0.3\n" +
                        "192.168.0.4\n";
        String responseInstance = IPRange.Test.collectRange(reqOctets1, reqOctets2);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getBounds() {
        int[] reqOctets1 = {1, 0, 0, 0};
        int[] reqOctets2 = {1, 0, 0, 1};
        long[] responseTemplate = {16777216, 16777217};
        long[] responseInstance = IPRange.Test.getBounds(reqOctets1, reqOctets2);
        assertArrayEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getValueOf() {
        int[] reqOctets = {1, 0, 0, 2};
        long responseTemplate = 16777218;
        long responseInstance = IPRange.Test.getValueOf(reqOctets);
        assertEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getArrayOf() {
        long reqValue = 16777219;
        int[] responseTemplate = {1, 0, 0, 3};
        int[] responseInstance = IPRange.Test.getArrayOf(reqValue);
        assertArrayEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getValue() {
        assertEquals(16777220, IPRange.Test.getValue(new int[]{1, 0, 0, 4}));
    }

    @org.junit.Test
    public void getArray() {
        assertArrayEquals(new int[]{1, 0, 0, 5}, IPRange.Test.getArray(16777221));
    }

    @org.junit.Test
    public void getMinOctVal1() {
        assertEquals(1, IPRange.Test.getMinOctVal1());
    }

    @org.junit.Test
    public void getMaxOctVal1() {
        assertEquals(247, IPRange.Test.getMaxOctVal1());
    }

    @org.junit.Test
    public void getMinOctVal() {
        assertEquals(0, IPRange.Test.getMinOctVal());
    }

    @org.junit.Test
    public void getMaxOctVal() {
        assertEquals(255, IPRange.Test.getMaxOctVal());
    }

    @org.junit.Test
    public void getIFOctets() {
        IPRange.Test.initialize();
        int[] responseTemplate = {0, 0, 0, 0};
        int[] responseInstance = IPRange.Test.getIFOctets();
        assertArrayEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getIFOctets_1() {
        IPRange.getRangeOf("192.168.0.1\n192.168.1.0");
        int[] responseTemplate = {192, 168, 0, 1};
        int[] responseInstance = IPRange.Test.getIFOctets();
        assertArrayEquals(responseTemplate, responseInstance);
    }

    @org.junit.Test
    public void getIPSOctets() {
        IPRange.Test.initialize();
        assertArrayEquals(new int[]{0, 0, 0, 0}, IPRange.Test.getIPSOctets());
    }

    @org.junit.Test
    public void getIPSOctets_1() {
        IPRange.getRangeOf("192.168.0.255\n192.168.1.25");
        assertArrayEquals(new int[]{192, 168, 1, 25}, IPRange.Test.getIPSOctets());
    }
}
