package br.com.fiapride.model;

// Modelo que representa um veiculo da frota do FiapRide
public class Veiculo {

	// Capacidade maxima do tanque em litros
	private static final double CAPACIDADE_TANQUE = 50.0;

	// Atributos privados: ninguem altera o estado sem passar pelas validacoes
	private String nomeProprietario;
	private String placa;
	private double nivelCombustivel;

	// Construtor: o objeto ja nasce com dados validados
	public Veiculo(String nomeProprietario, String placa, double nivelCombustivel) {
		setNomeProprietario(nomeProprietario);
		setPlaca(placa);
		setNivelCombustivel(nivelCombustivel);
	}

	// Adiciona combustivel respeitando o limite do tanque
	public void abastecer(double litros) {
		if (litros <= 0) {
			System.out.println("[ERRO] A quantidade abastecida deve ser maior que zero.");
			return;
		}
		if (nivelCombustivel + litros > CAPACIDADE_TANQUE) {
			System.out.println("[ERRO] Tanque comporta no maximo " + CAPACIDADE_TANQUE + "L.");
			return;
		}
		nivelCombustivel += litros;
		System.out.println("[OK] Abastecido " + litros + "L. Tanque: " + nivelCombustivel + "L.");
	}

	// Consome combustivel apenas se houver saldo suficiente
	public void consumir(double litros) {
		if (litros <= 0) {
			System.out.println("[ERRO] A quantidade consumida deve ser maior que zero.");
			return;
		}
		if (litros > nivelCombustivel) {
			System.out.println("[ERRO] Combustivel insuficiente. Disponivel: " + nivelCombustivel + "L.");
			return;
		}
		nivelCombustivel -= litros;
		System.out.println("[OK] Consumido " + litros + "L. Tanque: " + nivelCombustivel + "L.");
	}

	// Monta a ficha do veiculo para exibicao
	public String descrever() {
		return "Dono: " + nomeProprietario + " | Placa: " + placa + " | Gasolina: " + nivelCombustivel + "L";
	}

	// --- Getters e Setters ---

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	// Nome nao pode ser nulo nem vazio
	public void setNomeProprietario(String nomeProprietario) {
		if (nomeProprietario == null || nomeProprietario.trim().isEmpty()) {
			System.out.println("[ERRO] Nome do proprietario invalido.");
			return;
		}
		this.nomeProprietario = nomeProprietario.trim();
	}

	public String getPlaca() {
		return placa;
	}

	// Placa precisa ter 7 ou 8 caracteres (ABC1234 ou ABC-1234)
	public void setPlaca(String placa) {
		if (placa == null || placa.trim().length() < 7 || placa.trim().length() > 8) {
			System.out.println("[ERRO] Placa invalida.");
			return;
		}
		this.placa = placa.trim().toUpperCase();
	}

	public double getNivelCombustivel() {
		return nivelCombustivel;
	}

	// Nivel precisa estar entre 0 e a capacidade do tanque
	public void setNivelCombustivel(double nivelCombustivel) {
		if (nivelCombustivel < 0) {
			System.out.println("[ERRO] Nivel de combustivel nao pode ser negativo.");
			return;
		}
		if (nivelCombustivel > CAPACIDADE_TANQUE) {
			System.out.println("[ERRO] Nivel acima da capacidade do tanque (" + CAPACIDADE_TANQUE + "L).");
			return;
		}
		this.nivelCombustivel = nivelCombustivel;
	}
}