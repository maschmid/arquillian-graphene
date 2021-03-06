/**
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.arquillian.ajocado.waiting.retrievers;

/**
 * Retrieves the typed value from page and stores it as {@link ThreadLocal}.
 *
 * @param <T> the type of object to retrieve from page
 *
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public abstract class AbstractRetriever<T> implements RetrievedValueHolder<T> {
    private ThreadLocal<T> oldValue = new ThreadLocal<T>();

    @Override
    public T getValue() {
        return oldValue.get();
    }

    @Override
    public void initializeValue() {
        oldValue.set(retrieve());
    }

    @Override
    public void setValue(T value) {
        oldValue.set(value);
    }

    public boolean isValueChanged() {
        if (oldValue.get() == null) {
            return retrieve() != null;
        }
        return !oldValue.get().equals(retrieve());
    }

    public abstract T retrieve();
}
