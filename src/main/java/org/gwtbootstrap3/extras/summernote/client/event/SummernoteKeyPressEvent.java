package org.gwtbootstrap3.extras.summernote.client.event;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2015 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;

/**
 * The {@link SummernoteKeyPressEvent} is fired when a key is pressed within
 * the summernote editor.
 *
 * @author Xiaodong Sun
 */
public class SummernoteKeyPressEvent extends GwtEvent<SummernoteKeyPressHandler> {

    private static Type<SummernoteKeyPressHandler> TYPE;

    private final NativeEvent nativeEvent;

    /**
     * Fires a summernote key down event on all registered handlers in the handler
     * manager. If no such handlers exist, this method will do nothing.
     *
     * @param source the source of the handlers
     * @param nativeEvent native key down event
     */
    public static void fire(final HasSummernoteKeyPressHandlers source, NativeEvent nativeEvent) {
        if (TYPE != null) {
            SummernoteKeyPressEvent event = new SummernoteKeyPressEvent(nativeEvent);
            source.fireEvent(event);
        }
    }

    /**
     * Gets the type associated with this event.
     *
     * @return returns the handler type
     */
    public static Type<SummernoteKeyPressHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<SummernoteKeyPressHandler>();
        }
        return TYPE;
    }

    @Override
    public Type<SummernoteKeyPressHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SummernoteKeyPressHandler handler) {
        handler.onSummnernoteKeyPress(this);
    }

    @Override
    public String toDebugString() {
        return super.toDebugString() + " key code is: " + nativeEvent.getKeyCode();
    }

    /**
     * Returns the native key down event.
     *
     * @return native key down event
     */
    public NativeEvent getNativeEvent() {
        return nativeEvent;
    }
    
    /**
     * Returns the char code
     * @return 
     */
    public int getCharCode() {
        return getNativeEvent().getCharCode();
    }

    /**
     * Creates a summernote key down event.
     *
     * @param nativeEvent native key down event
     */
    protected SummernoteKeyPressEvent(NativeEvent nativeEvent) {
        this.nativeEvent = nativeEvent;
    }
    
    /**
     * Prevents the browser from taking its default action for the given event.
     */
    public final void preventDefault() {
        this.getNativeEvent().preventDefault();
    }

    /**
     * Stops the event from being propagated to parent elements.
     */
    public final void stopPropagation() {
      this.getNativeEvent().stopPropagation();
    }   
}
    