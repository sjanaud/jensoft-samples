/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.radar;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.GlyphMetricMarkerPainter;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.glyphmetrics.painter.marker.TicTacMarker;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.radar.DimensionMetrics;
import org.jensoft.core.plugin.radar.Radar;
import org.jensoft.core.plugin.radar.RadarDimension;
import org.jensoft.core.plugin.radar.RadarToolkit;
import org.jensoft.core.plugin.radar.RadarView;
import org.jensoft.core.plugin.radar.painter.label.RadarDimensionDefaultLabel;
import org.jensoft.core.plugin.radar.painter.radar.RadarDefaultPainter;
import org.jensoft.core.plugin.translate.TranslateCompassWidget;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class RadarWithoutToolkitDemo extends RadarView {

	public RadarWithoutToolkitDemo() {

		super();

		// create radar
		Radar radar = RadarToolkit.createRadar(0, 0, 200);
		radar.setRadarPainter(new RadarDefaultPainter());

		addRadar(radar);

		// create dimension
		RadarDimension d1 = RadarToolkit.createDimension("d1", 0, 0, 100);
		RadarDimension d2 = RadarToolkit.createDimension("d2", 60, 0, 100);
		RadarDimension d3 = RadarToolkit.createDimension("d3", 120, 0, 100);
		RadarDimension d4 = RadarToolkit.createDimension("d4", 180, 0, 100);
		RadarDimension d5 = RadarToolkit.createDimension("d5", 240, 0, 100);
		RadarDimension d6 = RadarToolkit.createDimension("d6", 300, 0, 100);

		RadarToolkit.pushDimensions(radar, d1, d2, d3, d4, d5, d6);

		// or for each
		// radar.addDimension(d1);
		// radar.addDimension(d2);
		// radar.addDimension(d3);
		// radar.addDimension(d4);
		// radar.addDimension(d5);
		// radar.addDimension(d6);

		// without factory, you can create radar metrics like show in following
		// RadarMetrics metric = new RadarMetrics();
		// metric.setValue(50);
		// metric.setStylePosition(StylePosition.Tangent);
		// metric.setMetricsLabel("50");
		// metric.setDivergence(10);
		// metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
		// PetalPalette.PETAL1_HC));
		// metric.setGlyphMetricMarkerPainter(new
		// RoundMarker(PetalPalette.PETAL1_HC, Color.WHITE, 3));
		// metric.setFont(InputFonts.getFont(InputFonts.NEUROPOL,10));

		GlyphFill metricsFill = new GlyphFill(RosePalette.MANDARIN, RosePalette.MELON);
		GlyphMetricMarkerPainter metricsMarker = new RoundMarker(PetalPalette.PETAL1_HC, Color.WHITE, 3);

		GlyphFill fill2 = new GlyphFill(RosePalette.FLAMINGO, RosePalette.LIME);
		GlyphMetricMarkerPainter marker2 = new RoundMarker(PetalPalette.PETAL2_HC, Color.WHITE, 3);

		GlyphFill fill3 = new GlyphFill(PetalPalette.PETAL4_HC, RosePalette.REDWOOD);
		GlyphMetricMarkerPainter marker3 = new TicTacMarker(PetalPalette.PETAL4_HC, 2, 4);

		DimensionMetrics metric;
		Font f =  new Font("Dialog", Font.PLAIN, 10);
		
		metric = RadarToolkit.createDimensionMetrics("20", 20, StylePosition.Default, 10, fill3, marker3, f);
		d1.addMetrics(metric);
		metric = RadarToolkit.createDimensionMetrics("40", 40, StylePosition.Default, 10, fill3, marker3, f);
		d1.addMetrics(metric);
		metric = RadarToolkit.createDimensionMetrics("60", 60, StylePosition.Default, 10, fill3, marker3, f);
		d1.addMetrics(metric);
		metric = RadarToolkit.createDimensionMetrics("80", 80, StylePosition.Default, 10, fill3, marker3, f);
		d1.addMetrics(metric);

		metric = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 10, metricsFill, metricsMarker, f);
		d1.addMetrics(metric);

		metric = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 10, metricsFill, metricsMarker, f);
		d2.addMetrics(metric);

		metric = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 10, metricsFill, metricsMarker, f);
		d3.addMetrics(metric);

		metric = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 10, metricsFill, metricsMarker, f);
		d4.addMetrics(metric);

		metric = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 10, metricsFill, metricsMarker, f);
		d5.addMetrics(metric);

		metric = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 10, metricsFill, metricsMarker, f);
		d6.addMetrics(metric);

		// LABELS
		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };

		// DIMENSION LABEL
		RadarDimensionDefaultLabel label;

		label = RadarToolkit.createDimensionDefaultLabel("Type 1", ColorPalette.WHITE, RosePalette.REDWOOD, fractions, c, 20);
		d1.setDimensionLabel(label);

		label = RadarToolkit.createDimensionDefaultLabel("Type 2", ColorPalette.WHITE, RosePalette.EMERALD, fractions, c, 20);
		d2.setDimensionLabel(label);

		label = RadarToolkit.createDimensionDefaultLabel("Type 3", ColorPalette.WHITE, RosePalette.MELON, fractions, c, 20);
		d3.setDimensionLabel(label);

		label = RadarToolkit.createDimensionDefaultLabel("Type 4", ColorPalette.WHITE, RosePalette.CORALRED, fractions, c, 20);
		d4.setDimensionLabel(label);

		label = RadarToolkit.createDimensionDefaultLabel("Type 5", ColorPalette.WHITE, RosePalette.AZALEA, fractions, c, 20);
		d5.setDimensionLabel(label);

		label = RadarToolkit.createDimensionDefaultLabel("Type 6", ColorPalette.WHITE, RosePalette.INDIGO, fractions, c, 20);
		d6.setDimensionLabel(label);

		// Surface
		// RadarSurface surface = new RadarSurface();
		// surface.setSurfacePainter(new
		// RadarSurfaceDefaultPainter(Color.WHITE,ColorPalette.alpha(RosePalette.MANDARIN,
		// 50)));

		// Surface Anchors
		// RadarMetrics anchorMetrics = new RadarMetrics();
		// anchorMetrics.setValue(67);
		// anchorMetrics.setStylePosition(StylePosition.Tangent);
		// anchorMetrics.setMetricsLabel("67 l.");
		// anchorMetrics.setDivergence(10);
		// anchorMetrics.setGlyphMetricFill(new GlyphFill(Color.WHITE,
		// PetalPalette.PETAL1_HC));
		// anchorMetrics.setGlyphMetricMarkerPainter(new
		// RoundMarker(PetalPalette.PETAL1_HC, Color.WHITE, 3));
		// anchorMetrics.setFont(InputFonts.getFont(InputFonts.NEUROPOL,10));

		// metricsFill = new GlyphFill(Color.WHITE,RosePalette.MANDARIN);
		// metricsMarker = new RoundMarker(RosePalette.MANDARIN, Color.WHITE,
		// 3);
		// Font anchorFont = InputFonts.getFont(InputFonts.NEUROPOL,14);
		// //or use factory
		// RadarMetrics anchorMetrics;
		// anchorMetrics = RadarFactory.createRadarMetrics("A1", 67,
		// StylePosition.Default, 20,metricsFill ,metricsMarker,anchorFont );
		// surface.addSurfaceAnchor(d1, anchorMetrics);
		//
		// anchorMetrics = RadarFactory.createRadarMetrics("A2", 82,
		// StylePosition.Default, 20,metricsFill ,metricsMarker, anchorFont);
		// surface.addSurfaceAnchor(d2, anchorMetrics);
		//
		// anchorMetrics = RadarFactory.createRadarMetrics("A3", 17,
		// StylePosition.Default, 20,metricsFill ,metricsMarker, anchorFont);
		// surface.addSurfaceAnchor(d3, anchorMetrics);
		//
		// anchorMetrics = RadarFactory.createRadarMetrics("A4", 44,
		// StylePosition.Default, 20,metricsFill ,metricsMarker, anchorFont);
		// surface.addSurfaceAnchor(d4, anchorMetrics);
		//
		// anchorMetrics = RadarFactory.createRadarMetrics("A5", 58,
		// StylePosition.Default, 20,metricsFill ,metricsMarker, anchorFont);
		// surface.addSurfaceAnchor(d5, anchorMetrics);
		//
		// anchorMetrics = RadarFactory.createRadarMetrics("A6", 36,
		// StylePosition.Default, 20,metricsFill ,metricsMarker, anchorFont);
		// surface.addSurfaceAnchor(d6, anchorMetrics);
		//
		// radar.addSurface(surface);

		registerPlugin(new OutlinePlugin());

		// TRANSLATE
		TranslatePlugin translatePlugin = new TranslatePlugin();
		registerPlugin(translatePlugin);

		// use compass widget with objectif
		TranslateCompassWidget compass = new TranslateCompassWidget();
		translatePlugin.registerWidget(compass);
		compass.setRingFillColor(new Alpha(RosePalette.EMERALD, 150));
		compass.setRingDrawColor(Color.WHITE);
		compass.setRingNeedleFillColor(new Alpha(RosePalette.EMERALD, 150));
		compass.setRingNeedleDrawColor(Color.WHITE);
		// pre lock selected
		translatePlugin.lockSelected();

	}

}
