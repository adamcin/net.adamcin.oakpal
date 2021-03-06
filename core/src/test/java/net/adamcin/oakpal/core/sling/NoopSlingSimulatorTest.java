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

package net.adamcin.oakpal.core.sling;

import net.adamcin.oakpal.api.EmbeddedPackageInstallable;
import org.apache.jackrabbit.vault.packaging.PackageId;
import org.junit.Test;

import javax.jcr.Node;

import static net.adamcin.oakpal.core.sling.NoopSlingSimulator.instance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class NoopSlingSimulatorTest {

    @Test
    public void testInstance() {
        assertNotNull("expect non-null instance", instance());
    }

    @Test
    public void testDequeueInstallable() {
        assertNull("never dequeue an installable", instance().dequeueInstallable());
    }

    @Test(expected = IllegalStateException.class)
    public void testOpenEmbeddedPackage() throws Exception {
        instance().open(new EmbeddedPackageInstallable(PackageId.fromString("test:pack:1"), "/test/path",
                PackageId.fromString("test:pack:2"))).tryGet();
    }

    @Test
    public void testSetPackageManager() {
        instance().setPackageManager(null);
    }

    @Test
    public void testSetErrorListener() {
        instance().setErrorListener(null);
    }

    @Test
    public void testSetSession() {
        instance().setSession(null);
    }

    @Test
    public void testPrepareInstallableNode() {
        assertNull("always null",
                instance().addInstallableNode(PackageId.fromString("test:pack:1"), mock(Node.class)));
    }
}