package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuarioService {
	
	public List<Usuario> listAllUsuario(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("config_hibernate");        
        EntityManager em = emf.createEntityManager();
        List<Usuario> users = null;
        Query query = em.createQuery("SELECT u FROM usuario u "); 
        users = query.getResultList();
        em.close();
        emf.close();
        return users;            
	}

	public Usuario getUsuario(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config_hibernate");        
        EntityManager em = emf.createEntityManager();
        
        Usuario usuario = em.find(Usuario.class, id);
        
        em.close();
        emf.close();
		return usuario;
	}

	public void insertUsuario(Usuario newUsuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config_hibernate");        
        EntityManager em = emf.createEntityManager();
        
        Usuario usuario = new Usuario();
        usuario.setNome(newUsuario.getNome());
        usuario.setEmail(newUsuario.getEmail());
        usuario.setSenha(newUsuario.getSenha());
        usuario.setIdade(newUsuario.getIdade());
        
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
        emf.close();		
	}

	public void updateUsuario(Usuario usuarioParaEditar) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config_hibernate");        
        EntityManager em = emf.createEntityManager();
        
        Usuario usuario = em.find(Usuario.class, usuarioParaEditar.getId());
        em.detach(usuario);
        usuario.setNome(usuarioParaEditar.getNome());
        usuario.setEmail(usuarioParaEditar.getEmail());
        usuario.setSenha(usuarioParaEditar.getSenha());
        usuario.setIdade(usuarioParaEditar.getIdade());
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
	}

	public void deleteUsuario(Usuario usuarioParaRemover) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config_hibernate");        
        EntityManager em = emf.createEntityManager();
        
		Usuario usuario = em.find(Usuario.class, usuarioParaRemover.getId());
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();

		em.close();
        emf.close();
	}

}
