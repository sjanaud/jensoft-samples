
/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */package org.jensoft.catalog.views.legend;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.data.DataLegend;
import org.jensoft.core.plugin.legend.data.DataLegend.Orientation;
import org.jensoft.core.plugin.legend.data.DataLegendPlugin;
import org.jensoft.core.plugin.legend.data.painter.DefaultDataLegendBackgroundPainter;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;


/**
 * <code>RowDataLegendView</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show row data legend background")
public class DataLegendBackgroundView extends View {

private static final long serialVersionUID = -4710088133368319315L;


public DataLegendBackgroundView() {
	super();
	setPlaceHolder(100);
	Projection proj = new Projection.Linear(-10, 10, -10, 10);
	registerProjection(proj);
	proj.registerPlugin(new OutlinePlugin());

	DataLegendPlugin legendPlugin = new DataLegendPlugin();
	proj.registerPlugin(legendPlugin);

	 DataLegend legend = new DataLegend();
	 legendPlugin.setLegend(legend);
     legend.setOrientation(Orientation.Row);
     
     DefaultDataLegendBackgroundPainter background = new DefaultDataLegendBackgroundPainter();
     background.setTexture(TexturePalette.getBeeCarbonTexture1());
     background.setRadius(6);
     background.setOutlineColor(Color.WHITE);
     
     legend.setBackgroundPainter(background);
     
     legend.setMarginY(100);
     
     legend.setPadding(10);
     
     
	 
     legend.addItem(new DataLegend.Item(RosePalette.CALYPSOBLUE.brighter(), "legend 1"));
     legend.addItem(new DataLegend.Item(RosePalette.CORALRED.brighter(), "legend two"));
     legend.addItem(new DataLegend.Item(RosePalette.LAVENDER.brighter(), "legend 3"));
     legend.addItem(new DataLegend.Item(RosePalette.LEMONPEEL.brighter(), "legend four"));
     legend.addItem(new DataLegend.Item(RosePalette.LIGHTBROWN.brighter(), "legend 5"));
     legend.addItem(new DataLegend.Item(RosePalette.LIME.brighter(), "legend 6"));
	
	

		
	
}

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new DataLegendBackgroundView());
	}

}
