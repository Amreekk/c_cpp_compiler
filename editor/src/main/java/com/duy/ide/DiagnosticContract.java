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

package com.duy.ide;

import android.support.annotation.MainThread;

import com.duy.ide.suggestion.ISuggestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duy on 28-Apr-18.
 */

public class DiagnosticContract {
    public interface View {
        void showDiagnostic(List<Diagnostic> diagnostics);

        void showLog(CharSequence log);

        void remove(Diagnostic diagnostic);

        void add(Diagnostic diagnostic);

        void clear();

        void setPresenter(Presenter presenter);

    }

    public interface Presenter {
        @MainThread
        void onDiagnosticClick(android.view.View view, Diagnostic diagnostic);

        @MainThread
        void onSuggestionClick(Diagnostic diagnostic, ISuggestion suggestion);

        @MainThread
        void expandView();

        @MainThread
        void hideView();

        @MainThread
        void setDiagnostics(ArrayList<Diagnostic> diagnostics);

        @MainThread
        void log(String string);

        @MainThread
        void clear();
    }
}