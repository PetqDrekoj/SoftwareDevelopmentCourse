package core.repository.dbRepository;

import core.domain.Movie;
import core.domain.validators.SQLRepoException;
import core.utils.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface MovieRepository
        extends CatalogRepository<Movie, Integer> {

    default Iterable<Movie> findAll(Sort sort) {
        ArrayList<Movie> result = new ArrayList<>((Collection<? extends Movie>) findAll());
        List<Sort> sortList = sort.getSortList();
        sortList.forEach((s) -> {
            List<String> fieldsList = s.getFields();
            fieldsList.forEach(p -> result.sort((c1, c2) -> {
                try {
                    Field f1 = c1.getClass().getDeclaredField(p);
                    f1.setAccessible(true);
                    String n1 = (String) f1.get(c1);
                    Field f2 = c2.getClass().getDeclaredField(p);
                    f2.setAccessible(true);
                    String n2 = (String) f2.get(c2);
                    if (s.getCurrentDirection())
                        return n1.compareTo(n2);
                    else
                        return n2.compareTo(n1);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new SQLRepoException(e.getMessage(), e);
                }
            }));
        });
        return result;
    }
}
