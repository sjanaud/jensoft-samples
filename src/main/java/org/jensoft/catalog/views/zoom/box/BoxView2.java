package org.jensoft.catalog.views.zoom.box;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDonutWidget;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class,description="a zoom lens (view 2) view which is use in zoom box synchronization demo, see SynchronizedZoomBox Dashboard.",ignore=true)
public class BoxView2 extends View {

	private ZoomBoxPlugin box;

	public BoxView2() {

		setPlaceHolder(60, ViewPart.South);
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());
		proj.setThemeColor(RosePalette.LEMONPEEL);

		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		Font font = new Font("lucida console", Font.PLAIN, 10);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		StripePlugin stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 20));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN4, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		box = new ZoomBoxPlugin();
		box.setThemeColor(Color.RED);
		// ZoomBoxBarWidget w1 = new ZoomBoxBarWidget();
		ZoomBoxDonutWidget w2 = new ZoomBoxDonutWidget();
		w2.setShiftEffect(false);
		box.registerWidget(w2);

		proj.registerPlugin(box);
	}

	/**
	 * @return the box
	 */
	public ZoomBoxPlugin getBox() {
		return box;
	}

}
