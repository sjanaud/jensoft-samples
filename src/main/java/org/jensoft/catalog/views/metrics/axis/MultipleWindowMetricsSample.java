/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.window.ProjectionShiftPlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class MultipleWindowMetricsSample extends View {

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new MultipleWindowMetricsSample());
	}
	public MultipleWindowMetricsSample() {

		setPlaceHolderAxisSouth(120);
		setPlaceHolderAxisWest(80);

		Projection projection1 = new Projection.Linear(-3000, 3000, -2500, 2500);
		projection1.setThemeColor(NanoChromatique.GREEN);

		Projection projection2 = new Projection.Linear(-1000, 1000, -800, 800);
		projection2.setThemeColor(NanoChromatique.RED);

		Projection projection3 = new Projection.Linear(-320, 1200, -300, 80);
		projection3.setThemeColor(NanoChromatique.BLUE);
		
		projection1.registerPlugin(new ProjectionShiftPlugin());
		projection2.registerPlugin(new ProjectionShiftPlugin());
		projection3.registerPlugin(new ProjectionShiftPlugin());
		

		AxisMetricsPlugin proj1MetricsWest = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisWest);
		projection1.registerPlugin(proj1MetricsWest);

		AxisMetricsPlugin.ModeledMetrics proj1MetricsSouth = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisSouth);//Multiplier3Metrics(0, Axis.AxisSouth);
		proj1MetricsSouth.setAxisSpacing(0);
		proj1MetricsSouth.setBaseLinePaint(true);
		projection1.registerPlugin(proj1MetricsSouth);

		AxisMetricsPlugin proj2MetricsSouth = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisSouth);
		proj2MetricsSouth.setAxisSpacing(40);
		proj2MetricsSouth.setBaseLinePaint(true);
		projection2.registerPlugin(proj2MetricsSouth);

		AxisMetricsPlugin proj3MetricsSouth = new AxisMetricsPlugin.ModeledMetrics(Axis.AxisSouth);
		proj3MetricsSouth.setAxisSpacing(80);
		proj3MetricsSouth.setBaseLinePaint(true);
		projection3.registerPlugin(proj3MetricsSouth);

		//projection1.registerPlugin(new OutlinePlugin());
		//projection2.registerPlugin(new OutlinePlugin());
		//projection3.registerPlugin(new OutlinePlugin());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Multi Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, projection1.getThemeColor()));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		projection1.registerPlugin(lgendL);
		registerProjection(projection1);
		registerProjection(projection2);
		registerProjection(projection3);

		// zoom wheel
		ZoomWheelPlugin wheelPlugin1 = new ZoomWheelPlugin();
		ZoomWheelPlugin wheelPlugin2 = new ZoomWheelPlugin();
		ZoomWheelPlugin wheelPlugin3 = new ZoomWheelPlugin();
		projection1.registerPlugin(wheelPlugin1);
		projection2.registerPlugin(wheelPlugin2);
		projection3.registerPlugin(wheelPlugin3);

		// zooms box
		ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		zoomPlugin.registerContext(new ZoomBoxDefaultDeviceContext());
		projection1.registerPlugin(zoomPlugin);

		// zoom lens
		ZoomLensPlugin lensPlugin = new ZoomLensPlugin();
		lensPlugin.registerContext(new LensDefaultDeviceContext());
		// create two objectif for x and y dimension
		LensX ox = new LensX();
		LensY oy = new LensY();
		// register widget in zoom objectif plugin
		lensPlugin.registerWidget(ox);
		lensPlugin.registerWidget(oy);
		projection1.registerPlugin(lensPlugin);

		ox.setOutlineColor(Color.BLACK);
		ox.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		ox.setButton2DrawColor(RosePalette.CALYPSOBLUE);
		oy.setOutlineColor(Color.BLACK);
		oy.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		oy.setButton2DrawColor(RosePalette.CALYPSOBLUE);

		// translate
		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		projection1.registerPlugin(translatePlugin);

		setActiveProjection(projection1);

	}

	@Portfolio(name = "Metrics-MultipleWindow-Multiplier", width = 500, height = 250)
	public static View getPortofolio() {
		MultipleWindowMetricsSample demo = new MultipleWindowMetricsSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
