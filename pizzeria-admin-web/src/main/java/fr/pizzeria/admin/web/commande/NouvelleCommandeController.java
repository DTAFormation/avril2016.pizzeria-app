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

import fr.pizzeria.admin.metier.CommandeService;
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
	private static final String VUE_NOUVELLE_COMMANDE = "/WEB-INF/views/commandes/editer.jsp";

	@Inject private CommandeService commandeService;
	@Inject private PizzaService pizzaService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO: utiliser livreurService.findAll => seulement les livreurs disponiles
		List<Livreur> livreursDisponibles = new ArrayList<>();
		Livreur l1 = new Livreur();
		l1.setId(1);
		l1.setPrenom("Bob");
		l1.setNom("Legros");
		Livreur l2 = new Livreur();
		l2.setId(2);
		l2.setPrenom("Jim");
		l2.setNom("Marshall");
		livreursDisponibles.add(l1);
		livreursDisponibles.add(l2);

		// TODO: utiliser clientService.findAll
		List<Client> clients = new ArrayList<>();
		Client c1 = new Client();
		c1.setId(1);
		c1.setPrenom("John");
		c1.setNom("Doe");
		Client c2 = new Client();
		c2.setId(2);
		c2.setPrenom("Dale");
		c2.setNom("Cooper");
		clients.add(c1);
		clients.add(c2);

		Commande commande = new Commande();
		commande.setDateCommande(Calendar.getInstance());

		StatutCommande[] statuts = StatutCommande.values();

		req.setAttribute("commande", commande);
		req.setAttribute("statuts", statuts);
		req.setAttribute("livreurs", livreursDisponibles);
		req.setAttribute("clients", clients);
		req.setAttribute("pizzas", pizzaService.findAll());
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
		
		if (isBlank(numeroParam) || isBlank(statutParam) || isBlank(dateParam) || isBlank(livreurIdParam) || isBlank(clientIdParam)) {
			req.setAttribute("msgErreur", "Tous les paramètres sont obligatoires !");
			this.getServletContext().getRequestDispatcher(VUE_NOUVELLE_COMMANDE).forward(req, resp);
		} else {
			// Traitement des params
			StatutCommande statut = StatutCommande.valueOf(statutParam);

			Calendar date = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			try {
				date.setTime(sdf.parse(req.getParameter("date")));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			int livreurId = Integer.parseInt(req.getParameter("livreur"));
			int clientId = Integer.parseInt(req.getParameter("client"));
			Livreur l = new Livreur();
			l.setId(livreurId);
			Client c = new Client();
			c.setId(clientId);

			Commande commandeSansId = new Commande(numeroParam, statut, date, l, c);
			commandeService.saveCommande(commandeSansId);
			
			// Ajout des pizzas
			List<Pizza> allPizzas = pizzaService.findAll();
			allPizzas.forEach(p -> {
				int qte = Integer.parseInt(req.getParameter(p.getCode()));
				if (qte > 0) {
					commandeSansId.addPizza(p, qte);
				}
			});
			commandeService.updateCommande(commandeSansId.getNumeroCommande(), commandeSansId);;
			
			// Redirection
			resp.sendRedirect(req.getContextPath() + "/commandes/list");
		}
	}

	protected boolean isBlank(String param) {
		return param == null || param.isEmpty();
	}
}
