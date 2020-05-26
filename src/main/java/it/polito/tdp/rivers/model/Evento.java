package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{
	public enum EventType{
		MISURAZIONE,TRACIMAZIONE,
	}
	private EventType tipo;
	private double misurazione;
	private LocalDate giorno;
	/**
	 * @param tipo
	 * @param misurazione
	 * @param giorno
	 */
	public Evento(EventType tipo, double misurazione, LocalDate giorno) {
		super();
		this.tipo = tipo;
		this.misurazione = misurazione;
		this.giorno = giorno;
	}
	public EventType getTipo() {
		return tipo;
	}
	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}
	public double getMisurazione() {
		return misurazione;
	}
	public void setMisurazione(double misurazione) {
		this.misurazione = misurazione;
	}
	public LocalDate getGiorno() {
		return giorno;
	}
	public void setGiorno(LocalDate giorno) {
		this.giorno = giorno;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.giorno.compareTo(o.giorno);
	}
	@Override
	public String toString() {
		return "Evento [tipo=" + tipo + ", misurazione=" + misurazione + ", giorno=" + giorno + "]";
	}
	
	

}
