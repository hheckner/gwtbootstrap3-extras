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
 * The {@link SummernoteKeyDownEvent} is fired when a key is pressed within
 * the summernote editor.
 *
 * @author Xiaodong Sun
 */
public class SummernoteKeyDownEvent extends GwtEvent<SummernoteKeyDownHandler> {

    private static Type<SummernoteKeyDownHandler> TYPE;

    private final NativeEvent nativeEvent;

    /**
     * Fires a summernote key down event on all registered handlers in the handler
     * manager. If no such handlers exist, this method will do nothing.
     *
     * @param source the source of the handlers
     * @param nativeEvent native key down event
     */
    public static void fire(final HasSummernoteKeyDownHandlers source, NativeEvent nativeEvent) {
        if (TYPE != null) {
            SummernoteKeyDownEvent event = new SummernoteKeyDownEvent(nativeEvent);
            source.fireEvent(event);
        }
    }

    /**
     * Gets the type associated with this event.
     *
     * @return returns the handler type
     */
    public static Type<SummernoteKeyDownHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<SummernoteKeyDownHandler>();
        }
        return TYPE;
    }

    @Override
    public Type<SummernoteKeyDownHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SummernoteKeyDownHandler handler) {
        handler.onSummnernoteKeyDown(this);
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
     * Creates a summernote key down event.
     *
     * @param nativeEvent native key down event
     */
    protected SummernoteKeyDownEvent(NativeEvent nativeEvent) {
        this.nativeEvent = nativeEvent;
    }

    /**
     * 
     * @return whether Alt Key is pressed
     */
    public boolean isAltKeyDown() {
        return getNativeEvent().getAltKey();
    }
    
    public boolean isAnyModifierKeyDown() {
        return isControlKeyDown() || isShiftKeyDown() || isMetaKeyDown()
            || isAltKeyDown();
    }

    /**
     * Is the <code>control</code> key down?
     * 
     * @return whether the control key is down
     */
    public boolean isControlKeyDown() {
      return getNativeEvent().getCtrlKey();
    }
    
    /**
     * Is the <code>meta</code> key down?
     * 
     * @return whether the meta key is down
     */
    public boolean isMetaKeyDown() {
      return getNativeEvent().getMetaKey();
    }
    
    /**
     * Is the <code>shift</code> key down?
     * 
     * @return whether the shift key is down
     */
    public boolean isShiftKeyDown() {
      return getNativeEvent().getShiftKey();
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