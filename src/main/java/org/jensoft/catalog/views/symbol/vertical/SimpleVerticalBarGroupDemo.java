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
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.PetalPalette;
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
import org.jensoft.core.plugin.symbol.BarSymbol.SymbolInflate;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.SymbolToolkit;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect4;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleVerticalBarGroupDemo extends View {

	@Portfolio(name = "SimpleVerticalBarGroupDemo2", width = 500, height = 250)
	public static View getPortofolio() {
		SimpleVerticalBarGroupDemo demo = new SimpleVerticalBarGroupDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public SimpleVerticalBarGroupDemo() {

		Projection proj = new Projection.Linear(0, 0, -42, 82);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Vertical);
		proj.registerPlugin(barPlugin);

		setPlaceHolderAxisWest(60);
		Color blue = PetalPalette.PETAL1_HC;
		Color green = PetalPalette.PETAL2_HC;
		Color orange = PetalPalette.PETAL3_HC;

		// create symbols for group 1
		BarSymbol b1g1 = SymbolToolkit.createBarSymbol("b1g1", blue, SymbolInflate.Ascent, 62);
		BarSymbol b2g1 = SymbolToolkit.createBarSymbol("b2g1", green, SymbolInflate.Ascent, 83);
		BarSymbol b3g1 = SymbolToolkit.createBarSymbol("b3g1", orange, SymbolInflate.Ascent, 47);

		// or use bar factory
		// BarFactory.createGroup(nameSymbol, base, thickness);
		// BarFactory.createGroup(nameSymbol, base, thickness, round)
		// BarFactory.createGroup(nameSymbol, base, thickness, barDraw, barFill,
		// barEffect);

		BarSymbolGroup group1 = new BarSymbolGroup("G1");
		group1.setBase(-30);
		group1.setThickness(25);
		group1.setRound(8);
		group1.setMorpheStyle(MorpheStyle.Round);
		group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group1.setBarFill(new BarFill1());
		group1.setBarEffect(new BarEffect4());

		// lay out symbol in group
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b2g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b3g1);

		// Symbols for Group 2
		BarSymbol b1g2 = SymbolToolkit.createBarSymbol("b1g1", blue, SymbolInflate.Ascent, 64);
		BarSymbol b2g2 = SymbolToolkit.createBarSymbol("b2g1", green, SymbolInflate.Ascent, 77);
		BarSymbol b3g2 = SymbolToolkit.createBarSymbol("b3g1", orange, SymbolInflate.Ascent, 32);

		// or use factory
		// BarFactory.createGroup(nameSymbol, base, thickness);
		// BarFactory.createGroup(nameSymbol, base, thickness, round)
		// BarFactory.createGroup(nameSymbol, base, thickness, barDraw, barFill,
		// barEffect);
		BarSymbolGroup group2 = new BarSymbolGroup("G2");
		group2.setBase(-30);
		group2.setThickness(25);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect4());

		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b2g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b3g2);

		// Group3
		BarSymbol b1g3 = SymbolToolkit.createBarSymbol("b1g1", blue, SymbolInflate.Ascent, 17);
		BarSymbol b2g3 = SymbolToolkit.createBarSymbol("b2g1", green, SymbolInflate.Ascent, 43);
		BarSymbol b3g3 = SymbolToolkit.createBarSymbol("b3g1", orange, SymbolInflate.Ascent, 78);

		BarSymbolGroup group3 = new BarSymbolGroup();

		group3.setSymbol("Group 3");
		group3.setName("group3");
		group3.setBase(-30);
		group3.setThickness(25);
		group3.setRound(8);
		group3.setMorpheStyle(MorpheStyle.Round);
		group3.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group3.setBarFill(new BarFill1());
		group3.setBarEffect(new BarEffect4());

		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b2g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b3g3);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 30);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 30, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		FreeGrid grids = new GridPlugin.FreeGrid(GridOrientation.Horizontal);
		grids.getGridManager().addGrid(-32, "JenSoft", PetalPalette.PETAL4_HC, 0.001f);
		proj.registerPlugin(grids);

		BarSymbolLayer barLayer = new BarSymbolLayer();

		// lay out group symbol in view
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		barPlugin.addLayer(barLayer);

		// create legend
		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Example of Symbols");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY3));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		legendPlugin.addLegend(legend);
		proj.registerPlugin(legendPlugin);

		// register zoom tool (zoom only into scalar dimension and not in symbol
		// dimension)
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);
		repaintDevice();
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(200);
					repaintView();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		// t.start();

	}

}
