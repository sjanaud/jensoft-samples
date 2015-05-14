/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.quickstart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>MultipleCurveLabelDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class QuickStart extends View {

	/**
	 * Create the quickstart view
	 */
	public QuickStart() {
		Font font = new Font("lucida console", Font.PLAIN, 10);

		// default in 40 pixel when the view is created by default constructor
		setPlaceHolder(60, ViewPart.South, ViewPart.West);

		//  projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		registerProjection(proj);
		proj.setThemeColor(RosePalette.LEMONPEEL);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// Legend
		TitleLegend legend = new TitleLegend("JenSoft QuickStart");
		legend.setFont(font);
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, proj.getThemeColor()));
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj.registerPlugin(lgendL);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// some stripes
		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		// some grids
		GridPlugin hgrids = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		hgrids.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(hgrids);

		GridPlugin vgrids = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		vgrids.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(vgrids);

		// zoom wheel
		ZoomWheelPlugin wheelPlugin = new ZoomWheelPlugin();
		proj.registerPlugin(wheelPlugin);

		// zooms box
		ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		zoomPlugin.registerContext(new ZoomBoxDefaultDeviceContext());
		proj.registerPlugin(zoomPlugin);

		// zoom lens
		ZoomLensPlugin lensPlugin = new ZoomLensPlugin();
		lensPlugin.registerContext(new LensDefaultDeviceContext());

		// translate
		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translatePlugin);

		// pre lock translate, just drag device pane and translate! else use
		// context menu to lock/unlock tools
		// translatePlugin.lockSelected();
	}

	/**
	 * 
	 * @return image portfolio
	 */
	@Portfolio(name = "QuickStart", width = 800, height = 600)
	public static View getPortofolio() {
		QuickStart demo = new QuickStart();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
