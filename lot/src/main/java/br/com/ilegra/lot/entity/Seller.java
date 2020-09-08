package br.com.ilegra.lot.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seller extends EntityClass {

	private String cpf;

	private String name;

	private BigDecimal salary;
}