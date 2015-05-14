/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.axis;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Locale;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.FlowMetrics;
import org.jensoft.core.plugin.metrics.Metrics.Gravity;
import org.jensoft.core.plugin.metrics.format.IMetricsFormat;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class FlowMetricsSample3 extends View {

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new FlowMetricsSample3());
	}
	

	public FlowMetricsSample3() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(100);
		setPlaceHolderAxisEast(80);

		Projection proj = new Projection.Linear(0, 100, -2, 102);
		proj.setThemeColor(RosePalette.MELON);
		registerProjection(proj);

		Font f1 =  new Font("Dialog", Font.PLAIN, 10);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		FlowMetrics flowWest = new AxisMetricsPlugin.FlowMetrics(0, 100, 10, Axis.AxisWest);
		proj.registerPlugin(flowWest);
		flowWest.setMarkerColor(RosePalette.EMERALD.brighter());
		flowWest.setTextColor(RosePalette.EMERALD.brighter());
		flowWest.setBaseLineColor(RosePalette.EMERALD.brighter());
		flowWest.setMajorTextFont(f1);
		flowWest.setGravity(Gravity.Natural);
		flowWest.setLocale(Locale.US);
		flowWest.setSuffix(" %");
		flowWest.setAxisSpacing(20);
		flowWest.setBaseLineColor(Color.GREEN);
		flowWest.setBaseLinePaint(true);
		flowWest.setMajorMarkerSize(20);

		FlowMetrics flowSouth = new AxisMetricsPlugin.FlowMetrics(0, 100, 10, Axis.AxisSouth);
		proj.registerPlugin(flowSouth);
		flowSouth.setMarkerColor(RosePalette.CALYPSOBLUE.brighter());
		flowSouth.setTextColor(RosePalette.CALYPSOBLUE.brighter());
		flowSouth.setBaseLineColor(RosePalette.CALYPSOBLUE.brighter());
		flowSouth.setMajorTextFont(f);
		flowSouth.setSuffix(" %");
		
		flowSouth.setFormat(new IMetricsFormat() {
			@Override
			public String format(double value) {
				DecimalFormat format = new DecimalFormat("#0.0");
				return format.format(value)+" %";
			}
		});
		
		flowSouth.setAxisSpacing(20);
		flowSouth.setBaseLineColor(Color.GREEN);
		flowSouth.setBaseLinePaint(true);
		flowSouth.setMajorMarkerSize(20);

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("Flow metrics");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.EMERALD, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		proj.registerPlugin(new ZoomWheelPlugin());

	}
	

}
