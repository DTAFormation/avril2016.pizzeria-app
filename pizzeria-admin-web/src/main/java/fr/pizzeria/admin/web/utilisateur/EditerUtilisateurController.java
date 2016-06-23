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
import java.util.logging.Logger;

@WebServlet("/utilisateurs/edit")
public class EditerUtilisateurController extends HttpServlet {

    private static final Logger LOG = Logger
            .getLogger(EditerUtilisateurController.class.getName());

    public static final String URL = "/utilisateurs/edit";
    private static final String VUE_EDITER_UTILISATEUR = "/WEB-INF/views/pizzas/editerUtilisateur.jsp";

    @Inject
    private UtilisateurService utilisateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");

        if (email == null || email.isEmpty()) {
            resp.setStatus(400); // Bad Request
            req.setAttribute("msgErreur",
                    "Email obligatoire pour editer un utilisateur");
            this.getServletContext().getRequestDispatcher(VUE_EDITER_UTILISATEUR)
                    .forward(req, resp);
        } else {

            Utilisateur utilisateur = this.utilisateurService.findOneUtilisateur(email);
            if (utilisateur == null) {
                sendErrorUtilisateurInconnu(req, resp);
            } else {
                req.setAttribute("utilisateur", utilisateur);
                this.getServletContext()
                        .getRequestDispatcher(VUE_EDITER_UTILISATEUR)
                        .forward(req, resp);
            }
        }

    }

    private void sendErrorUtilisateurInconnu(HttpServletRequest req,
                                        HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(400); // Bad Request
        req.setAttribute("msgErreur", "Email utilisateur inconnu");
        this.getServletContext().getRequestDispatcher(VUE_EDITER_UTILISATEUR)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String email = req.getParameter("email");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String motDePasse = req.getParameter("motDePasse");

        if (isBlank(email) || isBlank(motDePasse)) {
            req.setAttribute("utilisateur", this.utilisateurService.findOneUtilisateur(email));
            req.setAttribute("msgErreur", "Tous les paramètres sont obligatoires !");
            this.getServletContext().getRequestDispatcher(VUE_EDITER_UTILISATEUR)
                    .forward(req, resp);
        } else {
        	Utilisateur utilisateurAvecId = new Utilisateur(new Integer(id), nom, prenom, email, motDePasse);

        	utilisateurService.updateUtilisateur(email, utilisateurAvecId);
            resp.sendRedirect(this.getServletContext().getContextPath()
                    + "/utilisateurs/list");
        }
    }

    protected boolean isBlank(String param) {
        return param == null || param.isEmpty();
    }

}
