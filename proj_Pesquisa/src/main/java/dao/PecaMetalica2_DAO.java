package dao;

import model.PecaMetalica2;

import javax.persistence.*;
import java.util.List;

public class PecaMetalica2_DAO {
    private final EntityManagerFactory produtorDeEntidades = Persistence.createEntityManagerFactory("exemplo-jpa");
    private final EntityManager gerenciarEntidades;

    public PecaMetalica2_DAO() {
        gerenciarEntidades = produtorDeEntidades.createEntityManager();
    }

    public void adicionarPecaMetalica2(PecaMetalica2 pecaMetalica2) {
        gerenciarEntidades.getTransaction().begin(); //iniciar a transação
        gerenciarEntidades.persist(pecaMetalica2);
        gerenciarEntidades.getTransaction().commit();//confirmar as operações feitas
    }

    public List<PecaMetalica2> listarPecas2() {
        try {
            return gerenciarEntidades.createQuery("SELECT p FROM PecaMetalica2 p ORDER BY p.idContagem", PecaMetalica2.class)
                    .getResultList();
        }catch (NoResultException e) {
            throw new EntityNotFoundException("Nenhuma peça metalica do tipo 2 encontrada.");
        }
    }
    public void fechar() {
        gerenciarEntidades.close();
        produtorDeEntidades.close();
    }
}