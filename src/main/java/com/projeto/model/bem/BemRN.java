package com.projeto.model.bem;

import java.sql.Date;
import java.util.List;

public class BemRN {

	public void gravar(Bem b){
		System.out.println(b.getId());
		//if(b.getId() == null){
			new BemDAO().insert(b);
		//}else{
		//	new BemDAO().update(b);
		//}
	}
	public void atualizar(Bem b){
		new BemDAO().update(b);
	}
	
	///////////////////////////////////////////////////////////////////
	public List<Bem> lista(){
		return new BemDAO().select();
	}
	
	public void apagar(Bem b){
		System.out.println(b.getId());
		new BemDAO().delete(b);
	}
	
	public Bem seleciona(Bem b){
		return new BemDAO().selectId(b);
	}
	
	public Double calculoData(Date datai, Date dataf){
		double total = (datai.getTime() - dataf.getTime());
		return total;
	}
	
	public Bem buscar(Bem b){
		return new BemDAO().selectId(b);
	}
	
	public void status(Bem b){
		 new BemDAO().updateStatus(b);
		
	}
}
