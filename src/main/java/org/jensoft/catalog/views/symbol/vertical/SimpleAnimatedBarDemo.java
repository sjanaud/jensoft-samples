/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.vertical;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import org.jensoft.core.catalog.nature.JenSoftView;
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
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect4;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleAnimatedBarDemo extends View {

	BarSymbol b1g1;
	BarSymbol b2g1;
	BarSymbol b3g1;

	BarSymbol b1g2;
	BarSymbol b2g2;
	BarSymbol b3g2;

	BarSymbol b1g3;
	BarSymbol b2g3;
	BarSymbol b3g3;

	public SimpleAnimatedBarDemo() {

		Projection proj = new Projection.Linear(0, 0, -42, 260);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Vertical);
		proj.registerPlugin(barPlugin);

		Color blue = PetalPalette.PETAL1_HC;
		Color green = PetalPalette.PETAL2_HC;
		Color orange = PetalPalette.PETAL3_HC;

		// Group1
		b1g1 = new BarSymbol();
		b1g1.setAscentValue(62);
		b1g1.setThemeColor(blue);
		b2g1 = new BarSymbol();
		b2g1.setAscentValue(83);
		b2g1.setThemeColor(green);
		b3g1 = new BarSymbol();
		b3g1.setAscentValue(47);
		b3g1.setThemeColor(orange);

		BarSymbolGroup group1 = new BarSymbolGroup();
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b2g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b3g1);

		group1.setSymbol("Group 1");
		group1.setName("group1");
		group1.setBase(-30);
		group1.setThickness(25);
		group1.setRound(8);
		group1.setMorpheStyle(MorpheStyle.Round);
		group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group1.setBarFill(new BarFill1());
		group1.setBarEffect(new BarEffect4());

		// Group2
		b1g2 = new BarSymbol();
		b1g2.setAscentValue(64);
		b1g2.setThemeColor(blue);

		b2g2 = new BarSymbol();
		b2g2.setAscentValue(77);
		b2g2.setThemeColor(green);

		b3g2 = new BarSymbol();
		b3g2.setAscentValue(32);
		b3g2.setThemeColor(orange);

		BarSymbolGroup group2 = new BarSymbolGroup();
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b2g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b3g2);

		group2.setBase(-30);
		group2.setThickness(25);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect4());

		// Group3
		b1g3 = new BarSymbol();
		b1g3.setAscentValue(17);
		b1g3.setThemeColor(blue);

		b2g3 = new BarSymbol();
		b2g3.setAscentValue(43);
		b2g3.setThemeColor(green);

		b3g3 = new BarSymbol();
		b3g3.setAscentValue(78);
		b3g3.setThemeColor(orange);

		BarSymbolGroup group3 = new BarSymbolGroup();
		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b2g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b3g3);

		group3.setSymbol("Group 3");
		group3.setName("group3");
		group3.setBase(-30);
		group3.setThickness(25);
		group3.setRound(8);
		group3.setMorpheStyle(MorpheStyle.Round);
		group3.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group3.setBarFill(new BarFill1());
		group3.setBarEffect(new BarEffect4());

		b1g3.setName("b4");
		b1g3.setSymbol("bar 4");
		b2g3.setName("b5");
		b2g3.setSymbol("bar 5");
		b3g3.setName("b6");
		b3g3.setSymbol("bar 6");

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
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		// LEGEND
		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Bar Animation");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY3));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		proj.registerPlugin(lgendL);

		// ZOOM WHEEL
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		/**
		 * demo animator
		 */
		class InflateDefateDemo extends Thread {

			public InflateDefateDemo() {
			}

			Random r1 = new Random();
			Random r2 = new Random();

			int delay = 50;
			int delta = 20;

			private void animate(BarSymbol s) {
				int i = r1.nextInt(2);
				int j = r2.nextInt(delta);

				if (i == 0) {
					if (s.getValue() < 240) {
						s.inflate(j, 0, delay, 20);
					}
				} else {
					if (s.getValue() > 20) {
						s.deflate(j, 0, delay, 20);
					}
				}
			}

			@Override
			public void run() {
				try {

					b1g1.setAscentValue(10);
					b2g1.setAscentValue(10);
					b3g1.setAscentValue(10);

					b1g2.setAscentValue(10);
					b2g2.setAscentValue(10);
					b3g2.setAscentValue(10);

					b1g3.setAscentValue(10);
					b2g3.setAscentValue(10);
					b3g3.setAscentValue(10);

					Thread.sleep(1000);

					// inflate all with delay
					b1g1.inflate(160, 0, 300, 20);
					b2g1.inflate(160, 50, 300, 20);
					b3g1.inflate(160, 100, 300, 20);

					b1g2.inflate(160, 150, 300, 20);
					b2g2.inflate(160, 200, 300, 20);
					b3g2.inflate(160, 250, 300, 20);

					b1g3.inflate(160, 300, 300, 20);
					b2g3.inflate(160, 350, 300, 20);
					b3g3.inflate(160, 450, 300, 20);

					Thread.sleep(1000);

					// deflate all together
					b1g1.deflate(100, 0, 300, 20);
					b2g1.deflate(100, 0, 300, 20);
					b3g1.deflate(100, 0, 300, 20);

					b1g2.deflate(100, 0, 300, 20);
					b2g2.deflate(100, 0, 300, 20);
					b3g2.deflate(100, 0, 300, 20);

					b1g3.deflate(100, 0, 300, 20);
					b2g3.deflate(100, 0, 300, 20);
					b3g3.deflate(100, 0, 300, 20);

					Thread.sleep(1000);

					while (true) {

						animate(b1g1);
						animate(b2g1);
						animate(b3g1);

						animate(b1g2);
						animate(b2g2);
						animate(b3g2);

						animate(b1g3);
						animate(b2g3);
						animate(b3g3);

						Thread.sleep(100);

					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

		InflateDefateDemo demoThread = new InflateDefateDemo();
		demoThread.start();

	}

}
