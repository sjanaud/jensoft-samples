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
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.GlyphMetricMarkerPainter;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.radar.DimensionMetrics;
import org.jensoft.core.plugin.radar.Radar;
import org.jensoft.core.plugin.radar.RadarDimension;
import org.jensoft.core.plugin.radar.RadarSurface;
import org.jensoft.core.plugin.radar.RadarSurfaceAnchor;
import org.jensoft.core.plugin.radar.RadarToolkit;
import org.jensoft.core.plugin.radar.RadarView;
import org.jensoft.core.plugin.radar.painter.label.RadarDimensionDefaultLabel;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class RadarMultipleSurface2Demo extends RadarView {
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new RadarMultipleSurface2Demo());
	}

	public RadarMultipleSurface2Demo() {

		// **************************************
		// RADAR VIEW
		//
		// API
		// Create view : RadarView radarView =
		// RadarToolkit.createCompatibleView();
		// **************************************
		super();

		// **************************************
		// RADAR
		//
		// API
		// Create radar : Radar radar = RadarFactory.createRadar(centerX,
		// centerY, radius)
		// **************************************

		// create radar center (0,0) in user nature coordinate with 200 pixel
		// radius and register radar in view
		Radar radar = RadarToolkit.createRadar(0, 0, 200);
		addRadar(radar);

		// **************************************
		// RADAR DIMENSIONS
		//
		// API
		// Create RadarDimension : RadarDimension radarDim =
		// RadarToolkit.createDimension(name, angleDegree, min, max)
		// Create dimension metrics : DimensionMetrics dimMetrics =
		// RadarFactory.createDimensionMetrics(label, value, stylePosition,
		// divergence, glyphFill, glyphMarker, metricsFont)
		// **************************************

		// create radar dimensions and dimension label
		RadarDimension d1 = RadarToolkit.createDimension("january",RosePalette.CALYPSOBLUE, 90, 0, 100);
		RadarDimension d2 = RadarToolkit.createDimension("february",RosePalette.CALYPSOBLUE, 60, 0, 100);
		RadarDimension d3 = RadarToolkit.createDimension("march",RosePalette.EMERALD, 30, 0, 100);
		RadarDimension d4 = RadarToolkit.createDimension("april",RosePalette.EMERALD, 0, 0, 100);
		RadarDimension d5 = RadarToolkit.createDimension("may",RosePalette.EMERALD, 330, 0, 100);
		RadarDimension d6 = RadarToolkit.createDimension("june",RosePalette.LEMONPEEL, 300, 0, 100);
		RadarDimension d7 = RadarToolkit.createDimension("july",RosePalette.LEMONPEEL, 270, 0, 100);
		RadarDimension d8 = RadarToolkit.createDimension("august",RosePalette.LEMONPEEL, 240, 0, 100);
		RadarDimension d9 = RadarToolkit.createDimension("september",RosePalette.CHOCOLATE, 210, 0, 100);
		RadarDimension d10 = RadarToolkit.createDimension("october",RosePalette.CHOCOLATE, 180, 0, 100);
		RadarDimension d11 = RadarToolkit.createDimension("november",RosePalette.CHOCOLATE, 150, 0, 100);
		RadarDimension d12 = RadarToolkit.createDimension("december",RosePalette.CALYPSOBLUE, 120, 0, 100);
		
		RadarToolkit.pushDimensions(radar, d1, d2, d3, d4, d5, d6,d7,d8,d9,d10,d11,d12);

		// Create label dimension
		Font labelFont =  new Font("Dialog", Font.PLAIN, 12);
		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };

		RadarDimensionDefaultLabel dimLabel1, dimLabel2, dimLabel3, dimLabel4, dimLabel5, dimLabel6,dimLabel7, dimLabel8, dimLabel9, dimLabel10, dimLabel11, dimLabel12;

		dimLabel1 = RadarToolkit.createDimensionDefaultLabel("January", labelFont, RosePalette.WHITE, RosePalette.CALYPSOBLUE, fractions, c, 20);
		dimLabel2 = RadarToolkit.createDimensionDefaultLabel("February", labelFont, RosePalette.WHITE, RosePalette.CALYPSOBLUE, fractions, c, 20);
		dimLabel3 = RadarToolkit.createDimensionDefaultLabel("March", labelFont, RosePalette.WHITE, RosePalette.EMERALD, fractions, c, 20);
		dimLabel4 = RadarToolkit.createDimensionDefaultLabel("April", labelFont, RosePalette.WHITE, RosePalette.EMERALD, fractions, c, 20);
		dimLabel5 = RadarToolkit.createDimensionDefaultLabel("May", labelFont, RosePalette.WHITE, RosePalette.EMERALD, fractions, c, 20);
		dimLabel6 = RadarToolkit.createDimensionDefaultLabel("June", labelFont, RosePalette.WHITE, RosePalette.LEMONPEEL, fractions, c, 20);
		dimLabel7 = RadarToolkit.createDimensionDefaultLabel("July", labelFont, RosePalette.WHITE, RosePalette.LEMONPEEL, fractions, c, 20);
		dimLabel8 = RadarToolkit.createDimensionDefaultLabel("August", labelFont, RosePalette.WHITE, RosePalette.LEMONPEEL, fractions, c, 20);
		dimLabel9 = RadarToolkit.createDimensionDefaultLabel("September", labelFont, RosePalette.WHITE, RosePalette.CHOCOLATE, fractions, c, 20);
		dimLabel10 = RadarToolkit.createDimensionDefaultLabel("October", labelFont, RosePalette.WHITE, RosePalette.CHOCOLATE, fractions, c, 20);
		dimLabel11 = RadarToolkit.createDimensionDefaultLabel("November", labelFont, RosePalette.WHITE, RosePalette.CHOCOLATE, fractions, c, 20);
		dimLabel12 = RadarToolkit.createDimensionDefaultLabel("December", labelFont, RosePalette.WHITE, RosePalette.CALYPSOBLUE, fractions, c, 20);

		d1.setDimensionLabel(dimLabel1);
		d2.setDimensionLabel(dimLabel2);
		d3.setDimensionLabel(dimLabel3);
		d4.setDimensionLabel(dimLabel4);
		d5.setDimensionLabel(dimLabel5);
		d6.setDimensionLabel(dimLabel6);
		d7.setDimensionLabel(dimLabel7);
		d8.setDimensionLabel(dimLabel8);
		d9.setDimensionLabel(dimLabel9);
		d10.setDimensionLabel(dimLabel10);
		d11.setDimensionLabel(dimLabel11);
		d12.setDimensionLabel(dimLabel12);

		// Create metrics dimensions max limit markers
		// font, label and marker painter
		Font metricMaxFont =  new Font("Dialog", Font.PLAIN, 12);
		GlyphFill metricsFill = new GlyphFill(RosePalette.MANDARIN, RosePalette.MELON);
		GlyphMetricMarkerPainter metricsMarker = new RoundMarker(PetalPalette.PETAL1_HC, Color.WHITE, 3);

		// metrics
		DimensionMetrics m1, m2, m3, m4, m5, m6;
		m1 = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Radial, 20, metricsFill, metricsMarker, metricMaxFont);
		m2 = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 20, metricsFill, metricsMarker, metricMaxFont);
		m3 = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 20, metricsFill, metricsMarker, metricMaxFont);
		m4 = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 20, metricsFill, metricsMarker, metricMaxFont);
		m5 = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 20, metricsFill, metricsMarker, metricMaxFont);
		m6 = RadarToolkit.createDimensionMetrics("100", 100, StylePosition.Default, 20, metricsFill, metricsMarker, metricMaxFont);

		// register dimension metrics in dimensions
		d1.addMetrics(m1);
		d2.addMetrics(m2);
		d3.addMetrics(m3);
		d4.addMetrics(m4);
		d5.addMetrics(m5);
		d6.addMetrics(m6);
