package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Dados {
    private final LocalDate dataFabricacao;
    private final LocalTime tempoDeCiclo;
    private final Integer contagemPecaNaoMetalica;
    private final Integer contagemPecaMetalicaPequena;
    private final Integer contagemPecaMetalicaMedia;
    private final Integer contagemPecaMetalicaGrande;

    public Dados(LocalDate dataFabricacao, LocalTime tempoDeCiclo, Integer contagemPecaNaoMetalica, Integer contagemPecaMetalicaPequena, Integer contagemPecaMetalicaMedia, Integer contagemPecaMetalicaGrande) {
        this.dataFabricacao = dataFabricacao;
        this.tempoDeCiclo = tempoDeCiclo;
        this.contagemPecaNaoMetalica = contagemPecaNaoMetalica;
        this.contagemPecaMetalicaPequena = contagemPecaMetalicaPequena;
        this.contagemPecaMetalicaMedia = contagemPecaMetalicaMedia;
        this.contagemPecaMetalicaGrande = contagemPecaMetalicaGrande;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }


    public LocalTime getTempoDeCiclo() {
        return tempoDeCiclo;
    }


    public Integer getContagemPecaNaoMetalica() {
        return contagemPecaNaoMetalica;
    }

    public Integer getContagemPecaMetalicaPequena() {
        return contagemPecaMetalicaPequena;
    }


    public Integer getContagemPecaMetalicaMedia() {
        return contagemPecaMetalicaMedia;
    }


    public Integer getContagemPecaMetalicaGrande() {
        return contagemPecaMetalicaGrande;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dados dados)) return false;
        return Objects.equals(dataFabricacao, dados.dataFabricacao) && Objects.equals(contagemPecaNaoMetalica, dados.contagemPecaNaoMetalica) && Objects.equals(contagemPecaMetalicaPequena, dados.contagemPecaMetalicaPequena) && Objects.equals(contagemPecaMetalicaMedia, dados.contagemPecaMetalicaMedia) && Objects.equals(contagemPecaMetalicaGrande, dados.contagemPecaMetalicaGrande);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataFabricacao, contagemPecaNaoMetalica, contagemPecaMetalicaPequena, contagemPecaMetalicaMedia, contagemPecaMetalicaGrande);
    }

    @Override
    public String toString() {
        return "Dados [" + "dataFabricacao=" + dataFabricacao + ", tempoDeCiclo='" + tempoDeCiclo +
                ", pecaNaoMetalica='" + contagemPecaNaoMetalica + ", pecaMetalicaPequena='" + contagemPecaMetalicaPequena +
                ", pecaMetalicaMedia='" + contagemPecaMetalicaMedia + ", pecaMetalicaGrande='" + contagemPecaMetalicaGrande + " ]";
    }
}
