package com.projeto.web;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.projeto.model.bem.Bem;
import com.projeto.model.bem.BemRN;
import com.projeto.util.Calculos;

@SessionScoped
@ManagedBean (name="BeanB")
public class BeanBem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Bem b = new Bem();

	public Bem getB() {
		return b;
	}

	public void setB(Bem b) {
		this.b = b;
	}
	
	public String cal(){
		double compra = b.getDa();
		double venda = b.getValor_venda();
		double gp = b.getDa() - b.getValor_venda();
		b.setGp(gp);
		return "gp";
		
	}
	
	
	public String cadastro(){
		if(b.getId() == null){
			new BemRN().gravar(b);
		}else{
			new BemRN().atualizar(b);
		}
		
		return "admin?faces-redirect=true";
	}
	
	public List<Bem> getListagem(){	
		return new BemRN().lista();
	}
	
	public String actionApagar() {
		new BemRN().apagar(b);
		return "admin?faces-redirect=true";
	}
	/*
	public String actionPesquisar(){
		this.b = new BemRN().seleciona(b);
		Integer integer = new Calculos().periodo(b);
		int t = integer;
		b.setDa((((b.getValor_compra() - b.getValor_residual()) * b.getTaxa()) / 12) *t );
		if(b.getDa() <= b.getValor_residual()) b.setDa(b.getValor_residual());
		b.setVc(b.getValor_compra() - b.getDa());
		return "tela_baixa"; 
	}
	*/
	public String actionUpdate(){
		new BemRN().atualizar(b);
		return "admin?faces-redirect=true";
	}
	
	public String pesquisar(){
		setB(new BemRN().buscar(b));
		new Calculos().calcular(getB());
		
		/*
		Integer integer = new Calculos().calcular(b);
		int t = integer;
		System.out.println(b.getData_aquisicao());
		System.out.println(b.getData_hoje());
		System.out.println(b.getValor_compra());
		System.out.println(b.getValor_residual());
		System.out.println(b.getTaxa());
		
		b.setDa((((b.getValor_compra() - b.getValor_residual()) * (b.getTaxa() / 100)) / 12) *t );
		if(b.getDa() <= b.getValor_residual()) b.setDa(b.getValor_residual());
		b.setVc(b.getValor_compra() - b.getDa());
		*/
		return "tela_item";
	}
	
	
	
	public String cadastrar(){
		this.b = new Bem();
		return "formulario";
	}
	
	public String baixa(){
		setB(new BemRN().buscar(b));
		new Calculos().calcular(getB());
		return "tela_baixa";
	}
	
	public String baixar(){
		new BemRN().status(b);
		return "admin?faces-redirect=true";
	}
	
}
