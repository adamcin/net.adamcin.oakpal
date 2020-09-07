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

import org.osgi.annotation.versioning.ProviderType;

/**
 * Some sling installable resources must be opened to be read, perhaps throwing an exception, and then maybe closed.
 *
 * @param <ResourceType> the concrete resource type that can be opened from the installable type
 * @since 2.2.0
 */
@ProviderType
public interface SlingOpenable<ResourceType> extends SlingInstallable {
}
