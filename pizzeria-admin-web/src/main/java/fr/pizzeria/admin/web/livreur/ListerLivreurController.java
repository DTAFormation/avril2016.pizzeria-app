package fr.pizzeria.admin.web.livreur;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.LivreurService;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/livreurs/list")
public class ListerLivreurController extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(ListerLivreurController.class.getName());

  private static final String VUE_LISTER_LIVREURS = "/WEB-INF/views/livreurs/listerLivreurs.jsp";
  private static final String ACTION_EDITER = "editer";
  private static final String ACTION_SUPPRIMER = "supprimer";

  @Inject private LivreurService livreurService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("listeLivreurs", this.livreurService.findAll());
    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_LIVREURS);
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action"); // editer ou supprimer
    String id = req.getParameter("id"); // identifiant de la pizza

    switch (action) {

      case ACTION_EDITER:
        resp.sendRedirect(this.getServletContext().getContextPath()
            + EditerLivreurController.URL
            + "?id="
            + id);
        break;
      case ACTION_SUPPRIMER:
        livreurService.deleteLivreur(id);
        req.setAttribute("msg", "le livreur avec l'id = " + id + " a été supprimée");
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
