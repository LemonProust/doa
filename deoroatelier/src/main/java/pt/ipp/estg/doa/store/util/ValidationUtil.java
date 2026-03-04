package pt.ipp.estg.doa.store.util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationUtil {

    /**
     * Valida um número de telefone português.
     *
     * Aceita os seguintes formatos:
     * - 9 dígitos (ex: 912345678)
     * - 9 dígitos com indicativo nacional (+351) (ex: +351912345678)
     * - 9 dígitos com indicativo nacional e separadores (ex: +351 912 345 678)
     * - 9 dígitos com separadores (ex: 912 345 678)
     *
     * @param phoneNumber O número de telefone a ser validado.
     * @return true se o número de telefone for válido, false caso contrário.
     */
    public static boolean isTelephoneValidPT(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }

        // Remove espaços em branco e hífens para facilitar a comparação
        String cleanNumber = phoneNumber.replaceAll("[\\s-]", "");

        // Expressão regular para números de telefone portugueses
        String regexPT = "^(\\+351)?(9[1-3,6]{1}\\d{7}|2\\d{8})$";

        Pattern patternPT = Pattern.compile(regexPT);
        Matcher matcherPT = patternPT.matcher(cleanNumber);

        return matcherPT.matches();
    }

    /**
     * Valida um Número de Identificação Fiscal (NIF) português.
     *
     * @param nif O NIF a ser validado.
     * @return true se o NIF for válido, false caso contrário.
     */
    public static boolean isValidNIF(String nif){
        if (nif == null || nif.trim().isEmpty() || nif.length() != 9) {
            System.out.println("Formato inválido!");
            return false;
        }
        try {
            int[] nifArray = new int[9];
            for (int i = 0; i < 9; i++) {
                nifArray[i] = Integer.parseInt(nif.substring(i, i + 1));
            }

            int checkDigit = nifArray[8];
            int sum = 0;
            int[] weights = {9, 8, 7, 6, 5, 4, 3, 2};

            for (int i = 0; i < 8; i++) {
                sum += nifArray[i] * weights[i];
            }

            int remainder = sum % 11;
            int calculatedCheckDigit = (remainder < 2) ? 0 : (11 - remainder);

            return checkDigit == calculatedCheckDigit;

        } catch (NumberFormatException e) {
            System.out.println("Formato inválido. " + e);
            return false;
        }
    }

}
