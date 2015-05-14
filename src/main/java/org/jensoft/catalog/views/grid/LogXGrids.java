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
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.FreeGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.manager.FreeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>LogXGridDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class LogXGrids extends View {

	/**
	 * Create demo with log x  projection and grids
	 */
	public LogXGrids() {
		super();

		Projection proj = new Projection.LogX(0.01, 1000, 0, 100);
		proj.setThemeColor(Color.WHITE);

		registerProjection(proj);

		AxisMetricsPlugin afm = new AxisMetricsPlugin.FreeMetrics(Axis.AxisSouth);
		afm.setMarkerColor(JennyPalette.JENNY1);
		afm.setTextColor(JennyPalette.JENNY1);
		
		FreeMetricsManager freeMetricsManager = new FreeMetricsManager();
		freeMetricsManager.addMetrics(0.01);
		freeMetricsManager.addMetrics(0.1);
		freeMetricsManager.addMetrics(1);
		freeMetricsManager.addMetrics(10);
		freeMetricsManager.addMetrics(100);
		freeMetricsManager.addMetrics(1000);

		afm.setMetricsManager(freeMetricsManager);
		proj.registerPlugin(afm);

		// free grid on x
		FreeGrid gridX = new GridPlugin.FreeGrid.FreeGrid(GridOrientation.Vertical);
		proj.registerPlugin(gridX);

		for (double g = 0.01; g <= 0.09; g = g + 0.01) {
			gridX.addGrid(g, new Alpha(PetalPalette.PETAL1_HC, 60));
		}
		for (double g = 0.1; g <= 0.9; g = g + 0.1) {
			gridX.addGrid(g, new Alpha(PetalPalette.PETAL2_HC, 60));
		}
		for (double g = 1; g <= 9; g = g + 1) {
			gridX.addGrid(g, new Alpha(PetalPalette.PETAL3_HC, 60));
		}
		for (double g = 10; g <= 90; g = g + 10) {
			gridX.addGrid(g, new Alpha(PetalPalette.PETAL4_HC, 60));
		}
		for (double g = 100; g <= 900; g = g + 100) {
			gridX.addGrid(g, new Alpha(PetalPalette.PETAL5_HC, 60));
		}

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Free Log X Grid");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		proj.registerPlugin(new OutlinePlugin());
		proj.registerPlugin(new ZoomWheelPlugin());

	}

	/**
	 * 
	 * @return image portfolio
	 */
	@Portfolio(name = "Grid-LogX", width = 500, height = 250)
	public static View getPortofolio() {
		LogXGrids demo = new LogXGrids();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
