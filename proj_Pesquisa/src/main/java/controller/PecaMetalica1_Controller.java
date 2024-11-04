package controller;

import dao.PecaMetalica1_DAO;
import model.PecaMetalica1;
import service.Csv_Service;
import view.PecaMetalica1_View;
import java.util.ArrayList;


public class PecaMetalica1_Controller {
    private final PecaMetalica1_DAO pecaMetalica1DAO;
    private final PecaMetalica1_View pecaMetalica1View;
    private final ArrayList<PecaMetalica1> pecaMetalica1Lista;


    public PecaMetalica1_Controller(Csv_Service csvService) {
        pecaMetalica1DAO = new PecaMetalica1_DAO();
        pecaMetalica1View = new PecaMetalica1_View();

        pecaMetalica1Lista = csvService.getListaPecaMetalica1();
    }

    public void adicionarPeca() {
        for(int i = 0; i < pecaMetalica1Lista.size(); i++){
             PecaMetalica1 pecaMetalica1 = new PecaMetalica1(pecaMetalica1Lista.get(i).getDataFabricacao(), pecaMetalica1Lista.get(i).getIdContagem()+1, pecaMetalica1Lista.get(i).getTipo(), pecaMetalica1Lista.get(i).getDescricao(), pecaMetalica1Lista.get(i).getTempoCiclo(), pecaMetalica1Lista.get(i).getStatus());
             pecaMetalica1DAO.adicionarPecaMetalica1(pecaMetalica1);
        }
    }

    public void listarPecas() {
        pecaMetalica1View.exibirListaDePecas(pecaMetalica1DAO.listarPecas1());
    }

    public void fecharPecaMetalica1_DAO() {
        pecaMetalica1DAO .fechar();
    }
}
