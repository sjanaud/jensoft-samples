/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.draw;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.StrokePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.draw.PieDefaultDraw;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie draw.")
public class PieDraw2Demo extends View {

	private static final long serialVersionUID = -4095906650973995325L;

	public PieDraw2Demo() {
		super(0);
		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = PieToolkit.createPie("pie", 120);
		PieLinearEffect fx1 = new PieLinearEffect();
		pie.setPieEffect(fx1);

		PieDefaultDraw draw = new PieDefaultDraw();
		draw.setDrawColor(RosePalette.MANDARIN);
		draw.setDrawStroke(StrokePalette.drawingStroke3);
		pie.setPieDraw(draw);

		PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", ColorPalette.alpha(Spectral.SPECTRAL_RED, 240), 5, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", ColorPalette.alpha(Spectral.SPECTRAL_BLUE2, 240), 30, 0);
		PieSlice s4 = PieToolkit.createSlice("s4", ColorPalette.alpha(Spectral.SPECTRAL_PURPLE1, 240), 20, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);

		piePlugin.addPie(pie);
	}
	
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new PieDraw2Demo());
	}
}
