/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.device;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.message.Message;
import org.jensoft.core.plugin.message.MessagePlugin;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin.DeviceFreeMetrics;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class DeviceFreeMetricsSample extends View {

	public DeviceFreeMetricsSample() {
		super();
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Projection proj = new Projection.Linear(-60, 100, -20, 140);
		proj.setThemeColor(RosePalette.MELON);
		registerProjection(proj);

		final MessagePlugin messagePlugin = new MessagePlugin();
		proj.registerPlugin(messagePlugin);

		final Message msg = new Message("This sample shows the free metrics.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Free Metrics");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					messagePlugin.showVolatileMessage(msg, 4000);
				} catch (InterruptedException e) {
				}

			}
		};
		t.start();

		OutlinePlugin outline = new OutlinePlugin();
		proj.registerPlugin(outline);

		DeviceFreeMetrics deviceFreeXMetrics = new DeviceMetricsPlugin.DeviceFreeMetrics.X(20);
		deviceFreeXMetrics.setTextColor(RosePalette.LEMONPEEL);
		deviceFreeXMetrics.addMetrics(-47);
		deviceFreeXMetrics.addMetrics(-12);
		deviceFreeXMetrics.addMetrics(13.4);
		deviceFreeXMetrics.addMetrics(33, "label");
		deviceFreeXMetrics.addMetrics(78);
		proj.registerPlugin(deviceFreeXMetrics);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Device Free Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.EMERALD, RosePalette.COALBLACK));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

	}

	@Portfolio(name = "Metrics-Device-Free", width = 500, height = 250)
	public static View getPortofolio() {
		DeviceFreeMetricsSample demo = new DeviceFreeMetricsSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
