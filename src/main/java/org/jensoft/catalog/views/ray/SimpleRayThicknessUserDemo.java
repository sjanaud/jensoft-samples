/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.ray;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.NanoChromatique;
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
import org.jensoft.core.plugin.ray.painter.draw.RayDefaultDraw;
import org.jensoft.core.plugin.ray.painter.fill.RayFill1;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleRayThicknessUserDemo extends RayView {

	public SimpleRayThicknessUserDemo() {

		super(-260, 260, -250, 250);

		// CREATE RAY WITH RANDOM
		Random r = new Random();
		for (int i = -250; i <= 250; i = i + 4) {

			Ray ray = new Ray();
			ray.setName("ray" + i);
			ray.setThicknessType(ThicknessType.User);
			ray.setThickness(2);
			ray.setRayNature(RayNature.XRay);
			ray.setRayBase(0);

			int style = r.nextInt(2);

			if (style == 0) {
				ray.setAscentValue(r.nextInt(200));
				ray.setThemeColor(NanoChromatique.BLUE);
			} else {
				ray.setDescentValue(r.nextInt(200));
				ray.setThemeColor(NanoChromatique.ORANGE);
			}
			ray.setRay(i);

			ray.setRayDraw(new RayDefaultDraw(Color.WHITE));
			ray.setRayFill(new RayFill1());

			addRay(ray);
		}

		// OUTLINE
		registerPlugin(new OutlinePlugin());

		// LEGEND
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Simple Ray");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY3));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
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
