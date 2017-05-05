/*
 * Copyright 2008-2010 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package com.sun.btrace.samples;

import com.sun.btrace.annotations.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sun.btrace.BTraceUtils.*;

/**
 * <p>
 * 本示例收集了由追踪的程序创建的JComponets的历史记录。
 * 但是，历史记录仅在客户端触发事件时被打印。
 * </p>
 * This sample collects history of javax.swing.JComponets created by traced app.
 * But, the history is printed only on event (from client).
 */
@BTrace
public class HistogramOnEvent {

    private static Map<String, AtomicInteger> history = newHashMap();

    @OnMethod(
            clazz = "javax.swing.JComponent",
            method = "<init>"
    )
    public static void onNewObject(@Self Object obj) {
        String className = name(classOf(obj));
        AtomicInteger classNameCounter = get(history, className);
        if (classNameCounter == null) {
            classNameCounter = newAtomicInteger(1);
            put(history, className, classNameCounter);
        } else {
            incrementAndGet(classNameCounter);
        }
    }

    @OnEvent
    public static void print() {
//        if (size(history) != 0) {
        if (!isEmpty(history)) {
            printNumberMap("Component Histogram", history);
        }
    }

}
