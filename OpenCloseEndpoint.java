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

@Api(name = "opencloseendpoint", namespace = @ApiNamespace(ownerDomain = "myhome.com", ownerName = "myhome.com", packagePath = ""))
public class OpenCloseEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listOpenClose")
	public CollectionResponse<OpenClose> listOpenClose(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<OpenClose> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from OpenClose as OpenClose");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<OpenClose>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (OpenClose obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<OpenClose> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getOpenClose")
	public OpenClose getOpenClose(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		OpenClose openclose = null;
		try {
			openclose = mgr.find(OpenClose.class, id);
		} finally {
			mgr.close();
		}
		return openclose;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param openclose the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertOpenClose")
	public OpenClose insertOpenClose(OpenClose openclose) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsOpenClose(openclose)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(openclose);
		} finally {
			mgr.close();
		}
		return openclose;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param openclose the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateOpenClose")
	public OpenClose updateOpenClose(OpenClose openclose) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsOpenClose(openclose)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(openclose);
		} finally {
			mgr.close();
		}
		return openclose;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeOpenClose")
	public void removeOpenClose(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			OpenClose openclose = mgr.find(OpenClose.class, id);
			mgr.remove(openclose);
		} finally {
			mgr.close();
		}
	}

	private boolean containsOpenClose(OpenClose openclose) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			OpenClose item = mgr.find(OpenClose.class, openclose.getIDName());
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
