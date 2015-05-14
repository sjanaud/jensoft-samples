package org.jensoft.catalog.views.plot;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.plot.PlotAnchorsPlugin;
import org.jensoft.core.plugin.plot.PlotPlugin;
import org.jensoft.core.plugin.plot.spline.BSpline;
import org.jensoft.core.plugin.plot.spline.CatmullRom;
import org.jensoft.core.plugin.plot.spline.NatCubic;
import org.jensoft.core.plugin.plot.spline.NatCubicClosed;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class PlotMultipleView extends View {

	public PlotMultipleView() {
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
		
		//CATMULL ROM PLOT
		CatmullRom catRom = new CatmullRom();
		catRom.addPoint(-80, 30);
		catRom.addPoint(-60, -20);
		catRom.addPoint(-40, 70);
		catRom.addPoint(-20, 20);
		
		catRom.addPoint(20, -20);
		catRom.addPoint(40, 50);
		catRom.addPoint(60, -20);
		catRom.addPoint(80, 90);
		
		catRom.setPlotDrawColor(NanoChromatique.BLUE);
		plotsPlugin.addPlot(catRom);
		
		//BSPLINE PLOT
		BSpline bspline = new BSpline();
		bspline.addPoint(-80, 30);
		bspline.addPoint(-60, -20);
		bspline.addPoint(-40, 70);
		bspline.addPoint(-20, 20);
		
		bspline.addPoint(20, -20);
		bspline.addPoint(40, 50);
		bspline.addPoint(60, -20);
		bspline.addPoint(80, 90);
		
		bspline.setPlotDrawColor(NanoChromatique.RED);
		plotsPlugin.addPlot(bspline);
		
		//Natural Cubic
		NatCubic natCubic = new NatCubic();
		natCubic.addPoint(-80, 30);
		natCubic.addPoint(-60, -20);
		natCubic.addPoint(-40, 70);
		natCubic.addPoint(-20, 20);
		
		natCubic.addPoint(20, -20);
		natCubic.addPoint(40, 50);
		natCubic.addPoint(60, -20);
		natCubic.addPoint(80, 90);
		
		natCubic.setPlotDrawColor(NanoChromatique.YELLOW);
		plotsPlugin.addPlot(natCubic);
		
		
		//Natural closed Cubic
		NatCubicClosed natClosedCubic = new NatCubicClosed();
		natClosedCubic.addPoint(-80, 30);
		natClosedCubic.addPoint(-60, -20);
		natClosedCubic.addPoint(-40, 70);
		natClosedCubic.addPoint(-20, 20);
		
		natClosedCubic.addPoint(20, -20);
		natClosedCubic.addPoint(40, 50);
		natClosedCubic.addPoint(60, -20);
		natClosedCubic.addPoint(80, 90);
		
		natClosedCubic.setPlotDrawColor(NanoChromatique.PURPLE);
		plotsPlugin.addPlot(natClosedCubic);
		
		
		//Plot point Handler
		PlotAnchorsPlugin anchorsPlugin = new PlotAnchorsPlugin();
		proj.registerPlugin(anchorsPlugin);
		
		anchorsPlugin.addPlot(catRom);
		anchorsPlugin.addPlot(bspline);
		anchorsPlugin.addPlot(natCubic);
		anchorsPlugin.addPlot(natClosedCubic);
		
		
		//Zoom wheel
		ZoomWheelPlugin wheel = new ZoomWheelPlugin();
		proj.registerPlugin(wheel);
		
	}


	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new PlotMultipleView());
	}
	

}
