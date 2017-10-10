package leonardo.pmo.unibratec.slideactivity;

import java.io.Serializable;

/**
 * Created by Leonardo Marques on 10/10/2017.
 */

public class Aluno implements Serializable {

    private Integer id;
    private String nome;
    private String nota;
    private String matricula;
    private String dtaNascimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDtaNascimento() {
        return dtaNascimento;
    }

    public void setDtaNascimento(String dtaNascimento) {
        this.dtaNascimento = dtaNascimento;
    }

    @Override
    public String toString() {
        return nome;
    }
}
