/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.point;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.symbol.PointSymbol;
import org.jensoft.core.plugin.symbol.PointSymbolLayer;
import org.jensoft.core.plugin.symbol.PolylinePointSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.point.PointSymbolImage;
import org.jensoft.core.plugin.symbol.painter.polyline.PolylinePointSymbolDefaultPainter;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.sharedicon.SharedIcon;
import org.jensoft.core.sharedicon.marker.Marker;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class PointPolylineSymbolDemo extends View {

	@Portfolio(name = "Doc Portfolio - Polyline", width = 650, height = 250)
	public static View getPortofolio() {
		PointPolylineSymbolDemo demo = new PointPolylineSymbolDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public PointPolylineSymbolDemo() {
		super();

		Color blue = PetalPalette.PETAL1_HC;
		Color green = PetalPalette.PETAL2_HC;
		Color orange = Spectral.SPECTRAL_BLUE2;

		setPlaceHolderAxisEast(80);
		setPlaceHolderAxisWest(80);

		// curve symbol
		Projection proj = new Projection.Linear(0, 0, 0, 30);
		registerProjection(proj);

		proj.registerPlugin(new OutlinePlugin());

		// metrics 
		Font f = new Font("Dialog", Font.PLAIN, 12);
		AxisMetricsPlugin.ModeledMetrics sunshineMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		sunshineMetrics.setTextFont(f);
		sunshineMetrics.setTextColor(orange);
		sunshineMetrics.setMarkerColor(orange);
		proj.registerPlugin(sunshineMetrics);

		SymbolPlugin symbolPlugin = new SymbolPlugin();
		symbolPlugin.setNature(SymbolNature.Vertical);
		symbolPlugin.setPriority(100);
		proj.registerPlugin(symbolPlugin);

		PointSymbolLayer pointLayer = new PointSymbolLayer();
		symbolPlugin.addLayer(pointLayer);

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

		PointSymbol[] pointSymbols = { ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12 };

		/**
		 * polyline does not contribute itself to layer constraints solving for
		 * location, it only solve by its points elements coordinates which have
		 * to solved in the same layer. you can add without contrainst at any
		 * time. Assume that all registered point in polyline are registered in
		 * point layer.
		 */

		PolylinePointSymbol polylineSymbol = new PolylinePointSymbol("polyline");

		for (int i = 0; i < pointSymbols.length; i++) {
			polylineSymbol.addSymbol(pointSymbols[i]);
		}
		pointLayer.addSymbol(polylineSymbol);
		PolylinePointSymbolDefaultPainter ppsp = new PolylinePointSymbolDefaultPainter();
		ppsp.setPolylineColor(NanoChromatique.ORANGE);
		polylineSymbol.setPolylinePainter(ppsp);

		pointLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));
		for (int i = 0; i < pointSymbols.length; i++) {
			// sunshines[i].setThickness(25);//virtual thickness for lay out
			ImageIcon ic = SharedIcon.getMarker(Marker.MARKER_SQUARED_BLUE);
			PointSymbolImage psi = new PointSymbolImage(ic.getImage());
			psi.setOffsetX(-ic.getIconWidth() / 2);
			psi.setOffsetY(-ic.getIconHeight());
			pointSymbols[i].addPointSymbolPainter(psi);
			pointLayer.addSymbol(pointSymbols[i]);
			if (i < 11) {
				pointLayer.addSymbol(SymbolComponent.createStrut(PointSymbol.class, 32));
			}
		}
		pointLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));

		// global legend
		
		TitleLegend legend = new TitleLegend("Polyline Symbols");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.YELLOW2));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Rigth));

	}

}
