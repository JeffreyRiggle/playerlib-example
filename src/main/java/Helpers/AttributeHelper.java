package consoleplayerlibapplication.helpers;

import java.util.List;

import playerlib.attributes.IAttribute;

public class AttributeHelper {

	public static String getAttributeNames(List<IAttribute> attributes) {
		String attributeNames = new String();
		for (int i = 0; i < attributes.size(); i++) {
			attributeNames += attributes.get(i).name();
			if (i != attributes.size()-1) {
				attributeNames += ", ";
			}
		}
		return attributeNames;
	}

	public static String getAttributesFull(List<IAttribute> attributes) {
		String fullAttributes = new String();
		for (IAttribute attribute : attributes) {
			fullAttributes += String.format("Name: %s, Description: %s, Value: %s \n",
					attribute.name(), attribute.description(), attribute.value());
		}
		return fullAttributes;
	}
}
