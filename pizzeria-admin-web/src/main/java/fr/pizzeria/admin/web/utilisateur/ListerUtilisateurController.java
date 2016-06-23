package fr.pizzeria.admin.web.utilisateur;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.admin.metier.UtilisateurService;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Contrôleur de la page Liste des utilisateurs.
 */
@WebServlet("/utilisateurs/list")
public class ListerUtilisateurController extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(ListerUtilisateurController.class.getName());

  private static final String VUE_LISTER_UTILISATEURS = "/WEB-INF/views/pizzas/listerUtilisateurs.jsp";
  private static final String ACTION_EDITER = "editer";
  private static final String ACTION_SUPPRIMER = "supprimer";

  @Inject private UtilisateurService utilisateurService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("listeUtilisateurs", this.utilisateurService.findAll());
    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_UTILISATEURS);
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action"); // editer ou supprimer
    String email = req.getParameter("email"); // identifiant de l'utilisateur

    switch (action) {

      case ACTION_EDITER:
        resp.sendRedirect(this.getServletContext().getContextPath()
            + EditerUtilisateurController.URL
            + "?email="
            + email);
        break;
      case ACTION_SUPPRIMER:
    	utilisateurService.deleteUtilisateur(email);
        req.setAttribute("msg", "L'utilisateur dont l'email est " + email + " a été supprimé");
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
