package consoleplayerlibapplication;

import java.util.List;
import java.util.Scanner;

import playerlib.characteristics.Characteristic;
import playerlib.characteristics.ICharacteristic;
import consoleplayerlibapplication.helpers.CharacteristicHelper;

public class CharacteristicManager{

	private Scanner _input;

	public CharacteristicManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option, List<ICharacteristic> characteristics) {
		boolean retVal = true;
		switch(option) {
			case "1":
				createCharacteristic(characteristics);
				break;
			case "2":
				removeCharacteristic(characteristics);
				break;
			case "3":
				modifyCharacteristic(characteristics);
				break;
			case "4":
				listCharacteristics(characteristics);
				break;
			case "5":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is an unknown command.\n", option);
				break;
		}
		return retVal;
	}

	private void createCharacteristic(List<ICharacteristic> characteristics) {
		System.out.print("Name: ");
		String name = _input.nextLine();
		System.out.print("Description: ");
		String description = _input.nextLine();
		System.out.print("Value: ");
		String value = _input.nextLine();
		ICharacteristic character = new Characteristic(name, value, description, null);
		characteristics.add(character);
	}

	private void removeCharacteristic(List<ICharacteristic> characteristics) {
		listCharacteristicNames(characteristics);
		System.out.print("Name of Characteristic to remove: ");
		String removeCharacter = _input.nextLine();

		for(ICharacteristic character : characteristics) {
			if (!character.name().equalsIgnoreCase(removeCharacter)) continue;

			characteristics.remove(character);
			return;
		}
	}

	private void modifyCharacteristic(List<ICharacteristic> characteristics) {
		listCharacteristicNames(characteristics);
		ICharacteristic characteristic = null;

		System.out.print("Name of characterisitc: ");
		String characterName = _input.nextLine();

		for(ICharacteristic character : characteristics) {
			if (!character.name().equalsIgnoreCase(characterName)) continue;

			characteristic = character;
			characteristics.remove(character);
			break;
		}

		if (characteristic == null) {
			System.out.printf("Unable to find %s.\n", characterName);
			return;
		}

		System.out.print("New value: ");
		characteristic.value(_input.nextLine());
		characteristics.add(characteristic);
	}

	private void listCharacteristics(List<ICharacteristic> characteristics) {
		System.out.print(CharacteristicHelper.getCharacteristicsFull(characteristics));
	}

	private void listCharacteristicNames(List<ICharacteristic> characteristics) {
		System.out.printf("Charactersitics: %s\n", CharacteristicHelper.getCharacteristicNames(characteristics));
	}
}
