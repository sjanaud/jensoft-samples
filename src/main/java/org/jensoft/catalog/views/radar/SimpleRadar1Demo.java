/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.radar;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
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
public class SimpleRadar1Demo extends RadarView {

	public SimpleRadar1Demo() {
		super();

		// create radar
		Radar radar = RadarToolkit.createRadar(0, 0, 200);
		radar.setRadarPainter(new RadarDefaultPainter());

		addRadar(radar);

		// create dimension
		RadarDimension d1 = RadarToolkit.createDimension("d1", RosePalette.EMERALD, 0, 0, 100);
		RadarDimension d2 = RadarToolkit.createDimension("d2", 120, 0, 100);
		RadarDimension d3 = RadarToolkit.createDimension("d3", 240, 0, 100);

		RadarToolkit.pushDimensions(radar, d1, d2, d3);

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
