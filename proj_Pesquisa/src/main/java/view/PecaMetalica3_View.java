package view;

import model.PecaMetalica3;

import java.util.List;

public class PecaMetalica3_View {
    public void exibirListaDePecas(List<PecaMetalica3> pecas) {

        System.out.println("==== Lista de Peças Metálicas 3 ====");
        System.out.println("--------------------");

        for (PecaMetalica3 peca : pecas) {
            exibirPecas(peca);
        }
        System.out.println("--------------------");
    }


    public void exibirPecas(PecaMetalica3 peca) {
        if (peca != null) {
            System.out.println(peca.toString());
        } else {
            System.out.println("A peça não foi encontrada");
        }
    }

}
