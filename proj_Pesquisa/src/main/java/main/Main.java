package main;

import controller.*;

import model.RegistroProducao;
import service.Csv_Service;


public class Main {
    public static void main(String[] args) {
        String caminhoArquivoLeitura = "historico.csv";

        Csv_Service csvService = new Csv_Service();
        csvService.processarCSV(caminhoArquivoLeitura);

        PecaNaoMetalica_Controller pecaNaoMetalica_controller = new PecaNaoMetalica_Controller(csvService);
        pecaNaoMetalica_controller.adicionarPeca();
        pecaNaoMetalica_controller.fecharPecaNaoMetalica_DAO();

        PecaMetalica1_Controller pecaMetalica1_controller = new PecaMetalica1_Controller(csvService);
        pecaMetalica1_controller.adicionarPeca();
        pecaMetalica1_controller.fecharPecaMetalica1_DAO();
//
        PecaMetalica2_Controller pecaMetalica2_controller = new PecaMetalica2_Controller(csvService);
        pecaMetalica2_controller.adicionarPeca();
        pecaMetalica2_controller.fecharPecaMetalica2_DAO();
//
        PecaMetalica3_Controller pecaMetalica3_controller = new PecaMetalica3_Controller(csvService);
        pecaMetalica3_controller.adicionarPeca();
        pecaMetalica3_controller.fecharPecaMetalica3_DAO();
//
        RegistroProducao_Controller registroProducao_controller = new RegistroProducao_Controller(csvService);
        registroProducao_controller.adicionarRegistro();
        registroProducao_controller.fecharRegistroProducao_DAO();
    }
}