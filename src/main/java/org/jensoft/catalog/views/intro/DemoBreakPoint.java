/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro;

import org.jensoft.core.plugin.message.Message;
import org.jensoft.core.view.View;

/**
 * <code>DemoBreakPoint</code> defines a demo break point which is use to show
 * JenSoft API feature.
 * 
 * @author Sébastien Janaud
 */
public abstract class DemoBreakPoint extends Thread {

	/** view root */
	public View view;

	/** break points executor */
	public JenSoftBreackpointExecutor jenSoftDemoExecutor;

	/** skip break point flag */
	public boolean skipBreackpoint = false;

	/** skip break point message */
	public boolean skipMessage = false;

	/**
	 * Create break point with the specified given parameters
	 * 
	 * @param jenSoftDemoExecutor
	 *            the demo executor
	 * @param view
	 *            the view root components
	 */
	public DemoBreakPoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		this.jenSoftDemoExecutor = jenSoftDemoExecutor;
		this.view = view;
	}

	/**
	 * @return the skipBreackpoint
	 */
	public boolean isSkipBreackpoint() {
		return skipBreackpoint;
	}

	/**
	 * @param skipBreackpoint
	 *            the skipBreackpoint to set
	 */
	public void setSkipBreackpoint(boolean skipBreackpoint) {
		this.skipBreackpoint = skipBreackpoint;
	}

	/**
	 * @return the skipMessage
	 */
	public boolean isSkipMessage() {
		return skipMessage;
	}

	/**
	 * @param skipMessage
	 *            the skipMessage to set
	 */
	public void setSkipMessage(boolean skipMessage) {
		this.skipMessage = skipMessage;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @return the jenSoftDemoExecutor
	 */
	public JenSoftBreackpointExecutor getJenSoftDemoExecutor() {
		return jenSoftDemoExecutor;
	}

	public class DemoMessage extends Message {

		private static final long serialVersionUID = -2077354418450925291L;

		public DemoMessage() {
			super();
			// TODO Auto-generated constructor stub
		}

		public DemoMessage(String title, String text) {
			super(title, text);
			// TODO Auto-generated constructor stub
		}

		public DemoMessage(String text) {
			super(text);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onClose() {
			synchronized (DemoBreakPoint.this) {
				DemoBreakPoint.this.notifyAll();
			}
		}
	}

	protected DemoMessage getMessage() {
		return null;
	}

	@Override
	public void run() {
		synchronized (this) {
			if (getMessage() != null && !isSkipMessage() && !jenSoftDemoExecutor.isSkipBreackpointsMessages()) {
				try {
					jenSoftDemoExecutor.getMessagePlugin().showVolatileMessage(getMessage());
					wait();
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
