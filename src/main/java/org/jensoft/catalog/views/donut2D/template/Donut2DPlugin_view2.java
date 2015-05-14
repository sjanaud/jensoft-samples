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
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.painter.effect.Donut2DLinearEffect;
import org.jensoft.core.plugin.donut2d.painter.label.AbstractDonut2DSliceLabel.Style;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DRadialLabel;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut2DPlugin_view2</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="template for donut2D.")
public class Donut2DPlugin_view2 extends View {

	private static final long serialVersionUID = 8818486001233696909L;

	/**
	 * create Donut2D demo with radial label
	 */
	public Donut2DPlugin_view2() {
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
		donut1.setInnerRadius(30);
		donut1.setOuterRadius(60);
		donut1.setStartAngleDegree(50);

		Donut2DLinearEffect effect = new Donut2DLinearEffect(300);
		effect.setOffsetRadius(2);
		donut1.setDonut2DEffect(effect);

		final Donut2DSlice s1 = new Donut2DSlice("s1", new Color(250, 250, 250));
		s1.setAlpha(0.5f);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", NanoChromatique.ORANGE);
		s2.setValue(20.0);
		s2.setAlpha(0.5f);

		final Donut2DSlice s3 = new Donut2DSlice("s3", new Color(78, 148, 44));
		s3.setValue(20.0);
		s3.setAlpha(0.5f);
		//s3.setDivergence(10);

		float[] fractions = { 0f, 0.5f, 1f };
		Color[] c = { new Color(0, 0, 0, 255), new Color(0, 0, 0, 200), new Color(0, 0, 0, 255) };
		Font font =  new Font("Dialog", Font.PLAIN, 12);
		
		Donut2DRadialLabel label;
		label = Donut2DToolkit.createRadialLabel("JenSoft", RosePalette.COALBLACK, font, 20, Style.Both);
		label.setOutlineRound(15);		
		label.setLabelColor(RosePalette.WHITE);
		label.setOutlineColor(RosePalette.CORALRED);
		label.setOutlineStroke(new BasicStroke(2f));
		label.setShader(fractions, c);
		s1.addSliceLabel(label);
		
		label = Donut2DToolkit.createRadialLabel("Java", RosePalette.COALBLACK, font, 20, Style.Both);
		label.setOutlineRound(15);		
		label.setLabelColor(RosePalette.WHITE);
		label.setOutlineColor(NanoChromatique.ORANGE);
		label.setOutlineStroke(new BasicStroke(2f));
		label.setShader(fractions, c);
		s2.addSliceLabel(label);
		
		label = Donut2DToolkit.createRadialLabel("X2D", RosePalette.COALBLACK, font, 20, Style.Both);
		label.setOutlineRound(15);		
		label.setLabelColor(RosePalette.WHITE);
		label.setOutlineColor(new Color(78, 148, 44));
		label.setOutlineStroke(new BasicStroke(2f));
		label.setShader(fractions, c);
		s3.addSliceLabel(label);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		Donut2D donut2 = new Donut2D();
		donut2.setNature(Donut2DNature.User);
		donut2.setCenterX(0);
		donut2.setCenterY(0);
		donut2.setInnerRadius(40);
		donut2.setOuterRadius(60);
		donut2.setStartAngleDegree(120);

		final Donut2DSlice s4 = new Donut2DSlice("s4", new Color(0, 176, 217));
		s4.setValue(30.0);
		s4.setAlpha(0.4f);
		final Donut2DSlice s5 = new Donut2DSlice("s5", new Color(174, 221, 227));
		s5.setValue(10.0);
		s5.setAlpha(0.7f);
		final Donut2DSlice s6 = new Donut2DSlice("s6", new Color(28, 152, 183));
		s6.setValue(20.0);
		s6.setAlpha(0f);

		donut2.addSlice(s4);
		donut2.addSlice(s5);
		donut2.addSlice(s6);

		Donut2D donut3 = new Donut2D();
		donut3.setNature(Donut2DNature.User);
		donut3.setCenterX(1);
		donut3.setCenterY(0);
		donut3.setInnerRadius(40);
		donut3.setOuterRadius(60);
		donut3.setStartAngleDegree(120);

		final Donut2DSlice s7 = new Donut2DSlice("S7", new Color(240, 90, 40));
		s7.setValue(40.0);
		s7.setAlpha(0.7f);
		final Donut2DSlice s8 = new Donut2DSlice("s8", new Color(251, 148, 9));
		s8.setValue(25);
		s8.setAlpha(0.7f);

		final Donut2DSlice s9 = new Donut2DSlice("s9", new Color(251, 174, 66));
		s9.setValue(25);
		s9.setAlpha(0.5f);

		donut3.addSlice(s7);
		donut3.addSlice(s8);
		donut3.addSlice(s9);

		registerProjection(proj);

	}

	/**
	 * create the view portfolio for {@link #Donut2DRadialLabelDemo()}
	 * 
	 * @return view portfolio
	 */
	@Portfolio(name = "Donut2DRadialLabelDemo", width = 500, height = 250)
	public static View getPortofolio() {
		Donut2DPlugin_view2 demo = new Donut2DPlugin_view2();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
