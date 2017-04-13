package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import be.vdab.entities.Pizza;

public class PizzaRepository {
	private static final Map<Long, Pizza> Pizzas = new ConcurrentHashMap<>();
	static {
		Pizzas.put(12L, new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true));
		Pizzas.put(14L, new Pizza(14, "Margehrita", BigDecimal.valueOf(5), false));
		Pizzas.put(17L, new Pizza(17, "Calzone", BigDecimal.valueOf(4), false));
		Pizzas.put(23L, new Pizza(23, "Fungi & Olive", BigDecimal.valueOf(5), false));
	}
	public List<Pizza> findAll() {
		return new ArrayList<>(Pizzas.values());
	}
	public Optional<Pizza> read(long id) {
		Pizza pizza = Pizzas.get(id);
		return pizza == null ? Optional.empty() : Optional.of(pizza);
	}
	public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
		return Pizzas.values().stream()
				.filter(
						pizza -> pizza.getPrijs().compareTo(van) >= 0 &&
								pizza.getPrijs().compareTo(tot) <= 0)
				.collect(Collectors.toList());
	}
	public void create(Pizza pizza) { //pizza toevoegan
		pizza.setId(Collections.max(Pizzas.keySet()) + 1);
		Pizzas.put(pizza.getId(), pizza);
	}
}
