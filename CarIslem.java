package car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Manager.ManagerIslem;

public class CarIslem {
	private static Connection cnn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;
	private static Scanner scn=new Scanner(System.in);
	
	private static String path="jdbc:mysql://localhost:3306/rent_a_car";
	private static String user="root";
	private static String pass="1234";
	
	public static void listCar() throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		st=cnn.createStatement();
		String q="select  * from cars where is_available='Açık'";
		rs=st.executeQuery(q);
		
		while(rs.next()) {
				System.out.println(Renk.BLUE+"\nREZERVE EDİLEBİLİR"+Renk.RESET+"\nID: "+rs.getInt("id")+" - "+rs.getString("name")+"\nVites: "+rs.getString("vites")+"\nAraç Tür: "
						+rs.getString("genre")+"\nFiyat: "+rs.getInt("price")+" TL");
		}
	}
	
	public static int pickCar() throws SQLException {
		CarIslem.listCar();
		System.out.println();
		System.out.println(Renk.GREEN+"Seçeceğiniz Araba ID: "+Renk.RESET);
		int secim=scn.nextInt();
		
		return secim;
	}
	
	public static int ManagerpickCar() throws SQLException {
		ManagerIslem.listCarbyManager();
		System.out.println();
		System.out.println(Renk.GREEN+"Seçeceğiniz Araba ID: "+Renk.RESET);
		int secim=scn.nextInt();
		
		return secim;
	}

}
