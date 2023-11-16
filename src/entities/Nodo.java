package entities;

public class Nodo<E> {

    private Nodo<E> esquerda, direita;
    private Dado<E> dado;
    private int altura;

    public Nodo(Dado<E> dado) {
        this.dado = dado;
    }

    public Nodo<E> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    public Nodo<E> getDireita() {
        return direita;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }

    public Dado<E> getDado() {
        return dado;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}