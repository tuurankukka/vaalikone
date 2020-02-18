package rest;

import javax.ws.rs.GET;

import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persist.Ehdokkaat;
import persist.Kysymykset;

@Path("/kysymyspalvelu")
public class restfilu {

	@POST
	@Path("/lisaaKysymys")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Kysymykset postKysymys(Kysymykset k) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(k);
		em.getTransaction().commit();
		k.setKysymys(k.getKysymys());
		em.close();
		return k;
	}

	@GET
	@Path("/haeKysymykset")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Kysymykset> haeKaikkiKysymykset() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT k FROM Kysymykset k");
		List<Kysymykset> kysymysLista = (List<Kysymykset>) (q.getResultList());
		em.close();
		return kysymysLista;
	}
	
	@GET
	@Path("/haeYksiKysymys/{par1}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Kysymykset haeYksi(@PathParam("par1")int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();		
		Kysymykset kys = (Kysymykset) em.find(Kysymykset.class, id);
		em.close();
		return kys;					
	}
	
	@POST
	@Path("/muokkaaKysymys")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Kysymykset postMuokattuKysymys(Kysymykset k) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Kysymykset kys = (Kysymykset) em.find(Kysymykset.class, k.getKysymysId());
		if (kys!=null) {
			em.merge(k);
		}
		em.getTransaction().commit();		
		em.close();
		return kys;
	}
	
	@POST
	@Path("/poistaKysymys")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Kysymykset postPoistettuKysymys(Kysymykset k) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		int id = k.getKysymysId();
		k = em.find(Kysymykset.class, id);
		if (k!=null) {
			em.remove(k);
		}
		em.getTransaction().commit();
		em.close();
		return k;
	}
	@POST
	@Path("/lisaaEhdokas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Ehdokkaat postEhdokas(Ehdokkaat k) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(k);
		em.getTransaction().commit();
		k.setEhdokasId(k.getEhdokasId());
		em.close();
		return k;
	}
	
	
}
