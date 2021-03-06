/*
 * Copyright 2019 Mark Adamcin
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

package net.adamcin.oakpal.core;

import org.junit.Test;

import javax.json.JsonValue;
import java.util.Collections;
import java.util.List;

import static net.adamcin.oakpal.api.JavaxJson.key;
import static net.adamcin.oakpal.api.JavaxJson.obj;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ForcedRootTest {

    @Test
    public void testGetPath() {
        final ForcedRoot root = new ForcedRoot();
        assertFalse("has no path when empty", root.hasPath());
        root.setPath("/testSet");
        assertTrue("has path", root.hasPath());
        assertEquals("getPath()", "/testSet",
                root.getPath());
        assertEquals("getPath()", "/testWith",
                root.withPath("/testWith").getPath());
    }

    @Test
    public void testGetPrimaryType() {
        final ForcedRoot root = new ForcedRoot();
        root.setPrimaryType("primaryTypeSet");
        assertEquals("getPrimaryType()", "primaryTypeSet",
                root.getPrimaryType());
        assertEquals("getPrimaryType()", "primaryTypeWith",
                root.withPrimaryType("primaryTypeWith").getPrimaryType());
    }

    @Test
    public void testGetMixinTypes() {
        final ForcedRoot root = new ForcedRoot();
        final List<String> expectedSet = Collections.singletonList("mixinTypeSet");
        final List<String> expectedWith = Collections.singletonList("mixinTypeWith");
        root.setMixinTypes(expectedSet);
        assertEquals("getMixinTypes()", expectedSet, root.getMixinTypes());
        assertEquals("getMixinTypes()", expectedWith,
                root.withMixinTypes("mixinTypeWith").getMixinTypes());

        root.setMixinTypes(null);
        assertEquals("getMixinTypes()", Collections.emptyList(),
                root.getMixinTypes());
        assertEquals("getMixinTypes()", Collections.emptyList(),
                root.withMixinTypes().getMixinTypes());
    }

    @Test
    public void testGetNamespacePrefixes() {
        final ForcedRoot emptyRoot = new ForcedRoot();
        assertArrayEquals("expect prefixes", new String[0],
                emptyRoot.getNamespacePrefixes());

        final ForcedRoot noPrefixRoot = new ForcedRoot()
                .withPath("path")
                .withPrimaryType("type")
                .withMixinTypes("mixinType");

        assertArrayEquals("expect prefixes", new String[0],
                noPrefixRoot.getNamespacePrefixes());

        final ForcedRoot root = new ForcedRoot()
                .withPath("foo:path")
                .withPrimaryType("bar:type")
                .withMixinTypes("foobar:type");

        assertArrayEquals("expect prefixes", new String[]{"foo", "bar", "foobar"},
                root.getNamespacePrefixes());
    }

    @Test
    public void testFromJson() {
        final ForcedRoot.JsonKeys keys = ForcedRoot.keys();
        final String path = "/correct/path";
        final String primaryType = "primaryType";
        final List<String> mixinTypes = Collections.singletonList("mixinType");
        final ForcedRoot rootEmpty = ForcedRoot.fromJson(obj().get());
        assertNull("empty root has null path", rootEmpty.getPath());
        assertNull("empty root has null primaryType", rootEmpty.getPrimaryType());
        assertTrue("empty root has empty mixinTypes", rootEmpty.getMixinTypes().isEmpty());

        final ForcedRoot rootPath = ForcedRoot.fromJson(
                obj().key(keys.path(), path).get());

        assertEquals("path root has correct path", path, rootPath.getPath());

        final ForcedRoot rootPrimaryType = ForcedRoot.fromJson(
                obj().key(keys.primaryType(), primaryType).get());

        assertEquals("primaryType root has correct primaryType",
                primaryType, rootPrimaryType.getPrimaryType());

        final ForcedRoot rootMixinTypes = ForcedRoot.fromJson(
                obj().key(keys.mixinTypes(), mixinTypes).get());

        assertEquals("mixinTypes root has correct mixinTypes",
                mixinTypes, rootMixinTypes.getMixinTypes());
    }

    @Test
    public void testToJson() {
        final ForcedRoot.JsonKeys keys = ForcedRoot.keys();
        final String path = "/correct/path";
        final String primaryType = "primaryType";
        final List<String> mixinTypes = Collections.singletonList("mixinType");

        final ForcedRoot emptyRoot = new ForcedRoot();
        assertEquals("empty root toJson should be empty",
                JsonValue.EMPTY_JSON_OBJECT, emptyRoot.toJson());

        final ForcedRoot pathRoot = new ForcedRoot().withPath(path);
        assertEquals("path root toJson should have path",
                key(keys.path(), path).get(), pathRoot.toJson());

        final ForcedRoot primaryTypeRoot = new ForcedRoot().withPrimaryType(primaryType);
        assertEquals("primaryType root toJson should have primaryType",
                key(keys.primaryType(), primaryType).get(), primaryTypeRoot.toJson());

        final ForcedRoot mixinTypeRoot = new ForcedRoot().withMixinTypes(mixinTypes.get(0));
        assertEquals("mixinTypes root toJson should have mixinTypes",
                key(keys.mixinTypes(), mixinTypes).get(), mixinTypeRoot.toJson());
    }

    @Test
    public void testCompareTo() {
        final ForcedRoot left = new ForcedRoot().withPath("/a");
        final ForcedRoot right = new ForcedRoot().withPath("/b");
        assertEquals("compare based on paths",
                "/a".compareTo("/b"), left.compareTo(right));
    }

    @Test
    public void testToString() {
        final ForcedRoot.JsonKeys keys = ForcedRoot.keys();
        final String path = "/correct/path";
        final String primaryType = "primaryType";
        final List<String> mixinTypes = Collections.singletonList("mixinType");
        final ForcedRoot root = new ForcedRoot()
                .withPath(path)
                .withPrimaryType(primaryType)
                .withMixinTypes(mixinTypes.get(0));
        final String jsonString = root.toString();
        final String expectString = key(keys.path(), path)
                .key(keys.primaryType(), primaryType)
                .key(keys.mixinTypes(), mixinTypes)
                .get().toString();
        assertEquals("expect json string for toString", expectString, jsonString);
    }

    @Test
    public void testHashCode() {
        final ForcedRoot initial = new ForcedRoot().withPath("/some/path")
                .withPrimaryType("nt:folder").withMixinTypes("mix:title");
        assertEquals("same equals for same objects", initial, initial);
        assertEquals("same hash for same objects", initial.hashCode(), initial.hashCode());
        final ForcedRoot equalToInitial = new ForcedRoot().withPath("/some/path")
                .withPrimaryType("nt:folder").withMixinTypes("mix:title");
        assertEquals("same equals for equal objects", initial, equalToInitial);
        assertEquals("same hash for equals objects", initial.hashCode(),
                equalToInitial.hashCode());
        final ForcedRoot notEqual = new ForcedRoot().withPath("/other/path")
                .withPrimaryType("nt:folder").withMixinTypes("mix:title");
        assertNotEquals("different equals for nonequal objects", initial, notEqual);
        assertNotEquals("different hash for nonequal objects", initial.hashCode(),
                notEqual.hashCode());
    }
}