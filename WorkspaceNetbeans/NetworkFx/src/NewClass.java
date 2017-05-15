
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rgn
 */
public class NewClass {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("vertex\\{");
        Matcher m = p.matcher("vertex{");
        System.out.println("value: " + (int)"{".toCharArray()[0]);
        System.out.println(p.toString() + " : " + m.toString() + "\nMatch: " + (m.matches() ? " YES!": "NO!"));
    }
}
