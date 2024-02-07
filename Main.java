import java.sql.SQLException;
import java.util.Scanner;

import Manager.Manager;
import car.Renk;
import customer.Customer;
import customer.CustomerIslem;

public class Main {

	public static void main(String[] args) throws SQLException {

		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.println("\n[0] Çıkış" + "\n[1] Müşteri Girişi " + "\n[2] Yönetici Girişi");
			int enter = in.nextInt();

			if (enter == 0) {
				System.out.println(Renk.CYAN + "BYE" + Renk.RESET);
				break;
			} else {
				switch (enter) {
				case 1: {
					Customer.menu(CustomerIslem.signUp());
					break;
				}
				case 2: {
					Manager.menu();
					break;
				}
				default:
					System.out.println("Hatalı Tuşlama");
					break;
				}
			}
		}
	}
}
