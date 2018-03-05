package com.springfilms.demo.beans;

/**
 * Bean utilizado como entidad de respuesta en el controlador
 * BestAndWorstController
 *
 * 
 * @author acosanchez
 *
 */
public class ConcreteFilmBean {

	private String title;
	private String totalRate;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(String totalRate) {
		this.totalRate = totalRate;
	}

	@Override
	public String toString() {
		return "ConcreteFilmBean [title=" + title + ", totalRate=" + totalRate + ", id=" + id + "]";
	}

}
