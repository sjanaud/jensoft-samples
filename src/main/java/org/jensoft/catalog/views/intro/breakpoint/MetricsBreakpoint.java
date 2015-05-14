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
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin.DeviceAxis;
import org.jensoft.core.plugin.metrics.Metrics.MarkerPosition;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class MetricsBreakpoint extends DemoBreakPoint {

	public MetricsBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install metrics.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Metrics");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(new Alpha(Color.WHITE, 250));
		return msg;
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Metrics", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				Projection.Linear proj = jenSoftDemoExecutor.getDefaultProjection();
				proj.bound(-2800, 2800, -2800, +2800);

				Thread.sleep(800);
				
				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				Font f10 =  new Font("Dialog", Font.PLAIN, 10);
				
				AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				proj.registerPlugin(westMetrics);
				westMetrics.setTextFont(f12);
				
				westMetrics.setTextColor(RosePalette.EMERALD.brighter());
				westMetrics.setMarkerColor(RosePalette.EMERALD.brighter());
				westMetrics.setMajorTextFont(f12);
				westMetrics.setMedianTextFont(f10);

				view.repaintPart(ViewPart.West);
				Thread.sleep(800);

				AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
				proj.registerPlugin(southMetrics);
				southMetrics.setTextFont(f12);
				
				southMetrics.setTextColor(RosePalette.EMERALD.brighter());
				southMetrics.setMarkerColor(RosePalette.EMERALD.brighter());
				southMetrics.setMajorTextFont(f12);
				southMetrics.setMedianTextFont(f10);

				view.repaintPart(ViewPart.South);
				Thread.sleep(800);

				AxisMetricsPlugin.ModeledMetrics eastMetrics = new AxisMetricsPlugin.ModeledMetrics.E();
				proj.registerPlugin(eastMetrics);
				eastMetrics.setTextFont(f12);
				eastMetrics.setTextColor(RosePalette.EMERALD.brighter());
				eastMetrics.setMarkerColor(RosePalette.EMERALD.brighter());
				eastMetrics.setMajorTextFont(f12);
				eastMetrics.setMedianTextFont(f10);

				view.repaint();
				Thread.sleep(800);

				AxisMetricsPlugin.ModeledMetrics northMetrics = new AxisMetricsPlugin.ModeledMetrics.N();
				proj.registerPlugin(northMetrics);
				northMetrics.setTextFont(f12);
				northMetrics.setTextColor(RosePalette.EMERALD.brighter());
				northMetrics.setMarkerColor(RosePalette.EMERALD.brighter());
				northMetrics.setMajorTextFont(f12);
				northMetrics.setMedianTextFont(f10);

				view.repaint();
				Thread.sleep(800);

				proj.unregisterPlugin(northMetrics);
				view.repaint();
				Thread.sleep(400);

				proj.unregisterPlugin(eastMetrics);
				view.repaint();

				DeviceMetricsPlugin dmmX = new DeviceMetricsPlugin.DeviceModeledMetrics(-500, MarkerPosition.S, DeviceAxis.AxisX);

				proj.registerPlugin(dmmX);

				dmmX.setMarkerColor(RosePalette.MELON);
				dmmX.setBaseLineColor(RosePalette.CALYPSOBLUE.brighter());

				view.repaint();
				Thread.sleep(800);
				proj.unregisterPlugin(dmmX);

				view.repaint();
				Thread.sleep(1000);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
