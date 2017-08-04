package consoleplayerlibapplication;

import java.util.List;
import java.util.Scanner;

import playerlib.attributes.Attribute;
import playerlib.attributes.IAttribute;
import consoleplayerlibapplication.helpers.AttributeHelper;

public class AttributeManager {

	private Scanner _input;

	public AttributeManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option, List<IAttribute> attributes) {
		boolean retVal = true;
		switch (option) {
			case "1":
				createAttribute(attributes);
				break;
			case "2":
				removeAttribute(attributes);
				break;
			case "3":
				modifyAttribute(attributes);
				break;
			case "4":
				listAttributes(attributes);
				break;
			case "5":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is a unknown command.\n", option);
				break;
		}
		return retVal;
	}

	private void createAttribute(List<IAttribute> attributes) {
		System.out.print("Name: ");
		String name = _input.nextLine();
		System.out.println("Description: ");
		String description = _input.nextLine();
		System.out.println("Value: ");
		String value = _input.nextLine();
		IAttribute attribute = new Attribute(name, description, value, null);
		attributes.add(attribute);
	}

	private void modifyAttribute(List<IAttribute> attributes) {
		listAttributeNames(attributes);
		System.out.print("Name of attribute to modify: ");
		IAttribute attribute = null;
		String attributeName = _input.nextLine();
		for(IAttribute a : attributes) {
			if (a.name().equalsIgnoreCase(attributeName)) {
				attribute = a;
				attributes.remove(a);
				break;
			}
		}

		if (attribute == null) {
			System.out.printf("Unable to find %s.", attributeName);
			return;
		}

		System.out.print("New Value: ");
		attribute.value(_input.nextLine());
		attributes.add(attribute);
	}

	private void removeAttribute(List<IAttribute> attributes) {
		listAttributeNames(attributes);
		System.out.print("Name of attribute to remove: ");
		String attributeName = _input.nextLine();
		for(IAttribute a : attributes) {
			if (a.name().equalsIgnoreCase(attributeName)) {
				attributes.remove(a);
				break;
			}
		}
	}

	private void listAttributes(List<IAttribute> attributes) {
		System.out.print(AttributeHelper.getAttributesFull(attributes));
	}

	private void listAttributeNames(List<IAttribute> attributes) {
		System.out.printf("Attributes: %s \n", AttributeHelper.getAttributeNames(attributes));
	}
}
