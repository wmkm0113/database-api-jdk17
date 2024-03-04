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
package org.nervousync.database.beans.configs.column;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.xml.bind.annotation.*;
import org.nervousync.beans.core.BeanObject;
import org.nervousync.commons.Globals;
import org.nervousync.database.commons.DatabaseUtils;

import java.io.Serial;
import java.lang.reflect.Field;
import java.sql.Types;
import java.util.Date;

/**
 * <h2 class="en-US">Column information</h2>
 * <h2 class="zh-CN">列基本信息</h2>
 *
 * @author Steven Wee	<a href="mailto:wmkm0113@gmail.com">wmkm0113@gmail.com</a>
 * @version $Revision: 1.0.0 $ $Date: Jun 27, 2018 23:02:27 $
 */
@XmlType(name = "column_info")
@XmlRootElement(name = "column_info")
@XmlAccessorType(XmlAccessType.NONE)
public final class ColumnInfo extends BeanObject {
    /**
     * <span class="en-US">Serial version UID</span>
     * <span class="zh-CN">序列化UID</span>
     */
	@Serial
    private static final long serialVersionUID = -2535643284257171857L;

    /**
     * <span class="en-US">Column name</span>
     * <span class="zh-CN">列名</span>
     */
    @XmlElement(name = "column_name")
    private String columnName;
    /**
     * <span class="en-US">JDBC data type code</span>
     * <span class="zh-CN">JDBC数据类型代码</span>
     */
    @XmlElement(name = "jdbc_type")
    private int jdbcType;
    /**
     * <span class="en-US">Column is nullable</span>
     * <span class="zh-CN">列允许为空值</span>
     */
    @XmlElement(name = "nullable")
    private boolean nullable;
    /**
     * <span class="en-US">Column length</span>
     * <span class="zh-CN">列长度</span>
     */
    @XmlElement
    private int length;
    /**
     * <span class="en-US">Column precision</span>
     * <span class="zh-CN">列精度</span>
     */
    @XmlElement
    private int precision;
    /**
     * <span class="en-US">Column scale</span>
     * <span class="zh-CN">列小数位数</span>
     */
    @XmlElement
    private int scale;
    /**
     * <span class="en-US">Column default value</span>
     * <span class="zh-CN">列默认值</span>
     */
    @XmlElement(name = "default_value")
    private Object defaultValue;

    /**
     * <h4 class="en-US">Constructor method for column information</h4>
     * <h4 class="zh-CN">列基本信息的构造方法</h4>
     */
    public ColumnInfo() {
    }

    /**
     * <h4 class="en-US">Private constructor method for column information</h4>
     * <h4 class="zh-CN">列基本信息的私有构造方法</h4>
     *
     * @param columnName   <span class="en-US">Column name</span>
     *                     <span class="zh-CN">列名</span>
     * @param jdbcType     <span class="en-US">JDBC data type code</span>
     *                     <span class="zh-CN">JDBC数据类型代码</span>
     * @param nullable     <span class="en-US">Column is nullable</span>
     *                     <span class="zh-CN">列允许为空值</span>
     * @param length       <span class="en-US">Column length</span>
     *                     <span class="zh-CN">列长度</span>
     * @param precision    <span class="en-US">Column precision</span>
     *                     <span class="zh-CN">列精度</span>
     * @param scale        <span class="en-US">Column scale</span>
     *                     <span class="zh-CN">列小数位数</span>
     * @param defaultValue <span class="en-US">Column default value</span>
     *                     <span class="zh-CN">列默认值</span>
     */
    private ColumnInfo(final String columnName, final int jdbcType, final boolean nullable,
                       final int length, final int precision, final int scale, final Object defaultValue) {
        this();
        this.columnName = columnName;
        this.jdbcType = jdbcType;
        this.nullable = nullable;
        this.length = length;
        this.precision = precision;
        this.scale = scale;
        this.defaultValue = defaultValue;
    }

