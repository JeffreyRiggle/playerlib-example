package consoleplayerlibapplication.helpers;

import playerlib.inventory.IInventory;
import playerlib.items.IItem;

public class InventoryHelper {

	public static String getInventory(IInventory inventory) {
		String inventoryString = new String();
		inventoryString += "Items: ";
		for (IItem item : inventory.items()) {
			inventoryString += ItemHelper.formatItem(item);
			inventoryString += String.format("**Amount** \n %s \n", inventory.getAmount(item));
		}
		return inventoryString;
	}
}
