package com.vgoryashko.sql.collectfromsqlru;

import java.time.LocalDate;

/**
 * Class that converts text date into int format.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class ConvertDate {

    private String[] date;

    private String[] months = new String[] {
            "ЯНВ", "ФЕВ", "МАР", "АПР", "МАЙ", "ИЮН", "ИЮЛ", "АВГ", "СЕН", "ОКТ", "НОЯ", "ДЕК"
    };

    public ConvertDate(String[] date) {
        this.date = date;
    }

    public int[] convert() {

        int[] result = new int[5];

        LocalDate localDate = LocalDate.now();

        if (date[0].equals("вчера")) {
            result[0] = localDate.getDayOfMonth() - 1;
            result[1] = localDate.getMonthValue();
            result[2] = localDate.getYear() - 2000;
        } else if (date[0].equals("сегодня")) {
            result[0] = localDate.getDayOfMonth();
            result[1] = localDate.getMonthValue();
            result[2] = localDate.getYear() - 2000;
        } else {
            result[0] = Integer.parseInt(date[0]);
            for (int i = 0; i < months.length; i++) {
                if (date[1].toUpperCase().equals(months[i])) {
                    result[1] = i + 1;
                    break;
                }
            }

            result[2] = Integer.parseInt(date[2]);
        }


        return result;

    }
}
