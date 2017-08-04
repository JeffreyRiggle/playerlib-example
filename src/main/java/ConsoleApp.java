package consoleplayerlibapplication;

public class ConsoleApp {

	private Chooser _choice;
	private Manager _manager;

	public ConsoleApp() {
		_choice = new Chooser();
		_manager = new Manager();
	}

	public void run() {
		boolean running = true;
		String c = _choice.getChoice();
		try {
			switch (c) {
				case "1":
					_manager.manageAttributes();
					break;
				case "2":
					_manager.manageCharacteristics();
					break;
				case "3":
					_manager.manageBodyParts();
					break;
				case "4":
					_manager.manageProperties();
					break;
				case "5":
					_manager.manageItems();
					break;
				case "6":
					_manager.manageInventory();
					break;
				case "7":
					_manager.manageEquipment();
					break;
				case "8":
					_manager.managePlayer();
					break;
				case "9":
					running = false;
					break;
				default:
					System.out.printf("{0} is an unknown command.\n", c);
					break;
				}
			} catch (Exception e) {
				System.out.printf("Unexpected exception, %s\n", e.toString());
			}
		if (running) run();
	}
}
