/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.horizontal;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.StackedBarSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect2;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>HStackedBarSimpleDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class HStackedBarSimpleDemo extends View {

	public HStackedBarSimpleDemo() {
		super();
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Color green = new Color(119, 150, 98);
		Color orange = new Color(235, 137, 92);

		StackedBarSymbol b1 = new StackedBarSymbol();
		b1.setName("stacked bar 1");

		b1.setThemeColor(new Color(255, 255, 255));
		b1.setDescentValue(80);
		b1.setBase(60);
		b1.setThickness(25);

		b1.addStack("stack 1", orange, 20);
		b1.addStack("stack 2", green, 60);

		b1.setRound(8);
		b1.setMorpheStyle(MorpheStyle.Rectangle);
		b1.setBarDraw(new BarDefaultDraw());
		b1.setBarFill(new BarFill1());
		b1.setBarEffect(new BarEffect2());

		// Windows Projection
		Projection proj = new Projection.Linear(-30, 120, 0, 0);
		registerProjection(proj);

		// symbol plugin
		SymbolPlugin symbolPlugin = new SymbolPlugin();
		symbolPlugin.setNature(SymbolNature.Horizontal);
		proj.registerPlugin(symbolPlugin);

		// a dynamic grid for bar step

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 20, GridOrientation.Vertical);
		gridLayout.setGridColor(new Color(227, 216, 192));
		proj.registerPlugin(gridLayout);

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		southMetrics.setTextFont(f);
		

		BarSymbolLayer barLayer = new BarSymbolLayer();
		symbolPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(b1);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("Hor. Stacked Bar");
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
