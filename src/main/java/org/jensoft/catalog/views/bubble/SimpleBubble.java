/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.bubble;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.bubble.Bubble;
import org.jensoft.core.plugin.bubble.BubblePlugin;
import org.jensoft.core.plugin.bubble.painter.effect.BubbleEffect1;
import org.jensoft.core.plugin.bubble.painter.effect.BubbleEffect2;
import org.jensoft.core.plugin.bubble.painter.effect.BubbleEffect3;
import org.jensoft.core.plugin.bubble.painter.effect.BubbleEffect4;
import org.jensoft.core.plugin.bubble.painter.fill.BubbleDefaultFill;
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
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>SimpleBubble</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description = "Display basic bubble - incubator")
public class SimpleBubble extends View {

	private static final long serialVersionUID = -6502172737450632135L;

	/**
	 * Create a simple bubble demo
	 */
	public SimpleBubble() {
		super();

		// set size in pixel for outer's components
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		// projection
		Projection proj = new Projection.Linear(-180, 180, -180, 180);
		proj.setThemeColor(RosePalette.LIME);
		registerProjection(proj);

		// bubbles
		Bubble b1 = new Bubble(-30, -80, 40, RosePalette.AEGEANBLUE);
		b1.setBubbleEffect(new BubbleEffect1());
		b1.setBubbleFill(new BubbleDefaultFill());

		Bubble b2 = new Bubble(10, 20, 50, RosePalette.EMERALD);
		b2.setBubbleEffect(new BubbleEffect2());
		b2.setBubbleFill(new BubbleDefaultFill());

		Bubble b3 = new Bubble(70, 50, 40, RosePalette.MANDARIN);
		b3.setBubbleEffect(new BubbleEffect3());
		b3.setBubbleFill(new BubbleDefaultFill());

		Bubble b4 = new Bubble(75, -80, 34, RosePalette.FLANNELGRAY);
		b4.setBubbleEffect(new BubbleEffect4());
		b4.setBubbleFill(new BubbleDefaultFill());

		Bubble b5 = new Bubble(50, 150, 38, RosePalette.FLAMINGO);
		b5.setBubbleEffect(new BubbleEffect4());
		b5.setBubbleFill(new BubbleDefaultFill());

		Bubble b6 = new Bubble(-50, 120, 46, RosePalette.LIME);
		b6.setBubbleEffect(new BubbleEffect4());
		b6.setBubbleFill(new BubbleDefaultFill());

		BubblePlugin bubblePlugin = new BubblePlugin();
		proj.registerPlugin(bubblePlugin);

		bubblePlugin.addBubble(b1);
		bubblePlugin.addBubble(b2);
		bubblePlugin.addBubble(b3);
		bubblePlugin.addBubble(b4);
		bubblePlugin.addBubble(b5);
		bubblePlugin.addBubble(b6);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		// legend
		TitleLegend legend = new TitleLegend("Simple Bubble");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY8));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// grid plug-in
		GridPlugin grids = new GridPlugin.MultiplierGrid(0, 80, GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids);

		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		

		// outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// zoom box plug-in
		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		// translate plug-in
		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		// zoom wheel plug-in
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

	/**
	 * create the portfolio for the {@link #BubbleSimpleDemo()}
	 * 
	 * @return portfolio view
	 */
	@Portfolio(name = "BubbleSimpleDemo", width = 600, height = 400)
	public static View getPortofolio() {
		SimpleBubble demo = new SimpleBubble();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
