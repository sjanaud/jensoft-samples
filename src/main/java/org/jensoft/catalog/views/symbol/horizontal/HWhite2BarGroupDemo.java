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
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.background.DeviceNightBackground;
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
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolDefaultLabel;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.HorizontalAlignment;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.VerticalAlignment;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>HWhite2BarGroupDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class HWhite2BarGroupDemo extends View {

	public HWhite2BarGroupDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		// Group2
		StackedBarSymbol b1g2 = new StackedBarSymbol("bar 1", "bar 1");
		// b1g2.setThemeColor(new Color(255,255,255));
		b1g2.setDescentValue(50);

		BarSymbolRelativeLabel rl1 = new BarSymbolRelativeLabel(VerticalAlignment.NorthTop, HorizontalAlignment.Middle);
		rl1.setText("stack1");

		BarSymbolRelativeLabel rl2 = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.Middle);
		rl2.setText("stack2");

		BarSymbolRelativeLabel rl3 = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.Middle);
		rl3.setText("stack3");

		b1g2.addStack("orange", TangoPalette.CHAMELEON3, 12, rl1);
		b1g2.addStack("green", TangoPalette.BUTTER3, 28, rl2);
		b1g2.addStack("blue", TangoPalette.ORANGE3, 13, rl3);

		BarSymbolDefaultLabel cl1 = new BarSymbolDefaultLabel(-20, 0);
		BarSymbolDefaultLabel cl2 = new BarSymbolDefaultLabel(-20, 0);
		BarSymbolDefaultLabel cl3 = new BarSymbolDefaultLabel(-20, 0);

		StackedBarSymbol b2g2 = new StackedBarSymbol("bar 2", "bar 2");
		b2g2.setAscentValue(40);
		b2g2.addStack("blue", TangoPalette.CHAMELEON3, 20, cl1);
		b2g2.addStack("green", TangoPalette.BUTTER3, 15, cl2);
		b2g2.addStack("orange", TangoPalette.ORANGE3, 5, cl3);

		StackedBarSymbol b3g2 = new StackedBarSymbol("bar 3", "bar 3");
		b3g2.setAscentValue(30);
		b3g2.addStack("blue", TangoPalette.CHAMELEON3, 23);
		b3g2.addStack("green", TangoPalette.BUTTER3, 15);
		b3g2.addStack("orange", TangoPalette.ORANGE3, 7);

		BarSymbolGroup group2 = new BarSymbolGroup();
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));

		group2.setSymbol("Group 2");
		group2.setName("group2");
		group2.setBase(0);
		group2.setThickness(50);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect1());

		// Windows Projection
		Projection proj = new Projection.Linear(-30, 120, 0, 0);
		registerProjection(proj);

		proj.registerPlugin(new DeviceNightBackground());

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Horizontal);
		proj.registerPlugin(barPlugin);
		proj.setName("vbar proj");

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.V(0, 20);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 20, GridOrientation.Vertical);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		southMetrics.setTextFont(f);
		

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("Stacked Bar Chart");
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
