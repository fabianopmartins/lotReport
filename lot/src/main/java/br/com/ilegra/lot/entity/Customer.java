package br.com.ilegra.lot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends EntityClass {

	private String cnpj;

	private String name;

	private String businessArea;
}