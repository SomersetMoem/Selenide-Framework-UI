package app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AppConfig {

    public static final String baseUrl = "http://the-internet.herokuapp.com";

    /**
     * Генерирует случайный email
     * @return строку со случайным email
     */
    public static String generateEmail()
    {
        String[] names = new String[] {"nik", "nik1", "n1ik", "1nik", "ni_k", "ni.k"};
        String[] domains = new String[] {"asd.com", "g-asd.com", "a_as.ru", "asd12.com", "12asd.com"};


        Random random = new Random();
        int domainIndex = random.nextInt(domains.length);
        int nameIndex = random.nextInt(names.length);
        return names[nameIndex] + "@" + domains[domainIndex];
    }

    /**
     * Генерирует случайный телефон в формате +7 XXX XXX-XX-XX после + 7 "9" гарантированно не будет
     * @return строку со случайным телефоном
     */
    public static String generatePhoneNumber() {
        String phoneNumber = "+7";
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(9);
            phoneNumber += digit;
        }
        return phoneNumber;

    }

    private static final Random RANDOM = new Random();

    /**
     * Генерирует случайную фамилию, имя и отчество
     *
     * @return ФИО в формате "Фамилия Имя Отчество"
     */
    public static String generateFullName() {
        String[] LAST_NAMES = {"Иванов", "Петров", "Сидоров-Иванов", "кузнецов", "Смирнов"};
        String[] FIRST_NAMES = {"Иван", "Петр-Сидор", "сидор", "Алексей", "Дмитрий"};
        String[] MIDDLE_NAMES = {"Иванович", "Петрович-Сидорович", "Сидорович", "Алексеевич", "кмитриевич"};

        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String middleName = MIDDLE_NAMES[RANDOM.nextInt(MIDDLE_NAMES.length)];
        return lastName + " " + firstName + " " + middleName;
    }

    /**
     * Генерирует случайную дату рождения
     *
     * @return Дата рождения в формате "dd.MM.yyyy"
     */
    public static String generateBirthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1950 + RANDOM.nextInt(50));
        calendar.set(Calendar.MONTH, RANDOM.nextInt(12));
        calendar.set(Calendar.DAY_OF_MONTH, 1 + RANDOM.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
        Date birthDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(birthDate);
    }




}
