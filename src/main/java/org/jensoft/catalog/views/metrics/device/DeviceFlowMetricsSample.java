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
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin.DeviceFlowMetrics;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>FlowMetricsSample</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class)
public class DeviceFlowMetricsSample extends View {

	public DeviceFlowMetricsSample() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(80);
		setPlaceHolderAxisEast(80);

		Projection proj = new Projection.Linear(-3000, 3000, -2500, 2500);
		proj.setThemeColor(RosePalette.MELON);
		registerProjection(proj);

		final MessagePlugin messagePlugin = new MessagePlugin();
		proj.registerPlugin(messagePlugin);

		final Message msg = new Message("This sample shows a flow metrics.");
		msg.setSize(320, 110);
		msg.setTitle("Device Flow Metrics");
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
		Font font = new Font("lucida console", Font.PLAIN, 8);
		DeviceFlowMetrics deviceFlowMetrics = new DeviceMetricsPlugin.DeviceFlowMetrics.X(1000, 1800, 400, -500);
		proj.registerPlugin(deviceFlowMetrics);
		deviceFlowMetrics.setMarkerColor(RosePalette.EMERALD.brighter());
		deviceFlowMetrics.setTextColor(RosePalette.EMERALD.brighter());
		deviceFlowMetrics.setBaseLineColor(RosePalette.EMERALD.brighter());
		deviceFlowMetrics.setMajorTextFont(font);


		proj.registerPlugin(new OutlinePlugin());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Flow metrics");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.EMERALD, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		proj.registerPlugin(new ZoomWheelPlugin());

	}

	@Portfolio(name = "Metrics-Device-Flow", width = 500, height = 250)
	public static View getPortofolio() {
		DeviceFlowMetricsSample demo = new DeviceFlowMetricsSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
