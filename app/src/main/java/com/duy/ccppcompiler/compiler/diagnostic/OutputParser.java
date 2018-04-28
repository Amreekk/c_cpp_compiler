/*
 * Copyright 2018 Mr Duy
 *
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
 */

package com.duy.ccppcompiler.compiler.diagnostic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://gcc.gnu.org/onlinedocs/gcc-3.3.5/gnat_ug_unx/Output-and-Error-Message-Control.html
 * <p>
 * https://gcc.gnu.org/onlinedocs/gcc-7.2.0/gcc/Diagnostic-Message-Formatting-Options.html
 * <p>
 * Created by Duy on 28-Apr-18.
 */

public class OutputParser {
    public static final Pattern PATTERN = Pattern.compile(
            "(.*):" + /*File name*/
                    "([0-9]+):" + /*Line*/
                    "([0-9]+):" + /*Col*/
                    "(\\s+.*:\\s+)" + /*Type*/
                    "(.*)" /*Message*/
            , Pattern.CASE_INSENSITIVE);

    private DiagnosticsCollector diagnosticsCollector;

    public OutputParser(DiagnosticsCollector diagnosticsCollector) {
        this.diagnosticsCollector = diagnosticsCollector;
    }

    @SuppressWarnings("unchecked")
    public void parse(String inputData) {
        Matcher matcher = PATTERN.matcher(inputData);
        while (matcher.find()) {
            try {
                String file = matcher.group(1);
                int line = Integer.parseInt(matcher.group(2));
                int col = Integer.parseInt(matcher.group(3));
                Kind type = DiagnosticFactory.createType(matcher.group(4));
                String message = matcher.group(5);

                Diagnostic diagnostic = DiagnosticFactory.create(type, file, line, col, message);
                diagnosticsCollector.report(diagnostic);
            } catch (Exception e) {
                //should not happened
                e.printStackTrace();
            }
        }
    }
}
