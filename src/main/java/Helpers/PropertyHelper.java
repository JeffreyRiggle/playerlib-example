package consoleplayerlibapplication.helpers;

import java.util.List;

import playerlib.items.IProperty;

public class PropertyHelper {

	public static String getPropertiesFull(List<IProperty> properties) {
		String propertiesFull = new String();
		for (IProperty prop : properties) {
			propertiesFull += String.format("Name: %s, Description: %s, Value: %s \n", prop.name(), prop.description(), prop.value());
		}
		return propertiesFull;
	}

	public static String getPropertyNames(List<IProperty> properties) {
		String propertyNames = new String();
		for (int i = 0; i < properties.size(); i++) {
			propertyNames += properties.get(i).name();
			if (i != properties.size()-1) {
				propertyNames += ", ";
			}
		}
		return propertyNames;
	}

	public static IProperty findProperty(String propertyName, List<IProperty> properties) {
		IProperty foundProperty = null;
		for(IProperty property : properties) {
			if (!property.name().equalsIgnoreCase(propertyName)) continue;
			foundProperty = property;
			break;
		}
		return foundProperty;
	}
}
