package br.com.bank.onlybank.utils;

import org.springframework.stereotype.Component;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataUtil {

    private static final String STANDARD_FORMAT = "MM/dd/yyyy HH:mm";

    public String dateFormat(Date date) {
        Format formatter = new SimpleDateFormat(STANDARD_FORMAT);
        return formatter.format(date);
    }
}
