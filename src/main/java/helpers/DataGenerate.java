package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DataGenerate {


    private static final String[] LAST_NAMES = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов"};
    private static final String[] FIRST_NAMES = {"Иван", "Петр", "Сидор", "Алексей", "Дмитрий"};
    private static final String[] MIDDLE_NAMES = {"Иванович", "Петрович", "Сидорович", "Алексеевич", "Дмитриевич"};
    private static final Calendar calendar = Calendar.getInstance();
    private static final Date birthDate = calendar.getTime();
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static final String[] names = new String[] {"nik", "nik1", "n1ik", "1nik", "ni_k", "ni.k"};
    private static final String[] domains = new String[] {"asd.com", "g-asd.com", "a_as.ru", "asd12.com", "12asd.com"};
    private static final Random random = new Random();


    /**
     * Генерирует случайную фамилию, имя и отчество
     *
     * @return ФИО в формате "Фамилия Имя Отчество"
     */
    public static String generateFullName() {
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String middleName = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        return lastName + " " + firstName + " " + middleName;
    }

    /**
     * Генерирует случайную дату рождения
     *
     * @return Дата рождения в формате "dd.MM.yyyy"
     */
    public static String generateBirthDate() {

        calendar.set(Calendar.YEAR, 1950 + random.nextInt(50));
        calendar.set(Calendar.MONTH, random.nextInt(12));
        calendar.set(Calendar.DAY_OF_MONTH, 1 + random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
        return format.format(birthDate);
    }

    /**
     * Генерирует случайный email
     * @return строку со случайным email
     */
    public static String generateEmail()
    {
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



}










