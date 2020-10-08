package ar.com.casa.unittesting.service;

import ar.com.casa.unittesting.dao.ElDao;
import ar.com.casa.unittesting.model.MiClase;

public class MiServicio {
	private MiClase elObjeto = new MiClase();
	private ElDao dao;

	public void calcularNumeros(int A, int B) {
		elObjeto.setUnEntero(A + B);
	}

	public void setearString() {
		elObjeto.setUnaCadena("Seteado desde el service");
	}

	public void setearUsandoDao() {
		elObjeto.setUnEntero(dao.getInt());
		elObjeto.setUnaCadena(dao.getString());
	}

	public void setDao(ElDao dao) {
		this.dao = dao;
	}

	public MiClase getElObjeto() {
		return elObjeto;
	}
	
	

}
