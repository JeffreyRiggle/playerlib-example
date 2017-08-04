package consoleplayerlibapplication.helpers;

import java.util.List;

import playerlib.items.IItem;

public class ItemHelper {

	public static String getItemNames(List<IItem> items) {
		String itemNames = new String();
		for (int i = 0; i < items.size(); i ++) {
			itemNames += items.get(i).name();
			if (i != items.size()-1) {
				itemNames += ", ";
			}
		}
		return itemNames;
	}

	public static String getItemsFull(List<IItem> items) {
		String itemsFull = new String();
		for (IItem item : items) {
			itemsFull += formatItem(item);
		}
		return itemsFull;
	}

	public static String formatItem(IItem item) {
		String formattedItem = new String();
		formattedItem += String.format("Name: %s, Description: %s \n", item.name(), item.description());
		formattedItem += "**Properties** \n";
		formattedItem += PropertyHelper.getPropertyNames(item.properties());
		formattedItem += "\n";
		return formattedItem;
	}
	public static IItem findItem(String itemName, List<IItem> items) {
		IItem foundItem = null;
		for (IItem item : items) {
			if (!item.name().equalsIgnoreCase(itemName)) continue;
			foundItem = item;
			break;
		}
		return foundItem;
	}
}
