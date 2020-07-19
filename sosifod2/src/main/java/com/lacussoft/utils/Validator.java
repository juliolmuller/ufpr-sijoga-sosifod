package com.lacussoft.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {}

    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }

        String regex = "^[\\w\\d\\.]+@[\\w\\d\\.]+\\.[A-Za-z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isName(String name) {
        if (name == null) {
            return false;
        }

        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    public static boolean isLogin(String login) {
        if (login == null) {
            return false;
        }

        String regex = "^\\w+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);

        return matcher.matches();
    }

    public static boolean isDate(String date) {
        return (Converter.toDate(date) != null);
    }

    public static boolean isCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        for (int factor : new int[] { 9, 10 }) {
            int sum = 0;
            for (int i = 0, div = factor + 1; i < factor; i++, div--) {
                int num = (int) (cpf.charAt(i) - '0');
                sum += (num * div);
            }
            int rem = 11 - (sum % 11);
            int dig = (rem == 10 || rem == 11) ? 0 : rem;
            if (dig != (int) (cpf.charAt(factor) - '0')) {
                return false;
            }
        }

        return true;
    }

    public static boolean isCep(String cep) {
        if (cep == null || Converter.removeNonDigits(cep).length() != 8) {
            return false;
        }

        return true;
    }
}
