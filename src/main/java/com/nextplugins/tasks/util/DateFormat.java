package com.nextplugins.tasks.util;

public final class DateFormat {

    public static String format(String rawDateExpression) {

        String[] split = rawDateExpression.split(":");

        String day = split[0];
        String hour = split[1];
        String minute = split[2];

        switch (day) {
            case "SUNDAY":
                day = "Domingo";
                break;
            case "MONDAY":
                day = "Segunda-feira";
                break;
            case "TUESDAY":
                day = "Terça-feira";
                break;
            case "WEDNESDAY":
                day = "Quarta-feira";
                break;
            case "THURSDAY":
                day = "Quinta-feira";
                break;
            case "FRIDAY":
                day = "Sexta-feira";
                break;
            case "SATURDAY":
                day = "Sábado";
                break;
            default:
                day = "Todos os dias";
                break;
        }

        return day + " ás " + hour + ":" + minute;
    }

}
