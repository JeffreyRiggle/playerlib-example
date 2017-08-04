package consoleplayerlibapplication;

import java.util.List;
import java.util.Scanner;

import consoleplayerlibapplication.helpers.PropertyHelper;
import playerlib.items.IProperty;
import playerlib.items.Property;

public class PropertyManager {

	private Scanner _input;

	public PropertyManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option, List<IProperty> properties) {
		boolean retVal = true;
		switch(option) {
			case "1":
				createProperty(properties);
				break;
			case "2":
				removeProperty(properties);
				break;
			case "3":
				modifyProperty(properties);
				break;
			case "4":
				listProperties(properties);
				break;
			case "5":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is a unknown command.\n", option);
		}
		return retVal;
	}

	private void createProperty(List<IProperty> properties) {
		System.out.print("Name: ");
		String name = _input.nextLine();
		System.out.print("Description: ");
		String description = _input.nextLine();
		System.out.print("Value: ");
		String value = _input.nextLine();
		IProperty prop = new Property(name, description, value);
		properties.add(prop);
	}

	private void removeProperty(List<IProperty> ref) {
		listPropertyNames(ref);
		System.out.print("Name of property to remove: ");
		String propertyName = _input.nextLine();

		for (IProperty prop : ref) {
			if (!prop.name().equalsIgnoreCase(propertyName)) continue;
			ref.remove(prop);
			break;
		}
	}

	private void modifyProperty(List<IProperty> ref) {
		listPropertyNames(ref);
		IProperty property = null;
		System.out.print("Name of property to modify: ");
		String propertyName = _input.nextLine();

		for (IProperty prop : ref) {
			if (!prop.name().equalsIgnoreCase(propertyName)) continue;
			property = prop;
			ref.remove(prop);
			break;
		}

		if (property == null) {
			return;
		}

		System.out.print("New Value: ");
		property.value(_input.nextLine());
		ref.add(property);
	}

	private void listProperties(List<IProperty> properties) {
		System.out.printf("Properties: %s \n", PropertyHelper.getPropertiesFull(properties));
	}

	private void listPropertyNames(List<IProperty> properties) {
		System.out.printf("Properties: %s \n", PropertyHelper.getPropertyNames(properties));
	}
}
