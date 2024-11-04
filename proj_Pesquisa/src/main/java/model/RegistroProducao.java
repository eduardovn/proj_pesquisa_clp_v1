package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "registro_producao")
public class RegistroProducao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data_fabricacao")
    private LocalDate dataFabricacao;
    @Column(name = "qtd_pecas_nao_metalicas")
    private Integer qtdPecasNaoMetalicas;

    @Column(name = " qtd_pecas_metalicas_1")
    private Integer qtdPecasMetalicas1;

    @Column(name = " qtd_pecas_metalicas_2")
    private Integer qtdPecasMetalicas2;

    @Column(name = " qtd_pecas_metalicas_3")
    private Integer qtdPecasMetalicas3;

    @Column(name = "status_registro")
    private Boolean status;



    public RegistroProducao() {
    }

    public RegistroProducao(LocalDate dataFabricacao, Integer qtdPecasNaoMetalicas, Integer qtdPecasMetalicas1, Integer qtdPecasMetalicas2, Integer qtdPecasMetalicas3, Boolean status) {
       this.dataFabricacao = dataFabricacao;
       this.qtdPecasNaoMetalicas = qtdPecasNaoMetalicas;
       this.qtdPecasMetalicas1 = qtdPecasMetalicas1;
       this.qtdPecasMetalicas2 = qtdPecasMetalicas2;
       this.qtdPecasMetalicas3 = qtdPecasMetalicas3;
       this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQtdPecasNaoMetalicas() {
        return qtdPecasNaoMetalicas;
    }

    public void setQtdPecasNaoMetalicas(Integer qtdPecasNaoMetalicas) {
        this.qtdPecasNaoMetalicas = qtdPecasNaoMetalicas;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Integer getQtdPecasMetalicas1() {
        return qtdPecasMetalicas1;
    }

    public void setQtdPecasMetalicas1(Integer qtdPecasMetalicas1) {
        this.qtdPecasMetalicas1 = qtdPecasMetalicas1;
    }

    public Integer getQtdPecasMetalicas2() {
        return qtdPecasMetalicas2;
    }

    public void setQtdPecasMetalicas2(Integer qtdPecasMetalicas2) {
        this.qtdPecasMetalicas2 = qtdPecasMetalicas2;
    }

    public Integer getQtdPecasMetalicas3() {
        return qtdPecasMetalicas3;
    }

    public void setQtdPecasMetalicas3(Integer qtdPecasMetalicas3) {
        this.qtdPecasMetalicas3 = qtdPecasMetalicas3;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroProducao that)) return false;
        return Objects.equals(dataFabricacao, that.dataFabricacao) && Objects.equals(qtdPecasNaoMetalicas, that.qtdPecasNaoMetalicas) && Objects.equals(qtdPecasMetalicas1, that.qtdPecasMetalicas1) && Objects.equals(qtdPecasMetalicas2, that.qtdPecasMetalicas2) && Objects.equals(qtdPecasMetalicas3, that.qtdPecasMetalicas3) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataFabricacao, qtdPecasNaoMetalicas, qtdPecasMetalicas1, qtdPecasMetalicas2, qtdPecasMetalicas3, status);
    }

    @Override
    public String toString() {
        return "PecaMetalica1 [" + "id=" + id + ", dataFabricacao=" + dataFabricacao +
                ", qtdPecasNaoMetalicas=" + qtdPecasNaoMetalicas + ", qtdPecasMetalicas1=" + qtdPecasMetalicas1 +
                ", qtdPecasMetalicas2=" + qtdPecasMetalicas2 + ", qtdPecasMetalicas3=" + qtdPecasMetalicas3 +
                ", status=" + status + ']';
    }
}
