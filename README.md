# Prova Prática: Missão Refatoração (Clean Code & OO)

**Disciplina:** Object-Oriented Programming - FIAP
**Projeto:** FiapRide (Módulo de Frota)
**Aluno:** Nícolas Borges Natal
**RM:** 568230
**Sala:** 2CCPR
**Professor:** Ygor Moraes Martins dos Anjos

---

## Sobre o projeto

Refatoração do sistema de cadastro de veículos do FiapRide. O código legado funcionava, mas expunha todos os atributos como `public`, aceitava valores inválidos (combustível negativo, consumo maior que o disponível) e usava nomes sem significado.

A refatoração aplica os conceitos das Aulas 01, 02 e 03: **Classes, Métodos, Clean Code e Encapsulamento**.

---

## Estrutura do repositório

```
prova-refatoracao-nicolas-borges-natal/
├── diagrama-veiculo-refatorado.png
├── src/
│   └── br/
│       └── com/
│           └── fiapride/
│               ├── model/
│               │   └── Veiculo.java
│               └── main/
│                   └── SistemaPrincipal.java
└── README.md
```

---

## O que foi corrigido

### 1. Nomenclatura (Clean Code)

| Antes | Depois | Motivo |
|---|---|---|
| `veiculos` | `Veiculo` | Classe é um molde de **um** objeto: singular e em PascalCase |
| `principal` | `SistemaPrincipal` | PascalCase e nome que revela a intenção |
| `individuo` | `nomeProprietario` | Diz o que o dado realmente é |
| `pl` | `placa` | Abreviação sem significado eliminada |
| `gas` | `nivelCombustivel` | Nome autoexplicativo |
| `adicionar()` | `abastecer()` | Verbo do domínio do negócio |
| `gasta()` | `consumir()` | Verbo no infinitivo, padrão de métodos |

### 2. Encapsulamento (Aula 03)

- Todos os atributos passaram de `public` para `private`.
- Acesso agora acontece apenas por **getters** e **setters**.
- Cada setter valida o dado antes de alterar o estado do objeto.

### 3. Blindagem das regras de negócio

- `nivelCombustivel` nunca fica negativo.
- `nivelCombustivel` nunca ultrapassa a constante `CAPACIDADE_TANQUE` (50L).
- `consumir()` recusa consumo maior que o combustível disponível.
- `abastecer()` e `consumir()` só aceitam valores maiores que zero.
- `nomeProprietario` não aceita nulo nem vazio.
- `placa` valida o tamanho e normaliza para maiúsculas.

### 4. Consistência de tipos

O código original declarava `gas` como `int`, mas `gasta(double v)` recebia `double` - o que causava perda de precisão. Todo o controle de combustível passou a usar `double`.

### 5. Construtor

Foi adicionado um construtor que já obriga o objeto a nascer com dados validados, eliminando a atribuição direta de campos feita no `main` original.

---

## Como executar

**Pelo Eclipse:** clique com o botão direito em `SistemaPrincipal.java` → *Run As* → *Java Application*.

**Pelo terminal:**

```bash
javac -d bin $(find src -name "*.java")
java -cp bin br.com.fiapride.main.SistemaPrincipal
```

---

## Saída esperada

```
Dono: Carlos | Placa: ABC-1234 | Gasolina: 10.0L

----- Testes de blindagem -----
[ERRO] Nivel de combustivel nao pode ser negativo.
[OK] Abastecido 20.0L. Tanque: 30.0L.
[ERRO] Combustivel insuficiente. Disponivel: 30.0L.
[OK] Consumido 15.0L. Tanque: 15.0L.
[ERRO] Tanque comporta no maximo 50.0L.
[ERRO] Placa invalida.

----- Estado final -----
Dono: Carlos | Placa: ABC-1234 | Gasolina: 15.0L
```

---

## Diagrama de classes

O arquivo `diagrama-veiculo-refatorado.png` (na raiz do repositório) foi gerado no Astah e representa a classe `Veiculo` já refatorada, com visibilidade `-` (private) nos atributos e `+` (public) nos métodos.