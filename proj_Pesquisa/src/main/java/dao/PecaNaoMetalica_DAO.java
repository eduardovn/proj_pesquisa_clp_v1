package dao;

import model.PecaNaoMetalica;

import javax.persistence.*;
import java.util.List;

public class PecaNaoMetalica_DAO {
    private final EntityManagerFactory produtorDeEntidades = Persistence.createEntityManagerFactory("exemplo-jpa");
    private final EntityManager gerenciarEntidades;

    public PecaNaoMetalica_DAO() {
        gerenciarEntidades = produtorDeEntidades.createEntityManager();
    }

    public void adicionarPecaNaoMetalica(PecaNaoMetalica pecaNaoMetalica) {
        gerenciarEntidades.getTransaction().begin(); //iniciar a transação
        gerenciarEntidades.persist(pecaNaoMetalica);
        gerenciarEntidades.getTransaction().commit();//confirmar as operações feitas
    }

    public List<PecaNaoMetalica> listarPecasNaoMetalicas() {
        try {
            return gerenciarEntidades.createQuery("SELECT p FROM PecaNaoMetalica p ORDER BY p.idContagem", PecaNaoMetalica.class)
                    .getResultList();
        }catch (NoResultException e) {
            throw new EntityNotFoundException("Nenhuma peça Não Metalica.");
        }
    }
    public void fechar() {
        gerenciarEntidades.close();
        produtorDeEntidades.close();
    }
}