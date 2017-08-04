package consoleplayerlibapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import playerlib.attributes.*;
import playerlib.characteristics.*;
import playerlib.equipment.*;
import playerlib.inventory.*;
import playerlib.items.*;
import playerlib.player.*;

public class Manager {

	private final Scanner _input;
	private final String _attributeOptions = " 1. Create Attribute \n 2. Remove Attribte \n 3. Modify Attribute \n 4. List Attributes \n 5. Back \n";
	private final String _characteristicOptions = " 1. Create Characteristic \n 2. Remove Characteristic \n 3. Modify Characteristic \n 4. List Characteristics \n 5. Back \n";
	private final String _bodyPartOptions = " 1. Create Body Part \n 2. Remove Body Part \n 3. Modify Body Part \n 4. List Body Parts \n 5. Back \n";
	private final String _propertyOptions = " 1. Create Property \n 2. Remove Property \n 3. Modify Property \n 4. List Properties \n 5. Back \n";
	private final String _itemOptions = " 1. Create Item \n 2. Remove Item \n 3. Modify Item \n 4. List Items \n 5. Back \n";
	private final String _inventoryOptions = " 1. Add Item \n 2. Remove Item \n 3. List Items \n 4. Change amount. \n 5. Back \n";
	private final String _equipmentOptions = " 1. Add Item \n 2. Remove Item \n 3. List Items \n 4. Back \n";
	private final String _playerOptions = " 1. Create Player \n 2. Add Attribute \n 3. Remove Attribute \n 4. Add Body Part \n 5. Remove Body Part \n 6. Add Characteristic \n 7. Remove Characteristic \n 8. List details \n 9. Back \n";

	private AttributeManager _attributeManager;
	private CharacteristicManager _characteristicManager;
	private BodyPartManager _bodyPartManager;
	private PropertyManager _propertyManager;
	private ItemManager _itemManager;
	private InventoryManager _inventoryManager;
	private EquipmentManager _equipmentManager;
	private PlayerManager _playerManager;

	private List<IAttribute> _attributes;
	private List<ICharacteristic> _characteristics;
	private List<IBodyPart> _bodyParts;
	private List<IProperty> _properties;
	private List<IItem> _items;
	private IInventory _inventory;
	private IEquipment _equipment;
	private IPlayer _player;

	public Manager() {
		_input = new Scanner(System.in);
		_attributeManager = new AttributeManager(_input);
		_characteristicManager = new CharacteristicManager(_input);
		_bodyPartManager = new BodyPartManager(_input);
		_propertyManager = new PropertyManager(_input);
		_itemManager = new ItemManager(_input);
		_inventoryManager = new InventoryManager(_input);
		_equipmentManager = new EquipmentManager(_input);
		_playerManager = new PlayerManager(_input);
		_attributes = new ArrayList<IAttribute>();
		_characteristics = new ArrayList<ICharacteristic>();
		_bodyParts = new ArrayList<IBodyPart>();
		_properties = new ArrayList<IProperty>();
		_items = new ArrayList<IItem>();
		_inventory = new Inventory();
		_equipment = new Equipment();
		_player = new Player("", null, _inventory, _equipment, new ArrayList<IAttribute>(),
				new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
	}

	public void manageAttributes() {
		System.out.println(_attributeOptions);
		String o = _input.nextLine();
		if (_attributeManager.execute(o, _attributes)) {
			manageAttributes();
		}
	}

	public void manageCharacteristics() {
		System.out.println(_characteristicOptions);
		String o =_input.nextLine();
		if (_characteristicManager.execute(o, _characteristics)) {
			manageCharacteristics();
		}
	}

	public void manageBodyParts() {
		System.out.println(_bodyPartOptions);
		String o = _input.nextLine();
		if (_bodyPartManager.execute(o, _bodyParts, _characteristics)) {
			manageBodyParts();
		}
	}

	public void manageProperties() {
		System.out.println(_propertyOptions);
		String o = _input.nextLine();
		if (_propertyManager.execute(o, _properties)) {
			manageProperties();
		}
	}

	public void manageItems() {
		System.out.println(_itemOptions);
		String o = _input.nextLine();
		if (_itemManager.execute(o, _items, _properties)) {
			manageItems();
		}
	}

	public void manageInventory() {
		System.out.println(_inventoryOptions);
		String o = _input.nextLine();
		if (_inventoryManager.execute(o, _player.inventory(), _items)) {
			manageInventory();
		}
	}

	public void manageEquipment() {
		System.out.println(_equipmentOptions);
		String o = _input.nextLine();
		if (_equipmentManager.execute(o, _player.equipment(), _items, _bodyParts)) {
			manageEquipment();
		}
	}

	public void managePlayer() {
		System.out.println(_playerOptions);
		String o = _input.nextLine();
		if (_playerManager.execute(o, _player, _attributes, _bodyParts, _characteristics)) {
			managePlayer();
		}
	}
}
