package view;
import java.util.List;
import model.PecaMetalica2;

public class PecaMetalica2_View {
    public void exibirListaDePecas(List<PecaMetalica2> pecas) {

        System.out.println("==== Lista de Peças Metálicas 2 ====");
        System.out.println("--------------------");

        for (PecaMetalica2 peca : pecas) {
            exibirPecas(peca);
        }
        System.out.println("--------------------");
    }


    public void exibirPecas(PecaMetalica2 peca) {
        if (peca != null) {
            System.out.println(peca.toString());
        } else {
            System.out.println("A peça não foi encontrada");
        }
    }

}
