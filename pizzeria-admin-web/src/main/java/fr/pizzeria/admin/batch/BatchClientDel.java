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

		   }
		   @Schedule(second ="0")
		   public void batch() {
		    try {

		         cs.hardDeleteClients();

		            } catch (Exception e) {

		        System.out.println("error running thread " + e.getMessage());
		    }
		}
}
