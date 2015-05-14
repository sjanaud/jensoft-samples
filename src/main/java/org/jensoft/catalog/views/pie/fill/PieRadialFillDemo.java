/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.fill;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.marker.MarkerPlugin;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.animator.PieDivergenceAnimator;
import org.jensoft.core.plugin.pie.painter.fill.PieRadialFill;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to fill pie with radial paint.")
public class PieRadialFillDemo extends View {

	private static final long serialVersionUID = -8863125049119393329L;
	
	public PieRadialFillDemo() {
		super();
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();

		Pie pie = new Pie();
		pie.setRadius(90);
		pie.setPieFill(new PieRadialFill());
		pie.setStartAngleDegree(0);

		PieSlice s1 = new PieSlice("gris", new Color(240, 240, 240, 255));
		s1.setValue(45);
		s1.setDivergence(0);

		PieSlice s2 = new PieSlice("bleu", NanoChromatique.ORANGE);
		s2.setValue(8.0);
		s2.setDivergence(0);

		PieSlice s3 = new PieSlice("bleu", RosePalette.COALBLACK);
		s3.setValue(15.0);
		s3.setDivergence(0);


		PieSlice s4 = new PieSlice("purple", Spectral.SPECTRAL_BLUE1);
		s4.setValue(30.0);
		s4.setDivergence(0);

		pie.addSlice(s1);
		pie.addSlice(s2);
		pie.addSlice(s3);
		pie.addSlice(s4);

		piePlugin.addPie(pie);

		pie.addPieAnimator(new PieDivergenceAnimator());

		proj.registerPlugin(piePlugin);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		ZoomLensPlugin objectif = new ZoomLensPlugin();
		proj.registerPlugin(objectif);

		ZoomPercentPlugin percent = new ZoomPercentPlugin();
		proj.registerPlugin(percent);

		MarkerPlugin cross = new MarkerPlugin();
		proj.registerPlugin(cross);
	}
}
