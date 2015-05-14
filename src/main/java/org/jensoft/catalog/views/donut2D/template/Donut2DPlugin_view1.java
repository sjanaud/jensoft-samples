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
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.painter.effect.Donut2DLinearEffect;
import org.jensoft.core.plugin.donut2d.painter.label.AbstractDonut2DSliceLabel.Style;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DBorderLabel;
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
 * <code>Donut2DPlugin_view1</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="template for donut2D.")
public class Donut2DPlugin_view1 extends View {

	private static final long serialVersionUID = 1990879657715032952L;

	/**
	 * create Donut2D demo with border label
	 */
	public Donut2DPlugin_view1() {
		super(2);

		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setName("donuts2D");
		proj.setThemeColor(Color.WHITE);
		//proj.registerPlugin(new OutlinePlugin(RosePalette.COALBLACK));

		proj.registerPlugin(new ZoomWheelPlugin());

		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translatePlugin);

		Donut2DPlugin donut2DPlugin = new Donut2DPlugin();
		proj.registerPlugin(donut2DPlugin);
		
		

		Donut2D donut1 = new Donut2D();
		donut1.setNature(Donut2DNature.User);
		donut1.setCenterX(1);
		donut1.setCenterY(0);
		donut1.setInnerRadius(30);
		donut1.setOuterRadius(60);
		donut1.setStartAngleDegree(20);
		//donut1.setExplose(10);
		//donut1.setDonut2DDraw(new Donut2DDefaultDraw(RosePalette.COALBLACK.brighter(),new BasicStroke(0.5f)));
		Donut2DLinearEffect effect = new Donut2DLinearEffect();
		effect.setOffsetRadius(2);
		donut1.setDonut2DEffect(effect);

		final Donut2DSlice s1 = new Donut2DSlice("s1", new Color(250, 250, 250));
		s1.setValue(40.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", Spectral.SPECTRAL_RED);
		s2.setValue(5.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", NanoChromatique.ORANGE);
		s3.setValue(20.0);

		float[] fractions = { 0f, 0.5f, 1f };
		Color[] c = { new Color(0, 0, 0, 255),new Color(0, 0, 0, 100), new Color(0, 0, 0, 255) };
		
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		Donut2DBorderLabel label1 = Donut2DToolkit.createBorderLabel("JENSOFT", RosePalette.COALBLACK, f, 20, Style.Both);
		label1.setLinkColor(RosePalette.COALBLACK);		
		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(NanoChromatique.PINK);
		label1.setShader(fractions, c);
		label1.setOutlineStroke(new BasicStroke(2f));
		s1.addSliceLabel(label1);
		
		Donut2DBorderLabel label2 = Donut2DToolkit.createBorderLabel("API", RosePalette.COALBLACK, f, 20, Style.Both);
		label2.setLinkColor(RosePalette.COALBLACK);		
		label2.setLabelColor(ColorPalette.WHITE);
		label2.setOutlineColor(NanoChromatique.BLUE);
		label2.setShader(fractions, c);
		label2.setOutlineStroke(new BasicStroke(2f));
		s2.addSliceLabel(label2);
		
		Donut2DPathLabel ppl3 = new Donut2DPathLabel(TextPosition.Middle, "FRAMEWORK", TangoPalette.CHAMELEON2.darker());
		float[] fractions2 = { 0f, 1f };
		Color[] colors2 = { Color.BLACK, RosePalette.AMETHYST };
		ppl3.setLabelFont(new Font("lucida console",Font.PLAIN,12));
		ppl3.setTextShader(fractions2, colors2);
		ppl3.setPathSide(PathSide.Above);
		ppl3.setDivergence(2);
		s3.addSliceLabel(ppl3);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);

	}

	/**
	 * create the view portfolio for {@link #Donut2DBorderLabelDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DBorderLabelDemo", width = 500, height = 250)
	public static View getPortofolio() {
		Donut2DPlugin_view1 demo = new Donut2DPlugin_view1();
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
