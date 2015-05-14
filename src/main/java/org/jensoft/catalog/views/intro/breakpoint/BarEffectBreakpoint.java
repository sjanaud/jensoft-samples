/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
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
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class BarEffectBreakpoint extends DemoBreakPoint {

	public BarEffectBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and show bar effects.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Bar Effect");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	/**
	 * Un install all plug ins which has been registered in this demo
	 * 
	 * @author Sébastien Janaud
	 */
	class Desinstall extends Thread {

		private Projection proj;

		public Desinstall(Projection proj) {
			this.proj = proj;
		}

		@Override
		public void run() {
			try {
				synchronized (this) {

					OutlinePlugin o = new OutlinePlugin(Color.BLACK);
					proj.registerPlugin(o);
					view.repaint();

					List<AbstractPlugin> plugins = proj.getPluginRegistry();
					System.out.println("plugins size " + plugins.size());
					int size = plugins.size();
					List<AbstractPlugin> dplugins = new ArrayList<AbstractPlugin>();
					for (int i = size - 1; i >= 0; i--) {
						if (plugins.get(i) != jenSoftDemoExecutor.getMessagePlugin() && plugins.get(i) != o) {
							dplugins.add(plugins.get(i));
						}
					}

					int rsize = dplugins.size();
					for (int j = rsize - 1; j >= 0; j--) {
						System.out.println("unregister : " + dplugins.get(j).getClass() + "@" + dplugins.get(j).getName());
						proj.unregisterPlugin(dplugins.get(j));
						view.repaintView();
						wait(200);
					}
					o.deflateOutline(0).join();
					proj.unregisterPlugin(o);
					view.repaintView();

				}
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Bar Effect", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(1000);

				view.repaint();

				Projection barWindow = new Projection.Linear(0, 0, -42, 260);
				view.registerProjection(barWindow);
				OutlinePlugin p = new OutlinePlugin(RosePalette.MANDARIN);
				barWindow.registerPlugin(p);

				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				
				AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				westMetrics.setTextFont(f12);
				
				westMetrics.setMarkerColor(RosePalette.CALYPSOBLUE.brighter());
				westMetrics.setTextColor(RosePalette.CALYPSOBLUE.brighter());
				barWindow.registerPlugin(westMetrics);

				SymbolPlugin symbolPlugin = new SymbolPlugin();
				symbolPlugin.setNature(SymbolNature.Vertical);
				barWindow.registerPlugin(symbolPlugin);

				BarSymbol b1 = new BarSymbol();
				b1.setThemeColor(PetalPalette.PETAL1_LC);
				b1.setThickness(32);
				b1.setBase(-30);
				b1.setAscentValue(20);
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
				b2.setAscentValue(20);
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
				b3.setAscentValue(20);
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
				b4.setAscentValue(20);
				b4.setName("b4");
				b4.setSymbol("bar 3");
				b4.setMorpheStyle(MorpheStyle.Round);
				b4.setBarDraw(new BarDefaultDraw());
				b4.setBarFill(new BarFill1());
				b4.setBarEffect(new BarEffect4());
				b4.setBarDraw(new BarDefaultDraw(Color.WHITE));
				b4.setRound(10);

				StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(0, 30);
				StripePalette bp = new StripePalette();
				bp.addPaint(new Color(255, 255, 255, 40));
				bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
				bandLayout.setStripePalette(bp);
				bandLayout.setAlpha(0.3f);
				barWindow.registerPlugin(bandLayout);

				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 30, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(255, 255, 255, 60));
				barWindow.registerPlugin(gridLayout);

				BarSymbolLayer barLayer = new BarSymbolLayer();
				symbolPlugin.addLayer(barLayer);

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
				TitleLegend legend = new TitleLegend("Bar Effect");
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY10));
				legend.setFont(f12);
				legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
				TitleLegendPlugin lgendL = new TitleLegendPlugin();
				lgendL.addLegend(legend);
				barWindow.registerPlugin(lgendL);

				view.repaint();

				wait(300);

				b1.inflate(160, 0, 300, 20);
				b2.inflate(160, 300, 300, 20);
				b3.inflate(160, 600, 300, 20);
				b4.inflate(160, 900, 300, 20);

				wait(3000);

				b1.deflate(100, 0, 300, 20);
				b2.deflate(100, 300, 300, 20);
				b3.deflate(100, 600, 300, 20);
				b4.deflate(100, 900, 300, 20);

				wait(1800);

				b4.setVisible(false);
				view.repaint();
				wait(200);
				b3.setVisible(false);
				view.repaint();
				wait(200);
				b2.setVisible(false);
				view.repaint();
				wait(200);
				b1.setVisible(false);
				view.repaint();
				wait(200);

				wait(1000);

				Desinstall d = new Desinstall(barWindow);
				d.start();
				d.join();

				view.unregisterProjection(barWindow);
				view.repaint();
				Thread.sleep(500);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
