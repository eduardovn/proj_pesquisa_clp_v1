package service;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Csv_Service {

    private final ArrayList<PecaNaoMetalica> listaPecaNaoMetalica;
    private final ArrayList<PecaMetalica1> listaPecaMetalica1;
    private final ArrayList<PecaMetalica2> listaPecaMetalica2;
    private final ArrayList<PecaMetalica3> listaPecaMetalica3;
    
    private RegistroProducao registroProducao;

    public Csv_Service() {
        listaPecaNaoMetalica = new ArrayList<>();
        listaPecaMetalica1 = new ArrayList<>();
        listaPecaMetalica2 = new ArrayList<>();
        listaPecaMetalica3 = new ArrayList<>();
        registroProducao = new RegistroProducao();
    }

    public void processarCSV(String caminhoArquivoDeLeitura) {
        ArrayList<Dados> dados = new ArrayList<>();
        Dados dadosAnteriores, dadosAtuais;
        LocalTime tempoInicialCiclo;

        try {
            FileReader fileReader = new FileReader(caminhoArquivoDeLeitura);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Ler e ignorar a primeira linha
            bufferedReader.readLine();
            int indice = 0;

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] valorCampo = linha.split(",");

                //obter data
                String[] parte_1 = valorCampo[0].trim().split("T");
                String dataString = parte_1[0];
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataFinal = LocalDate.parse(dataString, formatterData);

                //obter horário
                String horarioString = parte_1[1];
                DateTimeFormatter formatterHorario = DateTimeFormatter.ofPattern("HH:mm:ss.SSSX");
                LocalTime horario = LocalTime.parse(horarioString, formatterHorario);

                // Corrigir para o fuso horário do sistema
                ZonedDateTime horarioUTC = ZonedDateTime.of(dataFinal, horario, ZoneId.of("UTC"));
                ZonedDateTime horarioFusoLocal = horarioUTC.withZoneSameInstant(ZoneId.systemDefault());
                LocalTime horarioFinal = horarioFusoLocal.toLocalTime();

                int qtdPecaNaoMetalica = Integer.parseInt(valorCampo[1].trim());
                int qtdPecaMetalicaPequena = Integer.parseInt(valorCampo[2].trim());
                int qtdPecaMetalicaMedia = Integer.parseInt(valorCampo[3].trim());
                int qtdPecaMetalicaGrande = Integer.parseInt(valorCampo[4].trim());

                Dados conteudo = new Dados(dataFinal, horarioFinal, qtdPecaNaoMetalica, qtdPecaMetalicaPequena, qtdPecaMetalicaMedia, qtdPecaMetalicaGrande);
                dados.add(conteudo);
                indice = dados.indexOf(conteudo);

                if(indice > 0){
                    dadosAnteriores = dados.get(indice-1);
                    dadosAtuais = dados.get(indice);
                    tempoInicialCiclo = dadosAnteriores.getTempoDeCiclo();

                    LocalTime tempoCicloPeca = subtrairTempos(dadosAtuais.getTempoDeCiclo(), tempoInicialCiclo);
                    registroProducao = processarItens(dadosAnteriores, dadosAtuais, tempoCicloPeca);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static LocalTime subtrairTempos(LocalTime tempoFinal, LocalTime tempoInicial) {
        long tempoInicialUnico = tempoInicial.toSecondOfDay();
        long tempoFinalUnico = tempoFinal.toSecondOfDay();
        long diferencaSegundos = tempoFinalUnico - tempoInicialUnico;

        int horasResultantes = (int) (diferencaSegundos / 3600);
        int minutosResultantes = (int) ((diferencaSegundos % 3600) / 60);
        int segundosResultantes = (int) (diferencaSegundos % 60);

        return LocalTime.of(horasResultantes, minutosResultantes, segundosResultantes);
    }



    //private void processarItens(Dados dadosAnteriores, Dados dadosAtuais,  LocalTime tempoInicialCiclo){
    private RegistroProducao processarItens(Dados dadosAnteriores, Dados dadosAtuais, LocalTime tempoCicloPeca){

        PecaNaoMetalica pecaNaoMetalica = new PecaNaoMetalica();
        PecaMetalica1 pecaMetalica1 = new PecaMetalica1();
        PecaMetalica2 pecaMetalica2 = new PecaMetalica2();
        PecaMetalica3 pecaMetalica3 = new PecaMetalica3();

        registroProducao.setDataFabricacao(dadosAnteriores.getDataFabricacao());
        registroProducao.setStatus(true);

        int qtdMetalica1Aux = 1;
        int qtdMetalica1Atual = 0;

        int qtdMetalica2Aux = 1;
        int qtdMetalica2Atual = 0;

        int qtdMetalica3Aux = 1;
        int qtdMetalica3Atual = 0;

        int qtdNaoMetalicaAux = 1;
        int qtdNaoMetalicaAtual = 0;


        if( (!dadosAnteriores.getContagemPecaNaoMetalica().equals(dadosAtuais.getContagemPecaNaoMetalica()))
                && (dadosAnteriores.getContagemPecaMetalicaPequena().equals(dadosAtuais.getContagemPecaMetalicaPequena()))
                && (dadosAnteriores.getContagemPecaMetalicaMedia().equals(dadosAtuais.getContagemPecaMetalicaMedia()))
                && (dadosAnteriores.getContagemPecaMetalicaGrande().equals(dadosAtuais.getContagemPecaMetalicaGrande()))){

            pecaNaoMetalica.setDataFabricacao(dadosAnteriores.getDataFabricacao());
            pecaNaoMetalica.setIdContagem(dadosAnteriores.getContagemPecaNaoMetalica());
            pecaNaoMetalica.setTempoCiclo(tempoCicloPeca);
            pecaNaoMetalica.setTipo("Não Metálica");
            pecaNaoMetalica.setDescricao("Direcionada para outro fluxo");
            pecaNaoMetalica.setStatus(true);

            listaPecaNaoMetalica.add(pecaNaoMetalica);

            qtdNaoMetalicaAtual = dadosAnteriores.getContagemPecaNaoMetalica();
            qtdNaoMetalicaAux = qtdNaoMetalicaAux + qtdNaoMetalicaAtual;
            registroProducao.setQtdPecasNaoMetalicas(qtdNaoMetalicaAux);
        }

        else if( (dadosAnteriores.getContagemPecaNaoMetalica().equals(dadosAtuais.getContagemPecaNaoMetalica()))
                && (!dadosAnteriores.getContagemPecaMetalicaPequena().equals(dadosAtuais.getContagemPecaMetalicaPequena()))
                && (dadosAnteriores.getContagemPecaMetalicaMedia().equals(dadosAtuais.getContagemPecaMetalicaMedia()))
                && (dadosAnteriores.getContagemPecaMetalicaGrande().equals(dadosAtuais.getContagemPecaMetalicaGrande()))){

            pecaMetalica1.setDataFabricacao(dadosAnteriores.getDataFabricacao());
            pecaMetalica1.setIdContagem(dadosAnteriores.getContagemPecaMetalicaPequena());
            pecaMetalica1.setTempoCiclo(tempoCicloPeca);
            pecaMetalica1.setTipo("Metálica Pequena");
            pecaMetalica1.setDescricao("separação tipo 1");
            pecaMetalica1.setStatus(true);

            listaPecaMetalica1.add(pecaMetalica1);

            qtdMetalica1Atual = dadosAnteriores.getContagemPecaMetalicaPequena();
            qtdMetalica1Aux = qtdMetalica1Aux + qtdMetalica1Atual;
            registroProducao.setQtdPecasMetalicas1(qtdMetalica1Aux);
        }

        else if( (dadosAnteriores.getContagemPecaNaoMetalica().equals(dadosAtuais.getContagemPecaNaoMetalica()))
                && (dadosAnteriores.getContagemPecaMetalicaPequena().equals(dadosAtuais.getContagemPecaMetalicaPequena()))
                && (!dadosAnteriores.getContagemPecaMetalicaMedia().equals(dadosAtuais.getContagemPecaMetalicaMedia()))
                && (dadosAnteriores.getContagemPecaMetalicaGrande().equals(dadosAtuais.getContagemPecaMetalicaGrande()))){

            pecaMetalica2.setDataFabricacao(dadosAnteriores.getDataFabricacao());
            pecaMetalica2.setIdContagem(dadosAnteriores.getContagemPecaMetalicaMedia());
            pecaMetalica2.setTempoCiclo(tempoCicloPeca);
            pecaMetalica2.setTipo("Metálica Média");
            pecaMetalica2.setDescricao("separação tipo 2");
            pecaMetalica2.setStatus(true);

            listaPecaMetalica2.add(pecaMetalica2);

            qtdMetalica2Atual = dadosAnteriores.getContagemPecaMetalicaMedia();
            qtdMetalica2Aux = qtdMetalica2Aux + qtdMetalica2Atual;
            registroProducao.setQtdPecasMetalicas2(qtdMetalica2Aux);
        }

        else if( (dadosAnteriores.getContagemPecaNaoMetalica().equals(dadosAtuais.getContagemPecaNaoMetalica()))
                && (dadosAnteriores.getContagemPecaMetalicaPequena().equals(dadosAtuais.getContagemPecaMetalicaPequena()))
                && (dadosAnteriores.getContagemPecaMetalicaMedia().equals(dadosAtuais.getContagemPecaMetalicaMedia()))
                && (!dadosAnteriores.getContagemPecaMetalicaGrande().equals(dadosAtuais.getContagemPecaMetalicaGrande()))){

            pecaMetalica3.setDataFabricacao(dadosAnteriores.getDataFabricacao());
            pecaMetalica3.setIdContagem(dadosAnteriores.getContagemPecaMetalicaGrande());
            pecaMetalica3.setTempoCiclo(tempoCicloPeca);
            pecaMetalica3.setTipo("Metálica Grande");
            pecaMetalica3.setDescricao("separação tipo 3");
            pecaMetalica3.setStatus(true);

            listaPecaMetalica3.add(pecaMetalica3);

            qtdMetalica3Atual = dadosAnteriores.getContagemPecaMetalicaGrande();
            qtdMetalica3Aux = qtdMetalica3Aux + qtdMetalica3Atual;
            registroProducao.setQtdPecasMetalicas3(qtdMetalica3Aux);
        }

        return registroProducao;
    }

    // ainda não feito ..
//    public void processarLote(ArrayList<Dados> dados1, ArrayList<Dados> dados2, int cont) {
//        LocalDate dataFabricacao;
//        LocalTime tempoDeCiclo;
//        Integer contagemPecaNaoMetalica, contagemPecaMetalicaPequena, contagemPecaMetalicaMedia, contagemPecaMetalicaGrande;
//
//        for (Dados dado : dados1) {
//            System.out.println("Anterior: "+dado);
////            dataFabricacao = dado.getDataFabricacao();
////            tempoDeCiclo = dado.getTempoDeCiclo();
////            contagemPecaNaoMetalica = dado.getContagemPecaNaoMetalica();
////            contagemPecaMetalicaPequena = dado.getContagemPecaMetalicaPequena();
////            contagemPecaMetalicaMedia = dado.getContagemPecaMetalicaMedia();
////            contagemPecaMetalicaGrande = dado.getContagemPecaMetalicaGrande();
//
//        }
//        for (Dados dado : dados2) {
//            System.out.println("Atual: " + dado);
//        }
//    }

    public ArrayList<PecaNaoMetalica> getListaPecaNaoMetalica() {
        return listaPecaNaoMetalica;
    }

    public ArrayList<PecaMetalica1> getListaPecaMetalica1() {
        return listaPecaMetalica1;
    }

    public ArrayList<PecaMetalica2> getListaPecaMetalica2() {
        return listaPecaMetalica2;
    }

    public ArrayList<PecaMetalica3> getListaPecaMetalica3() {
        return listaPecaMetalica3;
    }

    public RegistroProducao getRegistroProducao() {
        return registroProducao;
    }


}