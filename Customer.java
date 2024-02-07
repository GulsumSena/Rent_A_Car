package customer;

import java.sql.SQLException;
import java.util.Scanner;

import Manager.ManagerIslem;
import car.CarIslem;

public class Customer {

	private String name;
	
	public Customer(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	public static void menu(Customer customer) throws SQLException {
		Scanner scn=new Scanner(System.in);
		while(true) {
		System.out.println("\nİşlem Seçiniz: ");
		System.out.println("0 - Çıkış");
		System.out.println("1 - Araç Listele");
		System.out.println("2 - Araç Rezerve Et");
		System.out.println("3 - Araç Teslim Et");
		
		int secim=scn.nextInt();
		
		if(secim==0) {
			System.out.println("Menüden çıkılıyor...");
			break;
		}
		
		switch (secim) {
		case 1: {
			CarIslem.listCar();
			break;
		}
		case 2: {
			CustomerIslem.rezerveEt(CarIslem.pickCar(), customer);
			break;
		}
		case 3: {
			CustomerIslem.iadeEt(customer);
			break;
		}
		
		default:
			System.out.println("Tanımsız işlem");
		}
	}
	}
}
