package consoleplayerlibapplication;

import java.util.Scanner;

public class Chooser {

	private final String _optionText = " 1. Attributes \n 2. Characteristics \n 3. BodyParts \n 4. Properties \n 5. Items \n 6. Inventory \n 7. Equipment \n 8. Player \n 9. Exit";
	private Scanner _input;

	public Chooser () {
		_input = new Scanner(System.in);
	}

	public String getChoice() {
		String c = new String();

		System.out.println(_optionText);
		c = _input.nextLine();
		return c;
	}
}
