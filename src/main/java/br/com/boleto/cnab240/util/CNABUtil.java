package br.com.boleto.cnab240.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CNABUtil {

    public static String formatAlphanumeric(String value, int length) {
        if (value == null) {
            value = "";
        }
        // Remove accents and cedilla, convert to uppercase
        value = StringUtils.stripAccents(value).toUpperCase().replace("Ç", "C");
        // Right pad with spaces and limit to length
        return StringUtils.rightPad(value, length).substring(0, length);
    }

    public static String formatNumber(Long value, int length) {
        if (value == null) {
            value = 0L;
        }
        // Left pad with zeros
        return StringUtils.leftPad(value.toString(), length, '0');
    }

    public static String formatDecimal(BigDecimal value, int length) {
        if (value == null) {
            value = BigDecimal.valueOf(0.0);
        }
        // Move decimal point and format as long
        long longValue = Math.round(value.doubleValue() * 100);
        return formatNumber(longValue, length);
    }

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return StringUtils.repeat("0", 8);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return date.format(formatter);
    }
}
