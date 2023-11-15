package entities;

import java.util.ArrayList;
import java.util.List;

public class Dado<E> {
    private final E dado;
    private List<Integer> listaDadosPorIndice;

    public Dado(E dado, int index) {
        this.dado = dado;
        listaDadosPorIndice = new ArrayList<>();
        listaDadosPorIndice.add(index);
    }

    public E getDado() {
        return dado;
    }

    public List<Integer> getListaDadosPorIndice() {
        return listaDadosPorIndice;
    }
}