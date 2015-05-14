/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.template;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.drawable.text.TextPath.PathSide;
import org.jensoft.core.drawable.text.TextPath.TextPosition;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DPathLabel;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut2DPlugin_view3</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="template for donut2D.")
public class Donut2DPlugin_view3 extends View {

	private static final long serialVersionUID = 4562069076226964354L;

	/**
	 * create Donut2D demo with path label
	 */
	public Donut2DPlugin_view3() {
		super(2);

		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setThemeColor(Color.WHITE);

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
		donut1.setStartAngleDegree(50);

		final Donut2DSlice s1 = new Donut2DSlice("s1", RosePalette.AMETHYST);
		s1.setAlpha(0.5f);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", TangoPalette.CHAMELEON2);
		s2.setValue(5.0);
		s2.setAlpha(0.5f);

		final Donut2DSlice s3 = new Donut2DSlice("s3", TangoPalette.SKYBLUE2);
		s3.setValue(10.0);
		s3.setAlpha(0.5f);
		s3.setDivergence(10);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);
		Font f =  new Font("Dialog", Font.PLAIN, 16);
		
		Donut2DPathLabel ppl = new Donut2DPathLabel(TextPosition.Right, "jensoft framework");
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

	/**
	 * create the view portfolio for {@link #Donut2DPathLabelDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DPathLabelDemo", width = 500, height = 250)
	public static View getPortofolio() {
		Donut2DPlugin_view3 demo = new Donut2DPlugin_view3();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
