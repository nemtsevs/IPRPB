import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class IPRange
{
    static {
        initialize();
    }
    public static String getRangeOf(String iPs) {
        return getRangeOf(iPs.split("\n"));
    }
    public static String getRangeOf(String[] iPs) {
        return getRangeOf(iPs[0], iPs[1]);
    }
    public static String getRangeOf(String iPFirst, String iPSecond) {
        if (checkIPPatternAndSetArrays(iPFirst, iPSecond)) {
            if (checkIPValue(iPFOctets) && checkIPValue(iPSOctets)) {
                return collectRange(iPFOctets, iPSOctets);
            }
        }
        return "Некорректный ввод IP.\n";
    }
    private static boolean checkIPPatternAndSetArrays(String iPF, String iPS) {
        Pattern iPPat = Pattern.compile(
                "^([0-9]{1,3})[.]([0-9]{1,3})[.]([0-9]{1,3})[.]([0-9]{1,3})$");
        Matcher m1 = iPPat.matcher(iPF);
        Matcher m2 = iPPat.matcher(iPS);
        if (m1.matches() && m2.matches()) {
            for(int i=0; i < iPFOctets.length; i++) {
                iPFOctets[i] = Integer.parseInt(m1.group(i+1));
                iPSOctets[i] = Integer.parseInt(m2.group(i+1));
            }
            return true;
        }
        return false;
    }
    private static boolean checkIPValue(int[] octets) {
        if (minOctVal1 <= octets[0] && octets[0] <= maxOctVal1) {
            for(int i=1; i < octets.length; i++) {
                if (!(minOctVal <= octets[i] && octets[i] <= maxOctVal)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private static String collectRange(int[] fArr, int[] sArr) {
        String range = "";
        long[] bounds = getBounds(fArr, sArr);
        for(long index = bounds[0] + 1; index < bounds[1]; index++) {
            int[] octets = getArray(index);
            range += Integer.toString(octets[0]);
            for(int i=1; i < octets.length; i++) {
                range += "."+Integer.toString(octets[i]);
            }
            range += "\n";
        }
        return range;
    }
    private static long[] getBounds(int[] fArr, int[] sArr) {
        long first = getValue(fArr);
        long second = getValue(sArr);
        long start = Math.min(first, second);
        long stop = Math.max(first, second);
        return new long[] {start, stop};
    }
    private static long getValueOf(int[] arr) {
        long value = 0, rate = 1;
        for(int i=arr.length-1; i >= 0; i--) {
            value += (long)arr[i] * rate;
            rate *= 256;
        }
        return value;
    }
    private static int[] getArrayOf(long value) {
        int[] array = new int[4];
        for(int i=array.length-1; i >= 0; i--) {
            array[i] = (int)(value % 256);
            value /= 256;
        }
        return array;
    }
    private static long getValue(int[] arr) {
        long value =
                (long)arr[0] * 256 * 256 * 256 +
                (long)arr[1] * 256 * 256 +
                (long)arr[2] * 256 +
                (long)arr[3];
        return value;
    }
    private static int[] getArray(long value) {
        return new int[] {
                (int)(value / 256 / 256 / 256 % 256),
                (int)(value / 256 / 256 % 256),
                (int)(value / 256 % 256),
                (int)(value % 256)
        };
    }
    private static void initialize() {
        minOctVal1 = 1;
        maxOctVal1 = 247;
        minOctVal = 0;
        maxOctVal = 255;
        iPFOctets = new int[4];
        iPSOctets = new int[4];
    }
    private static int minOctVal1;
    private static int maxOctVal1;
    private static int minOctVal;
    private static int maxOctVal;
    private static int[] iPFOctets;
    private static int[] iPSOctets;

    public static class Test
    {
        public static boolean checkIPPatAndSetArr(String iPF, String iPS) {
            return IPRange.checkIPPatternAndSetArrays(iPF, iPS);
        }
        public static boolean checkIPValue(int[] octets) {
            return IPRange.checkIPValue(octets);
        }
        public static String collectRange(int[] fArr, int[] sArr) {
            return IPRange.collectRange(fArr, sArr);
        }
        public static long[] getBounds(int[] fArr, int[] sArr) {
            return IPRange.getBounds(fArr, sArr);
        }
        public static long getValueOf(int[] arr) {
            return IPRange.getValueOf(arr);
        }
        public static int[] getArrayOf(long value) {
            return IPRange.getArrayOf(value);
        }
        public static long getValue(int[] arr) {
            return IPRange.getValue(arr);
        }
        public static int[] getArray(long value) {
            return IPRange.getArray(value);
        }
        public static void initialize() {
            IPRange.initialize();
        }
        public static int getMinOctVal1() {
            return IPRange.minOctVal1;
        }
        public static int getMaxOctVal1() {
            return IPRange.maxOctVal1;
        }
        public static int getMinOctVal() {
            return IPRange.minOctVal;
        }
        public static int getMaxOctVal() {
            return IPRange.maxOctVal;
        }
        public static int[] getIFOctets() {
            return IPRange.iPFOctets;
        }
        public static int[] getIPSOctets() {
            return IPRange.iPSOctets;
        }
    }
}
