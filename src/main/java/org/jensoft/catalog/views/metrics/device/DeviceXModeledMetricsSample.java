/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.device;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin.DeviceModeledMetrics;
import org.jensoft.core.plugin.metrics.Metrics.MarkerPosition;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin.ZoomWheelType;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class DeviceXModeledMetricsSample extends View {

	private static final long serialVersionUID = 2809957053752697077L;

	public DeviceXModeledMetricsSample() {
		super();

		// proj linear projection
		Projection proj = new Projection.Linear(-3000, 3000, -2500, 2500);
		proj.setThemeColor(RosePalette.EMERALD);
		registerProjection(proj);

		// device x metrics
		DeviceModeledMetrics deviceXMetrics = new DeviceMetricsPlugin.DeviceModeledMetrics.X(-500, MarkerPosition.N);
		proj.registerPlugin(deviceXMetrics);
		deviceXMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		deviceXMetrics.setTextColor(RosePalette.LEMONPEEL);
		deviceXMetrics.setBaseLineColor(RosePalette.MELON);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// legend plug-in
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Device Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// zoom wheel on x dimension only
		proj.registerPlugin(new ZoomWheelPlugin(ZoomWheelType.ZoomX));

	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new DeviceXModeledMetricsSample());
	}


}
