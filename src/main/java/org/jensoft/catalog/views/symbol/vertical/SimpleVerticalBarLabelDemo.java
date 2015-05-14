/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.vertical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.FreeGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect2;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolDefaultLabel;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleVerticalBarLabelDemo extends View {

	@Portfolio(name = "SimpleVerticalBarLabelDemo", width = 600, height = 400)
	public static View getPortofolio() {
		SimpleVerticalBarLabelDemo demo = new SimpleVerticalBarLabelDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public SimpleVerticalBarLabelDemo() {

		Projection proj = new Projection.Linear(0, 0, -35, 80);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Vertical);
		proj.registerPlugin(barPlugin);

		Color chameleon = TangoPalette.CHAMELEON2;
		Color butter = TangoPalette.BUTTER2;
		Color orange = TangoPalette.ORANGE2;

		// Create 3 groups

		// create bars symbol for group 1
		BarSymbol b1g1 = new BarSymbol("bar 1", "bar 1");
		b1g1.setAscentValue(62);
		b1g1.setThemeColor(chameleon);
		b1g1.setBarLabel(new BarSymbolDefaultLabel(-20, -40));

		BarSymbol b2g1 = new BarSymbol("bar 2", "bar 2");
		b2g1.setAscentValue(83);
		b2g1.setThemeColor(butter);
		b2g1.setBarLabel(new BarSymbolDefaultLabel(10, 0));

		BarSymbol b3g1 = new BarSymbol("bar 3", "bar 3");
		b3g1.setAscentValue(47);
		b3g1.setThemeColor(orange);
		b3g1.setBarLabel(new BarSymbolDefaultLabel(0, +40));

		// lay out bars in group 1 symbol
		BarSymbolGroup group1 = new BarSymbolGroup("Group 1", "Group 1");
		group1.setBase(-30);
		group1.setThickness(42);
		group1.setRound(12);
		group1.setMorpheStyle(MorpheStyle.Round);
		group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group1.setBarFill(new BarFill1());
		group1.setBarEffect(new BarEffect2());

		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group1.addSymbol(b2g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group1.addSymbol(b3g1);

		// create bars symbol for group 2
		BarSymbol b1g2 = new BarSymbol("bar 4", "bar 4");
		b1g2.setAscentValue(64);
		b1g2.setThemeColor(chameleon);
		b1g2.setBarLabel(new BarSymbolDefaultLabel(-60, 0, "label", Color.DARK_GRAY, new Color(40, 40, 40, 40), Color.WHITE));

		BarSymbol b2g2 = new BarSymbol("bar 5", "bar 5");
		b2g2.setAscentValue(77);
		b2g2.setThemeColor(butter);
		b2g2.setBarLabel(new BarSymbolDefaultLabel(0, 0));

		BarSymbol b3g2 = new BarSymbol("bar 6", "bar 6");
		b3g2.setAscentValue(32);
		b3g2.setThemeColor(orange);
		b3g2.setBarLabel(new BarSymbolDefaultLabel(0, 0));

		BarSymbolGroup group2 = new BarSymbolGroup("Group 2", "Group 2");
		group2.setBase(-30);
		group2.setThickness(42);
		group2.setRound(12);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect2());

		// lay out bar in group 2 symbol
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group2.addSymbol(b2g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group2.addSymbol(b3g2);

		// create bars symbol for group 3
		BarSymbol b1g3 = new BarSymbol("bar 7", "bar 7");
		b1g3.setAscentValue(30);
		b1g3.setThemeColor(chameleon);
		b1g3.setBarLabel(new BarSymbolDefaultLabel(0, 0));

		BarSymbol b2g3 = new BarSymbol("bar 8", "bar 8");
		b2g3.setAscentValue(40);
		b2g3.setThemeColor(butter);
		b2g3.setBarLabel(new BarSymbolDefaultLabel(0, 0));

		BarSymbol b3g3 = new BarSymbol("bar 9", "bar 9");
		b3g3.setAscentValue(50);
		b3g3.setThemeColor(orange);
		b3g3.setBarLabel(new BarSymbolDefaultLabel(0, 0));

		BarSymbolGroup group3 = new BarSymbolGroup("Group 3", "Group 3");
		group3.setBase(-30);
		group3.setThickness(42);
		group3.setRound(12);
		group3.setMorpheStyle(MorpheStyle.Round);
		group3.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group3.setBarFill(new BarFill1());
		group3.setBarEffect(new BarEffect2());

		// lay out bar in group 3 symbol
		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group3.addSymbol(b2g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		group3.addSymbol(b3g3);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 30);
		StripePalette bp = new StripePalette();
		float[] fractions = { 0f, 0.5f, 1f };
		Color c = RosePalette.CORALRED;
		Color[] colors = { ColorPalette.alpha(c, 200), ColorPalette.alpha(c, 80), ColorPalette.alpha(c, 200) };
		bp.addPaint(fractions, colors);
		bp.addPaint(TexturePalette.getTriangleCarbonFiber());
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.1f);
		proj.registerPlugin(stripePlugin);

		// a dynamic grid for bar step
		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(-30, 20, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		FreeGrid grids = new GridPlugin.FreeGrid(GridOrientation.Horizontal);
		grids.getGridManager().addGrid(-32, "JenSoft", PetalPalette.PETAL4_HC, 0.001f);
		proj.registerPlugin(grids);
		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 120));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 120));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		// LEGEND
		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Classic Bar Label");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.COPPER2));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		proj.registerPlugin(lgendL);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
