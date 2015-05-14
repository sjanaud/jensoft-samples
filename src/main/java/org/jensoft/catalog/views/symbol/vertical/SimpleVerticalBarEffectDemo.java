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
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect1;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect2;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect3;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect4;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleVerticalBarEffectDemo extends View {

	@Portfolio(name = "SimpleVerticalBarEffectDemo", width = 500, height = 250)
	public static View getPortofolio() {
		SimpleVerticalBarEffectDemo demo = new SimpleVerticalBarEffectDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public SimpleVerticalBarEffectDemo() {

		Projection proj = new Projection.Linear(0, 0, -42, 60);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Vertical);
		proj.registerPlugin(barPlugin);

		// create symbols from scratch or use BarFactory.
		// BarFactory.createSymbol(nameSymbol, themeColor, symbolInflate,
		// symbolInflateValue)
		// BarFactory.createSymbol(nameSymbol, themeColor, thickness, base,
		// symbolInflate, symbolInflateValue)
		// etc.
		BarSymbol b1 = new BarSymbol();
		b1.setThemeColor(PetalPalette.PETAL1_LC);
		b1.setThickness(32);
		b1.setBase(-30);
		b1.setAscentValue(62);
		b1.setName("b1");
		b1.setSymbol("bar 1");
		b1.setMorpheStyle(MorpheStyle.Round);
		b1.setBarDraw(new BarDefaultDraw());
		b1.setBarFill(new BarFill1());
		b1.setBarEffect(new BarEffect1());
		b1.setBarDraw(new BarDefaultDraw(Color.WHITE));
		b1.setRound(10);
		// bar 2
		BarSymbol b2 = new BarSymbol();
		b2.setThemeColor(PetalPalette.PETAL2_LC);
		b2.setThickness(32);
		b2.setBase(-30);
		b2.setAscentValue(83);
		b2.setName("b2");
		b2.setSymbol("bar 2");
		b2.setMorpheStyle(MorpheStyle.Round);
		b2.setBarDraw(new BarDefaultDraw());
		b2.setBarFill(new BarFill1());
		b2.setBarEffect(new BarEffect2());
		b2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		b2.setRound(10);

		// bar 3
		BarSymbol b3 = new BarSymbol();
		b3.setThemeColor(PetalPalette.PETAL3_LC);
		b3.setThickness(32);
		b3.setBase(-30);
		b3.setAscentValue(47);
		b3.setName("b3");
		b3.setSymbol("bar 3");
		b3.setMorpheStyle(MorpheStyle.Round);
		b3.setBarDraw(new BarDefaultDraw());
		b3.setBarFill(new BarFill1());
		b3.setBarEffect(new BarEffect3());
		b3.setBarDraw(new BarDefaultDraw(Color.WHITE));
		b3.setRound(10);
		// bar 3
		BarSymbol b4 = new BarSymbol();
		b4.setThemeColor(PetalPalette.PETAL4_LC);
		b4.setThickness(32);
		b4.setBase(-30);
		b4.setAscentValue(47);
		b4.setName("b4");
		b4.setSymbol("bar 3");
		b4.setMorpheStyle(MorpheStyle.Round);
		b4.setBarDraw(new BarDefaultDraw());
		b4.setBarFill(new BarFill1());
		b4.setBarEffect(new BarEffect4());
		b4.setBarDraw(new BarDefaultDraw(Color.WHITE));
		b4.setRound(10);

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

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(b1);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 30));
		barLayer.addSymbol(b2);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 30));
		barLayer.addSymbol(b3);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 30));
		barLayer.addSymbol(b4);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		// LEGEND
		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Bar Effect");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY10));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		proj.registerPlugin(lgendL);

		// ZOOM
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
