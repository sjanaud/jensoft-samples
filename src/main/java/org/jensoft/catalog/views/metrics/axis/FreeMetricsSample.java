/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.axis;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
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
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class FreeMetricsSample extends View {

	private static final long serialVersionUID = -114560977917616448L;


	public FreeMetricsSample() {
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
		TitleLegend legend = new TitleLegend("Free Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.EMERALD, RosePalette.COALBLACK));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

	}

}
