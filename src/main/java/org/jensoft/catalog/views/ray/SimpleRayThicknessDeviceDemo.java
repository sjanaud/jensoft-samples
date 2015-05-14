/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.ray;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.ray.Ray;
import org.jensoft.core.plugin.ray.Ray.RayNature;
import org.jensoft.core.plugin.ray.Ray.ThicknessType;
import org.jensoft.core.plugin.ray.RayView;
import org.jensoft.core.plugin.ray.painter.fill.RayDefaultFill;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleRayThicknessDeviceDemo extends RayView {

	@Portfolio(name = "SimpleRay2Demo", width = 800, height = 600)
	public static View getPortofolio() {
		SimpleRayThicknessDeviceDemo demo = new SimpleRayThicknessDeviceDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public SimpleRayThicknessDeviceDemo() {

		super(-260, 260, -250, 250);

		setPlaceHolder(45);
		setPlaceHolderAxisWest(50);
		setPlaceHolderAxisSouth(40);
		setPlaceHolderAxisNorth(30);
		setPlaceHolderAxisEast(40);

		getProjection().setThemeColor(RosePalette.LEMONPEEL.brighter());

		// CREATE RAY WITH RANDOM
		Random r = new Random();
		for (int i = -250; i <= 250; i = i + 10) {

			Ray ray = new Ray();
			ray.setName("ray" + i);
			ray.setThicknessType(ThicknessType.Device);
			ray.setThickness(2);
			ray.setRayNature(RayNature.XRay);
			ray.setRayBase(0);

			ray.setAscentValue(80 + r.nextInt(120));
			ray.setThemeColor(Spectral.SPECTRAL_YELLOW.brighter());
			ray.setRay(i);
			ray.setRayFill(new RayDefaultFill());

			addRay(ray);
		}

		for (int i = -250; i <= 250; i = i + 10) {

			Ray ray = new Ray();
			ray.setName("ray" + i);
			ray.setThicknessType(ThicknessType.Device);
			ray.setThickness(2);
			ray.setRayNature(RayNature.XRay);
			ray.setRayBase(0);

			ray.setDescentValue(80 + r.nextInt(120));
			ray.setThemeColor(Spectral.SPECTRAL_GREEN);
			ray.setRay(i);
			ray.setRayFill(new RayDefaultFill());

			addRay(ray);
		}

		// OUTLINE
		registerPlugin(new OutlinePlugin());

		// LEGEND
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Ray Diagram");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, Spectral.SPECTRAL_YELLOW.brighter()));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.4f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		registerPlugin(lgendL);

		// TOOLS
		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		registerPlugin(zoomWheel);

	}

}
