/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.PluginPlatform;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class GridBreakpoint extends DemoBreakPoint {

	public GridBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install grids.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Grid");
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
				view.getWidgetPlugin().pushMessage("Install Grid", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(1000);
				Projection proj = jenSoftDemoExecutor.getDefaultProjection();

				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 500, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(255, 255, 255, 60));
				proj.registerPlugin(gridLayout);

				view.repaint();

				Thread.sleep(400);

				GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 500, GridOrientation.Vertical);
				gridLayout2.setGridColor(new Color(255, 255, 255, 60));
				proj.registerPlugin(gridLayout2);

				view.repaint();

				List<AbstractPlugin> plugins = PluginPlatform.instance().getPlugins();
				for (AbstractPlugin abstractPlugin : plugins) {
					//System.out.println("PluginPlatform - " + abstractPlugin.getName());
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
