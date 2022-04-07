package fr.eni.jee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("")
public class DemoCookieServlet extends HttpServlet {
	// je stocke dans un attribut le nombre de requêtes HTTP que je vais faire au servlet
	private int nbRequetes = 0;
	
	/**
	 * doGet : à chaque requête:
	 * - j'incrémente mon attribut nbRequetes
	 * - j'affiche les cookies de ma requête
	 * - j'ajoute un cookie à la réponse  
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je recupère le flux d'écriture de la réponse pour écrire dedans
		PrintWriter out = response.getWriter();
		
		// 1 - j'incrémente mon attribut nbRequetes
		nbRequetes++;
		
		
		// 2 - j'affiche les cookies de ma requête
		Cookie[] cookies = request.getCookies();
		out.println("----- Liste des cookies ------");
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				out.println("nom=" + cookie.getName() + ", valeur=" + cookie.getValue());
			}
		}
		
		// 3 - j'ajoute un cookie à la réponse 
		Cookie nouveauCookie = new Cookie("cookie_" + nbRequetes , "valeur_" + nbRequetes);
		nouveauCookie.setMaxAge(20); // IMPORTANT : je définit une durée de vie en secondes pour le cookie (si j'oublie, le cookie sera détruit à la fermeture du navigateur)
		response.addCookie(nouveauCookie);
		
	}
}
