package entities;

public class Node<E> {

    private Node<E> esquerda, direita;
    private Dado<E> dado;
    private int altura, raiz;

    public Node(Dado<E> dado, int raiz) {
        this.dado = dado;
        this.raiz = raiz;
    }

    public Node(int raiz) {
        this.raiz = raiz;
    }

    public Node<E> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public Node<E> getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
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

    public int getRaiz() {
        return raiz;
    }

    public void setRaiz(int raiz) {
        this.raiz = raiz;
    }
}