/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class ZoomWheelBreakpoint extends DemoBreakPoint {

	public ZoomWheelBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and show zoom wheel in action\n" + "Next sequence shows zoom in and zoom out operations\nwith zoom wheel plugin.");
		msg.setSize(340, 110);
		msg.setTitle("JenSoft API - Zoom Wheel");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Zoom Wheel", 200, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);
				Projection proj = jenSoftDemoExecutor.getDefaultProjection();

				ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
				proj.registerPlugin(zoomWheel);

				wait(200);

				for (int i = 0; i < 40; i++) {
					zoomWheel.zoomIn();
					view.repaint();
					Thread.sleep(38);
				}

				view.repaint();
				Thread.sleep(800);

				for (int i = 39; i >= 0; i--) {
					zoomWheel.zoomOut();
					view.repaint();
					Thread.sleep(38);
				}

				proj.unregisterPlugin(zoomWheel);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
