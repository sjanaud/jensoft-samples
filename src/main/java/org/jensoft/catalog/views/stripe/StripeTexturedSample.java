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
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.marker.MarkerPlugin;
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
public class StripeTexturedSample extends View {

	@Portfolio(name = "Stripe-Textured", width = 500, height = 250)
	public static View getPortofolio() {
		StripeTexturedSample demo = new StripeTexturedSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public StripeTexturedSample() {
		super();

		Projection proj = new Projection.Linear(0, 10, 0, 18);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 80));
		// bp.addPaint(new Color(40,40,40,40));
		bp.addPaint(ColorPalette.alpha(JennyPalette.JENNY8, 80));
		bp.addPaint(TexturePalette.getTriangleCarbonFiber());
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin grids = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids);

		GridPlugin grids2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);

		registerProjection(proj);

		proj.registerPlugin(new OutlinePlugin());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Textured Stripe");
		legend.setFont(f);
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, proj.getThemeColor()));
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj.registerPlugin(lgendL);

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
