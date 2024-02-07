package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

import car.Car;
import car.CarIslem;

public class CustomerIslem {

	private static Connection cnn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;
	private static Scanner scn = new Scanner(System.in);

	private static String path = "jdbc:mysql://localhost:3306/rent_a_car";
	private static String user = "root";
	private static String pass = "1234";
	
	public static Customer signUp() {
		System.out.println("Ad-Soyad: ");
		String username=scn.nextLine();
		
		Customer c1=new Customer(username);
		return c1;
	}
	
	public static void rezerveEt(int id, Customer customer) throws SQLException {
		
		cnn = DriverManager.getConnection(path, user, pass);
		
			String q2 = "select cars.is_available from cars where id=" + id;
			st = cnn.createStatement();
			rs = st.executeQuery(q2);
			String kullan = null;

			if (rs.next()) {
				kullan = rs.getString("is_available");
			}

			if (kullan.equalsIgnoreCase("açık")) {
				String q = "update cars set is_available=?, customer_name=? where id=?";
				pst = cnn.prepareStatement(q);
				pst.setString(1, "Kapalı");
				pst.setString(2, customer.getName());
				pst.setInt(3, id);
				int row = pst.executeUpdate();
				if (row > 0) {
					System.out.println("İşleminiz başarılı.");
				}
				
			} else if (kullan.equalsIgnoreCase("kapalı")) {
				System.out.println("Seçtiğiniz araç başka bir müşteride. Başkasını seçin");	
		}

	}

	public static void iadeEt(Customer customer) throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);
		st = cnn.createStatement();
		
		String q = "select * from cars where customer_name='"+customer.getName()+"'";
		rs = st.executeQuery(q);

			if (rs.next()) {
				int Cid = rs.getInt("id");
				String CName = rs.getString("name");
				String CVites = rs.getString("vites");
				String CTur = rs.getString("genre");
			
			
			System.out.println("\nİade edeceğiniz aracın:" + "\nID: " + Cid + "\nModel: " + CName + "\nVites: " + CVites
					+ "\nKasa Tipi:" + CTur + "\nOnaylıyor musunuz? [1] EVET,  [2] HAYIR");
			int onay = scn.nextInt();

			if (onay == 1) {
				String q2 = "update cars set is_available='Açık',  customer_name='Rezerve Edilebilir' where customer_name='"+customer.getName()+"'";
				int rs = st.executeUpdate(q2);
				if(rs>0) {
					System.out.println("Aracı teslim ettiniz.");
				}
			} else if (onay == 2) {
				System.out.println("İşleminiz İptal Edildi");
			}
		}else {
			System.out.println("Bizden Aldığınız Araç Yok");
		}
	}
}
