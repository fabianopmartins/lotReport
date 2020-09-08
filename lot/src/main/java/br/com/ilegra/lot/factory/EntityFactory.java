package br.com.ilegra.lot.factory;

import br.com.ilegra.lot.entity.EntityClass;

public interface EntityFactory {

	abstract EntityClass create(String register);
}