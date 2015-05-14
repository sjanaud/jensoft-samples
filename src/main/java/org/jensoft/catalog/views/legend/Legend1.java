/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.legend;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>Legend1</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show how to use outer part title legend.")
public class Legend1 extends View {

	private static final long serialVersionUID = -8148681284682647302L;

	/**
	 * Create demo with legend
	 */
	public Legend1() {
		super();
		setPlaceHolder(100);
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());

		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		TitleLegend l2 = TitleLegendPlugin.createLegend("Leg/south/0.5/middle", Color.ORANGE, new TitleLegendConstraints(LegendPosition.South, 0.5f, LegendAlignment.Middle));
		legendPlugin.addLegend(l2);

		TitleLegend l3 = TitleLegendPlugin.createLegend("Leg/south/0.5/Left", Color.CYAN, Color.BLUE, new TitleLegendConstraints(LegendPosition.South, 0.5f, LegendAlignment.Left));
		legendPlugin.addLegend(l3);

		TitleLegend l4 = TitleLegendPlugin.createLegend("Leg/south/0.9/left", Color.DARK_GRAY, Color.BLACK, new TitleLegendConstraints(LegendPosition.South, 0.9f, LegendAlignment.Left));
		legendPlugin.addLegend(l4);

		TitleLegend l5 = TitleLegendPlugin.createLegend("Leg/west/0.8/left", Color.CYAN, Color.BLACK, new TitleLegendConstraints(LegendPosition.West, 0.8f, LegendAlignment.Left));
		legendPlugin.addLegend(l5);

		TitleLegend l6 = TitleLegendPlugin.createLegend("Leg/west/0.2/middle", Color.RED, Color.BLACK, new TitleLegendConstraints(LegendPosition.West, 0.2f, LegendAlignment.Middle));
		legendPlugin.addLegend(l6);

		TitleLegend l7 = TitleLegendPlugin.createLegend("Leg/west/0.6/right", Color.ORANGE, Color.BLUE, new TitleLegendConstraints(LegendPosition.West, 0.6f, LegendAlignment.Rigth));
		legendPlugin.addLegend(l7);

		TitleLegend l8 = TitleLegendPlugin.createLegend("Leg/north/0.2/left", Color.ORANGE, Color.BLUE, new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Left));
		legendPlugin.addLegend(l8);

		TitleLegend l9 = TitleLegendPlugin.createLegend("Leg/north/0.6/middle", f, Color.ORANGE, Color.BLUE, new TitleLegendConstraints(LegendPosition.North, 0.6f, LegendAlignment.Middle));
		legendPlugin.addLegend(l9);

		TitleLegend l10 = TitleLegendPlugin.createLegend("Leg/north/0.1/right", f, Color.GREEN, Color.DARK_GRAY, new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));
		legendPlugin.addLegend(l10);

		TitleLegend l11 = TitleLegendPlugin.createLegend("Leg/east/0.3/left", f, FilPalette.PINK1, FilPalette.PURPLE1, new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		legendPlugin.addLegend(l11);

	}

	/**
	 * create image view portfolio for {@link #LegendDemo1()}
	 * 
	 * @return image portfolio
	 */
	@Portfolio(name = "LegendDemo1", width = 800, height = 600)
	public static View getPortofolio() {
		Legend1 demo = new Legend1();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
