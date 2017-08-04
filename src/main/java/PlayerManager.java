package consoleplayerlibapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import playerlib.attributes.IAttribute;
import playerlib.characteristics.ICharacteristic;
import playerlib.equipment.IBodyPart;
import playerlib.equipment.IEquipment;
import playerlib.inventory.IInventory;
import playerlib.items.*;
import playerlib.player.IPlayer;

public class PlayerManager {

	private Scanner _input;

	public PlayerManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option,
			IPlayer player, List<IAttribute> attributes,
			List<IBodyPart> bodyParts, List<ICharacteristic> characteristics) {
		boolean retVal = true;
		switch(option) {
			case "1":
				createPlayer(player, attributes, bodyParts, characteristics);
				break;
			case "2":
				addAttribute(player, attributes);
				break;
			case "3":
				removeAttribute(player);
				break;
			case "4":
				addBodyPart(player, bodyParts);
				break;
			case "5":
				removeBodyPart(player);
				break;
			case "6":
				addCharacteristic(player, characteristics);
				break;
			case "7":
				removeCharacteristic(player);
				break;
			case "8":
				listPlayer(player);
				break;
			case "9":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is an unknown command. \n", option);
				break;
		}
		return retVal;
	}

	private void listPlayer(IPlayer player) {
		System.out.println("*****Player*****");
		System.out.printf("Name: %s", player.name());
		System.out.println("\n**BodyParts**");
		printBodyPartsFull(player.bodyParts());
		System.out.println("\n**Characteristics**");
		printCharacteristicsFull(player.characteristics());
		System.out.println("\n**Attributes**");
		printAttributesFull(player.attributes());
		System.out.println("\n**Inventory**");
		printInventoryFull(player.inventory());
		System.out.println("\n**Equipment**");
		printEquipment(player.equipment(), player.bodyParts());
	}

	private void printEquipment(IEquipment equipment, List<IBodyPart> parts) {
		for (IItem item : equipment.equipted()) {
			System.out.printf("Name: %s, Description: %s, BodyPart: %s",
					item.name(), item.description(), findBodyPart(item, equipment, parts));
			System.out.println("    **Properties** ");
			printProperties(item.properties());
		}
	}

	private void printInventoryFull(IInventory inventory) {
		for(IItem item : inventory.items()) {
			System.out.printf("Name: %s, Description: %s, Amount: %s",
					item.name(), item.description(), inventory.getAmount(item));
			System.out.println("    **Properties** ");
			printProperties(item.properties());
		}
	}

	private void printBodyPartsFull(List<IBodyPart> bodyParts) {
		for (IBodyPart part : bodyParts) {
			System.out.printf("Name: %s, Description: %s\n", part.name(), part.description());
			System.out.println("    **Characteristics** ");
			printCharacteristicsFull(part.getCharacteristics());
		}
	}

	private void printCharacteristicsFull(List<ICharacteristic> characteristics) {
		for (ICharacteristic characteristic : characteristics) {
			System.out.printf("Name: %s, Description: %s, Value: %s",
					characteristic.name(), characteristic.description(), characteristic.value());
		}
	}

	private void printAttributesFull(List<IAttribute> attributes) {
		for (IAttribute attribute : attributes) {
			System.out.printf("Name: %s, Description: %s, Value: %s",
					attribute.name(), attribute.description(), attribute.value());
		}
	}

	private void printProperties(List<IProperty> properties) {
		for (IProperty property : properties) {
			System.out.printf("Name: %s, Description: %s, Value: %s",
					property.name(), property.description(), property.value());
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

	private void removeCharacteristic(IPlayer player) {
		listCharacteristicNames(player.characteristics());
		System.out.print("Characteristic to remove: ");
		String removeChar = _input.nextLine();
		ICharacteristic characteristic = getCharacteristic(removeChar, player.characteristics());
		if (characteristic != null) {
			player.removeCharacteristic(characteristic);
		}
	}

	private ICharacteristic getCharacteristic(String removeChar, List<ICharacteristic> characteristics) {
		ICharacteristic character = null;
		for (ICharacteristic characteristic : characteristics) {
			if (!characteristic.name().equalsIgnoreCase(removeChar)) continue;
			character = characteristic;
		}
		return character;
	}

	private void listCharacteristicNames(List<ICharacteristic> characteristics) {
		System.out.print("Characteristics: ");
		for (ICharacteristic characteristic : characteristics) {
			System.out.printf("%s, ", characteristic.name());
		}
		System.out.println();
	}

	private void addCharacteristic(IPlayer player, List<ICharacteristic> characteristics) {
		listCharacteristicNames(characteristics);
		System.out.print("Characteristic to add: ");
		String addChar = _input.nextLine();
		ICharacteristic characteristic = getCharacteristic(addChar, characteristics);
		if (characteristic != null) {
			player.addCharacteristic(characteristic);
		}
	}

	private void removeBodyPart(IPlayer player) {
		listBodyPartNames(player.bodyParts());
		System.out.print("Body Part to remove: ");
		String removePart = _input.nextLine();
		IBodyPart bodyPart = getBodyPart(removePart, player.bodyParts());
		if (bodyPart != null) {
			player.addBodyPart(bodyPart);
		}
	}

	private IBodyPart getBodyPart(String removePart, List<IBodyPart> bodyParts) {
		IBodyPart bPart = null;

		for(IBodyPart part : bodyParts) {
			if (!part.name().equalsIgnoreCase(removePart)) continue;
			bPart = part;
			break;
		}

		return bPart;
	}

	private void listBodyPartNames(List<IBodyPart> bodyParts) {
		System.out.print("Body Parts: ");
		for (IBodyPart bodyPart : bodyParts) {
			System.out.printf("%s, ", bodyPart.name());
		}
		System.out.println();
	}

	private void addBodyPart(IPlayer player, List<IBodyPart> bodyParts) {
		listBodyPartNames(bodyParts);
		System.out.print("Body Part to add: ");
		String addPart = _input.nextLine();
		IBodyPart part = getBodyPart(addPart, bodyParts);
		if (part != null) {
			player.addBodyPart(part);
		}
	}

	private void removeAttribute(IPlayer player) {
		listAttributeNames(player.attributes());
		System.out.print("Attribute to remove: ");
		String removeAtt = _input.nextLine();
		IAttribute attribute = getAttribute(removeAtt, player.attributes());
		if (attribute != null) {
			player.addAttribute(attribute);
		}
	}

	private IAttribute getAttribute(String removeAtt,
			List<IAttribute> attributes) {
		IAttribute atrib = null;
		for (IAttribute att : attributes) {
			if (!att.name().equalsIgnoreCase(removeAtt)) continue;
			atrib = att;
			break;
		}
		return atrib;
	}

	private void listAttributeNames(List<IAttribute> attributes) {
		System.out.print("Attributes: ");
		for (IAttribute att : attributes) {
			System.out.printf("%s, ", att.name());
		}
		System.out.println();
	}

	private void addAttribute(IPlayer player, List<IAttribute> attributes) {
		listAttributeNames(attributes);
		System.out.print("Attribute to add: ");
		String addAtt = _input.nextLine();
		IAttribute attribute = getAttribute(addAtt, player.attributes());
		if (attribute != null) {
			player.removeAttribute(attribute);
		}
	}

	private void createPlayer(IPlayer player, List<IAttribute> attributes,
			List<IBodyPart> bodyParts, List<ICharacteristic> characteristics) {
		System.out.println("Name: ");
		String name = _input.nextLine();
		player.name(name);
		List<IAttribute> att = getAttributes(attributes);
		player.clearAttributes();
		player.addAttributes(att);
		List<ICharacteristic> characters = getCharacteristics(characteristics);
		player.clearCharacteristics();
		player.addCharacteristics(characters);
		List<IBodyPart> parts = getBodyParts(bodyParts);
		player.clearBodyParts();
		player.addBodyParts(parts);
	}

	private List<IAttribute> getAttributes(List<IAttribute> attributes) {
		List<IAttribute> attribs = new ArrayList<IAttribute>();
		listAttributeNames(attributes);
		while(true) {
			System.out.print("Attribute to add(~ to end): ");
			String addAtt = _input.nextLine();

			if (addAtt.equals("~")) break;

			IAttribute att = getAttribute(addAtt, attributes);
			if (att != null) {
				attribs.add(att);
			}
		}
		return attribs;
	}

	private List<IBodyPart> getBodyParts(List<IBodyPart> bodyPartList) {
		List<IBodyPart> bodyParts = new ArrayList<IBodyPart>();
		listBodyPartNames(bodyPartList);
		while(true) {
			System.out.print("Body Part to add(~ to end): ");
			String addPart = _input.nextLine();

			if (addPart.equals("~")) break;

			IBodyPart part = getBodyPart(addPart, bodyPartList);
			if (part != null) {
				bodyParts.add(part);
			}
		}
		return bodyParts;
	}

	private List<ICharacteristic> getCharacteristics(List<ICharacteristic> chars) {
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		listCharacteristicNames(chars);
		while(true) {
			System.out.print("Characteristic to add(~ to end): ");
			String addChar = _input.nextLine();

			if (addChar.equals("~")) break;

			ICharacteristic character = getCharacteristic(addChar, chars);
			if (character != null) {
				characteristics.add(character);
			}
		}
		return characteristics;
	}

}
