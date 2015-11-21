package pl.vgtworld.resourceobserver.core;

import javax.persistence.Query;
import java.util.List;

public final class PersistenceUtil {

	private PersistenceUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getResultList(Query query) {
		return query.getResultList();
	}

}
