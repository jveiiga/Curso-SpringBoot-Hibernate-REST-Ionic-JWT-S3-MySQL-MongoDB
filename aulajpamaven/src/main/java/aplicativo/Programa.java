package aplicativo;

import dominio.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Programa {

    public static void main(String[] args) {
//      Aula de introdução 1
//      Imprimindo um usuário a partir da classe sem utilizar o mapeamento de objeto relacional(JPA)
//      Pessoa p1 = new Pessoa(null, "Raquel", "raquel@mail.com");
//      System.out.println(p1);

//      Aula de introdução 2 e 3
//      Persistencia dos dados no db a partir do hibernate-entitymanager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

//       Persiste dados no db de forma "manual"
//       em.getTransaction().begin();
//
//        em.persist(p1);
//        em.getTransaction().commit();

//      Deletando um dado no db de forma manual através de uma entidade monitorada
//      Pessoa p = em.find(Pessoa.class, 2);
//      em.getTransaction().begin();
//      em.remove(p);
//      em.getTransaction().commit();

//      Busca de pessoa pelo id no db
//      Pessoa p = em.find(Pessoa.class, 1);
//      System.out.println(p);

        System.out.println("Pronto!");
        em.close();
        emf.close();
    }
}
