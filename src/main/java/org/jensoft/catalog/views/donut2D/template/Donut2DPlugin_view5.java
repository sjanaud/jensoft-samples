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
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.painter.effect.Donut2DLinearEffect;
import org.jensoft.core.plugin.donut2d.painter.fill.Donut2DRadialFill;
import org.jensoft.core.plugin.donut2d.painter.label.AbstractDonut2DSliceLabel.Style;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DBorderLabel;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut2DPlugin_view5</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="template for donut2D.")
public class Donut2DPlugin_view5 extends View {

	private static final long serialVersionUID = 8272582539485737677L;

	/**
	 * create Donut2D demo with radial slice fill
	 */
	public Donut2DPlugin_view5() {
		super(0);

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
		donut1.setStartAngleDegree(0);
		//donut1.setExplose(10);
		Donut2DLinearEffect effect = new Donut2DLinearEffect(30);
		donut1.setDonut2DEffect(effect);

		Donut2DRadialFill donut2DRadialFill = new Donut2DRadialFill();
		donut1.setDonut2DFill(donut2DRadialFill);

		final Donut2DSlice s1 = new Donut2DSlice("s1", new Color(240, 240, 240, 255));
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", Spectral.SPECTRAL_BLUE2.brighter());
		s2.setValue(20.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", Spectral.SPECTRAL_PURPLE1.brighter());
		s3.setValue(20.0);

		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		Donut2DBorderLabel label1 = Donut2DToolkit.createBorderLabel("Symbian", RosePalette.COALBLACK, f, 20, Style.Both);
		label1.setLinkColor(RosePalette.LEMONPEEL);
		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(Color.BLACK);
		label1.setShader(fractions, c);
		//s1.addSliceLabel(label1);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);

	}

	/**
	 * create the view portfolio for {@link #Donut2DRadialFillDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DRadialFillDemo", width = 500, height = 250)
	public static View getPortofolio() {
		Donut2DPlugin_view5 demo = new Donut2DPlugin_view5();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
