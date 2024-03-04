/*
 * Licensed to the Nervousync Studio (NSYC) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nervousync.database.query.filter;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.nervousync.beans.transfer.basic.ClassAdapter;
import org.nervousync.database.query.core.SortedItem;
import org.nervousync.utils.ObjectUtils;

import java.io.Serial;

/**
 * <h2 class="en-US">Query group by column define</h2>
 * <h2 class="zh-CN">查询分组列信息定义</h2>
 *
 * @author Steven Wee	<a href="mailto:wmkm0113@gmail.com">wmkm0113@gmail.com</a>
 * @version $Revision: 1.0.0 $ $Date: Oct 7， 2020 13:36：28 $
 */
@XmlType(name = "group_by", namespace = "https://nervousync.org/schemas/query")
@XmlRootElement(name = "group_by", namespace = "https://nervousync.org/schemas/query")
@XmlAccessorType(XmlAccessType.NONE)
public final class GroupBy extends SortedItem {
	/**
	 * <span class="en-US">Serial version UID</span>
	 * <span class="zh-CN">序列化UID</span>
	 */
	@Serial
	private static final long serialVersionUID = -7703148998062830489L;
	/**
	 * <span class="en-US">Entity class</span>
	 * <span class="zh-CN">实体类</span>
	 */
	@XmlElement(name = "entity_class")
	@XmlJavaTypeAdapter(ClassAdapter.class)
	private Class<?> entityClass;
	/**
	 * <span class="en-US">Identify key</span>
	 * <span class="zh-CN">识别代码</span>
	 */
	@XmlElement(name = "identify_key")
	private String identifyKey;

	/**
	 * <h4 class="en-US">Constructor method for query group by column define</h4>
	 * <h4 class="zh-CN">查询分组列信息定义的构造方法</h4>
	 */
	public GroupBy() {
	}

	/**
	 * <h4 class="en-US">Constructor method for query group by column define</h4>
	 * <h4 class="zh-CN">查询分组列信息定义的构造方法</h4>
	 *
	 * @param entityClass <span class="en-US">Entity class</span>
	 *                    <span class="zh-CN">实体类</span>
	 * @param identifyKey <span class="en-US">Identify key</span>
	 *                    <span class="zh-CN">识别代码</span>
	 * @param sortCode    <span class="en-US">Sort code</span>
	 *                    <span class="zh-CN">排序代码</span>
	 */
	public GroupBy(final Class<?> entityClass, final String identifyKey, final int sortCode) {
		this();
		this.entityClass = entityClass;
		this.identifyKey = identifyKey;
		super.setSortCode(sortCode);
	}

	/**
	 * <h4 class="en-US">Getter method for entity class</h4>
	 * <h4 class="zh-CN">实体类的Getter方法</h4>
	 *
	 * @return <span class="en-US">Entity class</span>
	 * <span class="zh-CN">实体类</span>
	 */
	public Class<?> getEntityClass() {
		return entityClass;
	}

	/**
	 * <h4 class="en-US">Setter method for entity class</h4>
	 * <h4 class="zh-CN">实体类的Setter方法</h4>
	 *
	 * @param entityClass <span class="en-US">Entity class</span>
	 *                    <span class="zh-CN">实体类</span>
	 */
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * <h4 class="en-US">Getter method for identify key</h4>
	 * <h4 class="zh-CN">识别代码的Getter方法</h4>
	 *
	 * @return <span class="en-US">Identify key</span>
	 * <span class="zh-CN">识别代码</span>
	 */
	public String getIdentifyKey() {
		return identifyKey;
	}

	/**
	 * <h4 class="en-US">Setter method for identify key</h4>
	 * <h4 class="zh-CN">识别代码的Setter方法</h4>
	 *
	 * @param identifyKey <span class="en-US">Identify key</span>
	 *                    <span class="zh-CN">识别代码</span>
	 */
	public void setIdentifyKey(String identifyKey) {
		this.identifyKey = identifyKey;
	}

	/**
	 * <h4 class="en-US">Checks whether the given parameter value matches the current information</h4>
	 * <h4 class="zh-CN">检查给定的参数值是否与当前信息匹配</h4>
	 *
	 * @param entityClass <span class="en-US">Entity class</span>
	 *                    <span class="zh-CN">实体类</span>
	 * @param identifyKey <span class="en-US">Identify key</span>
	 *                    <span class="zh-CN">识别代码</span>
	 * @return <span class="en-US">Match result</span>
	 * <span class="zh-CN">匹配结果</span>
	 */
	public boolean match(final Class<?> entityClass, final String identifyKey) {
		return ObjectUtils.nullSafeEquals(entityClass, this.entityClass)
				&& ObjectUtils.nullSafeEquals(identifyKey, this.identifyKey);
	}
}
