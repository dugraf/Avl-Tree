package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dado<E>
{
    private final E dado;
    private final List<Integer> listaDadosIguais;

    public Dado(E dado, int index)
    {
        this.dado = dado;
        listaDadosIguais = new ArrayList<>();
        listaDadosIguais.add(index);
    }

    public boolean valorMenorAtual(Dado<E> atual)
    {
        if(dado instanceof LocalDate)
            return ((LocalDate) dado).isBefore((LocalDate) atual.getDado());
        else if(dado instanceof String)
            return ((String) this.dado).compareTo((String) atual.getDado()) < 0;
        else
            return ((long) dado) < ((long) atual.dado);
    }

    public boolean valorMaiorAtual(Dado<E> atual)
    {
        if(dado instanceof LocalDate)
            return ((LocalDate) dado).isAfter((LocalDate) atual.getDado());
        else if(dado instanceof String)
            return ((String) this.dado).compareTo((String) atual.getDado()) > 0;
        else 
            return ((long) dado) > ((long) atual.dado);
    }

    public String toString()
    {
        String retorno = listaDadosIguais.toString();
        if(dado != null)
        {
            if (dado instanceof LocalDate)
            {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                retorno += format.format((LocalDate) dado);
            }
            else if (dado instanceof String)
                retorno += (String) dado;
            else
                retorno += ((Long) dado).toString();
        }
        else
            return "";
        return retorno;
    }

    public E getDado()
    {
        return dado;
    }

    public List<Integer> getListaDadosIguais()
    {
        return listaDadosIguais;
    }
}