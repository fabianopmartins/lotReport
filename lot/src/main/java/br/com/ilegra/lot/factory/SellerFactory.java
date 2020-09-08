package br.com.ilegra.lot.factory;

import java.math.BigDecimal;

import br.com.ilegra.lot.entity.EntityClass;
import br.com.ilegra.lot.entity.Seller;

public class SellerFactory implements EntityFactory {

	@Override
	public EntityClass create(String register) {
		String[] splitedRegister = register.split("ç");

		return new Seller(splitedRegister[1], splitedRegister[2], new BigDecimal(splitedRegister[3]));
	}
}