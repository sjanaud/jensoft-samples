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
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin.InflateOutline;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class OutlineBreakpoint extends DemoBreakPoint {

	public OutlineBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install outline.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Outline");
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
				view.getWidgetPlugin().pushMessage("Install Outline", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);
				Projection proj = jenSoftDemoExecutor.getDefaultProjection();
				OutlinePlugin outline = new OutlinePlugin(RosePalette.COALBLACK);
				proj.registerPlugin(outline);
				InflateOutline io = outline.inflateOutline();
				io.join();
			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
