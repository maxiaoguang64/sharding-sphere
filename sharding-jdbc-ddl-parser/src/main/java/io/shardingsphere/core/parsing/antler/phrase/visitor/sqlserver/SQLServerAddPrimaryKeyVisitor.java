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

package io.shardingsphere.core.parsing.antler.phrase.visitor.sqlserver;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import io.shardingsphere.core.parsing.antler.phrase.visitor.PhraseVisitor;
import io.shardingsphere.core.parsing.antler.sql.ddl.AlterTableStatement;
import io.shardingsphere.core.parsing.antler.sql.ddl.ColumnDefinition;
import io.shardingsphere.core.parsing.antler.utils.TreeUtils;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;

public class SQLServerAddPrimaryKeyVisitor implements PhraseVisitor {

    /** Visit add primary key node.
     * @param ancestorNode ancestor node of ast
     * @param statement sql statement
     */
    @Override
    public void visit(final ParserRuleContext ancestorNode, final SQLStatement statement) {
        AlterTableStatement alterStatement = (AlterTableStatement) statement;

        ParserRuleContext addColumnCtx = TreeUtils.getFirstChildByRuleName(ancestorNode, "addColumn");

        if (null == addColumnCtx) {
            return;
        }

        ParserRuleContext tableConstraintCtx = TreeUtils.getFirstChildByRuleName(addColumnCtx, "tableConstraint");

        if (null == tableConstraintCtx) {
            return;
        }

        ParserRuleContext primaryKeyCtx = TreeUtils.getFirstChildByRuleName(tableConstraintCtx,
                "primaryKey");
        if (null == primaryKeyCtx) {
            return;
        }

        List<ParserRuleContext> columnNameCtxs = TreeUtils.getAllDescendantByRuleName(tableConstraintCtx, "columnName");
        for (ParseTree columnNameCtx : columnNameCtxs) {
            alterStatement.getUpdateColumns().put(columnNameCtx.getText(),
                    new ColumnDefinition(columnNameCtx.getText(), null, null, true));
        }
    }
}