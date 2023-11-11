package entities;

import java.util.List;

public class Dado<E> {
    private final E dado;
    private List<Integer> listaDadosPorIndice;

    public Dado(E dado, List<Integer> listaDadosPorIndice) {
        this.dado = dado;
        this.listaDadosPorIndice = listaDadosPorIndice;
    }

    public E getDado() {
        return dado;
    }

    public List<Integer> getListaDadosPorIndice() {
        return listaDadosPorIndice;
    }
}