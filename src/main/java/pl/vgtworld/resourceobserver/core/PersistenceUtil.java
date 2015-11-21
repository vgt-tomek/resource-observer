package pl.vgtworld.resourceobserver.core;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public final class PersistenceUtil {

	private PersistenceUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getResultList(Query query) {
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static <E> E getSingleResult(Query query) {
		query.setMaxResults(2);
		List<E> results = query.getResultList();
		if (results.size() == 2) {
			throw new NonUniqueResultException();
		}
		if (results.size() == 1) {
			return results.get(0);
		}
		return null;
	}

}