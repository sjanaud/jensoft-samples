/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.legend;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.data.DataLegend;
import org.jensoft.core.plugin.legend.data.DataLegendPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;


/**
 * <code>ColumnDataLegendView</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show column data legend in east part")
public class ColumnDataLegendInEastView extends View {


private static final long serialVersionUID = -8150525550869567793L;

public ColumnDataLegendInEastView() {
	super();
	setPlaceHolder(100);
	Projection proj = new Projection.Linear(-10, 10, -10, 10);
	registerProjection(proj);
	proj.registerPlugin(new OutlinePlugin());

	DataLegendPlugin legendPlugin = new DataLegendPlugin();
	proj.registerPlugin(legendPlugin);

	 DataLegend legend = new DataLegend(ViewPart.East);
	 legendPlugin.setLegend(legend);
     
	 
     legend.addItem(new DataLegend.Item(RosePalette.CALYPSOBLUE, "legend 1"));
     legend.addItem(new DataLegend.Item(RosePalette.CORALRED, "legend 2"));
     legend.addItem(new DataLegend.Item(RosePalette.LAVENDER, "legend 3"));
     legend.addItem(new DataLegend.Item(RosePalette.LEMONPEEL, "legend 4"));
     legend.addItem(new DataLegend.Item(RosePalette.LIGHTBROWN, "legend 5"));
     legend.addItem(new DataLegend.Item(RosePalette.LIME, "legend 6"));
	
	
	
	

	
}

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new ColumnDataLegendInEastView());
	}

}
