/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
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
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class BarAnimatorBreakpoint extends DemoBreakPoint {

	public BarAnimatorBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install bar symbol.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Bar Symbol");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	BarSymbol b1g1;
	BarSymbol b2g1;
	BarSymbol b3g1;

	BarSymbol b1g2;
	BarSymbol b2g2;
	BarSymbol b3g2;

	BarSymbol b1g3;
	BarSymbol b2g3;
	BarSymbol b3g3;

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
		public void interrupt() {
			b1g1.interruptInflating();
			b2g1.interruptInflating();
			b3g1.interruptInflating();

			b1g2.interruptInflating();
			b2g2.interruptInflating();
			b3g2.interruptInflating();

			b1g3.interruptInflating();
			b2g3.interruptInflating();
			b3g3.interruptInflating();

			super.interrupt();
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
				long flagTime = 1000 * 10;
				long startMillis = System.currentTimeMillis();
				boolean flagrun = true;
				while (flagrun || !interrupted()) {
					long currentDuration = System.currentTimeMillis() - startMillis;
					if (currentDuration > flagTime) {
						flagrun = false;
						interrupt();
					}
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
				Thread.currentThread().interrupt();
			}

		}
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Bar", 500, null, PushingBehavior.Fast, f, Color.BLACK);
				jenSoftDemoExecutor.getDefaultProjection().setVisible(false);
				view.repaint();
				wait(500);
				view.repaint();

				Projection barProj = new Projection.Linear(0, 0, -42, 260);
				view.registerProjection(barProj);

				barProj.registerPlugin(new OutlinePlugin(RosePalette.MANDARIN));

				Font f2 =  new Font("Dialog", Font.PLAIN, 14);
				AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				westMetrics.setTextFont(f2);
				
				barProj.registerPlugin(westMetrics);

				SymbolPlugin symbolPlugin = new SymbolPlugin();
				symbolPlugin.setNature(SymbolNature.Vertical);
				barProj.registerPlugin(symbolPlugin);
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

				StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(0, 30);
				StripePalette bp = new StripePalette();
				bp.addPaint(new Color(255, 255, 255, 40));
				bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
				bandLayout.setStripePalette(bp);
				bandLayout.setAlpha(0.3f);
				barProj.registerPlugin(bandLayout);

				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 30, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(59, 89, 152, 100));
				barProj.registerPlugin(gridLayout);

				BarSymbolLayer barLayer = new BarSymbolLayer();

				barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
				barLayer.addSymbol(group1);
				barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
				barLayer.addSymbol(group2);
				barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
				barLayer.addSymbol(group3);
				barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

				symbolPlugin.addLayer(barLayer);

				view.repaint();

				InflateDefateDemo demoThread = new InflateDefateDemo();
				demoThread.start();
				demoThread.join();

				view.unregisterProjection(barProj);
				view.repaint();
				Thread.sleep(500);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
