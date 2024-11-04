package view;
import java.util.List;
import model.PecaMetalica1;

public class PecaMetalica1_View {
    public void exibirListaDePecas(List<PecaMetalica1> pecas) {

        System.out.println("==== Lista de Peças Metálicas 1 ====");
        System.out.println("--------------------");

        for (PecaMetalica1 peca : pecas) {
            exibirPecas(peca);
        }
        System.out.println("--------------------");
    }


    public void exibirPecas(PecaMetalica1 peca) {
        if (peca != null) {
            System.out.println(peca.toString());
        } else {
            System.out.println("A peça não foi encontrada");
        }
    }

}
