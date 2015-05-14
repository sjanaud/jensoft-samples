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
import org.jensoft.core.graphics.Shader;
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
import org.jensoft.core.plugin.symbol.PolylinePointSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.SymbolToolkit;
import org.jensoft.core.plugin.symbol.painter.axis.BarDefaultAxisLabel;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect4;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.symbol.painter.point.PointSymbolDebugGeometryPainter;
import org.jensoft.core.plugin.symbol.painter.point.PointSymbolImage;
import org.jensoft.core.plugin.symbol.painter.polyline.PolylinePointSymbolDefaultPainter;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.sharedicon.SharedIcon;
import org.jensoft.core.sharedicon.weather.Weather;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>ClimateDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show how to create composite view using different kind of plugin.")
public class Climate extends View {

	/**
	 * create a demo for climate type chart
	 */
	public Climate() {
		super();

		Color blue = PetalPalette.PETAL1_HC;
		Color green = PetalPalette.PETAL2_HC;
		Color orange = PetalPalette.PETAL3_HC;

		setPlaceHolderAxisEast(80);
		setPlaceHolderAxisWest(80);

		// RAINFALL PROJECTION
		Projection projRainfall = new Projection.Linear(0, 0, 0, 300);
		projRainfall.setName("compatible vertical bar");
		registerProjection(projRainfall);

		Font f12 =  new Font("Dialog", Font.PLAIN, 12);
		Font f14 =  new Font("Dialog", Font.PLAIN, 14);
		
		projRainfall.setThemeColor(RosePalette.MELON);
		projRainfall.registerPlugin(new OutlinePlugin());

		AxisMetricsPlugin.ModeledMetrics rainMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		rainMetrics.setTextFont(f12);
		
		rainMetrics.setTextColor(blue);
		rainMetrics.setMarkerColor(blue);

		projRainfall.registerPlugin(rainMetrics);
		projRainfall.setThemeColor(RosePalette.MELON);

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
		// create axis label
		for (int i = 0; i < rainfalls.length; i++) {
			BarDefaultAxisLabel rl = new BarDefaultAxisLabel("Jan", Color.WHITE);
			// BarSymbolRelativeLabel rl = new BarSymbolRelativeLabel(
			// VerticalAlignment.SouthTop, HorizontalAlignment.Middle,
			// Color.WHITE, new Alpha(TangoPalette.SKYBLUE3, 80),
			// Color.BLACK);
			rl.setFont(f12);
			rl.setTextColor(ColorPalette.WHITE);
			rl.setText(rainfalls[i].getSymbol().substring(0, 3));
			rl.setTextPaddingY(1);
			rl.setOffsetY(10);
			// rainfalls[i].setBarLabel(rl);
			rainfalls[i].setAxisLabel(rl);
		}

		// or use bar factory
		// BarFactory.createGroup(nameSymbol, base, thickness);
		// BarFactory.createGroup(nameSymbol, base, thickness, round)
		// BarFactory.createGroup(nameSymbol, base, thickness, barDraw, barFill,
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

		// lay out group symbol in view

		for (int i = 0; i < rainfalls.length; i++) {
			group1.addSymbol(rainfalls[i]);
			if (i < 11) {
				group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 20));
			}
		}

		SymbolPlugin rainFallSymbolPlugin = new SymbolPlugin();
		rainFallSymbolPlugin.setNature(SymbolNature.Vertical);
		rainFallSymbolPlugin.setPriority(100);
		projRainfall.registerPlugin(rainFallSymbolPlugin);

		BarSymbolLayer rainFallLayer = new BarSymbolLayer();
		rainFallSymbolPlugin.addLayer(rainFallLayer);

		rainFallLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		rainFallLayer.addSymbol(group1);
		rainFallLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 30);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		projRainfall.registerPlugin(stripePlugin);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 30, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		projRainfall.registerPlugin(gridLayout);

		FreeGrid freeGrid = new GridPlugin.FreeGrid(GridOrientation.Horizontal);
		freeGrid.getGridManager().addGrid(60, "60 mm", new Alpha(blue, 150), 0.9f);
		freeGrid.getGridManager().addGrid(120, "120 mm", new Alpha(blue, 150), 0.9f);
		freeGrid.getGridManager().addGrid(180, "180 mm", new Alpha(blue, 150), 0.9f);
		freeGrid.getGridManager().setGridStroke(new BasicStroke(0.8f));
		projRainfall.registerPlugin(freeGrid);

		// curve symbol
		Projection projSunshine = new Projection.Linear(0, 0, 0, 30);
		registerProjection(projSunshine);

		// metrics for sunshine
		AxisMetricsPlugin.ModeledMetrics sunshineMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		sunshineMetrics.setTextFont(f12);
		sunshineMetrics.setTextColor(orange);
		sunshineMetrics.setMarkerColor(orange);
		projSunshine.registerPlugin(sunshineMetrics);

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

		/**
		 * polyline does not contribute itself to layer constraints solving for
		 * location, it only solve by its points elements coordinates which have
		 * to solved in the same layer.�
		 */

		PolylinePointSymbol polylineSymbol = new PolylinePointSymbol("sunshine polyline");
		for (int i = 0; i < sunshines.length; i++) {
			polylineSymbol.addSymbol(sunshines[i]);
		}
		temperatureLayer.addSymbol(polylineSymbol);

		polylineSymbol.setPolylinePainter(new PolylinePointSymbolDefaultPainter(TangoPalette.BUTTER1));

		temperatureLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));
		for (int i = 0; i < sunshines.length; i++) {

			ImageIcon ic = SharedIcon.getWeather(Weather.WEATHER_SUN_16);

			sunshines[i].addPointSymbolPainter(new PointSymbolImage(ic.getImage(), -8, -8));
			sunshines[i].addPointSymbolPainter(new PointSymbolDebugGeometryPainter());

			temperatureLayer.addSymbol(sunshines[i]);
			if (i < 11) {
				temperatureLayer.addSymbol(SymbolComponent.createStrut(PointSymbol.class, 45));
			}
		}
		temperatureLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));

		// global legend
		TitleLegend legend = new TitleLegend("Bordeaux Climate");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.YELLOW2));
		legend.setFont(f12);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Rigth));

		TitleLegend rainfalllegend = new TitleLegend("Rainfall");
		rainfalllegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, blue));
		rainfalllegend.setFont(f14);
		rainfalllegend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.5f, LegendAlignment.Middle));

		TitleLegend sunshinelegend = new TitleLegend("Temperature");
		sunshinelegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, orange));
		sunshinelegend.setFont(f14);
		sunshinelegend.setConstraints(new TitleLegendConstraints(LegendPosition.West, 0.5f, LegendAlignment.Middle));

		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		legendPlugin.addLegend(legend);
		legendPlugin.addLegend(rainfalllegend);
		legendPlugin.addLegend(sunshinelegend);
		projRainfall.registerPlugin(legendPlugin);

		// pie
		Pie pie = PieToolkit.createPie("pie", 30);
		PieLinearEffect fx1 = new PieLinearEffect();
		PieReflectionEffect fx2 = new PieReflectionEffect();
		PieCompoundEffect c = new PieCompoundEffect(fx1, fx2);
		fx1.setOffsetRadius(2);
		pie.setPieEffect(c);
		pie.setStartAngleDegree(10);

		// slices
		PieSlice s1 = PieToolkit.createSlice("s1", ColorPalette.alpha(orange, 240), 25, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", new Color(240, 240, 240, 240), 75, 0);
		s1.setDivergence(10);

		PieBorderLabel label2 = new PieBorderLabel();
		label2.setLabelFont(f12);
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

		PieToolkit.pushSlices(pie, s1, s2);

		pie.setPieNature(PieNature.Device);
		pie.setCenterX(50);
		pie.setCenterY(80);

		PiePlugin piePlugin = new PiePlugin();
		piePlugin.setPriority(100);// paint last
		piePlugin.addPie(pie);

		projRainfall.registerPlugin(piePlugin);

	}

	/**
	 * create climate view portfolio view
	 * 
	 * @return portfolio view
	 */
	@Portfolio(name = "ClimateDemo", width = 800, height = 250)
	public static View getPortofolio() {
		Climate demo = new Climate();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
