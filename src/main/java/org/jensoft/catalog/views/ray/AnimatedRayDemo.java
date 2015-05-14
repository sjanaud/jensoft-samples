/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.ray;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.JennyPalette;
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
import org.jensoft.core.plugin.ray.painter.fill.RayFill1;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class AnimatedRayDemo extends RayView {

	public AnimatedRayDemo() {

		super(-260, 260, -250, 250);

		// OUTLINE
		registerPlugin(new OutlinePlugin());

		// LEGEND
		TitleLegend legend = new TitleLegend("Simple Ray");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY3));
		Font f =  new Font("Dialog", Font.PLAIN, 12);
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

		// ANIMATOR FOR DEMO (use inflate or deflate method on ray)
		class Animation extends Thread {

			private RayView view;

			public Animation(RayView view) {
				this.view = view;
			}

			@Override
			public void run() {
				try {
					Thread.sleep(1000);

					// CREATE RAY WITH RANDOM
					Random r = new Random();

					List<HashMap<String, Object>> raysParameters = new ArrayList<HashMap<String, Object>>();

					// RAY CREATION
					for (int i = -250; i <= 250; i = i + 4) {
						Ray ray = new Ray();
						ray.setName("ray" + i);
						ray.setRay(i);
						ray.setThicknessType(ThicknessType.User);
						ray.setThickness(2);
						ray.setRayNature(RayNature.XRay);
						ray.setRayBase(0);

						// ray.setRayDraw(new RayDefaultDraw(Color.WHITE));
						ray.setRayFill(new RayFill1());
						addRay(ray);

						int mode = r.nextInt(2);
						int value = r.nextInt(200);
						HashMap<String, Object> rayParams = new HashMap<String, Object>();
						rayParams.put("ray", ray);
						rayParams.put("value", value);
						if (mode == 0) {
							ray.setAscentValue(1);
						} else {
							ray.setDescentValue(1);
						}

						raysParameters.add(rayParams);
					}

					// RAY INFLATE ANIMATION
					for (HashMap<String, Object> rayParams : raysParameters) {
						Ray ray = (Ray) rayParams.get("ray");
						int value = (Integer) rayParams.get("value");
						ray.inflate(value, 0, 200, 20);
						Thread.sleep(20);
					}

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}

		Animation animation = new Animation(this);
		animation.start();

	}

}
