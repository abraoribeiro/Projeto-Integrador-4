package com.projeto.util;

import java.util.Calendar;
import java.util.Date;
import com.projeto.model.bem.Bem;

public class Calculos {
	
	private Integer quantidadeDepreciado;
	//Bem b = new Bem();
	private Double valor;
	
	public void calcular(Bem b) {
		
		System.out.println();
		calcularTaxa(b);
		calcularValorDepreciar(b);
		calcularDepreciacaoAcumulada(b);
		calcularValorContabil(b);
		gp(b);
	
	}
	
	public Integer periodo(Bem b) {
		
		Date dataCompra = (Date) b.getData_aquisicao();
		Date dataAtual = (java.sql.Date) b.getData_hoje();
		System.out.println(dataCompra);
		System.out.println(dataAtual);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataCompra);
		int diaCompra = calendar.get(Calendar.DAY_OF_MONTH);
		int mesCompra = calendar.get(Calendar.MONTH) + 1;
		int anoCompra = calendar.get(Calendar.YEAR);
		Calendar calendarAtual = Calendar.getInstance();
		calendarAtual.setTime(dataAtual);

		int diaAtual = calendarAtual.get(Calendar.DAY_OF_MONTH);
		int mesAtual = calendarAtual.get(Calendar.MONTH) + 1;
		int anoAtual = calendarAtual.get(Calendar.YEAR);

		quantidadeDepreciado = (anoAtual - anoCompra) * 12;

		if (quantidadeDepreciado > 0) {
			mesCompra = 12 - mesCompra;

			// COMPRA DEPOIS DO DIA 15 NÃO CONTA
			// VENDA DEPOIS DO DIA 15 CONTA O MES

			if (diaCompra < 15) {
				mesCompra += 1;
			}
		}

		if (diaAtual < 15) {
			mesAtual -= 1;
		}

		if (quantidadeDepreciado < 12) {
			quantidadeDepreciado = mesCompra - mesAtual;

			if (quantidadeDepreciado < 0) {
				quantidadeDepreciado *= -1;
			}
		} else {
			if (quantidadeDepreciado == 12) {
				quantidadeDepreciado = mesCompra + mesAtual;
			} else {
				quantidadeDepreciado += (mesCompra + mesAtual) - 12;
			}
		}

		if (quantidadeDepreciado >b.getVidaUtil() * 12) {
			quantidadeDepreciado = (int) (b.getVidaUtil() * 12);
		}
		System.out.println(quantidadeDepreciado);
		return quantidadeDepreciado;	
	}
	
	
	public void calcularTaxa(Bem b) {
		double verificarVidaUtil;
		verificarVidaUtil = verificarVidaUtil(
				b.getVidaUtil(),
				b.getTempo_uso());

		double taxa;
		taxa = 100 / verificarVidaUtil;

		switch (b.getTurnos()) {
		case 2:
			taxa *= 1.5;
			break;
		case 3:
			taxa *= 2.0;
			break;
		}
		
		b.setTaxa(taxa);
	}
	
	public double verificarVidaUtil(double vidaUtil, double tempoDeUso) {
		double metadeVidaUtil = (vidaUtil / 2);

		if (tempoDeUso == 0) {
			return vidaUtil;
		}
		double verificarVidaUtil = vidaUtil - tempoDeUso;
		if (verificarVidaUtil < metadeVidaUtil) {
			verificarVidaUtil = metadeVidaUtil;
		}
		return verificarVidaUtil;
	}
	
	public void calcularValorDepreciar(Bem b) {
		double aux = 0.0;
		aux =  (b.getValor_compra() * (b.getValor_residual() / 100) ) 
				- b.getValor_compra();
		valor = aux;
	}
	
	public void calcularDepreciacaoAcumulada(Bem b) {
		double valorDepreciar = 0.0;
		valorDepreciar =  (b.getValor_compra() * (b.getValor_residual() / 100) ) 
				- b.getValor_compra();
		
		double aux = 0.0;
		aux= ((( valorDepreciar * (b.getTaxa() / 100)) / 12) * periodo(b));
		
		b.setDa(aux);
	}
	public void calcularValorContabil(Bem b) {
		double aux;
		aux = (b.getValor_compra() + b.getDa());
		b.setVc(aux);
	}
	public double gp(Bem b) {
		double gp = b.getVc() - b.getValor_venda() ;
		return gp;
	}
	
	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
