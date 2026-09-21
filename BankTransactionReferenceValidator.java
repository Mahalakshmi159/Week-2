import java.util.Scanner;

public class BankTransactionReferenceValidator {

    // Method to normalize reference
    public static String normalizeReference(String raw) {

        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed;
        }

        return trimmed.substring(0, 3).toUpperCase()
                + trimmed.substring(3);
    }

    // Method to validate and format reference
    public static String validateAndFormat(String reference) {

        // Length validation
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Bank code validation (first 3 letters)
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Remaining 11 characters must be digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String bankCode = reference.substring(0, 3);

        String datePart = reference.substring(3, 9);
        String day = datePart.substring(0, 2);
        String month = datePart.substring(2, 4);
        String year = datePart.substring(4, 6);

        String sequence = reference.substring(9);

        StringBuilder output = new StringBuilder();
        output.append("[")
              .append(bankCode)
              .append("] DATE: ")
              .append(day)
              .append("/")
              .append(month)
              .append("/")
              .append(year)
              .append(" | SEQ: ")
              .append(sequence);

        return output.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Reference Code: ");
        String rawReference = sc.nextLine();

        String normalizedReference = normalizeReference(rawReference);

        System.out.println(
                validateAndFormat(normalizedReference)
        );

        sc.close();
    }
}
