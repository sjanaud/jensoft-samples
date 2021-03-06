/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.grid;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.StrokePalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>FlowGridDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class FlowGrids extends View {

	/**
	 * Create a demo with a grid flow
	 */
	public FlowGrids() {
		super();
		//  projection
		Projection proj = new Projection.Linear(-3000, 3000, -3000, 3000);
		proj.setThemeColor(Color.WHITE);
		registerProjection(proj);

		// grid flow
		GridPlugin grids = new GridPlugin.FlowGrid(-500, 1500, 500, GridOrientation.Horizontal);
		grids.setGridColor(RosePalette.EMERALD);
		grids.setGridStroke(StrokePalette.drawingStroke5);
		proj.registerPlugin(grids);

		// legend plug-in
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Flow Grid");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// zoom wheel plug-in
		proj.registerPlugin(new ZoomWheelPlugin());

	}

	/**
	 * 
	 * @return image portfolio
	 */
	@Portfolio(name = "Grid-Flow", width = 500, height = 250)
	public static View getPortofolio() {
		FlowGrids demo = new FlowGrids();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
