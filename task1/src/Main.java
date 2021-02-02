public class Main {

    static String USAGE =
            "Usage: inputNumber<unsigned int>  base<String> \n " +
            "         (dec -> base)\n" +
            "   or inputNumber<String> baseSrc<String> BaseDst<String>\n" +
            "         (baseSrc -> baseDst)";

    public static void main(String[] args) {
        try {
            switch (args.length) {
                case 2:
                    //if base contain duplicate characters, program will print usage
                    System.out.println(itoBase(Integer.parseInt(args[0]), args[1]) != null ? itoBase(Integer.parseInt(args[0]), args[1]) : USAGE);
                    break;
                case 3:
                    //if base contain duplicate characters or character not found in base, program will print usage
                    System.out.println(itoBase(args[0], args[1], args[2]) != null ? itoBase(args[0], args[1], args[2]) : USAGE);
                    break;
                default:
                    System.out.println(USAGE);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(USAGE);
        }

    }

    static String itoBase(int nb, String base) {

        String result = "";

        //base should not contain duplicate characters
        if (hasDuplicate(base)) {
            return null;
        } else {
            while (nb >= base.length()) {
                result = base.charAt(nb % base.length()) + result;
                nb /= base.length();
            }
            result = base.charAt(nb) + result;

            return result;
        }
    }

    static String itoBase(String nb, String baseSrc, String baseDst) {

        String result = "";

        //base should not contain duplicate characters
        if (hasDuplicate(baseDst) || hasDuplicate(baseSrc)) {
            return null;
        } else {
            //nb in baseSrc -> nb in dec
            int newNb = 0;
            for (int i = 0; i < nb.length(); i++) {
                int newDigit = (int) (baseSrc.indexOf(nb.charAt(i)) * Math.pow(baseSrc.length(), nb.length() - i - 1));
                if (newDigit < 0) {
                    //character not found in base
                    return null;
                }
                newNb += newDigit;
            }
            result = itoBase(newNb, baseDst);


            return result;
        }
    }


    static boolean hasDuplicate(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

}
