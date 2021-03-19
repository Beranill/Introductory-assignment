import java.util.Stack;

/**
 *  @author Kravchenko Daniil
 */
public class Main {
    public static void main(String[] args) {
        String string = "2[3[x]y]z";
        //3[xyz]4[xy]z    2[3[x]y]z    zxy3[x]2[2[y]]z
        if (!string.isEmpty()) {
            char[] arrayOfChars = string.toCharArray();
            System.out.println(arrayOfChars);
            String result = "";
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < arrayOfChars.length; i++) {
                if (isDigit(arrayOfChars[i])) {
                    stack.push((int) arrayOfChars[i] - '0');
                    String sub = "";
                    if (isLetter(arrayOfChars[i + 2])) {
                        int num = stack.pop();
                        i = i + 2;
                        while (arrayOfChars[i] != ']') {
                            sub += arrayOfChars[i];
                            i++;
                        }
                        sub = stringMultiply(sub, num);
                        if (stack.isEmpty()) {
                            result += sub;
                        } else if (!stack.isEmpty()) {
                            i++;
                            while (arrayOfChars[i] != ']') {
                                sub += arrayOfChars[i];
                                i++;
                            }
                            num = stack.pop();
                            result += stringMultiply(sub, num);
                        }
                    } else {
                        i = i++;
                    }
                } else if (isLetter(arrayOfChars[i])) {
                    result += arrayOfChars[i];
                }
            }

            System.out.println("Результат распаковки: " + result);
        }else {
            System.out.println("Строка пустая!");
        }
    }

    public static boolean isDigit(char c){
        return Character.isDigit(c);
    }

    public static boolean isLetter(char c){
        return Character.isLetter(c);
    }

    public static String stringMultiply(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(s);
        }
        return sb.toString();
    }
}
