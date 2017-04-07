package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/**		request.setAttribute("pizzas", Arrays.asList(
				new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true),
				new Pizza(14, "Margherita", BigDecimal.valueOf(5), false),
				new Pizza(17, "Calzone", BigDecimal.valueOf(4), false))); */
		Map<Long, Pizza> pizzas = new LinkedHashMap<>(); // keys zijn pizza ids
		pizzas.put(12L, new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true));
		pizzas.put(14L, new Pizza(14, "Margherita", BigDecimal.valueOf(5), false));
		pizzas.put(17L, new Pizza(17, "Calzone", BigDecimal.valueOf(4), false));
		
		request.setAttribute("pizzas", pizzas);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}