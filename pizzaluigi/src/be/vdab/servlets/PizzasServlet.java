package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
/*import java.util.Arrays; */
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Pizza;
import be.vdab.repositories.PizzaRepository;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZA_REQUESTS = "pizzasRequests";
	private final PizzaRepository pizzaRepository = new PizzaRepository();
	private String pizzaFotosPad;
	
	@Override
	public void init() throws ServletException {
		pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
		this.getServletContext().setAttribute(PIZZA_REQUESTS, new AtomicInteger());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/**		request.setAttribute("pizzas", Arrays.asList(
				new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true),
				new Pizza(14, "Margherita", BigDecimal.valueOf(5), false),
				new Pizza(17, "Calzone", BigDecimal.valueOf(4), false))); */
		
//		Map<Long, Pizza> pizzas = new LinkedHashMap<>(); // keys zijn pizza ids
//		pizzas.put(12L, new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true));
//		pizzas.put(14L, new Pizza(14, "Margherita", BigDecimal.valueOf(5), false));
//		pizzas.put(17L, new Pizza(17, "Calzone", BigDecimal.valueOf(4), false));
//		pizzas.put(23L, new Pizza(23, "Fungi & Olive", BigDecimal.valueOf(5), false));
		
		((AtomicInteger) this.getServletContext().getAttribute(PIZZA_REQUESTS))
		.incrementAndGet();
		List<Pizza> pizzas = pizzaRepository.findAll();
		
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("pizzaIdsMetFoto",
				pizzas.stream()
				.filter(pizza -> Files.exists(Paths.get(pizzaFotosPad,
						pizza.getId() + ".jpg")))
				.map(pizza -> pizza.getId())
				.collect(Collectors.toList()));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}