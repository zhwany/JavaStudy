package test;

/**
 * @author MaBo
 * @create 2020-03-05 3:31
 */
public class Test {
    public static void main(String[] args) {
        String str = new String("helloworld");
        String newStr = str.replaceAll("hello", "mabo");
        System.out.println("newStr = " + newStr);
        System.out.println("create successfully!");
    }
}
