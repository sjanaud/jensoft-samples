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
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePaint;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class StripePaintGradientSample extends View {

	@Portfolio(name = "Stripe-Gradient", width = 500, height = 250)
	public static View getPortofolio() {
		StripePaintGradientSample demo = new StripePaintGradientSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public StripePaintGradientSample() {
		super();

		Projection proj = new Projection.Linear(0, 10, 0, 18);
		registerProjection(proj);

		proj.setThemeColor(RosePalette.MELON);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		Shader paint1 = new Shader(new float[] { 0f, 0.3f, 0.7f, 1f }, new Color[] { new Alpha(TangoPalette.SKYBLUE3, 10), new Alpha(TangoPalette.SKYBLUE3, 40), new Alpha(TangoPalette.SKYBLUE3, 40), new Alpha(TangoPalette.SKYBLUE3, 10) });
		StripePaint pGradient1 = new StripePaint(paint1);
		bp.addPaint(pGradient1);
		Shader paint2 = new Shader(new float[] { 0f, 0.3f, 0.7f, 1f }, new Color[] { new Alpha(TangoPalette.ORANGE3, 10), new Alpha(TangoPalette.ORANGE3, 40), new Alpha(TangoPalette.ORANGE3, 40), new Alpha(TangoPalette.ORANGE3, 10) });
		StripePaint pGradient2 = new StripePaint(paint2);
		bp.addPaint(pGradient2);

		stripePlugin.setStripePalette(bp);
		// stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin grids = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids);

		GridPlugin grids2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);

		proj.registerPlugin(new OutlinePlugin());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Stripe");
		legend.setFont(f);
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, proj.getThemeColor()));
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj.registerPlugin(lgendL);

		// ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		// proj.registerPlugin(zoomPlugin);
		//
		// TranslatePlugin translatePlugin = new TranslatePlugin();
		// proj.registerPlugin(translatePlugin);
		//
		// ZoomWheelPlugin wheelPlugin = new ZoomWheelPlugin();
		// proj.registerPlugin(wheelPlugin);
		//
		// ZoomObjectifPlugin objectifPlugin = new ZoomObjectifPlugin();
		// proj.registerPlugin(objectifPlugin);
		//
		// ZoomPercentPlugin percentPlugin = new ZoomPercentPlugin();
		// proj.registerPlugin(percentPlugin);
		//
		// MarkerPlugin crossPlugin = new MarkerPlugin();
		// proj.registerPlugin(crossPlugin);

	}

}
