/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.effect;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
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
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DLinearEffectShift</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to shift linear effect on donut2D.")
public class D2DLinearEffectShift extends View {

	private static final long serialVersionUID = -8806081651211111691L;

	/**
	 * Create Donut2D demo with linear effect shift
	 */
	public D2DLinearEffectShift() {
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
		donut1.setOuterRadius(80);
		donut1.setStartAngleDegree(50);

		final Donut2DSlice s1 = new Donut2DSlice("s1", TangoPalette.BUTTER2);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", TangoPalette.CHAMELEON2);
		s2.setValue(20.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", TangoPalette.SKYBLUE2);
		s3.setValue(20.0);

		Donut2DLinearEffect effect = new Donut2DLinearEffect();
		donut1.setDonut2DEffect(effect);

		Donut2DLinearEffect.shiftIncidence(donut1);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);
	}

}
