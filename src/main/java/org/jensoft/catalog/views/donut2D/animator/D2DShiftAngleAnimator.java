/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.animator;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.animator.Donut2DShiftStartAngleAnimator;
import org.jensoft.core.plugin.donut2d.animator.Donut2DShiftStartAngleAnimator.ShiftVelocity;
import org.jensoft.core.plugin.donut2d.painter.fill.Donut2DRadialFill;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DShiftAngleAnimator</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use shift angle animator on donut2D slice.")
public class D2DShiftAngleAnimator extends View {

	private static final long serialVersionUID = -8002430526359329488L;

	/**
	 * create donut2D shift angle animator
	 */
	public D2DShiftAngleAnimator() {
		super();

		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setThemeColor(Color.WHITE);
		proj.registerPlugin(new OutlinePlugin(RosePalette.MELON));

		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translatePlugin);

		Donut2DPlugin donut2DPlugin = new Donut2DPlugin();
		proj.registerPlugin(donut2DPlugin);

		Donut2D donut1 = new Donut2D();
		donut1.setNature(Donut2DNature.User);
		donut1.setCenterX(0);
		donut1.setCenterY(0);
		donut1.setInnerRadius(60);
		donut1.setOuterRadius(90);
		donut1.setStartAngleDegree(50);

		Donut2DRadialFill donut2DRadialFill = new Donut2DRadialFill();
		donut1.setDonut2DFill(donut2DRadialFill);

		final Donut2DSlice s0 = new Donut2DSlice("s1", Spectral.SPECTRAL_GREEN.brighter());
		s0.setValue(20.0);

		final Donut2DSlice s1 = new Donut2DSlice("s1", Spectral.SPECTRAL_RED.brighter());
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", Spectral.SPECTRAL_BLUE2.brighter());
		s2.setValue(20.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", Spectral.SPECTRAL_PURPLE1.brighter());
		s3.setValue(20.0);

		donut1.addSlice(s0);
		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);
		Donut2DShiftStartAngleAnimator animator = new Donut2DShiftStartAngleAnimator();
		donut1.addDonutAnimator(animator);

		animator.shift(ShiftVelocity.Speed);

		registerProjection(proj);
	}

}
