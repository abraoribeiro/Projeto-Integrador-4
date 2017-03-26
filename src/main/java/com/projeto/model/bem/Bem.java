package com.projeto.model.bem;

import java.io.Serializable;
import java.util.Date;

public class Bem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String status;
	private String nome;
	private String tipo;
	private Double vidaUtil = 0.0;
	private Date data_aquisicao;
	private Double taxa = 0.0;
	private Integer turnos = 1;
	private Boolean ativo;
	private String novo_usado;
	private Double tempo_uso = 0.0;
	private Double valor_atual = 0.0;
	private Double da = 0.0;
	private Double vc = 0.0;
	private Double valor_compra = 0.0;
	private Double valor_residual = 0.0;
	private Date data_hoje;
	private Double valor_venda = 0.0;
	private Double gp;

	public Date getData_hoje() {
		Date data = new java.sql.Date(System.currentTimeMillis());
		this.data_hoje = data;
		return this.data_hoje;
	}

	public void setData_hoje(Date data_hoje) {
		Date data = new java.sql.Date(System.currentTimeMillis());
		this.data_hoje = data;
	}

	public Double getValor_residual() {
		return valor_residual;
	}

	public void setValor_residual(Double valor_residual) {

		this.valor_residual = valor_residual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo.equals("MAQUINA")) {
			setVidaUtil((double) 10);
		} else if (tipo.equals("VEICULO")) {
			setVidaUtil((double) 5);
		} else if (tipo.equals("MOVEIS")) {
			setVidaUtil((double) 10);
		} else if (tipo.equals("INSTALACOES")) {
			setVidaUtil((double) 10);
		} else if (tipo.equals("COMPUTADORES")) {
			setVidaUtil((double) 5);
		} else if (tipo.equals("IMOVEL")) {
			setVidaUtil((double) 25);
		} else if (tipo.equals("TERRENO")) {
			setVidaUtil((double) 0);
		}

		double tx = 100 / getVidaUtil();
		setTaxa(tx);

		this.tipo = tipo;
	}

	public Double getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Double vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public Date getData_aquisicao() {
		return data_aquisicao;
	}

	public void setData_aquisicao(Date data_Aquisicao) {
		this.data_aquisicao = data_Aquisicao;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Integer getTurnos() {
		return turnos;
	}

	public void setTurnos(Integer turnos) {
		double tx = 0.0;
		if (turnos.equals(2)) {
			tx = getTaxa() * 1.5;
		} else if (turnos.equals(3)) {
			tx = getTaxa() * 2;
		}
		setTaxa(tx);
		this.turnos = turnos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNovo_usado() {
		return novo_usado;
	}

	public void setNovo_usado(String novo_usado) {
		this.novo_usado = novo_usado;
	}

	public Double getTempo_uso() {
		return tempo_uso;
	}

	public void setTempo_uso(Double tempo_uso) {
		double tst = vidaUtil;

		if (tst - (tempo_uso / 12) < (tst / 2)) {
			vidaUtil = (tst / 2);
		} else {
			vidaUtil = (tst - (tempo_uso / 12));
		}
		this.tempo_uso = tempo_uso;
	}

	public Double getValor_atual() {
		return valor_atual;
	}

	public void setValor_atual(Double valor_atual) {
		this.valor_atual = valor_atual;
	}

	public Double getDa() {
		return da;
	}

	public void setDa(Double da) {
		this.da = da;
	}

	public Double getVc() {
		return vc;
	}

	public void setVc(Double vc) {
		this.vc = vc;
	}

	public Double getValor_compra() {
		return valor_compra;
	}

	public void setValor_compra(Double valor_compra) {
		this.valor_compra = valor_compra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(Double valor_venda) {
		this.valor_venda = valor_venda;
	}

	public Double getGp() {
		return gp;
	}

	public void setGp(Double gp) {
		this.gp = gp;
	}
}