package br.com.ilegra.lot.service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ilegra.lot.entity.Customer;
import br.com.ilegra.lot.entity.EntityClass;
import br.com.ilegra.lot.entity.Item;
import br.com.ilegra.lot.entity.Sale;
import br.com.ilegra.lot.entity.Seller;
import br.com.ilegra.lot.factory.CustomerFactory;
import br.com.ilegra.lot.factory.EntityFactory;
import br.com.ilegra.lot.factory.SaleFactory;
import br.com.ilegra.lot.factory.SellerFactory;

public class RegisterServiceImpl {

	private List<EntityClass> registers;

	public RegisterServiceImpl() {
		registers = new ArrayList<EntityClass>();
	}

	public void registerAll(List<String> lines) {
		lines.stream().forEach(line -> register(line));
	}

	public void register(String line) {
		EntityFactory factory = getCorrespondentFactory(line);
		registers.add(factory.create(line));
	}

	public void clear() {
		registers.clear();
	}

	public List<Customer> getCustomers() {
		return registers.stream().filter(reg -> reg instanceof Customer).map(reg -> (Customer) reg)
				.collect(Collectors.toList());
	}

	public List<Seller> getSellers() {
		return registers.stream().filter(reg -> reg instanceof Seller).map(reg -> (Seller) reg)
				.collect(Collectors.toList());
	}

	public List<Sale> getSales() {
		return registers.stream().filter(reg -> reg instanceof Sale).map(reg -> (Sale) reg)
				.collect(Collectors.toList());
	}

	public Integer getMostExpensiveSaleId() {
		BigDecimal mostExpensivePrice = BigDecimal.ZERO;
		Integer mostExpensiveSaleId = 0;
		List<Sale> sales = getSales();
		for (Sale sale : sales) {
			BigDecimal purchaseTotal = purchaseTotal(sale);
			if (mostExpensivePrice.compareTo(purchaseTotal) <= 0) {
				mostExpensiveSaleId = sale.getId();
				mostExpensivePrice = purchaseTotal;
			}
		}
		return mostExpensiveSaleId;
	}

	public String getWorstSeller() {
		List<Sale> sales = getSales();
		BigDecimal worstSalePrice = purchaseTotal(sales.get(0));
		Sale worstSale = sales.get(0);
		for (Sale sale : sales) {
			if (worstSalePrice.compareTo(purchaseTotal(sale)) < 0) {
			} else {
				worstSalePrice = purchaseTotal(sale);
				worstSale = sale;
			}
		}

		return worstSale.getSeller();
	}

	public String getReportResult() {
		return String.format("|Quantiade de clientes:%d| |Quantiade de vendedores:%d| |Id da venda mais cara:% 02d| |Nome do pior vendedor:%s|", getCustomers().size(), getSellers().size(), getMostExpensiveSaleId(),
				getWorstSeller());
	}

	private EntityFactory getCorrespondentFactory(String line) {
		String type = line.substring(0, 3);
		EntityFactory factory;

		switch (type) {
		case "001":
			factory = new SellerFactory();
			break;
		case "002":
			factory = new CustomerFactory();
			break;
		case "003":
			factory = new SaleFactory();
			break;
		default:
			throw new InvalidParameterException("Registro inválido");
		}
		return factory;
	}

	private BigDecimal purchaseTotal(Sale sale) {
		BigDecimal purchaseTotal = BigDecimal.ZERO;
		for (Item item : sale.getItems()) {
			purchaseTotal = purchaseTotal.add(item.getPrice());
		}
		return purchaseTotal;
	}
}