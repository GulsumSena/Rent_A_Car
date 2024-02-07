package Manager;

import java.sql.SQLException;
import java.util.Scanner;

import car.Car;
import car.CarIslem;

public class Manager {
	private static String user;
	private static String pass;
	
	public Manager(String user, String pass) {
		this.pass=pass;
		this.user=user;
	}
	
	
	public static void menu() throws SQLException {
		Scanner scn=new Scanner(System.in);
		while(true) {
		System.out.println("\nİşlem Seçiniz: ");
		System.out.println("0 - Çıkış");
		System.out.println("1 - Araç Listele");
		System.out.println("2 - Araç Ekle");
		System.out.println("3 - Araç Sil");
		System.out.println("4 - Araç Güncelle");
		
		int secim=scn.nextInt();
		
		if(secim==0) {
			System.out.println("Menüden çıkılıyor...");
			break;
		}
		
		switch (secim) {
		case 1: {
			ManagerIslem.listCarbyManager();
			break;
		}
		case 2: {
			ManagerIslem.addCar();
			break;
		}
		case 3: {
			ManagerIslem.deleteCar(CarIslem.ManagerpickCar());
			break;
		}
		case 4: {
			ManagerIslem.updateCar(CarIslem.ManagerpickCar());
			break;
		}
		
		default:
			System.out.println("Tanımsız işlem");
		}
	}
	}
}
