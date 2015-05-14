/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.legend;

import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.legend.data.DataLegend;
import org.jensoft.core.plugin.legend.data.DataLegendPlugin;
import org.jensoft.core.plugin.legend.data.painter.SquareSymbolPainter;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
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
@JenSoftView(background = ViewDarkBackground.class, description = "Show column data legend with square symbol in south")
public class ColumnDataLegendInSouthWithSquareSymbolView extends View {

	private static final long serialVersionUID = -8150525550869567793L;

	public ColumnDataLegendInSouthWithSquareSymbolView() {
		super();

		// -mean south, west = 100 pixel and north and south 100 pixel.
		// -place holders means width for (west and east) and height for (north and south).
		// place holder for outer's parts is a fixed size in pixel for outer components
		setPlaceHolder(100);
		
		// you can use other method to customize each of them, for example : 
		//setPlaceHolderAxisSouth(placeHolderAxisSouth);

		//the user projection
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());
		
		
		//metrics
		Font font = new Font("lucida console", Font.PLAIN, 10);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		southMetrics.setTextColor(TangoPalette.SCARLETRED3.brighter());

		
		Font fontLegend = new Font("Verdana", Font.PLAIN, 14);
		
		//register the datalegend plugin
		DataLegendPlugin legendPlugin = new DataLegendPlugin();
		proj.registerPlugin(legendPlugin);

		//create and add in plugin the data legend south part
		DataLegend legend = new DataLegend(ViewPart.South);
		legend.setFont(fontLegend);
		legendPlugin.setLegend(legend);
		
		// set margin x to 100 pixel, to be align with device
		legend.setMarginX(100);
		
		//set margin y enough to not collide with south metrics
		legend.setMarginY(50);

		
		//add data items to data legend
		
		DataLegend.Item i1 = new DataLegend.Item(RosePalette.CALYPSOBLUE, "legend 1");
		DataLegend.Item i2 =new DataLegend.Item(RosePalette.CORALRED, "legend 2");
		DataLegend.Item i3 =new DataLegend.Item(RosePalette.LAVENDER, "legend 3");
		DataLegend.Item i4 =new DataLegend.Item(RosePalette.LEMONPEEL, "legend 4");
		DataLegend.Item i5 =new DataLegend.Item(RosePalette.LIGHTBROWN, "legend 5");
		DataLegend.Item i6 =new DataLegend.Item(RosePalette.LIME, "legend 6");
		
		i1.setSymbolPainter(new SquareSymbolPainter());
		
		legend.addItem(i1);
		legend.addItem(i2);
		legend.addItem(i3);
		legend.addItem(i4);
		legend.addItem(i5);
		legend.addItem(i6);

	}

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new ColumnDataLegendInSouthWithSquareSymbolView());
	}

}
