package fr.pizzeria.admin.web.utilisateur;

import fr.pizzeria.admin.metier.UtilisateurService;
import fr.pizzeria.model.Utilisateur;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/utilisateurs/new")
public class NouvelUtilisateurController extends HttpServlet {

  public static final String URL = "/utilisateurs/new";
  private static final String VUE_NOUVEL_UTILISATEUR = "/WEB-INF/views/pizzas/editerUtilisateur.jsp";
  @Inject
  private UtilisateurService utilisateurService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("utilisateur", new Utilisateur());
    this.getServletContext().getRequestDispatcher(VUE_NOUVEL_UTILISATEUR).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String nom = req.getParameter("nom");
    String prenom = req.getParameter("prenom");
    String email = req.getParameter("email");
    String motDePasse = req.getParameter("motDePasse");

    if (isBlank(email) || isBlank(motDePasse)) {
      req.setAttribute("msgErreur", "L'email et le mot de passe sont obligatoires !");
      this.getServletContext().getRequestDispatcher(VUE_NOUVEL_UTILISATEUR).forward(req, resp);
    } else {
      Utilisateur utilisateurSansId = new Utilisateur(nom, prenom, email, motDePasse);
      utilisateurService.saveUtilisateur(utilisateurSansId);;
      resp.sendRedirect("/utilisateurs/list");
    }
  }

  protected boolean isBlank(String param) {
    return param == null || param.isEmpty();
  }
}
