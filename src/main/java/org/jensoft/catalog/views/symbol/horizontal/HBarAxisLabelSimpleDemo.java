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
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.axis.BarDefaultAxisLabel;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect4;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>HBarAxisLabelSimpleDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class HBarAxisLabelSimpleDemo extends View {

	public HBarAxisLabelSimpleDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Color red = new Color(254, 206, 12);
		Color green = new Color(125, 186, 39);
		Color orange = new Color(223, 167, 59);

		// bar 1
		BarSymbol b1 = new BarSymbol();
		b1.setThemeColor(orange);
		b1.setThickness(20);
		b1.setBase(-30);
		b1.setAscentValue(62);
		b1.setName("b1");
		b1.setSymbol("bar 1");
		b1.setMorpheStyle(MorpheStyle.Round);
		b1.setBarDraw(new BarDefaultDraw());
		b1.setBarFill(new BarFill1());
		b1.setBarEffect(new BarEffect4());
		b1.setAxisLabel(new BarDefaultAxisLabel());

		// bar 2
		BarSymbol b2 = new BarSymbol();
		b2.setThemeColor(green);
		b2.setThickness(20);
		b2.setBase(-30);
		b2.setDescentValue(83);
		b2.setName("b2");
		b2.setSymbol("bar 2");
		b2.setMorpheStyle(MorpheStyle.Round);
		b2.setBarDraw(new BarDefaultDraw());
		b2.setBarFill(new BarFill1());
		b2.setBarEffect(new BarEffect4());

		// bar 3
		BarSymbol b3 = new BarSymbol();
		b3.setThemeColor(red);
		b3.setThickness(20);
		b3.setBase(-30);
		b3.setDescentValue(47);
		b3.setName("b3");
		b3.setSymbol("bar 3");
		b3.setMorpheStyle(MorpheStyle.Rectangle);
		b3.setBarDraw(new BarDefaultDraw());
		b3.setBarFill(new BarFill1());
		b3.setBarEffect(new BarEffect4());

		Projection proj = new Projection.Linear(-100, 120, 0, 0);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Horizontal);
		proj.registerPlugin(barPlugin);
		proj.setName("hbar proj");

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		southMetrics.setTextFont(f);
		

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(b1);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(b2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(b3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("H Bar Simple Chart");
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		proj.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
