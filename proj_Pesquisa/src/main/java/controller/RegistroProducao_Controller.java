package controller;

import dao.RegistroProducao_DAO;
import model.RegistroProducao;
import service.Csv_Service;
import view.RegistroProducao_View;

import java.time.LocalDate;
import java.util.ArrayList;


public class RegistroProducao_Controller {
    private final RegistroProducao_DAO registroProducaoDAO;
    private final RegistroProducao_View registroProducaoView;
    private final RegistroProducao registroProducao;


    public RegistroProducao_Controller(Csv_Service csvService) {
        registroProducaoDAO = new RegistroProducao_DAO();
        registroProducaoView = new RegistroProducao_View();
        registroProducao =  csvService.getRegistroProducao();
    }

    public void adicionarRegistro() {
            registroProducaoDAO.adicionarRegistro(registroProducao);
    }

    public void listarRegistros() {
        registroProducaoView.exibirListaDeRegistros(registroProducaoDAO.listarRegistroProducao());
    }

    public void fecharRegistroProducao_DAO() {
        registroProducaoDAO .fechar();
    }
}
