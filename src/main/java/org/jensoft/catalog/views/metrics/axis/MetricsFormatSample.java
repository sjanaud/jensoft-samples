/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.FreeMetrics;
import org.jensoft.core.plugin.metrics.format.IMetricsFormat;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class MetricsFormatSample extends View {

	@Portfolio(name = "Metrics-Format", width = 500, height = 250)
	public static View getPortofolio() {
		MetricsFormatSample demo = new MetricsFormatSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public MetricsFormatSample() {
		super();
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Date now = new Date();
		long nowMillisecond = now.getTime();

		long hourMillisecond = 60 * 60 * 1000;

		long minX = nowMillisecond - 12 * hourMillisecond; // now - 12 hours
		long maxX = nowMillisecond + 12 * hourMillisecond; // now + 12 hours

		Projection proj = new Projection.Linear(minX, maxX, 0, 18);
		registerProjection(proj);

		// GradientPlugin gradientPlugin = new Night();
		// gradientPlugin.setAlpha(0.9f);
		// proj.registerPlugin(gradientPlugin);

		AxisMetricsPlugin miliWest = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisWest);
		proj.registerPlugin(miliWest);

		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEE dd");
		AxisMetricsPlugin miliSouth = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisSouth);
		miliSouth.setMetricsFormat(new IMetricsFormat() {
			@Override
			public String format(double d) {
				return sdf.format(new Date((long) d));
			}
		});
		proj.registerPlugin(miliSouth);

		proj.registerPlugin(new OutlinePlugin());
		registerProjection(proj);

		final MessagePlugin messagePlugin = new MessagePlugin();
		proj.registerPlugin(messagePlugin);

		final Message msg = new Message("This sample shows the metrics format.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Metrics Format");
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

		FreeMetrics westFreeMetrics = new AxisMetricsPlugin.FreeMetrics(Axis.AxisWest);
		westFreeMetrics.addMetrics(32, "west label");
		westFreeMetrics.setTextColor(RosePalette.LIME);
		proj.registerPlugin(westFreeMetrics);

		FreeMetrics southFreeMetrics = new AxisMetricsPlugin.FreeMetrics(Axis.AxisSouth);
		southFreeMetrics.setTextColor(RosePalette.LEMONPEEL);
		southFreeMetrics.addMetrics(-40);
		southFreeMetrics.addMetrics(-10);
		southFreeMetrics.addMetrics(30, "label");
		southFreeMetrics.addMetrics(80);
		proj.registerPlugin(southFreeMetrics);

		FreeMetrics eastFreeMetrics = new AxisMetricsPlugin.FreeMetrics(Axis.AxisEast);
		eastFreeMetrics.setTextColor(RosePalette.MELON);
		eastFreeMetrics.addMetrics(-10);
		eastFreeMetrics.addMetrics(30, "label");
		eastFreeMetrics.addMetrics(60);
		eastFreeMetrics.addMetrics(120);
		proj.registerPlugin(eastFreeMetrics);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Format Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.MANDARIN, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

	}

}
