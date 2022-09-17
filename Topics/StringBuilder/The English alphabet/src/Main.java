
class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        String letter;
        for (int i = 0; i < 26; i ++) {
            letter = source.charAt(i) + " ";
            result.append(letter);
        }
        result.deleteCharAt(result.length() - 1);
        return result;
    }
}