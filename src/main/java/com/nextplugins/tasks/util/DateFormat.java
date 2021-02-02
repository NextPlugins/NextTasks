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
                day = "Segunda-Feira";
                break;
            case "TUESDAY":
                day = "Terça-Feira";
                break;
            case "WEDNESDAY":
                day = "Quarta-Feira";
                break;
            case "THURSDAY":
                day = "Quinta-Feira";
                break;
            case "FRIDAY":
                day = "Sexta-Feira";
                break;
            case "SATURDAY":
                day = "Sábado";
                break;
            case "EVERYDAY":
                day = "Todos os dias";
                break;
        }

        return day + " ás " + hour + ":" + minute;
    }

}
