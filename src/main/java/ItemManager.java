package consoleplayerlibapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleplayerlibapplication.helpers.ItemHelper;
import consoleplayerlibapplication.helpers.PropertyHelper;
import playerlib.items.IItem;
import playerlib.items.IProperty;
import playerlib.items.Item;

public class ItemManager {

	private Scanner _input;

	public ItemManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option, List<IItem> items, List<IProperty> properties) {
		boolean retVal = true;
		switch(option) {
			case "1":
				createItem(items, properties);
				break;
			case "2":
				removeItem(items);
				break;
			case "3":
				modifyItem(items, properties);
				break;
			case "4":
				listItems(items);
				break;
			case "5":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is an invalid command.\n", option);
				break;
		}
		return retVal;
	}

	private void createItem(List<IItem> items, List<IProperty> properties) {
		IItem item = null;
		System.out.print("Name: ");
		String name = _input.nextLine();
		System.out.print("Description: ");
		String description = _input.nextLine();
		List<IProperty> props = getProperties(properties);
		item = new Item(props, name, description, null);
		items.add(item);
	}

	private void removeItem(List<IItem> items) {
		listItemNames(items);
		System.out.print("Item to remove: ");
		String removeItem = _input.nextLine();
		IItem removedItem = ItemHelper.findItem(removeItem, items);
		if (removedItem != null) {
			items.remove(removedItem);
		}
	}

	private void modifyItem(List<IItem> items, List<IProperty> properties) {
		listItemNames(items);
		IItem itm = null;
		System.out.print("Item to modify: ");
		String modItem = _input.nextLine();

		itm = ItemHelper.findItem(modItem, items);
		if (itm == null) {
			return;
		}

		items.remove(itm);

		System.out.print("Clearing Properties...");
		itm.clearProperties();
		itm.addProperties(getProperties(properties));
		items.add(itm);
	}

	private void listItems(List<IItem> items) {
		System.out.printf("Items: %s \n", ItemHelper.getItemsFull(items));
	}

	private List<IProperty> getProperties(List<IProperty> properties) {
		List<IProperty> retProps = new ArrayList<IProperty>();
		listProperties(properties);
		while (true) {
			System.out.print("Name of property to add(~to end): ");
			String propertyName = _input.nextLine();

			if (propertyName.equals("~")) break;

			IProperty addProp = PropertyHelper.findProperty(propertyName, properties);
			if (addProp != null) {
				retProps.add(addProp);
			}
		}

		return retProps;
	}

	private void listItemNames(List<IItem> items) {
		System.out.printf("Items: %s \n", ItemHelper.getItemNames(items));
	}

	private void listProperties(List<IProperty> properties) {
		System.out.printf("Properties: %s \n", PropertyHelper.getPropertyNames(properties));
	}
}
