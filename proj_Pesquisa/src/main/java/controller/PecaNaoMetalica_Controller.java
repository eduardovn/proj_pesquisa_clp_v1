package controller;

import dao.PecaNaoMetalica_DAO;
import model.PecaNaoMetalica;
import service.Csv_Service;
import view.PecaNaoMetalica_View;

import java.util.ArrayList;

public class PecaNaoMetalica_Controller {
    private final PecaNaoMetalica_DAO pecaNaoMetalicaDAO;
    private final PecaNaoMetalica_View pecaNaoMetalicaView;
    private final ArrayList<PecaNaoMetalica> pecaNaoMetalicaLista;


    public PecaNaoMetalica_Controller(Csv_Service csvService) {
        pecaNaoMetalicaDAO = new PecaNaoMetalica_DAO();
        pecaNaoMetalicaView = new PecaNaoMetalica_View();

        pecaNaoMetalicaLista = csvService.getListaPecaNaoMetalica();
    }

    public void adicionarPeca() {
        for(int i = 0; i < pecaNaoMetalicaLista.size(); i++){
            PecaNaoMetalica pecaNaoMetalica = new PecaNaoMetalica(pecaNaoMetalicaLista.get(i).getDataFabricacao(), pecaNaoMetalicaLista.get(i).getIdContagem()+1, pecaNaoMetalicaLista.get(i).getTipo(), pecaNaoMetalicaLista.get(i).getDescricao(), pecaNaoMetalicaLista.get(i).getTempoCiclo(), pecaNaoMetalicaLista.get(i).getStatus());
            pecaNaoMetalicaDAO.adicionarPecaNaoMetalica(pecaNaoMetalica);
        }
    }

    public void listarPecas() {
        pecaNaoMetalicaView.exibirListaDePecas(pecaNaoMetalicaDAO.listarPecasNaoMetalicas());
    }

    public void fecharPecaNaoMetalica_DAO() {
        pecaNaoMetalicaDAO .fechar();
    }
}
