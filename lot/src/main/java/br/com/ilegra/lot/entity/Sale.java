package br.com.ilegra.lot.entity;

import java.util.List;

import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sale extends EntityClass {

	private Integer id;

	@OneToMany(mappedBy = "sale")
	@Cascade(CascadeType.ALL)
	private List<Item> items;

	private String seller;
}