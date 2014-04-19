package com.myhome;

import com.myhome.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "movementsensorendpoint", namespace = @ApiNamespace(ownerDomain = "myhome.com", ownerName = "myhome.com", packagePath = ""))
public class MovementSensorEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listMovementSensor")
	public CollectionResponse<MovementSensor> listMovementSensor(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<MovementSensor> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from MovementSensor as MovementSensor");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<MovementSensor>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (MovementSensor obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<MovementSensor> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getMovementSensor")
	public MovementSensor getMovementSensor(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		MovementSensor movementsensor = null;
		try {
			movementsensor = mgr.find(MovementSensor.class, id);
		} finally {
			mgr.close();
		}
		return movementsensor;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param movementsensor the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertMovementSensor")
	public MovementSensor insertMovementSensor(MovementSensor movementsensor) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsMovementSensor(movementsensor)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(movementsensor);
		} finally {
			mgr.close();
		}
		return movementsensor;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param movementsensor the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateMovementSensor")
	public MovementSensor updateMovementSensor(MovementSensor movementsensor) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsMovementSensor(movementsensor)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(movementsensor);
		} finally {
			mgr.close();
		}
		return movementsensor;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeMovementSensor")
	public void removeMovementSensor(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			MovementSensor movementsensor = mgr.find(MovementSensor.class, id);
			mgr.remove(movementsensor);
		} finally {
			mgr.close();
		}
	}

	private boolean containsMovementSensor(MovementSensor movementsensor) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			MovementSensor item = mgr.find(MovementSensor.class,
					movementsensor.getIDName());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
