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

@Api(name = "actionendpoint", namespace = @ApiNamespace(ownerDomain = "myhome.com", ownerName = "myhome.com", packagePath = ""))
public class ActionEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listAction")
	public CollectionResponse<Action> listAction(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Action> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Action as Action");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Action>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Action obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Action> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getAction")
	public Action getAction(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		Action action = null;
		try {
			action = mgr.find(Action.class, id);
		} finally {
			mgr.close();
		}
		return action;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param action the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertAction")
	public Action insertAction(Action action) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsAction(action)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(action);
		} finally {
			mgr.close();
		}
		return action;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param action the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateAction")
	public Action updateAction(Action action) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsAction(action)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(action);
		} finally {
			mgr.close();
		}
		return action;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeAction")
	public void removeAction(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			Action action = mgr.find(Action.class, id);
			mgr.remove(action);
		} finally {
			mgr.close();
		}
	}

	private boolean containsAction(Action action) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Action item = mgr.find(Action.class, action.getID());
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
