package consoleplayerlibapplication;

import java.util.List;
import java.util.Scanner;

import consoleplayerlibapplication.helpers.*;
import playerlib.equipment.IBodyPart;
import playerlib.equipment.IEquipment;
import playerlib.items.IItem;
import playerlib.items.IProperty;

public class EquipmentManager {

	private Scanner _input;

	public EquipmentManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option,
			IEquipment equipment, List<IItem> items, List<IBodyPart> bodyParts) {
		boolean retVal = true;
		switch (option) {
			case "1":
				addItem(equipment, items, bodyParts);
				break;
			case "2":
				removeItem(equipment, bodyParts);
				break;
			case "3":
				listItems(equipment, bodyParts);
				break;
			case "4":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is an unknown command.\n");
				break;
		}

		return retVal;
	}

	private void addItem(IEquipment equipment, List<IItem> items, List<IBodyPart> bodyParts) {
		listItemNames(items);
		System.out.print("Name of Item to add: ");
		String addItemName = _input.nextLine();
		listBodyParts(bodyParts);
		System.out.print("Body Part to add to: ");
		String findPart = _input.nextLine();
		IBodyPart bodyPart = BodyPartHelper.findBodyPart(findPart, bodyParts);
		IItem addItem = ItemHelper.findItem(addItemName, items);

		if (bodyPart != null && addItem != null) {
			equipment.equip(bodyPart, addItem);
		}
	}

	private void removeItem(IEquipment equipment, List<IBodyPart> bodyParts) {
		listBodyParts(bodyParts);
		System.out.print("Name of Body Part to unequip: ");
		String unequipLocation = _input.nextLine();

		IBodyPart bodyPart = BodyPartHelper.findBodyPart(unequipLocation, bodyParts);

		if (bodyPart != null) {
			equipment.unEquip(bodyPart);
		}
	}

	private void listItems(IEquipment equipment, List<IBodyPart> parts) {
		System.out.print("Items: ");
		for (IItem item : equipment.equipted()) {
			System.out.printf("Name: %s, Description: %s, Body Part: %s\n",
					item.name(), item.description(), findBodyPart(item, equipment, parts));
			listProperties(item.properties());
		}
	}

	private String findBodyPart(IItem item, IEquipment equip, List<IBodyPart> parts) {
		IBodyPart part = null;
		for(IBodyPart bp : parts) {
			if (equip.equipted(bp) != item) continue;
			part = bp;
			break;
		}
		if (part == null) {
			return "";
		}

		return part.name();
	}

	private void listBodyParts(List<IBodyPart> bodyParts) {
		System.out.printf("Body Parts: %s \n", BodyPartHelper.getBodyPartNames(bodyParts));
	}

	private void listProperties(List<IProperty> properties) {
		System.out.printf("Properties: %s \n", PropertyHelper.getPropertyNames(properties));
	}

	private void listItemNames(List<IItem> items) {
		System.out.printf("Items: %s \n", ItemHelper.getItemNames(items));
	}
}
