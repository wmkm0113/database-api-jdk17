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

package org.nervousync.database.annotations.query;

import java.lang.annotation.*;

/**
 * <h2 class="en-US">The annotation of query result data</h2>
 * <h2 class="zh-CN">查询结果数据的注解</h2>
 *
 * @author Steven Wee	<a href="mailto:wmkm0113@gmail.com">wmkm0113@gmail.com</a>
 * @version $Revision: 1.0.0 $ $Date: Nov 15, 2023 15:30:22 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ResultData {

    /**
     * @return <span class="en-US">The entity class to which the data column belongs</span>
     * <span class="zh-CN">数据列所属的实体类</span>
     */
    Class<?> entity();

    /**
     * @return <span class="en-US">Identify key of the data column</span>
     * <span class="zh-CN">数据列识别代码</span>
     */
    String identifyKey();

    /**
     * @return <span class="en-US">Whether to remove duplicates</span>
     * <span class="zh-CN">是否去重</span>
     */
    boolean distinct() default false;
}
