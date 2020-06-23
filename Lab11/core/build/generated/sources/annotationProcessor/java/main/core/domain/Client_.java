package core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Client, String> name;
	public static volatile ListAttribute<Client, Rent> rentals;
	public static volatile SingularAttribute<Client, Integer> age;

	public static final String NAME = "name";
	public static final String RENTALS = "rentals";
	public static final String AGE = "age";

}

