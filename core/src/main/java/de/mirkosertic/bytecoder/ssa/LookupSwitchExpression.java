/*
 * Copyright 2017 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.ssa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LookupSwitchExpression extends Expression implements ExpressionListContainer {

    private final ExpressionList defaultExpressions;
    private final Map<Long, ExpressionList> pairs;

    public LookupSwitchExpression(final Value aValue, final ExpressionList aDefaultExpressions,
            final Map<Long, ExpressionList> aPairs) {
        defaultExpressions = aDefaultExpressions;
        pairs = aPairs;
        receivesDataFrom(aValue);
    }

    public ExpressionList getDefaultExpressions() {
        return defaultExpressions;
    }

    public Map<Long, ExpressionList> getPairs() {
        return pairs;
    }

    @Override
    public Set<ExpressionList> getExpressionLists() {
        final Set<ExpressionList> theResult = new HashSet<>();
        theResult.add(defaultExpressions);
        theResult.addAll(pairs.values());
        return theResult;
    }

    @Override
    public Expression deepCopy() {
        final Map<Long, ExpressionList> thePairs = new HashMap<>();
        for (final Map.Entry<Long, ExpressionList> theEntry : pairs.entrySet()) {
            thePairs.put(theEntry.getKey(), theEntry.getValue().deepCopy());
        }
        return new LookupSwitchExpression(incomingDataFlows().get(0), defaultExpressions.deepCopy(), thePairs);
    }
}