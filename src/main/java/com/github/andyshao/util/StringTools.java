package com.github.andyshao.util;

/**
 * 
 * Title: some usefu tools of java.lang.String<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 28, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class StringTools {
    private StringTools() {
        throw new AssertionError("Not allowed to instance " + StringTools.class.getName());
    }

    /**
     * more efficient<br>
     * replace all of the key words
     * 
     * @param str the string which will be process
     * @param key the key words will be instead
     * @param padding the right words will be inputed
     * @return the end of the string
     */
    public static String replaceAll(String str , String key , String padding) {
        while (str.indexOf(key) != -1)
            str = replaceFirst(str , key , padding);
        return str;
    }

    /**
     * more efficient<br>
     * only replace the first time occur
     * 
     * @param str the string which will be process
     * @param key the key words will be insted
     * @param padding the right words will be inputed
     * @return the end of the string
     */
    public static String replaceFirst(String str , String key , String padding) {
        return replace(str, padding, str.indexOf(key), key.length());
    }

    /**
     * more efficient<br>
     * only replace the last time occur
     * 
     * @param str the string which will be process
     * @param key the key words will be insted
     * @param padding the right words will be inputed
     * @return the end of the string
     */
    public static String replaceLast(String str , String key , String padding) {
        return replace(str, padding, str.lastIndexOf(key), key.length());
    }

    /**
     *  more efficient<br>
     * replace the string
     * 
     * @param str the string which will be process
     * @param padding the right words will be inputed
     * @param index the right words will be inputed
     * @param length the length of string which should be replaced
     * @return the end of the string
     */
	static String replace(String str, String padding, int index, int length) {
		String result;
		if (index == -1) result = str;
		else {
			if (index == 0) result = padding + str.substring(length);
			else if (index + 1 >= str.length()) result = str.substring(0 , index) + padding;
			else result = str.substring(0 , index) + padding + str.substring(index + length);
		}

        return result;
	}

    /**
     * more efficient<br>
     * split the string by separator
     * 
     * @param str the string which will be process
     * @param separator the str will be splited by it
     * @return the end of string
     */
    public static String[] split(String str , String separator) {
        String[] result = new String[0];
        for (int index ; (index = str.indexOf(separator)) != -1 ;) {
            result = ArrayTools.mergeArray(String[].class , result , new String[] {
                str.substring(0 , index)
            });
            str = str.substring(index + separator.length());
        }
        if (str.length() != 0) result = ArrayTools.mergeArray(String[].class , result , new String[] {
            str
        });

        return result;
    }
}
