/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.stripe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.marker.MarkerPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class StripeFlowSample extends View {

	@Portfolio(name = "Stripe-Flow", width = 500, height = 250)
	public static View getPortofolio() {
		StripeFlowSample demo = new StripeFlowSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public StripeFlowSample() {
		super();

		Projection proj = new Projection.Linear(0, 10, 0, 18);
		registerProjection(proj);

		StripePlugin.FlowStripe flowStripe = new StripePlugin.FlowStripe.V(2, 8, 3);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 80));
		bp.addPaint(ColorPalette.alpha(RosePalette.MANDARIN, 80));
		flowStripe.setStripePalette(bp);

		flowStripe.setAlpha(0.3f);
		proj.registerPlugin(flowStripe);

		Font font = new Font("lucida console", Font.PLAIN, 10);
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		southMetrics.setTextColor(TangoPalette.SCARLETRED3.brighter());
		// southMetrics.setMarkerColor(TangoPalette.SCARLETRED3);

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		westMetrics.setTextColor(TangoPalette.SKYBLUE3.brighter());

		proj.registerPlugin(new OutlinePlugin());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Flow Stripe");
		legend.setFont(f);
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, proj.getThemeColor()));
		TitleLegendConstraints c = new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth);
		legend.setConstraints(c);
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		legendPlugin.addLegend(legend);
		proj.registerPlugin(legendPlugin);

		ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		proj.registerPlugin(zoomPlugin);

		TranslatePlugin translatePlugin = new TranslatePlugin();
		proj.registerPlugin(translatePlugin);

		ZoomWheelPlugin wheelPlugin = new ZoomWheelPlugin();
		proj.registerPlugin(wheelPlugin);

		ZoomLensPlugin objectifPlugin = new ZoomLensPlugin();
		proj.registerPlugin(objectifPlugin);

		ZoomPercentPlugin percentPlugin = new ZoomPercentPlugin();
		proj.registerPlugin(percentPlugin);

		MarkerPlugin crossPlugin = new MarkerPlugin();
		proj.registerPlugin(crossPlugin);

	}

}
