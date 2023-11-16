package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa
{
    private final long cpf;
    private final String nome, rg, cidade;
    private final LocalDate dataNascimento;

    public Pessoa(long cpf, String rg, String nome, LocalDate dataNascimento, String cidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.cidade = cidade;
        this.dataNascimento = dataNascimento;
    }

    public long getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public String getRg() {
        return rg;
    }
    public String getCidade() {
        return cidade;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nome+ "\n\tCPF: " +cpf+ "\n\tRG: " +rg+ "\n\tCidade: " +cidade+ "\n\tData de Nascimento: " +dataNascimento.format(formatter)+ "\n\n";
    }
}