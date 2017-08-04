package consoleplayerlibapplication.helpers;

import java.util.List;

import playerlib.equipment.IBodyPart;

public class BodyPartHelper {

	public static String getBodyPartNames(List<IBodyPart> bodyParts) {
		String bodyPartNames = new String();
		for (int i = 0; i < bodyParts.size(); i ++) {
			bodyPartNames += bodyParts.get(i).name();
			if (i != bodyParts.size()-1) {
				bodyPartNames += ", ";
			}
		}
		return bodyPartNames;
	}

	public static String getBodyPartsFull(List<IBodyPart> bodyParts) {
		String bodyPartsFull = new String();
		for (IBodyPart bodyPart : bodyParts) {
			bodyPartsFull += String.format("Name: %s, Description: %s \n", bodyPart.name(), bodyPart.description());
			bodyPartsFull += "**Characteristics** \n";
			bodyPartsFull += CharacteristicHelper.getCharacteristicNames(bodyPart.getCharacteristics());
			bodyPartsFull += "\n";
		}
		return bodyPartsFull;
	}

	public static IBodyPart findBodyPart(String bodyPartName, List<IBodyPart> bodyParts) {
		IBodyPart foundPart = null;
		for (IBodyPart bodyPart : bodyParts) {
			if (!bodyPart.name().equalsIgnoreCase(bodyPartName)) continue;
			foundPart = bodyPart;
			break;
		}
		return foundPart;
	}
}
