package interviewPracticeQuestions.javaInterviewQuestions;

public class PasswordValidation {

    public static void main(String[] args) {

        System.out.println(verifyValidPassword("A12*saDd"));
    }

    /**
     * 1. Write a return method that can verify if a password is valid or not.
     * requirements:
     * 1. Password MUST be at least have 6 characters and should not contain space
     * 2. PassWord should at least contain one upper case letter
     * 3. PassWord should at least contain one lowercase letter
     * 4. Password should at least contain one special characters
     * 5. Password should at least contain a digit
     * <p>
     * if all requirements above are met, the method returns true, otherwise returns  false
     */

    //1.WAy
    public static boolean verifyValidPassword(String password) {
        char[] passChar = password.toCharArray();
        //1. Password MUST be at least have 6 characters and should not contain space
        if (passChar.length < 6 || password.contains(" ")) {
            return false;
        }
        //2. hasSpace, hasUpper, hasLower, hasSpecial, hasDigit
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;
        boolean hasDigit = false;
        for (Character c : passChar) {
//            if (Character.isSpaceChar(c)) {  //  c == 32 --> 32 Dec value from ASCII  // or c==' ' // or c.equals(' ')
//                return false;
//            }
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isLowerCase(c)) {
                hasLower = true;
            }
            if (!(Character.isAlphabetic(c) || Character.isLetter(c) || Character.isDigit(c) || c <= ' ')) { //  c == 32
                hasSpecial = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return (hasUpper && hasLower && hasSpecial && hasDigit);
    }


    //2.Way  REGULAR EXPRESSION    Reg Ex
    public static boolean PassWordvalidation(String password) {

        String lowercase="(.*[a-z].*)";
        String uppercase="(.*[A-Z].*)";
        String numbers="(.*[0-9].*)";
        String specialchars="(.*[ -/, :-@].*)";

        boolean  HasLower = password.matches(lowercase),
                HasUppere = password.matches(uppercase),
                HasDigits = password.matches(numbers),
                HasSpecial = password.matches(specialchars),
                Valid = false;

        if(password.length() >= 6 && !password.contains(" "))
            if( HasLower && HasUppere && HasDigits && HasSpecial)
                Valid = true;

        return Valid;
    }



}
