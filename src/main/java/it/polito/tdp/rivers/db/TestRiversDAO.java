package it.polito.tdp.rivers.db;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers());
		System.out.println(dao.getAllMisurazioniFiume(dao.getAllRivers().get(0)));
		System.out.println(dao.getMedia(dao.getAllRivers().get(0)));
	}

}
