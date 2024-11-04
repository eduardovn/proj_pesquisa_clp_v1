package view;

import model.RegistroProducao;

import java.util.List;

public class RegistroProducao_View {
    public void exibirListaDeRegistros(List<RegistroProducao> registros) {

        System.out.println("==== Lista de Registros ====");
        System.out.println("--------------------");

        for (RegistroProducao registro : registros) {
            exibirPecas(registro);
        }
        System.out.println("--------------------");
    }

    public void exibirPecas(RegistroProducao registro) {
        if (registro != null) {
            System.out.println(registro.toString());
        } else {
            System.out.println("O registro não foi encontrada");
        }
    }

}
