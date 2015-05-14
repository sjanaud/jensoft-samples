/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class ZoomBoxBreakpoint extends DemoBreakPoint {

	public ZoomBoxBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install zoom box.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Zoom Box");
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
				view.getWidgetPlugin().pushMessage("Install Zoom Box", 200, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);
				Projection.Linear proj = jenSoftDemoExecutor.getDefaultProjection();
				proj.bound(-2800, 2800, -2800, +2800);

				ZoomBoxPlugin zoomBox = new ZoomBoxPlugin();
				proj.registerPlugin(zoomBox);

				zoomBox.setZoomBoxDrawColor(RosePalette.MELON);
				zoomBox.setZoomBoxFillColor(RosePalette.CALYPSOBLUE.brighter());

				wait(1000);
				zoomBox.lockSelected();
				wait(500);
				int deviceWidth = zoomBox.getProjection().getDevice2D().getDeviceWidth();
				int deviceHeight = zoomBox.getProjection().getDevice2D().getDeviceHeight();

				int stepCount = 70;

				double deltaX = deviceWidth / 4;
				double dx = deltaX / stepCount;
				double deltaY = deviceHeight / 4;
				double dy = deltaY / stepCount;

				int startx = (int) (deviceWidth / 2 - deltaX / 2);
				int starty = (int) (deviceHeight / 2 - deltaY / 2);

				zoomBox.processZoomStart(new Point2D.Double(startx, starty));

				double w = startx;
				double h = starty;

				for (int i = 0; i < stepCount; i++) {
					w = w + dx;
					h = h + dy;
					zoomBox.processZoomBound(new Point2D.Double((int) w, (int) h));
					zoomBox.getProjection().getView().repaint();
					Thread.sleep(10);
				}

				Thread.sleep(1000);
				zoomBox.processZoomIn();

				Thread.sleep(1500);
				zoomBox.processZoomOut();
				Thread.sleep(500);

				proj.unregisterPlugin(zoomBox);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
