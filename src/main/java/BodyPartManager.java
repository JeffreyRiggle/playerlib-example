package consoleplayerlibapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import playerlib.characteristics.ICharacteristic;
import consoleplayerlibapplication.helpers.*;
import playerlib.equipment.BodyPart;
import playerlib.equipment.IBodyPart;

public class BodyPartManager{

	private Scanner _input;

	public BodyPartManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option, List<IBodyPart> bodyParts, List<ICharacteristic> characteristics) {
		boolean retVal = true;
		switch(option) {
			case "1":
				createBodyPart(bodyParts, characteristics);
				break;
			case "2":
				removeBodyPart(bodyParts);
				break;
			case "3":
				modifyBodyPart(bodyParts, characteristics);
				break;
			case "4":
				listBodyParts(bodyParts);
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

	private void createBodyPart(List<IBodyPart> bodyParts, List<ICharacteristic> characters) {
		System.out.print("Name: ");
		String name = _input.nextLine();
		System.out.print("Description: ");
		String description = _input.nextLine();
		List<ICharacteristic> characteristics = getCharacteristics(characters);
		IBodyPart bodyPart = new BodyPart(name, description, characteristics);
		bodyParts.add(bodyPart);
	}

	private void removeBodyPart(List<IBodyPart> bodyParts) {
		listBodyPartNames(bodyParts);
		System.out.print("Name of body part to remove: ");

		String bodyPartName = _input.nextLine();
		IBodyPart removePart = BodyPartHelper.findBodyPart(bodyPartName, bodyParts);
		if (removePart != null) {
			bodyParts.remove(removePart);
		}
	}

	private void modifyBodyPart(List<IBodyPart> bodyParts, List<ICharacteristic> characters) {
		IBodyPart bod = null;

		listBodyParts(bodyParts);
		System.out.print("Name of body part to modify: ");
		String modPart = _input.nextLine();

		bod = BodyPartHelper.findBodyPart(modPart, bodyParts);

		if (bod == null) return;

		System.out.print("Clearing Characteristics...");
		List<ICharacteristic> chars = getCharacteristics(characters);
		bod.clearCharacteristics();
		bod.addCharacteristics(chars);
		bodyParts.add(bod);
	}

	private List<ICharacteristic> getCharacteristics(List<ICharacteristic> characteristics) {
		List<ICharacteristic> retChars = new ArrayList<ICharacteristic>();
		System.out.println(CharacteristicHelper.getCharacteristicNames(characteristics));

		while (true) {
			System.out.print("Name of characteristic to add(~to end): ");
			String characteristicName = _input.nextLine();

			if (characteristicName.equals("~")) break;

			ICharacteristic addCharacter = CharacteristicHelper.findCharacteristic(characteristics, characteristicName);
			if (addCharacter != null) {
				retChars.add(addCharacter);
			}
		}

		return retChars;
	}

	private void listBodyParts(List<IBodyPart> bodyParts) {
		System.out.printf("Body Parts: %s \n", BodyPartHelper.getBodyPartsFull(bodyParts));
	}

	private void listBodyPartNames(List<IBodyPart> bodyParts) {
		System.out.printf("Body Parts: %s \n", BodyPartHelper.getBodyPartNames(bodyParts));
	}
}
