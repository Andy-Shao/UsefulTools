package andy.shao.convert;

import java.util.Objects;

/**
 * General convert interface.
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jun 14, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <IN>
 * @param <OUT>
 */
@FunctionalInterface
public interface Convert<IN , OUT> {
    OUT convert(IN in);

    public static final Convert<Object , String> OB_2_STR = (Object in) -> {
        return Objects.toString(in , null);
    };
    public static final Convert<Object , Character> OB_2_CHAR = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str.charAt(0);
    };
    public static final Convert<Object , Integer> OB_2_INT = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str == null ? null : Integer.valueOf(str);
    };
    public static final Convert<Object , Short> OB_2_SHORT = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str == null ? null : Short.valueOf(str);
    };
    public static final Convert<Object , Long> OB_2_LONG = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str == null ? null : Long.valueOf(str);
    };
    public static final Convert<Object , Boolean> OB_2_BOOLEAN = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str == null ? null : Boolean.valueOf(str);
    };
    public static final Convert<Object , Float> OB_2_FLOAT = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str == null ? null : Float.valueOf(str);
    };
    public static final Convert<Object , Double> OB_2_DOUBLE = (Object in) -> {
        String str = OB_2_STR.convert(in);
        return str == null ? null : Double.valueOf(str);
    };
    public static final Convert<byte[] , String> BYTES_2_STR = (byte[] keys) -> {
        char[] cs = new char[keys.length << 1];
        for(int i=keys.length; i>=1; i--){
            int temp = i << 1;
            cs[--temp] = ARRAY[0x0F & keys[i-1]];
            cs[--temp] = ARRAY[(0xF0 & keys[i-1]) >> 4];
        }
        return new String(cs);
    };
    static final char[] ARRAY = new char[] {
        '0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F'
    };
}
