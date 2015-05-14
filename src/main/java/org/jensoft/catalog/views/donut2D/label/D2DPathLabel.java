/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.label;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.drawable.text.TextPath.PathSide;
import org.jensoft.core.drawable.text.TextPath.TextPosition;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DPathLabel;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DPathLabel</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to create path label on donut2D.")
public class D2DPathLabel extends View {

	private static final long serialVersionUID = 8409938544877889715L;

	/**
	 * create Donut2D demo with path label
	 */
	public D2DPathLabel() {
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
		donut1.setInnerRadius(80);
		donut1.setOuterRadius(110);
		donut1.setStartAngleDegree(50);

		final Donut2DSlice s1 = new Donut2DSlice("s1", TangoPalette.BUTTER2);
		s1.setAlpha(0.5f);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", TangoPalette.CHAMELEON2);
		s2.setValue(20.0);
		s2.setAlpha(0.5f);

		final Donut2DSlice s3 = new Donut2DSlice("s3", TangoPalette.SKYBLUE2);
		s3.setValue(20.0);
		s3.setAlpha(0.5f);
		s3.setDivergence(10);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		Donut2DPathLabel ppl = new Donut2DPathLabel(TextPosition.Right, "java framework");
		ppl.setPathSide(PathSide.Below);
		ppl.setLabelFont(f);
		ppl.setLabelColor(RosePalette.MANDARIN);
		ppl.setDivergence(2);
		s1.addSliceLabel(ppl);

		Donut2DPathLabel ppl12 = Donut2DToolkit.createPathLabel("JenSoft API", RosePalette.INDIGO, f, TextPosition.Middle);
		s1.addSliceLabel(ppl12);

		Donut2DPathLabel ppl3 = new Donut2DPathLabel(TextPosition.Left, "Pie Path Label", TangoPalette.CHAMELEON2.darker());
		float[] fractions = { 0f, 1f };
		Color[] colors = { Color.BLACK, RosePalette.AMETHYST };
		ppl3.setLabelFont(f);
		ppl3.setTextShader(fractions, colors);
		ppl3.setPathSide(PathSide.Above);
		ppl3.setDivergence(2);

		s3.addSliceLabel(ppl3);

		Donut2DPathLabel ppl4 = new Donut2DPathLabel(TextPosition.Right, "JenSoft");
		ppl4.setPathSide(PathSide.Below);
		ppl4.setLabelFont(f);
		ppl4.setDivergence(0);

		registerProjection(proj);

	}
}
