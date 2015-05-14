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
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
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
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.StackedBarSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.axis.BarDefaultAxisLabel;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect1;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill2;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class BarAxisLabelBreakpoint extends DemoBreakPoint {

	public BarAxisLabelBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and show stacked bar with axis label.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Axis Label");
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
				view.getWidgetPlugin().pushMessage("Install Bar Stacked", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(1000);

				view.repaint();
				view.setPlaceHolderAxisSouth(100);
				view.repaint();

				Projection proj = new Projection.Linear(0, 0, -10, 120);
				view.registerProjection(proj);

				// OUTLINE
				proj.registerPlugin(new OutlinePlugin());
				view.repaint();
				wait(500);
				// LEGEND
				TitleLegend legend = new TitleLegend("Bar Axis Label");
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.COPPER2));
				Font f2 =  new Font("Dialog", Font.PLAIN, 14);
				legend.setFont(f2);
				legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));
				TitleLegendPlugin legendPlg = new TitleLegendPlugin();
				legendPlg.addLegend(legend);
				proj.registerPlugin(legendPlg);
				view.repaint();
				wait(500);

				// AXIS IN THE SCALAR DIMENSION
				AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				westMetrics.setTextFont(f2);
				
				proj.registerPlugin(westMetrics);
				view.repaint();
				wait(500);

				StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(-30, 20);
				StripePalette bp = new StripePalette();

				float[] fractions = { 0f, 0.5f, 1f };
				// Color[] colors ={new Color(255,255,255,150),new
				// Color(255,255,255,80),new Color(255,255,255,150)};
				Color c = RosePalette.AMETHYST;
				Color[] colors = { ColorPalette.alpha(c, 200), ColorPalette.alpha(c, 80), ColorPalette.alpha(c, 200) };

				bp.addPaint(fractions, colors);

				bp.addPaint(TexturePalette.getTriangleCarbonFiber());
				bandLayout.setStripePalette(bp);
				bandLayout.setAlpha(0.1f);
				proj.registerPlugin(bandLayout);
				view.repaint();
				wait(500);

				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(-30, 20, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(59, 89, 152, 100));
				proj.registerPlugin(gridLayout);

				view.repaint();
				wait(500);

				// CREATE BAR PLUGIN AND SYMBOL LAY OUT
				SymbolPlugin barPlugin = new SymbolPlugin();
				barPlugin.setNature(SymbolNature.Vertical);
				proj.registerPlugin(barPlugin);

				// affect a priority to bar plugin layer (paint as the last
				// layer)
				barPlugin.setPriority(100);
				BarSymbolLayer barLayer = new BarSymbolLayer();
				barPlugin.addLayer(barLayer);
				view.repaint();
				wait(500);

				BarSymbolGroup group1 = new BarSymbolGroup("G1");
				group1.setBase(0);
				group1.setThickness(36);
				group1.setRound(8);
				group1.setMorpheStyle(MorpheStyle.Round);
				group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
				group1.setBarFill(new BarFill2());
				group1.setBarEffect(new BarEffect1());

				barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
				barLayer.addSymbol(group1);
				barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

				StackedBarSymbol b1g1 = new StackedBarSymbol("b1g1");
				b1g1.setAscentValue(20);
				b1g1.addStack("s1", FilPalette.COPPER1, 12);
				b1g1.addStack("s2", FilPalette.COPPER2, 20);
				b1g1.addStack("s3", FilPalette.COPPER3, 40);

				// symbol 2
				StackedBarSymbol b2g1 = new StackedBarSymbol("b2g1");
				b2g1.setAscentValue(20);
				b2g1.addStack("s1", FilPalette.COPPER1, 20);
				b2g1.addStack("s2", FilPalette.COPPER2, 40);
				b2g1.addStack("s3", FilPalette.COPPER3, 20);

				// lay out symbol in group 1
				group1.addSymbol(b1g1);
				group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
				group1.addSymbol(b2g1);

				view.repaint();
				wait(500);

				b1g1.inflate(60, 0, 300, 20);
				b2g1.inflate(75, 300, 300, 20);

				/***
				 * SYMBOL AXIS LABEL GROUP 1
				 */
				Font f10 =  new Font("Dialog", Font.PLAIN, 10);
				
				// axis label for symbol 1
				BarDefaultAxisLabel axisLabelBar1Group1 = new BarDefaultAxisLabel("b1 G1", Color.BLACK);
				axisLabelBar1Group1.setFont(f10);
				axisLabelBar1Group1.setFillColor(FilPalette.COPPER1);
				axisLabelBar1Group1.setDrawColor(Color.WHITE);
				axisLabelBar1Group1.setTextColor(Color.WHITE);
				axisLabelBar1Group1.setText("Symbol 1");
				axisLabelBar1Group1.setTextPaddingY(1);
				axisLabelBar1Group1.setOffsetY(10);
				b1g1.setAxisLabel(axisLabelBar1Group1);
				view.repaint();
				wait(500);
				// axis label for symbol 2
				BarDefaultAxisLabel axisLabelBar2Group1 = new BarDefaultAxisLabel("b2 G1", Color.BLACK);
				axisLabelBar2Group1.setFont(f10);
				axisLabelBar2Group1.setFillColor(FilPalette.COPPER1);
				axisLabelBar2Group1.setDrawColor(Color.WHITE);
				axisLabelBar2Group1.setTextColor(Color.WHITE);
				axisLabelBar2Group1.setText("Symbol 2");
				axisLabelBar2Group1.setTextPaddingY(1);
				axisLabelBar2Group1.setOffsetY(30);
				b2g1.setAxisLabel(axisLabelBar2Group1);
				view.repaint();
				wait(500);
				// axis label for group
				BarDefaultAxisLabel axisLabelGroup1 = new BarDefaultAxisLabel("Group1", Color.BLACK);
				axisLabelGroup1.setDrawColor(Color.BLACK);
				axisLabelGroup1.setFillColor(FilPalette.COPPER3);
				axisLabelGroup1.setDrawColor(FilPalette.COPPER1);
				axisLabelGroup1.setTextColor(Color.WHITE);
				axisLabelGroup1.setText("Group 1");
				axisLabelGroup1.setFont(f10);
				axisLabelGroup1.setTextPaddingY(1);
				axisLabelGroup1.setOffsetY(50);

				group1.setAxisLabel(axisLabelGroup1);
				view.repaint();
				wait(1500);

				view.unregisterProjection(proj);
				view.repaint();
				Thread.sleep(500);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
