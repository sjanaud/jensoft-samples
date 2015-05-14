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
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.StackedBarSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect1;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>HStackedBarGroupDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class HStackedBarGroupDemo extends View {

	private static final long serialVersionUID = 156889765687899L;

	public static Color PINNACLE1 = new Color(255, 173, 0);
	public static Color PINNACLE2 = new Color(255, 39, 0);
	public static Color PINNACLE3 = new Color(243, 54, 153);
	public static Color PINNACLE4 = new Color(105, 90, 168);

	public HStackedBarGroupDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Color orange = PINNACLE1;
		Color green = PINNACLE2;
		Color blue = PINNACLE3;
		Color pink = PINNACLE4;

		StackedBarSymbol b1g1 = new StackedBarSymbol();
		b1g1.setThemeColor(new Color(255, 255, 255));
		b1g1.setAscentValue(60);
		b1g1.addStack("orange", orange, 12);
		b1g1.addStack("green", green, 20);
		b1g1.addStack("blue", blue, 40);
		b1g1.addStack("pink", pink, 5);

		StackedBarSymbol b2g1 = new StackedBarSymbol();
		b2g1.setThemeColor(new Color(255, 255, 255));
		b2g1.setDescentValue(40);
		b2g1.addStack("orange", orange, 36);
		b2g1.addStack("green", green, 17);
		b2g1.addStack("blue", blue, 8);
		b2g1.addStack("pink", pink, 21);

		BarSymbolGroup group1 = new BarSymbolGroup();
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b2g1);

		group1.setSymbol("Group 1");
		group1.setName("group1");
		group1.setBase(-20);
		group1.setThickness(25);
		group1.setRound(8);
		group1.setMorpheStyle(MorpheStyle.Rectangle);
		group1.setBarDraw(new BarDefaultDraw());
		group1.setBarFill(new BarFill1());
		group1.setBarEffect(new BarEffect1());

		b1g1.setName("b1");
		b1g1.setSymbol("bar 1");
		b2g1.setName("b2");
		b2g1.setSymbol("bar 2");

		// Group2
		StackedBarSymbol b1g2 = new StackedBarSymbol();
		b1g2.setThemeColor(new Color(255, 255, 255));
		b1g2.setAscentValue(73);
		b1g2.addStack("orange", orange, 12);
		b1g2.addStack("green", green, 28);
		b1g2.addStack("blue", blue, 13);
		b1g2.addStack("pink", pink, 9);

		StackedBarSymbol b2g2 = new StackedBarSymbol();
		b2g2.setThemeColor(new Color(255, 255, 255));
		b2g2.setAscentValue(38);
		b2g2.addStack("orange", orange, 7);
		b2g2.addStack("green", green, 15);
		b2g2.addStack("blue", blue, 23);
		b2g2.addStack("pink", pink, 16);

		BarSymbolGroup group2 = new BarSymbolGroup();
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b2g2);

		group2.setSymbol("Group 2");
		group2.setName("group2");
		group2.setBase(30);
		group2.setThickness(25);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Rectangle);
		group2.setBarDraw(new BarDefaultDraw());
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect1());

		b1g2.setName("b3");
		b1g2.setSymbol("bar 3");
		b2g2.setName("b4");
		b2g2.setSymbol("bar 4");

		// Group3
		StackedBarSymbol b1g3 = new StackedBarSymbol();
		b1g3.setThemeColor(new Color(255, 255, 255));
		b1g3.setAscentValue(53);
		b1g3.addStack("orange", orange, 39);
		b1g3.addStack("green", green, 6);
		b1g3.addStack("blue", blue, 17);
		b1g3.addStack("pink", pink, 10);

		StackedBarSymbol b2g3 = new StackedBarSymbol();
		b2g3.setThemeColor(new Color(255, 255, 255));
		b2g3.setAscentValue(22);
		b2g3.addStack("orange", orange, 6);
		b2g3.addStack("green", green, 45);
		b2g3.addStack("blue", blue, 7);
		b2g3.addStack("pink", pink, 13);

		BarSymbolGroup group3 = new BarSymbolGroup();
		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b2g3);

		group3.setSymbol("Group 3");
		group3.setName("group3");
		group3.setBase(0);
		group3.setThickness(25);
		group3.setRound(8);
		group3.setMorpheStyle(MorpheStyle.Rectangle);
		group3.setBarDraw(new BarDefaultDraw());
		group3.setBarFill(new BarFill1());
		group3.setBarEffect(new BarEffect1());

		b1g3.setName("b4");
		b1g3.setSymbol("bar 4");
		b2g3.setName("b5");
		b2g3.setSymbol("bar 5");

		// Window Projection
		Projection proj = new Projection.Linear(-30, 120, 0, 0);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Horizontal);
		proj.registerPlugin(barPlugin);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.V(0, 20);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin grids2 = new GridPlugin.MultiplierGrid(0, 20, GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		southMetrics.setTextFont(f);
		

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("VBar Chart");
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
