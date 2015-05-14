/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut3D.label;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut3d.Donut3D;
import org.jensoft.core.plugin.donut3d.Donut3DPlugin;
import org.jensoft.core.plugin.donut3d.Donut3DSlice;
import org.jensoft.core.plugin.donut3d.Donut3DToolkit;
import org.jensoft.core.plugin.donut3d.painter.label.AbstractDonut3DSliceLabel.Style;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DRadialLabel;
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
 * <code>D3DRadialLabel</code>
 * 
 * @author JenSoftAPI
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use radial label on donut3D slice.")
public class D3DRadialLabel extends View {

	private static final long serialVersionUID = 7704712302357394788L;

	/**
	 * Create Donut3D radial label demo
	 */
	public D3DRadialLabel() {
		super(0);
		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		Donut3DPlugin donut3DPlugin = new Donut3DPlugin();
		proj.registerPlugin(donut3DPlugin);

		// DONUT
		Donut3D donut3d = Donut3DToolkit.createDonut3D("myDonut", 60, 110, 50, 60, 40);
		donut3DPlugin.addDonut(donut3d);

		// create sections
		Donut3DSlice s1 = Donut3DToolkit.createDonut3DSlice("s1", new Color(250, 250, 250), 45);
		Donut3DSlice s2 = Donut3DToolkit.createDonut3DSlice("s2", TangoPalette.BUTTER2, 5);
		Donut3DSlice s3 = Donut3DToolkit.createDonut3DSlice("s3", TangoPalette.CHAMELEON2, 30);
		Donut3DSlice s4 = Donut3DToolkit.createDonut3DSlice("s4", TangoPalette.SKYBLUE2, 20);
		// add section in donut
		Donut3DToolkit.pushSlices(donut3d, s1, s2, s3, s4);

		// LABELS
		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		// LABEL 1
		Donut3DRadialLabel label1 = Donut3DToolkit.createRadialLabel("Symbian", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setShader(fractions, c);
		s1.addSliceLabel(label1);

		// LABEL 2
		Donut3DRadialLabel label2 = Donut3DToolkit.createRadialLabel("WiMo", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label2.setLabelColor(ColorPalette.WHITE);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setShader(fractions, c);
		s2.addSliceLabel(label2);

		// LABEL 3
		Donut3DRadialLabel label3 = Donut3DToolkit.createRadialLabel("iPhone", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label3.setLabelColor(ColorPalette.WHITE);
		label3.setOutlineColor(RosePalette.COBALT);
		label3.setShader(fractions, c);
		s3.addSliceLabel(label3);

		// LABEL 4
		Donut3DRadialLabel label4 = Donut3DToolkit.createRadialLabel("Android", RosePalette.COALBLACK, f, 30, 20, Style.Both);
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
