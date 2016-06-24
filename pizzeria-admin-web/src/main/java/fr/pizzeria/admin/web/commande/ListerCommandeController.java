package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.CommandeService;
import fr.pizzeria.model.Commande;

/**
 * Contrôleur de la page Liste des commandes.
 */
@WebServlet("/commandes/list")
public class ListerCommandeController extends HttpServlet {

	  private static final Logger LOG = Logger.getLogger(ListerCommandeController.class.getName());
	  private static final String VUE_LISTER_COMMANDES = "/WEB-INF/views/commandes/listerCommandes.jsp";

	  @Inject private CommandeService commandeService;

	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
		  
		List<Commande> commandes = this.commandeService.findAll();
	    req.setAttribute("listeCommandes", commandes);
	    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_COMMANDES);
	    dispatcher.forward(req, resp);
	  }
}
