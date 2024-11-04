package controller;

import dao.PecaMetalica2_DAO;
import model.PecaMetalica2;
import service.Csv_Service;
import view.PecaMetalica2_View;

import java.util.ArrayList;

public class PecaMetalica2_Controller {
    private final PecaMetalica2_DAO pecaMetalica2DAO;
    private final PecaMetalica2_View pecaMetalica2View;
    private final ArrayList<PecaMetalica2> pecaMetalica2Lista;


    public PecaMetalica2_Controller(Csv_Service csvService) {
        pecaMetalica2DAO = new PecaMetalica2_DAO();
        pecaMetalica2View = new PecaMetalica2_View();

        pecaMetalica2Lista = csvService.getListaPecaMetalica2();
    }

    public void adicionarPeca() {
        for(int i = 0; i < pecaMetalica2Lista.size(); i++){
            PecaMetalica2 pecaMetalica2 = new PecaMetalica2(pecaMetalica2Lista.get(i).getDataFabricacao(), pecaMetalica2Lista.get(i).getIdContagem()+1, pecaMetalica2Lista.get(i).getTipo(), pecaMetalica2Lista.get(i).getDescricao(), pecaMetalica2Lista.get(i).getTempoCiclo(), pecaMetalica2Lista.get(i).getStatus());
            pecaMetalica2DAO.adicionarPecaMetalica2(pecaMetalica2);
        }
    }

    public void listarPecas() {
        pecaMetalica2View.exibirListaDePecas(pecaMetalica2DAO.listarPecas2());
    }

    public void fecharPecaMetalica2_DAO() {
        pecaMetalica2DAO .fechar();
    }
}
