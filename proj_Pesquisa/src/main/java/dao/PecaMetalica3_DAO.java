package dao;

import model.PecaMetalica3;

import javax.persistence.*;
import java.util.List;

public class PecaMetalica3_DAO {
    private final EntityManagerFactory produtorDeEntidades = Persistence.createEntityManagerFactory("exemplo-jpa");
    private final EntityManager gerenciarEntidades;

    public PecaMetalica3_DAO() {
        gerenciarEntidades = produtorDeEntidades.createEntityManager();
    }

    public void adicionarPecaMetalica3(PecaMetalica3 pecaMetalica3) {
        gerenciarEntidades.getTransaction().begin(); //iniciar a transação
        gerenciarEntidades.persist(pecaMetalica3);
        gerenciarEntidades.getTransaction().commit();//confirmar as operações feitas
    }

    public List<PecaMetalica3> listarPecas3() {
        try {
            return gerenciarEntidades.createQuery("SELECT p FROM PecaMetalica3 p ORDER BY p.idContagem", PecaMetalica3.class)
                    .getResultList();
        }catch (NoResultException e) {
            throw new EntityNotFoundException("Nenhuma peça metalica do tipo 3 encontrada.");
        }
    }
    public void fechar() {
        gerenciarEntidades.close();
        produtorDeEntidades.close();
    }
}