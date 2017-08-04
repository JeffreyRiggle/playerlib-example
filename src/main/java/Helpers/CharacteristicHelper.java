package consoleplayerlibapplication.helpers;

import java.util.List;

import playerlib.characteristics.ICharacteristic;

public class CharacteristicHelper {

	public static String getCharacteristicsFull(List<ICharacteristic> characteristics) {
		String characteristicsFull = new String();
		for(ICharacteristic character : characteristics) {
			characteristicsFull += String.format("Name: %s, Description: %s, Value: %s \n", character.name(), character.description(), character.value());
		}
		return characteristicsFull;
	}

	public static String getCharacteristicNames(List<ICharacteristic> characteristics) {
		String characteristicNames = new String();
		for (int i = 0; i < characteristics.size(); i++) {
			characteristicNames += characteristics.get(i).name();
			if (i != characteristics.size()-1) {
				characteristicNames += ", ";
			}
		}
		return characteristicNames;
	}

	public static ICharacteristic findCharacteristic(List<ICharacteristic> characteritics, String characteristicName) {
		ICharacteristic retCharacter = null;

		for(ICharacteristic character : characteritics) {
			if (!character.name().equalsIgnoreCase(characteristicName)) continue;

			retCharacter = character;
			break;
		}

		return retCharacter;
	}
}
