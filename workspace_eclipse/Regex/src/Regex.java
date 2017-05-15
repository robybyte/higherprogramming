import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Regex {
	public static void main(String[] args) {
		//vertex{id: "0",xPos: "-48.62304862717076",yPos: "-8.859780913382075",zPos: "-107.94486655853689",color: "#009669"}
		Pattern pattern = Pattern.compile("vertex\\{id:\"[0-9]+\","
				+ "xPos:\"(-)[0-9]+.[0-9]+\","
				+ "yPos:\"(-)[0-9]+.[0-9]+\","
				+ "zPos:\"(-)[0-9]+.[0-9]+\","
				+ "color:\"#[0-9A-Fa-f]{6}?\"\\}");
		Pattern edgePattern = Pattern.compile("edge\\{from:\"[0-9]+\",to:\"[0-9]*\"\\}");
		String s  = "vertex{id:\"1\",xPos:\"0.0\",yPos:\"1.1\",zPos:\"3.3\",color:\"#00ffee\"}";
		System.out.println("Line: " + s);
		
		s = s.substring(s.indexOf("\"") + 1);
		s = s.substring(0, s.indexOf("\""));
		
		
		System.out.println("Line: " + s);
		Matcher m = pattern.matcher("vertex{id:\"111\","
				+ "xPos:\"-48.62304862717076\","
				+ "yPos:\"-8.859780913382075\","
				+ "zPos:\"-107.94486655853689\","
				+ "color:\"#0099AA\"}");
		Matcher m2 = edgePattern.matcher("edge{from:\"2\",to:\"3\"}");
		String input = "";
	//	Scanner scanner = new Scanner(System.in);
		//input = scanner.nextLine();
	//m = pattern.matcher(input);
	//	m2 = edgePattern.matcher(input);
		if(m.matches()) 
			System.out.println("Match!");
		else 
			System.out.println("No Match!");
		
		if(m2.matches()) 
			System.out.println("Match!");
		else 
			System.out.println("No Match!");
	}
}
