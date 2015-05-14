/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.animator;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.animator.Donut2DLabelAnimator;
import org.jensoft.core.plugin.donut2d.painter.fill.Donut2DRadialFill;
import org.jensoft.core.plugin.donut2d.painter.label.AbstractDonut2DSliceLabel.Style;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DBorderLabel;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DLabelAnimator</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use label animator on donut2D slice.")
public class D2DLabelAnimator extends View {

	private static final long serialVersionUID = -8180447467545605583L;

	/**
	 * create donut2D label animator
	 */
	public D2DLabelAnimator() {
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

		// LABELS
		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		// LABEL 1
		Donut2DBorderLabel label0 = Donut2DToolkit.createBorderLabel("JET", RosePalette.COALBLACK, f, 20, Style.Both);
		label0.setLinkColor(RosePalette.LEMONPEEL);
		// label1.setLinkExtends(30);
		label0.setLabelColor(ColorPalette.WHITE);
		label0.setOutlineColor(Color.BLACK);
		label0.setShader(fractions, c);

		// LABEL 1
		Donut2DBorderLabel label1 = Donut2DToolkit.createBorderLabel("JenSoft", RosePalette.COALBLACK, f, 20, Style.Both);
		label1.setLinkColor(RosePalette.LEMONPEEL);
		// label1.setLinkExtends(30);
		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(Color.BLACK);
		label1.setShader(fractions, c);

		// LABEL 2
		Donut2DBorderLabel label2 = Donut2DToolkit.createBorderLabel("API", RosePalette.COALBLACK, f, 20, Style.Both);
		label2.setLinkColor(RosePalette.LEMONPEEL);
		// label1.setLinkExtends(30);
		label2.setLabelColor(ColorPalette.WHITE);
		label2.setOutlineColor(Color.BLACK);
		label2.setShader(fractions, c);

		// LABEL 2
		Donut2DBorderLabel label3 = Donut2DToolkit.createBorderLabel("JenScript", RosePalette.COALBLACK, f, 20, Style.Both);
		label3.setLinkColor(RosePalette.LEMONPEEL);
		// label1.setLinkExtends(30);
		label3.setLabelColor(ColorPalette.WHITE);
		label3.setOutlineColor(Color.BLACK);
		label3.setShader(fractions, c);

		donut1.addSlice(s0);
		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		donut1.addDonutAnimator(new Donut2DLabelAnimator(s0, label0));
		donut1.addDonutAnimator(new Donut2DLabelAnimator(s1, label1));
		donut1.addDonutAnimator(new Donut2DLabelAnimator(s2, label2));
		donut1.addDonutAnimator(new Donut2DLabelAnimator(s3, label3));

		registerProjection(proj);
	}

}
