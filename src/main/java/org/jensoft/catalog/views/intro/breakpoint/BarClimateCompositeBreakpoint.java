/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.Alpha;
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
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.Pie.PieNature;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieCompoundEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieReflectionEffect;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbol.SymbolInflate;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.PointSymbol;
import org.jensoft.core.plugin.symbol.PointSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.SymbolToolkit;
import org.jensoft.core.plugin.symbol.painter.axis.BarDefaultAxisLabel;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect4;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.symbol.painter.point.PointSymbolImage;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.sharedicon.SharedIcon;
import org.jensoft.core.sharedicon.weather.Weather;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class BarClimateCompositeBreakpoint extends DemoBreakPoint {

	public BarClimateCompositeBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install a composite between some kind of symbols\n and pie.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Symbols and Pie Composite");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	class ClimateMount extends Thread {

		private View climateView;

		public ClimateMount(View climateView) {
			this.climateView = climateView;
		}

		@Override
		public void run() {
			try {

				Thread.sleep(500);
				Color blue = PetalPalette.PETAL1_HC;
				Color green = PetalPalette.PETAL2_HC;
				Color orange = PetalPalette.PETAL3_HC;
				Font f =  new Font("Dialog", Font.PLAIN, 12);
				// global legend
				TitleLegend legend = new TitleLegend("Bordeaux Climate");
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.YELLOW2));
				legend.setFont(f);
				legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Rigth));

				Thread.sleep(500);
				climateView.repaintView();

				// RAINFALL PROJ
				Projection projRainfall = new Projection.Linear(0, 0, 0, 300);
				projRainfall.setName("compatible vertical bar proj");
				climateView.registerProjection(projRainfall);

				TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
				legendPlugin.addLegend(legend);
				// legendPlugin.addLegend(rainfalllegend);
				// legendPlugin.addLegend(sunshinelegend);
				projRainfall.registerPlugin(legendPlugin);

				projRainfall.setThemeColor(RosePalette.MELON);
				projRainfall.registerPlugin(new OutlinePlugin());

				
				AxisMetricsPlugin.ModeledMetrics eastMetrics = new AxisMetricsPlugin.ModeledMetrics.E();
				eastMetrics.setTextFont(f);
				eastMetrics.setTextColor(blue);
				eastMetrics.setMarkerColor(blue);

				projRainfall.registerPlugin(eastMetrics);
				projRainfall.setThemeColor(RosePalette.MELON);

				Thread.sleep(500);
				climateView.repaintView();

				StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(0, 30);
				StripePalette bp = new StripePalette();
				bp.addPaint(new Color(255, 255, 255, 40));
				bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
				bandLayout.setStripePalette(bp);
				bandLayout.setAlpha(0.3f);
				projRainfall.registerPlugin(bandLayout);

				climateView.repaintDevice();
				Thread.sleep(500);

				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 30, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(59, 89, 152, 100));
				projRainfall.registerPlugin(gridLayout);

				climateView.repaintDevice();
				Thread.sleep(500);

				FreeGrid freeGrid = new GridPlugin.FreeGrid(GridOrientation.Horizontal);

				freeGrid.getGridManager().addGrid(60, "60 mm", new Alpha(blue, 150), 0.9f);
				freeGrid.getGridManager().addGrid(120, "120 mm", new Alpha(blue, 150), 0.9f);
				freeGrid.getGridManager().addGrid(180, "180 mm", new Alpha(blue, 150), 0.9f);
				freeGrid.getGridManager().setGridStroke(new BasicStroke(0.8f));

				projRainfall.registerPlugin(freeGrid);

				climateView.repaintDevice();
				Thread.sleep(500);

				Font f14 =  new Font("Dialog", Font.PLAIN, 14);
				
				TitleLegend rainfalllegend = new TitleLegend("Rainfall");
				rainfalllegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, blue));
				rainfalllegend.setFont(f14);
				rainfalllegend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.5f, LegendAlignment.Middle));
				legendPlugin.addLegend(rainfalllegend);

				climateView.repaintDevice();
				Thread.sleep(500);

				double[] values = { 49.9, 71.5, 106.4, 129.2, 144, 176, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 };

				// create symbols for group 1
				BarSymbol b1 = SymbolToolkit.createBarSymbol("January", blue, SymbolInflate.Ascent, 49.9);
				BarSymbol b2 = SymbolToolkit.createBarSymbol("Februry", blue, SymbolInflate.Ascent, 71.5);
				BarSymbol b3 = SymbolToolkit.createBarSymbol("March", blue, SymbolInflate.Ascent, 106.4);
				BarSymbol b4 = SymbolToolkit.createBarSymbol("April", blue, SymbolInflate.Ascent, 129.2);
				BarSymbol b5 = SymbolToolkit.createBarSymbol("May", blue, SymbolInflate.Ascent, 144);
				BarSymbol b6 = SymbolToolkit.createBarSymbol("June", blue, SymbolInflate.Ascent, 176);
				BarSymbol b7 = SymbolToolkit.createBarSymbol("July", blue, SymbolInflate.Ascent, 135.6);
				BarSymbol b8 = SymbolToolkit.createBarSymbol("August", blue, SymbolInflate.Ascent, 148.5);
				BarSymbol b9 = SymbolToolkit.createBarSymbol("September", blue, SymbolInflate.Ascent, 216.4);
				BarSymbol b10 = SymbolToolkit.createBarSymbol("October", blue, SymbolInflate.Ascent, 194.1);
				BarSymbol b11 = SymbolToolkit.createBarSymbol("November", blue, SymbolInflate.Ascent, 95.6);
				BarSymbol b12 = SymbolToolkit.createBarSymbol("December", blue, SymbolInflate.Ascent, 54.4);

				BarSymbol[] rainfalls = { b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12 };

				// or use bar factory
				// BarFactory.createGroup(nameSymbol, base, thickness);
				// BarFactory.createGroup(nameSymbol, base, thickness, round)
				// BarFactory.createGroup(nameSymbol, base, thickness, barDraw,
				// barFill,
				// barEffect);

				// create a group for properties
				BarSymbolGroup group1 = new BarSymbolGroup("G1");
				group1.setBase(0);
				group1.setThickness(25);
				group1.setRound(8);
				group1.setMorpheStyle(MorpheStyle.Round);
				group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
				group1.setBarFill(new BarFill1());
				group1.setBarEffect(new BarEffect4());

				SymbolPlugin rainFallSymbolPlugin = new SymbolPlugin();
				rainFallSymbolPlugin.setNature(SymbolNature.Vertical);
				rainFallSymbolPlugin.setPriority(100);
				projRainfall.registerPlugin(rainFallSymbolPlugin);

				BarSymbolLayer rainFallLayer = new BarSymbolLayer();
				rainFallSymbolPlugin.addLayer(rainFallLayer);

				rainFallLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
				rainFallLayer.addSymbol(group1);
				rainFallLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
				// lay out group symbol in view

				for (int i = 0; i < rainfalls.length; i++) {
					// add symbol
					// double value = rainfalls[i].getValue();
					rainfalls[i].setAscentValue(0);// reset to base for inflate
													// animation
					group1.addSymbol(rainfalls[i]);
					// rainfalls[i].inflate(value, 10, 50, 20);
					// Thread.sleep(500);
					// climateView.repaintView();
					// add strut after symbol exclude last symbol
					if (i < 11) {
						group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 20));
					}
				}
				Thread.sleep(500);
				climateView.repaintView();

				for (int j = 0; j < rainfalls.length; j++) {

					rainfalls[j].inflate(values[j], 0, 150, 20);
					Thread.sleep(100);

				}
				climateView.repaintDevice();

				
				// create axis label
				for (int i = 0; i < rainfalls.length; i++) {
					BarDefaultAxisLabel al1 = new BarDefaultAxisLabel("Jan", Color.WHITE);
					al1.setFont(f);
					al1.setTextColor(ColorPalette.BLACK);
					al1.setText(rainfalls[i].getSymbol().substring(0, 3));
					rainfalls[i].setAxisLabel(al1);
					// BarSymbolRelativeLabel rl = new BarSymbolRelativeLabel(
					// VerticalAlignment.SouthTop, HorizontalAlignment.Middle,
					// Color.WHITE, new Alpha(TangoPalette.SKYBLUE3, 80),
					// Color.BLACK);
					// rl.setFont(InputFonts.getNeuropol(12));
					// rl.setTextColor(NanoChromatique.BLACK);
					// rl.setText(rainfalls[i].getSymbol().substring(0, 3));
					// rl.setTextPaddingY(1);
					// rl.setOffsetY(10);
					// rainfalls[i].setBarLabel(rl);
					Thread.sleep(100);
					climateView.repaintPart(ViewPart.South);
				}

				TitleLegend sunshinelegend = new TitleLegend("Temperature");
				sunshinelegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, orange));
				sunshinelegend.setFont(f14);
				sunshinelegend.setConstraints(new TitleLegendConstraints(LegendPosition.West, 0.5f, LegendAlignment.Middle));

				legendPlugin.addLegend(sunshinelegend);

				climateView.repaintView();
				Thread.sleep(200);

				// curve symbol
				Projection projSunshine = new Projection.Linear(0, 0, 0, 30);
				climateView.registerProjection(projSunshine);

				// metrics for sunshine proj
				AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				westMetrics.setTextFont(f);
				
				westMetrics.setTextColor(orange);
				westMetrics.setMarkerColor(orange);
				projSunshine.registerPlugin(westMetrics);

				climateView.repaintView();
				Thread.sleep(200);

				SymbolPlugin sunshineSymbolPlugin = new SymbolPlugin();
				sunshineSymbolPlugin.setNature(SymbolNature.Vertical);
				sunshineSymbolPlugin.setPriority(100);
				projSunshine.registerPlugin(sunshineSymbolPlugin);

				PointSymbolLayer temperatureLayer = new PointSymbolLayer();
				sunshineSymbolPlugin.addLayer(temperatureLayer);

				PointSymbol ps1 = new PointSymbol(7);
				PointSymbol ps2 = new PointSymbol(6.9);
				PointSymbol ps3 = new PointSymbol(9.5);
				PointSymbol ps4 = new PointSymbol(14.5);
				PointSymbol ps5 = new PointSymbol(18.2);
				PointSymbol ps6 = new PointSymbol(21.5);
				PointSymbol ps7 = new PointSymbol(25.2);
				PointSymbol ps8 = new PointSymbol(26.5);
				PointSymbol ps9 = new PointSymbol(23.3);
				PointSymbol ps10 = new PointSymbol(18.3);
				PointSymbol ps11 = new PointSymbol(13.9);
				PointSymbol ps12 = new PointSymbol(9.6);

				PointSymbol[] sunshines = { ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12 };

				temperatureLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));
				for (int i = 0; i < sunshines.length; i++) {
					// sunshines[i].setThickness(25);//virtual thickness for lay
					// out
					ImageIcon ic = SharedIcon.getWeather(Weather.WEATHER_SUN_16);
					PointSymbolImage ps = new PointSymbolImage(ic.getImage());
					sunshines[i].setVisible(false);
					sunshines[i].addPointSymbolPainter(ps);
					temperatureLayer.addSymbol(sunshines[i]);
					if (i < 11) {
						temperatureLayer.addSymbol(SymbolComponent.createStrut(PointSymbol.class, 45));
					}
				}
				temperatureLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));

				for (int i = 0; i < sunshines.length; i++) {
					sunshines[i].setVisible(true);
					climateView.repaintDevice();
					Thread.sleep(80);
				}
				climateView.repaintDevice();
				Thread.sleep(300);
				//
				// LegendPlugin legendPlugin = new LegendPlugin();
				// legendPlugin.addLegend(legend);
				// legendPlugin.addLegend(rainfalllegend);
				// legendPlugin.addLegend(sunshinelegend);
				// proj2DRainfall.registerPlugin(legendPlugin);
				//
				// // register zoom tool (zoom only into scalar dimension and
				// not in symbol
				// // dimension)
				// // ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
				// // barView.registerPlugin(zoomWheel);
				//
				// PIE
				Pie pie = PieToolkit.createPie("pie", 30);

				pie.setStartAngleDegree(10);

				// slices
				PieSlice s1 = PieToolkit.createSlice("s1", ColorPalette.alpha(orange, 240), 25, 0);
				PieSlice s2 = PieToolkit.createSlice("s2", new Color(240, 240, 240, 240), 75, 0);

				PieToolkit.pushSlices(pie, s1, s2);

				pie.setPieNature(PieNature.Device);
				pie.setCenterX(50);
				pie.setCenterY(80);

				PiePlugin piePlugin = new PiePlugin();
				piePlugin.setPriority(100);// paint last
				piePlugin.addPie(pie);

				projRainfall.registerPlugin(piePlugin);

				climateView.repaintDevice();
				Thread.sleep(200);

				s1.setDivergence(10);

				climateView.repaintDevice();
				Thread.sleep(200);

				PieLinearEffect fx1 = new PieLinearEffect();
				pie.setPieEffect(fx1);

				climateView.repaintDevice();
				Thread.sleep(200);

				PieReflectionEffect fx2 = new PieReflectionEffect();
				PieCompoundEffect c = new PieCompoundEffect(fx1, fx2);
				fx1.setOffsetRadius(2);
				pie.setPieEffect(c);

				climateView.repaintDevice();
				Thread.sleep(200);
				Font f10 =  new Font("Dialog", Font.PLAIN, 10);
				
				PieBorderLabel label2 = new PieBorderLabel();
				label2.setLabelFont(f10);
				label2.setFillColor(Color.BLACK);
				label2.setOutlineColor(Color.WHITE);
				label2.setLinkColor(Color.BLACK);
				label2.setLabelPaddingY(2);
				label2.setLabelPaddingX(4);
				label2.setOutlineRound(12);
				float[] fractions = { 0f, 0.5f, 1f };
				Color[] colors = { Color.DARK_GRAY, Color.BLACK, Color.DARK_GRAY };
				label2.setShader(fractions, colors);
				label2.setLabel("sunshine hours");
				s1.addSliceLabel(label2);

				climateView.repaintDevice();
				Thread.sleep(2000);

				climateView.unregisterProjection(projRainfall);
				climateView.unregisterProjection(projSunshine);
				climateView.repaint();
				Thread.sleep(500);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Composite", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);
				ClimateMount mount = new ClimateMount(view);
				mount.start();
				mount.join();
				view.repaint();

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
