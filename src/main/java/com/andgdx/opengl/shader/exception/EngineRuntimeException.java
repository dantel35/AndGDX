package com.andgdx.opengl.shader.exception;

/**
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 02:40:26 - 07.08.2011
 */
public class EngineRuntimeException extends RuntimeException {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final long serialVersionUID = -4325207483842883006L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public EngineRuntimeException() {
		super();
	}

	public EngineRuntimeException(final String pMessage) {
		super(pMessage);
	}

	public EngineRuntimeException(final Throwable pThrowable) {
		super(pThrowable);
	}

	public EngineRuntimeException(final String pMessage, final Throwable pThrowable) {
		super(pMessage, pThrowable);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
