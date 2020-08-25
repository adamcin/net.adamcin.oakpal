/*
 * Copyright 2020 Mark Adamcin
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

package net.adamcin.oakpal.api;

import org.apache.jackrabbit.vault.packaging.PackageId;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Locates a jcr path that should be treated as an installable provider of repoinit scripts.
 */
public final class RepoInitScriptsInstallable implements SlingInstallable<Iterable<String>> {
    private final @NotNull PackageId parentId;
    private final @NotNull String jcrPath;
    private final @NotNull List<String> scripts;

    public RepoInitScriptsInstallable(final @NotNull PackageId parentId,
                                      final @NotNull String jcrPath,
                                      final @NotNull List<String> scripts) {
        this.parentId = parentId;
        this.jcrPath = jcrPath;
        this.scripts = scripts;
    }

    @Override
    public @NotNull PackageId getParentId() {
        return parentId;
    }

    @Override
    public @NotNull String getJcrPath() {
        return jcrPath;
    }

    @NotNull
    public List<String> getScripts() {
        return scripts;
    }

}
