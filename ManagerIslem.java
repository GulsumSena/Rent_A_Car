package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import car.Renk;

public class ManagerIslem {
	
	private static Connection cnn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;
	private static Scanner scn=new Scanner(System.in);
	
	private static String path="jdbc:mysql://localhost:3306/rent_a_car";
	private static String user="root";
	private static String pass="1234";
	
	
	public static void listCarbyManager() throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		st=cnn.createStatement();
		String q="Select * from cars";
		rs=st.executeQuery(q);
		
		while(rs.next()) {
			if(rs.getString("customer_name").equalsIgnoreCase("rezerve edilebilir")) {
				System.out.println(Renk.BLUE+"\nREZERVE EDİLEBİLİR"+Renk.RESET+"\nID: "+rs.getInt("id")+"\nModel/Marka: "+rs.getString("name")+"\nVites: "+rs.getString("vites")+
						"\nKasa: "+rs.getString("genre")+"\nFiyat: "+rs.getInt("price")+" TL");
			}else {
				System.out.println(Renk.BLUE+"\nREZERVE"+Renk.RESET+"\nID: "+rs.getInt("id")+"\nModel/Marka: "+rs.getString("name")+"\nVites: "+rs.getString("vites")+
						"\nKasa: "+rs.getString("genre")+"\nFiyat: "+rs.getInt("price")+" TL"+"\nMüşteri Bilgisi: "+rs.getString("customer_name"));
			}
			
		}
	}

	public static void addCar() throws SQLException {
		System.out.println("Araç Marka ve Modeli: ");
		String name=scn.nextLine();
		
		System.out.println("Araç Vites Bilgisi: ");
		String vites=scn.nextLine();
		
		System.out.println("Araç Kasa Bilgisi: ");
		String genre=scn.nextLine();
		
		System.out.println("Fiyat: ");
		int price=scn.nextInt();
		
		cnn=DriverManager.getConnection(path, user, pass);
		String q="insert into cars (name, vites, genre, price, is_available, customer_name) values(?,?,?,?,?,?)";
		pst=cnn.prepareStatement(q);
		pst.setString(1, name);
		pst.setString(2, vites);
		pst.setString(3, genre);
		pst.setInt(4, price);
		pst.setString(5, "Açık");
		pst.setString(6, "Rezerve Edilebilir");
		int row=pst.executeUpdate();
		if(row>0) {
			System.out.println("Ekleme İşlemi Başarılı.");
		}
	}
	
	public static void deleteCar(int id) throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		
		String q="delete from cars where id=?";
		pst=cnn.prepareStatement(q);
		pst.setInt(1, id);
		int row=pst.executeUpdate();
		if(row>0) {
			System.out.println("Silme işlemi başarılı.");
		}
	}
	
	public static void updateCar(int id) throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		st=cnn.createStatement();
		String q="select * from cars where id="+id;
		rs=st.executeQuery(q);
		if(rs.next()) {
			System.out.println("Model/Marka: ");
			String nname=scn.nextLine();
			
			System.out.println("Vites: ");
			String nvites=scn.nextLine();
			
			System.out.println("Kasa: ");
			String ngenre=scn.nextLine();
			
			System.out.println("Fiyat: [0] Değiştirme");
			int nprice=scn.nextInt();
			
			if(nname==null||nname=="") {
				nname=rs.getString("name");
			}
			
			if(nvites==null||nvites=="") {
				nvites=rs.getString("vites");
			}
			if(ngenre==null||ngenre=="") {
				ngenre=rs.getString("genre");
			}
			if(nprice==0) {
				nprice=rs.getInt("price");
			}
			
			String q1="update cars set name=?, vites=?, genre=?, price=? where id=?";
			pst=cnn.prepareStatement(q1);
			pst.setString(1, nname);
			pst.setString(2, nvites);
			pst.setString(3, ngenre);
			pst.setInt(4, nprice);
			pst.setInt(5, id);
			int row=pst.executeUpdate();
			if(row>0) {
				System.out.println("Güncelleme işlemi başarılı");
			}
		}
		
	
	}
}
