package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;

public class TestSimulatore {

	public static void main(String[] args) {
		Simulazione sim=new Simulazione();
		RiversDAO dao=new RiversDAO();
		sim.inizializzazione(0.8,dao.getAllRivers().get(0));
		sim.run();

	}

}
