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

import org.osgi.annotation.versioning.ConsumerType;

/**
 * Defines listener notifications for beginning and end of scan common to {@link ProgressCheck} and
 * {@code ErrorListener}.
 */
@ConsumerType
public interface ScanListener {

    /**
     * Called once at the beginning of the scan to notify for re-initialization of listener state.
     */
    default void startedScan() {

    }

    /**
     * Called once at the end of the scan to notify for cleanup of resources.
     */
    default void finishedScan() {

    }
}
