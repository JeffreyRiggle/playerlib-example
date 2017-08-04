package consoleplayerlibapplication;

import java.util.List;
import java.util.Scanner;

import consoleplayerlibapplication.helpers.InventoryHelper;
import consoleplayerlibapplication.helpers.ItemHelper;
import playerlib.inventory.IInventory;
import playerlib.items.IItem;

public class InventoryManager {

	private Scanner _input;

	public InventoryManager(Scanner input) {
		_input = input;
	}

	public boolean execute(String option, IInventory inventory, List<IItem> items) {
		boolean retVal = true;
		switch (option) {
			case "1":
				addItem(inventory, items);
				break;
			case "2":
				removeItem(inventory);
				break;
			case "3":
				listItems(inventory);
				break;
			case "4":
				changeAmount(inventory);
				break;
			case "5":
				retVal = false;
				break;
			default:
				System.out.printf("%s, is an unknown command.\n");
				break;
		}

		return retVal;
	}

	private void addItem(IInventory inventory, List<IItem> items) {
		listItemNames(items);
		System.out.print("Name of Item to add: ");
		String addItemName = _input.nextLine();
		System.out.print("Amount to add: ");
		int amount = _input.nextInt();
		//This is a scanner hack.
		_input.nextLine();

		IItem addItem = ItemHelper.findItem(addItemName, items);

		if (addItem != null) {
			inventory.addItem(addItem, amount);
		}
	}

	private void removeItem(IInventory inventory) {
		listItemNames(inventory.items());
		System.out.print("Name of Item to remove: ");
		String removeItemName = _input.nextLine();

		IItem removeItem = ItemHelper.findItem(removeItemName, inventory.items());

		if (removeItem != null) {
			inventory.removeItem(removeItem);
		}
	}

	private void listItems(IInventory inventory) {
		System.out.print(InventoryHelper.getInventory(inventory));
	}

	private void changeAmount(IInventory inventory) {
		listItemNames(inventory.items());
		System.out.print("Item to change: ");
		String itemName = _input.nextLine();
		System.out.print("New Amount: ");
		int newAmount = _input.nextInt();
		//This is a scanner hack.
		_input.nextLine();

		IItem changeItem = ItemHelper.findItem(itemName, inventory.items());

		if (changeItem != null) {
			inventory.setAmount(changeItem, newAmount);
		}
	}

	private void listItemNames(List<IItem> items) {
		System.out.printf("Items: %s \n", ItemHelper.getItemNames(items));
	}
}
