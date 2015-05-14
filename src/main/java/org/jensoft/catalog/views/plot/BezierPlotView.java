package org.jensoft.catalog.views.plot;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.plot.PlotAnchorsPlugin;
import org.jensoft.core.plugin.plot.PlotPlugin;
import org.jensoft.core.plugin.plot.spline.Bezier;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class,description="Bezier plot")
public class BezierPlotView extends View {

	public BezierPlotView() {
		super(80);
		
		
		Projection proj = new Projection.Linear(-100, 100, -100, 100);
		proj.setThemeColor(NanoChromatique.GREEN);
		registerProjection(proj);
		
		proj.registerPlugin(new OutlinePlugin());
		
		AxisMetricsPlugin.ModeledMetrics metrics = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisSouth);
		proj.registerPlugin(metrics);
		
		AxisMetricsPlugin.ModeledMetrics metrics2 = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisWest);
		proj.registerPlugin(metrics2);
		
		
		PlotPlugin plotsPlugin = new PlotPlugin();
		proj.registerPlugin(plotsPlugin);
		
		Bezier plot = new Bezier();
		plot.addPoint(-80, 30);
		plot.addPoint(-60, -20);
		plot.addPoint(-40, 70);
		plot.addPoint(-20, 20);
		
		plot.addPoint(20, -20);
		plot.addPoint(40, 50);
		plot.addPoint(60, -20);
		plot.addPoint(80, 90);
		
		plot.setPlotDrawColor(NanoChromatique.BLUE);
		plotsPlugin.addPlot(plot);
				
		
		//Plot point Handler
		PlotAnchorsPlugin anchorsPlugin = new PlotAnchorsPlugin();
		proj.registerPlugin(anchorsPlugin);
		
		anchorsPlugin.addPlot(plot);
		
		
		//Zoom wheel
		ZoomWheelPlugin wheel = new ZoomWheelPlugin();
		proj.registerPlugin(wheel);
		
	}


	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new BezierPlotView());
	}
	

}
