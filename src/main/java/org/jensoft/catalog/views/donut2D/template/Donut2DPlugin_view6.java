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
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.painter.effect.Donut2DLinearEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut2DPlugin_view6</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="template for donut2D.")
public class Donut2DPlugin_view6 extends View {

	private static final long serialVersionUID = 899900995425527241L;

	/**
	 * Create Donut2D demo with linear effect
	 */
	public Donut2DPlugin_view6() {
		super(2);

		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setThemeColor(Color.WHITE);
		
		Donut2DPlugin donut2DPlugin = new Donut2DPlugin();
		proj.registerPlugin(donut2DPlugin);

		Donut2D donut1 = new Donut2D();
		donut1.setNature(Donut2DNature.User);
		donut1.setCenterX(0);
		donut1.setCenterY(0);
		donut1.setInnerRadius(50);
		donut1.setOuterRadius(110);
		donut1.setStartAngleDegree(20);

		final Donut2DSlice s0 = new Donut2DSlice("s0", new Color(240, 240, 240, 255));
		s0.setValue(20.0);

		final Donut2DSlice s1 = new Donut2DSlice("s1", Spectral.SPECTRAL_PURPLE2);
		s1.setValue(10.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", Spectral.SPECTRAL_RED);
		s2.setValue(5.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", TangoPalette.SKYBLUE2);
		s3.setValue(15.0);

		Donut2DLinearEffect effect = new Donut2DLinearEffect(180);
		donut1.setDonut2DEffect(effect);

		donut1.addSlice(s0);
		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);
	}

	/**
	 * create the view portfolio for {@link #Donut2DLinearEffectDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DLinearEffectDemo", width = 500, height = 250)
	public static View getPortofolio() {
		Donut2DPlugin_view6 demo = new Donut2DPlugin_view6();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
