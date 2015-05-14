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
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.ray.Ray.RayNature;
import org.jensoft.core.plugin.ray.Ray.ThicknessType;
import org.jensoft.core.plugin.ray.RayStack;
import org.jensoft.core.plugin.ray.RayView;
import org.jensoft.core.plugin.ray.StackedRay;
import org.jensoft.core.plugin.ray.painter.effect.RayEffect1;
import org.jensoft.core.plugin.ray.painter.fill.RayFill2;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleRayStackedDemo extends RayView {

	public SimpleRayStackedDemo() {

		super(-200, 200, 0, 250);

		Random r = new Random();
		Random r2 = new Random();

		// CREATE STACKED RAY WITH RANDOM
		for (int i = -250; i <= 250; i = i + 4) {

			StackedRay sray = new StackedRay();
			sray.setName("stacked ray 1");
			sray.setThicknessType(ThicknessType.User);
			sray.setThickness(3);
			sray.setRayNature(RayNature.XRay);
			sray.setRayBase(13);
			sray.setAscentValue(r.nextInt(200));
			sray.setRay(i);
			sray.setThemeColor(Color.WHITE);
			// sray.setRayDraw(new RayDefaultDraw(Color.WHITE));
			RayStack rs11 = new RayStack("ray 1 stack 1", TangoPalette.BUTTER3, r2.nextInt(20));
			rs11.setRayFill(new RayFill2());
			RayStack rs12 = new RayStack("ray 1 stack 2", TangoPalette.CHAMELEON3, r2.nextInt(20));
			rs12.setRayFill(new RayFill2());
			RayStack rs13 = new RayStack("ray 1 stack 3", TangoPalette.ORANGE3, r2.nextInt(20));
			rs13.setRayFill(new RayFill2());

			sray.addStack(rs11);
			sray.addStack(rs12);
			sray.addStack(rs13);

			// sray.setRayDraw(new RayDefaultDraw(Color.WHITE));
			sray.setThemeColor(TangoPalette.BUTTER1);
			sray.setRayEffect(new RayEffect1());
			addRay(sray);
		}

		// OUTLINE
		registerPlugin(new OutlinePlugin());

		// LEGEND
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Simple Stacked Ray");
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