//
//		// Create dimension metrics
//		Font metricsFont = InputFonts.getFont(InputFonts.PHOENIX, 12);
//		GlyphFill dim1Fill = new GlyphFill(ColorPalette.WHITE, RosePalette.REDWOOD);
//		GlyphFill dim2Fill = new GlyphFill(ColorPalette.WHITE, RosePalette.AEGEANBLUE);
//
//		GlyphMetricMarkerPainter dim1Marker = new TicTacMarker(RosePalette.REDWOOD, 2, 4);
//		GlyphMetricMarkerPainter dim2Marker = new TicTacMarker(RosePalette.AEGEANBLUE, 2, 4);
//
//		DimensionMetrics m1d1 = RadarToolkit.createDimensionMetrics("20", 20, StylePosition.Default, 10, dim1Fill, dim1Marker, metricsFont);
//		DimensionMetrics m2d1 = RadarToolkit.createDimensionMetrics("40", 40, StylePosition.Default, 10, dim1Fill, dim1Marker, metricsFont);
//		DimensionMetrics m3d1 = RadarToolkit.createDimensionMetrics("60", 60, StylePosition.Default, 10, dim1Fill, dim1Marker, metricsFont);
//		DimensionMetrics m4d1 = RadarToolkit.createDimensionMetrics("80", 80, StylePosition.Default, 10, dim1Fill, dim1Marker, metricsFont);
//
//		DimensionMetrics m1d2 = RadarToolkit.createDimensionMetrics("20", 20, StylePosition.Default, 10, dim2Fill, dim2Marker, metricsFont);
//		DimensionMetrics m2d2 = RadarToolkit.createDimensionMetrics("40", 40, StylePosition.Default, 10, dim2Fill, dim2Marker, metricsFont);
//		DimensionMetrics m3d2 = RadarToolkit.createDimensionMetrics("60", 60, StylePosition.Default, 10, dim2Fill, dim2Marker, metricsFont);
//		DimensionMetrics m4d2 = RadarToolkit.createDimensionMetrics("80", 80, StylePosition.Default, 10, dim2Fill, dim2Marker, metricsFont);
//
//		// push metrics in dimension
//		RadarToolkit.pushMetricsDimensions(d1, m1d1, m2d1, m3d1, m4d1);
//		RadarToolkit.pushMetricsDimensions(d2, m1d2, m2d2, m3d2, m4d2);
//
//		// etc.
//
//		// **************************************
//		// SURFACE
//		//
//		// API
//		// Create surface : RadarFactory.createSurface(name, outlineColor,
//		// fillColor)
//		// Create anchor : RadarSurfaceAnchor rsa =
//		// RadarToolkit.createSurfaceAnchor(dimension, label, value,
//		// stylePosition, divergence, glyphFill, glyphMarker, metricsFont)
//		// Create anchor : RadarSurfaceAnchor rsa =
//		// RadarToolkit.createSurfaceAnchor(dimension, anchorMetrics)
//		// **************************************
//
		// CREATE SURFACE 1
		RadarSurface surface1 = RadarToolkit.createSurface("surface1", Color.WHITE, ColorPalette.alpha(RosePalette.CORALRED, 230));


		// CREATE SURFACE ANCHORS
		Font anchorFont =  new Font("Dialog", Font.PLAIN, 10);

		// create metrics filler and metrics marker
		metricsFill = new GlyphFill(Color.WHITE, NanoChromatique.GREEN);
		metricsMarker = new RoundMarker(NanoChromatique.GREEN, Color.WHITE, 3);

		// create surface anchors
		RadarSurfaceAnchor rsa1 = RadarToolkit.createSurfaceAnchor(d1, "A1", 20, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa2 = RadarToolkit.createSurfaceAnchor(d2, "A2", 40, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa3 = RadarToolkit.createSurfaceAnchor(d3, "A3", 80, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa4 = RadarToolkit.createSurfaceAnchor(d4, "A4", 40, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa5 = RadarToolkit.createSurfaceAnchor(d5, "A5", 5, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		//RadarSurfaceAnchor rsa6 = RadarToolkit.createSurfaceAnchor(d6, "A6", 36, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);

		// push anchor into surface
		RadarToolkit.pushAnchors(surface1, rsa1, rsa2, rsa3, rsa4, rsa5);

		// CREATE SURFACE 2
		RadarSurface surface2 = RadarToolkit.createSurface("surface2", Color.WHITE, ColorPalette.alpha(RosePalette.FLAMINGO, 250));

		

		// CREATE SURFACE ANCHORS

		// create metrics filler and metrics marker

		metricsFill = new GlyphFill(Color.WHITE, NanoChromatique.ORANGE);
		metricsMarker = new RoundMarker(NanoChromatique.ORANGE, Color.WHITE, 3);

		// create surface anchors
		//RadarSurfaceAnchor rsa1S2 = RadarToolkit.createSurfaceAnchor(d1, "A1", 12, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa2S2 = RadarToolkit.createSurfaceAnchor(d2, "A2", 10, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa3S2 = RadarToolkit.createSurfaceAnchor(d3, "A3", 20, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa4S2 = RadarToolkit.createSurfaceAnchor(d4, "A4", 30, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa5S2 = RadarToolkit.createSurfaceAnchor(d5, "A5", 30, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa6S2 = RadarToolkit.createSurfaceAnchor(d6, "A6", 10, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);

		// push anchor into surface
		RadarToolkit.pushAnchors(surface2,  rsa2S2, rsa3S2, rsa4S2, rsa5S2, rsa6S2);
		
		
		// CREATE SURFACE 3
		RadarSurface surface3 = RadarToolkit.createSurface("surface3", Color.WHITE, ColorPalette.alpha(RosePalette.EMERALD, 250));

		metricsFill = new GlyphFill(Color.WHITE, NanoChromatique.ORANGE);
		metricsMarker = new RoundMarker(NanoChromatique.ORANGE, Color.WHITE, 3);

		// create surface anchors
		//RadarSurfaceAnchor rsa1S2 = RadarToolkit.createSurfaceAnchor(d1, "A1", 12, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa2S3 = RadarToolkit.createSurfaceAnchor(d5, "A2", 40, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa3S3 = RadarToolkit.createSurfaceAnchor(d6, "A3", 50, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa4S3 = RadarToolkit.createSurfaceAnchor(d7, "A4", 65, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa5S3 = RadarToolkit.createSurfaceAnchor(d8, "A5", 50, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa6S3 = RadarToolkit.createSurfaceAnchor(d9, "A6", 30, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa7S3 = RadarToolkit.createSurfaceAnchor(d10, "A6", 20, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);

		// push anchor into surface
		RadarToolkit.pushAnchors(surface3,  rsa2S3, rsa3S3, rsa4S3, rsa5S3, rsa6S3,rsa7S3);
		
		// CREATE SURFACE 4
		RadarSurface surface4 = RadarToolkit.createSurface("surface4", Color.WHITE, ColorPalette.alpha(RosePalette.CALYPSOBLUE, 250));

		metricsFill = new GlyphFill(Color.WHITE, NanoChromatique.ORANGE);
		metricsMarker = new RoundMarker(NanoChromatique.ORANGE, Color.WHITE, 3);

		// create surface anchors
		//RadarSurfaceAnchor rsa1S2 = RadarToolkit.createSurfaceAnchor(d1, "A1", 12, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa2S4 = RadarToolkit.createSurfaceAnchor(d9, "A2", 30, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa3S4 = RadarToolkit.createSurfaceAnchor(d10, "A3", 70, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa4S4 = RadarToolkit.createSurfaceAnchor(d11, "A4", 30, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);

		// push anchor into surface
		RadarToolkit.pushAnchors(surface4,  rsa2S4, rsa3S4, rsa4S4);
		
		
		// CREATE SURFACE 5
		RadarSurface surface5 = RadarToolkit.createSurface("surface4", Color.WHITE, ColorPalette.alpha(RosePalette.FLAMINGO, 250));

		metricsFill = new GlyphFill(Color.WHITE, NanoChromatique.ORANGE);
		metricsMarker = new RoundMarker(NanoChromatique.ORANGE, Color.WHITE, 3);

		// create surface anchors
		//RadarSurfaceAnchor rsa1S2 = RadarToolkit.createSurfaceAnchor(d1, "A1", 12, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa2S5 = RadarToolkit.createSurfaceAnchor(d9, "A2", 10, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa3S5 = RadarToolkit.createSurfaceAnchor(d10, "A3", 50, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa4S5 = RadarToolkit.createSurfaceAnchor(d11, "A4", 45, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);
		RadarSurfaceAnchor rsa5S5 = RadarToolkit.createSurfaceAnchor(d12, "A4", 20, StylePosition.Default, 10, metricsFill, metricsMarker, anchorFont);

		// push anchor into surface
		RadarToolkit.pushAnchors(surface5,  rsa2S5, rsa3S5, rsa4S5,rsa5S5);
				
				
		// register surface in radar
		radar.addSurface(surface4);
		radar.addSurface(surface1);
		radar.addSurface(surface3);
		radar.addSurface(surface5);
		radar.addSurface(surface2);

		// OUTLINE
		registerPlugin(new OutlinePlugin());

	}

}
