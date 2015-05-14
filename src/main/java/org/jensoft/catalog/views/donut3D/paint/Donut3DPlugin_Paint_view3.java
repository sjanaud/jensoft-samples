/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut3D.paint;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.donut3d.Donut3D;
import org.jensoft.core.plugin.donut3d.Donut3DPlugin;
import org.jensoft.core.plugin.donut3d.Donut3DSlice;
import org.jensoft.core.plugin.donut3d.Donut3DToolkit;
import org.jensoft.core.plugin.donut3d.painter.label.AbstractDonut3DSliceLabel.Style;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DRadialLabel;
import org.jensoft.core.plugin.donut3d.painter.paint.Donut3DDefaultPaint;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut3DPlugin_Paint_view3</code>
 * 
 * @author JenSoftAPI
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to paint for donut3D.")
public class Donut3DPlugin_Paint_view3 extends View {

	private static final long serialVersionUID = 3272205461590104782L;

	/**
	 * Create Donut3D radial label demo
	 */
	public Donut3DPlugin_Paint_view3() {
		super(0);
		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		Donut3DPlugin donut3DPlugin = new Donut3DPlugin();
		proj.registerPlugin(donut3DPlugin);

		
		Donut3D donut3d = Donut3DToolkit.createDonut3D("myDonut", 70, 120, 50, 60, 40);
		donut3DPlugin.addDonut(donut3d);

		Donut3DDefaultPaint paint = new Donut3DDefaultPaint();
		paint.setAlphaFill(1f);
		paint.setAlphaOuter(0.5f);
		paint.setAlphaInner(0.5f);
		paint.setAlphaTop(0.4f);
		
		donut3d.setDonut3DPaint(paint);
		
		// create sections
		Donut3DSlice s1 = Donut3DToolkit.createDonut3DSlice("s1", new Color(250, 250, 250), 45);
		Donut3DSlice s2 = Donut3DToolkit.createDonut3DSlice("s2", NanoChromatique.ORANGE, 8);
		Donut3DSlice s3 = Donut3DToolkit.createDonut3DSlice("s3", RosePalette.COALBLACK, 15);
		Donut3DSlice s4 = Donut3DToolkit.createDonut3DSlice("s4", Spectral.SPECTRAL_BLUE1, 30);
		// add section in donut
		Donut3DToolkit.pushSlices(donut3d, s1, s2, s3, s4);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		s3.setDivergence(30);
		s3.setThemeColor(new Alpha(s3.getThemeColor(),120));
		
		// LABELS
		float[] fractions = { 0f, 0.5f, 1f };
		Color[] c = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };

		// LABEL 1
		Donut3DRadialLabel label1 = Donut3DToolkit.createRadialLabel("Silver", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setShader(fractions, c);
		s1.addSliceLabel(label1);

		// LABEL 2
		Donut3DRadialLabel label2 = Donut3DToolkit.createRadialLabel("Gold", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label2.setLabelColor(ColorPalette.WHITE);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setShader(fractions, c);
		s2.addSliceLabel(label2);

		// LABEL 3
		Donut3DRadialLabel label3 = Donut3DToolkit.createRadialLabel("Platinium", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label3.setLabelColor(ColorPalette.WHITE);
		label3.setOutlineColor(RosePalette.COBALT);
		label3.setShader(fractions, c);
		s3.addSliceLabel(label3);

		// LABEL 4
		Donut3DRadialLabel label4 = Donut3DToolkit.createRadialLabel("Rhodium", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label4.setLabelColor(ColorPalette.WHITE);
		label4.setOutlineColor(RosePalette.EMERALD);
		label4.setShader(fractions, c);
		s4.addSliceLabel(label4);

		// LEGEND
		TitleLegend legend = new TitleLegend("Radial Slice Label");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.BLUE1));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlg = new TitleLegendPlugin();
		legendPlg.addLegend(legend);
		proj.registerPlugin(legendPlg);

	}
}
