package br.com.ilegra.lot.factory;

import br.com.ilegra.lot.entity.Customer;
import br.com.ilegra.lot.entity.EntityClass;

public class CustomerFactory implements EntityFactory {

	@Override
	public EntityClass create(String register) {
		String[] splitedRegistries = register.split("ç");
		
		return new Customer(splitedRegistries[1], splitedRegistries[2], splitedRegistries[3]);
	}
}