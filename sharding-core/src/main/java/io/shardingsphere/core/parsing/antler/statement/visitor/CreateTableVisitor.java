/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antler.statement.visitor;

import io.shardingsphere.core.parsing.antler.phrase.visitor.ColumnDefinitionVisitor;
import io.shardingsphere.core.parsing.antler.phrase.visitor.CreatePrimaryKeyVisitor;
import io.shardingsphere.core.parsing.antler.phrase.visitor.IndexesNameVisitor;
import io.shardingsphere.core.parsing.antler.phrase.visitor.TableNamesVisitor;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.sql.ddl.create.table.CreateTableStatement;

public class CreateTableVisitor extends AbstractStatementVisitor {
    public CreateTableVisitor() {
        addVisitor(new TableNamesVisitor());
        addVisitor(new ColumnDefinitionVisitor());
        addVisitor(new IndexesNameVisitor());
        addVisitor(new CreatePrimaryKeyVisitor());
    }

    /** Create create table statement.
     * @return empty sql statment
     */
    @Override
    protected SQLStatement newStatement() {
        return new CreateTableStatement();
    }
}