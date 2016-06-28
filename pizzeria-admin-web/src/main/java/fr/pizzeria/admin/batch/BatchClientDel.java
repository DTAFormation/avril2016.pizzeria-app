package fr.pizzeria.admin.batch;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.pizzeria.admin.metier.ClientService;
@Stateless
public class BatchClientDel   {

	
	@Inject
	private ClientService cs;
	
		   public BatchClientDel(){
		         System.out.println("___constructeur du batch____");

		   }
		   @Schedule(minute="*")
		   public void batch() {
		    try {
		    	 System.out.println("debut de la tache planifier");
		         cs.hardDeleteClients();
		         System.out.println("le batch est passé");
		            } catch (Exception e) {

		        System.out.println("error running thread " + e.getMessage());
		    }
		}
}
