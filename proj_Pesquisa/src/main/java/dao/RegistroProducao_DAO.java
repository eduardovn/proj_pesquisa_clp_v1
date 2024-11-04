package dao;

import model.RegistroProducao;

import javax.persistence.*;
import java.util.List;

public class RegistroProducao_DAO {
    private final EntityManagerFactory produtorDeEntidades = Persistence.createEntityManagerFactory("exemplo-jpa");
    private final EntityManager gerenciarEntidades;

    public RegistroProducao_DAO() {
        gerenciarEntidades = produtorDeEntidades.createEntityManager();
    }

    public void adicionarRegistro(RegistroProducao registroProducao) {
        gerenciarEntidades.getTransaction().begin(); //iniciar a transação
        gerenciarEntidades.persist(registroProducao);
        gerenciarEntidades.getTransaction().commit();//confirmar as operações feitas
    }

    public List<RegistroProducao> listarRegistroProducao() {
        try {
            return gerenciarEntidades.createQuery("SELECT r FROM RegistroProducao r ORDER BY r.id", RegistroProducao.class)
                    .getResultList();
        }catch (NoResultException e) {
            throw new EntityNotFoundException("Nenhum registro foi encontrado.");
        }
    }
    public void fechar() {
        gerenciarEntidades.close();
        produtorDeEntidades.close();
    }
}