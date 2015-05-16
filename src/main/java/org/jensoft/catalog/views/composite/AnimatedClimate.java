/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.composite;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
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
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>AnimatedClimate</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show how to animate and create composite view using different kind of plugin")
public class AnimatedClimate extends View {

	
	private static final long serialVersionUID = 3407773311940856597L;

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new AnimatedClimate());
	}
	
	/**
	 * create animated climate demo
	 */
	public AnimatedClimate() {
		super();
		setPlaceHolderAxisEast(80);
		setPlaceHolderAxisWest(80);

		// view installer
		ClimateMount mount = new ClimateMount(this);
		mount.start();

	}

	/**
	 * <code>ClimateMount</code>
	 * 
	 * @author Sébastien Janaud
	 */
	class ClimateMount extends Thread {

		/** climate view */
		private View climateView;

		/**
		 * Climate mount
		 * 
		 * @param climateView
		 */
		public ClimateMount(View climateView) {
			this.climateView = climateView;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {

				Thread.sleep(500);
				Color blue = PetalPalette.PETAL1_HC;
				Color green = PetalPalette.PETAL2_HC;
				Color orange = PetalPalette.PETAL3_HC;
				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				Font f14 =  new Font("Dialog", Font.PLAIN, 14);
				// global legend
				TitleLegend legend = new TitleLegend("Bordeaux Climate");
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.YELLOW2));
				legend.setFont(f12);
				legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Rigth));

				Thread.sleep(500);
				climateView.repaintView();

				// rainfall projection
				Projection projRainfall = new Projection.Linear(0, 0, 0, 300);
				projRainfall.setName("compatible vertical bar");
				projRainfall.setThemeColor(RosePalette.MELON);
				climateView.registerProjection(projRainfall);

				// legend plug-in
				TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
				legendPlugin.addLegend(legend);
				projRainfall.registerPlugin(legendPlugin);

				projRainfall.setThemeColor(RosePalette.MELON);
				projRainfall.registerPlugin(new OutlinePlugin());

				AxisMetricsPlugin.ModeledMetrics eastMetrics = new AxisMetricsPlugin.ModeledMetrics.E();
				eastMetrics.setTextFont(f12);
				eastMetrics.setTextColor(blue);
				eastMetrics.setMarkerColor(blue);
				projRainfall.registerPlugin(eastMetrics);

				Thread.sleep(500);
				climateView.repaintView();

				// strip plug-in
				StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 30);
				StripePalette bp = new StripePalette();
				bp.addPaint(new Color(255, 255, 255, 40));
				bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
				stripePlugin.setStripePalette(bp);
				stripePlugin.setAlpha(0.3f);
				projRainfall.registerPlugin(stripePlugin);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(500);

				// grid plug-in
				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 30, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(255, 255, 255, 60));
				projRainfall.registerPlugin(gridLayout);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(500);

				// free grid plug-in
				FreeGrid freeGrid = new GridPlugin.FreeGrid(GridOrientation.Horizontal);
				freeGrid.getGridManager().addGrid(60, "60 mm", new Alpha(blue, 150), 0.9f);
				freeGrid.getGridManager().addGrid(120, "120 mm", new Alpha(blue, 150), 0.9f);
				freeGrid.getGridManager().addGrid(180, "180 mm", new Alpha(blue, 150), 0.9f);
				freeGrid.getGridManager().setGridStroke(new BasicStroke(0.8f));
				projRainfall.registerPlugin(freeGrid);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(500);

				// legend plug-in for rain fall
				TitleLegend rainfalllegend = new TitleLegend("Rainfall");
				rainfalllegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, blue));
				rainfalllegend.setFont(f14);
				rainfalllegend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.5f, LegendAlignment.Middle));
				legendPlugin.addLegend(rainfalllegend);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(500);

				// symbol plug-in
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
					rainfalls[i].setAscentValue(0);// reset to base for inflate
													// animation
					group1.addSymbol(rainfalls[i]);
					// add strut after symbol exclude last symbol
					if (i < 11) {
						group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 20));
					}
				}

				// tempo
				Thread.sleep(500);
				climateView.repaintView();

				// inflate symbols
				for (int j = 0; j < rainfalls.length; j++) {
					rainfalls[j].inflate(values[j], 0, 150, 20);
					Thread.sleep(100);
				}

				climateView.repaintDevice();

				// create axis label
				for (int i = 0; i < rainfalls.length; i++) {
					BarDefaultAxisLabel al1 = new BarDefaultAxisLabel("Jan", Color.WHITE);
					al1.setFont(f12);
					al1.setTextColor(ColorPalette.BLACK);
					al1.setText(rainfalls[i].getSymbol().substring(0, 3));
					rainfalls[i].setAxisLabel(al1);

					Thread.sleep(100);
					climateView.repaintPart(ViewPart.South);
				}

				// legend for sunshine
				TitleLegend sunshinelegend = new TitleLegend("Temperature");
				sunshinelegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, orange));
				sunshinelegend.setFont(f14);
				sunshinelegend.setConstraints(new TitleLegendConstraints(LegendPosition.West, 0.5f, LegendAlignment.Middle));
				legendPlugin.addLegend(sunshinelegend);

				// tempo
				climateView.repaintView();
				Thread.sleep(200);

				// curve symbol
				Projection projSunshine = new Projection.Linear(0, 0, 0, 30);
				climateView.registerProjection(projSunshine);

				AxisMetricsPlugin.ModeledMetrics sunshineMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				sunshineMetrics.setTextFont(f12);
				sunshineMetrics.setTextColor(orange);
				sunshineMetrics.setMarkerColor(orange);
				projSunshine.registerPlugin(sunshineMetrics);

				// tempo
				climateView.repaintView();
				Thread.sleep(200);

				SymbolPlugin sunshineSymbolPlugin = new SymbolPlugin();
				sunshineSymbolPlugin.setNature(SymbolNature.Vertical);
				sunshineSymbolPlugin.setPriority(100);
				projSunshine.registerPlugin(sunshineSymbolPlugin);

				// point layer
				PointSymbolLayer temperatureLayer = new PointSymbolLayer();
				sunshineSymbolPlugin.addLayer(temperatureLayer);

				// point symbols
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

				// pie
				PiePlugin piePlugin = new PiePlugin();
				piePlugin.setPriority(100);// paint last
				projRainfall.registerPlugin(piePlugin);

				Pie pie = PieToolkit.createPie("pie", 30);
				pie.setPieNature(PieNature.Device);
				pie.setCenterX(50);
				pie.setCenterY(80);
				pie.setStartAngleDegree(10);
				piePlugin.addPie(pie);

				PieSlice s1 = PieToolkit.createSlice("s1", ColorPalette.alpha(orange, 240), 25, 0);
				PieSlice s2 = PieToolkit.createSlice("s2", new Color(240, 240, 240, 240), 75, 0);

				PieToolkit.pushSlices(pie, s1, s2);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(200);

				s1.setDivergence(10);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(200);

				// effect on pie
				PieLinearEffect fx1 = new PieLinearEffect();
				pie.setPieEffect(fx1);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(200);

				// pie reflection effect
				PieReflectionEffect fx2 = new PieReflectionEffect();
				PieCompoundEffect c = new PieCompoundEffect(fx1, fx2);
				fx1.setOffsetRadius(2);
				pie.setPieEffect(c);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(200);

				// label
				PieBorderLabel pieLabel = new PieBorderLabel();
				pieLabel.setLabelFont(f12);
				pieLabel.setFillColor(Color.BLACK);
				pieLabel.setOutlineColor(Color.WHITE);
				pieLabel.setLinkColor(Color.BLACK);
				pieLabel.setLabelPaddingY(2);
				pieLabel.setLabelPaddingX(4);
				pieLabel.setOutlineRound(12);
				float[] fractions = { 0f, 0.5f, 1f };
				Color[] colors = { Color.DARK_GRAY, Color.BLACK, Color.DARK_GRAY };
				pieLabel.setShader(fractions, colors);
				pieLabel.setLabel("sunshine hours");
				s1.addSliceLabel(pieLabel);

				// tempo
				climateView.repaintDevice();
				Thread.sleep(200);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
