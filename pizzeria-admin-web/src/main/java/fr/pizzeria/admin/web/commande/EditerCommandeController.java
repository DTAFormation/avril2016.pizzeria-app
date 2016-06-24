package fr.pizzeria.admin.web.commande;

import fr.pizzeria.admin.metier.CommandeService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/commandes/edit")
public class EditerCommandeController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditerCommandeController.class.getName());

    public static final String URL = "/commandes/edit";
    private static final String VUE_EDITER_COMMANDE = "/WEB-INF/views/commandes/editerCommande.jsp";

    @Inject private CommandeService commandeService;
    @Inject private PizzaService pizzaService;

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
        		
        		StatutCommande[] statuts = StatutCommande.values();
            	
                req.setAttribute("commande", commande);
                req.setAttribute("statuts", statuts);
        		req.setAttribute("livreurs", livreursDisponibles);
        		req.setAttribute("clients", clients);
        		req.setAttribute("pizzas", pizzaService.findAll());
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

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        String id = req.getParameter("id");
//        String code = req.getParameter("code");
//        String nom = req.getParameter("nom");
//        String urlImage = req.getParameter("urlImage");
//        String prix = req.getParameter("prix");
//        String categorie = req.getParameter("categorie");
//
//        if (isBlank(nom) || isBlank(urlImage) || isBlank(prix)) {
//            req.setAttribute("pizza", this.pizzaService.findOnePizza(code));
//            req.setAttribute("msgErreur", "Tous les paramètres sont obligatoires !");
//            this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA)
//                    .forward(req, resp);
//        } else {
//            Pizza pizzaAvecId = new Pizza(Integer.valueOf(id), code, nom, new BigDecimal(prix), CategoriePizza.VIANDE, urlImage);
//
//            pizzaService.updatePizza(code, pizzaAvecId);
//            resp.sendRedirect(this.getServletContext().getContextPath()
//                    + "/pizzas/list");
//        }
//    }

    protected boolean isBlank(String param) {
        return param == null || param.isEmpty();
    }

}
