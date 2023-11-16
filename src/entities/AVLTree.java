package entities;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AVLTree<E> {

    private Nodo<E> raiz;
    private int numElementos;

    public AVLTree()
    {
        this.numElementos = 0;
    }

    private boolean buscar(Nodo<E> atual, Dado<E> valor) {
        if(Objects.isNull(atual))
            return false;

        if(valor.valorMenorAtual(atual.getDado()))
            return buscar(atual.getEsquerda(), valor);

        if(valor.valorMaiorAtual(atual.getDado()))
            return buscar(atual.getDireita(), valor);

        return !(valor.getDado() instanceof String) && !(valor.getDado() instanceof LocalDate);
    }

    public void inserir(Dado<E> valor) {
        if(!buscar(raiz, valor))
        {
            raiz = inserir(raiz, valor);
            numElementos++;
        }
    }

    private Nodo<E> inserir(Nodo<E> atual, Dado<E> valor)
    {
        if(Objects.isNull(atual))
            return new Nodo<>(valor);

        if(atual.getDado().getDado().equals(valor.getDado()))
        {
            atual.getDado().getListaDadosIguais().add(valor.getListaDadosIguais().get(0));
            return balancear(atual);
        }

        if(valor.valorMaiorAtual(atual.getDado())) 
            atual.setEsquerda(inserir(atual.getEsquerda(), valor));
        else 
            atual.setDireita(inserir(atual.getDireita(), valor));

        return balancear(atual);
    }

    private Nodo<E> balancear(Nodo<E> atual) {
        atualizar(atual);

        if(fatorBalanceamento(atual) > 1) {
            if (!rotacaoSimplesDireita(atual))
                atual.setEsquerda(rotacaoEsquerda(atual.getEsquerda()));
            atual = rotacaoDireita(atual);
        }
        else if(fatorBalanceamento(atual) < -1)
        {
            if(!rotacaoSimplesEsquerda(atual))
                atual.setDireita(rotacaoDireita(atual.getDireita()));
            atual = rotacaoEsquerda(atual);
        }
        return atual;
    }

    private Nodo<E> rotacaoDireita(Nodo<E> atual) {
        Nodo<E> pai = atual.getEsquerda();

        atual.setEsquerda(pai.getDireita());
        pai.setDireita(atual);

        atualizar(atual);
        atualizar(pai);

        return pai;
    }

    private Nodo<E> rotacaoEsquerda(Nodo<E> atual) {
        Nodo<E> pai = atual.getDireita();

        atual.setDireita(pai.getEsquerda());
        pai.setEsquerda(atual);

        atualizar(atual);
        atualizar(pai);

        return pai;
    }

    private void atualizar(Nodo<E> atual) {
        int alturaEsquerda = altura(atual.getEsquerda());
        int alturaDireita = altura(atual.getDireita());

        atual.setAltura(1 + Math.max(alturaEsquerda, alturaDireita));
    }

    private int fatorBalanceamento(Nodo<E> atual) {
        if(Objects.isNull(atual))
            return 0;
        else 
            return altura(atual.getEsquerda()) - altura(atual.getDireita());
    }

    private int altura(Nodo<E> atual) {
        if(Objects.isNull(atual))
            return -1;
        else
            return atual.getAltura();
    }

    private boolean rotacaoSimplesDireita(Nodo<E> atual) {
        return altura(atual.getEsquerda().getEsquerda()) > altura(atual.getEsquerda().getDireita());
    }

    private boolean rotacaoSimplesEsquerda(Nodo<E> atual) {
        return altura(atual.getDireita().getDireita()) > altura(atual.getDireita().getEsquerda());
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public List<Integer> indicePorCpf(Nodo<Long> atual, long valor) {
        if(Objects.isNull(atual))
            return Collections.emptyList();


        if(valor < (atual.getDado().getDado()))
            return indicePorCpf(atual.getDireita(), valor);


        if(valor > (atual.getDado().getDado()))
            return indicePorCpf(atual.getEsquerda(), valor);

        return atual.getDado().getListaDadosIguais();
    }

    public List<Integer> indicePorData(Nodo<LocalDate> atual, LocalDate dataInicio, LocalDate dataFim, List<Integer> indices) {
        if(Objects.isNull(atual))
            return Collections.emptyList();

        LocalDate data = atual.getDado().getDado();

        if(data.isAfter(dataInicio))
            indicePorData(atual.getEsquerda(), dataInicio, dataFim, indices);

        if(data.isBefore(dataFim))
            indicePorData(atual.getDireita(), dataInicio, dataFim, indices);

        if(data.isAfter(dataInicio) && data.isBefore(dataFim))
            indices.addAll(atual.getDado().getListaDadosIguais());

        return indices;
    }

    public List<Integer> indicesComecandoPor(Nodo<String> atual, String nameFragment, List<Integer> indices) {
        if(Objects.isNull(atual))
            return Collections.emptyList();

        if(nameFragment.toUpperCase().compareTo(String.valueOf(atual.getDado().getDado()).toUpperCase().substring(0, nameFragment.length())) <= 0)
            indicesComecandoPor(atual.getDireita(), nameFragment, indices);

        if(nameFragment.toUpperCase().compareTo(String.valueOf(atual.getDado().getDado()).toUpperCase().substring(0, nameFragment.length())) >= 0)
            indicesComecandoPor(atual.getEsquerda(), nameFragment, indices);


        if(String.valueOf(atual.getDado().getDado()).toUpperCase().startsWith(nameFragment.toUpperCase()))
            indices.addAll(atual.getDado().getListaDadosIguais());

        return indices;
    }
}