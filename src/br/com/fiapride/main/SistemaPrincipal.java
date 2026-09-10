package br.com.fiapride.main;

import br.com.fiapride.model.Veiculo;

// Classe de teste do modulo de frota do FiapRide
public class SistemaPrincipal {

	public static void main(String[] args) {

		// Criacao valida: dados passam pelas validacoes do construtor
		Veiculo v1 = new Veiculo("Carlos", "ABC-1234", 10.0);
		System.out.println(v1.descrever());

		System.out.println("\n----- Testes de blindagem -----");

		// Teste 1: valor negativo bloqueado pelo setter
		v1.setNivelCombustivel(-10);

		// Teste 2: abastecimento valido
		v1.abastecer(20);

		// Teste 3: consumo maior que o disponivel bloqueado
		v1.consumir(100);

		// Teste 4: consumo valido
		v1.consumir(15);

		// Teste 5: estouro da capacidade do tanque bloqueado
		v1.abastecer(60);

		// Teste 6: placa fora do formato bloqueada
		v1.setPlaca("XX1");

		System.out.println("\n----- Estado final -----");
		System.out.println(v1.descrever());
	}
}