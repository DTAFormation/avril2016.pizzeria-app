package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.admin.metier.CommandeService;
import fr.pizzeria.admin.metier.LivreurService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;

/**
 * Contrôleur de la page Nouvelle Commande.
 */
@WebServlet("/commandes/new")
public class NouvelleCommandeController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(NouvelleCommandeController.class.getName());
	private static final String VUE_NOUVELLE_COMMANDE = "/WEB-INF/views/commandes/editerCommande.jsp";

	@Inject 
	private CommandeService commandeService;
	
	@Inject 
	private PizzaService pizzaService;
	
	@Inject 
	private ClientService clientService;
	
	@Inject
	private LivreurService livreurService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Définition des attributs par défaut
		List<Livreur> livreursDisponibles = livreurService.findAll();
		List<Pizza> pizzas = pizzaService.findAll();
		List<Client> clients = clientService.findAll();
		StatutCommande[] statuts = StatutCommande.values();

		Commande commande = new Commande();
		commande.setDateCommande(Calendar.getInstance());

		req.setAttribute("commande", commande);
		req.setAttribute("statuts", statuts);
		req.setAttribute("livreurs", livreursDisponibles);
		req.setAttribute("clients", clients);
		req.setAttribute("pizzas", pizzas);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_NOUVELLE_COMMANDE);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numeroParam = req.getParameter("numero");
		String statutParam = req.getParameter("statut");
		String dateParam = req.getParameter("date");
		String livreurIdParam = req.getParameter("livreur");
		String clientIdParam = req.getParameter("client");

		if (isBlank(numeroParam) || isBlank(statutParam) || isBlank(dateParam) || isBlank(livreurIdParam)
				|| isBlank(clientIdParam)) {
			
			req.setAttribute("msgErreur", "Tous les paramètres sont obligatoires !");
			this.getServletContext().getRequestDispatcher(VUE_NOUVELLE_COMMANDE).forward(req, resp);

		} else if (commandeService.isCodeTaken(numeroParam) > 0) {
			List<Livreur> livreursDisponibles = livreurService.findAll();
			List<Pizza> pizzas = pizzaService.findAll();
			List<Client> clients = clientService.findAll();
			StatutCommande[] statuts = StatutCommande.values();
			
			Commande commande = new Commande();
			commande.setDateCommande(Calendar.getInstance());
			
			req.setAttribute("msgErreur", "Le numéro de commande existe déjà !");
			req.setAttribute("commande", commande);
			req.setAttribute("statuts", statuts);
			req.setAttribute("livreurs", livreursDisponibles);
			req.setAttribute("clients", clients);
			req.setAttribute("pizzas", pizzas);
			this.getServletContext().getRequestDispatcher(VUE_NOUVELLE_COMMANDE).forward(req, resp);
			
		} else {
			// Traitement des paramètres
			StatutCommande statut = StatutCommande.valueOf(statutParam);
			
			dateParam = dateParam.replace('T', ' ');
			Calendar date = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				date.setTime(sdf.parse(dateParam));
			} catch (ParseException e) {
				throw new IllegalArgumentException("Format de la date incorrect !");
			}

			int livreurId = Integer.parseInt(livreurIdParam);
			Livreur l = new Livreur();
			l.setId(livreurId);

			int clientId = Integer.parseInt(clientIdParam);
			Client c = new Client();
			c.setId(clientId);

			// Enregistrement de la commande
			Commande commandeSansId = new Commande(numeroParam, statut, date, l, c);
			commandeService.saveCommande(commandeSansId);

			// Ajout des pizzas
			// TODO: uniquement celles dont qte > 0
			List<Pizza> allPizzas = pizzaService.findAll();
			allPizzas.forEach(p -> {
				int qte = Integer.parseInt(req.getParameter(p.getCode()));
				commandeSansId.addPizza(p, qte);
			});
			commandeService.updateCommande(commandeSansId.getNumeroCommande(), commandeSansId);

			// Redirection
			resp.sendRedirect(req.getContextPath() + "/commandes/list");
		}
	}

	protected boolean isBlank(String param) {
		return param == null || param.isEmpty();
	}
}
