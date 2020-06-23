package core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ extends core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Movie, Integer> year;
	public static volatile SingularAttribute<Movie, String> genre;
	public static volatile ListAttribute<Movie, Rent> rentals;
	public static volatile SingularAttribute<Movie, String> title;

	public static final String YEAR = "year";
	public static final String GENRE = "genre";
	public static final String RENTALS = "rentals";
	public static final String TITLE = "title";

}

