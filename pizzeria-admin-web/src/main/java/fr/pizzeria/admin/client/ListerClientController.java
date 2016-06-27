package fr.pizzeria.admin.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/clients/list")
public class ListerClientController extends HttpServlet {

	private static final String VUE_LISTER_CLIENTS = "/WEB-INF/views/clients/listerClients.jsp";
	private static final String ACTION_EDITER = "editer";
	private static final String ACTION_SUPPRIMER = "supprimer";

	@Inject
	private ClientService clientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listeClients", clientService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_CLIENTS);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action"); // editer ou supprimer
		String email = req.getParameter("email"); // identifiant du client

		switch (action) {

		case ACTION_EDITER:
			resp.sendRedirect(
					this.getServletContext().getContextPath() + EditerClientController.URL + "?email=" + email);
			break;
		case ACTION_SUPPRIMER:
			clientService.deleteClient(email);
			req.setAttribute("msg", "Le client email = " + email + " a été supprimé");
			doGet(req, resp);
			break;
		default:
			req.setAttribute("msg", "Action inconnue");
			resp.setStatus(400);
			doGet(req, resp);
			break;
		}
	}
}
