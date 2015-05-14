package org.jensoft.catalog.views.zoom.objectif;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class,description="a zoom lens (view 1) view which is use in zoom lens synchronization demo, see SynchronizedZoomLens Dashboard.",ignore=true)
public class ObjectifView1 extends View {

	private ZoomLensPlugin objectif;

	public ObjectifView1() {
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());
		proj.setThemeColor(RosePalette.LEMONPEEL);

		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		TitleLegend l1 = TitleLegendPlugin.createLegend("Zoom Lens Synchronizer", RosePalette.COALBLACK, RosePalette.MANDARIN.darker());
		legendPlugin.addLegend(l1);

		Font f = new Font("Dialog", Font.PLAIN, 12);
		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextColor(RosePalette.LEMONPEEL);
		southMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		southMetrics.setTextFont(f);

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextColor(RosePalette.LEMONPEEL);
		westMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		westMetrics.setTextFont(f);

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

		objectif = new ZoomLensPlugin();
		objectif.registerContext(new LensDefaultDeviceContext());
		LensX ox = new LensX();
		LensY oy = new LensY();
		// register widget in zoom objectif plugin
		objectif.registerWidget(ox);
		objectif.registerWidget(oy);

		ox.setOutlineColor(Color.BLACK);
		ox.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		ox.setButton2DrawColor(RosePalette.CALYPSOBLUE);
		oy.setOutlineColor(Color.BLACK);
		oy.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		oy.setButton2DrawColor(RosePalette.CALYPSOBLUE);

		proj.registerPlugin(objectif);
	}

	/**
	 * @return the objectif
	 */
	public ZoomLensPlugin getObjectif() {
		return objectif;
	}

}
