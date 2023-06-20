/*
 * Copyright © 2003 Nervousync® Studio, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * Nervousync Studio, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Nervousync Studio.
 */
package org.nervousync.database.query.condition;

import org.nervousync.database.query.operate.ConditionCode;
import org.nervousync.database.query.operate.ConnectionCode;
import org.nervousync.utils.ConvertUtils;
import org.nervousync.utils.SecurityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Query condition.
 *
 * @param connectionCode Condition connection code
 * @param conditionCode  Condition operate code
 * @param identifyKey    Column identify key, maybe field name or column name
 * @param matchCondition Condition match condition object
 * @author Steven Wee	<a href="mailto:wmkm0113@Hotmail.com">wmkm0113@Hotmail.com</a>
 * @version $Revision : 1.0 $ $Date: 10/28/2020 17:35 $
 */
public record QueryCondition(ConnectionCode connectionCode, ConditionCode conditionCode, String identifyKey,
							 MatchCondition matchCondition) {

	/**
	 * Instantiates a new Query condition.
	 *
	 * @param connectionCode the connection code
	 * @param conditionCode  the condition code
	 * @param identifyKey    the identify key
	 * @param matchCondition the match condition
	 */
	public QueryCondition {
	}

	public boolean match(ConnectionCode connectionCode, ConditionCode conditionCode,
						 String identifyKey, MatchCondition matchCondition) {
		return Objects.equals(this.connectionCode, connectionCode)
				&& Objects.equals(this.conditionCode, conditionCode)
				&& Objects.equals(this.identifyKey, identifyKey)
				&& Objects.equals(this.matchCondition, matchCondition);
	}

	/**
	 * Gets connection code.
	 *
	 * @return the connection code
	 */
	@Override
	public ConnectionCode connectionCode() {
		return connectionCode;
	}

	/**
	 * Gets condition code.
	 *
	 * @return the condition code
	 */
	@Override
	public ConditionCode conditionCode() {
		return conditionCode;
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
	 * Gets match condition.
	 *
	 * @return the match condition
	 */
	@Override
	public MatchCondition matchCondition() {
		return matchCondition;
	}

	/**
	 * Cache key string.
	 *
	 * @return the string
	 */
	public String cacheKey() {
		Map<String, Object> cacheMap = new HashMap<>();

		cacheMap.put("ConnectionCode", this.connectionCode.toString());
		cacheMap.put("ConditionCode", this.conditionCode.toString());
		cacheMap.put("IdentifyKey", this.identifyKey);
		cacheMap.put("MatchCondition", this.matchCondition.cacheKey());

		return ConvertUtils.byteToHex(SecurityUtils.SHA256(cacheMap));
	}

	/**
	 * The enum Condition type.
	 */
	public enum ConditionType {
		/**
		 * Constant condition type.
		 */
		CONSTANT,
		/**
		 * Group condition type
		 */
		GROUP,
		/**
		 * Dynamic condition type.
		 */
		DYNAMIC
	}
}
