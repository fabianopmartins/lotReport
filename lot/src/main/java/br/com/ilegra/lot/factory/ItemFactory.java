package br.com.ilegra.lot.factory;

import java.math.BigDecimal;

import br.com.ilegra.lot.entity.EntityClass;
import br.com.ilegra.lot.entity.Item;

public class ItemFactory implements EntityFactory {

	@Override
	public EntityClass create(String register) {
		String[] splitedRegistries = register.split("-");

		return new Item(new Integer(splitedRegistries[0]), new Integer(splitedRegistries[1]),
				new BigDecimal(splitedRegistries[2]));

	}
}