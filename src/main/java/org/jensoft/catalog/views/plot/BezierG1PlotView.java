package org.jensoft.catalog.views.plot;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.plot.PlotAnchorsPlugin;
import org.jensoft.core.plugin.plot.PlotPlugin;
import org.jensoft.core.plugin.plot.spline.BezierG1;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.translate.TranslateX;
import org.jensoft.core.plugin.translate.TranslateY;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class,description="Bezier G1 plot")
public class BezierG1PlotView extends View {

	public BezierG1PlotView() {
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
		
		
		BezierG1 plot = new BezierG1();
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
		
		
		//zoom box
		ZoomBoxPlugin box = new ZoomBoxPlugin();
		ZoomBoxDefaultDeviceContext ctx = new ZoomBoxDefaultDeviceContext();
		box.registerContext(ctx);
		
		//translate
		TranslatePlugin translatePlugin = new TranslatePlugin();
		TranslateDefaultDeviceContext ctxt = new TranslateDefaultDeviceContext();
		translatePlugin.registerContext(ctxt);
		proj.registerPlugin(translatePlugin);
		TranslateX tx = new TranslateX();
		TranslateY ty = new TranslateY();
		translatePlugin.registerWidget(tx, ty);
		
		proj.registerPlugin(box);
		
	}


	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new BezierG1PlotView());
	}
	

}
