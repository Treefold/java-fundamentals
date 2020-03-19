package string;

public class Main {

    static String membruClasa;

    public static void main (String[] args) {
        System.out.println(membruClasa);

        String s1 = "";
        String s2 = "abc";

        System.out.println(s2);
//        System.out.println(s2.toUpperCase());
//        System.out.println(s2);
//        s2 = s2.toUpperCase();
//        System.out.println(s2);

        String s3 = new String("abc");
//        System.out.println(s2 == "abc");
//        System.out.println(s3 == "abc");
//        System.out.println(s2 == s3);
//        System.out.println(s2.equals(s3));
//        System.out.println(s3.equals(s2));
//        System.out.println("abc".equals(s3));
//        System.out.println(s3.equals("abc"));
        s3 = s3.intern();
        System.out.println(s2 == "abc");
        System.out.println(s3 == "abc");
        System.out.println(s2 == s3);

        String s4 = "a\\b \n de\tf";
        System.out.println(s4);

        String adresa = s1 + s2 + s3.toUpperCase() + s4.length();
        System.out.println(adresa);
        StringBuilder sb = new StringBuilder(adresa);
        sb.append(1234);
        System.out.println(sb);
        StringBuffer sbf = new StringBuffer(adresa);
        StringBuffer sbf1 = new StringBuffer(sb);
        StringBuilder sb1 = new StringBuilder(sbf);
        System.out.println(sbf);
        System.out.println(sbf1);
        System.out.println(sb1);
    }
}
