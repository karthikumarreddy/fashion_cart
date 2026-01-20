package commandpattern;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class VehicleFactory {
	static Properties prop = new Properties();

	public static Vehicle getVehicle(String type) {
		if (type == null) {

			throw new IllegalArgumentException("Vehicle type cannot be null or empty");
		}
		System.out.println("type : " + type);

		Vehicle v;

		switch (type.toUpperCase()) {
		case "C":
			v = new Car();
			break;
		case "B":
			v = new Bike();
			break;
		case "T":
			v = new Truck();
			break;
		default:
			throw new IllegalArgumentException("Unknown vehicle type: " + type);
		}

		return v;
	}
	static {
		try {
			InputStream input = VehicleFactory.class.getClassLoader().getResourceAsStream("commandpattern/vehicle.properties");
			System.out.println(input);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Vehicle getVehicle2(String type) {
		Vehicle vehicle = null;

		
		String strClassName = prop.getProperty(type);
		if (strClassName == null) {
			System.out.println("no such vehicle defined ");
		} else {
			try {
				Class<?> cls = Class.forName(strClassName);
				vehicle = (Vehicle) cls.getDeclaredConstructor().newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				return null;
			}
		}
		return vehicle;
	}
}