    /**
     * <h4 class="en-US">Generate column information instance by given field instance and default value</h4>
     * <h4 class="zh-CN">根据给定的反射获得的属性实例对象和默认值生成列基本信息实例对象</h4>
     *
     * @param field        <span class="en-US">Field instance</span>
     *                     <span class="zh-CN">反射获得的属性实例对象</span>
     * @param defaultValue <span class="en-US">Default value of current field</span>
     *                     <span class="zh-CN">当前属性的默认值</span>
     * @return <span class="en-US">Generated column information instance</span>
     * <span class="zh-CN">生成的列基本信息实例对象</span>
     */
    public static ColumnInfo newInstance(final Field field, final Object defaultValue) {
        if (field == null) {
            return null;
        }
        Column column = field.getAnnotation(Column.class);
        String columnName = column.name().isEmpty() ? field.getName() : column.name();
        int precision = column.precision(), scale = column.scale(), jdbcType, length;
        Class<?> fieldType = field.getType();
        if (Date.class.equals(fieldType) && field.isAnnotationPresent(Temporal.class)) {
	        jdbcType = switch (field.getAnnotation(Temporal.class).value()) {
		        case DATE -> Types.DATE;
		        case TIME -> Types.TIME;
		        default -> Types.TIMESTAMP;
	        };
        } else if (field.isAnnotationPresent(Lob.class)) {
            if (String.class.equals(fieldType) || char[].class.equals(fieldType)
                    || Character[].class.equals(fieldType)) {
                jdbcType = Types.CLOB;
            } else {
                jdbcType = Types.BLOB;
            }
        } else {
            jdbcType = DatabaseUtils.jdbcType(fieldType);
        }
	    length = switch (jdbcType) {
		    case Types.CHAR, Types.NCHAR, Types.VARCHAR, Types.NVARCHAR -> column.length();
		    default -> Globals.DEFAULT_VALUE_INT;
	    };
        return new ColumnInfo(columnName, jdbcType,
                (field.isAnnotationPresent(Id.class) ? Boolean.FALSE : column.nullable()),
                length, precision, scale, defaultValue);
    }

    /**
     * <h4 class="en-US">Getter method for column name</h4>
     * <h4 class="zh-CN">列名的Getter方法</h4>
     *
     * @return <span class="en-US">Column name</span>
     * <span class="zh-CN">列名</span>
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * <h4 class="en-US">Getter method for JDBC data type code</h4>
     * <h4 class="zh-CN">JDBC数据类型代码的Getter方法</h4>
     *
     * @return <span class="en-US">JDBC data type code</span>
     * <span class="zh-CN">JDBC数据类型代码</span>
     */
    public int getJdbcType() {
        return jdbcType;
    }

    /**
     * <h4 class="en-US">Getter method for column is nullable</h4>
     * <h4 class="zh-CN">列允许为空值的Getter方法</h4>
     *
     * @return <span class="en-US">Column is nullable</span>
     * <span class="zh-CN">列允许为空值</span>
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * <h4 class="en-US">Getter method for column length</h4>
     * <h4 class="zh-CN">列长度的Getter方法</h4>
     *
     * @return <span class="en-US">Column length</span>
     * <span class="zh-CN">列长度</span>
     */
    public int getLength() {
        return length;
    }

    /**
     * <h4 class="en-US">Getter method for column precision</h4>
     * <h4 class="zh-CN">列精度的Getter方法</h4>
     *
     * @return <span class="en-US">Column precision</span>
     * <span class="zh-CN">列精度</span>
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * <h4 class="en-US">Getter method for column scale</h4>
     * <h4 class="zh-CN">列小数位数的Getter方法</h4>
     *
     * @return <span class="en-US">Column scale</span>
     * <span class="zh-CN">列小数位数</span>
     */
    public int getScale() {
        return scale;
    }

    /**
     * <h4 class="en-US">Getter method for column default value</h4>
     * <h4 class="zh-CN">列默认值的Getter方法</h4>
     *
     * @return <span class="en-US">Column default value</span>
     * <span class="zh-CN">列默认值</span>
     */
    public Object getDefaultValue() {
        return defaultValue;
    }
}
