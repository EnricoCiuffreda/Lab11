package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
private RiversDAO dao=new RiversDAO();
private Simulazione sim=new Simulazione();

public List<River> getAllRivers() {
return dao.getAllRivers();
}

public List<Flow> getAllMisurazioniFiume(River r) {
	return dao.getAllMisurazioniFiume(r);
}

public Double getMedia(River r) {
	return dao.getMedia(r);
}
public void Simulazione(double k,River r) {
	sim.inizializzazione(k, r);
	sim.run();
}
public int getcontatore(){
	return sim.getContatore();
}
public double getmedia() {
	return sim.getC_media();
}
public int getGiorniDisservizio() {
	return sim.getGiorni_erogazione();
}


}


