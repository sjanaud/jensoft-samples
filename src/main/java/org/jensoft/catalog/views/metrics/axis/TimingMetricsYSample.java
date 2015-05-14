/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.manager.TimeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class TimingMetricsYSample extends View {

	@Portfolio(name = "Metrics-TimingY", width = 500, height = 250)
	public static View getPortofolio() {
		TimingMetricsYSample demo = new TimingMetricsYSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new TimingMetricsYSample());
	}


	public TimingMetricsYSample() {
		super();
		setPlaceHolder(100, ViewPart.West, ViewPart.East);
		// view.setPlaceHolder(20, WindowPart.South,WindowPart.North);
		Date now = new Date();
		System.out.println("Now  : " + now);
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		System.out.println("calendar month : " + cal.get(Calendar.MONTH));

		Calendar calMin = (Calendar) cal.clone();
		calMin.add(Calendar.HOUR_OF_DAY, -12);
		Date minDate = calMin.getTime();
		System.out.println("date Min : " + minDate);

		Calendar calMax = (Calendar) cal.clone();
		calMax.add(Calendar.HOUR_OF_DAY, +8);
		Date maxDate = calMax.getTime();
		System.out.println("date Max : " + maxDate);

		// Window2D.TimeX txw = new Window2D.TimeX(minDate,
		// maxDate,
		// 0, 100);

		Projection.TimeY proj = new Projection.TimeY(0, 100, minDate, maxDate);
		registerProjection(proj);

		ZoomWheelPlugin wheel = new ZoomWheelPlugin();
		proj.registerPlugin(wheel);
		TranslatePlugin translate = new TranslatePlugin();
		proj.registerPlugin(translate);
		translate.lockSelected();

		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisWest);

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

		//proj.registerPlugin(new DebugPaintWindowPartPlugin());

		// TimeMetricsManager m1nh = new
		// TimeMetricsManager.Minute1TimingNoHolder();
		// m1nh.setTextColor(RosePalette.MANDARIN);
		// m1nh.setMarkerColor(RosePalette.MANDARIN);
		// timingMetrics.registerTimingManager(m1nh);
		//
		// TimeMetricsManager m1 = new TimeMetricsManager.Minute1Model();
		// m1.setTextColor(RosePalette.MANDARIN);
		// m1.setMarkerColor(RosePalette.MANDARIN);
		// timingMetrics.registerTimingManager(m1);
		//
		// TimeMetricsManager m10 = new TimeMetricsManager.Minute10Timing();
		// m10.setTextColor(RosePalette.EMERALD);
		// m10.setMarkerColor(RosePalette.EMERALD);
		// timingMetrics.registerTimingManager(m10);
		//
		// TimeMetricsManager m10nh = new
		// TimeMetricsManager.Minute10TimingNoHolder();
		// m10nh.setTextColor(RosePalette.EMERALD);
		// m10nh.setMarkerColor(RosePalette.EMERALD);
		// timingMetrics.registerTimingManager(m10nh);
		//
		//
		// TimeMetricsManager m15 = new TimeMetricsManager.Minute15Timing();
		// m15.setTextColor(RosePalette.DEEPHARBOR);
		// m15.setMarkerColor(RosePalette.DEEPHARBOR);
		// timingMetrics.registerTimingManager(m15);
		//
		// TimeMetricsManager m15nh = new
		// TimeMetricsManager.Minute15TimingNoHolder();
		// m15nh.setTextColor(RosePalette.DEEPHARBOR);
		// m15nh.setMarkerColor(RosePalette.DEEPHARBOR);
		// //timingMetrics.registerTimingManager(m15nh);
		//
		// TimeMetricsManager m20 = new TimeMetricsManager.Minute20Timing();
		// m20.setTextColor(RosePalette.CORALRED);
		// m20.setMarkerColor(RosePalette.CORALRED);
		// timingMetrics.registerTimingManager(m20);
		//
		// TimeMetricsManager m20nh = new
		// TimeMetricsManager.Minute20TimingNoHolder();
		// m20nh.setTextColor(RosePalette.CORALRED);
		// m20nh.setMarkerColor(RosePalette.CORALRED);
		// timingMetrics.registerTimingManager(m20nh);
		//
		//
		// TimeMetricsManager h = new TimeMetricsManager.HourTiming();
		// h.setTextColor(RosePalette.LEMONPEEL);
		// h.setMarkerColor(RosePalette.LEMONPEEL);
		// timingMetrics.registerTimingManager(h);
		//
		// TimeMetricsManager h3 = new TimeMetricsManager.Hour3Timing();
		// h3.setTextColor(RosePalette.FOXGLOWE);
		// h3.setMarkerColor(RosePalette.FOXGLOWE);
		// timingMetrics.registerTimingManager(h3);
		//
		// TimeMetricsManager day = new TimeMetricsManager.DayTiming();
		// day.setTextColor(RosePalette.LEAFGREEN);
		// day.setMarkerColor(RosePalette.LEAFGREEN);
		// timingMetrics.registerTimingManager(day);
		//
		// TimeMetricsManager daynh = new
		// TimeMetricsManager.DayTimingNoHolder();
		// daynh.setTextColor(RosePalette.LEAFGREEN);
		// daynh.setMarkerColor(RosePalette.LEAFGREEN);
		// timingMetrics.registerTimingManager(daynh);
		//
		// TimeMetricsManager dayText = new TimeMetricsManager.DayTextTiming();
		// dayText.setTextColor(RosePalette.FLAMINGO);
		// dayText.setMarkerColor(RosePalette.FLAMINGO);
		// timingMetrics.registerTimingManager(dayText);
		//
		// TimeMetricsManager dayLongText = new
		// TimeMetricsManager.DayLongTextTiming();
		// dayLongText.setTextColor(RosePalette.FLAMINGO);
		// dayLongText.setMarkerColor(RosePalette.FLAMINGO);
		// timingMetrics.registerTimingManager(dayLongText);
		//
		// TimeMetricsManager week = new TimeMetricsManager.WeekTiming();
		// week.setTextColor(RosePalette.SAFFRON);
		// week.setMarkerColor(RosePalette.SAFFRON);
		// timingMetrics.registerTimingManager(week);
		//
		// TimeMetricsManager weeknh = new
		// TimeMetricsManager.WeekTimingNoHolder();
		// weeknh.setTextColor(RosePalette.SAFFRON);
		// weeknh.setMarkerColor(RosePalette.SAFFRON);
		// timingMetrics.registerTimingManager(weeknh);
		//
		//
		//
		// TimeMetricsManager weekDuration = new
		// TimeMetricsManager.WeekDurationTiming();
		// weekDuration.setTextColor(RosePalette.COBALT);
		// weekDuration.setMarkerColor(RosePalette.COBALT);
		// timingMetrics.registerTimingManager(weekDuration);
		//
		// TimeMetricsManager month = new TimeMetricsManager.MonthTiming();
		// month.setTextColor(RosePalette.MELON);
		// month.setMarkerColor(RosePalette.MELON);
		// // timingMetrics.registerTimingManager(month);
		//
		// TimeMetricsManager monthDuration = new
		// TimeMetricsManager.MonthDurationTiming();
		// monthDuration.setTextColor(RosePalette.NEPTUNE);
		// monthDuration.setMarkerColor(RosePalette.NEPTUNE);
		// timingMetrics.registerTimingManager(monthDuration);

		proj.registerPlugin(timingMetrics);

		proj.registerPlugin(new OutlinePlugin());

	}

}
