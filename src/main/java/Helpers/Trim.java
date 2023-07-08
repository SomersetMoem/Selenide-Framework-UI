package Helpers;


import java.util.regex.Pattern;

public class Trim {

    /**
     * Удаляет заданный символ из начала и конца строки. Если символ не передан, то удаляет пробелы.
     * @param string строка для удаления символов
     * @param trimSymbol символ для удаления из строки. Если не передан, то используются пробелы.
     * @return отформатированную строку без символов в начале и конце.
     */
    public static String trim(String string, String trimSymbol) {
        trimSymbol = Pattern.quote(trimSymbol);
        String trimmed = ltrim(string, trimSymbol);
        return rtrim(trimmed, trimSymbol);
    }

    /**
     Удаляет символы-разделители из начала переданной строки.
     @param string Строка, из которой нужно удалить символы-разделители.
     @param trimSymbol Символ-разделитель, который нужно удалить.
     @return Строка без символов-разделителей в начале.
     */
    public static String ltrim(String string, String trimSymbol) {
        trimSymbol = Pattern.quote(trimSymbol);
        return string.replaceAll("^" + trimSymbol + "+", "");
    }

    /**
     * Удаляет все вхождения заданного символа в конце строки.
     * @param string строка, из которой нужно удалить символы
     * @param trimSymbol символ, который нужно удалить
     * @return строка без символов в конце
     */
    public static String rtrim(String string, String trimSymbol) {
        trimSymbol = Pattern.quote(trimSymbol);
        return string.replaceAll(trimSymbol + "+$", "");
    }
}
