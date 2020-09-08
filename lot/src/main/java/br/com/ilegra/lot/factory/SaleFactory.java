package br.com.ilegra.lot.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ilegra.lot.entity.EntityClass;
import br.com.ilegra.lot.entity.Item;
import br.com.ilegra.lot.entity.Sale;

public class SaleFactory implements EntityFactory {

	@Override
	public EntityClass create(String register) {
		String[] splitedRegistries = register.split("ç");
		Sale sale = new Sale(new Integer(splitedRegistries[1]), createSaleItems(splitedRegistries[2]),
				splitedRegistries[3]);

		return sale;
	}

	private List<Item> createSaleItems(String itemsRegister) {
		List<Item> saleItems = new ArrayList();
		List<String> items = Arrays.asList(itemsRegister.replace("[", "").replace("]", "").split(","));
		EntityFactory saleItemsFactory = new ItemFactory();
		for (String item : items) {
			saleItems.add((Item) saleItemsFactory.create(item));
		}
		return saleItems;
	}

}