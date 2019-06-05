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

package net.adamcin.oakpal.core.jcrfacade.security.authorization;

import javax.jcr.AccessDeniedException;
import javax.jcr.NamespaceException;
import javax.jcr.RepositoryException;
import javax.jcr.security.AccessControlException;
import javax.jcr.security.Privilege;

import net.adamcin.oakpal.core.ListenerReadOnlyException;
import org.apache.jackrabbit.api.security.authorization.PrivilegeManager;

/**
 * Wraps {@link PrivilegeManager} to prevent writes by handlers.
 */
public class PrivilegeManagerFacade implements PrivilegeManager {

    private final PrivilegeManager delegate;

    public PrivilegeManagerFacade(final PrivilegeManager delegate) {
        this.delegate = delegate;
    }

    @Override
    public Privilege[] getRegisteredPrivileges() throws RepositoryException {
        return delegate.getRegisteredPrivileges();
    }

    @Override
    public Privilege getPrivilege(final String privilegeName) throws AccessControlException, RepositoryException {
        return delegate.getPrivilege(privilegeName);
    }

    @Override
    public Privilege registerPrivilege(final String privilegeName, final boolean isAbstract, final String[] declaredAggregateNames) throws AccessDeniedException, NamespaceException, RepositoryException {
        throw new ListenerReadOnlyException();
    }
}