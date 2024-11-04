package view;

import model.PecaNaoMetalica;

import java.util.List;

public class PecaNaoMetalica_View {
    public void exibirListaDePecas(List<PecaNaoMetalica> pecas) {

        System.out.println("==== Lista de Peças Não Metálicas ====");
        System.out.println("--------------------");

        for (PecaNaoMetalica peca : pecas) {
            exibirPecas(peca);
        }
        System.out.println("--------------------");
    }


    public void exibirPecas(PecaNaoMetalica peca) {
        if (peca != null) {
            System.out.println(peca.toString());
        } else {
            System.out.println("A peça não foi encontrada");
        }
    }

}
