package commandpattern;

import java.util.Scanner;

public class VehicleMain {
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			String input=sc.nextLine();
			Vehicle vehicle=VehicleFactory.getVehicle2(input);
			if(input==null) {
				System.out.println("no such vehicle ");
				System.exit(0);
			}
			vehicle.start();
			vehicle.stop();
		}
	}
}
