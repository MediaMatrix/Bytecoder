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
package de.mirkosertic.bytecoder.core;

public class BytecodeParserUtils {

    public static int integerFromByteArray(byte[] aData, int aOffset) {
        byte theByte1 = aData[aOffset++];
        byte thByte2 = aData[aOffset];
        return (theByte1 << 8) | thByte2;
    }

    public static long longFromByteArray(byte[] aData, int aOffset) {
        byte theByte1 = aData[aOffset++];
        byte theByte2 = aData[aOffset++];
        byte theByte3 = aData[aOffset++];
        byte theByte4 = aData[aOffset];

        return (theByte1 << 24) | (theByte2 << 16) | (theByte3 << 8) | theByte4;
    }

    public static float intToFloat(int aIntValue) {
        if (aIntValue ==  0x7f800000) {
            return Float.POSITIVE_INFINITY;
        }
        if (aIntValue == 0xff800000) {
            return Float.NEGATIVE_INFINITY;
        }

        int s = ((aIntValue >> 31) == 0) ? 1 : -1;
        int e = ((aIntValue >> 23) & 0xff);
        int m = (e == 0) ?
                (aIntValue & 0x7fffff) << 1 :
                (aIntValue & 0x7fffff) | 0x800000;

        return (float) (s * m * Math.pow(2, e-150));
    }
}