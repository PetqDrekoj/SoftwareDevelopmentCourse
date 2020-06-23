package core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rent.class)
public abstract class Rent_ extends core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Rent, Movie> movie;
	public static volatile SingularAttribute<Rent, Client> client;

	public static final String MOVIE = "movie";
	public static final String CLIENT = "client";

}

