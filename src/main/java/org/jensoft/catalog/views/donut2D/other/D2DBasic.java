/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.other;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut2DBasic</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to create basic donut2D")
public class D2DBasic extends View {

	private static final long serialVersionUID = -2136097559828927621L;

	/**
	 * Create a basic donut2D demo
	 */
	public D2DBasic() {
		super();

		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setThemeColor(Color.WHITE);
		proj.registerPlugin(new OutlinePlugin(RosePalette.MELON));

		proj.registerPlugin(new ZoomWheelPlugin());

		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translatePlugin);

		Donut2DPlugin donut2DPlugin = new Donut2DPlugin();
		proj.registerPlugin(donut2DPlugin);

		Donut2D donut1 = new Donut2D();
		donut1.setNature(Donut2DNature.User);
		donut1.setCenterX(0);
		donut1.setCenterY(0);
		donut1.setInnerRadius(40);
		donut1.setOuterRadius(60);
		donut1.setStartAngleDegree(50);
		donut1.setExplose(10);

		final Donut2DSlice s1 = new Donut2DSlice("s1", new Color(139, 196, 40));
		s1.setAlpha(0.5f);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", new Color(213, 222, 35));
		s2.setValue(20.0);
		s2.setAlpha(0.5f);

		final Donut2DSlice s3 = new Donut2DSlice("s3", new Color(78, 148, 44));
		s3.setValue(20.0);
		s3.setAlpha(0.5f);
		s3.setDivergence(10);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);

	}

	/**
	 * create view portfolio for {@link #Donut2DMultipleUserProjection()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DBasicDemo", width = 500, height = 250)
	public static View getPortofolio() {
		D2DBasic demo = new D2DBasic();
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
