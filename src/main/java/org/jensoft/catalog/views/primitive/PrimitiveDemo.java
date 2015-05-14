/*
 * Copyright (c) JenSoft API
 * This source file is part of JenSoft API, All rights reserved.
 * JENSOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.jensoft.catalog.views.primitive;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.minidevice.MiniDevicePlugin;
import org.jensoft.core.plugin.morphe.Ellipse;
import org.jensoft.core.plugin.morphe.Primitive.PrimitiveNature;
import org.jensoft.core.plugin.morphe.PrimitivePlugin;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class PrimitiveDemo extends View {

	private static final long serialVersionUID = 156889765687899L;

	public PrimitiveDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		setDeviceBackground(Color.BLACK);

		Projection proj = new Projection.Linear(-3000, 3000, -2500, 2500);
		proj.setName("primitive projection");

		PrimitivePlugin primitiveLayout = new PrimitivePlugin();

		Ellipse circle = new Ellipse(1000, 1000, 400, 400);
		circle.setNature(PrimitiveNature.USER);

		primitiveLayout.registerPrimitive(circle);

		proj.registerPlugin(primitiveLayout);

		registerProjection(proj);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextColor(RosePalette.LEMONPEEL);
		southMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		southMetrics.setTextFont(f);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextColor(RosePalette.LEMONPEEL);
		westMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		westMetrics.setTextFont(f);
		

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		MiniDevicePlugin toolMinidevice = new MiniDevicePlugin();
		proj.registerPlugin(toolMinidevice);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		ZoomLensPlugin objectif = new ZoomLensPlugin();
		proj.registerPlugin(objectif);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final ViewFrameUI demoFrame = new ViewFrameUI(new PrimitiveDemo());

	}

}
