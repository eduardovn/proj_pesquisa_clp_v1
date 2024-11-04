package controller;

import dao.PecaMetalica3_DAO;
import model.PecaMetalica3;
import service.Csv_Service;
import view.PecaMetalica3_View;

import java.util.ArrayList;

public class PecaMetalica3_Controller {
    private final PecaMetalica3_DAO pecaMetalica3DAO;
    private final PecaMetalica3_View pecaMetalica3View;

    private final ArrayList<PecaMetalica3> pecaMetalica3Lista;


    public PecaMetalica3_Controller(Csv_Service csvService) {
        pecaMetalica3DAO = new PecaMetalica3_DAO();
        pecaMetalica3View = new PecaMetalica3_View();

        pecaMetalica3Lista = csvService.getListaPecaMetalica3();
    }

    public void adicionarPeca() {
        for(int i = 0; i < pecaMetalica3Lista.size(); i++){
            PecaMetalica3 pecaMetalica3 = new PecaMetalica3(pecaMetalica3Lista.get(i).getDataFabricacao(), pecaMetalica3Lista.get(i).getIdContagem()+1, pecaMetalica3Lista.get(i).getTipo(), pecaMetalica3Lista.get(i).getDescricao(), pecaMetalica3Lista.get(i).getTempoCiclo(), pecaMetalica3Lista.get(i).getStatus());
            pecaMetalica3DAO.adicionarPecaMetalica3(pecaMetalica3);
        }
    }

    public void listarPecas() {
        pecaMetalica3View.exibirListaDePecas(pecaMetalica3DAO.listarPecas3());
    }

    public void fecharPecaMetalica3_DAO() {
        pecaMetalica3DAO .fechar();
    }
}
