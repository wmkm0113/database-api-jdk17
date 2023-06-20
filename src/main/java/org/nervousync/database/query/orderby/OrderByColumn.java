/*
 * Copyright © 2003 Nervousync® Studio, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * Nervousync Studio, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Nervousync Studio.
 */
package org.nervousync.database.query.orderby;

import org.nervousync.commons.core.Globals;

/**
 * The type Order by column.
 *
 * @param entityClass The Alias name.
 * @param identifyKey The Identify key.
 * @param orderByType The Order by type.
 * @author Steven Wee	<a href="mailto:wmkm0113@Hotmail.com">wmkm0113@Hotmail.com</a>
 * @version $Revision : 1.0 $ $Date: 9/14/2020 5:15 PM $
 */
public record OrderByColumn(OrderByType orderByType,
							Class<?> entityClass, String identifyKey) {

	/**
	 * Instantiates a new Order by column.
	 *
	 * @param orderByType the order by type
	 * @param entityClass the entity class
	 * @param identifyKey the identify key
	 */
	public OrderByColumn {
	}

	/**
	 * Gets identify key.
	 *
	 * @return the identify key
	 */
	@Override
	public String identifyKey() {
		return identifyKey;
	}

	/**
	 * Gets entity class.
	 *
	 * @return the entity class
	 */
	@Override
	public Class<?> entityClass() {
		return entityClass;
	}

	/**
	 * Gets order by type.
	 *
	 * @return the orderByType
	 */
	@Override
	public OrderByType orderByType() {
		return orderByType;
	}

	public String cacheKey() {
		return this.entityClass.getName() + Globals.EXTENSION_SEPARATOR + this.identifyKey;
	}

	/**
	 * The enum Order by type.
	 */
	public enum OrderByType {
		/**
		 * Asc order by type.
		 */
		ASC,
		/**
		 * Desc order by type.
		 */
		DESC
	}
}
