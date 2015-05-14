/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.effect;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.painter.effect.Donut2DLinearEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DLinearEffect</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use linear effect on donut2D.")
public class D2DLinearEffect extends View {

	private static final long serialVersionUID = -5667689587745146235L;

	/**
	 * Create Donut2D demo with linear effect
	 */
	public D2DLinearEffect() {
		super();

		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setThemeColor(Color.WHITE);

		Donut2DPlugin donut2DPlugin = new Donut2DPlugin();
		proj.registerPlugin(donut2DPlugin);

		Donut2D donut1 = new Donut2D();
		donut1.setNature(Donut2DNature.User);
		donut1.setCenterX(0);
		donut1.setCenterY(0);
		donut1.setInnerRadius(90);
		donut1.setOuterRadius(140);
		donut1.setStartAngleDegree(50);

		final Donut2DSlice s0 = new Donut2DSlice("s0", new Color(240, 240, 240, 255));
		s0.setValue(20.0);

		final Donut2DSlice s1 = new Donut2DSlice("s1", TangoPalette.BUTTER2);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", TangoPalette.CHAMELEON2);
		s2.setValue(20.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", TangoPalette.SKYBLUE2);
		s3.setValue(20.0);

		Donut2DLinearEffect effect = new Donut2DLinearEffect();
		donut1.setDonut2DEffect(effect);

		donut1.addSlice(s0);
		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);
	}
}
