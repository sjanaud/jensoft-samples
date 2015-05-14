/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.multiple;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.FilPalette;
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
 * <code>D2DMultiple</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to create multiple donut2D overlay")
public class D2DMultiple extends View {

	private static final long serialVersionUID = -5142712565631123423L;

	/**
	 * create donut2D demo with multiple donut2D
	 */
	public D2DMultiple() {
		super(0);

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
		donut1.setInnerRadius(70);
		donut1.setOuterRadius(100);
		donut1.setStartAngleDegree(0);

		final Donut2DSlice s1 = new Donut2DSlice("s1", FilPalette.BLUE1);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", FilPalette.GREEN1);
		s2.setValue(20.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", FilPalette.BROWN1);
		s3.setValue(20.0);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		Donut2D donut2 = new Donut2D();
		donut2.setNature(Donut2DNature.User);
		donut2.setCenterX(0);
		donut2.setCenterY(0);
		donut2.setInnerRadius(30);
		donut2.setOuterRadius(70);
		donut2.setStartAngleDegree(0);

		final Donut2DSlice d2s1 = new Donut2DSlice("d2s1", FilPalette.BLUE2);
		d2s1.setValue(20.0);
		final Donut2DSlice d2s1prim = new Donut2DSlice("d2s1prim", FilPalette.BLUE3);
		d2s1prim.setValue(20.0);

		final Donut2DSlice d2s2 = new Donut2DSlice("d2s2", FilPalette.GREEN2);
		d2s2.setValue(20.0);
		final Donut2DSlice d2s2prim = new Donut2DSlice("d2s2prim", FilPalette.GREEN3);
		d2s2prim.setValue(20.0);

		final Donut2DSlice d2s3 = new Donut2DSlice("d2s3", FilPalette.BROWN2);
		d2s3.setValue(20.0);
		final Donut2DSlice d2s3prim = new Donut2DSlice("d2s3", FilPalette.BROWN3);
		d2s3prim.setValue(20.0);

		donut2.addSlice(d2s1);
		donut2.addSlice(d2s1prim);

		donut2.addSlice(d2s2);
		donut2.addSlice(d2s2prim);

		donut2.addSlice(d2s3);
		donut2.addSlice(d2s3prim);

		donut2DPlugin.addDonut(donut1);
		donut2DPlugin.addDonut(donut2);

		registerProjection(proj);

	}

	/**
	 * create the view portfolio for {@link #Donut2DMultipleDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DMultipleDemo", width = 500, height = 250)
	public static View getPortofolio() {
		D2DMultiple demo = new D2DMultiple();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
