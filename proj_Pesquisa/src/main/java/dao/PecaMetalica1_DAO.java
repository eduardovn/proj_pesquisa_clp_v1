package dao;

import model.PecaMetalica1;

import javax.persistence.*;
import java.util.List;

public class PecaMetalica1_DAO {
    private final EntityManagerFactory produtorDeEntidades = Persistence.createEntityManagerFactory("exemplo-jpa");
    private final EntityManager gerenciarEntidades;

    public PecaMetalica1_DAO() {
        gerenciarEntidades = produtorDeEntidades.createEntityManager();
    }

    public void adicionarPecaMetalica1(PecaMetalica1 pecaMetalica1) {
        gerenciarEntidades.getTransaction().begin(); //iniciar a transação
        gerenciarEntidades.persist(pecaMetalica1);
        gerenciarEntidades.getTransaction().commit();//confirmar as operações feitas
    }

    public List<PecaMetalica1> listarPecas1() {
        try {
            return gerenciarEntidades.createQuery("SELECT p FROM PecaMetalica1 p ORDER BY p.idContagem", PecaMetalica1.class)
                    .getResultList();
        }catch (NoResultException e) {
            throw new EntityNotFoundException("Nenhuma peça metalica do tipo 1 encontrada.");
        }
    }
    public void fechar() {
        gerenciarEntidades.close();
        produtorDeEntidades.close();
    }
}