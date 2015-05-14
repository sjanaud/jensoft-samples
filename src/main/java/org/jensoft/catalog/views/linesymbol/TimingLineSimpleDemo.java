/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.linesymbol;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.background.DeviceGradientBackgroundPlugin;
import org.jensoft.core.plugin.background.DeviceNightBackground;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.linesymbol.LineSymbolPlugin;
import org.jensoft.core.plugin.linesymbol.LineSymbolPlugin.LineNature;
import org.jensoft.core.plugin.linesymbol.core.LineSymbolBox;
import org.jensoft.core.plugin.linesymbol.iconline.IconLineSymbol;
import org.jensoft.core.plugin.linesymbol.iconline.LineEntry;
import org.jensoft.core.plugin.linesymbol.painter.LinePainter;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.manager.TimeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePaint;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class TimingLineSimpleDemo extends View {

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new TimingLineSimpleDemo());
	}
	public TimingLineSimpleDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Color red = new Color(254, 206, 12);
		Color green = new Color(125, 186, 39);
		Color orange = new Color(223, 167, 59);

		Date now = new Date();
		long nowMillisecond = now.getTime();

		// 1 s = 1000 ms
		// 1 min = 60 s = 60 * 1000 = 60 000 ms;
		// 1 h = 60 min =60 * 60 * 1000 = 3600 * 1000 = 3 600 000 ms

		long hourMillisecond = 60 * 60 * 1000;
		long minX = nowMillisecond - 12 * hourMillisecond; // now - 12 heures
		long maxX = nowMillisecond + 12 * hourMillisecond; // now + 12 heures

		Projection proj = new Projection.Linear(minX, maxX, 0, 18);
		registerProjection(proj);

		DeviceGradientBackgroundPlugin gradientPlugin = new DeviceNightBackground();
		gradientPlugin.setAlpha(0.9f);
		proj.registerPlugin(gradientPlugin);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextColor(RosePalette.LEMONPEEL);
		westMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		westMetrics.setTextFont(f);
		

		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisSouth);

		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute10Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute15Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute20Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.DayNumberModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.DayShortTextModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.DayLongTextModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.WeekModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.WeekDurationDurationModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.MonthModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.MonthDurationModel());

		proj.registerPlugin(timingMetrics);

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("Care protocol");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, proj.getThemeColor()));
		TitleLegendConstraints constraints = new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth);
		legend.setConstraints(constraints);
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(nowMillisecond, 60 * 60 * 1000 * 4);
		StripePalette bp = new StripePalette();
		bp.addPaint(new StripePaint(new Color(255, 255, 255, 40)));
		bp.addPaint(new StripePaint(new Color(40, 40, 40, 40)));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(nowMillisecond, 60 * 60 * 1000 * 4, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		LineSymbolPlugin lineSymbolPlugin = new LineSymbolPlugin();
		lineSymbolPlugin.setLineNature(LineNature.LineX);

		// line 1
		IconLineSymbol ls1 = new IconLineSymbol();
		ls1.setName("ls1");
		ls1.setThickness(40);
		URL pillURL = getClass().getResource("pill.png");
		ImageIcon pillIcon = new ImageIcon(pillURL);
		ls1.setIconSymbol(pillIcon);
		ls1.addEntry(new LineEntry(nowMillisecond));
		ls1.addEntry(new LineEntry(nowMillisecond + 1000l * 60l * 60l * 3l));
		ls1.setLinePainter(new LinePainter(TangoPalette.BUTTER3));

		// line 2
		IconLineSymbol ls2 = new IconLineSymbol();
		ls2.setName("ls2");
		ls2.setThickness(40);
		URL injectURL = getClass().getResource("inject.png");
		ImageIcon injectIcon = new ImageIcon(injectURL);
		ls2.setIconSymbol(injectIcon);
		ls2.addEntry(new LineEntry((double) nowMillisecond - 1000l * 60l * 60l * 4l));
		ls2.addEntry(new LineEntry(nowMillisecond + 1000l * 60l * 60l * 6l));
		ls2.setLinePainter(new LinePainter(TangoPalette.CHAMELEON3));

		// lineSymbolPlugin.addLineComponent(LineSymbolBox.createVerticalGlue());
		lineSymbolPlugin.addLineComponent(LineSymbolBox.createVerticalStrut(40));
		lineSymbolPlugin.addLineComponent(ls1);
		lineSymbolPlugin.addLineComponent(LineSymbolBox.createVerticalStrut(20));
		lineSymbolPlugin.addLineComponent(ls2);
		lineSymbolPlugin.addLineComponent(LineSymbolBox.createVerticalStrut(40));
		// lineSymbolPlugin.addLineComponent(LineSymbolBox.createVerticalGlue());

		proj.registerPlugin(lineSymbolPlugin);

		registerProjection(proj);

	}

}
