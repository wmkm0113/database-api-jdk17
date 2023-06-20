/*
 * Copyright © 2003 Nervousync® Studio, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * Nervousync Studio, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Nervousync Studio.
 */
package org.nervousync.database.query.group;

/**
 * The type Group by column.
 *
 * @param entityClass The Alias name.
 * @param identifyKey The Identify key.
 * @author Steven Wee	<a href="mailto:wmkm0113@Hotmail.com">wmkm0113@Hotmail.com</a>
 * @version $Revision : 1.0 $ $Date: 10/7/2020 1:36 PM $
 */
public record GroupByColumn(Class<?> entityClass, String identifyKey) {

	/**
	 * Instantiates a new Group by column.
	 *
	 * @param entityClass the entity class
	 * @param identifyKey the identify key
	 */
	public GroupByColumn {
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
	 * Gets the value of columnName
	 *
	 * @return the value of columnName
	 */
	@Override
	public String identifyKey() {
		return identifyKey;
	}
}
