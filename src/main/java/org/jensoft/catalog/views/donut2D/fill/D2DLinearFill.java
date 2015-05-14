/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.fill;

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
import org.jensoft.core.plugin.donut2d.painter.fill.Donut2DDefaultFill;
import org.jensoft.core.plugin.donut2d.painter.label.AbstractDonut2DSliceLabel.Style;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DBorderLabel;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DLinearFill</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to linear fill donut2D.")
public class D2DLinearFill extends View {

	private static final long serialVersionUID = -832557510424174661L;

	/**
	 * Create Donut2D demo with slice linear fill
	 */
	public D2DLinearFill() {
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

		Donut2DDefaultFill defaultFill = new Donut2DDefaultFill();
		donut1.setDonut2DFill(defaultFill);

		final Donut2DSlice s1 = new Donut2DSlice("s1", Spectral.SPECTRAL_RED.brighter());
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", Spectral.SPECTRAL_BLUE2.brighter());
		s2.setValue(20.0);

		final Donut2DSlice s3 = new Donut2DSlice("s3", Spectral.SPECTRAL_PURPLE1.brighter());
		s3.setValue(20.0);

		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		Donut2DBorderLabel label1 = Donut2DToolkit.createBorderLabel("Default Fill", RosePalette.COALBLACK, f, 20, Style.Both);
		label1.setLinkColor(RosePalette.LEMONPEEL);

		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(Color.BLACK);
		label1.setShader(fractions, c);
		s1.addSliceLabel(label1);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2DPlugin.addDonut(donut1);

		registerProjection(proj);
	}
}
