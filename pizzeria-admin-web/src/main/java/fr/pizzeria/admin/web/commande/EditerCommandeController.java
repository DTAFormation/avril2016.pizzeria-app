package fr.pizzeria.admin.web.commande;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.admin.metier.CommandeService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.CommandePizza;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/commandes/edit")
public class EditerCommandeController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditerCommandeController.class.getName());

    public static final String URL = "/commandes/edit";
    private static final String VUE_EDITER_COMMANDE = "/WEB-INF/views/commandes/editerCommande.jsp";

    @Inject private CommandeService commandeService;
    @Inject private PizzaService pizzaService;
    @Inject private ClientService clientService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String code = req.getParameter("code");

        if (code == null || code.isEmpty()) {
            resp.setStatus(400); // Bad Request
            req.setAttribute("msgErreur",
                    "Code obligatoire pour editer une commande");
            this.getServletContext().getRequestDispatcher(VUE_EDITER_COMMANDE)
                    .forward(req, resp);
        } else {
            Commande commande = this.commandeService.findOneCommande(code);
            if (commande == null) {
//                sendErrorCommandeInconnue(req, resp);
            } else {
            	// TODO: utiliser livreurService.findAll => seulement les livreurs disponibles
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
        		List<Client> clients = clientService.findAll();
        		
        		StatutCommande[] statuts = StatutCommande.values();
            	
                req.setAttribute("commande", commande);
                req.setAttribute("statuts", statuts);
        		req.setAttribute("livreurs", livreursDisponibles);
        		req.setAttribute("clients", clients);
                this.getServletContext()
                        .getRequestDispatcher(VUE_EDITER_COMMANDE)
                        .forward(req, resp);
            }
        }

    }

//    private void sendErrorPizzaInconnue(HttpServletRequest req,
//                                        HttpServletResponse resp) throws ServletException, IOException {
//        resp.setStatus(400); // Bad Request
//        req.setAttribute("msgErreur", "Code pizza inconnu");
//        this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA)
//                .forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String numeroParam = req.getParameter("numero");
    	String idParam = req.getParameter("id");
		String statutParam = req.getParameter("statut");
		String dateParam = req.getParameter("date");
		String livreurIdParam = req.getParameter("livreur");
		String clientIdParam = req.getParameter("client");
		
		if (isBlank(numeroParam) || isBlank(statutParam) || isBlank(dateParam) || isBlank(livreurIdParam) || isBlank(clientIdParam) || isBlank(idParam)) {
			req.setAttribute("msgErreur", "Tous les paramètres sont obligatoires !");
			this.getServletContext().getRequestDispatcher(VUE_EDITER_COMMANDE).forward(req, resp);
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
			
			int id = Integer.parseInt(idParam);

			Commande commandeId = new Commande(id, numeroParam, statut, date, l, c);
//			commandeService.saveCommande(commandeSansId);
			
			// Ajout des pizzas
			List<Pizza> allPizzas = pizzaService.findAll();
			allPizzas.forEach(p -> {
				int qte = Integer.parseInt(req.getParameter(p.getCode()));
				commandeId.addPizza(p, qte);
			});
			commandeService.updateCommande(commandeId.getNumeroCommande(), commandeId);
			
			// Redirection
			resp.sendRedirect(req.getContextPath() + "/commandes/list");
		}
    }

    protected boolean isBlank(String param) {
        return param == null || param.isEmpty();
    }

}
