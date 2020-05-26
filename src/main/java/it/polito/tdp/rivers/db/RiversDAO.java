package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
public List<Flow> getAllMisurazioniFiume(River r) {
		
		final String sql = "SELECT * FROM flow WHERE river=? ORDER BY DAY";

		List<Flow> flow = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				flow.add(new Flow(res.getDate("day").toLocalDate(),res.getDouble("flow"),r));
			}
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return flow;
	}

public Double getMedia(River r) {
	
	final String sql = "SELECT AVG(flow) as media FROM flow WHERE id=? ORDER BY DAY";

	Double risultato =null;

	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1,r.getId());
		ResultSet res = st.executeQuery();
		if (res.next()) {
			risultato=res.getDouble("media");
		}
		conn.close();
	} catch (SQLException e) {
		//e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}
	return risultato;
}
}
