package model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "peca_metalica_1")
public class PecaMetalica1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data_fabricacao")
    private LocalDate dataFabricacao;
    @Column(name = "id_contagem")
    private Integer idContagem;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "tempo_ciclo")
    private LocalTime tempoCiclo;
    @Column(name = "peca1_status")
    private Boolean status;

    public PecaMetalica1() {
    }

    public PecaMetalica1(LocalDate dataFabricacao, Integer idContagem, String tipo, String descricao, LocalTime tempoCiclo, Boolean status) {
       this.dataFabricacao = dataFabricacao;
       this.idContagem = idContagem;
       this.tipo = tipo;
       this.descricao = descricao;
       this.tempoCiclo = tempoCiclo;
       this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdContagem() {
        return idContagem;
    }

    public void setIdContagem(Integer idContagem) {
        this.idContagem = idContagem;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalTime getTempoCiclo() {
        return tempoCiclo;
    }

    public void setTempoCiclo(LocalTime tempoCiclo) {
        this.tempoCiclo = tempoCiclo;
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
        if (!(o instanceof PecaMetalica1 that)) return false;
        return Objects.equals(dataFabricacao, that.dataFabricacao) && Objects.equals(tipo, that.tipo) && Objects.equals(descricao, that.descricao) && Objects.equals(tempoCiclo, that.tempoCiclo) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataFabricacao, tipo, descricao, tempoCiclo, status);
    }

    @Override
    public String toString() {
        return "PecaMetalica1 [" + "id=" + id + ", dataFabricacao=" + dataFabricacao +
                ", idContagem=" + idContagem + ", tipo=" + tipo + ", descricao=" + descricao +
                ", tempoCiclo=" + tempoCiclo + ", status=" + status + ']';
    }
}
