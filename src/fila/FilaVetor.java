package fila;

public class FilaVetor implements Fila {
	private int tamAtual;
	private int inicio;
	private int tamanhoVetor;
	private int[] vet;

	public FilaVetor(int tam) {
		this.tamanhoVetor = tam;
		this.vet = new int[tam];
		this.inicio = 0;
		this.tamAtual = 0;
	}

	@Override
	public void insere(int v) throws Exception {
		int fim;
		if (tamAtual == tamanhoVetor)
			throw new Exception("ERRO: a capacidade da fila estourou!");

		fim = (inicio + tamAtual) % tamanhoVetor;
		vet[fim] = v;
		tamAtual++;
	}

	@Override
	public int retira() throws Exception {
		int v;
		if (vazia())
			throw new Exception("ERRO: fila vazia!");

		v = vet[inicio];
		inicio = (inicio + 1) % tamanhoVetor;
		tamAtual--;
		return v;
	}

	@Override
	public boolean vazia() {
		return tamAtual == 0;
	}

	@Override
	public void libera() {
		tamAtual = 0;
		inicio = 0;
	}

	public FilaVetor concatena(FilaVetor f2) throws Exception {
		FilaVetor f3 = new FilaVetor(tamanhoVetor + f2.tamanhoVetor);
		while ((!vazia() || !f2.vazia())) {
			if (!this.vazia()) {
				f3.insere(this.retira());
			} else if (!f2.vazia()) {
				f3.insere(f2.retira());
			}
		}
		return f3;
	}

	public FilaVetor merge(FilaVetor f2) throws Exception {
		FilaVetor f3 = new FilaVetor(tamanhoVetor + f2.tamanhoVetor);
		while ((!vazia() || !f2.vazia())) {
			if (!this.vazia())
				f3.insere(this.retira());
			if (!f2.vazia())
				f3.insere(f2.retira());
		}
		return f3;
	}

	@Override
	public String toString() {
		String toString = "";
		if (vazia())
			return toString;
		
		int contador = inicio;
		do {
			toString += vet[contador] + "; ";
			contador = (contador + 1) % tamanhoVetor;
		} while (contador != (inicio + tamAtual) % tamanhoVetor);

		return toString;
	}

}
