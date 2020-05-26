package it.polito.tdp.rivers.model;

import java.awt.Event;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.rivers.db.RiversDAO;
import it.polito.tdp.rivers.model.Evento.EventType;

public class Simulazione {
	RiversDAO dao=new RiversDAO();
	//CODA EVENTI
	PriorityQueue<Evento> queue;
		//PARAMETRI SIMULAZIONE
	private final int PROBABILITA_IRR=5;
	private double f_media;
	private double F_OUT_MIN;
	private double Q;
	private List<Flow> misurazioni;
	//MODELLO MONDO
	private double C;
		//VALORI DA CALCOLARE
	private int contatore;
	private double C_media;
	private int giorni_erogazione;
		//SIMULAZIONE
	
		//////PREPARAZIONE INIZIALE
	public void inizializzazione(double k,River r) {
		queue=new PriorityQueue<Evento>();
		queue.clear();
		f_media=dao.getMedia(r);
		Q=k*f_media*30;
		C=Q/2.00;
		misurazioni=dao.getAllMisurazioniFiume(r);
		F_OUT_MIN=f_media*0.8;
		contatore=0;
		C_media=0;
		giorni_erogazione=0;
		for(Flow f:misurazioni) {
			Evento e=new Evento(EventType.MISURAZIONE,f.getFlow(),f.getDay());
			queue.add(e);
		}
	}

	public void run() {
		while(!queue.isEmpty()) {
			//data prima +1 giorno
			Evento e=queue.poll();
			System.out.println(e);
			AnalizzaEvento(e);
		}
		C_media=C_media/contatore;
		System.out.println(C_media);
		C_media=C_media*3600*24;
		System.out.println(contatore);
		System.out.println(C_media);
		System.out.println(giorni_erogazione);
	}
	
	
	private void AnalizzaEvento(Evento e) {
		switch(e.getTipo()) {
		case MISURAZIONE:
			contatore++;
			Random r=new Random();
			int probabilita=r.nextInt(101);
			double fout;
			if(probabilita<PROBABILITA_IRR) {
				fout=10*F_OUT_MIN;
			}
			else {
				fout=F_OUT_MIN;
			}
			if((C+e.getMisurazione()-fout)<0) {
				C=0;
				this.giorni_erogazione++;
			}
			else {
			if(fout>=e.getMisurazione()) {
				C=C-(fout-e.getMisurazione());
			}
			if(fout<e.getMisurazione()) {
				C=C+(e.getMisurazione()-fout);
			}
			if(Q>=C) {
				C_media=C_media+C;
				System.out.println(C_media);
			}
			else {
				queue.add(new Evento(EventType.TRACIMAZIONE,0.00,e.getGiorno()));
			}
			System.out.println("questo è attuale "+C);
			System.out.println("capacita "+Q);
			}
			break;
		case TRACIMAZIONE:
			C=Q;
			C_media=C_media+C;
			break;
		}
		
		
	}

	public int getContatore() {
		return contatore;
	}

	public void setContatore(int contatore) {
		this.contatore = contatore;
	}

	public double getC_media() {
		return C_media;
	}

	public void setC_media(double c_media) {
		C_media = c_media;
	}

	public int getGiorni_erogazione() {
		return giorni_erogazione;
	}

	public void setGiorni_erogazione(int giorni_erogazione) {
		this.giorni_erogazione = giorni_erogazione;
	}
	
		//PROCESSOEVENTO

	
	
}
