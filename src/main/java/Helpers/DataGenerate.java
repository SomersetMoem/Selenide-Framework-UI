package Helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DataGenerate {

    private static final String[] LAST_NAMES = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов"};
    private static final String[] FIRST_NAMES = {"Иван", "Петр", "Сидор", "Алексей", "Дмитрий"};
    private static final String[] MIDDLE_NAMES = {"Иванович", "Петрович", "Сидорович", "Алексеевич", "Дмитриевич"};
    private static final String[] NAMES = new String[] {"nik", "nik1", "n1ik", "1nik", "ni_k", "ni.k"};
    private static final String[] DOMAINS = new String[] {"asd.com", "g-asd.com", "asd.com"};
    private static final String[] SEX = new String[] {"Мужской", "Женский"};
    private static final String[] LOCATION = new String[] {"Вологодская Область, Вологда", "Воронежская Область, Воронеж",
            "Краснодарский Край, Сочи", "Республика Башкортостан, Кутерем", "Республика Бурятия, Еловка", "Республика Марий Эл, Йошкар-Ола"};
    private static final String[] CITY_LOCATION = new String[] {"Курган", "Орел", "Пенза", "Тверь", "Томск", "Тула", "Уфа", "Самара"};
    private static final String[] REGION_LOCATION = new String[] {"Адыгея республика", "Кировская область", "Рязанская область", "Ханты-Мансийский автономный округ - Югра", "Псковская область", "Новгородская область"};
    private static final String[] REGION_OFFICES = new String[] {"Астрахань", "Казань", "Москва"};
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final Random RANDOM = new Random();

    /**
     * Генерирует случайное имя.
     *
     * @return Случайное имя из массива FIRST_NAMES.
     */
    public static String generateFirstName() {
        return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
    }

    /**
     * Генерирует случайное отчество.
     *
     * @return Случайное отчество из массива MIDDLE_NAMES.
     */
    public static String generateMiddleName() {
        return MIDDLE_NAMES[RANDOM.nextInt(MIDDLE_NAMES.length)];
    }

    /**
     * Генерирует случайную фамилию.
     *
     * @return Случайная фамилия из массива LAST_NAMES.
     */
    public static String generateLastName() {
        return LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
    }

    /**
     * Генерирует случайную дату рождения в формате "dd.MM.yyyy".
     * Дата рождения будет в пределах возраста от 18 до 90 лет от текущей даты.
     *
     * @return Случайная дата рождения.
     */
    public static String generateBirthDate() {
        CALENDAR.setTime(new Date()); // Установка текущей даты
        int currentYear = CALENDAR.get(Calendar.YEAR);

        int minAge = 18;
        int maxAge = 90;

        int minYear = currentYear - maxAge;
        int maxYear = currentYear - minAge;

        int randomYear = RANDOM.nextInt(maxYear - minYear + 1) + minYear;
        int randomMonth = RANDOM.nextInt(12);
        int randomDay = RANDOM.nextInt(CALENDAR.getActualMaximum(Calendar.DAY_OF_MONTH))+1;

        CALENDAR.set(Calendar.YEAR, randomYear);
        CALENDAR.set(Calendar.MONTH, randomMonth);
        CALENDAR.set(Calendar.DAY_OF_MONTH, randomDay);

        return FORMAT.format(CALENDAR.getTime());
    }

    /**
     * Генерирует случайный адрес электронной почты.
     *
     * @return Случайный адрес электронной почты.
     */
    public static String generateEmail()
    {
        StringBuilder EMAIL = new StringBuilder();
        int domainIndex = RANDOM.nextInt(DOMAINS.length);
        int nameIndex = RANDOM.nextInt(NAMES.length);
        EMAIL.append(NAMES[nameIndex])
                .append("@")
                .append(DOMAINS[domainIndex]);
        return EMAIL.toString();
    }

    /**
     * Генерирует случайный номер телефона в формате "+7XXXXXXXXXX".
     *
     * @return Случайный номер телефона.
     */
    public static String generatePhoneNumber()
    {
        StringBuilder PHONE_NUMBER = new StringBuilder();
        PHONE_NUMBER.append("+7");
        for (int i = 0; i <= 9; i++) {
            int digit = RANDOM.nextInt(9);
            PHONE_NUMBER.append(digit);
        }
        return PHONE_NUMBER.toString();

    }

    /**
     * Генерирует случайный код.
     *
     * @return Случайный код, состоящий из 6 цифр.
     */
    public static String generatorCode()
    {
        StringBuilder CODES = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            int digit = RANDOM.nextInt(10);
            CODES.append(digit);
        }
        return CODES.toString();
    }

    /**
     * Генерирует случайный пол.
     *
     * @return Случайный пол из массива SEX.
     */
    public static String generatorSex()
    {
        return SEX[RANDOM.nextInt(SEX.length)];
    }

    /**
     * Генерирует случайное местоположение.
     *
     * @return Случайное местоположение из массива LOCATION.
     */
    public static String generatorLocation(){return LOCATION[RANDOM.nextInt(LOCATION.length)];}

    /** Генерирует случайный город местоположения.
     *
     * @return Случайный город местоположения из массива CITY_LOCATION.
     */
    public static String generatorCityLocation(){return  CITY_LOCATION[RANDOM.nextInt(CITY_LOCATION.length)];}

    /**
     * Генерирует случайный регион местоположения.
     *
     * @return Случайный регион местоположения из массива REGION_LOCATION.
     */
    public static String generatorRegionLocation(){return REGION_LOCATION[RANDOM.nextInt(REGION_LOCATION.length)];}

    /**
     * Генерирует случайный регион офиса.
     *
     * @return Случайный регион офиса из массива REGION_OFFICES.
     */
    public static String generatorRegionOffices(){return REGION_OFFICES[RANDOM.nextInt(REGION_OFFICES.length)];}

}
