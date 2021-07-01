package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVO {
	
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioDeVendasVO(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.setNomeProduto(nomeProduto);
		this.setQuantidadeVendida(quantidadeVendida);
		this.setDataUltimaVenda(dataUltimaVenda);
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Long quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	public void setDataUltimaVenda(LocalDate dataUltimaVenda) {
		this.dataUltimaVenda = dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVO [nomeProduto=" + nomeProduto + ", quantidadeVendida=" + quantidadeVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}

}
