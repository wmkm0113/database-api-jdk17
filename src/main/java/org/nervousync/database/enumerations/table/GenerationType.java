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

package org.nervousync.database.enumerations.table;

import org.nervousync.database.annotations.table.GeneratedValue;

/**
 * Defines the types of primary key generation strategies.
 *
 * @see GeneratedValue
 *
 * @since 1.0
 */
public enum GenerationType {
    /**
     * <span class="en-US">Generate automatically</span>
     * <span class="zh-CN">自动生成</span>
     */
    GENERATE,
    /**
     * <span class="en-US">Sequence generator</span>
     * <span class="zh-CN">序列生成器</span>
     */
    SEQUENCE,
    /**
     * <span class="en-US">Assigned value</span>
     * <span class="zh-CN">手动填写</span>
     */
    ASSIGNED
}
