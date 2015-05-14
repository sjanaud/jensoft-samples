/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.template;

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
import org.jensoft.core.plugin.donut2d.painter.effect.Donut2DLinearEffect;
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
 * <code>Donut2DPlugin_view4</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="template for donut2D.")
public class Donut2DPlugin_view4 extends View {

	private static final long serialVersionUID = -4026804179514047490L;

	/**
	 * create donut2D demo with multiple donut2D
	 */
	public Donut2DPlugin_view4() {
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
		donut1.setInnerRadius(50);
		donut1.setOuterRadius(110);
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

		Donut2D donut0 = new Donut2D();
		donut0.setNature(Donut2DNature.User);
		donut0.setCenterX(0);
		donut0.setCenterY(0);
		donut0.setInnerRadius(0);
		donut0.setOuterRadius(50);
		donut0.setStartAngleDegree(0);
		
		Donut2DLinearEffect effect = new Donut2DLinearEffect();
		effect.setOffsetRadius(0);
		
		donut1.setDonut2DEffect(effect);
		donut0.setDonut2DEffect(effect);

		final Donut2DSlice d2s1 = new Donut2DSlice("d2s1", FilPalette.BLUE2);
		d2s1.setValue(10.0);
		final Donut2DSlice d2s1prim = new Donut2DSlice("d2s1prim", FilPalette.BLUE3);
		d2s1prim.setValue(20.0);

		final Donut2DSlice d2s2 = new Donut2DSlice("d2s2", FilPalette.GREEN2);
		d2s2.setValue(10.0);
		final Donut2DSlice d2s2prim = new Donut2DSlice("d2s2prim", FilPalette.GREEN3);
		d2s2prim.setValue(15.0);
		final Donut2DSlice d2s2ters = new Donut2DSlice("d2s2ters", FilPalette.GREEN5);
		d2s2ters.setValue(5.0);

		final Donut2DSlice d2s3 = new Donut2DSlice("d2s3", FilPalette.BROWN2);
		d2s3.setValue(10.0);
		final Donut2DSlice d2s3prim = new Donut2DSlice("d2s3", FilPalette.BROWN3);
		d2s3prim.setValue(20.0);

		donut0.addSlice(d2s1);
		donut0.addSlice(d2s1prim);

		donut0.addSlice(d2s2);
		donut0.addSlice(d2s2prim);
		donut0.addSlice(d2s2ters);

		donut0.addSlice(d2s3);
		donut0.addSlice(d2s3prim);

		donut2DPlugin.addDonut(donut1);
		donut2DPlugin.addDonut(donut0);

		registerProjection(proj);

	}

	/**
	 * create the view portfolio for {@link #Donut2DMultipleDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DMultipleDemo", width = 500, height = 250)
	public static View getPortofolio() {
		Donut2DPlugin_view4 demo = new Donut2DPlugin_view4();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
